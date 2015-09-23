function CreateXmlHttpRequest()
{
        var xmlHttp = null;
        try 
        { 
             // Firefox, Opera 8.0+, Safari 
             xmlHttp=new XMLHttpRequest(); 
        } 
        catch (e) 
        { 
              // Internet Explorer 
             try 
             { 
                    xmlHttp=new ActiveXObject("Msxml2.XMLHTTP"); 
             } 
             catch (e) 
             { 
                    try
                    { 
                           xmlHttp=new ActiveXObject("Microsoft.XMLHTTP"); 
                    } 
                    catch (e) 
                    { 
                           alert("您的浏览器不支持AJAX！"); 
                    } 
             } 
        }
        return xmlHttp;
      }
      
var validation="qwer";
window.onload=changeimg;

function changeimg()
{
//	
	validation="";
	var base="1234567890abcdefghigklmnopqrstuvwxyz";
	for(var i=0;i<4;i++){
	validation+=base.charAt(parseInt(Math.random()*base.length))+"";
	}

	
	var imgnode=document.getElementById("imgid");
//	imgnode.src="./servlet/Validation?time="+new Date();
//	var validation="qwer"
		
	//1
	var xmlre=CreateXmlHttpRequest();
	
	//2
	xmlre.onreadystatechange=function()
	{
//		alert(xmlre.readyState);
		if(4==xmlre.readyState)
		{
//			alert(xmlre.status);
			if(200==xmlre.status||304==xmlre.status)
			{
				imgnode.src="/wmuitp/servlet/Validation?time="+new Date()+"&validation="+validation;
			}
		}
	}
	
	//3
	xmlre.open("get","/wmuitp/servlet/Validation?timeStamp="+new Date().getTime()+"&validation="+validation,true);
	//4
	xmlre.send(null);
	

}