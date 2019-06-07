/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function scrollFunction() {
    
    if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
        document.getElementById("myBtnTop").style.display = "block";
    } else {
        document.getElementById("myBtnTop").style.display = "none";
    }
}

// When the user clicks on the button, scroll to the top of the document
function topFunction() {
    document.body.scrollTop = 0;
    document.documentElement.scrollTop = 0;
}

$(document).ready(function(){
    $("body").append('<i onclick="topFunction()" id="myBtnTop" title="Haut de page" class=" fa fa-arrow-circle-up"></i>');
  // When the user scrolls down 20px from the top of the document, show the button
window.onscroll = function () {
    scrollFunction();
};
});
  




