function calculateTotalAmount() {
  // quantity 입력값 가져오기
  const quantity = parseInt(document.getElementsByName("quantity")[0].value);

  // 판매가 가져오기
  const prodSalesPrice = parseFloat(document.getElementById("prodSalesPrice").innerText);
  console.log("prodSalesPrice" + prodSalesPrice);

  // 총 주문금액 계산
  const totalAmount = quantity * prodSalesPrice;
  console.log("totalAmount" + totalAmount);

  // 총 주문금액 표시
  document.getElementById("totalAmount").innerText = totalAmount;
}

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

function sendDataToCart(button) {
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

    $("#cartSubForm").submit();

}