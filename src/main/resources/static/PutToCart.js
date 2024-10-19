
function putRequest(id)
{
	fetch("http://localhost:8080/cartService",{

        method: 'PUT',
        headers: {
            'Content-Type': 'text/plain'
        },
        body: id
    }).then(data => console.log)


    var itemNumber = document.getElementById("itemNumber")
    itemNumber.innerHTML = ""+(parseInt(itemNumber.innerHTML)+1)
}