(function($) {
  'use strict';
  $(function() {
    $('[data-toggle="offcanvas"]').on("click", function() {
      $('.sidebar-offcanvas').toggleClass('active')
    });
  });
})(jQuery);

//重新输入
function cleanInfo() {
  $(".modal-body input").not("#userId_edit").val("");
}
