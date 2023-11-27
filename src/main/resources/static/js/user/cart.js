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

// 장바구니에서 삭제하기
function deleteDataToCart(button) {
    // 폼 데이터 초기화
    var deleteForm = $("#cartDeleteForm");
    deleteForm.empty();

    $("input[name='selectedItems']:checked").each(function() {
        var cartItemId = $(this).val(); // Get the value of the current checkbox
        // Create a hidden input and append it to the form
        deleteForm.append('<input type="hidden" name="cartItemIds" value="' + cartItemId + '">');
    });

    deleteForm.submit(); // Submit the form
}

// 주문하기(주문서 생성)
function sendDataToOrder(button) {
    // 폼 데이터 초기화
    var orderForm = $("#orderDataForm");
    orderForm.empty();

    $("input[name='selectedItems']:checked").each(function() {
        var cartItemId = $(this).val(); // Get the value of the current checkbox
        // Create a hidden input and append it to the form
        orderForm.append('<input type="hidden" name="selectedItems" value="' + cartItemId + '">');
    });

    orderForm.submit(); // Submit the form
}

// 장바구니 내역 수정
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

$(document).ready(function() {
            // Update total amount when a checkbox is clicked
            $(".checkstandard").on("change", function() {
                calculateTotal();
            });

            // Function to calculate the total amount
            function calculateTotal() {
                var totalAmount = 0;

                // Loop through checked checkboxes and accumulate amounts
                $(".checkstandard:checked").each(function() {
                    var amount = parseFloat($(this).data("amount")) || 0;
                    totalAmount += amount;
                });

                // Update the total amount in the HTML
                $("#estimated_payment_amount").text(totalAmount.toLocaleString());
            }
});