const myModal = new bootstrap.Modal(document.getElementById('modalStation'), {});

let btnCreateStation = document.getElementById("btnCreateStation");
btnCreateStation.addEventListener("click", function() {
    myModal.show();
});

function saveStation() {
    let stationName = document.getElementById("stationName").value;
    xhr.open("POST", "/api/station", false);
    let station = {
        id: null,
        name: stationName
    };
    xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    xhr.send(JSON.stringify(station));
    if (xhr.status == 200) {
        myModal.hide();
        updateStations();
    }
}