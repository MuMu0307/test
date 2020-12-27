<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>

<script type="text/javascript"    src="http://cdn.static.runoob.com/libs/jquery/2.0.2/jquery.min.js"></script>
 

<script type="text/javascript">
	function doUpload() {  
	    var formData = new FormData($( "#uploadForm" )[0]);
        var i = formData.entries();

        console.log(i.next()); // {done:false, value:["k1", "v1"]}
        console.log(i.next()); // {done:false, value:["k1", "v1"]}

	    $.ajax({  
	         /*url: 'http://localhost:8080/fileTest/upload' ,  */
             url: 'http://localhost:8080/goods/addOneGoods_pic',
	         type: 'POST',  
	         data: formData,  
	         async: false,  
	         cache: false,  
	         contentType: false,  
	         processData: false,  
	         success: function (returndata) {  
	             alert(returndata);  
	         },  
	         error: function (returndata) {  
	             alert(returndata);  
	         }  
	    });  
	}
</script>

</head>
<body>

   <form id= "uploadForm">  
      <p >指定文件名： <input type="text" name="filename" value= ""/></p >  
      <p >上传文件： <input type="file" name="file"/></p>  
      <input type="button" value="上传" onclick="doUpload()" />  
</form>  

</body>
</html>