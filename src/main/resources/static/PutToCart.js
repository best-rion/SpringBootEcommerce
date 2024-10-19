
function putRequest(id)
{
	fetch("http://localhost:8080/addToCart",{

        method: 'PUT',
        headers: {
            'Content-Type': 'text/plain'
        },
        body: id
    })
	.then(response=>response.text())
	.then(body=>{
		if (body === "1")
		{
			var itemNumber = document.getElementById("itemNumber")
			itemNumber.innerHTML = ""+(parseInt(itemNumber.innerHTML)+1)
		} 
	})
}