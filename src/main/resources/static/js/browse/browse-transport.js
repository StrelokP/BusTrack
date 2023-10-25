function updateTransports() {
    xhr.open("GET", "/api/transports", false);
    xhr.send();

    let elements = [];
    if (xhr.status == 200) {
        elements = JSON.parse(xhr.responseText);
    }

    let baseUl = document.getElementById("listItemsTransport");
    baseUl.innerHTML = "";

    elements.forEach((e) => {
        let listItem = document.createElement("li");
        listItem.classList.add("list-group-item");
        let listItemText = document.createTextNode(e.id);
        listItem.appendChild(listItemText);
        baseUl.appendChild(listItem);
    });
}

updateTransports();