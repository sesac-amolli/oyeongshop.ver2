// Product Option 테이블의 productId를 업데이트하기 위한 함수
function sendProductIdForUpdate(button) {
//    console.log(button,"button");
    var productId = $(button).data('product-id');

//     Send Ajax request to update product status
    console.log("id", productId);
    $.ajax({
        type: 'POST',
        url: '/product/register/' + productId,
        data: {productId: productId}
        ,success: function (result) {
            console.log("result",result);
            // Update button text and class based on the new status
        },
        error: function () {
            console.log('Error updating product status');
        }
    });
}


//function sendProductIdForUpdate(button) {
//    var productId = $(button).data('product-id');
//
//    // 첫 번째 Ajax 요청
//    $.ajax({
//        type: 'GET',
//        url: '/product/register/picture/' + productId,
//        data: { productId: productId },
//        success: function (firstResult) {
//            console.log("First request result", firstResult);
//
//            // 두 번째 Ajax 요청
//            $.ajax({
//                type: 'POST',
//                url: '/product/register/picture/' + productId,
//                data: { productId: productId },
//                success: function (secondResult) {
//                    console.log("Second request result", secondResult);
//
//                    // 세 번째 Ajax 요청
//                    $.ajax({
//                        type: 'POST',
//                        url: '/product/register/' + productId,
//                        data: { productId: productId },
//                        success: function (thirdResult) {
//                            console.log("Third request result", thirdResult);
//                            // 세 번째 Ajax 요청이 성공한 경우 실행할 코드 추가
//                        },
//                        error: function () {
//                            console.log('Error updating product status (third request)');
//                        }
//                    });
//                },
//                error: function () {
//                    console.log('Error updating product status (second request)');
//                }
//            });
//        },
//        error: function () {
//            console.log('Error updating product status (first request)');
//        }
//    });
//}