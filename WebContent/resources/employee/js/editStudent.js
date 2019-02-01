

$('.form-control.mr-sm-2').on('input keypress',function(){
	

	
	let trs=document.getElementsByTagName('tr');
	
	let cntr=0;
	
	for(let i=1;i<trs.length;i++){
		
		if(this.value.trim().length==0){
			
			
			try{
			
				if(trs[i].getAttribute('style').match('display:none')!=null){
					
					trs[i].removeAttribute('style');
					continue;
				}
			
			}catch(expt){
				
			}
			
		}
		
		if(trs[i].getElementsByTagName('td')[3].textContent.trim().toLowerCase().match(this.value.trim().toLowerCase())==null
				&& !trs[i].getElementsByClassName('fas fa-user-minus').length>0){
			
			trs[i].setAttribute('style','display:none;');
		}
		
	}
	
	let trs2=document.getElementsByTagName('tr');
	
	for(let k=0;k<trs2.length;k++){
		
		try{
			if(trs2[k].getAttribute('style')==null){
				
				trs2[k].getElementsByTagName('td')[0].childNodes[0].textContent=++cntr;
			}
		
		}catch(exp){
			
		
		}
		
	}
	
	
	
});


var students=new Student();
function Student(){
	
	this.studentSet=new Set();
	
	
	
	this.addEntryOnSet=function (row){
		
		entry=returnEntryFromRow(row);
		this.studentSet.add(entry);
	}
	
	this.getSet=function (){
		
		return this.studentSet;
	}
	
	this.contains=function(student){
		
		let result=false;
		this.studentSet.forEach(function(item){
			
			
			
			if(student.email==item.email &&
					student.name==item.name && student.surname==item.surname
					&& student.birthDate==item.birthDate && student.yearOfEnrollment==item.yearOfEnrollment
					&& student.postGraduate==item.postGraduate && student.department==item.department
					&& student.phone==item.phone && student.address==item.address){
				
				result=true;
			}
			
			
			
			
		});
		
		return result;
	}
	
    
	
}


