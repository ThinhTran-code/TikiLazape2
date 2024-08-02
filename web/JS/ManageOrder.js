/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


const btn_view_details = document.querySelectorAll('.btn-view-detail');
const btn_declineds = document.querySelectorAll('.btn-declined');
const btn_approveds = document.querySelectorAll('.btn-approved');
const btn_confirms = document.querySelectorAll('.btn-confirm');

btn_view_details.forEach(btn =>{
    btn.addEventListener('click',()=>{
        const order_id = btn.getAttribute('order_id');
        location.href = "orderdetailseller?order_id="+order_id;
    });
});

btn_declineds.forEach(btn =>{
    btn.addEventListener('click',()=>{
        const order_id = btn.getAttribute('order_id');
        const status = btn.getAttribute('status');
        changeStatusAjax(order_id,status);
    });
});

btn_confirms.forEach(btn =>{
    btn.addEventListener('click',()=>{
        const order_id = btn.getAttribute('order_id');
        const status = btn.getAttribute('status');
        changeStatusAjax(order_id,status);
    });
});
function changeStatusAjax(order_id, status){
    $.ajax({
        url: "/tikilazapee/seller/manageorder",
        type:'put',
        contentType: 'application/json',
        data:JSON.stringify({
            'order_id':order_id,
            'status':status
        }),
        success: function(){
            location.reload();
        }
    });
}

