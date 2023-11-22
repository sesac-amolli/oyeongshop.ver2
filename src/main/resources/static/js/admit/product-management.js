// 판매등록 버튼 색깔 변경
$(document).ready(function () {
    // Click event listener for elements with class 'toggleButton'
    $('.toggleButton').click(function () {
        // Toggle 'btn-success' and 'btn-danger' classes
        $(this).toggleClass('btn-success btn-danger');

        // Toggle text between 'YES' and 'NO'
        var buttonText = $(this).text();
        $(this).text(buttonText === 'YES' ? 'NO' : 'YES');

        // Set color based on text
        if ($(this).text() === 'YES') {
            $(this).removeClass('btn-danger').addClass('btn-success');
        } else if ($(this).text() === 'NO') {
            $(this).removeClass('btn-success').addClass('btn-danger');
        }
    });
});

function toggleProductStatus(button) {
//    console.log(button,"button");
    var productId = $(button).data('product-id');

//     Send Ajax request to update product status
    console.log("id", productId);
    $.ajax({
        type: 'POST',
        url: '/admit/product/editor/' + productId,
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