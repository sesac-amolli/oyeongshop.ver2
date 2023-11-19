function daumPostcode() {
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
                        document.getElementById("address2").value = extraAddr;

                    } else {
                        document.getElementById("address2").value = '';
                    }

                    // 우편번호와 주소 정보를 해당 필드에 넣는다.
                    document.getElementById('postcode').value = data.zonecode;
                    document.getElementById("address1").value = addr;
                    // 커서를 상세주소 필드로 이동한다.
                    document.getElementById("userAddrDetail")
                            .focus();
                }
            }).open();
}


function cancel(){
    let msg = "회원가입을 중단하시겠습니까? 확인 시 메인페이지로 돌아갑니다.";
    let result;

    result = confirm(msg);

    if(result==true){
        location.replace("/");
    }
}

function validate() {
  let passReg =  /^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/;
  let password = document.getElementById("signup-pwd").value;
  let confirmPassword = document.getElementById("signup-pwd-check").value;

  let telReg = /^\d{3}-\d{3,4}-\d{4}$/;
  let tel = document.getElementById("signup-phone").value;
  if(tel.match(telReg) == null){
    alert("전화번호 형식이 맞지 않습니다.");
    return false;
  }
  if(password.match(passReg) == null){
    alert("비밀번호는 최소 8자에서 15자까지, 영문자, 숫자 및 특수 문자를 포함해야 합니다.");
    return false;
  }
  if (password != confirmPassword) {
    alert("비밀번호가 일치하지 않습니다.");
    return false;
  }

  return true;
}