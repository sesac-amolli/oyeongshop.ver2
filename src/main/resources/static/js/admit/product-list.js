function handleChange() {
//function handleChange(selection) {
//    console.log(button,"button");
    var selection = $('#sort option:selected').data('select-sort');

    var url = '/product/list/'+selection;
    // 직접 페이지로 이동

    console.log(selection);
    // Send Ajax request to update product status
    $.ajax({
        type: 'GET',
        url: '/product/list/'+selection,
        data: {selection: selection}
        ,success: function (result) {
            console.log("result",result);
            // Update button text and class based on the new status
            window.location.href = url;
        },
        error: function () {
            console.log('Error updating product status');
        }
    });
}