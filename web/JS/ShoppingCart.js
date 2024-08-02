const chk_select_all = document.querySelector('#myCheckBox');
const chk_select_all_bottom = document.querySelector('#cart-checkout-checkbox');
const span_bottom = document.querySelector('.cart-checkout .action-box span');
const chk_product_box_origin = document.querySelectorAll('.cart-main .product-box .checkbox input');
const box_checkout = document.querySelector('.cart-checkout .checkout-box .checkout p');
let numberItemSelected = 0;
let totalPrice = 0;
let length = 0;
chk_product_box_origin.forEach((input) => {
    if (input.disabled !== true) {
        ++length;
    }
});
span_bottom.innerHTML = '(' + length + ')';
chk_select_all.addEventListener('click', () => {
    const status = chk_select_all.checked;
    let chk_product_box = document.querySelectorAll('.cart-main .product-box .checkbox input');
    let count = 0;
    chk_product_box.forEach((input) => {
        if (input.disabled !== true) {
            input.checked = status;
            ++count;
        }
    });

    chk_select_all_bottom.checked = status;
    if (status) {
        numberItemSelected = count;
        ;
        totalPrice = getTotalPrice();
        box_checkout.innerHTML = 'Total (' + numberItemSelected + ' item): <span>' + price_format(totalPrice) + '</span>';
    } else {
        numberItemSelected = 0;
        totalPrice = 0;
        box_checkout.innerHTML = 'Total (0 item): <span>0đ</span>';
    }
});
chk_select_all_bottom.addEventListener('click', () => {
    const status = chk_select_all_bottom.checked;
    let chk_product_box = document.querySelectorAll('.cart-main .product-box .checkbox input');
    let count = 0;
    chk_product_box.forEach((input) => {
        if (input.disabled !== true) {
            input.checked = status;
            ++count;
        }
    });
    chk_select_all.checked = status;
    if (status) {
        numberItemSelected = count;
        totalPrice = getTotalPrice();
        box_checkout.innerHTML = 'Total (' + numberItemSelected + ' item): <span>' + price_format(totalPrice) + '</span>';
    } else {
        numberItemSelected = 0;
        totalPrice = 0;
        box_checkout.innerHTML = 'Total (0 item): <span>0đ</span>';
    }
});
for (var i = 0; i < chk_product_box_origin.length; ++i) {
    if (chk_product_box_origin[i].checked) {
        numberItemSelected++;
        totalPrice += Number(chk_product_box_origin[i].value);
    }
}



function onChecked(btn) {
    if (btn.checked) {
        totalPrice += Number(btn.value);
        console.log(btn.value);
        box_checkout.innerHTML = 'Total (' + (numberItemSelected + 1) + ' item): <span>' + price_format(totalPrice) + '</span>';
        numberItemSelected++;
        if (numberItemSelected == chk_product_box_origin.length) {
            chk_select_all.checked = true;
            chk_select_all_bottom.checked = true;
        }
    } else {
        numberItemSelected--;
        totalPrice -= Number(btn.value);
        box_checkout.innerHTML = 'Total (' + (numberItemSelected) + ' item): <span>' + price_format(totalPrice) + '</span>';
        if (numberItemSelected < chk_product_box_origin.length) {
            chk_select_all.checked = false;
            chk_select_all_bottom.checked = false;
        }
    }
}

function back() {
    window.history.back();
}
function home() {
    window.location.href = 'home';
}

function getTotalPrice() {
    var total = 0;
    chk_product_box_origin.forEach((input) => {
        if (input.disabled !== true) {
            total += Number(input.value);
        }

    });
    return total;
}
function price_format(p) {
    let price = String(p);
    let price_str = "";
    let n = price.length % 3;

    if (n !== 0) {
        price_str += price.substr(0, n) + '.';
    }
    for (var i = n; i < Number(price.length - 3); i += 3) {
        let tmp = (price.substr(i, 3) + '.');
        price_str += tmp;
    }
    if (price.length >= 3) {
        price_str += price.substr(price.length - 3, price.length);
    }
    return price_str + "₫";
}

function changeQuantityAdd(btn, val, unit_price, id, cart_id) {
    let quantity;
    //console.log(val);
    if (Number(val) > 0) {
        quantity = btn.previousElementSibling;
    } else {
        quantity = btn.nextElementSibling;
    }
    let x = Number(Number(quantity.value) + Number(val));
    console.log(quantity.max);
    if (x <= quantity.max) {
        quantity.value = Number(quantity.value) + Number(val);
        const total_price_div = btn.parentNode.nextElementSibling;
        const total_price = Number(unit_price) * Number(quantity.value);
        const checkbox = document.getElementById(id);
        checkbox.value = total_price;
        total_price_div.innerHTML = price_format(Number(unit_price) * Number(quantity.value));
        console.log(x);
        updateToQuantityDB(cart_id, val, unit_price);
    } else {
        window.alert("Sorry, you can only buy max this product " + (x - 1) + " in one checkout");
    }

}

