$(function () {
    $('#openModal').click(function(){
        $('#modalArea').fadeIn();
    });
    $('#closeModal , #modalBg,#modal_cancel').click(function(){
      $('#modalArea').fadeOut();
    });
    $('#modal_ok').click(function(){
      //入力値を取得
      var loginId = $('#email').val();
      var password = $('#password').val();
      var userName = $('input[name = "user_name"]').val();

      //空欄チェック
      if(loginId === '' || password === '' || userName === ''){
        alert('全ての項目を入力してください。');
        return false;  //登録阻止
      }

      $('#registform').submit();
  });
});