let btnReg = document.getElementById("btnReg");
btnReg.addEventListener("click", function() {
    modalReg.show();
});

let btnRegPerform = document.getElementById("btnRegPerform");
btnRegPerform.addEventListener("click", function() {
    let regLogin = document.getElementById("regLogin");
    let regPassword = document.getElementById("regPassword");

    let user = {
        "id" : null,
        "login": regLogin.value,
        "password": regPassword.value
    };

    xhr.open("POST", "/api/user", false);
    xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    xhr.send(JSON.stringify(user));
    if (xhr.status == 200) {
        let toastLiveExample = document.getElementById('liveToast');
        let alertBox = document.getElementById('alertBox');
        let toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLiveExample);
        alertBox.innerHTML = "Успешная регистрация! Можете авторизоваться.";
        toastBootstrap.show();
        modalReg.hide();
    } else {
        let toastLiveExample = document.getElementById('liveToast');
        let alertBox = document.getElementById('alertBox');
        let toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLiveExample);
        alertBox.innerHTML = "Ошибка! " + xhr.responseText;
        toastBootstrap.show();
    }
});