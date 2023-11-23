function calculateTotalAmount() {
  // quantity 입력값 가져오기
  const quantity = parseInt(document.getElementsByName("quantity")[0].value);

  // 판매가 가져오기
  const prodSalesPrice = parseFloat(document.getElementById("prodSalesPrice").innerText);

  // 총 주문금액 계산
  const totalAmount = quantity * prodSalesPrice;

  // 총 주문금액 표시
  document.getElementById("totalAmount").innerText = totalAmount;
}

// 개수 입력 값이 변경될 때마다 총 주문금액 계산 및 업데이트
const quantityInput = document.getElementById('quantityInput');
const totalAmount = document.getElementById('totalAmount');

quantityInput.addEventListener('input', function() {
    const quantity = parseInt(quantityInput.value);
    const prodSalesPrice = parseInt('${product.prodSalesPrice}'); // 총 주문금액을 계산할 때 사용할 상품 판매가
    const total = quantity * prodSalesPrice;

    if (!isNaN(total)) {
        totalAmount.textContent = total;
    } else {
        totalAmount.textContent = '0';
    }
});

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