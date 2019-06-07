/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    // validation d'un formulaire en plusieurs etapes
    var form = $(".steps-validation").show();
    $(".steps-validation").steps({
        headerTag: "h6",
        bodyTag: "fieldset",
        transitionEffect: "fade",
        titleTemplate: '<span class="step">#index#</span> #title#',
        labels: {
            finish: 'Enregistrer'
        },
        onStepChanging: function (event, currentIndex, newIndex)
        {
            // Allways allow previous action even if the current form is not valid!
            if (currentIndex > newIndex)
            {
                return true;
            }
            // Forbid next action on "Warning" step if the user is to young
            if (newIndex === 3 && Number($("#age-2").val()) < 18)
            {
                return false;
            }
            // Needed in some cases if the user went back (clean up)
            if (currentIndex < newIndex)
            {
                // To remove error styles
                form.find(".body:eq(" + newIndex + ") label.error").remove();
                form.find(".body:eq(" + newIndex + ") .error").removeClass("error");
            }
            form.validate().settings.ignore = ":disabled,:hidden";
            return form.valid();
        },
        onFinishing: function (event, currentIndex)
        {
            form.validate().settings.ignore = ":disabled";
            return form.valid();
        },
        onFinished: function (event, currentIndex)
        {
            if ($("#username").attr("username") !== undefined) {
                var username = $("#username");
                $.ajax({
                    type: "POST",
                    url: 'administration?action=verifiusername&q=jgddgg&model=access&username=' + username.val(),
                    data: {},
                    success: function (data) {
                        console.log(data);
                        if (!data[0]) {
                            toastr.error("Nom utilisateur non disponible", "Error", {"progressBar": true, "closeButton": true, "showMethod": "slideDown", "hideMethod": "slideUp"});
                            username.val("");

                        } else {

                            form.submit();
                        }
                    },
                    dataType: 'json',
                    processData: false,
                    contentType: false,
                    cache: false
                });
            }else if ($("#code").attr("code") !== undefined) {
                var code = $("#code");
                $.ajax({
                    type: "POST",
                    url: 'administration?action=verificodeclient&q=jgddgg&model=access&code=' + code.val(),
                    data: {},
                    success: function (data) {
                        console.log(data);
                        if (!data[0]) {
                            toastr.error("Code Client non disponible", "Error", {"progressBar": true, "closeButton": true, "showMethod": "slideDown", "hideMethod": "slideUp"});
                            username.val("");

                        } else {

                            form.submit();
                        }
                    },
                    dataType: 'json',
                    processData: false,
                    contentType: false,
                    cache: false
                });
            }else{
                form.submit();
            }

        }
    });

// Initialize validation
    $(".steps-validation").validate({
        ignore: 'input[type=hidden]', // ignore hidden fields
        errorClass: 'danger',
        successClass: 'success',
        highlight: function (element, errorClass) {
            $(element).removeClass(errorClass);
        },
        unhighlight: function (element, errorClass) {
            $(element).removeClass(errorClass);
        },
        errorPlacement: function (error, element) {
            error.insertAfter(element);
        },
        rules: {
            email: {
                email: true
            }
        }
    });



});