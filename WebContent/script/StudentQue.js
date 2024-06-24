window.onload = function() {
	fetch('http://localhost:8080/D1/StudentQueSubResult_insideServlet', {
		method: 'post'
	})
		.then(res => res.text())
		.then(text => {

			$('#inside').html(text);

		});
}

$('.reload').on('click', function() {
	fetch('http://localhost:8080/D1/StudentQueSubResult_insideServlet', {
		method: 'post'
	})
		.then(res => res.text())
		.then(text => {

			$('#inside').html(text);

		});
});



window.setInterval(newload,1000);

function newload(){
	fetch('http://localhost:8080/D1/StudentQueTestServlet', {
		method: 'post'
	})

	.then((Count) => {

		 if(Count > 0){
		 	fetch('http://localhost:8080/D1/StudentQueSubResult_insideServlet', {
				method: 'post'
			})

			.then(res => res.text())
			.then(text => {

			$('#inside').html(text);

			});
		 }

	})
}