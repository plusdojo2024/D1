
function onclick_reaction(date) {
alert("osaremasita");
	const data = null;
	data.append("date", date);
	fetch('http://localhost:8080/D1/StudentQueSubResult_ReactionServlet', {
		method: 'post', body: data
	})
		.then(res => res.text())
		.then(text => {

			$('#inside').html(text);

		});
}