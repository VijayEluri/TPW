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
<title>Dados do evento</title>

<link type="text/css" href="/tpw/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="/tpw/js/jquery-1.3.2.min.js"></script>
<!-- core, slider, datepicker -->
<script type="text/javascript" src="/tpw/js/jquery-ui-1.7.2.custom.min.js"></script>
<script type="text/javascript" src="/tpw/js/timepicker.js"></script>

<script type="text/javascript">
$(function() {
    $('#datetime').datepicker({
    	duration: '',
        showTime: true,
        constrainInput: false,
        stepMinutes: 1,
        stepHours: 1,
        altTimeField: '',
        time24h: true
     });
});
</script>

<script type="text/javascript">
	$(document).ready(function() {

		$('#btnSalvar').qtip( {
			content : 'Salvar dados do evento',
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

		$('#menu4').css("background-color", "#4198f6");

	});
</script>

</head>

<body>

<center>
<div class="divPadrao" style="width: 700px"><s:form theme="simple"
	action="evento!updateEvento" namespace="/jsp">

	<input type="hidden" name="evento.id" value="${evento.id}"></input>

	<table width="100%">
		<tr>
			<td colspan="4" align="center">
			<h3>Dados do evento</h3>
			</td>
		</tr>

		<tr>
			<td align="right"><span class="label" style="font-weight: bold">Nome:</span></td>
			<td colspan="3"><s:textfield name="evento.nome" maxlength="300"
				cssStyle="width: 300px" /></td>
		</tr>

		<tr>
			<td align="right"><span class="label" style="font-weight: bold">Descricao:</span></td>
			<td colspan="3"><s:textarea name="evento.descricao" cssStyle="width:80%; height: 100px;"></s:textarea></td>
		</tr>

		<tr>
			<td align="right"><span class="label" style="font-weight: bold">Data:</span></td>
			<td colspan="3"><s:textfield name="strData" id="datetime"/></td>
		</tr>

		<tr>
			<td align="right"><span class="label" style="font-weight: bold">Responsavel:</span></td>
			<td colspan="3"><s:textfield name="evento.responsavel"
				maxlength="300" cssStyle="width: 300px" /></td>
		</tr>

		<tr>
			<td align="right"><span class="label" style="font-weight: bold">Qtd. Vagas:</span></td>
			<td colspan="3"><s:textfield name="evento.qtVagas" maxlength="30" cssStyle="width: 30px" /></td>
		</tr>

		<tr>
			<td align="right"><span class="label" style="font-weight: bold">Qtd. Inscritos:</span></td>
			<td colspan="3"><s:textfield name="evento.qtInscritos" maxlength="30" cssStyle="width: 30px" /></td>
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