/**
 *
 */
'use strict';

var ctx = document.getElementById("myLineChart");
var myLineChart = new Chart(ctx, {
  type: 'line',
   data: {
	 labels: ['中間テスト', '期末テスト', '中間テスト', '期末テスト', '学年末テスト'],
	 datasets: [
     {
	   label: '国語',
	   data: [35, 50, 60, 40, 100],
	   borderColor: "rgba(255,0,0,1)",
	   backgroundColor: "rgba(0,0,0,0)"
	 },
	 {
	   label: '数学',
	   data: [25, 27, 27, 25, 26],
	   borderColor: "rgba(0,0,255,1)",
	   backgroundColor: "rgba(0,0,0,0)"
	  },
	 {
	   label: '英語',
	   data: [60, 96, 84, 25, 26],
	   borderColor: "rgba(0,128,0,1)",
	   backgroundColor: "rgba(0,0,0,0)"
	 },
	 {
	   label: '理科',
	   data: [74, 20, 10, 40, 44],
	   borderColor: "rgba(255,255,0,1)",
	   backgroundColor: "rgba(0,0,0,0)"
	 },
	 {
	   label: '社会',
	   data: [70, 50, 60, 55, 92],
	   borderColor: "rgba(255,192,203,1)",
	   backgroundColor: "rgba(0,0,0,0)"
	 },
	   ],
    },
    options: {
      responsive: false,

      title: {
	display: true,
	text: '得点（5教科）'
      },
      scales: {
	yAxes: [{
	  ticks: {
	    suggestedMax: 100,
	    suggestedMin: 0,
	    stepSize: 10,
	    callback: function(value, index, values){
	      return  value +  '点'
	    }
	  }
	}]
      },
    }
  });