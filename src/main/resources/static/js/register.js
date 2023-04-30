// Call the dataTables jQuery plugin
$(document).ready(function() {

});

async function registerUser(){
    let data = {};
    data.name = document.querySelector("#input-name").value;
    data.lastname = document.querySelector("#input-lastname").value;
    data.email = document.querySelector("#input-email").value;
    data.password = document.querySelector("#input-password").value;
    let pwRepeat = document.querySelector("#input-repeat-password").value;
    if(pwRepeat!==data.password){
        alert("Las contraseñas no coinciden")
        return;
    }

    const request = await fetch('api/users', {
        method: 'POST',
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
    });
    alert("La cuenta fue creada con éxito");
    window.location.href="login.html"
}

