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
