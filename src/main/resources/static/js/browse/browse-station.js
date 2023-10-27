function updateStations() {
    xhr.open("GET", "/api/stations", false);
    xhr.send();

    let elements = [];
    if (xhr.status == 200) {
        elements = JSON.parse(xhr.responseText);
    }

    baseUl.innerHTML = "";

    elements.forEach((e) => {
        let listItem = document.createElement("li"); //<li> </li>
        listItem.addEventListener("click", (elem) => {
            if (selectedStationId == elem.target.getAttribute("value")) {
                selectedStationId = null;
                clearSelection(baseUl.children);
                buttonActivate(btnEditStation, btnDeleteStation, true);
            } else {
                selectedStationId = elem.target.getAttribute("value");
                clearSelection(baseUl.children);
                elem.target.classList.add("li-selected");
                buttonActivate(btnEditStation, btnDeleteStation, false);
            }
        });
        listItem.classList.add("list-group-item"); //<li class="list-group-item"> </li>
        let listItemText = document.createTextNode(e.name); //Название
        listItem.appendChild(listItemText); //<li class="list-group-item">Название</li>
        listItem.setAttribute("value", e.id);
        baseUl.appendChild(listItem);
    });
    document.getElementById("stationName").value = "";
}

updateStations();