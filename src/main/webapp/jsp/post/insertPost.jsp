<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Digite o Post</title>

<script type="text/javascript">
	$(document).ready(function() {

		$('#btnSalvar').qtip( {
			content : 'Inserir Post',
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
	
<center>
<div class="divPadrao" style="width: 500px"><s:form theme="simple"
	action="usuario!insertUsuario" namespace="/jsp">

	<table width="100%">
		<tr>
			<td colspan="4" align="center">
			<h3>Digite o Post</h3>
			</td>
		</tr>

		<tr>
			<td align="right"><span class="label" style="font-weight: bold">Titulo:</span></td>
			<td colspan="3"><s:textfield name="post.titulo" maxlength="255" cssStyle="width: 300px" /></td>
		</tr>

		<tr>
			<td align="right"><span class="label" style="font-weight: bold">Texto:</span></td>
			<td colspan="3"><s:textarea name="post.texto" cssStyle="width: 300px; height: 300px" /></td>
		</tr>
		<tr>
			<td colspan="5">
			<div style="margin-top: 20px; text-align: right" class="sepDivUp">
			<s:submit id="btnSalvar" value="Salvar" /></div>
			</td>
		</tr>
	</table>

</s:form></div>
</center>

</body>
</html>