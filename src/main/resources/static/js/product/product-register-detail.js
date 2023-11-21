//// Product Option 테이블의 productId를 업데이트하기 위한 함수
//function sendProductIdForUpdate(button) {
////    console.log(button,"button");
//    var productId = $(button).data('product-id');
//
////     Send Ajax request to update product status
//    console.log("id", productId);
//    $.ajax({
//        type: 'POST',
//        url: '/product/register/' + productId,
//        data: {productId: productId}
//        ,success: function (result) {
//            console.log("result",result);
//            // Update button text and class based on the new status
//        },
//        error: function () {
//            console.log('Error updating product status');
//        }
//    });
//}