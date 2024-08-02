const link_back = document.getElementById('link-back');
link_back.addEventListener('click',()=>{
   window.history.back();           
});

const btn_buy_again = document.querySelectorAll('.btn-buy-again');
const btn_feedback = document.querySelectorAll('.btn-feedback');
btn_buy_again.forEach((i)=>{
    i.addEventListener('click',()=>{
        const order_id = i.getAttribute("order_id");
        const orderDetail_id = i.getAttribute("orderDetail_id");
        console.log(order_id);
        $.ajax({
            url: "/tikilazapee/repurchase",
            type: 'POST',
            contentType: 'application/json',
            data:JSON.stringify({
                'order_id': order_id,
                'orderDetail_id':orderDetail_id
            }),
            success: function(){
                console.log(order_id);
                location.href = "cart";
            }
        });
    });
});


btn_feedback.forEach((i)=>{
    i.addEventListener('click',()=>{
        const orderDetail_id = i.getAttribute("orderDetail_id");
        console.log(orderDetail_id);
        location.href = 'feedbackproduct?orderDetai_id='+orderDetail_id;
    });
});