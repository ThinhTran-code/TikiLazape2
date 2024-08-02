const btnRegister = document.getElementById('register-button');
const listInput = document.querySelectorAll('.input-box input');
let p = document.createElement('p');
function checkValidateCofirm() {
    var password = document.querySelector('.input-box .password');
    var password_confirm = document.querySelector('.input-box .confirm-password');
    if (password.value != password_confirm.value) {
        password.classList.add('notice');
        password_confirm.classList.add('notice');
        p.innerText = 'Password and Confirm-password must be same';
        password_confirm.parentNode.parentNode.append(p);
        p.classList.add('notice-p');
    }
}
function checkNull() {
    let flg = true;
    console.log([...listInput]);
    for (let i = 0; i < listInput.length; ++i) {
        if (listInput[i].value == "") {
            listInput[i].classList.add('notice');
            flg = false;
        } else {
            listInput[i].classList.remove('notice');
        }
    }
    if(flg == true){
        document.querySelector('form').submit();
    }

}

btnRegister.addEventListener('click', checkNull);
btnRegister.addEventListener('click', checkValidateCofirm);
