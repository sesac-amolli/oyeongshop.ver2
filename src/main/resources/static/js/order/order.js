function sample6_execDaumPostcode() {
    new daum.Postcode(
            {
                oncomplete : function(data) {
                    // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                    // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                    // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                    var addr = ''; // 주소 변수
                    var extraAddr = ''; // 참고항목 변수

                    //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                    if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                        addr = data.roadAddress;
                    } else { // 사용자가 지번 주소를 선택했을 경우(J)
                        addr = data.jibunAddress;
                    }

                    // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                    if (data.userSelectedType === 'R') {
                        // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                        // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                        if (data.bname !== ''
                                && /[동|로|가]$/g.test(data.bname)) {
                            extraAddr += data.bname;
                        }
                        // 건물명이 있고, 공동주택일 경우 추가한다.
                        if (data.buildingName !== ''
                                && data.apartment === 'Y') {
                            extraAddr += (extraAddr !== '' ? ', '
                                    + data.buildingName
                                    : data.buildingName);
                        }
                        // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                        if (extraAddr !== '') {
                            extraAddr = ' (' + extraAddr + ')';
                        }
                        // 조합된 참고항목을 해당 필드에 넣는다.
                        document.getElementById("sample6_extraAddress").value = extraAddr;

                    } else {
                        document.getElementById("sample6_extraAddress").value = '';
                    }

                    // 우편번호와 주소 정보를 해당 필드에 넣는다.
                    document.getElementById('sample6_postcode').value = data.zonecode;
                    document.getElementById("sample6_address").value = addr;
                    // 커서를 상세주소 필드로 이동한다.
                    document.getElementById("sample6_detailAddress")
                            .focus();
                }
            }).open();
}

// 라디오 버튼 요소들을 가져옵니다.
const radioButtons = document.querySelectorAll('input[name="addrType"]');
const receiveNameInput = document.getElementById('orderAttnName');

// 라디오 버튼의 변경 이벤트에 대한 리스너를 추가합니다.
radioButtons.forEach(radioButton => {
radioButton.addEventListener('change', function() {
// 선택된 라디오 버튼의 값을 가져옵니다.
const selectedValue = document.querySelector('input[name="addrType"]:checked').value;

console.log(selectedValue);
// 주소 상세 정보를 보여주거나 초기화합니다.
if (selectedValue === 'home') {
receiveNameInput.value = '김윤설';
} else if (selectedValue === 'office') {
receiveNameInput.value = '신은영바보';
//addressDetailsDiv.textContent = '사무실 주소: 회원의 사무실 주소 정보';
} else if (selectedValue === 'newPlace') {

receiveNameInput.value = '';
//addressDetailsDiv.textContent = ''; // 새로운 배송지를 선택하면 주소 정보를 초기화합니다.
}
});
});