function changeQuantityRemove(btn, val, unit_price, id, cart_id) {
    let quantity;
    //console.log(val);
    if (Number(val) > 0) {
        quantity = btn.previousElementSibling;
    } else {
        quantity = btn.nextElementSibling;
    }
    let x = Number(Number(quantity.value) + Number(val));
    console.log(quantity.max);
    if (x >= 1) {
        quantity.value = Number(quantity.value) + Number(val);
        const total_price_div = btn.parentNode.nextElementSibling;
        const total_price = Number(unit_price) * Number(quantity.value);
        const checkbox = document.getElementById(id);
        checkbox.value = total_price;
        total_price_div.innerHTML = price_format(Number(unit_price) * Number(quantity.value));
        console.log(x);
        updateToQuantityDB(cart_id, val, unit_price);

    } else {
        if (confirm("Do you want to delete this product")) {
            let product_boxes = document.querySelectorAll('.product-box');
            const product_box = btn.parentElement.parentElement;
            product_box.remove();
            updateDeleteToDB(cart_id);
        }
    }

}

function updateToQuantityDB(cart_id, val, unit_price) {
    $.ajax({
        url: "/tikilazapee/cart",
        type: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify({
            "id": cart_id,
            "value": val
        }),
        success: function (data, textStatus, jqXHR) {
            totalPrice = 0;
            for (var i = 0; i < chk_product_box_origin.length; ++i) {
                if (chk_product_box_origin[i].checked) {
                    totalPrice += Number(chk_product_box_origin[i].value);
                }
            }

            box_checkout.innerHTML = 'Total (' + (numberItemSelected) + ' item): <span>' + price_format(totalPrice) + '</span>';

        }
    });
}

function doDelete(id, input) {
    if (confirm("Do you want to delete this product")) {
        let product_boxes = document.querySelectorAll('.product-box');
        const product_box = input.parentElement.parentElement;
        product_box.remove();
        updateDeleteToDB(id);
    }


}
function updateDeleteToDB(id) {
    $.ajax({
        url: "/tikilazapee/cart",
        type: 'DELETE',
        contentType: 'application/json',
        data: JSON.stringify({
            "id": id
        }),
        success: function (data, textStatus, jqXHR) {

        }
    });
}


let product_type;
let toggle_menu_type_color;
const radio_button_type_colors = document.querySelectorAll('.shopping-cart .cart-main .product-box .product-type .menu-type-color-option-toggle input');
const initialState = Array.from(radio_button_type_colors).map(input => input.checked);

function toggle_menu_type_color_f(btn) {
    product_type = btn.parentNode;
    toggle_menu_type_color = btn.nextElementSibling;
    if (toggle_menu_type_color.classList.contains('close')) {
        radio_button_type_colors.forEach((input, index) => {
            if (input.checked) {
                input.nextElementSibling.style.border = "3px solid #ff1a1a";
                input.nextElementSibling.style.color = "#ff1a1a";
            } else {
                input.nextElementSibling.style.border = "2px solid black";
                input.nextElementSibling.style.color = "black";
            }
        });
        toggle_menu_type_color.classList.remove('close');

    } else {
        toggle_menu_type_color.classList.add('close');
    }
}

document.addEventListener('click', e => {
    if (!product_type.contains(e.target) && e.target !== toggle_menu_type_color) {
        toggle_menu_type_color.classList.add('close');
        radio_button_type_colors.forEach((input, index) => {
            input.checked = initialState[index];
        });
    }
    console.log(e.target);
});

function cancel_button(btn) {
    btn.parentNode.parentNode.classList.add('close');
    radio_button_type_colors.forEach((input, index) => {
        input.checked = initialState[index];
    });
}
//function changeColorLableRadioInput(){
//    radio_button_type_colors.forEach((input,index) => {
//        if(input.checked){
//            input.nextElementSibling.style.border = "3px solid #ff1a1a";
//            input.nextElementSibling.style.color = "#ff1a1a";
//        }else{
//            input.nextElementSibling.style.border = "2px solid black";
//            input.nextElementSibling.style.color = "black";
//        }        
//    });
//}
document.addEventListener('click', () => {
    radio_button_type_colors.forEach((input, index) => {
        if (input.checked) {
            input.nextElementSibling.style.border = "3px solid #ff1a1a";
            input.nextElementSibling.style.color = "#ff1a1a";
        } else {
            input.nextElementSibling.style.border = "2px solid black";
            input.nextElementSibling.style.color = "black";
        }
    });
});
//

