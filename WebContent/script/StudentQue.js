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


function onclick_subject() {

	const data = new FormData(document.getElementById('que_sub_select'));

	fetch('http://localhost:8080/D1/StudentQueSubResult_insideServlet', {
		method: 'post', body: data
	})
		.then(res => res.text())
		.then(text => {
			$('#inside').html(text);

		});
}


/*let select = document.querySelector('[name="subject_se"]')
select.onchange = event => {
	const data = select.value;

	fetch('http://localhost:8080/D1/StudentQueSubResult_insideServlet', {
		method: 'post', body: data
	})
		.then(res => res.text())
		.then(text => {
			$('#inside').html(text);

		});
}*/







//ドロップダウンの設定を関数でまとめる
function mediaQueriesWin(){
  var width = $(window).width();
  if(width <= 768) {//横幅が768px以下の場合
    $(".has-child>a").off('click'); //has-childクラスがついたaタグのonイベントを複数登録を避ける為offにして一旦初期状態へ
    $(".has-child>a").on('click', function() {//has-childクラスがついたaタグをクリックしたら
      var parentElem =  $(this).parent();// aタグから見た親要素の<li>を取得し
      $(parentElem).toggleClass('active');//矢印方向を変えるためのクラス名を付与して
      $(parentElem).children('ul').stop().slideToggle(500);//liの子要素のスライドを開閉させる※数字が大きくなるほどゆっくり開く
      return false;//リンクの無効化
    });
  }else{//横幅が768px以上の場合
    $(".has-child>a").off('click');//has-childクラスがついたaタグのonイベントをoff(無効)にし
    $(".has-child").removeClass('active');//activeクラスを削除
    $('.has-child').children('ul').css("display","");//スライドトグルで動作したdisplayも無効化にする
  }
}

// ページがリサイズされたら動かしたい場合の記述
$(window).resize(function() {
  mediaQueriesWin();/* ドロップダウンの関数を呼ぶ*/
});

// ページが読み込まれたらすぐに動かしたい場合の記述
$(window).on('load',function(){
  mediaQueriesWin();/* ドロップダウンの関数を呼ぶ*/
});