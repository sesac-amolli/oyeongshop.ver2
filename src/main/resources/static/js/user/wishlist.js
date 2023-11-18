
$(document).ready(function () {
    let wishListId = $("#jjimContainer").data("wish-list-id");
    let jjimAddDiv = $("#jjimAdd");
    let jjimMinusDiv = $("#jjimMinus");

    if (wishListId !== null) {
        jjimAddDiv.show();
        jjimMinusDiv.hide();
    } else {
        jjimAddDiv.hide();
        jjimMinusDiv.show();
    }
});



function jjimAdd(prodId){
    console.log("prodId" + prodId);

    $.ajax({
            type: 'POST',
            url: '/user/wishlist/'+prodId,
            success: function (result) {
                console.log("result",result);
                let jjimAddDiv = $("#jjimAdd");
                    let jjimMinusDiv = $("#jjimMinus");

                        jjimAddDiv.hide();
                        jjimMinusDiv.show();
                        $("#jjimContainer").data("wish-list-id").value = result;

            },
            error: function () {
                console.log('Error updating product status');
            }
        });
}

function jjimMinus(prodId){
    console.log("prodId" + prodId);

    $.ajax({
            type: 'POST',
            url: '/user/wishlist-delete/'+prodId,
            success: function (result) {
                console.log("result",result);
                let jjimAddDiv = $("#jjimAdd");
                    let jjimMinusDiv = $("#jjimMinus");

                        jjimAddDiv.show();
                        jjimMinusDiv.hide();


            },
            error: function () {
                console.log('Error updating product status');
            }
        });
}