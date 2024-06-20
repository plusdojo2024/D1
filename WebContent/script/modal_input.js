$(function () {
    $('#openModal').click(function(){
        $('#modalArea').fadeIn();
    });
    $('#closeModal , #modalBg,#modal_cancel').click(function(){
      $('#modalArea').fadeOut();
    });
  });

  $(function () {
    $('.chat-ret').click(function(){
        $('#modalArea_B').fadeIn();
    });
    $('#closeModal_B , #modalBg_B,#modal_cancel_B').click(function(){
      $('#modalArea_B').fadeOut();
    });
  });