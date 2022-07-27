window.setTimeout(successMessage, 3000)

function successMessage() {
    document.getElementById("success").style.display="none";
}

function showForm() {
    let x = document.getElementById("popupForm");

    if (x.style.display === "none") {
        x.style.display = "block";
    } else {
        x.style.display = "none";
    }
}

function deleteConfirmation(link) {
    let message = 'Are you sure you want to delete this user?'

    if (message === true) {
        return location.href = link
    } else {
        return false
    }
}

let refreshUsers = document.getElementById('refresh')

refreshUsers.addEventListener('click', loadUsers);

function loadUsers(event) {
    let requestOptions = {
        method: 'GET',
        redirect: 'follow'
    };

    let userContainer = document.getElementById('user-container')
    userContainer.innerHTML = ''

    fetch("/users/admin/all-users", requestOptions)
        .then(response => response.json())
        .then(json => json.forEach(user => {
            // here we will create some elements and add them to the table.
            let row = document.createElement('tr')

            let id = document.createElement('td')
            let username = document.createElement('td')
            let name = document.createElement('td')
            let addressCount = document.createElement('td')
            let orderCount = document.createElement('td')
            let totalSumOrders = document.createElement('td')
            let action = document.createElement('td')
            let buttonDelete = document.createElement('button')

            id.textContent = user.id
            username.textContent = user.username
            name.textContent = user.name
            addressCount.textContent = user.addressCount
            orderCount.textContent = user.orderCount
            totalSumOrders.textContent = user.totalSumOrders

            var deleteLink = '/users/admin/delete/' + user.id


            console.log(deleteLink)
            buttonDelete.type = 'submit'
            buttonDelete.setAttribute('href', deleteLink)
            buttonDelete.textContent = 'Delete'


            action.insertAdjacentElement("afterbegin", buttonDelete)



            // add the columns to the parent row
            row.appendChild(id)
            row.appendChild(username)
            row.appendChild(name)
            row.appendChild(addressCount)
            row.appendChild(orderCount)
            row.appendChild(totalSumOrders)
            row.appendChild(action)

            userContainer.appendChild(row);
        }))
        .catch(error => console.log('error', error));
}
// //For viewing BLOB object.
// var xhr = new XMLHttpRequest();
// xhr.open("GET", "http://localhost:8080/add-file/1");
// xhr.responseType = "blob";
// xhr.onload = response;
// xhr.send();
//
// function response(e) {
//     var urlCreator = window.URL || window.webkitURL;
//     var imageURL = urlCreator.createObjectURL(this.response);
//     document.querySelector("#image").src = imageURL;
// }
