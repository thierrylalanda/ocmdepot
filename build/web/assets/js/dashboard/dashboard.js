/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function drawBasiChart(ec, data, option, id) {
    var myChart = ec.init(document.getElementById(id));

    // Chart Options
    // ------------------------------
    chartOptions = {
        // Setup grid
        /*   grid: {
         x: 40,
         x2: 20,
         y: 35,
         y2: 25
         },*/
        // Add tooltip
        tooltip: {
            trigger: 'axis'
        },
        // Add legend

        title: {
            text: option.title,
            subtext: "",
            x: 'center',
            borderWidth: 2,
            borderColor: "#8d1e5a"


        },
        // Add legend
        legend: {
            data: option.legend,
            x: 'center',
            y: 'bottom',
            borderWidth: 2,
            borderColor: "#b48484"
        },
        toolbox: {
            show: true,
            orient: 'vertical',
            x: 'right',
            y: 35,
            feature: {
                magicType: {
                    show: true,
                    title: {
                        line: 'Switch to line chart',
                        bar: 'Switch to bar chart',
                        stack: 'Switch to stack',
                        tiled: 'Switch to tiled'
                    },
                    type: ['line', 'bar', 'stack']
                },
                restore: {
                    show: true,
                    title: 'Rafraichir'
                },
                saveAsImage: {
                    show: true,
                    title: 'Enregistrer l\'image',
                    lang: ['Save']
                }
            }
        },
        // Add custom colors
        color: ['#21d299', '#ff7487', '#ffa67c'],
        // Enable drag recalculate
        calculable: true,
        // Horizontal axis
        xAxis: [{
                type: 'category',
                boundaryGap: false,
                data: option.xAxis
            }],
        // Vertical axis
        yAxis: [{
                type: 'value'
            }],
        // Add series
        series: data
    };

    // Apply options
    // ------------------------------

    myChart.setOption(chartOptions);

    // Resize chart
    // ------------------------------

    $(function () {
        // Resize chart on menu width change and window resize
        $(window).on('resize', resize);
        $(".menu-toggle").on('click', resize);

        // Resize function
        function resize() {
            setTimeout(function () {

                // Resize chart

                myChart.resize();
            }, 200);
        }
    });

}

