
var header = document.getElementById('header')
var resMenu = document.getElementById('res-menu-btn')
var headerHeight = header.clientHeight

if (resMenu != null) resMenu.onclick = function(){
    var isClose = header.clientHeight === headerHeight;
    if (isClose){
        header.style.height = 'auto';
    }
    else {
        header.style.height = '50px';
    }
}

var password = document.getElementById("password")
  , confirm_password = document.getElementById("confirm-password");

function validatePassword(){
  if(password.value != confirm_password.value) {
    confirm_password.setCustomValidity("Passwords Don't Match");
  } else {
    confirm_password.setCustomValidity('');
  }
}

if (password != null) password.onchange = validatePassword;
if (confirm_password != null) confirm_password.onkeyup = validatePassword;