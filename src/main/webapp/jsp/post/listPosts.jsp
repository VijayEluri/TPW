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
 
<div class="divTable">
 
	<h3>Blog</h3>
	 
	<display:table list="${posts}" class="defaultTable" pagesize="100" cellspacing="0" cellpadding="0"
	sort="list" id="post" requestURI="${pageContext.request.contextPath}/jsp/post!listPosts.action">
	 
	 	<display:column title="Titulo" property="titulo" style="width: 100px" />
		<display:column title="Data" property="data" style="width: 100px" />
		<display:column title="Texto" property="texto" style="width: 400px"/>
		<display:column title="Editar" style="width: 50px">
	         <a href="${pageContext.request.contextPath}/jsp/post!editPost.action?post.id=${post.id}">editar</a>
	    </display:column>
	    <display:column title="Remover" style="width: 50px">
	         <a href="javascript:del('${post.id}');">remover</a>
	    </display:column>
	 
	</display:table>
</div>
 
<center>
<div class="sepDiv">
	<button id="btnInsert" onclick="location.href='${pageContext.request.contextPath}/jsp/post/insertPost.jsp'">Inserir post</button>
</div>
</center>
 
</body>
</html>