/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

class Chart {
    constructor() { }

    BarLineChart(ec, series, options, id) {
        var myChart = ec.init(document.getElementById(id));
        var chartOptions = {

            tooltip: {
                trigger: 'axis'
            },
            title: {
                text: options.title ? options.title : '',
                subtext: options.subtitle ? options.subtitle : '',
                x: 'center',
                borderWidth: 2,
                borderColor: "#8d1e5a"


            },
            // Add legend
            legend: options.legend,
            color: options.color ? options.color : [],
            toolbox: {
                show: true,
                orient: 'horizontal',
                feature: {
                    magicType: {
                        show: true,
                        title: {
                            pie: 'Switch to pies',
                            funnel: 'Switch to funnel',
                            bar: "en bâton",
                            line: "en ligne",
                            stack: "supperposer"
                        },
                        type: ['bar', 'line', 'stack'],
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
            calculable: true,
            xAxis: options.xAxis,
            yAxis: options.yAxis,
            series: series
        };

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

    drawBasicPieChart(ec, data, option, id) {
        var myChart = ec.init(document.getElementById(id));
        // Chart Options
        // ------------------------------

        // ------------------------------
        var chartOptions = {
            
            // Add title
            title: {
                text: option.title ? option.title : '',
                subtext: option.subtitle ? option.subtitle : '',
                x: 'center',
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
                data: option.legende
            },

            // Add custom colors
            // color: ['#00A5A8', '#626E82', '#FF7D4D', '#FF4558', '#16D39A'],

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
                                funnelAlign: 'left'
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
}