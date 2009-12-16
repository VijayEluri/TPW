<!DOCTYPE html PUBLIC 
	"-//W3C//DTD XHTML 1.1 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
	
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>

	<script type="text/javascript">
    	function showRSS() {
    	    var div = $("#rssdiv");
	        $.get(
                    	    "${pageContext.request.contextPath}/jsp/index!fillRSS.action",
                	        function (data,textStatus) {
            	            div.html(data);
        	                }
    	         );
		}

	
		$(document).ready(function() {
			$('#menu1').css("background-color", "#4198f6");
            showRSS();
		});
	</script>	

	<s:head />
</head>
<body>

    <div id="doc" align="right">
        <div class="demo">
			<div id="divtitulo">Notícias sobre software-livre</div>
			<marquee behavior="scroll" direction="up" scrollamount="1" width="100%" style="height: 330px">
				<div id=rssdiv>
			
				</div>
			</marquee>
        </div>
    </div>
    
    <div id="minicursos" align="left" >    	 	
    	<center><h3>Próximos minicursos</h3></center>
		<marquee behavior="scroll" direction="up" scrollamount="1" width="100%" style="height: 140px">    	
    	<s:iterator value="minicursos">
    		<span class="newsTitle">${nome} - <fmt:formatDate value="${data}" pattern="dd/MM/yyyy" type="date" /><br /></span>
   			${descricao} <br />
   			Vagas disponíveis: ${qtVagas - qtInscritos} <br /> <br />   	
    	</s:iterator>
    	</marquee>
	</div>

    <div id="eventos" align="left">    	    	
    	<center><h3>Próximos eventos</h3></center>
		<marquee behavior="scroll" direction="up" scrollamount="1" width="100%" style="height: 140px">    	
    	<s:iterator value="eventos">
    		<span class="newsTitle">${nome} - <fmt:formatDate value="${data}" pattern="dd/MM/yyyy" type="date" /><br /></span>
   			${descricao} <br />
   			Vagas disponíveis: ${qtVagas - qtInscritos} <br /> <br />   	
    	</s:iterator>
    	</marquee>
    </div>
    	
    	

       
    <div class="divTable" style="width: 900px">
 
	<s:iterator value="posts" status="id">
		<div class="postTitulo">
			<span>${titulo}</span>
						
			<% if (session.getAttribute("tipoUsuario") != null && session.getAttribute("tipoUsuario").equals("ADMINISTRADOR")) { %>
				<a href="${pageContext.request.contextPath}/jsp/post!editPost.action?post.id=${id}"><img id="edit" src="/tpw/images/edit.png" height="23px" /></a>
				<a href="javascript:del('${id}');"><img id="trash" src="/tpw/images/trash.png" height="23px" /></a>				
			<% } %>
		</div>
		<div>
			<span class="postTexto">${texto}</span>
		</div>
		
		<div class="postFooter">
			Postado por: ${usuario.nome} - data: ${data}
		</div>										
	</s:iterator>

</div>
    
        
</body>
</html>