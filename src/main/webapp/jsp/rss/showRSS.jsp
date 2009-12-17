<!DOCTYPE html PUBLIC 
	"-//W3C//DTD XHTML 1.1 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
	
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<link href="<s:url value='/styles/main.css'/>" rel="stylesheet" type="text/css" media="all"/>
</head>
<body style="background-color:white">    					
<marquee id="rssmarquee" behavior="scroll" direction="up" scrollamount="1" width="100%" style="height: 330px">
	<div>
		<s:iterator value="noticias" >
			<span class="newsTitle"><a href="${link}" target="blank">${titulo}</a><br /></span>
			<span class="newsBody"><s:property value="noticia"/><br/><br></span>
		</s:iterator>
	</div>
</marquee>
</body>
</html>