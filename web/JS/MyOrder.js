/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


const btn_view_detail = document.querySelectorAll('.btn-view-detail');
const btn_cancel = document.querySelectorAll('.btn-cancel');
const btn_confirm = document.querySelectorAll('.btn-confirm');
const btn_buy_again = document.querySelectorAll('.btn-buy-again');
console.log(btn_view_detail);
btn_view_detail.forEach((i) => {
    i.addEventListener('click', () => {
        const order_id = i.getAttribute("order_id");
        console.log(order_id);
        location.href = "orderdetail?order-id=" + order_id;
    });
});
btn_confirm.forEach((i) => {
    i.addEventListener('click', () => {
        const order_id = i.getAttribute("order_id");
        const status = i.getAttribute("status");
        changeStatusAjax(order_id, status);
    });
});

btn_cancel.forEach((i) => {
    i.addEventListener('click', () => {
        const order_id = i.getAttribute("order_id");
        const status = i.getAttribute("status");
        if (confirm("Are you sure to cancel this order")) {
            changeStatusAjax(order_id, status);
        }
    });
});

btn_buy_again.forEach((i)=>{
    i.addEventListener('click',()=>{
        const order_id = i.getAttribute("order_id");
        console.log(order_id);
        $.ajax({
            url: "/tikilazapee/repurchase",
            type: 'POST',
            contentType: 'application/json',
            data:JSON.stringify({
                'order_id': order_id
            }),
            success: function(){
                console.log(order_id);
                location.href = "cart";
            }
        });
    });
});

function changeStatusAjax(order_id, status) {
    $.ajax({
        url: "/tikilazapee/myorder",
        type: 'put',
        contentType: 'application/json',
        data: JSON.stringify({
            'order_id': order_id,
            'status': status
        }),
        success: function () {
            location.reload();
        }
    });
}