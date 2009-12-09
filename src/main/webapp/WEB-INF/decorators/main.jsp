<!DOCTYPE html PUBLIC 
	"-//W3C//DTD XHTML 1.1 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
	<title><decorator:title default="Sanca Livre"/></title>
    <link href="<s:url value='/styles/main.css'/>" rel="stylesheet" type="text/css" media="all"/>
    <link href="<s:url value='/struts/niftycorners/niftyCorners.css'/>" rel="stylesheet" type="text/css"/>
    <link href="<s:url value='/struts/niftycorners/niftyPrint.css'/>" rel="stylesheet" type="text/css" media="print"/>
    <script language="JavaScript" type="text/javascript" src="<s:url value='/struts/niftycorners/nifty.js'/>"></script>
    
    <script type="text/javascript" src='/tpw/js/jquery.js'></script>
   	<script type="text/javascript" src='/tpw/js/jquery.corner.js'></script>
   	<script type="text/javascript" src='/tpw/js/jquery.qtip.js'></script>
   	<script type="text/javascript" src='/tpw/js/jquery.marquee.js'></script>
    
    <script type="text/javascript">		
		$('#page').corner();
		$('#header').corner("round-top");
		$('#header h1').corner("round-top");
		$('#content').corner("round-bottom");
		$('.divPadrao').corner();
		$('.demo').corner();
		$('#login').corner();

		
		$(document).ready(function() {

			$('#btnLogin').qtip( {
				content : 'Clique aqui para logar-se no sistema',
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

		});
						
	</script>

    <title>Sanca Livre</title>
    
    <decorator:head/>
</head>
<body id="page-home">
    <div id="page">

        <div id="header" class="clearfix">
			
			<div align="right" style="padding-top: 10px; padding-right: 10px;">

				<% if (session.getAttribute("login") == null) { %>
				
					<s:form id="login" theme="simple" action="index!login" namespace="/jsp">
						<table>
							<tr>
								<td>Login:</td>
								<td><s:textfield name="usuario.login" size="12" maxlength="20"></s:textfield> </td>
							</tr>
							<tr>
								<td>Password:</td>
								<td><s:password name="usuario.password" size="12" maxlength="20"></s:password></td>
							</tr>					
							<tr>
								<td colspan="2" align="right"><s:submit value="Login" id="btnLogin"></s:submit> </td>								
							</tr>
	
						</table>
					</s:form>

				<% } else { %>
									
					<s:form id="login" theme="simple" action="index!logout" namespace="/jsp">
						<table width="100%">
							<tr><td align="left">Bem vindo</td>
							<tr><td><%= request.getSession().getAttribute("nome") %></td></tr>												
							<tr><td>&nbsp;</td></tr>
							<tr><td align="center"><s:submit value="Logout" /></td></tr>
						</table>
					</s:form>
				
				<% } %>
				
			</div>			
			
        </div>

		

        <div id="menu">
    		<ul>
                 <li id="menu1"><a href="/tpw/jsp/index!index.action">Home</a></li>
                 <li id="menu2"><a href="#">Blog</a></li>                   
                 <li id="menu3"><a href="#">Minicursos</a></li>
                 <li id="menu4"><a href="#">Eventos</a></li>
                 <li id="menu5"><a href="/tpw/jsp/usuario!listUsuarios.action">Usuários</a></li>
            </ul>
        </div>

        <div id="content" class="clearfix">
            <div id="main">				
				<center>
					<div style="font: 13px verdana, arial, tahoma, sans-serif; color: red; width: 500px; text-align: left;">
						<s:actionerror /> 
					</div>
				</center>				
            	<decorator:body/>
            </div>           
        </div>
        
    </div>

</body>
</html>
