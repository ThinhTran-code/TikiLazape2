const username = document.querySelector('#username').value;
const email = document.querySelector('#email').value;
const btn_send = document.querySelector('#email').nextElementSibling;
function sendMail(){
    
    $.ajax({
        url: "/tikilazapee/sendcaptcha",
        type: 'PUT',
        contentType: 'application/JSON',
        data: JSON.stringify({
            "username":username,
            "email":email
        })      
    });
}

btn_send.addEventListener('click',sendMail);