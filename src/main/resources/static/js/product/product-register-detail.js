//function toggleProductStatus(button) {
////    console.log(button,"button");
//    var productId = $(button).data('product-id');
//
////     Send Ajax request to update product status
//    console.log("id", productId);
//    $.ajax({
//        type: 'GET',
//        url: '/product/register/' + productId,
//        data: {productId: productId}
//        ,success: function (result) {
//            console.log("result",result);
//            // Update button text and class based on the new status
//        },
//        error: function () {
//            console.log('Error updating product status');
//        }
//    });
//
//$(document).ready(function() {
//    $('#summernote').summernote({
//        height: 700,
//        focus: true,
//        lang: "ko-KR",
//        placeholder: '상세정보를 입력하세요.',
//        disableResizeEditor: true,
//        toolbar: [
//                    ['fontname', ['fontname']],
//                    ['fontsize', ['fontsize']],
//                    ['style', ['bold', 'italic', 'underline','strikethrough', 'clear']],
//                    ['color', ['forecolor','color']],
//                    ['table', ['table']],
//                    ['para', ['ul', 'ol', 'paragraph']],
//                    ['height', ['height']],
//                    ['insert',['picture','link','video']],
//                    ['view', ['fullscreen', 'help']]
//                ],
//        fontNames: ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New','맑은 고딕','궁서','굴림체','굴림','돋움체','바탕체'],
//        fontSizes: ['8','9','10','11','12','14','16','18','20','22','24','28','30','36','50','72']
////        callbacks: {	//여기 부분이 이미지를 첨부하는 부분
////            onImageUpload : function(files) {
////                uploadSummernoteImageFile(files[0],this);
////            },
////            onPaste: function (e) {
////                var clipboardData = e.originalEvent.clipboardData;
////                if (clipboardData && clipboardData.items && clipboardData.items.length) {
////                    var item = clipboardData.items[0];
////                    if (item.kind === 'file' && item.type.indexOf('image/') !== -1) {
////                        e.preventDefault();
////                    }
////                }
////            }
////        }
//    });
//});
//
//
///**
//* 이미지 파일 업로드
//*/
////function uploadSummernoteImageFile(file, editor) {
////    data = new FormData();
////    data.append("file", file);
////    $.ajax({
////        data : data,
////      dataType: 'json',
////        type : "POST",
////        url : "/uploadSummernoteImageFile",
////        contentType : false,
////        processData : false,
////        success : function(data) {
////            //항상 업로드된 파일의 url이 있어야 한다.
////            $(editor).summernote('insertImage', data.url);
////        }
////    });
////}
////
////
////
////
////
///**
//* 이미지 파일 업로드
//*/
//function uploadSummernoteImageFile(file, editor) {
//    data = new FormData();
//    data.append("file", file);
//    $.ajax({
//        data : data,
//        type : "POST",
//        url : "/uploadSummernoteImageFile",
//        contentType : false,
//        processData : false,
//        success : function(data) {
//            //항상 업로드된 파일의 url이 있어야 한다.
//            $(editor).summernote('insertImage', data.url);
//        }
//    });
//}
//
////onImageUpload: function(files) {
////    var formData = new FormData();
////    formData.append('file', files[0]);
////
////    // AJAX를 사용하여 이미지를 서버에 업로드
////    $.ajax({
////        url: '/product/register/detail/'+product,
////        type: 'POST',
////        data: formData,
////        processData: false,
////        contentType: false,
////        success: function(response) {
////            if (response.success) {
////                // 이미지 업로드 성공 시, 에디터에 이미지 삽입
////                var imgNode = document.createElement('img');
////                imgNode.src = response.imageUrl;
////                $summernote.summernote('insertNode', imgNode);
////            } else {
////                console.error('Failed to upload image.');
////            }
////        },
////        error: function(error) {
////            console.error('Error uploading image:', error);
////        }
////    });