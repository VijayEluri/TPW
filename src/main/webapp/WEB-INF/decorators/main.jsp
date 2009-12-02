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
    
    <script type="text/javascript">		
		$('#page').corner();
		$('#header').corner("round-top");
		$('#header h1').corner("round-top");
		$('#content').corner("round-bottom");
		$('.divPadrao').corner();
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
                 <li><a href="#">menu1</a></li>
                 <li><a href="#">menu2</a></li>                   
                 <li><a href="#">menu3</a></li>
                 <li><a href="#">menu4</a></li>
                 <li><a href="#">menu5</a></li>
            </ul>
        </div>

        <div id="content" class="clearfix">
            <div id="main">
            	<decorator:body/>
            </div>           
        </div>
        
    </div>

</body>
</html>
