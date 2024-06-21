$(function () {
    $('#openModal').click(function(){
        $('#modalArea').fadeIn();
    });
    $('#closeModal , #modalBg,#modal_cancel').click(function(){
      $('#modalArea').fadeOut();
    });
    $('#modal_ok').click(function(){
      $('#registform').submit();
  });
});