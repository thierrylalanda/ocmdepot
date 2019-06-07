/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function drawPieChart(ec, data, option, id) {
    var myChart = ec.init(document.getElementById(id));
    // Chart Options
    // ------------------------------

    // ------------------------------
    var chartOptions = {
        // Add title
        title: {
            text: option.title,
            subtext: "",
            x: 'right',
            borderWidth: 2,
            borderColor: "#8d1e5a"
        },
        // Add tooltip
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b}: {c} ({d}%)"
        },
        // Add legend
        legend: {
            orient: 'vertical',
            x: 'left',
            //y:'bottom',
            borderWidth: 2,
            borderColor: "#b48484",
            data: option.legend
        },
        // Add custom colors
        color: ['#00A5A8', '#626E82', '#FF7D4D', '#FF4558', '#16D39A'],
        // Display toolbox
        toolbox: {
            show: true,
            orient: 'vertical',
            feature: {
                magicType: {
                    show: true,
                    title: {
                        pie: 'Switch to pies',
                        funnel: 'Switch to funnel'
                    },
                    type: ['pie', 'funnel'],
                    option: {
                        funnel: {
                            x: '25%',
                            y: '20%',
                            width: '50%',
                            height: '70%',
                            funnelAlign: 'left',
                            max: 1548
                        }
                    }
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
        // Add series
        series: data
    };

    // Apply options
    // ------------------------------

    myChart.setOption(chartOptions);
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

                        function getDataToDraw(id, url, donne) {
                            $.ajax({
                                type: "POST",
                                url: url,
                                data: donne,
                                success: function (data) {
                                    var legend = [];
                                    var dataserier = [];
                                    var dataseriernr = [];
                                    for (var i = 0; i < data.length; i++) {
                                        legend.push(data[i].entite);
                                        dataserier.push({value: data[i].TotalticketOK, name: data[i].entite});
                                        dataseriernr.push({value: data[i].TotalticketNonResolut, name: data[i].entite});
                                    }
                                    var SeriesR = [{
                                            name: 'Rubriques Resolu',
                                            type: 'pie',
                                            radius: '70%',
                                            center: ['65%', '50%'],
                                            data: dataserier
                                        }];
                                    var SeriesNR = [{
                                            name: 'Rubriques Non resolu',
                                            type: 'pie',
                                            radius: '70%',
                                            center: ['65%', '50%'],
                                            data: dataseriernr

                                        }];
                                    var option1 = {
                                        title: "Plaintes Non Resolues",
                                        legend: legend
                                    };
                                    var option2 = {
                                        title: "Plaintes Resolues",
                                        legend: legend
                                    };
                                    drawPieChart(ec, SeriesNR, option1, "plainte_traite_rubrique");
                                    drawPieChart(ec, SeriesR, option2, "basic-pie");
                                },
                                dataType: 'json'
                            });
                        }
                        getDataToDraw("basic-pie", "jtzxbkui4528f725jhf?q=sdfsdgfg&action=model&model=TicketmodelByPeriode&isNew=4&periode=0", {});

                        // Apply options
                        // ------------------------------
                        $(".rubrique_form").submit(function (e) {
                            e.preventDefault();
                            getDataToDraw("body_dashboard_table", $(this).attr("action"), $(this).serialize());



                        });
                        function drawSimpleChart(url, donnee, title) {
                            $.ajax({
                                type: "POST",
                                url: url,
                                data: donnee,
                                success: function (data) {
                                    var legend = [];
                                    var dataserier = [];
                                    for (var i = 0; i < data.length; i++) {
                                        legend.push(data[i].entite);
                                        dataserier.push({value: data[i].TotalTicket, name: data[i].entite});
                                    }
                                    var SeriesR = [{
                                            name: 'Plaintes par source',
                                            type: 'pie',
                                            data: dataserier,
                                            radius: ['50%', '70%'],
                                            center: ['50%', '57.5%'],
                                            itemStyle: {
                                                normal: {
                                                    label: {
                                                        show: true
                                                    },
                                                    labelLine: {
                                                        show: true
                                                    }
                                                },
                                                emphasis: {
                                                    label: {
                                                        show: true,
                                                        formatter: '{b}' + '\n\n' + '{c} ({d}%)',
                                                        position: 'center',
                                                        textStyle: {
                                                            fontSize: '17',
                                                            fontWeight: '500'
                                                        }
                                                    }
                                                }
                                            }
                                        }];

                                    var option = {
                                        title: "Plaintes par Source",
                                        legend: legend
                                    };
                                    drawPieChart(ec, SeriesR, option, "pie-chart");
                                },
                                dataType: 'json'
                            });
                        }
                        drawSimpleChart('jtzxbkui4528f725jhf?q=sdfsdgfg&action=model&model=TicketmodelByPeriode&isNew=7&periode=0', {}, "Tickets Societe");

                  /************************************
                         *       Top Selling Categories      *
                         ************************************/

                        // Initialize chart
                        // ------------------------------
                        var topCategoryChart = ec.init(document.getElementById('doughnut'));

                        // Chart Options
                        // ------------------------------
                        var topCategoryChartOptions = {

                            // Add title
                            title: {
                                text: 'Top 5 Categories',
                                subtext: 'Top selling mobiles',
                                x: 'center',
                                textStyle: {
                                    color: '#FFF'
                                },
                                subtextStyle: {
                                    color: '#FFF'
                                }
                            },

                            // Add legend
                            legend: {
                                orient: 'vertical',
                                x: 'left',
                                data: ['Moto Z', 'Galaxy S7 Edge', 'One Plus 3', 'Mi 5', 'iPhone 6s'],
                                textStyle: {
                                    color: '#FFF'
                                }
                            },

                            // Add custom colors
                            color: ['#ffd322', '#ffa422', '#e89805', '#ffc107', '#fff306'],

                            // Display toolbox
                            toolbox: {
                                show: true,
                                orient: 'vertical',
                                feature: {
                                   
                                    magicType: {
                                        show: true,
                                        title: {
                                            pie: 'Switch to pies',
                                            funnel: 'Switch to funnel'
                                        },
                                        type: ['pie', 'funnel'],
                                        option: {
                                            funnel: {
                                                x: '25%',
                                                y: '20%',
                                                width: '50%',
                                                height: '70%',
                                                funnelAlign: 'left',
                                                max: 1548
                                            }
                                        }
                                    },
                                    restore: {
                                        show: true,
                                        title: 'Restore'
                                    },
                                    saveAsImage: {
                                        show: true,
                                        title: 'Same as image',
                                        lang: ['Save']
                                    }
                                },
                                color: '#FFF'
                            },

                            // Enable drag recalculate
                            calculable: true,

                            // Add series
                            series: [
                                {
                                    name: 'Top Categories',
                                    type: 'pie',
                                    radius: ['50%', '70%'],
                                    center: ['50%', '57.5%'],
                                    itemStyle: {
                                        normal: {
                                            label: {
                                                show: true,
                                                textStyle: {
                                                    color: '#FFF'
                                                }
                                            },
                                            labelLine: {
                                                show: true,
                                                lineStyle: {
                                                    color: '#FFF'
                                                }
                                            }
                                        },
                                        emphasis: {
                                            label: {
                                                show: true,
                                                formatter: '{b}' + '\n\n' + '{c} ({d}%)',
                                                position: 'center',
                                                textStyle: {
                                                    fontSize: '17',
                                                    fontWeight: '500'
                                                }
                                            }
                                        }
                                    },

                                    data: [
                                        {value: 335, name: 'Moto Z'},
                                        {value: 618, name: 'Galaxy S7 Edge'},
                                        {value: 234, name: 'One Plus 3'},
                                        {value: 135, name: 'Mi 5'},
                                        {value: 956, name: 'iPhone 6s'}
                                    ]
                                }
                            ]
                        };

                        // Apply options
                        // ------------------------------

                        topCategoryChart.setOption(topCategoryChartOptions);


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
                                    topCategoryChart.resize();
                                }, 200);
                            }
                        });
                    }
            );
     

        });