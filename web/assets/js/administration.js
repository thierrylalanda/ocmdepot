/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
    $(".admin-add-societe").click(function (e) {
        e.preventDefault();
        var matricule = $(".admin-add-societe-form").find("#matricule").val();

        $.ajax({
            type: "POST",
            url: 'administration?action=verifiImmatriculation&q=jgddgg&model=access&imma=' + matricule,
            data: {},
            success: function (data) {
                if (data[0]) {
                    $(".admin-add-societe-form").submit();
                } else {
                    toastr.error("N° contribuable non disponible", "Error", {"progressBar": true, "closeButton": true, "showMethod": "slideDown", "hideMethod": "slideUp"});
                }
            },
            dataType: 'json',
            processData: false,
            contentType: false,
            cache: false
        });

    });
    
   
   
    
});