$('document').ready(function(){
	
	
	document.getElementById('saveError').getElementsByTagName('button')[0].
	addEventListener('click',function(){
		
		$('#saveError').hide();
	});
	
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr(
	  "content");

//	
// var xhttp = new XMLHttpRequest();
// xhttp.open('POST','createStudent',
// true);
// xhttp.setRequestHeader('Content-Type',
// 'application/json;charset=UTF-8');
// xhttp.setRequestHeader(header, token);
// xhttp.send(JSON.stringify(student));
	
	var xhttp = new XMLHttpRequest();
	xhttp.open('POST','getAllStudents',
			  true);
	xhttp.setRequestHeader('Content-Type',
	  'application/json;charset=UTF-8');
	xhttp.setRequestHeader(header, token);
	xhttp.send("{}");
	
	
	
	xhttp.onreadystatechange = function() {
	  if (this.readyState == 4 && this.status == 200) {
	    
		  existingStudents=JSON.parse(xhttp.responseText);
		  
		  existingStudents=existingStudents.sort(function(a,b){
			  
			  if(a.surname>b.surname){
				  return 1
			  }
			  
			  if(a.surname<b.surname){
				  return -1
			  }
			  
			  return 0;
			  
		  });
		  
		  for(let i=0;i<existingStudents.length;i++){
			  
			  var newRow=createRow();
			  
			  let table=document.getElementsByTagName('tbody');
				
			  let allRows=table[0].getElementsByTagName('tr');
			  
			  document.getElementsByClassName('btn btn-outline-light my-2 my-sm-0')[0].click();
			  
			  document.getElementsByTagName('tr')[i+1].
			  getElementsByTagName('td')[1].textContent=existingStudents[i].user;
						  
			  document.getElementsByTagName('tr')[i+1].
			  getElementsByTagName('td')[2].textContent=existingStudents[i].name;
			  
			  document.getElementsByTagName('tr')[i+1].
			  getElementsByTagName('td')[3].textContent=existingStudents[i].surname;
			  
			  var tmp=existingStudents[i].birthdate.split("-");
			  
			  document.getElementsByTagName('tr')[i+1].
			  getElementsByTagName('input')[0].value=existingStudents[i].birthdate=tmp[1]+"/"+tmp[2]+"/"+tmp[0];
			  
			  document.getElementsByTagName('tr')[i+1].
			  getElementsByTagName('td')[5].textContent=existingStudents[i].yearOfEnrollment;
			  
			  if(!existingStudents[i].postGraduate){
				  
				  document.getElementsByTagName('tr')[i+1].
				  getElementsByTagName('td')[6].click();
				  
			  }
			  
			  let dropDownList=document.getElementsByTagName('tr')[i+1].
			  getElementsByClassName('dropdown-menu')[0].getElementsByTagName('a');
			  
			  for(let k=0;k<dropDownList.length;k++){
				  
				  if(existingStudents[i].department.match(dropDownList[k].textContent)!=null){
					  
					  dropDownList[k].click();
					  break;
				  }
			  }
			  
			  document.getElementsByTagName('tr')[i+1].
			  getElementsByTagName('td')[8].textContent=existingStudents[i].phone;
			  
			  
			  document.getElementsByTagName('tr')[i+1].
			  getElementsByTagName('td')[9].textContent=existingStudents[i].address;
			  
		  	  var numOfExistingStudents=document.getElementsByTagName('tr').length;
		  	  
		  	 		  	  
		  	  document.getElementsByTagName('tr')[numOfExistingStudents-1].
		  	  getElementsByClassName('fas')[0].remove();
		  	  
		  	 document.getElementsByTagName('tr')[numOfExistingStudents-1].
		  	 getElementsByClassName('far')[0].setAttribute('class','fas fa-user-edit');
		  	 
		  	 document.getElementsByTagName('tr')[numOfExistingStudents-1].
		  	 getElementsByClassName('fas')[0].setAttribute('title','Update student on database');
			  
		  	 let currentTr=document.getElementsByTagName('tr')[numOfExistingStudents-1];
		  	 
		  	 let currentTds=currentTr.getElementsByTagName('td');
		  	 
		  	 let oldElement;
		  	 
		  	 let newElement;
		  	 
		  	 for(let i=0;i<currentTds.length-2;i++){
		  		 
		  		 newElement=currentTds[i].cloneNode(true);
		  		 
		  		currentTds[i].parentNode.replaceChild(newElement,currentTds[i]);
		  		 
		  		newElement.setAttribute('data-toggle','tooltip');
		  		
		  		newElement.setAttribute('title',"Employee is now allowed to modify this field");
		  		
		  		currentTds[i].setAttribute('contenteditable','false')
		  		
		  				  		
		  		if(i==4){
		  			
		  			currentTds[i].getElementsByTagName('input')[0].readOnly=true;
		  		}
		  		
		  		 
		  		
		  	 }
		  	 
		  	currentTr.getElementsByClassName('dropdown-menu')[0].remove();
		  	
		  	currentTr.getElementsByClassName('fas fa-user-edit')[0].
		  	addEventListener('click',function(){
		  		
		  		let element=this;
		  		setTimeout(function(){
		  			
		  			if(element.parentNode.parentNode.parentNode.getElementsByClassName('fas fa-times').length==0){
		  				
		  				if(students.contains(returnEntryFromRow(element.parentNode.parentNode.parentNode))){
		  					
		  					let modal=document.getElementById('saveError');
		  					
		  					modal.getElementsByTagName('p')[0].textContent='Row is identical with the one in the database.\n\
		  						Update will not take place.';
		  					
		  					$("#saveError").show('modal');
		  				}else{
		  					
		  					let token = $("meta[name='_csrf']").attr("content");
		  					let header = $("meta[name='_csrf_header']").attr(
		  					  "content");

		  					
		  					let xhttp = new XMLHttpRequest();
		  					xhttp.open('POST','updateStudent',
		  							  true);
		  					xhttp.setRequestHeader('Content-Type',
		  					  'application/json;charset=UTF-8');
		  					xhttp.setRequestHeader(header, token);
		  					
		  					let student=returnEntryFromRow(element.parentNode.parentNode.parentNode);
		  					
		  					xhttp.send(JSON.stringify(student));
		  					

		  					
		  					xhttp.onreadystatechange = function() {
		  					  if (this.readyState == 4 && this.status == 200) {
		  					    
		  						  
		  						 result=xhttp.responseText;
		  						  
		  						 result=JSON.parse(result);
		  						  
		  						 let resultMessage=document.createElement('div');
		  						 
		  						 resultMessage.setAttribute('class','alert alert-success');
		  						  
		  						 resultMessage.innerHTML=`User with email:${result.email} was successfully updated`
		  						  
		  						 resultMessage.setAttribute('style','width:40%;margin-left:33%;\
		  								 opacity 1; transition:opacity 5s');
		  						 
		  						 
		  							 
		  						 document.getElementById('dataTable').
		  						 prepend(resultMessage); 
		  						 
		  						 resultMessage.scrollIntoView();
		  						 
		  						 let position=resultMessage.getBoundingClientRect();
		  						 
		  						 window.scrollTo(position.x,position.y);
		  						 
		  						 let previousMessages=document.getElementsByClassName('alert alert-success');
		  						 
		  						 if(previousMessages.length>1){
		  							 
		  							 for(let i=1;i<previousMessages.length;i++){
		  								 
		  								previousMessages[i].remove();
		  							 }
		  						 }
		  						 
		  						 
		  						 let cnt=0;
		  						 let timeRunner=setInterval(function(){
		  							 
		  							 
		  							 
		  							 cnt++;
		  							 
		  							 resultMessage.style.opacity=0.02*resultMessage.style.opacity;
		  							 
		  							 if(cnt>65){
		  								 
		  								 position=element.parentNode.parentNode.parentNode.getBoundingClientRect();		  								 
		  								 window.scrollTo(position.x,position.y);
		  								 resultMessage.remove();
		  								 
		  								 clearInterval(timeRunner)
		  								 return;
		  							 }
		  							 
		  						 },100);
		  					  }
		  					};
		  					
		  					let correspondinStudent=returnEntryFromRow(element.parentNode.parentNode.parentNode);
		  					
		  					console.log(correspondinStudent);
		  					
		  					students.studentSet.forEach(function(student){
		  						
		  						if(correspondinStudent.email==student.email){
		  							
		  							student.address=correspondinStudent.address;
		  							
		  							student.phone=correspondinStudent.phone;
		  							
		  						//	students.studentSet.add(correspondinStudent);
		  						
		  						}
		  						
		  						
		  					});
		  					
		  				}
		  				
		  				
		  				
		  			}
		  			
		  		},10);
		  		
		  	});
		  	 
		  	 
		  
		  	 
			  
		  }
		  
		   
			
			let trs=document.getElementsByTagName('tr');
			
			for(let i=1;i<trs.length;i++){
				
				students.addEntryOnSet(trs[i]);
			}
			
			document.getElementById('loading').setAttribute('style','display:none;');
		  
	  }
	};
	
	
	
	
});



