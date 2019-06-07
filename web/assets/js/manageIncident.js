/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {

    var responsable = $();



    $('input[name=typeincident]').click(function () {

        if ($(this).val() === "0") {
            responsable.insertAfter($('#objet').parent('div'));
        } else {
            responsable = $('#responsable').parent('div:first').clone();
            console.log(responsable);
            $('#responsable').parent('div').remove();



        }

      
    });


  setTimeout(function () {
            $('.select2-size-xs').select2({
                containerCssClass: 'select-xs form-control'
            });
        }, 1000);
});