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

		$('#trash').qtip( {
			content : 'Excluir post',
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

		$('#edit').qtip( {
			content : 'Editar post',
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
		<div class="postTitulo">
			<span>${titulo}</span>
						
			<% if (session.getAttribute("tipoUsuario") != null && session.getAttribute("tipoUsuario").equals("ADMINISTRADOR")) { %>
				<a href="${pageContext.request.contextPath}/jsp/post!editPost.action?post.id=${id}"><img id="edit" src="/tpw/images/edit.png" height="23px" /></a>
				<a href="javascript:del('${id}');"><img id="trash" src="/tpw/images/trash.png" height="23px" /></a>				
			<% } %>
		</div>
		<div>
			<span class="postTexto">${texto}</span>
		</div>
		
		<div class="postFooter">
			Postado por: ${usuario.nome} - data: ${data}
		</div>										
	</s:iterator>

</div>

<% if (session.getAttribute("tipoUsuario") != null && session.getAttribute("tipoUsuario").equals("ADMINISTRADOR")) { %> 
<center>
<div class="sepDiv">
	<button id="btnInsert" onclick="location.href='${pageContext.request.contextPath}/jsp/post/insertPost.jsp'">Novo post</button>
</div>
</center>
<% } %>
 
</body>
</html>