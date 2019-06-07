/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function drawPieColumnChart(ec, data, option, id) {
    var myChart5 = ec.init(document.getElementById(id));

    // Chart Options
    // ------------------------------
    var chartOptions5 = {
        // Setup grid
        /* grid: {
         x: 40,
         x2: 40,
         y: 45,
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
        // Add custom colors
        color: ['#00A5A8', '#626E82', '#FF7D4D', '#FF4558', '#16D39A'],
        // Horizontal axis
        xAxis: [{
                type: 'category',
                splitLine: {show: false},
                data: option.xAxis
            }],
        // Vertical axis
        yAxis: [
            {
                type: 'value',
                position: 'right'
            }
        ],
        // Add series
        toolbox: {
            show: true,
            orient: 'vertical',
            feature: {
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
        series: data
    };
    myChart5.setOption(chartOptions5);
    $(function () {
        // Resize chart on menu width change and window resize
        $(window).on('resize', resize);
        $(".menu-toggle").on('click', resize);

        // Resize function
        function resize() {
            setTimeout(function () {

                myChart5.resize();
            }, 200);
        }
    });
}

$(document).ready(function () {
    var week = ["Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi", "Dimanche"];
    var Month = ["Janvier", "Fevrier", "Mars", "Avril", "Mai", "Juin", "Juillet", "Août", "Septembre", "Octobre", "Novembre", "Decembre"];
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



                        // Chart Options
                        // ------------------------------


                        DrawMultipleChart('jtzxbkui4528f725jhf?q=sdfsdgfg&action=model&model=TicketmodelByPeriode&isNew=5&periode=0', {}, "Indice de performance des secteurs aucours de l'année " + new Date().getFullYear());
                        function DrawMultipleChart(url, donne, title) {
                            $.ajax({
                                type: "POST",
                                url: url,
                                data: donne,
                                success: function (data) {
                                    var datar = [], datanr = [], datai = [], dataT = [];
                                    var sdatar = 0, sdatanr = 0, sdatai = 0;
                                    var xaxis = [];
                                    for (var i = 0; i < data.length; i++) {
                                        datar.push(data[i].TotalticketOK);
                                        datanr.push(data[i].TotalticketNonResolut);
                                        datai.push(data[i].TotalticketInsolvable);
                                        dataT.push(data[i].TotalTicket);

                                        sdatar += data[i].TotalticketOK;
                                        sdatanr += data[i].TotalticketNonResolut;
                                        sdatai += data[i].TotalticketInsolvable;

                                        xaxis.push(data[i].entite);
                                    }
                                    var option = {
                                        legend: ['Resolu', 'Non resolu', 'Insolvable', 'Plaintes Reçue', 'TResolu', 'TNon resolu', 'TInsolvable'],
                                        xAxis: xaxis,
                                        title: title
                                    };
                                    var data = [
                                        {
                                            name: 'Resolu',
                                            type: 'bar',
                                            tooltip: {trigger: 'item'},
                                            stack: 'Total',
                                            itemStyle: {normal: {label: {show: true, position: 'insideRight'}}},
                                            data: datar
                                        },
                                        {
                                            name: 'Non resolu',
                                            type: 'bar',
                                            tooltip: {trigger: 'item'},
                                            stack: 'Total',
                                            itemStyle: {normal: {label: {show: true, position: 'insideRight'}}},
                                            data: datanr
                                        },
                                        {
                                            name: 'Insolvable',
                                            type: 'bar',
                                            tooltip: {trigger: 'item'},
                                            stack: 'Total',
                                            itemStyle: {normal: {label: {show: true, position: 'insideRight'}}},
                                            data: datai
                                        },
                                        {
                                            name: 'Plaintes Reçue',
                                            type: 'line',
                                            stack: 'Total',
                                            itemStyle: {normal: {label: {show: true, position: 'insideRight'}}},
                                            data: dataT
                                        },
                                        {
                                            name: 'Tickets',
                                            type: 'pie',
                                            tooltip: {
                                                trigger: 'item',
                                                formatter: '{a} <br/>{b} : {c} ({d}%)'
                                            },
                                            center: [160, 130],
                                            radius: [0, 50],
                                            itemStyle: {
                                                normal: {
                                                    labelLine: {
                                                        length: 20
                                                    }
                                                }
                                            },
                                            data: [
                                                {value: sdatar, name: 'TResolu'},
                                                {value: sdatanr, name: 'TNon resolu'},
                                                {value: sdatai, name: 'TInsolvable'}
                                            ]
                                        }
                                    ];

                                    drawPieColumnChart(ec, data, option, "column-pie");
                                },
                                dataType: 'json'
                            });
                        }
                        $(".entity_form").submit(function (e) {
                            e.preventDefault();
                            var entite = $(this).find("select[name=entity]").find("option:selected").text();
                            var region = $(this).find("select[name=id]").find("option:selected").text();
                            var periode = $(this).find("[name=interval]").val().split("-");
                            var title = "Indice de performance des " + entite + " de :" + region + " du " + periode[0] + " au " + periode[1];
                            DrawMultipleChart($(this).attr("action"), $(this).serialize(), title);

                        });
                    }
            );
        });