document.getElementById('confirm').getElementsByClassName('btn btn-danger')[0].
addEventListener('click',function(event){
	
	event.stopPropagation();
	
	document.getElementById('confirm').extras.remove();
	
	document.getElementById('confirm').getElementsByClassName('btn btn-primary')[0].click();
	
	fixFirstColumnIndex();
	
	
	
});



function createRow(){
	
let newRow=document.createElement('tr');
	
let table=document.getElementsByTagName('tbody');

let allRows=table[0].getElementsByTagName('tr');
	
	newRow.innerHTML=`\
			<tr>		<td style="	    display: inline-flex;	">\
		<div class="container" style="	    display: inline-flex;	">	\
		<i class="fas fa-user-minus" style="cursor:pointer;padding-right: 30%; color:red;"\
		data-toggle="tooltip" title="Delete row" >	\
		</i>	\
		<i style="	    padding-right: 15%;	" class="far fa-save" data-toggle="tooltip" title=
		"Save student on database"></i>	\
		</div></td><td data-toggle="tooltip" title="Please insert student\'s E-mail (required)" contenteditable="true">\
		<div contenteditable="true"></div><br></td>\
		<td data-toggle="tooltip" title="Please insert student\'s Name (required)" contenteditable="true">\
		<div contenteditable="true"></div><br></td>\
		<td data-toggle="tooltip" title="Please insert student\'s Surname (required)" contenteditable="true">\
		<div contenteditable="true"></div><br></td>\
		<td data-toggle="tooltip" title="Please insert Student\'s birhdate">\
		<input id="datepicker${allRows.length}" width="150" /></td>	\
		<td data-toggle="tooltip" title="Please insert student\'s Year of enrollment format DD/MM/YYYY" contenteditable="true">\
		<div></div><br></td><td contenteditable="false">\
	<div class="custom-control custom-switch">
		<input class="custom-control-input"  checked="" type="checkbox"><label class="custom-control-label" >
		Yes</label></div><br></td>	<td> <div class="dropdown">
  <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
    Informatics and telematics
  </button>
  <div class="dropdown-menu" >
    <a class="dropdown-item" href="#">Home economics and ecology</a>
    <a class="dropdown-item" href="#">Geography</a>
    <a class="dropdown-item" href="#">International master of sustainable tourism development</a>
    <a class="dropdown-item" href="#">Nutrition and dietics</a>
  </div>
</div>
		</td>\
			<td contenteditable="true" data-toggle="tooltip" title="Please insert student's phone"><div></div><br></td>
			<td contenteditable="true" data-toggle="tooltip" title="Please insert student's address"><div></div><br></td></tr>`
	
		return newRow;
}


