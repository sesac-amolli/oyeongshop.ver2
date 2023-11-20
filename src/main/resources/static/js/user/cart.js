function incrementQuantity(btn) {
    var input = btn.parentNode.previousElementSibling; // Assuming the input is right before the span containing the buttons
    var currentValue = parseInt(input.value);
    input.value = currentValue + 1;
}

function decrementQuantity(btn) {
    var input = btn.parentNode.previousElementSibling; // Assuming the input is right before the span containing the buttons
    var currentValue = parseInt(input.value);
    if (currentValue > 1) { // Prevent quantity from going below 1
        input.value = currentValue - 1;
    }
}

//function deleteDataToCart(button) {
//    var prodId = $('.hidden').data('prod-id');
//    var prodOptColor = $('#colorSelect option:selected').data('prod-opt-color');
//    var prodOptSize = $('#sizeSelect option:selected').data('prod-opt-size');
//    var quantity = $('input[name="quantity"]').val();
//    var prodSalesPrice = $('div[data-prod-sales-price]').data('prod-sales-price');
//
//   //폼에 정보 저장하기
//    $("[name=prodId]").val(prodId);
//    $("[name=color]").val(prodOptColor);
//    $("[name=size]").val(prodOptSize);
//    $("[name=quantity]").val(quantity);
//    $("[name=prodSalesPrice]").val(prodSalesPrice);
//
//    $("#cartDeleteForm").submit();
//
//}

function deleteDataToCart(button) {
    // 폼 데이터 초기화
    var deleteForm = $("#cartDeleteForm");
    deleteForm.empty();

    // 체크된 체크박스를 찾아 각각 처리
    $("input[name='selectedItems']:checked").each(function() {
        var checkbox = $(this);
        var row = checkbox.closest('tr'); // 체크박스와 관련된 행을 찾음

        var prodId = row.find('.id').data('prod-id'); // 데이터 가져오기
        var color = row.find('.color').text();
        var size = row.find('.size').text();
        var quantity = row.find('.quantity_input').val();
        var prodSalesPrice = row.find('.content').data('prod-sales-price');

        // 폼에 정보 추가
        deleteForm.append('<input type="hidden" name="prodId" value="' + prodId + '">');
        deleteForm.append('<input type="hidden" name="color" value="' + color + '">');
        deleteForm.append('<input type="hidden" name="size" value="' + size + '">');
        deleteForm.append('<input type="hidden" name="quantity" value="' + quantity + '">');
        deleteForm.append('<input type="hidden" name="prodSalesPrice" value="' + prodSalesPrice + '">');
    });

    deleteForm.submit();
}

function modifyDataToCart(button) {
    // 폼 데이터 초기화
    var modifyForm = $("#cartModifyForm");
    modifyForm.empty();

    // 모든 행을 순회하며 데이터 수집
    $("#signup tbody tr").each(function() {
        var row = $(this);

        var prodId = row.find('.id').data('prod-id');
        var color = row.find('.color').text();
        var size = row.find('.size').text();
        var quantity = row.find('.quantity_input').val();
        var prodSalesPrice = row.find('.content').data('prod-sales-price');

        // 폼에 정보 추가
        modifyForm.append('<input type="hidden" name="prodId" value="' + prodId + '">');
        modifyForm.append('<input type="hidden" name="color" value="' + color + '">');
        modifyForm.append('<input type="hidden" name="size" value="' + size + '">');
        modifyForm.append('<input type="hidden" name="quantity" value="' + quantity + '">');
        modifyForm.append('<input type="hidden" name="prodSalesPrice" value="' + prodSalesPrice + '">');
    });

    modifyForm.submit();
}
