function saveStation() {
    let stationName = document.getElementById("stationName").value;
    xhr.open("POST", "/api/station", false);
    let station = {
        id: selectedStationId,
        name: stationName
    };
    xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    xhr.send(JSON.stringify(station));
    if (xhr.status == 200) {
        modalStation.hide();
        selectedStationId = null;
        buttonActivate(btnEditStation, btnDeleteStation, true);
        updateStations();
    }
}

function deleteStation() {
    if (selectedStationId == null) {
        return;
    }
    modalConfirm.show();
    //TODO: реализовать подтверждение удаления
    xhr.open("DELETE", "/api/station?id="+selectedStationId, false);
    xhr.send();
    if (xhr.status == 200) {
        selectedStationId = null;
        buttonActivate(btnEditStation, btnDeleteStation, true);
        updateStations();
    }
}

function getStation() {
    if (selectedStationId == null) {
        return;
    }
    xhr.open("GET", "/api/station?id="+selectedStationId, false);
    xhr.send();
    if (xhr.status == 200) {
        let station = JSON.parse(xhr.responseText);
        document.getElementById("stationName").value = station.name;
        modalStation.show();
    }
}

btnDeleteStation.addEventListener("click", (e) => {
    deleteStation();
});

btnCreateStation.addEventListener("click", function() {
    selectedStationId = null;
    buttonActivate(btnEditStation, btnDeleteStation, true);
    clearSelection(baseUl.children);
    document.getElementById("stationName").value = "";
    modalStation.show();
});

btnEditStation.addEventListener("click", function() {
    getStation();
});