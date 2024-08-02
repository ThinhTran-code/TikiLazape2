const radio_button_type_colors = document.querySelectorAll('.payment-method-box .payment-method-box-left input');
const initialState = Array.from(radio_button_type_colors).map(input => input.checked);
radio_button_type_colors.forEach(e=>{
 e.addEventListener('click', () => {
    radio_button_type_colors.forEach((input, index) => {
        if (input.checked) {
            input.nextElementSibling.style.border = "2px solid white";
            input.nextElementSibling.style.color = "white";
        } else {
            input.nextElementSibling.style.border = "1px solid rgb(228, 227, 227)";
            input.nextElementSibling.style.color = "black";
        }
    });
});
});
