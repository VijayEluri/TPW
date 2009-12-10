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
 
    $(document).ready(function() {
       $('#menu2').css("background-color", "#4198f6");
    });
 
</script>
 
</head>
<body>
 
<div class="divTable">
 
	<h3>Blog</h3>
<!--	 
	<display:table list="${blog}" class="defaultTable" pagesize="100" cellspacing="0" cellpadding="0"
	sort="list" id="post" requestURI="${pageContext.request.contextPath}/jsp/usuario!listUsuarios.action">
	 
		<display:column title="Id" property="id" style="width: 50px" />
	 	<display:column title="Titulo" property="titulo" style="width: 100px" />
		<display:column title="Data" property="data" style="width: 100px" />
		<display:column title="Texto" property="texto" style="width: 400px"/>
		<display:column title="Usuario" property="usuario.login" style="width: 100px"/>
		<display:column title="Editar" style="width: 50px">
	         <a href="${pageContext.request.contextPath}/jsp/usuario!editPost.action">editar</a>
	    </display:column>
	    <display:column title="Remover" style="width: 50px">
	         <a href="javascript:del('${usuario.login}');">remover</a>
	    </display:column>
	 
	</display:table>
  -->
</div>
 
<center>
<div class="sepDiv">
	<button id="btnInsert" onclick="location.href='${pageContext.request.contextPath}/jsp/post/insertPost.jsp'">Inserir post</button>
</div>
</center>
 
</body>
</html>