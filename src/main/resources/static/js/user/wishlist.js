$(document).ready(function () {
    let wishListId = $("#jjimAdd").data("wish-list-id");
    let jjimAddDiv = $("#jjimAdd");
    let jjimMinusDiv = $("#jjimMinus");

    console.log(wishListId);

    if (wishListId !== 0) {
        jjimAddDiv.hide();
        jjimMinusDiv.show();
    } else {
        jjimAddDiv.show();
        jjimMinusDiv.hide();
    }
});

function jjimAdd(button){

    var prodId = button.dataset.prodId;
    var userId = button.dataset.userId;

    console.log("prodId" + prodId);
    if(userId == null) {
        alert("로그인이 필요한 서비스 입니다.");
    }
    else {
        $.ajax({
                type: 'POST',
                url: '/user/wishlist/'+prodId,
                success: function (result) {
                    console.log("result",result);
                    let jjimAddDiv = $("#jjimAdd");
                    let jjimMinusDiv = $("#jjimMinus");

                    jjimAddDiv.hide();
                    jjimMinusDiv.show();
                    $("#jjimContainer").data("wish-list-id", result);
    //               $("#jjimContainer").data("wish-list-id").value = result;
                },
                error: function () {
                    console.log('Error updating product status');
                }
            });
    }

}

function jjimMinus(button){

    var prodId = button.dataset.prodId;
    var userId = button.dataset.userId;

    console.log("prodId" + prodId);

    if(userId == null) {
            alert("로그인이 필요한 서비스 입니다.");
        }
    else {
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
}


/*
function jjimMinus2(prodId){
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
*/


function jjimMinus2(prodId) {
    console.log("prodId" + prodId);

    $.ajax({
        type: 'POST',
        url: '/user/wishlist-delete/' + prodId,
        success: function (result) {
            console.log("result", result);
            let jjimAddDiv = $("#jjimAdd");
            let jjimMinusDiv = $("#jjimMinus");

            jjimAddDiv.show();
            jjimMinusDiv.hide();

            // Reload the page
            location.reload();
        },
        error: function () {
            console.log('Error updating product status');
        }
    });
}

