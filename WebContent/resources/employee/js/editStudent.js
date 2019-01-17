
document.getElementById('confirm').getElementsByClassName('btn btn-danger')[0].
addEventListener('click',function(event){
	
	event.stopPropagation();
	
	document.getElementById('confirm').extras.remove();
	
	document.getElementById('confirm').getElementsByClassName('btn btn-primary')[0].click();
	
	fixFirstColumnIndex();
	
	
	
});


//event listener για να προστεθεί επιπλέον γραμμή στον πίνακα
document.getElementsByClassName('btn btn-primary btn-lg my-2 my-sm-0')[0].addEventListener('click',function(){

	
	let table=document.getElementsByTagName('tbody');
	
	let allRows=table[0].getElementsByTagName('tr');
	
	let newRow=document.createElement('tr');
	
	
	
	newRow.innerHTML=`\
			<tr>		<td style="	    display: inline-flex;	">\
		<div class="container" style="	    display: inline-flex;	">	\
		<i class="fas fa-user-minus" style="cursor:pointer;padding-right: 15%; color:red;"\
		data-toggle="tooltip" title="Delete row" >	\
		</i>	\
		<i style="	    padding-right: 15%;	" class="far fa-save"></i>	\
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
			<td contenteditable="true"><div></div><br></td>
			<td contenteditable="true"><div></div><br></td></tr>`
	
		
	table[0].appendChild(newRow);
	
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
			
			console.log(returnEntryFromRow(row));
			
						
			
			
			
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
		 
		//Διπλό Event
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
		
		//console.log(rows[i].getElementsByTagName('td')[0].firstChild,i);
		
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


