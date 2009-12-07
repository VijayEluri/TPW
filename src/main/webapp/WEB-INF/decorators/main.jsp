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
    </script>

    <title>Sanca Livre</title>
    
    <decorator:head/>
</head>
<body id="page-home">
    <div id="page">

        <div id="header" class="clearfix">
			<h1>Sanca Livre</h1>
        </div>

        <div id="menu">
    		<ul>
                 <li id="menu1"><a href="/tpw/jsp/rss!index.action">Home</a></li>
                 <li id="menu2"><a href="#">Blog</a></li>                   
                 <li id="menu3"><a href="#">Minicursos</a></li>
                 <li id="menu4"><a href="#">Eventos</a></li>
                 <li id="menu5"><a href="/tpw/jsp/usuario!listUsuarios.action">Usuários</a></li>
            </ul>
        </div>

        <div id="content" class="clearfix">
            <div id="main">				
				<center><div style="font: 13px verdana, arial, tahoma, sans-serif; color: red; width: 500px; text-align: left;"> <s:actionerror /> </div></center>				
            	<decorator:body/>
            </div>           
        </div>
        
    </div>

</body>
</html>
