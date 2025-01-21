package kr.or.ddit.yguniv.paging.renderer;

import kr.or.ddit.yguniv.paging.PaginationInfo;

public class DefaultPaginationRenderer implements PaginationRenderer {

	@Override
	public String renderPagination(PaginationInfo paging, String fnName) {
		int startPage = paging.getStartPage();
		int endPage = paging.getEndPage();
		int totalPage = paging.getTotalPage();
		int blockSize = paging.getBlockSize();
		int currentPage = paging.getCurrentPage();
		
		endPage = endPage > totalPage ? totalPage : endPage;
		
		StringBuffer html = new StringBuffer();
		
		String pattern = "<a href='javascript:void(0);' onclick='"+fnName+"(%d);'>%s</a>";
		if(startPage > blockSize) {
			html.append(
				String.format(pattern, startPage-blockSize, "이전")
			);
		}
		for(int page = startPage; page <= endPage; page++) {
			if(page == currentPage) {
				html.append(
					String.format("[%d]", page)	
				);
			}else {
				html.append(
					String.format(pattern, page, page)
				);
			}
				
		}
		if(endPage < totalPage) {
			html.append(
				String.format(pattern, endPage+1, "다음")
			);
		}
		
		return html.toString();
	}

}











