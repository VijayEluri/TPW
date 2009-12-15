<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Minicurso</title>
 
<script type="text/javascript">
	function del(id) {
		if (confirm('Confirma exclusão?')) {
			document.location.href = "${pageContext.request.contextPath}/jsp/minicurso!deleteMinicurso.action?minicurso.id=" + id;
		}
	}

	function exitMinicurso (id) {
		if (confirm('Confirma nao participacao neste minicurso?')) {
			document.location.href = "${pageContext.request.contextPath}/jsp/minicurso!exitMinicurso.action?minicurso.id=" + id;
		}
	}
</script>
 
<script type="text/javascript">
 
    $(document).ready(function() {
 
       $('#btnInsert').qtip({
          content: 'Inserir um novo minicurso',
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
           content: 'Se inscrever no minicurso',
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
           content: 'Voce esta inscrito no minicurso. Clique para sair do minicurso.',
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
       
       $('#menu3').css("background-color", "#4198f6");
 
    });
 
</script>
 
</head>
<body>
 
<div class="divTable">
 
	<h3>Minicurso</h3>
	 
	<display:table list="${minicursos}" class="defaultTable" pagesize="100" cellspacing="0" cellpadding="0"
	sort="list" id="minicurso" requestURI="${pageContext.request.contextPath}/jsp/minicurso!listMinicursos.action">
	 
	<display:column title="Nome" property="nome" style="width: 100px" />
	<display:column title="Descricao" property="descricao" style="width: 200px" />
	<display:column title="Data" property="data" style="width: 100px" />
	<display:column title="Responsavel" property="responsavel" style="width: 100px"/>
	<display:column title="Vagas Totais" property="qtVagas" style="width: 100px" />
	<display:column title="Qtd Inscritos" property="qtInscritos" style="width: 100px"/>
	
	 	<% if ((session.getAttribute("login") != null)) { %>
			<% if (session.getAttribute("tipoUsuario").equals("ADMINISTRADOR")) {	%>
				<display:column title="Editar" style="width: 50px">
	         		<a href="${pageContext.request.contextPath}/jsp/minicurso!editMinicurso.action?minicurso.id=${minicurso.id}">editar</a>
	    		</display:column>
	    		<display:column title="Remover" style="width: 50px">
	         		<a href="javascript:del('${minicurso.id}');">remover</a>
	    		</display:column>
			<% } else { %>
				<display:column title="Inscrever/Sair" style="width: 50px">
					<% if ( !((beans.Minicurso) minicurso).getUsuarioInscrito())  {%>
						<a href="${pageContext.request.contextPath}/jsp/minicurso!enterMinicurso.action?minicurso.id=${minicurso.id}"><img id='btnEnter' src='/tpw/images/inscrever.gif' border=0/></a>
					<% } %>
					<% if ( ((beans.Minicurso) minicurso).getUsuarioInscrito())  {%>
						<a href="javascript:exitMinicurso('${minicurso.id}');"><img id='btnExit' src='/tpw/images/naoinscrever.jpg' border=0/></a>
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
	<button id="btnInsert" onclick="location.href='${pageContext.request.contextPath}/jsp/minicursos/insertMinicurso.jsp'">Inserir minicurso</button>
</div>
<% 		}
	}%>
</center>
 
</body>
</html>