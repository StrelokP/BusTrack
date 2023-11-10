function saveStation() {
    let stationName = document.getElementById("stationName").value;
    xhr.open("POST", "/api/station", false);
    let station = {
        id: selectedStationId,
        name: stationName,
        lat: lat,
        lng: lng
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
    modalConfirm.show();
    btnYes.addEventListener("click", (c) => {
        deleteStation();
        modalConfirm.hide();
    })
});

btnCreateStation.addEventListener("click", function() {
    selectedStationId = null;
    buttonActivate(btnEditStation, btnDeleteStation, true);
    clearSelection(baseUl.children);
    document.getElementById("stationName").value = "";
    lat = null;
    lng = null;
    modalStation.show();
});

btnEditStation.addEventListener("click", function() {
    getStation();
});

btnUpload.addEventListener("click", function() {
     modalUpload.show();
 });

btnUploadPerform.addEventListener("click", function() {
    if (upload.files.length == 0) {
        alert("Выберете файлы"); //TODO: переделать на уведомления
        return;
    }
    let fileData = new FormData();
    fileData.append('file', upload.files[0]);
    fileData.append('type', "station");

    xhr.open("POST", "/api/file", false);
    //xhr.setRequestHeader("Content-Type", "multipart/form-data");
    xhr.send(fileData);

    if (xhr.status == 200) {
        let toastLiveExample = document.getElementById('liveToast');
        let alertBox = document.getElementById('alertBox');
        let toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLiveExample);
        alertBox.innerHTML = xhr.responseText;
        toastBootstrap.show();

        modalUpload.hide();
        updateStations();
    } else {
        let toastLiveExample = document.getElementById('liveToast');
        let alertBox = document.getElementById('alertBox');
        let toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLiveExample);
        alertBox.innerHTML = xhr.responseText;
        toastBootstrap.show();
        console.log(xhr);
    }
 });

function initMap() {
  const map = new google.maps.Map(document.getElementById("map"), {
    zoom: 14,
    center: { lat: 51.52970826835268, lng: 45.97985254567595 },
  });

  map.addListener("click", (mapsMouseEvent) => {
  //TODO: реагировать
    lat = mapsMouseEvent.latLng.lat();
    lng = mapsMouseEvent.latLng.lng();
  });
}

window.initMap = initMap;