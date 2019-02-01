var hiddenButton=document.getElementsByClassName('btn btn-primary ')[0];


hiddenButton.addEventListener("click",function(){
	
	
	let userName=hiddenButton.parentNode.parentNode.
	parentNode.getElementsByTagName('input')[0].value.trim();
	
	let password=hiddenButton.parentNode.parentNode.
	parentNode.getElementsByTagName('input')[1].value.trim();
		
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr(
	  "content");

	
	var xhttp = new XMLHttpRequest();
	xhttp.open('POST','login',
			  true);
	xhttp.setRequestHeader('Content-Type',
	  'application/json;charset=UTF-8');
	xhttp.setRequestHeader(header, token);
	
	
	xhttp.send(JSON.stringify({user:userName}));
	

	
	xhttp.onreadystatechange = function() {
	  if (this.readyState == 4 && this.status == 200) {
	    
		  
		  result=xhttp.responseText;
		
		  let answer=JSON.parse(result);
		  
		  let hiddenForm=document.getElementById('hiddenForm');
		  
		  console.log(answer);
		  
		  if(answer.answer!=null){
			  
			  hiddenForm.getElementsByTagName('input')[0].value=answer.answer;
		  }else{
			  
			  hiddenForm.getElementsByTagName('input')[0].value=userName;
		  }
		  
		  hiddenForm.getElementsByTagName('input')[1].value=password;
		  
		  hiddenForm.getElementsByTagName('input')[2].click();
		  
		  
	  }
	};
	
	
});