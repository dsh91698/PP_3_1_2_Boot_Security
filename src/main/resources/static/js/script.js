let authUserData;//authenticated user data
let allUsersData;//all user from DB data

// Function to fetch authenticated user data
function fetchAuthUserData() {
    return fetch('/api/user')
        .then(response => response.json())
        .catch(error => {
            console.error('Error:', error);
        });
}

// Function to fetch all users data
function fetchAllUsersData() {
    return fetch('/api/admin/all')
        .then(response => response.json())
        .catch(error => {
            console.error('Error:', error);
        });
}

// Fetch data from the API when the page loads
window.addEventListener('load', () => {
    // Fetch authenticated user data
    fetchAuthUserData()
        .then(data => {
            authUserData = data;
            populateTable(data);
        });

    // Fetch all users data
    fetchAllUsersData()
        .then(data => {
            allUsersData = data;
            populateTableAll(data);
        });
});

const tableBody = document.querySelector('#userTable tbody');//one user table
const tableBodyAll = document.querySelector('#allTable tbody');//ALL users table


// Function to populate the table with user data
function populateTableAll(userData) {// for ALL users from array

    // Clear existing table rows
    tableBodyAll.innerHTML = '';

    // Loop through each user and create a table row
    userData.forEach(user => {
        const row = document.createElement('tr');

        // Create table cells for each data field
        const idCell = createTableCell(user.id);
        const nameCell = createTableCell(user.userName.trim());
        const statusCell = createTableCell(user.userStatus.trim());
        const ageCell = createTableCell(user.userAge);
        const emailCell = createTableCell(user.login.trim());
        const roleCell = createTableCell(user.roles.map(role => role.name.replace('ROLE_', '')).join(', '));

        // Create table cell for the modal window button
        const modalEditCell = document.createElement('td');
        modalEditCell.innerHTML = `
            <button type="button" class="btn btn-info" data-bs-toggle="modal" data-bs-target="#editModal-${user.id}">Edit</button>
            <div class="modal fade" id="editModal-${user.id}" tabindex="-1" aria-labelledby="editModalLabel" aria-hidden="true">
<!--            -->
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <!-- Modal content here -->
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="editModalLabel">Edit User</h5>
                                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                </div>
                                                <div class="modal-body">
                                                    <form id="editForm+${user.id}" 
                                                          method="post" action="/api/edit/${user.id}"
                                                          data-user-id="${user.id}"
                                                          class="edit-form needs-validation row g-3 p-3 d-flex flex-column align-items-center">

                                                        <div class="row mb-3 mx-auto position-relative justify-content-center">
                                                            <label for="userId" class="form-label fw-bold text-center col-sm-8">ID</label>
                                                            <input type="text" value="${user.id}"
                                                                   id="userId"
                                                                   class="form-control" readonly>
                                                        </div>

                                                        <div class="row mb-3 mx-auto position-relative justify-content-center">
                                                            <label for="userName" class="form-label fw-bold text-center col-sm-8">User Name</label>
                                                            <input type="text" value="${user.userName}"
                                                                   id="userName"
                                                                   name='userName'
                                                                   class="form-control">
                                                        </div>

                                                        <div class="row mb-3 mx-auto position-relative justify-content-center">
                                                            <label for="userAge" class="form-label fw-bold text-center col-sm-8">Age</label>
                                                            <input type="text" value="${user.userAge}"
                                                                   id="userAge"
                                                                   name="userAge"
                                                                   class="form-control">
                                                        </div>

                                                        <div class="row mb-3 mx-auto position-relative justify-content-center">
                                                            <label for="userStatus" class="form-label fw-bold text-center col-sm-8">User status</label>
                                                            <select value="${user.userStatus}"
                                                                    id="userStatus"
                                                                    name="userStatus"
                                                                    class="form-control">
                                                                <option value="active">Active</option>
                                                                <option value="banned">Banned</option>
                                                                <option value="read-only">Read-only</option>
                                                                <option value="registered">Registered</option>
                                                            </select></div>

                                                        <div class="row mb-3 mx-auto position-relative justify-content-center">
                                                            <label for="loginUser" class="form-label fw-bold text-center col-sm-8">Email</label>
                                                            <input type="text" value="${user.login}"
                                                                   id="loginUser"
                                                                   name="login"
                                                                   class="form-control">
                                                        </div>


                                                        <div class="row mb-3 mx-auto position-relative justify-content-center">
                                                            <label for="passwordUser" class="form-label fw-bold text-center col-sm-8">Password</label>
                                                            <input type="password" id="passwordUser"
                                                                   placeholder="Enter new password"
                                                                   name="password"
                                                                   class="form-control">
                                                        </div>

                                                        <div class="row mb-3 mx-auto position-relative justify-content-center">
                                                            <!---->
                                                            <label for="userRoles" class="form-label fw-bold text-center col-sm-8">Role</label>
                                                            <div id="userRoles">
                                                                <div>
                                                                    <input type="checkbox" id="userRoleEdit"
                                                                           name="roles"
                                                                           value="ROLE_USER"
                                                                           ${user.authoritiesAsString.includes('USER') ? 'checked' : ''} />
                                                                    <label for="userRoleEdit">USER</label>
                                                                </div>
                                                                <div>
                                                                    <input type="checkbox" id="adminRoleEdit"
                                                                           name="roles"
                                                                           value="ROLE_ADMIN"
                                                                           ${user.authoritiesAsString.includes('ADMIN') ? 'checked' : ''} />
                                                                    <label for="adminRoleEdit">ADMIN</label>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <!---->
                                                    </form>


                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-secondary"
                                                            data-bs-dismiss="modal"> Close
                                                    </button>
                                                    <button type="button submit" class="btn btn-primary edit-form-btn"
                                                            data-user-id="${user.id}"
                                                            form="editForm+${user.id}">Save Changes
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                
<!--                -->
            </div>
        `;

        const modalDeleteCell = document.createElement('td');
        modalDeleteCell.innerHTML = `
            <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#delModal-${user.id}">Delete</button>
                        <div class="modal fade" id="delModal-${user.id}" tabindex="-1" aria-labelledby="delModalLabel" aria-hidden="true">
<!--            -->
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <!-- Modal content here -->
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="delModalLabel">Edit User</h5>
                                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                </div>
                                                <div class="modal-body">
                                                    <form id="delForm+${user.id}" 
                                                          method="post" action="/api/delete/${user.id}"
                                                          data-user-id="${user.id}"
                                                          class="del-form needs-validation row g-3 p-3 d-flex flex-column align-items-center">

                                                        <div class="row mb-3 mx-auto position-relative justify-content-center">
                                                            <label for="userId" class="form-label fw-bold text-center col-sm-8">ID</label>
                                                            <input type="text" value="${user.id}"
                                                                   id="userId"
                                                                   class="form-control" readonly>
                                                        </div>

                                                        <div class="row mb-3 mx-auto position-relative justify-content-center">
                                                            <label for="userName" class="form-label fw-bold text-center col-sm-8">User Name</label>
                                                            <input type="text" value="${user.userName}"
                                                                   id="userName"
                                                                   name='userName'
                                                                   class="form-control" readonly>
                                                        </div>

                                                        <div class="row mb-3 mx-auto position-relative justify-content-center">
                                                            <label for="userAge" class="form-label fw-bold text-center col-sm-8">Age</label>
                                                            <input type="text" value="${user.userAge}"
                                                                   id="userAge"
                                                                   name="userAge"
                                                                   class="form-control" readonly>
                                                        </div>

                                                        <div class="row mb-3 mx-auto position-relative justify-content-center">
                                                            <label for="userStatus" class="form-label fw-bold text-center col-sm-8">User status</label>
                                                            <select value="${user.userStatus}"
                                                                    id="userStatus"
                                                                    name="userStatus"
                                                                    class="form-control" readonly>
                                                                <option value="active">Active</option>
                                                                <option value="banned">Banned</option>
                                                                <option value="read-only">Read-only</option>
                                                                <option value="registered">Registered</option>
                                                            </select></div>

                                                        <div class="row mb-3 mx-auto position-relative justify-content-center">
                                                            <label for="loginUser" class="form-label fw-bold text-center col-sm-8">Email</label>
                                                            <input type="text" value="${user.login}"
                                                                   id="loginUser"
                                                                   name="login"
                                                                   class="form-control" readonly>
                                                        </div>


                                                        <div class="row mb-3 mx-auto position-relative justify-content-center">
                                                            <label for="passwordUser" class="form-label fw-bold text-center col-sm-8">Password</label>
                                                            <input type="password" id="passwordUser"
                                                                   placeholder="Enter new password"
                                                                   name="password"
                                                                   class="form-control" readonly>
                                                        </div>

                                                        <div class="row mb-3 mx-auto position-relative justify-content-center">
                                                            <!---->
                                                            <label for="userRoles" class="form-label fw-bold text-center col-sm-8">Role</label>
                                                            <div id="userRoles">
                                                                <div>
                                                                    <input type="checkbox" id="userRoleEdit"
                                                                           name="roles"
                                                                           value="ROLE_USER"
                                                                           ${user.authoritiesAsString.includes('USER') ? 'checked' : ''} 
                                                                           disabled/>
                                                                    <label for="userRoleEdit">USER</label>
                                                                </div>
                                                                <div>
                                                                    <input type="checkbox" id="adminRoleEdit"
                                                                           name="roles"
                                                                           value="ROLE_ADMIN"
                                                                           ${user.authoritiesAsString.includes('ADMIN') ? 'checked' : ''} 
                                                                           disabled/>
                                                                    <label for="adminRoleEdit">ADMIN</label>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <!---->
                                                    </form>


                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-secondary"
                                                            data-bs-dismiss="modal"> Close
                                                    </button>
                                                    <button type="button submit" class="btn btn-danger del-form-btn"
                                                            data-user-id="${user.id}"
                                                            form="delForm+${user.id}">Delete
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                
<!--                -->
            </div>

            `;


        // Append the cells to the row
        row.appendChild(idCell);
        row.appendChild(nameCell);
        row.appendChild(statusCell);
        row.appendChild(ageCell);
        row.appendChild(emailCell);
        row.appendChild(roleCell);
        row.appendChild(modalEditCell);
        row.appendChild(modalDeleteCell);
//edit handler add
        let  editForms = modalEditCell.querySelectorAll('.edit-form');
        editForms.forEach(form => {
            const userId = form.getAttribute('data-user-id');
            form.addEventListener('submit', event => handleSubmit(event, userId));
        });
//delete handler add
        let  delForms = modalDeleteCell.querySelectorAll('.del-form');
        delForms.forEach(form => {
            const userId = form.getAttribute('data-user-id');
            form.addEventListener('submit', event => handleDelete(event, userId));
        });


        // Append the row to the table body
        tableBodyAll.appendChild(row);

    });
}

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


