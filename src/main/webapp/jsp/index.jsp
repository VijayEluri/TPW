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
			
				<s:property name="news.title"/>
				<span class="newsTitle">Microsoft Plugs "Drive-By" and 14 Other Holes</span><br />
				<span class="newsBody">Microsoft today patched 15 vulnerabilities in Windows, Windows Server, Excel, and Word, including one that will probably be exploited quickly by hackers. None affects Windows 7. Of today's 15 bugs, Microsoft tagged three 'critical' and the remaining 12 'important.' Experts agreed that users should focus on MS09-065 first and foremost. That update, which was ranked critical, affects all still-supported editions of Windows except Windows 7 and its server sibling, Windows Server 2008 R2. 'The Windows kernel vulnerability is going to take the cake,' said Andrew Storms, director of security operations at nCircle Network Security. 'The attack vector can be driven through Internet Explorer, and this is one of those instances where the user won't be notified or prompted. This is absolutely a drive-by attack scenario.' Richie Lai, the director of vulnerability research at security company Qualys, agreed. 'Anyone running IE [Internet Explorer] is at risk here, even though the flaw is not in the browser, but in the Win32k kernel mode driver.<br/><br/></span>
			
				<span class="newsTitle">Heart of the Milky Way Photos From NASA</span><br/>
				<span class="newsBody">PBH submitted a link to a really amazing composite image of the Milky Way released by NASA. They combined infra red, visible, and x-ray images taken by Spitzer, Hubble, and Chandra to create one beautiful image to commemorate the 400 years since 1609, when Galileo looked up.</span>
	    	</marquee>

        </div>
    </div>	
</body>
</html>