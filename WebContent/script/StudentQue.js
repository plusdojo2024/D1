function onclick_que() {
	fetch('http://localhost:8080/D1/StudentQueSubResultServlet', {
		method: 'post'
	})

		.then(res => res.text())
		.then(text => {
		$('#que_result').html(text);
		});
}