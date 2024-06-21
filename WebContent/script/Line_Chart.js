/**
 *
 */
'use strict';

var ctx = document.getElementById("myLineChart");
var myLineChart = new Chart(ctx, {
  type: 'line',
   data: {
	 labels: array2,
	 datasets: [
     {
	   label: '成績推移',
	   data: array1,
	   borderColor: "rgba(255,0,0,1)",
	   backgroundColor: "rgba(0,0,0,0)"
	 },

	 {
	   label: '国語',
	   data: array3,
	   borderColor: "rgba(0,0,255,1)",
	   backgroundColor: "rgba(0,0,0,0)"
	 }
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