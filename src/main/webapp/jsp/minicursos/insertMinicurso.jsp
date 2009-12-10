<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<s:head theme="ajax" />
<title>Dados do minicurso</title>

<script type="text/javascript">
	$(document).ready(function() {

		$('#btnSalvar').qtip( {
			content : 'Salvar dados do minicurso',
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

		$('#menu3').css("background-color", "#4198f6");

	});
</script>

</head>


<body>
	
<center>
<div class="divPadrao" style="width: 500px"><s:form theme="simple"
	action="minicurso!insertMinicurso" namespace="/jsp">

	<table width="100%">
		<tr>
			<td colspan="4" align="center">
			<h3>Dados do minicurso</h3>
			</td>
		</tr>

		<tr>
			<td align="right"><span class="label" style="font-weight: bold">Nome:</span></td>
			<td colspan="3"><s:textfield name="minicurso.nome" maxlength="300" cssStyle="width: 300px" /></td>
		</tr>

		<tr>
			<td align="right"><span class="label" style="font-weight: bold">Descricao:</span></td>
			<td colspan="3"><s:textfield name="minicurso.descricao" maxlength="1500" cssStyle="width: 300px" /></td>
		</tr>

		 <tr>
			<td align="right"><span class="label" style="font-weight: bold">Data:</span></td>
			<td colspan="3"><s:datetimepicker name="strData" displayFormat="dd/MM/yyyy"/></td>
   		</tr>

		<tr>
			<td align="right"><span class="label" style="font-weight: bold">Responsavel:</span></td>
			<td colspan="3"><s:textfield name="minicurso.responsavel" maxlength="300" cssStyle="width: 300px" /></td>
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