// Prevent form submission and update URL
function handleSubmit(event, userId) {
    event.preventDefault();

    // Make an API call using the Fetch API to handle form submission
    fetch(`/api/edit/${userId}`, {
        method: 'PATCH',
        body: new FormData(event.target),
    })
        .then(response => console.log('handler -> ',response.json()))
        .then(data => {
            // Handle the response data
            console.log(data);
            fetchAuthUserData()
                .then(data => {
                    console.log('auth user-> ', data )
                    populateTable(data);
                });

            // Fetch all users data
            fetchAllUsersData()
                .then(data => {
                    populateTableAll(data);
                });

        })
        .catch(error => {
            console.error('Error:', error);
        });
    const modal = bootstrap.Modal.getInstance(document.getElementById(`editModal-${userId}`));
    modal.hide();
}
function handleDelete(event, userId) {
    event.preventDefault();

    // Make an API call using the Fetch API to handle form submission
    fetch(`/api/delete/${userId}`, {
        method: 'DELETE',
        body: new FormData(event.target),
    })
        .then(response => console.log('handler -> ',response.json()))
        .then(data => {
            // Handle the response data
            console.log(data);
            // Fetch all users data
            fetchAllUsersData()
                .then(data => {
                    populateTableAll(data);
                });
        })
        .catch(error => {
            console.error('Error:', error);
        });
    const modal = bootstrap.Modal.getInstance(document.getElementById(`delModal-${userId}`));
    modal.hide();
}

// Attach submit event listeners to the forms
let  editForms = document.querySelectorAll('.edit-form');
editForms.forEach(form => {
    const userId = form.getAttribute('data-user-id');
    form.addEventListener('submit', event => handleSubmit(event, userId));
});

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
