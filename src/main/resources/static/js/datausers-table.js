// Call the dataTables jQuery plugin
$(document).ready(function() {

   loadUsers()
  $('#dataUsersTable').DataTable();
  loadUserEmail()
});

function loadUserEmail(){
    document.querySelector("#user-email").outerHTML = localStorage.email
}

async function loadUsers(){
    const request = await fetch('api/users', {
        method: 'GET',
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json",
            "Authorization": localStorage.token
        },
    });
    const users = await request.json();
    let userListHtml = "";

    users.forEach((user)=>{
            let btnDelete = `<a href="#" onclick="deleteUser(${user.id})" class="btn btn-danger btn-circle"><i class="fas fa-trash"></i></a>`;
            let cellphone = user.cellphone == null ? "-" : user.cellphone
            let userInTable = `<tr><td>${user.id}</td><td>${user.name} ${user.lastname}</td><td>${user.email}</td><td>${cellphone}</td><td>${btnDelete}</td></tr>`
            userListHtml += userInTable;
    })
    const table = document.querySelector("#dataUsersTable tbody").outerHTML = userListHtml;
}

async function deleteUser(id){
    if(!confirm("Desea eliminar este usuario?")){
        return;
    }

    const request = await fetch('api/users/' + id, {
        method: 'DELETE',
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json",
            "Authorization": localStorage.token
        },
    });
    users.forEach((user)=>{
                let btnDelete = `<a href="#" onclick="deleteUser(${user.id})" class="btn btn-danger btn-circle"><i class="fas fa-trash"></i></a>`;
                let userInTable = `<tr><td>${user.id}</td><td>${user.name} ${user.lastname}</td><td>${user.email}</td><td>${user.cellphone}</td><td>${btnDelete}</td></tr>`
                userListHtml += userInTable;
        })
}
