let loginBtn = document.getElementById("loginBtn");
loginBtn.addEventListener("click", login);

function login() {
    let xhr = new XMLHttpRequest();
    xhr.open('POST', '/', false);
    xhr.send(JSON.stringify(obj));
    if (xhr.status != 200) {
      console.log(xhr.status + ' : ' + xhr.statusText);
    } else {
      console.log(xhr.responseText);
    }
}