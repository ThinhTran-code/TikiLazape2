const account_box = document.querySelectorAll('.container .main-right .account-box input');
function Onselected(input){
    const btn = input.parentNode; 
    if(input.checked){        
        btn.style.border = "3px solid green";    
    }
    if(input.checked== false){
        btn.style.border = "";
    }
    
}

function cancel(){
    window.location.href = "login";
}
function onContinue(){
    const radioBoxs = document.getElementsByName('username');
    let username = null, email = null;
    radioBoxs.forEach((input) =>{
        if(input.checked){
            username = input.value;
            email = input.nextElementSibling.value;            
        }
    });
    $.ajax({
        url: "/tikilazapee/sendcaptcha",
        type: 'PUT',
        contentType: 'application/JSON',
        data: JSON.stringify({
            "username":username,
            "email":email
        }),
        success: function(){
            location.href = 'setnewpassword';
        }
    });
}