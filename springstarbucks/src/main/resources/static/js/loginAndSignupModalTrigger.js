  $('.js-anchor1').on('click',function () {
    $('#modalForLogin').modal('show').find('.modal-login-model-content').load($(".js-anchor1").attr('href'));
  });
  $('.js-anchor2').on('click',function () {
    $('#exampleModalCenter1').modal('show').find('.modal-content').load($(".js-anchor2").attr('href'));
  });
  $(".modal").on("shown.bs.modal", function () {
      if ($(".modal-backdrop").length > 1) {
          $(".modal-backdrop").not(':first').remove();
      }
  })
