let xhr = new XMLHttpRequest();

function updateStations() {
    xhr.open("GET", "/api/stations", false);
    xhr.send();

    let elements = [];
    if (xhr.status == 200) {
        elements = JSON.parse(xhr.responseText);
    }

    let baseUl = document.getElementById("listItemsStation");
    baseUl.innerHTML = "";

    elements.forEach((e) => {
        let listItem = document.createElement("li"); //<li> </li>
        listItem.classList.add("list-group-item"); //<li class="list-group-item"> </li>
        let listItemText = document.createTextNode(e.name); //Название
        listItem.appendChild(listItemText); //<li class="list-group-item">Название</li>
        baseUl.appendChild(listItem);
    });
}


updateStations();