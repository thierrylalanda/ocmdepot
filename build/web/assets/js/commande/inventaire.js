/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    $(".btn_toggle_form_inventaire").click(function (ev) {
        ev.preventDefault();
        $(".form_inventaire").toggleClass("hidden");
        $(".form_commentaire_inventaire").toggleClass("hidden");
    });
});
