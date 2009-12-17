<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sites RSS</title>
 
 <script type="text/javascript">
	function del(link) {
		if (confirm('Confirma exclusão?')) {
			document.location.href = "${pageContext.request.contextPath}/jsp/rss!deleteSiteRSS.action?siteRSS.link=" + link;
		}
	}
</script>
 
<script type="text/javascript">

	$(document).ready(function() {

		$('#btnInsert').qtip( {
			content : 'Inserir uma nova fonte de RSS',
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

		$('#trash').qtip( {
			content : 'Excluir fonte de RSS',
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

		$('#menu6').css("background-color", "#4198f6");
	});
</script>
 
</head>
<body>

<div class="divTable" style="width: 900px">
	<display:table list="${sitesRSS}" class="defaultTable" pagesize="100" cellspacing="0" cellpadding="0"
		sort="list" id="siteRSS" requestURI="${pageContext.request.contextPath}/jsp/usuario!listUsuarios.action">
 	
		<display:column title="Site" property="site" style="width: 200px" />
		<display:column title="Link" property="link" style="width: 300px" />
	    <display:column title="Remover" style="width: 50px">
    	     <a href="javascript:del('${siteRSS.link}');"><img id="trash" src="/tpw/images/trash.png" height="23px" /></a>
	    </display:column>
	 </display:table>
</div>

<div class="divPadrao" style="width: 920px;">
	<s:form theme="simple" action="rss!insertSiteRSS" namespace="/jsp" >

	<table width="100%">
		<tr>
			<td colspan="2">
				<span class="label" style="font-weight: bold; font-size: 16px">Adicionar fontes de RSS</span>
			</td>
		</tr>
		<tr>			
			<td colspan="1">
				<span class="label" style="font-weight: bold">Site:</span>
			</td>
			<td>
				<s:textfield name="siteRSS.site" maxlength="255" cssStyle="width: 600px" />
			</td>
		</tr>
		<tr>			
			<td colspan="1">
				<span class="label" style="font-weight: bold">Link:</span>
			</td>
			<td>
				<s:textfield name="siteRSS.link" maxlength="255" cssStyle="width: 600px" />
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<div style="margin-top: 20px; text-align: right" class="sepDivUp">
					<s:submit id="btnSalvar" value="Salvar" />
				</div>
			</td>
		</tr>
	</table>

</s:form></div>

</body>
</html>