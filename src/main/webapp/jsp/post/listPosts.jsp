<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Blog</title>
 
 <script type="text/javascript">
	function del(id) {
		if (confirm('Confirma exclusão?')) {
			document.location.href = "${pageContext.request.contextPath}/jsp/post!deletePost.action?post.id=" + id;
		}
	}
</script>
 
<script type="text/javascript">

	$(document).ready(function() {

		$('#btnInsert').qtip( {
			content : 'Inserir um novo post',
			style : {
				name : 'cream',
				padding : '7px 13px',
				width : {
					max : 210,
					min : 0
				},
				tip : true
			}
		});

		$('#menu2').css("background-color", "#4198f6");
	});
</script>
 
</head>
<body>
 
<div class="divTable" style="width: 900px">
 	 
	<s:iterator value="posts" status="id">
		<table>
			<tr>
				<td width="80%" style="text-align: left">
					<span class="postTitulo">${titulo}</span><br />
					<span class="postFooter">${data} - ${usuario.nome}</span><br/><br/>
				</td>
				<td>
					<a href="${pageContext.request.contextPath}/jsp/post!editPost.action?post.id=${id}">editar</a>
					<a href="javascript:del('${id}');">remover</a>
				</td>
			</tr>
			<tr>
				<td colspan="2"><span class="postTexto">${texto}</span></td>
			</tr>								
		</table>
	</s:iterator>

</div>
 
<center>
<div class="sepDiv">
	<button id="btnInsert" onclick="location.href='${pageContext.request.contextPath}/jsp/post/insertPost.jsp'">Novo post</button>
</div>
</center>
 
</body>
</html>