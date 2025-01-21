package kr.or.ddit.yguniv.projectboard.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import kr.or.ddit.yguniv.atch.service.AtchFileService;
import kr.or.ddit.yguniv.commons.exception.PKDuplicatedException;
import kr.or.ddit.yguniv.noticeboard.exception.BoardException;
import kr.or.ddit.yguniv.paging.PaginationInfo;
import kr.or.ddit.yguniv.projectboard.dao.ProjectBoardMapper;
import kr.or.ddit.yguniv.vo.AtchFileDetailVO;
import kr.or.ddit.yguniv.vo.AtchFileVO;
import kr.or.ddit.yguniv.vo.ProjectBoardVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectBoardServiceImpl implements ProjectBoardService {
	private final ProjectBoardMapper mapper;
	private final AtchFileService atchFileService;
	
	@Value("#{dirInfo.fsaveDir}")
	private Resource saveFolderRes;
	private File saveFolder;

	@PostConstruct
	public void init() throws IOException {
		this.saveFolder = saveFolderRes.getFile();
	}
	
	
	@Override
	public int createProjectBoard(ProjectBoardVO projectBoard) {
		String notiYn = projectBoard.getPbNoti();
		if(notiYn.equalsIgnoreCase("Y")) {
			int result = checkDuplicate(notiYn);
			if(result>0) {
				throw new PKDuplicatedException("이미 공지로 설정된 게시글이 있습니다.");
			}
		}
		Integer atchFileId = Optional.ofNullable(projectBoard.getAtchFile())
				.filter(af->! CollectionUtils.isEmpty(af.getFileDetails()))
				.map(af -> {
					atchFileService.createAtchFile(af, saveFolder);
					return af.getAtchFileId();
				}).orElse(null);
		
		projectBoard.setAtchFileId(atchFileId);
		int res = mapper.insertProjectBoard(projectBoard);
		if(res>0) {
			return projectBoard.getPbNo();
		}
		
		return 0;
	}

	@Override
	public int modifyProjectBoard(final ProjectBoardVO projectBoard) {
		
		String notiYn = projectBoard.getPbNoti();
		if(notiYn.equalsIgnoreCase("Y")) {
			int result = checkDuplicate(notiYn);
			if(result>0) {
				throw new PKDuplicatedException("이미 공지로 설정된 게시글이 있습니다.");
			}
		}
		
		final ProjectBoardVO saved = readProjectBoard(projectBoard.getPbNo());
		
		Integer newAtchFileId = Optional.ofNullable(projectBoard.getAtchFile())
				.filter(af -> af.getFileDetails() != null)
				.map(af ->mergeSavedDetailsAndNewDetails(saved.getAtchFile() , af))
				.orElse(null);
		
		projectBoard.setAtchFileId(newAtchFileId);
		
		return mapper.updateProjectBoard(projectBoard);
	}

	@Override
	public ProjectBoardVO readProjectBoard(int pbNo) {
		ProjectBoardVO board = mapper.selectProjectBoard(pbNo);
		if(board == null) {
			throw new BoardException(String.format("%d 번 글이 없음.", pbNo));
		}
		mapper.incrementHit(pbNo);
		return board;
	}

	@Override
	public List<ProjectBoardVO> readProjectBoardList(PaginationInfo<ProjectBoardVO> paging, String teamCd) {

		return mapper.selectProjectBoardList(paging, teamCd);
	}

	@Override
	public int removeProjectBoard(int pbNo) {
		ProjectBoardVO board = mapper.selectProjectBoard(pbNo);
		
		Optional.ofNullable(board.getAtchFileId())
		.ifPresent(fid -> atchFileService.disableAtchFile(fid));
		
		
		return mapper.deleteProjectBoard(pbNo);
	}

	@Override
	public AtchFileDetailVO download(int atchFileId, int fileSn) {
		return Optional.ofNullable(atchFileService.readAtchFileDetail(atchFileId, fileSn, saveFolder))
				.filter(fd -> fd.getSavedFile().exists())
				.orElseThrow(() -> new BoardException(String.format("[%d, %d]해당 파일이 없음.", atchFileId, fileSn)));
	}

	@Override
	public void removeFile(int atchFileId, int fileSn) {
		atchFileService.removeAtchFileDetail(atchFileId, fileSn, saveFolder);

	}
	

	/**
	 * 기존의 첨부파일 그룹이 있는 경우, 신규 파일과 기존 파일 그룹을 병합해 저장함.
	 * 
	 * @param atchFileId
	 */
	private Integer mergeSavedDetailsAndNewDetails(AtchFileVO savedAtchFile, AtchFileVO newAtchFile) {
		// 새로 병합할 파일 정보 설정
		AtchFileVO mergeAtchFile = new AtchFileVO();
		
		// 기존 파일 그룹과 신규 파일 그룹 병합
		List<AtchFileDetailVO> fileDetails = Stream.concat(
			Optional.ofNullable(savedAtchFile)
					.filter(saf->!CollectionUtils.isEmpty(saf.getFileDetails()))
					.map(saf->saf.getFileDetails().stream())
					.orElse(Stream.empty())
			, Optional.ofNullable(newAtchFile)
					.filter(naf->!CollectionUtils.isEmpty(naf.getFileDetails()))
					.map(naf->naf.getFileDetails().stream())
					.orElse(Stream.empty())
		).collect(Collectors.toList());		
		
		 // 병합된 파일 리스트 설정		
		mergeAtchFile.setFileDetails(fileDetails);
		
		 // 병합된 파일이 존재할 경우 저장
		if( ! mergeAtchFile.getFileDetails().isEmpty() ) {
			atchFileService.createAtchFile(mergeAtchFile, saveFolder);
		}
		
		if (savedAtchFile != null && savedAtchFile.getFileDetails() != null) {
			// 기존 첨부파일 그룹은 비활성화
			atchFileService.disableAtchFile(savedAtchFile.getAtchFileId());
		}

		return mergeAtchFile.getAtchFileId();
	}


	@Override
	public int checkDuplicate(String teamCd) {

		return mapper.checkDuplicate(teamCd);
	}


	@Override
	public ProjectBoardVO readNoti(String teamCd) {
		
		return mapper.selectNoti(teamCd);
	}
	
	
}
