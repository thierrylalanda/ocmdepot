/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function drawTable(id, data, caption) {
    $("#body_dashboard_table").html("");
    $(".text-indice-performance").text(caption);
    for (var i = 0; i < data.length; i++) {
        var val = data[i].TotalticketReponse;
        var pval = data[i].PticketTraiter;
        var bar = pval < 35 ? "bg-danger" : pval < 60 ? "bg-warning" : "bg-success";
        var tr = '<tr>' +
                '<td>' + data[i].entite.replace("null"," ") + '</td>' +
                '<td>' + data[i].TotalTicket + '</td>' +
                '<td>' + val + '</td>' +
                '<td class="text-center font-small-2">' + pval + '%' +
                '<div class="progress progress-sm mt-1 mb-0">' +
                '<div class="progress-bar ' + bar + '" role="progressbar" style="width: ' + pval + '%" aria-valuenow="25"aria-valuemin="0" aria-valuemax="100"></div>' +
                // '<div class="progress-bar ' + bar + '" role="progressbar" style="width: ' + pval + '%" aria-valuenow="25"aria-valuemin="0" aria-valuemax="100"></div>' +
                '</div>' +
                '</td>' +
                '</tr>';
        $("#" + id).prepend(tr);

    }
}
$(document).ready(function () {

    function getDataToDraw(id, url, donne, caption) {
        $.ajax({
            type: "POST",
            url: url,
            data: donne,
            success: function (data) {

                drawTable(id, data, caption);
            },
            dataType: 'json'
        });
    }
    getDataToDraw("body_dashboard_table", "jtzxbkui4528f725jhf?q=sdfsdgfg&action=model&model=TicketmodelByPeriode&isNew=3&periode=0", {}, "Indice de performance des utilisateurs aucours de l'année " + new Date().getFullYear());


    $(".indice_form").submit(function (e) {
        e.preventDefault();
        var entite = $(this).find("select[name=entity]").find("option:selected").text();
        var region = $(this).find("select[name=id]").find("option:selected").text();
        var periode = $(this).find("[name=interval]").val().split("-");
        var title = "Indice de performance des " + entite + " de :" + region + " du " + periode[0] + " au " + periode[1];
        getDataToDraw("body_dashboard_table", $(this).attr("action"), $(this).serialize(), title);



    });
});