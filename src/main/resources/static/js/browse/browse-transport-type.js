function updateTransportTypes() {
    xhr.open("GET", "/api/transportTypes", false);
    xhr.send();

    let elements = [];
    if (xhr.status == 200) {
        elements = JSON.parse(xhr.responseText);
    }

    let baseUl = document.getElementById("listItemsTransportType");
    baseUl.innerHTML = "";

    elements.forEach((e) => {
        let listItem = document.createElement("li");
        listItem.classList.add("list-group-item");
        let listItemText = document.createTextNode(e.name);
        listItem.appendChild(listItemText);
        baseUl.appendChild(listItem);
    });
}

updateTransportTypes();