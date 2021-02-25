$(function() {
  $('#tab1').show();
  $('.tabs a').click(function(e) {
    e.preventDefault();
    $('.tabs a').removeClass('on');
    $(this).addClass('on');

    var selected = $(this).attr('href');
    $('.pannel').hide();
    $('#' + selected).show();
  });

  
  // setTimeout(function() {
  //   $('.main-visual-txt-1').addClass('on');
  // }, 1000);

  // setTimeout(function() {
  //     $('.main-visual-txt-2').addClass('on');
  // }, 2200);

  // setTimeout(function() {
  //   $('.main-visual-txt-3').addClass('on');
  // }, 3400);

  document.querySelector( "form" )
  .addEventListener( "invalid", function( event ) {
      event.preventDefault();
  }, true );




});


//# sourceMappingURL=main.js.map
