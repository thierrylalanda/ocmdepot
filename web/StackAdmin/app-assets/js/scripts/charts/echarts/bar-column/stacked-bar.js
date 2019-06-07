/*=========================================================================================
 File Name: stacked-bar.js
 Description: echarts stacked bar chart
 ----------------------------------------------------------------------------------------
 Item Name: Stack - Responsive Admin Theme
 Version: 3.0
 Author: PIXINVENT
 Author URL: http://www.themeforest.net/user/pixinvent
 ==========================================================================================*/

// Stacked bar chart
// ------------------------------

$(window).on("load", function () {
    // Set paths
    // ------------------------------

    require.config({
        paths: {
            echarts: '../../../app-assets/vendors/js/charts/echarts'
        }
    });


    // Configuration
    // ------------------------------

    require(
            [
                'echarts',
                'echarts/chart/bar',
                'echarts/chart/line'
            ],
            // Charts setup
                    function (ec) {
                        // Initialize chart
                        // ------------------------------
                        var myChart = ec.init(document.getElementById('stacked-bar'));

                        // Chart Options
                        // ------------------------------
                        chartOptions = {

                            // Setup grid
                            grid: {
                                x: 40,
                                x2: 40,
                                y: 45,
                                y2: 25
                            },

                            // Add tooltip
                            tooltip: {
                                trigger: 'axis',
                                axisPointer: {// Axis indicator axis trigger effective
                                    type: 'shadow'        // The default is a straight line, optionally: 'line' | 'shadow'
                                }
                            },
                            title: 'lalanda',
                            // Add legend
                            legend: {
                                data: ['Montant']
                            },

                            // Add custom colors
                            color: ['#00B5B8'],

                            // Horizontal axis
                            xAxis: [{
                                    type: 'value'
                                }],

                            // Vertical axis
                            yAxis: [{
                                    type: 'category',
                                    data: ['Ondoa', 'Jean', 'Philippe', 'Therese', 'Fabrice']
                                }],

                            // Add series
                            series: [
                                {
                                    name: 'Montant',
                                    type: 'bar',
                                    stack: 'Total',
                                    itemStyle: {normal: {label: {show: true, position: 'insideRight'}}},
                                    data: [320, 302, 301, 334, 390]
                                }
                            ]
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

                        var myChart = ec.init(document.getElementById('top_clients'));

                        // Chart Options
                        // ------------------------------
                        chartOptions = {

                            // Setup grid
                            grid: {
                                x: 40,
                                x2: 40,
                                y: 45,
                                y2: 25
                            },

                            // Add tooltip
                            tooltip: {
                                trigger: 'axis',
                                axisPointer: {// Axis indicator axis trigger effective
                                    type: 'shadow'        // The default is a straight line, optionally: 'line' | 'shadow'
                                }
                            },

                            // Add legend
                            legend: {
                                data: ['Montant']
                            },

                            // Add custom colors
                            color: ['#607d8b'],

                            // Horizontal axis
                            xAxis: [{
                                    type: 'value'
                                }],

                            // Vertical axis
                            yAxis: [{
                                    type: 'category',
                                    data: ['Clement', 'Alerte', 'Tchounang', 'Temgoua', 'Djoussi']
                                }],

                            // Add series
                            series: [
                                {
                                    name: 'Montant',
                                    type: 'bar',
                                    stack: 'Total',
                                    itemStyle: {normal: {label: {show: true, position: 'insideRight'}}},
                                    data: [320, 302, 301, 334, 390]
                                }
                            ]
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
            );
        });