$(document).ready(function () {
    var week = ["Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi", "Dimanche"];
    var Month = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];
    function getEntity(id, option) {

        $.ajax({
            type: "POST",
            url: 'ticket?action=model&q=jgddgg&model=' + option.model + '&id=' + id,
            data: {},
            success: function (data) {
                $(option.id).html("");
                $(option.id).attr("class", "select2-size-xs block form-control round");
                $(option.id).attr("name", option.name);
                if (option.multiple) {
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

    $("#region_reporting_personnel_tb").change(function (e) {
        e.preventDefault();
        var option = {model: "getAllUsersByRegion", id: "#personnel_reporting_tb", name: "entity", multiple: false};
        getEntity($(this).val(), option);
    });
    $("#region_reporting_client_tb").change(function (e) {
        e.preventDefault();
        var option = {model: "getAllClientsByRegion", id: "#client_reporting_tb", name: "entity", multiple: false};
        getEntity($(this).val(), option);
    });
    $("#region_reporting_district_tb").change(function (e) {
        e.preventDefault();
        var option = {model: "getDistricts", id: "#district_reporting_tb", name: "entity", multiple: false};
        getEntity($(this).val(), option);
    });
    $("#region_reporting_secteur_tb").change(function (e) {
        e.preventDefault();
        var option = {model: "getDistricts", id: "#district_reporting_secteur_tb", name: "", multiple: false};
        getEntity($(this).val(), option);
    });

    $("#district_reporting_secteur_tb").change(function (e) {
        e.preventDefault();
        var option = {model: "getSecteurs", id: "#secteur_reporting_tb", name: "entity", multiple: false};
        getEntity($(this).val(), option);
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


                        // Column Chart Options
                        // ------------------------------

                        function drawBarChart(url, donnee, title) {
                            $.ajax({
                                type: "POST",
                                url: url,
                                data: donnee,
                                success: function (data) {
                                    var sdatar = 0, sdatanr = 0, sdatai = 0, sdatae = 0, sdataef = 0, sdatare = 0;
                                    for (var i = 0; i < data.length; i++) {


                                        sdatar += data[i].TotalticketOK;
                                        sdatanr += data[i].TotalticketNonResolut;
                                        sdatai += data[i].TotalticketInsolvable;

                                        sdatae += data[i].TotalticketEncour;
                                        sdataef += data[i].TotalticketDemandeFermeture;
                                        sdatare += data[i].TotalticketReponse;
                                    }
                                    var columnChartOptions = {
                                        // Setup grid
                                        /* grid: {
                                         x: 40,
                                         x2: 47,
                                         y: 35,
                                         y2: 25
                                         },*/
                                        // Add tooltip
                                        tooltip: {
                                            trigger: 'axis',
                                            axisPointer: {
                                                type: 'shadow'
                                            }
                                        },
                                        title: {
                                            text: "Etat des plaintes par Statut aucours de l'année",
                                            subtext: "",
                                            x: 'center',
                                            borderWidth: 2,
                                            borderColor: "#8d1e5a"


                                        },
                                        // Add legend
                                        legend: {
                                            data: ['Tickets'],
                                            x: 'center',
                                            y: 'bottom',
                                            borderWidth: 2,
                                            borderColor: "#b48484"
                                        },
                                        // Add custom colors
                                        color: ['#8bc541'],
                                        // Add toolbox
                                        toolbox: {
                                            show: true,
                                            orient: 'vertical',
                                            x: 'right',
                                            y: 35,
                                            feature: {
                                                magicType: {
                                                    show: true,
                                                    title: {
                                                        line: 'Switch to line chart',
                                                        bar: 'Switch to bar chart',
                                                        tiled: 'Switch to tiled'
                                                    },
                                                    type: ['line', 'bar']
                                                },
                                                restore: {
                                                    show: true,
                                                    title: 'Rafraichir'
                                                },
                                                saveAsImage: {
                                                    show: true,
                                                    title: 'Enregistrer l\'image',
                                                    lang: ['Save']
                                                }
                                            }
                                        },
                                        // Enable drag recalculate
                                        calculable: true,
                                        // Horizontal axis
                                        xAxis: [{
                                                type: 'category',
                                                data: ['Resolu', 'Non Resolu', 'Insolvable', 'Encours', 'Fermeture', 'Repondu']
                                            }],
                                        // Vertical axis
                                        yAxis: [{
                                                type: 'value',
                                                splitArea: {show: true}
                                            }],
                                        // Add series
                                        series: [
                                            {
                                                name: 'Tickets',
                                                type: 'bar',
                                                stack: 'Total',
                                                data: [sdatar, sdatanr, sdatai, sdatae, sdataef, sdatare]
                                            }
                                        ]
                                    };
                                    var columnChart = ec.init(document.getElementById('column-chart'));
                                    columnChart.setOption(columnChartOptions);
                                    $(function () {
                                        // Resize chart on menu width change and window resize
                                        $(window).on('resize', resize);
                                        $(".menu-toggle").on('click', resize);

                                        // Resize function
                                        function resize() {
                                            setTimeout(function () {

                                                // Resize chart

                                                columnChart.resize();
                                            }, 200);
                                        }
                                    });
                                },
                                dataType: 'json'
                            });
                        }
                        drawBarChart('jtzxbkui4528f725jhf?q=sdfsdgfg&action=model&model=TicketmodelByPeriode&isNew=7&periode=0', {}, "Tickets Societe");

                        function drawSimpleChart(url, donnee, title) {
                            $.ajax({
                                type: "POST",
                                url: url,
                                data: donnee,
                                success: function (data) {
                                    var dataTr = [];
                                    var dataTNr = [];
                                    var dataTI = [];
                                    for (var i = 0; i < data[0].length; i++) {
                                        dataTr.push(data[0][i].TotalticketReponse);
                                        dataTNr.push(data[0][i].TotalticketNonResolut);
                                        dataTI.push(data[0][i].TotalticketInsolvable);
                                    }
                                    var data = [
                                        {
                                            name: 'Tickets reponse',
                                            type: 'line',
                                            smooth: true,
                                            itemStyle: {normal: {areaStyle: {type: 'default'}}},
                                            data: dataTr
                                        },
                                        {
                                            name: 'Tickets non resolu',
                                            type: 'line',
                                            smooth: true,
                                            itemStyle: {normal: {areaStyle: {type: 'default'}}},
                                            data: dataTNr
                                        },
                                        {
                                            name: 'Tickets Insolvable',
                                            type: 'line',
                                            smooth: true,
                                            itemStyle: {normal: {areaStyle: {type: 'default'}}},
                                            data: dataTI
                                        }
                                    ];
                                    var option = {
                                        legend: ['Tickets reponse', 'Tickets non resolu', 'Tickets Insolvable'],
                                        xAxis: Month,
                                        title: title
                                    };
                                    drawBasiChart(ec, data, option, "basic-area");
                                },
                                dataType: 'json'
                            });
                        }
                        drawSimpleChart('jtzxbkui4528f725jhf?q=sdfsdgfg&action=model&model=TicketmodelByPeriode&isNew=0&periode=9', {}, "Etat General de l'année");

                        $(".basic_form_year").submit(function (e) {
                            e.preventDefault();
                            var region = $(this).find("select[name=id]").find("option:selected").text();
                            var title = "TRAITEMENT DES PLAINTES CLIENTS  REGION :" + region + " CUMULER POUR L'ANNEE " + $(this).find("select[name=annee]").find("option:selected").text();
                            drawSimpleChart($(this).attr("action"), $(this).serialize(), title);

                        });
                    }
            );

        });