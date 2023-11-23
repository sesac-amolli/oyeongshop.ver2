        $(document).ready(function () {
            // 달력 초기화
            $("#history_start_date, #history_end_date").datepicker({
                dateFormat: 'yy-mm-dd',
                changeMonth: true,
                changeYear: true
            });

            // 오늘 날짜 구하기
            var today = new Date();
            var dd = today.getDate();
            var mm = today.getMonth() + 1; // 0부터 시작하므로 1을 더함
            var yyyy = today.getFullYear();

            if (dd < 10) {
                dd = '0' + dd;
            }

            if (mm < 10) {
                mm = '0' + mm;
            }

            // 3개월 전 날짜 구하기
            var threeMonthsAgo = new Date();
            threeMonthsAgo.setMonth(threeMonthsAgo.getMonth() - 3);
            var ddThreeMonthsAgo = threeMonthsAgo.getDate();
            var mmThreeMonthsAgo = threeMonthsAgo.getMonth() + 1;
            var yyyyThreeMonthsAgo = threeMonthsAgo.getFullYear();

            if (ddThreeMonthsAgo < 10) {
                ddThreeMonthsAgo = '0' + ddThreeMonthsAgo;
            }

            if (mmThreeMonthsAgo < 10) {
                mmThreeMonthsAgo = '0' + mmThreeMonthsAgo;
            }

            // 달력에 오늘 날짜와 3개월 전 날짜 셋팅
            $("#history_start_date").val(yyyyThreeMonthsAgo + '-' + mmThreeMonthsAgo + '-' + ddThreeMonthsAgo);
            $("#history_end_date").val(yyyy + '-' + mm + '-' + dd);
        });