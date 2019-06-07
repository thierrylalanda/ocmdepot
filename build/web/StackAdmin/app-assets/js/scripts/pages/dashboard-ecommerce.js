/*=========================================================================================
    File Name: advance-cards.js
    Description: intialize advance cards
    ----------------------------------------------------------------------------------------
    Item Name: Stack - Responsive Admin Theme
    Version: 3.0
    Author: PIXINVENT
    Author URL: http://www.themeforest.net/user/pixinvent
==========================================================================================*/
    // Area chart
// ------------------------------
$(window).on("load", function(){
    $('#recent-buyers').perfectScrollbar({
        wheelPropagation: true
    });
    /********************************************
    *               PRODUCTS SALES              *
    ********************************************/
    
    var months = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];
   
    /********************************************
    *               Monthly Sales               *
    ********************************************/
    Morris.Bar.prototype.fillForSeries = function(i) {
      var color;
      return "0-#fff-#f00:20-#000";
    };

    Morris.Bar({
        element: 'monthly-sales',
        data: [{month: 'Jan', sales: 1835 }, {month: 'Feb', sales: 2356 }, {month: 'Mar', sales: 1459 }, {month: 'Apr', sales: 1289 }, {month: 'May', sales: 1647 }, {month: 'Jun', sales: 2156 }, {month: 'Jul', sales: 1835 }, {month: 'Aug', sales: 2356 }, {month: 'Sep', sales: 1459 }, {month: 'Oct', sales: 1289 }, {month: 'Nov', sales: 1647 }, {month: 'Dec', sales: 2156 }],
        xkey: 'month',
        ykeys: ['sales'],
        labels: ['Sales'],
        barGap: 4,
        barSizeRatio: 0.3,
        gridTextColor: '#bfbfbf',
        gridLineColor: '#E4E7ED',
        numLines: 5,
        gridtextSize: 14,
        resize: true,
        barColors: ['#00B5B8'],
        hideHover: 'auto',
    });
    
    /************************************************************
    *               Social Cards Content Slider                 *
    ************************************************************/
    // RTL Support
    var rtl = false;
    if($('html').data('textdirection') == 'rtl'){
        rtl = true;
    }
    if(rtl === true)
        $(".tweet-slider").attr('dir', 'rtl');
    if(rtl === true)
        $(".fb-post-slider").attr('dir', 'rtl');

    // Tweet Slider
    $(".tweet-slider").unslider({
        autoplay: true,
        delay:3500,
        arrows: false,
        nav: false,
        infinite: true
    });

    // FB Post Slider
    $(".fb-post-slider").unslider({
        autoplay: true,
        delay:4500,
        arrows: false,
        nav: false,
        infinite: true
    });
});