<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.Date" %>
 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Minicurso</title>
  
<script type="text/javascript">
 
    $(document).ready(function() {
        
       $('#menu3').css("background-color", "#4198f6");
 
    });
 
</script>
 
</head>
<body>

<center> 
	<div class="divPadrao" style="width: 500px;">
	
		<table width="100%">
			<tr>
				<td colspan="4" align="center">
				<h3>Detalhes do minicurso</h3>
				</td>
			</tr>
	
			<tr>
				<td align="right"><span class="label" style="font-weight: bold">Minicurso:</span></td>
				<td colspan="3">${minicurso.nome}</td>
			</tr>
			
			<tr>
				<td align="right"><span class="label" style="font-weight: bold">Descrição:</span></td>
				<td colspan="3">${minicurso.descricao}</td>
			</tr>
			
			<tr>
				<td align="right"><span class="label" style="font-weight: bold">Data:</span></td>
				<td colspan="3">${minicurso.data}</td>
			</tr>
	
			<tr>
				<td align="right"><span class="label" style="font-weight: bold">Responsável:</span></td>
				<td colspan="3">${minicurso.responsavel}</td>
			</tr>
	
			<tr>
				<td align="right"><span class="label" style="font-weight: bold">Vagas:</span></td>
				<td colspan="3">${minicurso.qtVagas}</td>
			</tr>
			
			<tr>
				<td align="right"><span class="label" style="font-weight: bold">Inscritos:</span></td>
				<td colspan="3">${minicurso.qtInscritos}</td>
			</tr>
		</table>
			
	</div>
</center>
 
<div class="divTable">
 
	<h3>Inscritos</h3>
	 
	<display:table list="${inscritos}" class="defaultTable" pagesize="100" cellspacing="0" cellpadding="0"
	sort="list" id="inscrito" requestURI="${pageContext.request.contextPath}/jsp/minicurso!listInscritos.action">
	 		
		<display:column title="Nome" property="nome" style="width: 300px" />
		<display:column title="Login" property="login" style="width: 300px" />
		<display:column title="Email" property="email" style="width: 400px" />
	
	</display:table>
</div>
 
 
</body>
</html>