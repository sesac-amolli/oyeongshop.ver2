let currentIndex=0;
let nameData=["prodOptColor","prodOptSize" ,"prodOptAmount"];

function addRow() {
    // 새로운 행 생성
    var table = document.getElementById("optionTable");
    var newRow = table.insertRow(table.rows.length);

   // Create cells
               var cell1 = newRow.insertCell(0);
               var cell2 = newRow.insertCell(1);
               var cell3 = newRow.insertCell(2);
               var cell4 = newRow.insertCell(3);

               // Cell 1 - Input for prodOptColor
               var colorInput = document.createElement("input");
               colorInput.type = "text";
               colorInput.classList.add("opt_regis_input");
               colorInput.setAttribute("name", "prodOptColor");
               cell1.appendChild(colorInput);

               // Cell 2 - Select for prodOptSize
               var sizeSelect = document.createElement("select");
               sizeSelect.classList.add("opt_select");
               sizeSelect.setAttribute("name", "prodOptSize");

               var sizeOptions = ["Free", "xs", "s", "m", "L"];
               for (var i = 0; i < sizeOptions.length; i++) {
                   var option = document.createElement("option");
                   option.value = i * 5; // Assuming values are incremented by 5
                   option.text = sizeOptions[i];
                   sizeSelect.appendChild(option);
               }

               cell2.appendChild(sizeSelect);

               // Cell 3 - Input for prodOptAmount
               var amountInput = document.createElement("input");
               amountInput.type = "number";
               amountInput.classList.add("opt_regis_input");
               amountInput.setAttribute("name", "prodOptAmount");
               cell3.appendChild(amountInput);

               // Cell 4 - Empty cell
               // You can add content or other elements here if needed

               // Reset the values in the newly added row
               colorInput.value = "";
               sizeSelect.value = "0"; // Assuming "Free" is the default option
               amountInput.value = "";

               // You may want to perform additional tasks here, such as updating the Thymeleaf field names.

           }


function removeRow() {
    var table = document.getElementById("optionTable");

    // 최소 한 행은 유지하기
    if (table.rows.length > 3) {
        var lastRow = table.rows[table.rows.length - 1];
        var lastIndex = lastRow.getAttribute("data-index");

        table.deleteRow(table.rows.length - 1);
        currentIndex--;
    } else {
        alert("행이 최소 1개 이상이어야 합니다.");
    }
}
