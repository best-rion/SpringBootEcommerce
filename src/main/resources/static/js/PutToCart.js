

function increaseQuantity(product_id)
{
	fetch
	(
		"http://localhost:8080/increaseQty",
		{
	        method: 'PUT',
	        headers:{'Content-Type': 'text/plain'},
	        body: product_id
	    }
	)
	.then(response=>response.text())
	.then(body=>{
		if (body === "1")
		{
			var quantity = document.getElementById("quantity"+product_id)
			quantity.innerHTML = ""+(parseInt(quantity.innerHTML)+1)
			
			var itemNumber = document.getElementById("itemNumber")
			itemNumber.innerHTML = ""+(parseInt(itemNumber.innerHTML)+1)

			location.reload()
		} 
	})

}



function decreaseQuantity(product_id)
{
	fetch
	(
		"http://localhost:8080/decreaseQty",
		{
	        method: 'PUT',
	        headers: {'Content-Type': 'text/plain'},
	        body: product_id
    	}
	)
	.then(response=>response.text())
	.then(body=>{
		if (body === "1")
		{
			var quantity = document.getElementById("quantity"+product_id)
			quantity.innerHTML = ""+(parseInt(quantity.innerHTML)-1)
			
			var itemNumber = document.getElementById("itemNumber")
			itemNumber.innerHTML = ""+(parseInt(itemNumber.innerHTML)-1)

			location.reload()
		} 
	})
		
}



function removeItem(product_id)
{
	fetch
	(
		"http://localhost:8080/removeItem",
		{
	        method: 'PUT',
	        headers: {'Content-Type': 'text/plain'},
	        body: product_id
    	}
	)
	.then
	(
		()=>
		{
			var quantity = document.getElementById("quantity"+product_id)
	
			var itemNumber = document.getElementById("itemNumber")
			itemNumber.innerHTML = ""+(parseInt(itemNumber.innerHTML)-parseInt(quantity.innerHTML))
	
			quantity.innerHTML = "0"
			
			location.reload()
		}
	)
	
}




