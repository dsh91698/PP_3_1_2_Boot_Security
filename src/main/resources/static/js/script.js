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
