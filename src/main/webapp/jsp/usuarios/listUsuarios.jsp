<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Usuários</title>
 
<script type="text/javascript">
	function del(login) {
		if (confirm('Confirma exclusão?')) {
			document.location.href = "${pageContext.request.contextPath}/jsp/usuario!deleteUsuario.action?usuario.login=" + login;
		}
	}
</script>
 
<script type="text/javascript">
 
    $(document).ready(function() {
 
       $('#btnInsert').qtip({
          content: 'Inserir um novo usuário',
          style: {
             name: 'cream',
             padding: '7px 13px',
             width: {
                max: 210,
                min: 0
             },
            tip: true
         }
       });
 
    });
 
</script>
 
</head>
<body>
 
<div class="divTable">
 
	<h3>Usuários</h3>
	 
	<display:table list="${usuarios}" class="defaultTable" pagesize="100" cellspacing="0" cellpadding="0"
	sort="list" id="usuario" requestURI="${pageContext.request.contextPath}/jsp/usuario!listUsuarios.action">
	 
		<display:column title="Nome" property="nome" style="width: 300px" />
		<display:column title="Login" property="login" style="width: 100px" />
		<display:column title="Tipo" property="tipoUsuario" style="width: 100px" />
		<display:column title="Email" property="email" style="width: 200px"/>
		<display:column title="Editar" style="width: 50px">
	         <a href="${pageContext.request.contextPath}/jsp/usuario!editUsuario.action?usuario.login=${usuario.login}">editar</a>
	    </display:column>
	    <display:column title="Remover" style="width: 50px">
	         <a href="javascript:del('${usuario.login}');">remover</a>
	    </display:column>
	 
	</display:table>
 
</div>
 
<center>
<div class="sepDiv">
	<button id="btnInsert" onclick="location.href='${pageContext.request.contextPath}/jsp/usuarios/insertUsuario.jsp'">Inserir usuário</button>
</div>
</center>
 
</body>
</html>