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

        // Add tooltip
        tooltip: {
            trigger: 'axis'
        },
        title: {
            text: "Etat General de l'année ",
            subtext: option.title,
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
                        drawSimpleChart('jtzxbkui4528f725jhf?q=sdfsdgfg&action=model&model=TicketmodelByPeriode&isNew=0&periode=9', {}, "");


                    }
            );
            function drawNotificationHome(data) {
                var mainNotif = $(".notification_page");
                mainNotif.html("");

                var $head = '<li class="dropdown-menu-header">' +
                        '<h6 class="dropdown-header m-0">' +
                        '<span class="grey darken-2">Notifications</span>' +
                        '<span class="notification-tag badge badge-default badge-danger float-right m-0 notif_number">' + data.length + '</span>' +
                        '</h6>' +
                        ' </li>';
                mainNotif.append($head);
                if (data.length <= 4) {


                    for (var i = 0; i < data.length; i++) {
                        var $message;
                        if (data[i].message.length > 100) {
                            $message = data[i].message.substring(0, 99) + " <strong>lire la suite ...</strong>";
                        } else {
                            $message = data[i].message;
                        }

                        var $body = '<li class="scrollable-container media-list">' +
                                '<a href="javascript:void(0)">' +
                                '<div class="media">' +
                                '<div class="media-left align-self-center"><i class="ft-plus-square icon-bg-circle bg-cyan"></i></div>' +
                                '<div class="media-body">' +
                                ' <h6 class="media-heading">' + data[i].titre + '</h6>' +
                                '<p class="notification-text font-small-3 text-muted">' + $message + '</p>' +
                                ' <small>' +
                                '   <time class="media-meta text-muted" datetime="2015-06-11T18:29:20+08:00">' + data[i].dateNotif + '</time>' +
                                '  </small>' +
                                '</div>' +
                                '</div>' +
                                '</a>' +
                                ' </li>';
                        mainNotif.append($body);
                    }
                } else {
                    for (var i = 0; i < 5; i++) {
                        var $message;
                        if (data[i].message.length > 100) {
                            $message = data[i].message.substring(0, 99) + " <strong>lire la suite ...</strong>";
                        } else {
                            $message = data[i].message;
                        }
                        var $body = '<li class="scrollable-container media-list">' +
                                '<a href="javascript:void(0)">' +
                                '<div class="media">' +
                                '<div class="media-left align-self-center"><i class="ft-plus-square icon-bg-circle bg-cyan"></i></div>' +
                                '<div class="media-body">' +
                                ' <h6 class="media-heading">' + data[i].titre + '</h6>' +
                                '<p class="notification-text font-small-3 text-muted">' + $message + '</p>' +
                                ' <small>' +
                                '   <time class="media-meta text-muted" datetime="2015-06-11T18:29:20+08:00">' + data[i].dateNotif + '</time>' +
                                '  </small>' +
                                '</div>' +
                                '</div>' +
                                '</a>' +
                                ' </li>';
                        mainNotif.append($body);
                    }
                }

                var $footer = '<li class="dropdown-menu-footer"><a class="dropdown-item text-muted text-center" href="javascript:void(0)">tout lire</a></li>';
                mainNotif.append($footer);

            }
            function getNewNotificationsHome(IdUser) {

                var $ul = $(".notification");
                $.post(
                        'ticket',
                        {"id": IdUser, "action": "model", model: "getUserssNotification", "q": "vue"},
                        function (data) {

                            drawNotificationHome(data);

                        },
                        'json'
                        );


            }
            sessionStorage.setItem("iduser", $(".notification").attr("id"));
            var IdUser = sessionStorage.getItem("iduser");
            getNewNotificationsHome(IdUser);
        });