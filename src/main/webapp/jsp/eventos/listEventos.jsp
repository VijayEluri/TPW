<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.Date" %>
 
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
		if (confirm('Confirma nao participacao no evento?')) {
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

        $('#btnEnter').qtip({
            content: 'Se inscrever no evento',
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

        $('#btnExit').qtip({
            content: 'Voce esta inscrito no evento. Clique para sair do evento.',
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
	 
		<% if ((session.getAttribute("login") != null) && (session.getAttribute("tipoUsuario").equals("ADMINISTRADOR"))) {	%>
			<display:column title="Relatório" style="width: 50px">
				<a href="${pageContext.request.contextPath}/jsp/evento!details.action?evento.id=${evento.id}"><img id='btnRelatorio' src='/tpw/images/relatorio.png' border=0/></a>
			</display:column>
		<% } %>
		<display:column title="Nome" property="nome" style="width: 100px" />
		<display:column title="Descricão" property="descricao" style="width: 200px" />
		<display:column title="Data" property="data" style="width: 100px" />
		<display:column title="Responsável" property="responsavel" style="width: 100px"/>

		<display:column title="Vagas Totais" property="qtVagas" style="width: 30px" />
		<display:column title="Qtd Inscritos" property="qtInscritos" style="width: 30px"/>
		<% if ((session.getAttribute("login") != null)) { %>
			<% if (session.getAttribute("tipoUsuario").equals("ADMINISTRADOR")) {	%>
				<display:column title="Editar" style="width: 50px">
	        		<a href="${pageContext.request.contextPath}/jsp/evento!editEvento.action?evento.id=${evento.id}">editar</a>
				</display:column>
				<display:column title="Remover" style="width: 50px">
					<a href="javascript:del('${evento.id}');">remover</a>
				</display:column>
	    	<% } else { %>
	    		<display:column title="Inscrever/Sair" style="width: 50px">
					<% if ( !((beans.Evento) evento).getUsuarioInscrito() &&  ((beans.Evento) evento).getData().after(new Date()))  {%>
						<a href="${pageContext.request.contextPath}/jsp/evento!enterEvento.action?evento.id=${evento.id}"><img id='btnEnter' src='/tpw/images/inscrever.gif' border=0/></a>
					<% } %>
					<% if ( ((beans.Evento) evento).getUsuarioInscrito() && ((beans.Evento) evento).getData().after(new Date()))  {%>
						<a href="javascript:exitEvent('${evento.id}');"><img id='btnExit' src='/tpw/images/naoinscrever.jpg' border=0/></a>
					<% } %>
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