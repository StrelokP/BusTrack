let xhr = new XMLHttpRequest();

/* Параметры карты */
let lat = null;
let lng = null;

/* Модальное окно загрузки */
const modalUpload = new bootstrap.Modal(document.getElementById('modalUpload'), {});
let btnUpload = document.getElementById("btnUpload");
let btnUploadPerform = document.getElementById("btnUploadPerform");
let upload = document.getElementById("upload");
/* Модальное окно подтверждения */
const modalConfirm = new bootstrap.Modal(document.getElementById('modalConfirm'), {});
let btnYes = document.getElementById("btnYes");
let btnNo = document.getElementById("btnNo");
btnNo.addEventListener("click", (e) => { modalConfirm.hide(); });
/* Кнопки остановок */
let selectedStationId = null;
const modalStation = new bootstrap.Modal(document.getElementById('modalStation'), {});
let btnCreateStation = document.getElementById("btnCreateStation");
let btnEditStation = document.getElementById("btnEditStation");
let btnDeleteStation = document.getElementById("btnDeleteStation");
let baseUl = document.getElementById("listItemsStation");

/* Кнопки ролей */


function clearSelection(list) {
    Array.prototype.forEach.call(list, child => {
        child.classList.remove("li-selected");
    });
}

function buttonActivate(b1, b2, status) {
    b1.disabled = status;
    b2.disabled = status;
}