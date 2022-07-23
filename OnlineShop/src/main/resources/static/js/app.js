function openTheForm() {
    document.getElementById("popupForm").style.display = "block";
}

function closeTheForm() {
    document.getElementById("popupForm").style.display = "none";
}

function myFunction() {
    let x = document.getElementById("popupForm");

    if (x.style.display === "none") {
        x.style.display = "block";
    } else {
        x.style.display = "none";
    }
}

let refreshUsers = document.getElementById('refresh')
refreshUsers.addEventListener('click', loadUsers);
refreshUsers.addEventListener('load', loadUsers)

let button = '<button type="submit" onclick="deleteConfirmation()">Delete</button>'

function deleteConfirmation() {
    let result = window.confirm('Are you sure you want to delete this user')

    if (result === true) {
        button = {
            method: 'POST',

        }
    }

}

function loadUsers(event) {
    let requestOptions = {
        method: 'GET',
        redirect: 'follow'
    };

    let userContainer = document.getElementById('user-container')
    userContainer.innerHTML = ''

    fetch("/users/all-users", requestOptions)
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
            let buttonDelete = document.createElement('td')





            id.textContent = user.id
            username.textContent = user.username
            name.textContent = user.name
            addressCount.textContent = user.addressCount
            orderCount.textContent = user.orderCount
            totalSumOrders.textContent = user.totalSumOrders
            buttonDelete.insertAdjacentHTML("afterbegin", button)



            // add the columns to the parent row
            row.appendChild(id)
            row.appendChild(username)
            row.appendChild(name)
            row.appendChild(addressCount)
            row.appendChild(orderCount)
            row.appendChild(totalSumOrders)
            row.appendChild(buttonDelete)

            userContainer.appendChild(row);
        }))
        .catch(error => console.log('error', error));
}
