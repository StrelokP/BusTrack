const modalLog = new bootstrap.Modal(document.getElementById('modalLog'), {});
const modalReg = new bootstrap.Modal(document.getElementById('modalReg'), {});

let xhr = new XMLHttpRequest();

let btnLog = document.getElementById("btnLog");

btnLog.addEventListener("click", function() {
    modalLog.show();
});

let btnLogPerform = document.getElementById("btnLogPerform");
btnLogPerform.addEventListener("click", function() {
    let logLogin = document.getElementById("logLogin");
    let logPassword = document.getElementById("logPassword");

    xhr.open("POST", "/login", false);
    const formData = new FormData();

    formData.append("login", logLogin.value);
    formData.append("password", logPassword.value);

    xhr.send(formData);
    if (xhr.status == 200) {
        let toastLiveExample = document.getElementById('liveToast');
        let alertBox = document.getElementById('alertBox');
        let toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLiveExample);
        alertBox.innerHTML = "Успешная авторизация!";
        toastBootstrap.show();
        modalLog.hide();
        console.log(xhr);
        location.href = xhr.responseURL;
    } else {
        let toastLiveExample = document.getElementById('liveToast');
        let alertBox = document.getElementById('alertBox');
        let toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLiveExample);
        alertBox.innerHTML = "Ошибка! " + JSON.parse(xhr.responseText).message;
        toastBootstrap.show();
    }

});

//TODO: добавить валидацию