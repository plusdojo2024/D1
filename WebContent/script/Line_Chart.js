/**
 *
 */
'use strict';

var ctx = document.getElementById("myLineChart");
var myLineChart = new Chart(ctx, {
  type: 'line',
   data: {
	 labels: ['4月','5月','6月','7月','9月','10月','11月','12月','1月','2月','3月'],
	 datasets: [
     {
	   label: '国語',
	   data: array1,
	   borderColor: "rgba(255,0,0,1)",
	   backgroundColor: "rgba(0,0,0,0)"
	 },
	 {
	   label: '数学',
	   data: array2,
	   borderColor: "rgba(0,0,255,1)",
	   backgroundColor: "rgba(0,0,0,0)"
	  },
	 {
	   label: '英語',
	   data: array3,
	   borderColor: "rgba(0,128,0,1)",
	   backgroundColor: "rgba(0,0,0,0)"
	 },
	 {
	   label: '理科',
	   data: array4,
	   borderColor: "rgba(255,255,0,1)",
	   backgroundColor: "rgba(0,0,0,0)"
	 },
	 {
	   label: '社会',
	   data: array5,
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