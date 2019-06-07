/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
    function getEntity(id, option) {
        var idl = 'id=' + id;
        if (option.entity) {
            idl = option.entity + "=" + id;
        }
        $.ajax({
            type: "POST",
            url: 'getconnexionClient?getEntityJson=model&q=jgddgg&entity=' + option.model + '&' + idl,
            data: {},
            success: function (data) {
                $(option.id).html("");
                if (option.class) {
                    $(option.id).attr("class", "select2-size-xs block form-control round " + option.class);
                } else {
                    $(option.id).attr("class", "select2-size-xs block form-control round");
                }

                $(option.id).attr("name", option.name);

                if (option.multiple || $(option.id).attr("mul") !== undefined) {
                    $(option.id).attr("multiple", "multiple");
                }
                if (data.length > 0) {
                    if (!option.multiple) {
                        $(option.id).append($("<option value=''></option>"));
                    }
                    for (var i = 0; i < data.length; i++) {
                        if (data[i].name) {
                            $(option.id).append($("<option value='" + data[i].id + "'>" + data[i].name + "</option>"));
                        }


                    }
                }

                $('.select2-size-xs').select2({
                    containerCssClass: 'select-xs form-control'
                });
            },
            dataType: 'json',
            processData: false,
            contentType: false,
            cache: false
        });
    }

  
    $(".region_by_societe_client").change(function (e) {
        e.preventDefault();
        var option = {model: "getregion", id: "#societe_region", name: "region", class: "district_by_region_client", entity: "id", multiple: false};
        getEntity($(this).val(), option);
    });

  

    $(".district_by_region_client").change(function (e) {
        e.preventDefault();
        var option = {model: "getDistricts", id: "#region_district", name: "district", class: "region_district_client", entity: "id", multiple: false};
        getEntity($(this).val(), option);
    });
    $(".region_district_client").change(function (e) {
        e.preventDefault();
        var option = {model: "getSecteurs", id: "#district_secteur", name: "secteur", class: "district_secteur_client", entity: "id", multiple: false};
        getEntity($(this).val(), option);
    });

   
    $(".typeclient_by_societe_client").change(function (e) {
        e.preventDefault();
        var option = {model: "getTypeClient", id: "#type", name: "typeclient", class: "societe_typeclient_client", entity: "id", multiple: false};
        getEntity($(this).val(), option);
    });

   $('#code').blur(function () {
   var code = $("#code");
                $.ajax({
                    type: "POST",
                    url: 'getconnexionClient?getEntityJson=1254447&entity=verificodeclient&code=' + code.val(),
                    data: {},
                    success: function (data) {
                        if (!data[0]) {
                            toastr.error("Code Client non disponible", "Error", {"closeButton": true, "showMethod": "slideDown", "hideMethod": "slideUp"});
                            code.val("");

                        }else{
                            
                        }
                    },
                    dataType: 'json',
                    processData: false,
                    contentType: false,
                    cache: false
                });
});
});

