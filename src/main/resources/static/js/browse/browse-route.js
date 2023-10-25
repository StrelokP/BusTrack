function updateRoutes() {
    xhr.open("GET", "/api/routes", false);
    xhr.send();

    let elements = [];
    if (xhr.status == 200) {
        elements = JSON.parse(xhr.responseText);
    }

    let baseUl = document.getElementById("listItemsRoute");
    baseUl.innerHTML = "";

    elements.forEach((e) => {
        let listItem = document.createElement("li");
        listItem.classList.add("list-group-item");
        let listItemText = document.createTextNode(e.num);
        listItem.appendChild(listItemText);
        baseUl.appendChild(listItem);
    });
}

updateRoutes();