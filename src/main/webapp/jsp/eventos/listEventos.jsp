<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Eventos</title>
 
<script type="text/javascript">
	function del(id) {
		if (confirm('Confirma exclusão?')) {
			document.location.href = "${pageContext.request.contextPath}/jsp/evento!deleteEvento.action?evento.id=" + id;
		}
	}

	function exitEvent (id) {
		if (confirm('Confirma Nao Participacao?')) {
			document.location.href = "${pageContext.request.contextPath}/jsp/evento!exitEvento.action?evento.id=" + id;
		}
	}
</script>
 
<script type="text/javascript">
 
    $(document).ready(function() {
 
       $('#btnInsert').qtip({
          content: 'Inserir um novo evento',
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

       $('#menu4').css("background-color", "#4198f6");
 
    });
 
</script>
 
</head>
<body>
 
<div class="divTable">
 
	<h3>Eventos</h3>
	 
	<display:table list="${eventos}" class="defaultTable" pagesize="100" cellspacing="0" cellpadding="0"
	sort="list" id="evento" requestURI="${pageContext.request.contextPath}/jsp/evento!listEventos.action">
	 
		<display:column title="Nome" property="nome" style="width: 100px" />
		<display:column title="Descricao" property="descricao" style="width: 200px" />
		<display:column title="Data" property="data" style="width: 100px" />
		<display:column title="Responsavel" property="responsavel" style="width: 100px"/>
		
		<% if ((session.getAttribute("login") != null)) { %>
			<% if (session.getAttribute("tipoUsuario").equals("ADMINISTRADOR")) {	%>
				<display:column title="Editar" style="width: 50px">
	        		<a href="${pageContext.request.contextPath}/jsp/evento!editEvento.action?evento.id=${evento.id}">editar</a>
				</display:column>
				<display:column title="Remover" style="width: 50px">
					<a href="javascript:del('${evento.id}');">remover</a>
				</display:column>
	    	<% } else { %>
	    		<display:column title="Participar" style="width: 50px">
	    			<s:if test="${evento.usuarioInscrito}=='false'">
	    				<a href="${pageContext.request.contextPath}/jsp/evento!enterEvento.action?evento.id=${evento.id}">Participar</a>
	    			</s:if>		
				</display:column>
				<display:column title="Nao Participar" style="width: 50px">
					<s:if test="${evento.usuarioInscrito}=='true'">
						<a href="javascript:exitEvent('${evento.id}');">Nao Participar</a>
					</s:if>
				</display:column>
	    	<% } %>
	 	<% } %>
	</display:table>
 
</div>
 
<center>
<% if ((session.getAttribute("login") != null)) { 
		if (session.getAttribute("tipoUsuario").equals("ADMINISTRADOR")) { %>
<div class="sepDiv">
			<button id="btnInsert" onclick="location.href='${pageContext.request.contextPath}/jsp/eventos/insertEvento.jsp'">Inserir evento</button>
</div>
	<%	}
	}%>
</center>
 
</body>
</html>