// event listener για να προστεθεί επιπλέον γραμμή στον πίνακα
document.getElementById('addStudent').addEventListener('click',function(){

	
	let table=document.getElementsByTagName('tbody');
	
	let allRows=table[0].getElementsByTagName('tr');
	
	
	
	let newRow=createRow();
		
	
	table[0].appendChild(newRow);
	
	let tds2 =newRow.getElementsByTagName('td');
	
	
	for(let i =1;i<tds2.length;i++){
		
		switch (i){
		
				case 1: case 2: case 3: case 5: case 8: case 9:
				tds2[i].addEventListener('mouseover',function(){
					
					if(this.getElementsByClassName('fas').length==0 && this.textContent.trim().length>0 
							
					 && !$(this).is(':focus')){
						
						this.title="Looks good";
					}else if(this.title.trim().match('Looks')!=null){
						
						this.title='Please correct this one';
					}
				
				});
			break;
			
			case 4:
				
				tds2[i].addEventListener('mouseover',function(){
				
				
					if(this.childNodes[1].childNodes[0].value.trim().match(/\d{2}\/\d{2}\/\d{4}/)!=null &&
							this.childNodes[1].childNodes[0].value.trim().length>0 && this.getElementsByClassName('fas').
							length==0){
						
						this.title="Looks good";
					}else{
						
						this.title='Please correct this one';
					}
					
				});
				
				
			
				
				
				break;
		}
	}
	
	newRow.getElementsByClassName('fas fa-user-minus')[0].addEventListener('click',function(){
		
		let row=this.parentElement.parentElement.parentElement;
		
		if(validRow(row)){
		
			document.getElementById('confirm').extras=row;
			
			document.getElementById('confirm').getElementsByTagName('p')[0].textContent=
				`Number ${row.childNodes[1].textContent} row is a valid row and it's going to be deleted Press delete if you are sure`
			
			$('#confirm').modal('show');
		}else{
			
			row.remove();
			fixFirstColumnIndex();
		}
		
		
		
	});
	
	newRow.getElementsByClassName('far fa-save')[0].addEventListener('click',function(){
		
		let row=this.parentElement.parentElement.parentElement;
		
		if(validRow(row)){
			
			console.log(sendEntry(returnEntryFromRow(row)));
			
						
			
			
			
		}else{
			
			$('#saveError').modal('show');
		}
		
	});
	
	
	// Προσθήκη αύξοντα αριθμού στην στήλη 1 του πίνακα.
	
	function tmp(){
		
		let rows=document.getElementsByTagName('tr');
		
		
		
		
		
		for(let i=1;i<rows.length;i++){
			
			
			rows[i].getElementsByTagName('td')[0].firstChild.textContent=i;
		}
	}
	
	
	
	let choices=newRow.getElementsByClassName('dropdown-item');
	
	
	for(let i=0;i<choices.length;i++){
		
		
		choices[i].addEventListener('click',function(){
			
			
			let currentValue=this;
			
			while(currentValue.getElementsByClassName('dropdown').length<=0){
				
				currentValue=currentValue.parentNode;
			}
			
			// To currentValue είναι το td
			
			selectedValue=currentValue.childNodes[1].firstElementChild.textContent.trim();
			
			let tmp=this.text
			
			this.text=selectedValue;
			
			currentValue.childNodes[1].firstElementChild.textContent=tmp;
			 
			k=this;
			
			return;
			
			
			
			
		});
		
		
	}
	
	
	
	
	
	
	 $('#datepicker'+`${allRows.length-1}`).datepicker({
         uiLibrary: 'bootstrap4'
     });
	 
	 newRow.date='';
	 
	 var ts=newRow.getElementsByTagName('td');
	 
	 
	 
	 for(let i=1;i<ts.length;i++){
		 
// if(ts[i].getAttribute('contenteditable')==false){
// continue;
// }
		 
		// Διπλό Event
		 $(ts[i]).on('click focus',function(){
			 
			
			 if(this.getElementsByClassName('fas fa-times').length>0){
				 
				 let infos=this.getElementsByTagName('i');
				 
				 for(j=0;j<infos.length;j++){
					 
					 if(infos[j].getAttribute('class')!=null) {
						 continue;
					 }
					 
					 infos[j].remove();
				 }
				 
				 
			 }
		 });
		 
		 
	 }
	 
	 newRow.addEventListener('click',function(){
		 
		 if(this.date!=this.getElementsByTagName('input')[0].value){
			 
			 this.date=this.getElementsByTagName('input')[0].value;
			 
			 if(this.date.trim().match(/\d{2}\/\d{2}\/\d{4}/)==null){
				 
				 let icon=document.createElement('i');
					
					icon.innerHTML='<i class="fas fa-times" style="color:red;" data-toggle="tooltip" title=\
						" Not a valid date format. correct format dd/mm/yyyy"></i>';
					icon.setAttribute('style',"padding-left:5%;")
					
					this.getElementsByClassName('gj-datepicker gj-datepicker-bootstrap gj-unselectable input-group')[0].
					parentNode.appendChild(icon);
					
					this.getElementsByClassName('gj-datepicker gj-datepicker-bootstrap gj-unselectable input-group')[0].
					parentNode.setAttribute('style',"display:flex");
					
					
			 }
			 
			
			 
		 }
		 
	 });
	 
	 
	 
	 
	 
	
	 let tds=newRow.getElementsByTagName('td');
	 
	 
	 
	 for(let i=1;i<tds.length;i++){
		 
		 switch (i) {
		 
		case 1:
			
			tds[i].addEventListener('blur', function(){
				
				if(this.getElementsByClassName('fas fa-times').length>0){
					
					return;
				}
				
				// Validation για email
				if(tds[i].textContent.trim().match(/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/)==null){
					
					
					
					
					let icon=document.createElement('i');
					
					icon.innerHTML='<i class="fas fa-times" style="color:red;" data-toggle="tooltip" title=\
						"Email doesn\'t seems to be valid"></i>';
					tds[i].childNodes[1].appendChild(icon);
				}
				
				
			});
			
		
			
			
			
			break;
			
		case 2:
			
			tds[i].addEventListener('blur', function(){
				
				
				// Υπάρχει ήδη ετικέτα
				if(this.getElementsByTagName('i').length>0){
					
					return;
				}
				
				if(tds[i].textContent.trim().match(/^(.){3,20}$/)==null){
					
					
					
					
					let icon=document.createElement('i');
					
					icon.innerHTML='<i class="fas fa-times" style="color:red;" data-toggle="tooltip" title=\
						"Name must contains characters min lengh 3 max 20 "></i>';
					tds[i].childNodes[1].appendChild(icon);	
				}
				
				
			});
			
			break;
			
		case 3:
				tds[i].addEventListener('blur', function(){
				
				
				// Υπάρχει ήδη ετικέτα
				if(this.getElementsByTagName('i').length>0){
					
					return;
				}
				
			
				if(this.getElementsByTagName('tr').length>0){
					
					return;
				}
					
				// Validation για Surname
				if(tds[i].textContent.trim().match(/^(.){3,20}$/)==null){
					
					
					
					
					let icon=document.createElement('i');
					
					icon.innerHTML='<i class="fas fa-times" style="color:red;" data-toggle="tooltip" title=\
						"Surname must contains characters min lengh 3 max 20 "></i>';
					tds[i].childNodes[1].appendChild(icon);	
				}
				
				
				});
			
			break;
			
		case 5:
			
			tds[i].addEventListener('blur', function(){
				
				if(this.getElementsByClassName('fas fa-times').length>0){
					
					return;
				}
				
			
				if(tds[i].textContent.trim().match(/^(\d){4}$/)==null){
					
					
					
					
					let icon=document.createElement('i');
					
					icon.innerHTML='<i class="fas fa-times" style="color:red;" data-toggle="tooltip" title=\
						"Wrong year , valid year format yyyy "></i>';
					tds[i].childNodes[1].appendChild(icon);	
				}
				
				
			});
		
			break;
		case 6:
			
			
			
			tds[i].addEventListener('click',function(){
				
				k=this;
				if(this.childNodes[1].childNodes[1].checked==true){
					
					this.childNodes[1].childNodes[1].checked=false;
					this.getElementsByClassName('custom-control-label')[0].textContent="No";
				}else{

					this.childNodes[1].childNodes[1].checked=true;
					this.getElementsByClassName('custom-control-label')[0].textContent="Yes";
				}
				
				
			});
				
			
				
				
				
			break;
			
			
			
		case 8:
			
			tds[i].addEventListener('blur', function(){
				
				if(this.getElementsByClassName('fas fa-times').length>0){
					
					return;
				}
				
			
				if(tds[i].textContent.trim().match(/^(\+){0,1}(\d){10,15}$/)==null){
					
					
					
					
					let icon=document.createElement('i');
					
					icon.innerHTML='<i class="fas fa-times" style="color:red;" data-toggle="tooltip" title=\
						"Not a valid number "></i>';
					this.appendChild(icon);	
				}
				
				
			});
		
			break;
			
		case 9:
			
			tds[i].addEventListener('blur', function(){
				
				if(this.getElementsByClassName('fas fa-times').length>0){
					
					return;
				}
				
			
				if(tds[i].textContent.trim().match(/^(\d|-|.){10,150}$/)==null){
					
					
					
					
					let icon=document.createElement('i');
					
					icon.innerHTML='<i class="fas fa-times" style="color:red;" data-toggle="tooltip" title=\
						"Not a valid address 10 characters minimum "></i>';
					this.appendChild(icon);	
				}
				
				
			});
		
			break;
			
			
			
		
			
			
				
	
			

		default:
			break;
		}
		 
	 }
	 
	 fixFirstColumnIndex();
		
});

 
function fixFirstColumnIndex(){
	
	let rows=document.getElementsByTagName('tr');
	
	
	
	
	
	for(let i=1;i<rows.length;i++){
		
		// console.log(rows[i].getElementsByTagName('td')[0].firstChild,i);
		
		try{		
			rows[i].getElementsByTagName('td')[0].childNodes[0].textContent=i;
		}catch(err){
			
			console.log(err);
		}
	}
}

