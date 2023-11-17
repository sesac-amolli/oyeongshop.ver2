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