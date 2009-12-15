<!DOCTYPE html PUBLIC 
	"-//W3C//DTD XHTML 1.1 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
	
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>

	<script type="text/javascript">
		$(document).ready(function() {
		
			$('#menu1').css("background-color", "#4198f6");
	
		});
	</script>	

	<s:head />
</head>
<body>

    <div id="doc" align="right">
        <div class="demo">
	    <div id="divtitulo">Not�cias sobre software-livre</div>
            
            <marquee behavior="scroll" direction="up" scrollamount="1" width="100%">					
				<s:iterator value="noticias" >
					<span class="newsTitle"><a href="${link}" target="blank">${titulo}</a><br /></span>
					<span class="newsBody"><s:property value="noticia"/><br/><br></span>
				</s:iterator>
	    	</marquee>

        </div>
    </div>
    
    <div id="minicursos" align="left">    	
    	<center><h3>Minicursos recentes</h3></center>
    	
    	<s:iterator value="minicursos">
    		<span class="newsTitle">${nome} - <fmt:formatDate value="${data}" pattern="dd/MM/yyyy" type="date" /><br /></span>
   			${descricao} <br />
   			Vagas dispon�veis: ${qtVagas - qtInscritos} <br /> <br />   	
    	</s:iterator>	
    </div>
        
</body>
</html>