/**
 * 
 * @param row
 *            Η γραμμή που θέλω να ελέγξω αν είναι έγκυρη
 * @returns true αν η γραμμή δεν έχει λάθη
 */
function validRow(row){
	
	// Προσομείωση του blur
	let evt=new Event('blur');
	
	let tds=row.getElementsByTagName('td');
	
	for(let i=0;i<tds.length;i++){
		
		if(i==4)
			k=tds[i].getElementsByTagName('input')[0].value;
	
		 if( i==4 && tds[i].getElementsByClassName('fas fa-times').length==0
				 && tds[i].getElementsByTagName('input')[0].value.length<5){
			 
			 
			 l=tds[i];
			 let icon=document.createElement('i');
				
			 icon.innerHTML='<i class="fas fa-times" style="color:red;" data-toggle="tooltip" title=\
				" Field required. correct format dd/mm/yyyy"></i>';
	        	icon.setAttribute('style',"padding-left:5%;")
			tds[i].getElementsByTagName('div')[0].appendChild(icon);	
				
		 
	        	
		
		 }
		 
		 tds[i].dispatchEvent(evt);
	}
	
	if(row.getElementsByClassName('fas fa-times').length>0){
		
		return false;
	}
	
	return true;
}


function returnEntryFromRow(row){
	
	entry={}
	
	entry.email=row.childNodes[2].textContent.trim();
	entry.name=row.childNodes[4].textContent.trim();
	entry.surname=row.childNodes[6].textContent.trim();
	entry.birthDate=row.getElementsByTagName('input')[0].value;
	entry.yearOfEnrollment=row.childNodes[10].textContent.trim();
	entry.postGraduate=row.childNodes[11].childNodes[1].childNodes[1].checked
	entry.department=row.childNodes[13].childNodes[1].childNodes[1].textContent.trim();
	entry.phone=row.childNodes[15].textContent.trim();
	entry.address=row.childNodes[17].textContent.trim();
	
	return entry;
	
}

