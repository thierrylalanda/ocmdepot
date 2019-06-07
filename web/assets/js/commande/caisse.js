/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
   
    $(".btn_caisse").click(function (e) {
        e.preventDefault();
        var form = $(this).parents("form:first");
        var montant = form.find("input[type=number]");

        $.confirm({
            draggable: true,
            closeIcon: true,
            closeIconClass: 'fa fa-close',
            icon: 'fa fa-warning',
            animation: 'zoom',
            closeAnimation: 'scale',
            theme: 'dark',
            type: 'red',
            title: 'Confirmation !!!',
            content: 'Vous êtes sur le point d\'effectuer une operation d\'un montant de \n <h3> ' + formatNumber(montant.val()) + '</h3>',
            buttons: {
                info: {
                    text: 'Confirmer',
                    btnClass: 'btn-success',
                    action: function () {
                        form.submit();
                    }
                },
                danger: {
                    text: 'Annuler',
                    btnClass: 'btn-red any-other-class' // multiple classes.

                }
            }
        });

    });
});