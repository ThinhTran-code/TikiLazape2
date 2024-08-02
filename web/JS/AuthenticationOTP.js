const btn_cancel = document.querySelector('.cancel-btn'),
        btn_submit = document.querySelector('.submit-btn'),
        btn_getCode = document.querySelector('.btn-getcode');
btn_cancel.addEventListener('click', () => {
    $.ajax({
        url: "/tikilazapee/sendcaptcha",
        type: 'DELETE',
        success: function (data) {
            location.href = "login";
        }
    });
});
setTimeout(function () {
    btn_getCode.addEventListener('click', () => {
        $.ajax({
            url: "/tikilazapee/sendcaptcha",
            type: 'POST',
            success: function () {

            }
        });
    });
}, 6000 * 10);
let count = 0;
let OTPcode = "";
function authenticate(OTP) {
    const inputs = document.querySelectorAll('.code');
    let tempOTP = "";
    inputs.forEach((input, e) => {
        tempOTP += input.value;
    });
    OTPcode = tempOTP;
    if (OTP != OTPcode) {
        count++;
        inputs.forEach((input1, e) => {
            input1.classList.add('danger');
        });


        document.querySelector('p').innerHTML = "<br> OTP is not correct!!!";

        if (count >= 5) {
            $.ajax({
                url: "/tikilazapee/sendcaptcha",
                type: 'DELETE',
                success: function (data) {
                    location.href = "login";
                }
            });
        }
    } else {
        $.ajax({
            url: "/tikilazapee/authentication",
            type: 'POST',
            success: function (data) {
                location.href = "index";
            }
        });
    }
    console.log(OTPcode);
}