function sendEntry(student){
	
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr(
	  "content");

	
	var xhttp = new XMLHttpRequest();
	xhttp.open('POST','createStudent',
			  true);
	xhttp.setRequestHeader('Content-Type',
	  'application/json;charset=UTF-8');
	xhttp.setRequestHeader(header, token);
	xhttp.send(JSON.stringify(student));
	

	
	xhttp.onreadystatechange = function() {
	  if (this.readyState == 4 && this.status == 200) {
	    
		  
		  result=xhttp.responseText;
		  
		  handleResult(result);
	  }
	};
}

function handleResult(result){
	
	result=JSON.parse(result);
	
	if(result.result==200){
		
		let allTrs=document.getElementsByTagName('tr');
		
		for(let i=1;i<allTrs.length;i++){
			
			if(returnEntryFromRow(allTrs[i]).email==result.username){
				
			   	 let currentTr=allTrs[i];
			  	 
			  	 let currentTds=currentTr.getElementsByTagName('td');
			  	 
			  	 let oldElement;
			  	 
			  	 let newElement;
			  	 
			  	 for(let i=0;i<currentTds.length-2;i++){
			  		 
			  		newElement=currentTds[i].cloneNode(true);
			  		
			  		currentTds[i].parentNode.replaceChild(newElement,currentTds[i]);
			  		 
			  		newElement.setAttribute('data-toggle','tooltip');
			  		
			  		newElement.setAttribute('title',"Employee is now allowed to modify this field");
			  		
			  		currentTds[i].setAttribute('contenteditable','false')
			  		
			  				  		
			  		if(i==4){
			  			
			  			currentTds[i].getElementsByTagName('input')[0].readOnly=true;
			  		}
			  		
			  		 
			  		
			  	 }
			}
			
			break;
		}
		
	}
}

