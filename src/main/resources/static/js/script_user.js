let authUserData;//authenticated user data

// Function to fetch authenticated user data
function fetchAuthUserData() {
    return fetch('/api/user')
        .then(response => response.json())
        .catch(error => {
            console.error('Error:', error);
        });
}

// // Function to fetch all users data
// function fetchAllUsersData() {
//     return fetch('/api/admin/all')
//         .then(response => response.json())
//         .catch(error => {
//             console.error('Error:', error);
//         });
// }

// Fetch data from the API when the page loads
window.addEventListener('load', () => {
    // Fetch authenticated user data
    fetchAuthUserData()
        .then(data => {
            authUserData = data;
            populateTable(data);
        });

    // Fetch all users data
    // fetchAllUsersData()
    //     .then(data => {
    //         allUsersData = data;
    //         populateTableAll(data);
    //     });
});

const tableBody = document.querySelector('#userTable tbody'); //one user table

// Function to populate the table with user data

function populateTable(userData) { //for ONE user
    // Clear existing table rows
    tableBody.innerHTML = '';

    // Create a table row for the user
    const row = document.createElement('tr');

    // Create table cells for each data field
    const idCell = createTableCell(userData.id);
    const nameCell = createTableCell(userData.userName);
    const statusCell = createTableCell(userData.userStatus);
    const ageCell = createTableCell(userData.userAge);
    const emailCell = createTableCell(userData.login);
    const roleCell = createTableCell(userData.roles.map(role => role.name.replace('ROLE_', '')).join(', '));

    // Append the cells to the row
    row.appendChild(idCell);
    row.appendChild(nameCell);
    row.appendChild(statusCell);
    row.appendChild(ageCell);
    row.appendChild(emailCell);
    row.appendChild(roleCell);

    // Append the row to the table body
    tableBody.appendChild(row);
}
// Function to create a table cell with the given text content
function createTableCell(text) {
    const cell = document.createElement('td');
    cell.textContent = text;
    return cell;
}

//---------------------------------------------------------------------------------------------

//buttons
const adminButton = document.getElementById('to-admin-panel');
const userButton = document.getElementById('to-user-section');
//admin section divs
const adminDiv0 = document.getElementById('admin-section-0');
const adminDiv1 = document.getElementById('admin-section-1');
const adminDiv2 = document.getElementById('nav-home');
const adminDiv3 = document.getElementById('nav-profile');
//user section divs
const userDiv0 = document.getElementById('authorized-user-section-0');
const userDiv1 = document.getElementById('authorized-user-section-1');
const userDiv2 = document.getElementById('authorized-user-section-2');

// Add event listeners to the buttons
adminButton.addEventListener('click', function () {
    toNormalBtn(adminButton);
    toOutlinedBtn(userButton);
    //
    hideDiv(userDiv0);
    hideDiv(userDiv1);
    hideDiv(userDiv2);
    showDiv(adminDiv0);
    showDiv(adminDiv1);
    showDiv(adminDiv2);
    showDiv(adminDiv3);
});
userButton.addEventListener('click', function () {
    toNormalBtn(userButton);
    toOutlinedBtn(adminButton);
    //
    showDiv(userDiv0);
    showDiv(userDiv1);
    showDiv(userDiv2);
    hideDiv(adminDiv0);
    hideDiv(adminDiv1);
    hideDiv(adminDiv2);
    hideDiv(adminDiv3);
});

// Function to hide a div
function hideDiv(element) {
    element.classList.add('d-none');
};

// Function to show a div
function showDiv(element) {
    element.classList.remove('d-none');
};

//func change buttons style
function toOutlinedBtn(btn) {
    btn.classList.remove('btn-primary');
    btn.classList.add('btn-outline-primary');
    btn.classList.add('border-0');
}
function toNormalBtn(btn) {
    btn.classList.add('btn-primary');
    btn.classList.remove('btn-outline-primary');
    btn.classList.remove('border-0');
}

// change color of input in new-user tab
function changeInputBackground(input) {
    if (input.value.length > 0) {
        input.classList.add('custom-input');
    } else {
        input.classList.remove('custom-input');
    }
};
