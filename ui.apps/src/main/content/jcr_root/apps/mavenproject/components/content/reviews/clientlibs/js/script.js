document.addEventListener("DOMContentLoaded", (function () {
  $('.reviews-slider-wrapper').each(function () {
    if (checklength($(this).find(".reviews-grid"))) {
      $(this).find(".reviews-grid").css("min-height", "209px");
    }
    function checklength(e) {
      grids = e.find(".reviews-divider");
      if (grids.length > 0) {
        return true;
      } else {
        return false;
      }
    }
    let noOfCards = 3;
    if ($(this).parents().hasClass('column-8') || $(this).hasClass("reviews-slider-wrapper slick-slider")) {
      noOfCards = 2;
    }
    $(this).slick({
      dots: true,
      speed: 500,
      slidesToShow: noOfCards,
      slidesToScroll: 1,
      infinite: true,
      arrows: true,
      autoplay: true,
      autoplaySpeed: 2000,
      easing: 'swing',
      useTransform: false,
      prevArrow: "<button type='button' class='slick-prev arrow-left' aria-label='previous button'></button>",
      nextArrow: "<button type='button' class='slick-next arrow-right' aria-label='next button'></button>",
      customPaging: function (slider, i) {
        return (i + 1) + '/' + slider.slideCount;
      },
      responsive: [
        {
          breakpoint: 540,
          settings: {
            slidesToShow: 1,
            slidesToScroll: 1,
            dots: true,
            arrows: false,
          }
        },
      ]
    });
    $(".reviews-parent-div").find(".slick-dots").addClass("review-dots");
    $(".reviews-parent-div").find(".slick-dots li").addClass("review-lis");
  });
}))