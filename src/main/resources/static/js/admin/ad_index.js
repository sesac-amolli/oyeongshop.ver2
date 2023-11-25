new Chart(document.getElementById("bar-chart"), {
    type: 'bar',
    data: {
      labels: ['결제 완료', '출고 준비 중', '출고 완료', '신규 문의'],
      datasets: [
        {
          label: "주문 현황 (건)",
          backgroundColor: ["#3e95cd", "#8e5ea2","#3cba9f","#e8c3b9","#c45850"],
          data: [2478,5267,734,784,433]
        }
      ]
    },
    options: {
      legend: { display: false },
      title: {
        display: false
      }
    }
});

new Chart(document.getElementById("pie-chart"), {
    type: 'pie',
    data: {
      labels: ['결제 완료', '출고 준비 중', '출고 완료', '신규 문의'],
      datasets: [
        {
          label: "주문 현황 (건)",
          backgroundColor: ["#3e95cd", "#8e5ea2","#3cba9f","#e8c3b9","#c45850"],
          data: [2478,5267,734,784,433]
        }
      ]
    },
    options: {
      legend: { display: false },
      title: {
        display: false
      }
    }
});