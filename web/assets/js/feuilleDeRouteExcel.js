/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function s2ab(s) {

    var buf = new ArrayBuffer(s.length);
    var view = new Uint8Array(buf);
    for (var i = 0; i < s.length; i++)
        view[i] = s.charCodeAt(i) & 0xFF;
    return buf;

}
function getContentExcel(data) {
    var content = new Array();
    var allcommandes = [];
    for (var i = 1; i < data.length; i++) {
        allcommandes.push(data[i]);
    }

    var body = [];

    /**
     *boucle pour l'entete du tableau
     */

    var header = [{v: "Nom", t: "s", s: {fill: {fgColor: {rgb: "D3D3D3"}}, font: {name: 'Arial', sz: 50, bold: true}, alignment: {horizontal: 'center'}}}];
    header.push("Total");
    for (var j = 0; j < allcommandes[0].commandes.length; j++) {
        var cmd = allcommandes[0].commandes[j];
        header.push(cmd.code);
    }
    content.push(["FEUILLE DE ROUTE DU " + data[0].datedebut + " AU " + data[0].datefin]);
    content.push(header);

    /**
     * boucle pour la premiere ligne du tableau
     */
    var total = [];
    var firstT = [];
    firstT.push("Total");
    var Gtotal = 0;
    var alltotal = [];

    var temp = [];
    for (var j = 0; j < allcommandes[0].commandes.length; j++) {
        var cmd = allcommandes[0].commandes[j];
        temp.push({
            qte: 0,
            id: cmd.id,
            code: cmd.code
        });
    }
    for (var l = 0; l < allcommandes.length; l++) {
        for (var j = 0; j < allcommandes[l].commandes.length; j++) {
            var cmd1 = allcommandes[l].commandes[j];
            for (var f = 0; f < temp.length; f++) {
                if (cmd1.id === temp[f].id) {
                    temp[f].qte += cmd1.quantite;
                }
            }
        }
    }

    var lastt = 0;
    for (var f = 0; f < temp.length; f++) {

        lastt += temp[f].qte;

    }
    firstT.push(lastt);
    for (var f = 0; f < temp.length; f++) {
        firstT.push(temp[f].qte);

    }
    content.push(firstT);
    /**
     * boucle pour les lignes du tableau
     */
    for (var l = 0; l < allcommandes.length; l++) {
        var ligne = [];

        ligne.push(allcommandes[l].nom);
        var t = 0;
        for (var j = 0; j < allcommandes[l].commandes.length; j++) {
            var cmd = allcommandes[l].commandes[j];

            t += cmd.quantite;
        }
        ligne.push(t);
        for (var f = 0; f < temp.length; f++) {
            for (var j = 0; j < allcommandes[l].commandes.length; j++) {
                var cmd1 = allcommandes[l].commandes[j];

                if (cmd1.id === temp[f].id) {
                    ligne.push(cmd1.quantite);
                }
            }

        }

        content.push(ligne);
    }


    return content;
}
$(document).ready(function () {
    $(".print_feuille_route_excel").click(function (e) {
        e.preventDefault();
        var data = JSON.parse(localStorage.getItem("data"));
        if (data.length > 1) {
            var allcommandes = getContentExcel(data);
            var wb = XLSX.utils.book_new();
            wb.Props = {
                Title: "Feuille de route",
                Subject: "Feuille de route",
                Author: "Lalanda",
                Company: "EH2S",
                Version: "1.2.6",
                CreatedDate: new Date()
            };
            wb.SheetNames.push("Feuille de route");

            var ws_data = allcommandes;
            //var ws = XLSX.utils.json_to_sheet(ws_data);
            var ws = XLSX.utils.aoa_to_sheet(ws_data);
            wb.Sheets["Feuille de route"] = ws;
            var wbout = XLSX.write(wb, {bookType: 'xlsx', type: 'binary'});
            var name = "feuille_de_route_du_" + data[0].datedebut + "_au_" + data[0].datefin + ".xlsx";
            saveAs(new Blob([s2ab(wbout)], {type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8"}), name);

        } else {

        }

    });
});

