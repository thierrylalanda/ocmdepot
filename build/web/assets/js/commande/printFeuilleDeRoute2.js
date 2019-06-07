/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
    function splideName(name) {
        var NameArray = name.split(" ");
        var name = NameArray[1];
//        if (NameArray.length > 2) {
//            name += "." + NameArray[2][0].toUpperCase();
//        }
        return name;
    }
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
    function PaginateContent(content, allcommandes, isEnd) {
        var width = ["auto"];
        var taille = allcommandes.length <= 20 ? allcommandes.length : 20;
        for (var k = 0; k <= taille; k++) {
            //  ~~(500 / taille)
            width.push("auto");
        }
        var body = [];

        /**
         *boucle pour l'entete du tableau
         */

        var header = [" "];
        header.push({
            text: "Total",
            bold: true,
            fontSize: 9,
            color: "red", alignment: 'center'
        });
        for (var j = 0; j < taille; j++) {
            var cmd = allcommandes[j];
            header.push({
                text: splideName(cmd.nom),
                fontSize: 6
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
            fontSize: 9,
            color: "green", alignment: 'center'
        });
        var Gtotal = 0;
        var alltotal = [];
        var articles = allcommandes[0].commandes.map(function (cmd) {
            return {nom: cmd.article, id: cmd.id, code: cmd.code, qte: 0, users: []};
        });
        var articlesL = [];
        for (var t = 0; t < taille; t++) {
            var TL = 0;
            for (var p = 0; p < allcommandes[t].commandes.length; p++) {
                TL += allcommandes[t].commandes[p].quantite;
            }
            articlesL.push({id: t, qte: TL});
        }
        var temp = [];
        for (var j = 0; j < taille; j++) {
            for (var l = 0; l < allcommandes[j].commandes.length; l++) {
                var art = allcommandes[j].commandes[l];
                for (var m = 0; m < articles.length; m++) {
                    if (articles[m].id === art.id) {
                        Gtotal += art.quantite;
                        articles[m].qte += art.quantite;
                        articles[m].users.push({nom: allcommandes[j].nom, qte: art.quantite});
                    }
                }
            }
            var cmd = allcommandes[0].commandes[j];
            temp.push({
                qte: 0,
                id: cmd.id,
                code: cmd.code
            });
        }

        firstT.push({
            text: " " + Gtotal,
            bold: true,
            color: "#ff14ed", alignment: 'center'
        });
        for (var f = 0; f < articlesL.length; f++) {
            firstT.push({
                text: " " + articlesL[f].qte,
                bold: true,
                color: "green", alignment: 'center'
            });

        }

        body.push(firstT);
        //retirer les articles donc la quantite en 0
        var articleTemp = articles.filter(function (elt) {
            return elt.qte !== 0;
        });
        /**
         * boucle pour les lignes du tableau
         */

        for (var q = 0; q < articleTemp.length; q++) {
            var ligne = [];
            ligne.push({
                text: " " + articleTemp[q].code,
                bold: true,
                color: "red", alignment: 'center'
            });
            ligne.push({
                text: " " + articleTemp[q].qte,
                bold: true,
                color: "green", alignment: 'center'
            });
            for (var v = 0; v < articleTemp[q].users.length; v++) {
                ligne.push({
                    text: " " + articleTemp[q].users[v].qte,
                    bold: false, alignment: 'center'
                });
            }

            body.push(ligne);
        }
        content.push({
            columns: [
                {
                    // auto-sized columns have their widths based on their content
                    width: "*",
                    text: ''
                },
                {
                    // fixed width
                    width: 'auto',
                    style: 'tableContent',
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
                },
                {
                    // % width
                    width: "*",
                    text: ''
                }
            ]
        });
        if (!isEnd) {
            content.push({
                text: "\n Suite \n", alignment: 'center'
            });
//        content.push({
//            text: '', pageBreak: 'after'
//        });
        }

//        content.push({
//            style: 'tableContent',
//            table: {
//                widths: width,
//                body: body
//
//            },
//            layout: {
//                hLineWidth: function (i, node) {
//                    return (i === 0 || i === node.table.body.length) ? 2 : 1;
//                },
//                vLineWidth: function (i, node) {
//                    return (i === 0 || i === node.table.widths.length) ? 2 : 1;
//                },
//                hLineColor: function (i, node) {
//                    return (i === 0 || i === node.table.body.length) ? 'black' : 'black';
//                },
//                vLineColor: function (i, node) {
//                    return (i === 0 || i === node.table.widths.length) ? 'black' : 'black';
//                }
//            }
//        });
        return content;
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
                        {text: data.nomsociete, style: 'header_center_top'},
                        {text: "BP : " + data.codepostalsociete+ " \t Tel : " + data.telsociete, style: 'header_center_bottom', alignment: 'center'}],
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
            text: " FEUILLE DE ROUTE DU " + data[0].datedebut + " AU " + data[0].datefin, color: 'red', style: 'header_center_top',
            decoration: 'underline',
            fontSize: 14
        });
        if (localStorage.getItem("tourners")) {
            content.push({
                text: " Tournées " + JSON.parse(localStorage.getItem("tourners")).join(), color: 'red', style: 'header_center_top',
                decoration: 'underline',
                fontSize: 12
            });
        }
        var allcommandes = [];
        for (var i = 1; i < data.length; i++) {
            allcommandes.push(data[i]);
        }
        var allContent = [];
        var numberTableau = 1;
        if (allcommandes.length <= 20) {
            allContent.push(allcommandes);
        } else {
            numberTableau = Math.ceil(allcommandes.length / 20);
            var start = 0;
            var end = 20;
            for (var i = 0; i < numberTableau; i++) {
                allContent.push(allcommandes.slice(start, end));
                start += 20;
                end += 20;
            }
        }


        for (var i = 0; i < allContent.length; i++) {
            var isEnd = i === allContent.length - 1 ? true : false;
            content = PaginateContent(content, allContent[i], isEnd);
        }



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
            pageMargins: [10, 30, 20, 30],
            pageSize: 'A4',
            pageOrientation: 'landscape',
            // watermark: {text: 'OCMANAGER', color: 'blue', opacity: 0.1, bold: true, italics: false},
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
                    margin: [20, 5, 0, 10],
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
                    margin: [0, -10, 0, 10],
                    fontSize: 6
                            //alignment: 'center'
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
                    margin: [0, 0, 0, 0],
                    alignment: 'center'
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
        $(".print_feuille_route").addClass("hidden");
        $(".print_feuille_route_excel").addClass("hidden");
        var form = $(this).serialize();
        var interval = $(this).find("[name=interval]").val();
        var etat = $(this).find("[name=etat]").find("option:selected").val();
        var client = $(this).find("[name=client]").val();
        var url = client.length === 0 ? 'gestionCommandes?action=model&model=getImpressionFeuilleDeRoute&q=commande/validation&interval=' + interval + '&etat=' + etat : 'gestionCommandes?action=model&model=getImpressionFeuilleDeRouteByClient&q=commande/validation&interval=' + interval + '&etat=' + etat + '&client=' + client;
        localStorage.removeItem("tourners");
        $.ajax({
            type: "POST",
            url: url,
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

    $(".feuille_route_tourner").submit(function (e) {
        e.preventDefault();
        $(".print_feuille_route").addClass("hidden");
        $(".print_feuille_route_excel").addClass("hidden");
        var form = $(this).serialize();
        var interval = $(this).find("[name=interval]").val();
        var etat = $(this).find("[name=etat]").find("option:selected").val();
        var tourner = $(this).find("[name=tourner]").val();

        if (tourner.length === 0) {
            toastr.error("Veuillez selectionner au moins une tournée", "Alert", {"progressBar": true, "closeButton": true, "showMethod": "slideDown", "hideMethod": "slideUp"});

        } else {
            var url = 'gestionCommandes?action=model&model=getImpressionFeuilleDeRouteByTourner&q=commande/validation&interval=' + interval + '&etat=' + etat + '&tourner=' + tourner;
            var allTr = $(this).find("[name=tourner]").find("option:selected").text();
            var tt = allTr.split(" ");
            tt.shift();
            localStorage.removeItem("tourners");
            $.ajax({
                type: "POST",
                url: url,
                data: {},
                success: function (data) {
                    localStorage.setItem("data", JSON.stringify(data));
                    if (data.length > 1) {
                        localStorage.setItem("data", JSON.stringify(data));
                        localStorage.setItem("tourners", JSON.stringify(tt));
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
        }

    });
});

