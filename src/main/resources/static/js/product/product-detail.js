function sendDataToDirectOrder(button) {
//    console.log(button,"button");
    var prodId = $('.hidden').data('prod-id');
    var prodOptColor = $('#colorSelect option:selected').data('prod-opt-color');
    var prodOptSize = $('#sizeSelect option:selected').data('prod-opt-size');
    var quantity = $('input[name="quantity"]').val();
    var prodSalesPrice = $('div[data-prod-sales-price]').data('prod-sales-price');


    //폼에 정보 저장하기
    $("[name=prodId]").val(prodId);
    $("[name=color]").val(prodOptColor);
    $("[name=size]").val(prodOptSize);
    $("[name=quantity]").val(quantity);
    $("[name=prodSalesPrice]").val(prodSalesPrice);

    $("#orderSubForm").submit();

}