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
            url: 'ticket?action=model&q=jgddgg&model=' + option.model + '&' + idl,
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
    $(".region_zone").hide();
    $(".region_zone_societe").hide();

    var villes = '<div class="form-group">' +
            '<label for="etat">' +
            'Villes :' +
            '<span class="danger">*</span>' +
            '</label>' +
            '<select style="width: 100%" class="  form-control-sm round  form-control custom-select region_district secteur_by_district" required id="region_district" name="district">' +
            '</select>' +
            '</div>';
    var secteurs = '<div class="form-group">' +
            '<label for="district_secteur">' +
            'Secteurs :' +
            '<span class="danger">*</span>' +
            '</label>' +
            '<select style="width: 100%" class="  form-control-sm round  form-control custom-select district_secteur" required id="district_secteur" name="district">' +
            '</select>' +
            '</div>';


    $("#zone").click(function (e) {
        e.preventDefault();
        var zone = $(this).val();

        $(".region_zone").hide();
        $(".region_zone_societe").hide();
        $(".secteurs").html('');
        $("#district_secteur").removeAttr("multiple");
        $("#district_secteur").removeAttr("mul");
        $("#region_district").removeAttr("multiple");
        $("#region_district").removeAttr("mul");
        $("#region_district").removeAttr("disabled");
        if (zone === "region") {
            $(".region_zone_societe").show();
            $("#region_district").attr("disabled", "");
        } else if (zone === "district") {
            $("#region_district").attr("mul", "");
            $(".region_zone").show();
            $("#region_district").html("");
            $(".district_by_region option:eq(0)").prop('selected', true);
        } else if (zone === "secteur") {

            $(".region_zone").show();
            $(".district_by_region option:eq(0)").prop('selected', true);
            $(".secteurs").html(secteurs);
            $("#region_district").html("");
            $("#district_secteur").attr("mul", "");
        }

    });

    // action a effectuer lors du traitement d'un incident
    $("#statut").change(function (e) {
        e.preventDefault();
        var val = $(this).val();
        $(".responsabme_fermeture").html('');

        if (val === "502") {


            $.ajax({
                type: "POST",
                url: 'administration?action=getusersfermePlainte&q=jgddgg',
                data: {},
                success: function (data) {
                    var option = "";
                    if (data.length > 0) {
                        for (var i = 0; i < data.length; i++) {
                            $(option.id).append($("<option value='" + data[i].id + "'>" + data[i].nom + "</option>"));

                            option += "<option value='" + data[i].id + "'>" + data[i].nom + "</option>";

                        }
                    }
                    var responsable =
                            '<label for="district_secteur">' +
                            'Responsable :' +
                            '<span class="danger">*</span>' +
                            '</label>' +
                            '<select style="width: 100%" class="  form-control-sm round  form-control custom-select user_fer" required id="user_fer" name="user">' +
                            option +
                            '</select>';
                    
                    $(".responsabme_fermeture").html(responsable);
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


    });

    $("#region_reporting_secteur").change(function (e) {
        e.preventDefault();
        var option = {model: "getDistricts", id: "#district_reporting_secteur", name: "", multiple: false};
        getEntity($(this).val(), option);
    });
    $("#region_reporting_personnel").change(function (e) {
        e.preventDefault();
        var option = {model: "getAllUsersByRegion", id: "#personnel_reporting", name: "entity", multiple: true};
        getEntity($(this).val(), option);
    });
    $("#region_reporting_client").change(function (e) {
        e.preventDefault();
        var option = {model: "getAllClientsByRegion", id: "#client_reporting", name: "entity", multiple: true};
        getEntity($(this).val(), option);
    });
    $("#region_reporting_district").change(function (e) {
        e.preventDefault();
        var option = {model: "getDistricts", id: "#district_reporting", name: "entity", multiple: true};
        getEntity($(this).val(), option);
    });
    $("#region_reporting_secteur").change(function (e) {
        e.preventDefault();
        var option = {model: "getDistricts", id: "#district_reporting_secteur", name: "", multiple: false};
        getEntity($(this).val(), option);
    });

    $("#district_reporting_secteur").change(function (e) {
        e.preventDefault();
        var option = {model: "getSecteurs", id: "#secteur_reporting", name: "entity", multiple: true};
        getEntity($(this).val(), option);
    });

    $("#categorie_article").change(function (e) {
        e.preventDefault();
        var option = {model: "getArticlesByCategorie", id: "#articles_categorie", name: "article", multiple: false};
        getEntity($(this).val(), option);
    });

    //parametrage du super admin site_by_region
    $(".region_by_societe").change(function (e) {
        e.preventDefault();
        var option = {model: "getRegionsBySociete", id: "#societe_region", name: "region", class: "site_by_region user_by_region", entity: "societe", multiple: false};
        getEntity($(this).val(), option);
    });
    $(".region_by_societe_to_district").change(function (e) {
        e.preventDefault();
        var option = {model: "getRegionsBySociete", id: "#societe_region", name: "region", class: "district_by_region", entity: "societe", multiple: false};
        getEntity($(this).val(), option);
    });

    $(".site_by_region").change(function (e) {
        e.preventDefault();
        var option = {model: "getSitesByRegion", id: "#region_site", name: "site", class: "region_site service_by_site", entity: "region", multiple: false};
        getEntity($(this).val(), option);
    });

    $(".user_by_service").change(function (e) {
        e.preventDefault();
        var option = {model: "getUserByService", id: "#xsmall-multiple", name: "users", class: "service_user", entity: "service", multiple: true};
        getEntity($(this).val(), option);
    });

    $(".district_by_region").change(function (e) {
        e.preventDefault();
        var option = {model: "getDistrictByRegion", id: "#region_district", name: "district", class: "region_district secteur_by_district", entity: "region", multiple: false};
        getEntity($(this).val(), option);
    });
    $(".secteur_by_district").change(function (e) {
        e.preventDefault();
        var option = {model: "getSecteurByDistrict", id: "#district_secteur", name: "secteur", class: "district_secteur", entity: "district", multiple: false};
        getEntity($(this).val(), option);
    });

    $(".categorie_by_societe").change(function (e) {
        e.preventDefault();
        var option = {model: "getCategorieBySociete", id: "#categorie_societe", name: "cat", class: "categorie_societe", entity: "societe", multiple: false};
        getEntity($(this).val(), option);
    });



    $(".typeclient_by_societe").change(function (e) {
        e.preventDefault();
        var option = {model: "getTypeclientsBySociete", id: "#type", name: "typeclient", class: "societe_typeclient", entity: "societe", multiple: false};
        getEntity($(this).val(), option);
    });

    $(".rubrique_by_societe").change(function (e) {
        e.preventDefault();
        var option = {model: "getRubriquesBySociete", id: "#societe_rubrique", name: "rubrique", class: "societe_rubrique", entity: "societe", multiple: false};
        getEntity($(this).val(), option);
    });

    $(".service_by_site").change(function (e) {
        e.preventDefault();
        var option = {model: "getServicesBySite", id: "#site_service", name: "service", class: "site_service user_by_service", entity: "site", multiple: false};
        getEntity($(this).val(), option);
    });

    $(".groupe_by_societe").change(function (e) {
        e.preventDefault();
        var option = {model: "getGroupsBySociete", id: "#groupe", name: "group", class: "societe_groupe", entity: "societe", multiple: false};
        getEntity($(this).val(), option);
    });

    $(".district_by_region_multiple").change(function (e) {
        e.preventDefault();
        var option = {model: "getDistrictByRegion", id: "#region_district", name: "district", class: "region_district secteur_by_district", entity: "region", multiple: true};
        getEntity($(this).val(), option);
    });
    $(".secteur_by_district_multiple").change(function (e) {
        e.preventDefault();
        var option = {model: "getSecteurByDistrict", id: "#district_secteur", name: "secteur", class: "district_secteur", entity: "district", multiple: true};
        getEntity($(this).val(), option);
    });



});

