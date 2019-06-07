/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
    localStorage.removeItem("all_entity");
    var week = ["Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi", "Dimanche"];
    var Month = ["Janvier", "Fevrier", "Mars", "Avril", "Mai", "Juin", "Juillet", "Août", "Septembre", "Octobre", "Novembre", "Decembre"];
    var ShortMonth = ["Jan", "Fev", "Mars", "Avr", "Mai", "Juin", "Juil", "Août", "Sept", "Oct", "Nov", "Dec"];
    var chart = new Chart();
    var url = "gestionStatistiquesCommandes?action=model&model=getVenteAnnuel&q=gfu;";
    var urlAllYear = "gestionStatistiquesCommandes?action=model&model=getVenteAllAnnee&q=gfu;";
    var urlEntity = "gestionStatistiquesCommandes?action=model&model=getVenteAnnuelByEntity&q=gfu;";
    var urlTopVendeur = "gestionStatistiquesCommandes?action=model&model=CommandeTOP10&q=gfu;";
    var urlCategorie = "gestionStatistiquesCommandes?action=model&model=getVenteAnnuelByCategorie&q=gfu;";
    var urlEntityCategorie = "gestionStatistiquesCommandes?action=model&model=getVenteAnnuelCategorieByEntity&q=gfu;";
    var annee = new Date().getFullYear();
    var EventEmitters = new EventEmitter();
    var self = this;

    function drawSelect(data, option) {
        $(option.id).html("");
        if (option.class) {
            $(option.id).attr("class", "select2-size-xs block form-control round " + option.class);
        } else {
            $(option.id).attr("class", "select2-size-xs block form-control round");
        }

        $(option.id).attr("name", option.name);

        if (option.multiple) {
            $(option.id).attr("multiple", "multiple");
        }
        if (option.entity === '0') {
            data = data.entity;
        } else if (option.entity === '1') {
            data = data.entity;
        } else if (option.entity === '2') {
            data = data.entity;

        } else if (option.entity === '3') {
            data = data.clients;
        } else if (option.entity === '4') {
            data = data.tourner;
        } else if (option.entity === '5') {
            data = data.preselleur;
        }

        if (!option.multiple) {
            $(option.id).append($("<option value=''></option>"));
        }

        for (var i = 0; i < data.length; i++) {
            $(option.id).append($("<option value='" + parseInt(data[i].id) + "'>" + data[i].nom + "</option>"));
        }

        $('.select2-size-xs').select2({
            containerCssClass: 'select-xs form-control'
        });
    }
    function getEntity(id, option) {
        $.ajax({
            type: "POST",
            url: 'gestionStatistiquesCommandes?action=model&model=getEntityZone&q=gfu;',
            data: {},
            success: function (data) {

                if (data.length > 0) {
                    localStorage.setItem("all_entity", JSON.stringify(data[0]));


                }

            },
            error: function (error) {
                console.log(error);
            },
            dataType: 'json'
        });
    }
    function SendRequest(url, form, evt) {
        $.ajax({
            type: "POST",
            url: url,
            data: form,
            success: function (data) {
                EventEmitters.emit(evt, data);
            },
            dataType: 'json'
        });
    }



    function getMonthTable(i) {
        $('.head_al_year' + i).html('<thead class="">' +
                '<tr class="head' + i + '"> </tr>' +
                '</thead>' +
                '<tbody class="head_al_year' + i + '">' +
                ' <tr class="body_qte' + i + '"></tr>' +
                ' <tr class="body_montant' + i + '"></tr>' +
                '</tbody>');
        $(".head" + i).html('');
        $(".head" + i).append('<th></th>');
        for (var j = 0; j < ShortMonth.length; j++) {
            $(".head" + i).append('<th>' + ShortMonth[j] + '</th>');
        }
    }

    SendRequest(url, {annee: annee, etat: 200}, "rapport_annuel");
    //SendRequest(urlTopVendeur, {annee: annee, etat: 200}, "rapport_categorie");
    SendRequest(urlCategorie, {annee: annee, etat: 200}, "categorie_dashboard");
    if (localStorage.getItem("all_entity")) {
        drawSelect(JSON.parse(localStorage.getItem("all_entity")), {});
    } else {

        getEntity("ff", {});
    }
    $("#annee_all_categorie").change(function (e) {
        e.preventDefault();
        SendRequest(urlCategorie, {annee: $(this).val(), etat: 200}, "categorie_dashboard");
    });
    $("#who_entity").change(function (e) {
        e.preventDefault();
        var option = {
            id: "#another_entity",
            name: "region",
            entity: $(this).val(),
            multiple: true
        };
        if ($(this).val() === '1') {
            option.name = "ville";
        } else if ($(this).val() === '2') {
            option.name = "secteur";
        } else if ($(this).val() === '3') {
            option.name = "client";
        } else if ($(this).val() === '4') {
            option.name = "tourner";
        } else if ($(this).val() === '5') {
            option.name = "presalleur";
        }
        drawSelect(JSON.parse(localStorage.getItem("all_entity")), option);

    });
    $("#who_entity_categorie").change(function (e) {
        e.preventDefault();
        var option = {
            id: "#another_entity_categorie",
            name: "region",
            entity: $(this).val(),
            multiple: true
        };
        if ($(this).val() === '1') {
            option.name = "ville";
        } else if ($(this).val() === '2') {
            option.name = "secteur";
        } else if ($(this).val() === '3') {
            option.name = "client";
        } else if ($(this).val() === '4') {
            option.name = "tourner";
        } else if ($(this).val() === '5') {
            option.name = "presalleur";
        }
        drawSelect(JSON.parse(localStorage.getItem("all_entity")), option);

    });
    var arrayYear = [];
    for (var i = 2018; i <= new Date().getFullYear(); i++) {
        arrayYear.push(i);

    }
    $("#year_annee").change(function (e) {
        e.preventDefault();
        annee = $(this).val();
        if (annee === '0') {
            $.ajax({
                type: "POST",
                url: urlAllYear,
                data: {'annee': arrayYear.join(","), 'etat': 200},
                success: function (data) {
                    EventEmitters.emit('rapport_annuel', {data: data, allyear: arrayYear});
                },
                dataType: 'json'
            });

        } else {
            SendRequest(url, {annee: annee, etat: 200}, "rapport_annuel");
        }
    });
    $("#top_entity").change(function (e) {

        var value = $(this).val();
        if (value === '0') {
            $('.region_entity').addClass('hidden').find('select').attr("disabled", "disabled");
            $('.region_entity_region').removeClass('hidden').find('select').removeAttr("disabled");
        } else {
            $('.region_entity').removeClass('hidden').find('select').removeAttr("disabled");
            $('.region_entity_region').addClass('hidden').find('select').attr("disabled", "disabled");
        }
    });
    $(".all_entity_form").submit(function (e) {
        e.preventDefault();
        SendRequest(urlEntity, $(this).serialize(), "rapport_annuel_entity");
    });
    $(".all_entity_categorie_form").submit(function (e) {
        e.preventDefault();
        SendRequest(urlEntityCategorie, $(this).serialize(), "categorie_dashboard_entity");
    });
    $(".form_top_entity").submit(function (e) {
        e.preventDefault();
        SendRequest(urlTopVendeur, $(this).serialize(), "top_entity");
    });
    require.config({
        paths: {
            echarts: '../../../StackAdmin/app-assets/vendors/js/charts/echarts'
        }
    });


    // Configuration
    // ------------------------------



    require(
            [
                'echarts',
                'echarts/chart/bar',
                'echarts/chart/line',
                'echarts/chart/pie',
                'echarts/chart/funnel'
            ],
            // Charts setup
                    function (ec) {


                        EventEmitters.on('rapport_annuel', function (data) {

                            var options = {
                                title: data.allyear ? 'Ventes Annuelle' : 'Ventes Annuelle ' + annee,
                                legend: {
                                    data: ['Vendu'],
                                    x: 'center',
                                    y: 'bottom',
                                    borderColor: "#b48484",
                                    type: 'scroll'
                                },
                                xAxis: [{
                                        type: 'category',
                                        data: data.allyear ? data.allyear : ShortMonth,
                                        boundaryGap: true
                                    }],
                                yAxis: [
                                    {
                                        type: 'value',
                                        name: '',
                                        axisLabel: {
                                            formatter: '{value}'
                                        }
                                    }
                                ]
                            };
                            var seriedata = [];
                            if (data instanceof Array) {
                                getMonthTable(1);
                                $(".body_montant1").html('');
                                $(".body_qte1").html('');
                                $(".body_montant1").append('<td>Montant</td>');
                                $(".body_qte1").append('<td>Quantite</td>');
                                for (var i = 0; i < data.length; i++) {
                                    seriedata.push(data[i].MT);
                                    $(".body_montant1").append('<td>' + formatNumber(data[i].MT) + '</td>');
                                    $(".body_qte1").append('<td>' + formatNumber(data[i].QT) + '</td>');
                                }
                            } else {
                                $(".head_al_year1").html('');

                                for (var i = 0; i < data.data.length; i++) {
                                    seriedata.push(data.data[i].MT);
                                }
                            }
                            var series = [
                                {
                                    name: 'Vendu',
                                    type: 'bar',
                                    itemStyle: {normal: {label: {show: true, position: 'top'}}},
                                    data: seriedata
                                }
                            ];
                            chart.BarLineChart(ec, series, options, 'column-line');
                        });
//Rapport annuelle par entité categorie_dashboard
                        EventEmitters.on('rapport_annuel_entity', function (data) {
                            var options = {
                                title: 'Ventes Annuelle cumulée des ' + $("#who_entity").find("option:selected").text() + 'pour l\' année ' + $("#annee_all_entity").find("option:selected").text(),
                                legend: {
                                    data: ['Vendu'],
                                    x: 'center',
                                    y: 'bottom',
                                    borderWidth: 2,
                                    borderColor: "#b48484",
                                    type: 'scroll'
                                },
                                xAxis: [{
                                        type: 'category',
                                        data: ShortMonth,
                                        boundaryGap: true
                                    }],
                                yAxis: [
                                    {
                                        type: 'value',
                                        name: '',
                                        axisLabel: {
                                            formatter: '{value}'
                                        }
                                    }
                                ]
                            };
                            var seriedata = [];
                            if (data instanceof Array) {
                                getMonthTable(2);
                                $(".body_montant2").html('');
                                $(".body_qte2").html('');
                                $(".body_montant2").append('<td>Montant</td>');
                                $(".body_qte2").append('<td>Quantite</td>');
                                for (var i = 0; i < data.length; i++) {
                                    seriedata.push(data[i].MT);
                                    $(".body_montant2").append('<td>' + formatNumber(data[i].MT) + '</td>');
                                    $(".body_qte2").append('<td>' + formatNumber(data[i].QT) + '</td>');
                                }


                            }
                            var series = [
                                {
                                    name: 'Vendu',
                                    type: 'bar',
                                    itemStyle: {normal: {label: {show: true, position: 'top'}}},
                                    data: seriedata
                                }
                            ];
                            chart.BarLineChart(ec, series, options, 'graph_par_entite');
                        });



//rapport des categories

                        EventEmitters.on('categorie_dashboard', function (data) {
                            var legend = []
                                    , allserie = [];
                            for (var i = 0; i < data.length; i++) {
                                legend.push(data[i].nom);
                                var allval = {
                                    name: data[i].nom,
                                    type: 'line',
                                    data: []
                                };
                                allval.data = data[i].data.map(function (elt) {
                                    return elt.MT;
                                });
                                allserie.push(allval);

                            }
                            var options = {
                                title: 'Ventes Annuelle par type de produit pour l\' année ' + $("#annee_all_categorie").find("option:selected").text(),
                                legend: {
                                    data: legend,
                                    x: 'center',
                                    y: 370,
                                    borderWidth: 2,
                                    borderColor: "#b48484"
                                },
                                xAxis: [{
                                        type: 'category',
                                        data: ShortMonth,
                                        boundaryGap: true
                                    }],
                                yAxis: [
                                    {
                                        type: 'value',
                                        name: '',
                                        axisLabel: {
                                            formatter: '{value}'
                                        }
                                    }
                                ]
                            };
                            var seriedata = [];
                            if (data instanceof Array) {
                                for (var i = 0; i < data.length; i++) {
                                    seriedata.push(data[i].MT);
                                }
                            }
                            var series = allserie;
                            chart.BarLineChart(ec, series, options, 'categorie_dashboard');
                        });
//rapport des categories par entite
                        EventEmitters.on('categorie_dashboard_entity', function (data) {
                            var legend = []
                                    , allserie = [];
                            for (var i = 0; i < data.length; i++) {
                                legend.push(data[i].nom);
                                var allval = {
                                    name: data[i].nom,
                                    type: 'line',
                                    smooth: true,
                                    stack: 'Total',
                                    itemStyle: {normal: {areaStyle: {type: 'default'}}},
                                    data: []
                                };
                                allval.data = data[i].data.map(function (elt) {
                                    return elt.MT;
                                });
                                allserie.push(allval);

                            }
                            var options = {
                                title: 'Ventes Annuelle cumulées par type de produits ' + $("#who_entity_categorie").find("option:selected").text() + 'pour l\' année ' + $("#annee_all_categorie_entity").find("option:selected").text(),
                                legend: {
                                    data: legend,
                                    x: 'center',
                                    y: 370,
                                    borderWidth: 2,
                                    borderColor: "#b48484"
                                },
                                xAxis: [{
                                        type: 'category',
                                        data: ShortMonth,
                                        boundaryGap: true
                                    }],
                                yAxis: [
                                    {
                                        type: 'value',
                                        name: '',
                                        axisLabel: {
                                            formatter: '{value}'
                                        }
                                    }
                                ]
                            };
                            var seriedata = [];
                            if (data instanceof Array) {
                                for (var i = 0; i < data.length; i++) {
                                    seriedata.push(data[i].MT);
                                }
                            }
                            var series = allserie;
                            chart.BarLineChart(ec, series, options, 'categorie_dashboard_entity');
                        });

                        EventEmitters.on('top_entity', function (data) {
                            console.log(data);
                            var label = [];
                            var series = [];
                            var series11 = [];
                            $(".table_top_entity").html(' <thead>' +
                                    '<tr>' +
                                    '<th>Nom</th>' +
                                    '<th>Quantite</th>' +
                                    '<th>Montant</th>' +
                                    '</tr>' +
                                    '</thead>' +
                                    '<tbody class="table_top_entity_tbody">' +
                                    '</tbody>');
                            for (var i = 0; i < data.length; i++) {
                                label.push(data[i].nom);
                                series.push({value: parseInt(data[i].montant), name: data[i].nom, itemStyle: {normal: {label: {formatter: "{c} ({d}%)"}}}});
                                series11.push(parseInt(data[i].montant));
                                $(".table_top_entity_tbody").append('<tr>' +
                                        '<td>' + data[i].nom + '</td>' +
                                        '<td>' + formatNumber(data[i].qt) + '</td>' +
                                        '<td>' + formatNumber(data[i].montant) + '</td>' +
                                        '</tr> ');
                            }

                            var options1 = {
                                legend: {
                                    data: ['Montant'],
                                    x: 'left',
                                    y: 'top',
                                    borderWidth: 2,
                                    borderColor: "#b48484"
                                },
                                title: 'Top ' + $('select[name=top]').find('option:selected').text() + ' ' + $('.who').find('option:selected').text(),
                                yAxis: [{
                                        type: 'category',
                                        data: label,
                                        boundaryGap: true
                                    }],
                            xAxis : [
                                    {
                                        type: 'value',
                                        name: '',
                                        axisLabel: {
                                            formatter: '{value}'
                                        }
                                    }
                                ]
                            };
                            var series1 = [
                                {
                                    name: 'Montant',
                                    type: 'bar',
                                    itemStyle: {normal: {label: {show: true, position: 'top'}}},
                                    data: series11
                                }
                            ];
                            var data = [{
                                    name: 'Montant',
                                    type: 'pie',
                                    radius: '70%',
                                    center: ['50%', '57.5%'],
                                    data: series
                                }];
                            var title = 'Top ' + $('select[name=top]').find('option:selected').text() + ' ' + $('.who').not('[disabled]').find('option:selected').text();

                            // chart.BarLineChart(ec, series1, options1, 'top_entity_dashboard_graph2');
                            chart.drawBasicPieChart(ec, data, {title: title, legende: label}, 'top_entity_dashboard_graph');
                        });

//                        var data = [{
//                                name: 'Browsers',
//                                type: 'pie',
//                                radius: '70%',
//                                center: ['50%', '57.5%'],
//                                data: [
//                                    {value: 335, name: 'IE', itemStyle: {normal: {label: {formatter: "{b}: {c} ({d}%)"}}}},
//                                    {value: 310, name: 'Opera', itemStyle: {normal: {label: {formatter: "{b}: {c} ({d}%)"}}}},
//                                    {value: 234, name: 'Safari', itemStyle: {normal: {label: {formatter: "{b}: {c} ({d}%)"}}}},
//                                    {value: 135, name: 'Firefox', itemStyle: {normal: {label: {formatter: "{b}: {c} ({d}%)"}}}},
//                                    {value: 1548, name: 'Chrome', itemStyle: {normal: {label: {formatter: "{b}: {c} ({d}%)"}}}}
//                                ]
//                            }];
//                        chart.drawBasicPieChart(ec, data, {title: "Top 5", legende: ['IE', 'Opera', 'Safari', 'Firefox', 'Chrome']}, 'doughnut_mensuelle');

                    }
            );


        });
