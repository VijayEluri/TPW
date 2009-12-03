<!DOCTYPE html PUBLIC 
	"-//W3C//DTD XHTML 1.1 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
	
<%@taglib prefix="s" uri="/struts-tags" %>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
	<s:head />
</head>
<body>
    <div id="doc" align="right">
        <div class="demo">
	    <div id="divtitulo">Notícias sobre software-livre</div>
            <marquee behavior="scroll" direction="up" scrollamount="1" width="100%">		
			
				<s:iterator value="noticias" >
			
					<span class="newsTitle"><a href="${link}"><s:property value="titulo"/></a></span><br />
					<span class="newsBody"><s:property value="noticia"/><br/><br/></span>
							
				</s:iterator>
	    	</marquee>

        </div>
    </div>	
</body>
</html>