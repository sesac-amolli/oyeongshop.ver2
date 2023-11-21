$(document).ready(function() {
    $('#summernote').summernote({
        height: 300,
        focus: true,
        lang: "ko-KR",
        placeholder: '상세정보를 입력하세요.',
        disableResizeEditor: true,
        toolbar: [
                    ['fontname', ['fontname']],
                    ['fontsize', ['fontsize']],
                    ['style', ['bold', 'italic', 'underline','strikethrough', 'clear']],
                    ['color', ['forecolor','color']],
                    ['para', ['paragraph']],
                    ['height', ['height']],
                ],
        fontNames: ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New','맑은 고딕','궁서','굴림체','굴림','돋움체','바탕체'],
        fontSizes: ['8','9','10','11','12','14','16','18','20','22','24','28','30','36','50','72']
    });
});