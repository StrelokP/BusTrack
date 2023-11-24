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

    xhr.open("POST", "/api/login", false);
//    const formData = new FormData();
//
//    formData.append("login", logLogin.value);
//    formData.append("password", logPassword.value);

    let auth = {
        login : logLogin.value,
        password : logPassword.value
    };
    xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    xhr.send(JSON.stringify(auth));
    if (xhr.status == 200) {
        let toastLiveExample = document.getElementById('liveToast');
        let alertBox = document.getElementById('alertBox');
        let toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLiveExample);
        alertBox.innerHTML = "Успешная авторизация!";
        toastBootstrap.show();
        modalLog.hide();

        let response = JSON.parse(xhr.responseText);

        let jwt = response.token;
        setCookie('Authorization', jwt, 1);

        let currentRole = response.role.name;
        if (currentRole == 'ADMIN') {
            location.href = "/admin";
        } else if (currentRole == 'USER'){
            location.href = "/user";
        } else {
            console.error("Модератор ещё не реализован");
        }


    } else {
        let toastLiveExample = document.getElementById('liveToast');
        let alertBox = document.getElementById('alertBox');
        let toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLiveExample);
        alertBox.innerHTML = "Ошибка! " + JSON.parse(xhr.responseText).message;
        toastBootstrap.show();
    }

});


function setCookie(cname, cvalue, exdays) {
  const d = new Date();
  d.setTime(d.getTime() + (exdays*24*60*60*1000));
  let expires = "expires="+ d.toUTCString();
  document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}

function getCookie(cname) {
  let name = cname + "=";
  let decodedCookie = decodeURIComponent(document.cookie);
  let ca = decodedCookie.split(';');
  for(let i = 0; i <ca.length; i++) {
    let c = ca[i];
    while (c.charAt(0) == ' ') {
      c = c.substring(1);
    }
    if (c.indexOf(name) == 0) {
      return c.substring(name.length, c.length);
    }
  }
  return "";
}
//TODO: добавить валидацию