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
		<display:column title="Editar" style="width: 50px">
	         <a href="${pageContext.request.contextPath}/jsp/minicurso!editMinicurso.action?minicurso.id=${minicurso.id}">editar</a>
	    </display:column>
	    <display:column title="Remover" style="width: 50px">
	         <a href="javascript:del('${minicurso.id}');">remover</a>
	    </display:column>
	 
	</display:table>
 
</div>
 
<center>
<div class="sepDiv">
	<button id="btnInsert" onclick="location.href='${pageContext.request.contextPath}/jsp/minicursos/insertMinicurso.jsp'">Inserir minicurso</button>
</div>
</center>
 
</body>
</html>