
//Προσθήκη λειτουργίας διαγραφής γραμμής όταν πατηθεί το delete button

setRowNumbers();
setRemoveFunctionallity();


function setRemoveFunctionallity(){

	var deleteButtons=document.getElementsByClassName('btn btn-danger btn-rounded btn-sm my-0');
	
	for(var i=0;i<deleteButtons.length;i++){
		
		deleteButtons[i].addEventListener('click',function(){
	
			//Μετάβαση στο δέντρο δυο κόμβους πάνω έτσι ώστε να σβήσω το row
			this.parentNode.parentNode.parentElement.remove();
			
			setRowNumbers();
	
			});
		
	
	}
	
}

//Διορθώνει τα πεδία στη στήλη # που αντιστοιχεί στον αύξοντα αριθμό του πίνακα
function setRowNumbers(){
	
	var rows=document.getElementById('mainTable').getElementsByTagName('tr');
	
	 for(var i=1;i<rows.length;i++){
		
		 rows[i].getElementsByTagName('td')[0].textContent=i;
	 }
	
	
}


var createNewRowButton= document.getElementById('createNewRow');

createNewRowButton.addEventListener('click',function(){
	
	var tr=document.createElement('tr');
	
	var td=document.createElement('td');
	
	td.setAttribute('class','pt-3-half');
	td.setAttribute('contenteditable','false');
	
	tr.appendChild(td);
	
	for(var i=0;i<10;i++){
		
		td=document.createElement('td');
		td.setAttribute('class','pt-3-half');
		td.setAttribute('contenteditable','true');
		
		tr.appendChild(td);
	}
	
	td=document.createElement('td');
	
	span=document.createElement('span');
	span.setAttribute('class','table-remove');
	
	
	
	var button=document.createElement('button');
	
	button.setAttribute('class','btn btn-danger btn-rounded btn-sm my-0');
	button.setAttribute('type','button');
	button.textContent="Delete";
	
	span.appendChild(button);
	
	td.appendChild(span);
	
	document.getElementsByTagName('tBody')[0].appendChild(tr);
	
	setRowNumbers();
	
	tr.appendChild(td);
	
	setRemoveFunctionallity();

});




