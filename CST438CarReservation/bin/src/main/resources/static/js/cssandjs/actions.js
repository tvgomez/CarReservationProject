function sortByPrice(){
	console.log("ButtonPressed");
	var table, rows, switching, i, x, y, shouldSwitch;
	table = document.getElementById("carTable");
	switching = true;
	while(switching){
		switching = false;
		rows = table.rows;
		console.log(rows.length);
		for (i =1; i < (rows.length -1); i++){
			console.log("In for loop " + i);
			shouldSwitch= false;
			x = rows[i].getElementsByTagName("TD")[4];
			console.log("x = " + x.innerHTML);
			y = rows[i+1].getElementsByTagName("TD")[4];
			console.log("y = " + y.innerHTML);
			if(x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()){
				shouldSwitch =true;
				break;
			}
		}
		if (shouldSwitch){
			rows[i].parentNode.insertBefore(rows[i+1], rows[i]);
			switching = true;
		}
	} 
}
/**
 * 
 */