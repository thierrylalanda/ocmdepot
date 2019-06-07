/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {

    $(".gamme_licence").change(function () {
        var $input = $(' <div class="form-group col-md-6 row_jour">' +
                '<label for="nbr">' +
                'Nbre Jours' +
                '<span class="danger">*</span>' +
                '</label>' +
                '<input type="number" value="" required class="form-control-sm form-control round" placeholder=""' +
                ' name="njs"' +
                '  />' +
                '</div>');
        if ($(this).find("option:selected").hasClass("mode-1")) {
            $input.insertAfter($(this).parent("div"));
        } else {
            $(".row_jour").remove();
        }

    });
});