function getQuantity(product_id, id) {
    const radio_button_type_color_by_class = document.getElementsByClassName(id);
    const checkedRadioButtons = Array.from(radio_button_type_color_by_class).filter(rb => rb.checked === true);
    const color_id = Number(checkedRadioButtons[0].value);
    const type_id = Number(checkedRadioButtons[1].value);
    console.log(color_id + " " + type_id);
    $.ajax({
        url: "/tikilazapee/type_color",
        type: 'GET',

        data: {
            'product_id': product_id,
            'color_id': color_id,
            'type_id': type_id
        },
        success: function (response) {
            let available = document.getElementById(id + 'available');
            available.innerHTML = "Available: " + response;
            console.log(response);
        }
    });
}

function confirm_button(product_id, cartItem_id, shoppingCart_id, ) {
    const radio_button_type_color_by_class = document.getElementsByClassName(cartItem_id);
    const checkedRadioButtons = Array.from(radio_button_type_color_by_class).filter(rb => rb.checked === true);
    const color_id = Number(checkedRadioButtons[0].value);
    const type_id = Number(checkedRadioButtons[1].value);
    const available = Number(document.getElementById(cartItem_id + 'available').innerHTML.replace('Available: ', ''));

    $.ajax({
        url: "/tikilazapee/type_color",
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({
            'product_id': product_id,
            'type_id': type_id,
            'color_id': color_id,
            'cartItem_id': cartItem_id,
            'shoppingCart_id': shoppingCart_id
        }),
        success: function (response) {
            const status = JSON.parse(response).status;
            const message = JSON.parse(response).message;
            if (status === 0)
                window.alert(message + " " + available + " products in my shop!!!!");
            else
                location.reload();


        }
    });
}

function deleteAll(shoppingcart_id) {
    if (window.confirm("Are you sure to delete all product in your cart???")) {
        $.ajax({
            url: "/tikilazapee/type_color",
            type: 'DELETE',
            data: JSON.stringify({
                'shoppingcart_id': shoppingcart_id
            }),
            success: function () {
                const cart_main = document.querySelector('.row .cart-main');
                cart_main.innerHTML = `<div class="select-all-box row">
                            <div class="checkbox col-md-1">
                                <input type="checkbox" id="myCheckBox" name="myCheckBox" />
                                <label for="myCheckBox" style="font-weight: 700;">All</label>
                            </div>
                            <div class="col-md-3">Name product</div>
                            <div class="col-md-2">Type</div>
                            <div class="col-md-1">Unit price</div>
                            <div class="col-md-2">Quantity</div>
                            <div class="col-md-2">Total price</div>
                            <div class="col-md-1">Action</div>
                        </div>`;
            }
        });
    }
}
const btn_checkout = document.getElementById('btn_checkout');

async function getQuantity2(product_id, type_id, color_id) {
    try {
        const response = await $.ajax({
            url: "/tikilazapee/type_color",
            type: 'GET',
            data: {
                'product_id': product_id,
                'color_id': color_id,
                'type_id': type_id
            }
        });

        const available = Number(response);
        return available;
    } catch (error) {
        console.error('Error fetching data:', error);
        return 0; // Handle the error case
    }
}

btn_checkout.addEventListener('click', () => {
    const chk_product_box_origin1 = document.querySelectorAll('.cart-main .product-box .checkbox input');
    const items_id = Array.from(chk_product_box_origin1).filter(rb => rb.checked === true && rb.disabled === false);
    if (items_id.length !== 0 && items_id !== null) {
        let flg = true;
        items_id.forEach((input) => {
            let product_id = input.getAttribute("id"), type_id = input.getAttribute("type_id");
            let color_id = input.getAttribute("color_id"), quantity = Number(input.getAttribute("quantity"));
            let availableQuantity;           
            console.log(quantity + " " + availableQuantity);
            if (availableQuantity < quantity) {
                flg = false;
                console.log(availableQuantity);
                console.log(product_id + " " + color_id + " " + quantity);
            }
        });
        if (flg === false) {
            window.alert("Something went wrong!!! Please try again");
            location.reload();
        } else {
            let item_id = "";
            items_id.forEach(e => item_id += (e.getAttribute("item_id") + ";"));
            location.href = "payment?item_id=" + item_id;
        }
    }

});

