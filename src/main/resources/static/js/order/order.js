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
                        document.getElementById("orderAttnAddr2").value = extraAddr;

                    } else {
                        document.getElementById("orderAttnAddr2").value = '';
                    }

                    // 우편번호와 주소 정보를 해당 필드에 넣는다.
                    document.getElementById('orderAttnPostcode').value = data.zonecode;
                    document.getElementById("orderAttnAddr1").value = addr;
                    // 커서를 상세주소 필드로 이동한다.
                    document.getElementById("orderAttnDetail")
                            .focus();
                }
            }).open();
}

// 라디오 버튼 요소들을 가져옵니다.
const radioButtons = document.querySelectorAll('input[name="addrType"]');
const orderAttnNameInput = document.getElementById('orderAttnName');
const orderAttnPhoneInput = document.getElementById('orderAttnPhone');
const orderAttnEmailInput = document.getElementById('orderAttnEmail');
const orderAttnPostcodeInput = document.getElementById('orderAttnPostcode');
const orderAttnAddr1Input = document.getElementById('orderAttnAddr1');
const orderAttnAddr2Input = document.getElementById('orderAttnAddr2');
const orderAttnDetailInput = document.getElementById('orderAttnDetail');

// 라디오 버튼의 변경 이벤트에 대한 리스너를 추가합니다.
radioButtons.forEach(radioButton => {
    radioButton.addEventListener('change', function() {
        // 선택된 라디오 버튼의 값을 가져옵니다.
        const selectedValue = document.querySelector('input[name="addrType"]:checked').value;

        // 선택된 라디오 버튼에 따라 원하는 작업을 수행합니다.
        if (selectedValue === 'home') {
            // 집을 선택한 경우에 수행할 작업
            // orderUserDto를 기반으로 값 설정
            orderAttnNameInput.value = orderUser.userAttnName;
            orderAttnPhoneInput.value = orderUser.userAttnPhone;
            orderAttnEmailInput.value = orderUser.userAttnEmail;
            orderAttnPostcodeInput.value = orderUser.userAttnPostcode;
            orderAttnAddr1Input.value = orderUser.userAttnAddr1;
            orderAttnAddr2Input.value = orderUser.userAttnAddr2;
            orderAttnDetailInput.value = orderUser.userAttnDetail;
        } else {
            // 다른 값을 선택한 경우에 수행할 작업
            orderAttnNameInput.value = '';
            orderAttnPhoneInput.value = '';
            orderAttnEmailInput.value = '';
            orderAttnPostcodeInput.value = '';
            orderAttnAddr1Input.value = '';
            orderAttnAddr2Input.value = '';
            orderAttnDetailInput.value = '';
        }
    });
});

function KGpay(){
    let orderFormObj = $("#orderForm").serializeArray();
    console.log(JSON.stringify(orderFormObj));

    var resultObject = {
        orderItems: []
    };

    // Iterate through the JSON data and group values by the index
    orderFormObj.forEach(function(item) {
        // Extract index from the name
        var index = item.name.match(/\[(\d+)\]/);
        index = index ? parseInt(index[1]) : null;

        // Extract property name from the name
        var propertyName = item.name.match(/\]\[(.+)\]/);
        propertyName = propertyName ? propertyName[1] : null;

        // Ensure index and property name are valid
        if (index !== null && propertyName !== null) {
            // Create an object for the index if it doesn't exist
            if (!resultObject.orderItems[index]) {
                resultObject.orderItems[index] = {};
            }

            // Set the property in the object
            resultObject.orderItems[index][propertyName] = item.value;
        }
    });
    console.log(resultObject)

    let orderItemDTOs = resultObject.orderItems;
    let orderDelivery = {
        orderAttnName:$('#orderAttnName').val(),
        orderAttnPhone:$('#orderAttnPhone').val(),
        orderAttnEmail:$('#orderAttnEmail').val(),
        orderAttnPostcode:$('#orderAttnPostcode').val(),
        orderAttnAddr1:$('#orderAttnAddr1').val(),
        orderAttnAddr2:$('#orderAttnAddr2').val(),
        orderAttnDetail:$('#orderAttnDetail').val(),
        orderAttnRequest:$('#orderAttnRequest').val()
    };
    let orderPriceDTO = {
        orderTotalOriginPrice:$('#orderTotalOriginPrice').val(),
        orderTotalPayment:$('#orderTotalPayment').val(),
        totalOrderPayment:$('#totalOrderPayment').val()
    }
    console.log(orderItemDTOs, orderDelivery)
    $.ajax({
        type:'post',
        url:"/order/create",
        contentType: 'application/json;charset=utf-8',
        traditional : true, //필수
        data: JSON.stringify({"orderItemDTOs": orderItemDTOs,
               "orderDeliveryDTO": orderDelivery,
               "orderPriceDTO": orderPriceDTO
        }),
        success: function(data){
            IMP.init("imp38164824")
            IMP.request_pay({
                pg : 'html5_inicis', //pg사
                pay_method : 'card', //결제 수단
                merchant_uid: data.orderNumber, // 상점에서 관리하는 주문 번호를 전달
                name : 'OyeongShop:상품 구매', //주문명 < 주문 상품명에 표시되는 내용
                amount : orderPriceDTO.totalOrderPayment, // 실제 결제되는 금액
                buyer_email : orderDelivery.orderAttnEmail, // 구매자 이메일: 결제자가 변경가능, 해당 이메일로 결제내역 발송됨
                buyer_name : orderDelivery.orderAttnName, // 구매자 이름
                buyer_tel : orderDelivery.orderAttnPhone, // 구매자 전화번호
                buyer_addr : orderDelivery.orderAttnAddr1, // 구매자 주소
                buyer_postcode : orderDelivery.orderAttnPostcode // 구매자 우편번호
            }, function(rsp) { // callback 로직
            	var result = '';
                if ( rsp.success ) {
                    var msg = '결제가 완료되었습니다.';
                    result='0'
                    let paymentDto = {
                        paymentImpUid:rsp.imp_uid, // 포트원 고유 결제 번호
                        paymentPg:rsp.pg_provider, // pg사
                        paymentApplyNum:rsp.apply_num, //신용카드 승인번호
                        paymentMethod:rsp.card_name,
                        paymentCardNum:rsp.card_number,
                        paymentDate:new Date(rsp.paid_at),
                        paymentTotalPrice:$('#totalOrderPayment').val(),
                        paymentCashPrice:rsp.paid_amount,
                        paymentPayerName:rsp.buyer_name,
                        paymentReceipt:rsp.receipt_url, //거래 매출전표 URL
                        orderId:data.orderId
                    }
                 	$.ajax({
                            type:'post',
                            url:"/order/payment",
                            contentType: 'application/json;charset=utf-8',
                            traditional : true, //필수
                            data: JSON.stringify(paymentDto),
                            success : function(response){
                            },
                            error : function(error) {
                            // ToDo 결제 취소, 주문 내역 삭제 + 에러 메시지
                            alert("결제내역 저장 실패"+paymentDto.paymentCardNum)
                            }
                    });
                } else {
                    var msg = '결제에 실패하였습니다.';
                 	msg += '에러내용 : ' + rsp.error_msg;
                 	result ='1';
                }
                if(result=='0') {
                    window.location.href = "/order/order-detail/"+data.orderId;
                }
                alert(msg);
            });
        },
        error : function(error){
            alert("서버 오류 발생, 관리자에게 문의해주세요");
        }
    });

}
