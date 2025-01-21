<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<script type="importmap">
{
	"imports": {
		"ckeditor5": "${pageContext.request.contextPath }/resources/js/ckeditor5/ckeditor5.js",
		"ckeditor5/": "${pageContext.request.contextPath }/resources/js/ckeditor5/translations/ko.js/"
	}
}
</script>

<input type="hidden" name="adminId" value="${adminId}" />

<style>
textarea.form-control {
    height: 200px;
}

table {
    width: 100%;
    border-collapse: collapse;
}

th{
    text-align: center;
    padding: 10px;
}

.content-editor {
    height: 500px;
    resize: none;
}
</style>

<form:form id="updateForm" method="POST" enctype="multipart/form-data" 
	modelAttribute="systemBoard">
	<table class="table table-primary">
		<tr>
			<th>제목</th>
			<td class="bg-light"><form:input path="snbTtl" cssClass="form-control" required="true" /></td>
		</tr>
		<tr>
			<th>기존 파일</th>
			<td class="bg-light">
				<c:forEach items="${systemBoard.atchFile.fileDetails}" var="fd" varStatus="vs">
					<span>
						${fd.orignlFileNm} [${fd.fileFancysize}]
						<a data-atch-file-id="${fd.atchFileId}" data-file-sn="${fd.fileSn}" class="btn btn-danger" href="javascript:;">
							삭제
						</a>
						${not vs.last ? '|' : ''}
					</span>
				</c:forEach>
			</td>
		</tr>
		<tr>
			<th>파일 업로드</th>
			<td class="bg-light"><input type="file" name="uploadFiles" multiple class="form-control" /></td>
		</tr>
		<tr>
			<td colspan="2" class="bg-light">
				<form:textarea path="snbCn" id="editor" placeholder="내용을 입력하세요" class="form-control" />


			</td>
		</tr>
		<tr>
			<td colspan="2" align="right" class="bg-light">
				<input type="submit" value="저장" class="btn btn-primary" />
			</td>
		</tr>
	</table>
</form:form>
<form action="<c:url value='/systemBoard/delete/${systemBoard.snbNo}' />" method="post" id="deleteForm">
<!-- 	POST 요청에 포함된 hidden "_method" 파라미터로 브라우저가 지원하지 않는 put/delete 등의 요청 메소드를 대신 표현할 수 있음. -->
<!-- 	단, 서버측에서 해당 파라미터로 요청의 메소드를 변경할 수 있는 Filter 등이 필요함.(web.xml 참고) -->
<!-- 	<input type="hidden" name="_method" required value="delete"/> -->
</form>

<script src="${pageContext.request.contextPath }/resources/js/board/boardEdit.js"></script>
<script src="${pageContext.request.contextPath }/resources/js/board/ck.js"></script>












