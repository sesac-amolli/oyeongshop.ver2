function addRow() {
    // 새로운 행 생성
    var table = document.getElementById("optionTable1");
    var newRow = table.insertRow(table.rows.length);

    // 추가한 행에 대한 index 부여
    currentIndex++;
    newRow.setAttribute("data-index", currentIndex);

    // 각 셀에 새로운 입력란 추가
    for (var i = 0; i < 3; i++) {
        var cell = newRow.insertCell(i);
        var input = document.createElement("input");
        input.type = "text";
        input.setAttribute("th:field", "*{prodOpts[" + currentIndex + "].prodOptColor}");
        // 다른 속성을 필요에 따라 추가할 수 있습니다.

        cell.appendChild(input);
    }
}

function removeRow() {
    var table = document.getElementById("optionTable1");

    // 최소 한 행은 유지하기
    if (table.rows.length > 2) {
        var lastRow = table.rows[table.rows.length - 1];
        var lastIndex = lastRow.getAttribute("data-index");

        table.deleteRow(table.rows.length - 1);
        currentIndex--;
    } else {
        alert("행이 최소 1개 이상이어야 합니다.");
    }
}

