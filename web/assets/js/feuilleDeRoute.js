/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




$(document).ready(function () {
    function getDate() {
        var date = new Date();
        var jour = date.getDate();
        var mois = (date.getMonth() + 1);
        var heure = date.getHours();
        var min = date.getMinutes();
        var sec = date.getSeconds();
        if ((date.getDate() + 1) < 10) {
            jour = "0" + date.getDate();
        }

        if ((date.getMonth() + 1) < 10) {
            mois = "0" + (date.getMonth() + 1);
        }
        if ((heure + 1) < 10) {
            heure = "0" + (heure + 1);
        }
        if ((min + 1) < 10) {
            min = "0" + (min + 1);
        }
        if ((sec + 1) < 10) {
            sec = "0" + (sec + 1);
        }
        return jour + "/" + mois + "/" + date.getFullYear() + "  " + heure + ":" + min + ":" + sec;
    }
    function getContent(data) {
        var content = new Array();
        var img = "";
        if (data[0].logosociete) {
            img = {
                // if you specify width, image will scale proportionally
                image: 'data:image/png;base64,' + data[0].logosociete,
                width: 70,
                height: 70,
                style: 'img'

            };
        } else {
            img = {
                // if you specify width, image will scale proportionally
                text: '',
                width: 70,
                height: 70,
                style: 'img'

            };
        }
        content.push({
            style: 'headertable',
            table: {
                widths: ["*", "auto", "*"],
                body: [
                    [
                        img,
                        [
                            {text: data[0].nomsociete, style: 'header_center_top'},
                            {text: data[0].adressesociete, style: 'header_center_bottom', alignment: 'center'},
                            {text: " " + data[0].mailsociete, alignment: 'center', margin: [20, 5, 0, 0]}],

                        {text: "", style: 'subheader'}
                    ]
                ]
            },
            layout: {
                hLineWidth: function (i, node) {
                    return (i === 0 || i === node.table.body.length) ? 2 : 1;
                },
                vLineWidth: function (i, node) {
                    return (i === 0 || i === node.table.widths.length) ? 2 : 1;
                },
                hLineColor: function (i, node) {
                    return (i === 0 || i === node.table.body.length) ? 'black' : 'white';
                },
                vLineColor: function (i, node) {
                    return (i === 0 || i === node.table.widths.length) ? 'black' : 'white';
                }
            }
        });

        content.push({
            text: " FEUILLE DE ROUTE", color: 'red', style: 'header_center_top',
            decoration: 'underline',
            fontSize: 18
        });
        content.push({
            text: "prévente du " + data[0].datedebut + " au " + data[0].datefin, style: 'header_center_top'
        });
        var allcommandes = [];
        for (var i = 1; i < data.length; i++) {
            allcommandes.push(data[i]);
        }

        var width = ["auto"];
        for (var k = 0; k < allcommandes[0].commandes.length; k++) {
            width.push(~~(600/allcommandes[0].commandes.length));
        }
        width.push("auto");
        var body = [];

        /**
         *boucle pour l'entete du tableau
         */

        var header = [" "];
        header.push({
            text: "Total",
            bold: true,
            fontSize: 6,
            color:"red"
        });
        for (var j = 0; j < allcommandes[0].commandes.length; j++) {
            var cmd = allcommandes[0].commandes[j];
            header.push({
                text: cmd.code,
                fontSize: 6,
                color: 'blue'
            });
        }
        body.push(header);

        /**
         * boucle pour la premiere ligne du tableau
         */
        var total = [];
        var firstT = [];
        firstT.push({
            text: "Total",
            bold: true,
            fontSize: 14,
            color:"green"
        });
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
        firstT.push({
            text: " " + lastt,
            bold: true,
            color:"#ff14ed"
        });
        for (var f = 0; f < temp.length; f++) {
            firstT.push({
                text: " " + temp[f].qte,
                bold: true,
                color:"green"
            });

        }
        body.push(firstT);
        /**
         * boucle pour les lignes du tableau
         */
        for (var l = 0; l < allcommandes.length; l++) {
            var ligne = [];

            ligne.push({
                text: allcommandes[l].nom,
                color: 'blue'
            });
            var t = 0;
            for (var j = 0; j < allcommandes[l].commandes.length; j++) {
                var cmd = allcommandes[l].commandes[j];

                t += cmd.quantite;
            }
            ligne.push({
                text: " " + t,
                bold: true,
                color:"red"
            });
            for (var f = 0; f < temp.length; f++) {
                for (var j = 0; j < allcommandes[l].commandes.length; j++) {
                    var cmd1 = allcommandes[l].commandes[j];

                    if (cmd1.id === temp[f].id) {
                        ligne.push({
                            text: " " + cmd1.quantite
                        });
                    }
                }

            }

            body.push(ligne);
        }


//width
        content.push({
            style: 'headertable',
            table: {
                widths: width,
                body: body
                
            },
            layout: {
                hLineWidth: function (i, node) {
                    return (i === 0 || i === node.table.body.length) ? 2 : 1;
                },
                vLineWidth: function (i, node) {
                    return (i === 0 || i === node.table.widths.length) ? 2 : 1;
                },
                hLineColor: function (i, node) {
                    return (i === 0 || i === node.table.body.length) ? 'black' : 'black';
                },
                vLineColor: function (i, node) {
                    return (i === 0 || i === node.table.widths.length) ? 'black' : 'black';
                }
            }
        });

        return content;
    }
    function PrintFeuilleDeRoute(data) {
        var docDefinition;


        docDefinition = {
            info: {
                title: 'OCMANAGER DOCUMENT',
                author: 'EH2S software solution',
                subject: 'subject of document',
                keywords: 'keywords for document'
            },
            pageMargins: [20, 30, 20, 30],
            pageSize: 'A4',
            pageOrientation: 'landscape',
            watermark: {text: 'OCMANAGER', color: 'blue', opacity: 0.1, bold: true, italics: false},
//            header: function (currentPage, pageCount) {
//                var tel = "";
//                if (data.telsociete) {
//                    tel = ",Tél :" + data[0].telsociete;
//                }
//                var tfoo = {
//                    columns: [
//                        {text: "Email :" + data[0].mailsociete + tel, alignment: 'center', margin: [20, 5, 0, 0]}
//
//
//
//                    ]};
//                return tfoo;
//            },
            footer: function (currentPage, pageCount) {
                var tfoo = {
                    columns: [
                        {text: "" + getDate(), alignment: 'left', style: 'footerleft'},
                        {text: " ", alignment: 'center', style: 'footercenter', link: 'https://www.eh2s.com'},
                        {text: currentPage.toString() + ' / ' + pageCount, alignment: 'right', style: 'footer'}


                    ]};
                return tfoo;
            },
            content: getContent(data),
            styles: {
                img: {
                    margin: [0, 0, 0, 0]
                },
                header_center_top: {
                    fontSize: 12,
                    bold: true,
                    margin: [35, 5, 0, 10],
                    alignment: 'center'
                },
                header_center_bottom: {
                    bold: false,
                    margin: [0, 0, 0, 0]
                },
                subheader: {
                    fontSize: 12,
                    bold: true,
                    margin: [80, 0, 0, 0]
                },
                headertable: {
                    margin: [0, 20, 0, 10],
                    fontSize: 6
                },
                title: {
                    fontSize: 12,
                    bold: true,
                    alignment: 'center'
                },
                subtitle: {
                    fontSize: 10,
                    bold: false,
                    margin: [170, 10, 0, 5]
                },
                tableExample: {
                    margin: [0, 5, 0, 0]
                },
                tableHeader: {
                    bold: true,
                    fontSize: 10


                },
                tableFooter: {
                    bold: true,
                    fontSize: 12,
                    margin: [0, 7, 0, 10]


                },
                tableContent: {
                    bold: false,
                    fontSize: 9,
                    margin: [0, 0, 0, 0]


                },
                footer: {
                    bold: true,
                    fontSize: 8,
                    margin: [0, 0, 20, 0]


                },
                footerleft: {
                    bold: true,
                    fontSize: 8,
                    margin: [20, 0, 0, 0]


                },
                footercenter: {
                    bold: true,
                    fontSize: 8,
                    margin: [0, 0, 0, 0]



                }
            },
            defaultStyle: {
                // alignment: 'justify'
            }
        };
       // 
        pdfMake.createPdf(docDefinition).open();
    }




    $(".print_feuille_route").click(function (e) {
        e.preventDefault();
        var data = JSON.parse(localStorage.getItem("data"));
        if (data.length > 1) {
            PrintFeuilleDeRoute(data);
        } else {

        }

    });


    $(".feuille_route").submit(function (e) {
        e.preventDefault();
        var form = $(this).serialize();
        var interval = $(this).find("[name=interval]").val();
        var etat = $(this).find("[name=etat]").find("option:selected").val();
        $.ajax({
            type: "POST",
            url: 'gestionCommandes?action=model&model=getImpressionFeuilleDeRoute&q=commande/validation&interval=' + interval+'&etat='+etat,
            data: {},
            success: function (data) {
                localStorage.setItem("data", JSON.stringify(data));
                if (data.length > 1) {
                    localStorage.setItem("data", JSON.stringify(data));
                  $(".print_feuille_route").removeClass("hidden");
                    $(".print_feuille_route_excel").removeClass("hidden");
                } else {
                    $(".print_feuille_route").addClass("hidden");
                    toastr.error("Aucune commande sur cette période", "Alert", {"progressBar": true, "closeButton": true, "showMethod": "slideDown", "hideMethod": "slideUp"});
                }
            },
            dataType: 'json',
            processData: false,
            contentType: false,
            cache: false
        });
    });
});