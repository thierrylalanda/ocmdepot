/*=========================================================================================
 File Name: mainscript.js
 Description: main file javascript
 ----------------------------------------------------------------------------------------
 Item Name: CCMANAGR
 Version: .0
 Author: EH2S
 Author URL: http://www.eh2s.com
 ==========================================================================================*/

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

function formatNumber(number) {
    var formated = "";
    var format = number.toString();
    var count = 0;
    for (var i = format.length - 1; i >= 0; i--) {
        if (count % 3 === 0) {
            if (i === format.length - 1) {
                formated = format[i];
            } else {
                formated = format[i] + " " + formated;
            }


        } else {
            formated = format[i] + formated;
        }
        count++;
    }
    return formated;
}
function getContentOneCommande(data) {
    var content = new Array();
    var region = $(".notification").attr("region") !== undefined ? $(".notification").attr("region") : "";
    var img = "";
    if (data.logosociete) {
        img = {
            // if you specify width, image will scale proportionally
            image: 'data:image/png;base64,' + data.logosociete,
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
                        {text: "BP : " + data.codepostalsociete + " \t Tel : " + data.telsociete, style: 'header_center_bottom', alignment: 'center'}],
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

    if (data.codeetat === 200 || data.codeetat === 201 || data.codeetat === 300) {
        var header = {
            margin: [30, 10, 0, 10],
            text: [
                {text: 'FACTURE \t N° ' + data.numero, fontSize: 15, italics: true, bold: true, alignment: 'center'}

            ]
        };
        content.push(header);
    } else {
        var header = {
            margin: [30, 10, 0, 10],
            text: [
                {text: 'PROFORMAT \t N° ' + data.numero, fontSize: 15, italics: true, bold: true, alignment: 'center'}

            ]
        };
        content.push(header);

    }

    var colun = {
        columns: [
            {
                width: '*',
                text: [
                    {text: 'Nom : \t', bold: true},
                    {text: data.nomclient.replace("null", " ") + ' \n'},
                    {text: 'Tel : \t', bold: true},
                    {text: data.telclient},
                    {text: '\n Code Client : \t', bold: true},
                    {text: data.codeclient ? data.codeclient : "" + '\n'}
                ]

            },
            {
                width: 70,
                text: ''
            },
            {
                width: '*',
                text: [
                    {text: 'Crée le : \t', bold: true},
                    {text: data.datecreation + ' \n'},
                    {text: 'Echeance : \t', bold: true},
                    {text: data.dateecheance + ' \n'},
                    {text: 'Statut : \t', bold: true},
                    {text: data.etat + ' \n'}
                ]
            }
        ]

    };
    content.push(colun);
    if (data.tourner) {
        var colun = {
            columns: [
                {
                    width: '*',
                    text: [
                        {text: 'Tourner ' + data.tourner + ' \n', bold: true, alignment: 'left', color: "#363636"}
                    ]

                },
                {
                    width: 70,
                    text: ''
                }
            ]

        };
        content.push(colun);
    }




    var total = 0;
    var totalP = 0;
    var bodyCMD = [
        [
            {text: "Code", bold: true},
            {text: "Article", bold: true},
            {text: "P.U", bold: true},
            {text: "Qte", bold: true},
            {text: "P.T", bold: true}]
    ];
    var trueArticle = data.articles.filter(function (elt) {
        return !elt.code.includes('VRAP') && !elt.code.includes('VRACC');
    });
    var falseArticle = data.articles.filter(function (elt) {
        return elt.code.includes('VRAP') || elt.code.includes('VRACC');
    });
    var totaltable = 0;
    for (var i = 0; i < trueArticle.length; i++) {
        var cmd = [];
        var item = trueArticle[i];
        cmd.push({text: "" + item.code, fontSize: 9});
        cmd.push({text: item.article, fontSize: 9});
        cmd.push({text: "" + formatNumber(item.pu), fontSize: 9});
        cmd.push({text: "" + formatNumber(item.quantite), fontSize: 9});
        cmd.push({text: "" + formatNumber(item.total), fontSize: 9});
        totaltable += item.total;
        totalP += item.quantite;
        bodyCMD.push(cmd);
    }
    var cmd = [];
    cmd.push({text: " "});
    cmd.push({text: "", "bold": true});
    cmd.push({text: "Total", "bold": true});
    cmd.push({text: "" + formatNumber(totalP), "bold": true});
    cmd.push({text: "" + formatNumber(totaltable), "bold": true});
    bodyCMD.push(cmd);
    var description = {
        style: 'headertable',
        table: {
            widths: ["auto", "*", "auto", "auto", "auto"],
            body: bodyCMD,
            pageBreak: 'after', margin: [0, 0, 20, 0]
        }
    };

    var total = data.total;
    if (data.transport) {
        total = data.total - data.transport;
    }
    const pourcentage = 1.2425;
    var totalHT = Math.round(total / pourcentage);
    var tva = Math.round(totalHT * (19.25 / 100));
    var prov = Math.round(totalHT * (5 / 100));

    content.push(description);
    content.push({"text": "Détails Emballage", "bold": true});
    var total1 = 0;
    var totalP1 = 0;
    var bodyCMD = [
        [
            {text: "Code", bold: true},
            {text: "Article", bold: true},
            {text: "P.U", bold: true},
            {text: "Qte", bold: true},
            {text: "P.T", bold: true}]
    ];
    if (data.emballages && data.emballages.length !== 0) {
        var totalemb = 0;
        var qteemb = 0;
        for (var i = 0; i < data.emballages.length; i++) {
            var cmd = [];
            var item = data.emballages[i];
            cmd.push({text: "" + item.code, fontSize: 9});
            cmd.push({text: item.article, fontSize: 9});
            cmd.push({text: "" + item.pu, fontSize: 9});
            cmd.push({text: "" + item.quantite, fontSize: 9});
            cmd.push({text: "" + item.total, fontSize: 9});
            totalemb += item.total;
            qteemb += item.quantite;
            bodyCMD.push(cmd);
        }
        var cmd = [];
        cmd.push({text: " ", fontSize: 9});
        cmd.push({text: " ", fontSize: 9});
        cmd.push({text: "Total", fontSize: 9});
        cmd.push({text: qteemb, fontSize: 9});
        cmd.push({text: totalemb, fontSize: 9});
        bodyCMD.push(cmd);
        var description = {
            style: 'headertable',
            table: {
                widths: ["auto", "*", "auto", "auto", "auto"],
                body: bodyCMD,
                pageBreak: 'after', margin: [0, 0, 20, 0]
            }
        };
        content.push(description);
    } else {
        if (data.casier) {
            var cmd = [];
            cmd.push({text: "XXXXX", fontSize: 9});
            cmd.push({text: "Consignation", fontSize: 9});
            cmd.push({text: "3600", fontSize: 9});
            cmd.push({text: "" + data.casier, fontSize: 9});
            cmd.push({text: "" + data.casier * 3600, fontSize: 9});
            total1 += data.casier * 3600;
            totalP1 += data.casier;
            bodyCMD.push(cmd);
            var cmd = [];
            // cmd.push({text: item.categorie});
            cmd.push({text: "XXXXX", fontSize: 9});
            cmd.push({text: "Déconsignation", fontSize: 9});
            cmd.push({text: "3600", fontSize: 9});
            cmd.push({text: "-" + data.casier, fontSize: 9});
            cmd.push({text: "-" + data.casier * 3600, fontSize: 9});
            total1 += -data.casier * 3600;
            totalP1 += -data.casier;
            bodyCMD.push(cmd);
            var cmd = [];
            cmd.push({text: " "});
            cmd.push({text: "", "bold": true});
            cmd.push({text: "Total", "bold": true});
            cmd.push({text: totalP1, "bold": true});
            cmd.push({text: total1, "bold": true});
            bodyCMD.push(cmd);
            var description = {
                style: 'headertable',
                table: {
                    widths: ["auto", "*", "auto", "auto", "auto"],
                    body: bodyCMD,
                    pageBreak: 'after', margin: [0, 0, 20, 0]
                }
            };
            content.push(description);
        }
    }

    content.push({text: "\n**************************************************************************************************************\n"});
    var transport = 0;
    if (data.transport) {
        transport = data.transport;
    }
    var colun = {
        columns: [
            {
                width: '*',
                alignment: 'left',
                columns: []
            },
            {
                width: '*',
                columns: [
                    {width: 150, text: [
//                            {text: 'Montant HT\n'},
//                            {text: 'Retenu TVA(19,25%)  \n'},
//                            {text: 'PSA(5%)  \n'},
                            {text: 'Montant \n'},
                            {text: 'Transport'}
                        ]},
                    {width: 100, text: [
//                            {text: formatNumber(totalHT) + ' FCFA \n', "bold": true, alignment: 'right'},
//                            {text: formatNumber(tva) + ' FCFA \n', "bold": true, alignment: 'right'},
//                            {text: formatNumber(prov) + ' FCFA \n', "bold": true, alignment: 'right'},
                            {text: formatNumber(total) + ' FCFA \n', "bold": true, alignment: 'right'},
                            {text: formatNumber(transport) + ' FCFA', "bold": true, alignment: 'right'}
                        ]}]
            }
        ]

    };
    content.push(colun);
    if (data.avancePaiement || data.restePaiement === 1) {
        var colun = {
            columns: [
                {
                    width: '*',
                    columns: [
                        {width: 150, text: [
                                {text: 'Net à payer\n', fontSize: 15, "bold": true, decoration: 'underline'},
                                {text: 'Avance  \n'},
                                {text: 'Reste A Payer  \n'}
                            ]},
                        {width: 100, text: [
                                {text: formatNumber(total + transport) + ' FCFA \n', "bold": true, alignment: 'right'},
                                {text: formatNumber(data.avancePaiement) + ' FCFA \n', "bold": true, alignment: 'right'},
                                {text: formatNumber(data.restePaiement) + ' FCFA \n', "bold": true, alignment: 'right'}
                            ]}]
                },
                {
                    width: '*',
                    alignment: 'left',
                    columns: []
                }
            ]

        };
        content.push(colun);
    } else {
        content.push({text: 'Net à payer ' + formatNumber(total + transport) + ' FCFA\n', fontSize: 15, "bold": true, alignment: 'left', decoration: 'underline'});
    }
    if (data.gestmarge && data.gestmarge === 1 && 1 === 2) {
        content.push({text: "\n**************************************************************************************************************\n\n"});
        var colun = {
            columns: [
                {
                    width: '*',
                    alignment: 'left',
                    columns: [
                        {width: 150, text: [
                                {text: 'Ristourne encours \n', "bold": true},
                                {text: 'Solde Ristourne encours', "bold": true}
                            ]},
                        {width: 100, text: [
                                {text: data.totalMargeClient + " FCFA\n", "bold": true, alignment: 'right'},
                                {text: data.allMargeClient + " FCFA", "bold": true, alignment: 'right'}
                            ]}]
                },
                {
                    width: 'auto',
                    text: ''
                },
                {
                    width: '*',
                    columns: []
                }
            ]

        };
        content.push(colun);
        content.push({text: "\n"});
    }
    if (data.gestcassier === 1 && data.totalCasier !== 0) {
        content.push({text: "\n**************************************************************************************************************\n"});
        var colun = {
            columns: [
                {
                    width: '*',
                    alignment: 'left',
                    columns: [
                        {width: 150, text: [
                                {text: 'Total Emballage \n', "bold": true},
                                {text: 'Total Consigne', "bold": true}
                            ]},
                        {width: 100, text: [
                                {text: data.totalCasierEncour + " \n", "bold": true, alignment: 'right'},
                                {text: data.totalCasier + " ", "bold": true, alignment: 'right'}
                            ]}]
                },
                {
                    width: 'auto',
                    text: ''
                },
                {
                    width: '*',
                    columns: []
                }
            ]

        };
        content.push(colun);
        content.push({text: "\n"});
    }
    var colun = {
        columns: [
            {
                width: 100,
                text: '', "bold": true

            },
            {
                width: '*',
                style: 'headertable',
                table: {
                    widths: ["*", "*"],
                    body: [
                        [{text: "Vendeur", "bold": true}, {text: "Client", "bold": true}]
                                // [{text:"\n\n\n\n\n"},{text:" "},{text:" "}]
                    ],
                    pageBreak: 'after', margin: [0, 0, 20, 0]
                }
            },
            {
                width: 100,
                alignment: 'right',
                text: ' ', "bold": true
            }
        ]

    };
    content.push(colun);
    // content.push({qr: "https://www.eh2s.com", alignment: 'right', fit: 50});
    return content;
}
function getContentOneIncident(data) {
    var content = new Array();

    var region = $(".notification").attr("region") !== undefined ? $(".notification").attr("region") : "";
    var img = "";
    if (data.logosociete) {
        img = {
            // if you specify width, image will scale proportionally
            image: 'data:image/png;base64,' + data.logosociete,
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
                        {text: data.mailsociete + "\t Tel : " + data.telsociete, style: 'header_center_bottom', alignment: 'center'}],
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


    var header = {
        margin: [30, 10, 0, 10],
        text: [
            {text: 'FICHE D\'INCIDENT\t N° ' + data.numerosticket, fontSize: 15, italics: true, bold: true, alignment: 'center'}

        ]
    };
    content.push(header);
    var colun = {
        columns: [
            {
                width: '*',
                text: [
                    {text: 'Info Client \n', bold: true, alignment: 'center', color: "#363636", margin: [0, 10, 0, 40]},
                    {text: 'Nom : \t', bold: true},
                    {text: data.nomclient + ' \n'},
                    {text: 'Tel : \t', bold: true},
                    {text: data.telclient},
                    {text: '\n Email : \t', bold: true},
                    {text: data.mailclient + '\n'}
                ]

            },
            {
                width: 70,
                text: ''
            },
            {
                width: '*',
                text: [
                    {text: 'Detail Incident \n', bold: true, alignment: 'center', color: "#363636"},
                    {text: 'Crée le : \t', bold: true},
                    {text: data.datecreationticket + ' \n'},
                    {text: 'Par : \t', bold: true},
                    {text: data.initiateurticket + ' \n'},
                    {text: 'Responsable : \t', bold: true},
                    {text: data.responsable + ' \n'}
                ]
            }
        ]

    };
    content.push(colun);

    var colun = {
        columns: [
            {
                width: '*',
                text: [
                    {text: 'Localisation Client \n', bold: true, alignment: 'center', color: "#363636", margin: [0, 10, 0, 40]},
                    {text: 'Region : \t', bold: true},
                    {text: data.regionclient + ' \n'},
                    {text: 'District : \t', bold: true},
                    {text: data.districtclient + ' \n'},
                    {text: 'Secteur : \t', bold: true},
                    {text: data.secteurclient + ' \n'},
                    {text: 'Type de client : \t', bold: true},
                    {text: data.typeclient + ' \n'}
                ]

            },
            {
                width: 70,
                text: ''
            },
            {
                width: '*',
                text: [
                    {text: 'Autres Details \n', bold: true, alignment: 'center', color: "#363636"},
                    {text: 'Delais : \t', bold: true},
                    {text: data.dateecheanceticket + ' \n'},
                    {text: 'Statut : \t', bold: true},
                    {text: data.statutticket + ' \n'},
                    {text: 'Priorite : \t', bold: true},
                    {text: data.prioriteticket + ' \n'},
                    {text: 'Dans Delais : \t', bold: true},
                    {text: data.delaisticket + ' \n'}
                ]
            }
        ]

    };
    content.push(colun);
    content.push({text: '\n\nObjet : \t' + data.objetticket + ' \n\n', bold: true});
    content.push({text: "Description de l'incident", bold: true});
    var description = {
        style: 'headertable',
        table: {
            widths: ["*"],
            body: [
                [{text: data.descriptionticket + "\n\n\n"}]
            ]
        }
    };
    content.push(description);
    if (data.reponseticket0) {
        content.push({text: "Reponse Ticket", bold: true});
        var description = {
            style: 'headertable',
            table: {
                widths: ["*"],
                body: [
                    [{text: data.reponseticket0 + "\n" + data.datereponseticket0 + "\n\n"}]
                ]
            }
        };
        content.push(description);
    }
    if (data.reponseticket1) {
        content.push({text: "Reponse Ticket", bold: true});
        var description = {
            style: 'headertable',
            table: {
                widths: ["*"],
                body: [
                    [{text: data.reponseticket1 + "\n" + data.datereponseticket1 + "\n\n"}]
                ]
            }
        };
        content.push(description);
    }

    // content.push({qr: "https://www.eh2s.com", alignment: 'right', fit: 50});
    return content;
}

function printCommande(data, pageSize) {
    var docDefinition;
    var firstdata = data[0];
    console.log(firstdata);
    docDefinition = {
        info: {
            title: 'CCMANAGER DOCUMENT',
            author: 'EH2S software solution',
            subject: 'subject of document',
            keywords: 'keywords for document'
        },
        pageMargins: [20, 30, 20, 30],
        pageSize: pageSize,
        header: function (currentPage, pageCount) {
            var tfoo = {
                columns: [
                    {text: "" + getDate(), alignment: 'left', style: 'footerleft'},
                    {text: currentPage.toString() + ' / ' + pageCount, alignment: 'right', style: 'footer'}


                ]};
            return tfoo;
        },
        footer: function (currentPage, pageCount) {
            var tfoo = {
                columns: [
                    {text: "" + getDate(), alignment: 'left', style: 'footerleft'},
                    {text: " ", alignment: 'center', style: 'footercenter', link: 'https://www.eh2s.com'},
                    {text: currentPage.toString() + ' / ' + pageCount, alignment: 'right', style: 'footer'}


                ]};
            return tfoo;
        },
        content: getContentOneCommande(firstdata),
        pageBreakBefore: function (currentNode, followingNodesOnPage, nodesOnNextPage, previousNodesOnPage) {
            return currentNode.headlineLevel === 1 && followingNodesOnPage.length === 0;
        },
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
                margin: [0, 10, 0, 10]
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
    // pdfMake.createPdf(docDefinition).open();
    return  pdfMake.createPdf(docDefinition);
}
function printIncident(data) {
    var docDefinition;
    var firstdata = data[0];

    docDefinition = {
        info: {
            title: 'CCMANAGER DOCUMENT',
            author: 'EH2S software solution',
            subject: 'subject of document',
            keywords: 'keywords for document'
        },
        pageMargins: [20, 30, 20, 30],
        pageSize: 'A4',
        //  watermark: {text: 'OCMANAGER', color: 'blue', opacity: 0.1, bold: true, italics: false},
        header: function (currentPage, pageCount) {
            var tfoo = {
                columns: [
                    {text: "" + getDate(), alignment: 'left', style: 'footerleft'},
                    {text: currentPage.toString() + ' / ' + pageCount, alignment: 'right', style: 'footer'}


                ]};
            return tfoo;
        },
        footer: function (currentPage, pageCount) {
            var tfoo = {
                columns: [
                    {text: "" + getDate(), alignment: 'left', style: 'footerleft'},
                    {text: " ", alignment: 'center', style: 'footercenter', link: 'https://www.eh2s.com'},
                    {text: currentPage.toString() + ' / ' + pageCount, alignment: 'right', style: 'footer'}


                ]};
            return tfoo;
        },
        content: getContentOneIncident(firstdata),
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
                margin: [0, 20, 0, 10]
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
    pdfMake.createPdf(docDefinition).open();
}

function getDataToPrint($id) {
    $.ajax({
        type: "POST",
        url: 'jtzxbkui4528f725jhf?action=model&model=getTicketmodelPrint&q=jgddgg&model=&id=' + $id,
        data: {},
        success: function (data) {
            printIncident(data);
        },
        dataType: 'json'
    });
}

function getDataToPrintCommande($id, type, caisse) {
    const TVA = 19.25;
    const PSA = 5;
    const pourcentage = 1.2425;
    const puCasier = 3600;
    var url = 'gestionCommandes?q=${q}&action=model&model=getCommandeToPrint&id=' + $id;
    if (caisse !== undefined) {
        url = 'gestionCommandes?q=${q}&action=model&model=getCommandeToPrint&fromcaisse=yes&id=' + $id;
    }
    $.ajax({
        type: "POST",
        url: url,
        data: {},
        success: function (data) {
            var pdfObj;
            if (type === "A4") {
                pdfObj = printCommande(data, "A4");
                pdfObj.open();
            } else {

                if (/Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent))
                {

                } else {
                    var url = "";
                    var param = "width=302,height=700,resizable=no,status=no,location=no,toolbar=no,menubar=no";
                    var p = window.open(url, "Printer", param);
                    p.document.body.outerHTML = "";
                    p.document.write(
                            "<html>" +
                            "  <body style=\"font-size:16px; margin: 0; padding: 0;\">"
                            );
                    data.forEach(comm => {
                        p.document.write(
                                "   <center>" +
                            ((comm.logosociete && comm.logosociete !== "") ? "       <img src=\"data:image/png;base64," + comm.logosociete + "\" height='80px'><br>" : " ") +
                                "       " + comm.nomsociete + "<br> " +
                                "      Tel " + comm.telsociete +
                                "       BP : " + comm.codepostalsociete + " " + "<br>" +
                                "   </center>" +
                                "   <h3 style='text-align:center'>" +
                                "     FACTURE N°" + comm.numero +
                                "   </h3>" +
                                "   <pre>" +
                                // "CODE:  " + comm.codeclient + "<br>" +
                                //  "TYPE:" + comm.etat + "<br>" +
                                "Client :  " + comm.nomclient.replace("null", " ") + "<br>" +
                                "DATE: " + comm.dateecheance +
                                
                                "   </pre>" +
                                "   <h3>Produits</h3>" +
                                "   <table border=1 width='100%' cellspacing=0>" +
                                "       <tr>" +
                                "           <td width='60%'>Produit</td>" +
                                "           <td>Qté</td>" +
                                "           <td>PU</td>" +
                                "           <td>Total</td>" +
                                "       <tr>"
                                );
                        var total = 0;
                        comm.articles.forEach(el => {
                            p.document.write(
                                    "<tr>" +
                                    " <td>" + el.code + "</td>" +
                                    " <td>" + el.quantite + "</td><td>" + el.pu + "</td><td>" + el.total + "</td>" +
                                    "<tr>"
                                    );
                            total += el.total;
                        });
                        p.document.write(
                                "<tr>" +
                                " <td colspan=3>Montant total</td>" +
                                " <td>" + total + "</td>" +
                                "<tr>"
                                );
                        p.document.write(
                                "   </table>" +
                                "   <h3>Embalages</h3>" +
                                "   <table border=1 width='100%' cellspacing=0>" +
                                "       <tr>" +
                                "           <td width='60%'>Embalage</td>" +
                                "           <td>Qté</td>" +
                                "           <td>PU</td>" +
                                "           <td>Total</td>" +
                                "       <tr>"
                                );
                        var total2 = 0;
                        var cont = 0;
                        comm.emballages.forEach(el => {
                            p.document.write(
                                    "<tr>" +
                                    " <td>" + el.code + "</td>" +
                                    " <td>" + el.quantite + "</td><td>" + el.pu + "</td><td>" + el.total + "</td>" +
                                    "<tr>"
                                    );
                            total2 += el.total;
                        });
                        p.document.write(
                                "<tr>" +
                                " <td colspan=3>Montant total</td>" +
                                " <td>" + total2 + "</td>" +
                                "<tr>"
                                );
                        total += total2;
                        comm.transport = comm.transport ? comm.transport : 0;
                        var montantHT = Math.round(total / pourcentage);
                        var montantTVA = Math.round(montantHT * TVA / 100);
                        var montantPSA = Math.round(montantHT * PSA / 100);
                        var montantTTC = montantHT + montantTVA + montantPSA;
                        p.document.write(
                                "   </table>" +
                                "   <h3>Détail du colis</h3>" +
                                "   <pre>" +
//                            "Montant HT:        " + montantHT + "<br>" +
//                            "Retenue TVA (" + TVA + "%):" + montantTVA + " FCFA<br>" +
//                            "PSA (" + PSA + "%):          " + montantPSA + " FCFA<br>" +
                                "Montant :       " + total + " FCFA<br>" +
                                "Transport:     " + comm.transport + " FCFA" +
                                "   </pre>");
                        p.document.write(
                                "   <h3 style='text-align:center'>" +
                                "     NET A PAYER " + (total + comm.transport) + "  FCFA" +
                                "   </h3>"
                                );
                        if (comm.avancePaiement || comm.restePaiement === 1) {
                            p.document.write(
                                    "   <pre style='text-align:left'>" +
                                    "     Avance " + (comm.avancePaiement) + "   FCFA" +
                                    "   </pre><br>" +
                                    "   <pre style='text-align:left'>" +
                                    "     Reste A Payer " + (comm.restePaiement) + "   FCFA" +
                                    "   </pre>"
                                    );

                        }
                        if (comm.casier && comm.totalCasier) {
                            p.document.write(
                                    "   <h5>" +
                                    "       Casier encours " + comm.casier + "<br>" +
                                    "       Total Casier " + comm.totalCasier +
                                    "   </h5>");
                        }
                        p.document.write(
                                "<p><strong>MERCI POUR VOTRE CONFIANCE !!!</strong></p>"
                                );
                    });
                    p.document.write(
                            "  </body>" +
                            "</html>"
                            );
                    var pre = p.document.createElement("pre");
                    p.document.body.append(pre);
                    p.print();
                    p.close();
                }

            }


        },
        dataType: 'json'
    });
}
var EventEmitters = new EventEmitter();

$(document).ready(function () {
    var all_year = $(".all_year");
    all_year.attr("class", "select2-size-xs block form-control round");
    all_year.attr("name", "annee");
    var date_debut = 2018;
    var arrayYear = [];
    for (var i = date_debut; i <= new Date().getFullYear(); i++) {
        arrayYear.push(i);
        if (i === new Date().getFullYear()) {
            all_year.prepend($("<option selected>" + i + "</option>"));
        } else {
            all_year.prepend($("<option>" + i + "</option>"));
        }

    }
    $("#seedetailscommande").click(function (e) {
        e.preventDefault();
        $("#detailCommande").modal("show");
    });
    $('.select2-border').each(function (i, obj) {
        var variation = "",
                textVariation = "",
                textColor = "";
        var color = $(this).data('border-color');
        textVariation = $(this).data('text-variation');
        variation = $(this).data('border-variation');
        textColor = $(this).data('text-color');
        if (textVariation !== "") {
            textVariation = " " + textVariation;
        }
        if (variation !== "") {
            variation = " border-" + variation;
        }

        var className = "border-" + color + " " + variation + " " + textColor + textVariation;

        $(this).select2({
            containerCssClass: className
        });
    });

    $('#find_client_tourner').change(function (e) {
        e.preventDefault();
        if ($(this).val() !== "") {
            $(this).parents("form:first").submit();
        }
    });
    /**
     * 
     * @type document.body|Document.body|HTMLElement|Node.body|Node|Element
     * @description recharger automatiquement les pages apres une huit minute d'inactivite
     */


    if ($(".autoloader").attr("autoloader") !== undefined) {
        var body = document.body;
        body.onmousemove = body.onclick = body.onscroll = document.onkeyup = (function () {

            var reload = function () {

                window.location.reload(true);

            },
                    timeout = window.setTimeout(reload, 240000);

            return function () {

                window.clearTimeout(timeout);

                timeout = window.setTimeout(reload, 240000);

            };

        })();

    }

    // Localization
    $('.localeRange').daterangepicker({
        ranges: {
            "Aujourd'hui": [moment(), moment()],
            'Hier': [moment().subtract('days', 1), moment().subtract('days', 1)],
            'Les 7 derniers jours': [moment().subtract('days', 6), moment()],
            "Cette Semaine": [moment().startOf('week').subtract('days', -1), moment().endOf('week').subtract('days', -1)],
            'Les 30 derniers jours': [moment().subtract('days', 29), moment()],
            'Ce mois-ci': [moment().startOf('month'), moment().endOf('month')],
            'le mois dernier': [moment().subtract('month', 1).startOf('month'), moment().subtract('month', 1).endOf('month')],
            'Cette Année': [moment().startOf('year'), moment().endOf('year')]
        },
        locale: {
            format: 'DD/MM/YYYY',
            applyLabel: "Vers l'avant",
            cancelLabel: 'Annulation',
            startLabel: 'Date initiale',
            endLabel: 'Date limite',
            customRangeLabel: 'Sélectionner une date',
            // daysOfWeek: ['Dimanche', 'Lundi', 'Mardi', 'Mercredi', 'Jeudi', 'Vendredi','Samedi'],
            daysOfWeek: ['Di', 'Lu', 'Ma', 'Me', 'Je', 'Ve', 'Sa'],
            monthNames: ['Janvier', 'février', 'Mars', 'Avril', 'Маi', 'Juin', 'Juillet', 'Août', 'Septembre', 'Octobre', 'Novembre', 'Decembre'],
            firstDay: 1
        }
    });
    $('.singledate').daterangepicker({
        singleDatePicker: true,
        showDropdowns: true,
        locale: {
            format: 'DD/MM/YYYY',
            // daysOfWeek: ['Dimanche', 'Lundi', 'Mardi', 'Mercredi', 'Jeudi', 'Vendredi','Samedi'],
            daysOfWeek: ['Di', 'Lu', 'Ma', 'Me', 'Je', 'Ve', 'Sa'],
            monthNames: ['Janvier', 'février', 'Mars', 'Avril', 'Маi', 'Juin', 'Juillet', 'Août', 'Septembre', 'Octobre', 'Novembre', 'Decembre'],
            firstDay: 1
        }
    });
    $('.singledate').keypress(function (e) {
        e.preventDefault();
    });
    /**
     * @envoie du formulaire contenant un element en repetition
     * @argument {type} e description
     */
    $("#submitForm").click(function (e) {
        e.preventDefault();
        var form = ($("#formcomment"));
        var select = $("<select name='commentaire' multiple='multiple' class='hidden'></select>");
        var list = $('.file-repeater').repeaterVal().list_commentaire;
        for (var i = 0; i < list.length; i++) {
            select.append($("<option selected>" + list[i].commentaire + "</option>"));
        }
        form.append(select);
        form.submit();
    });
    $("#rubriqueIncident").change(function (e) {
        e.preventDefault();
        var id = $(this).val();
        $.ajax({
            type: "POST",
            url: 'ticket?action=model&q=jgddgg&model=getSrubrique&id=' + id,
            data: {},
            success: function (data) {
                $("#srubriqueIncident").html("");
                $("#srubriqueIncident").attr("class", "select2-size-xs block form-control round");
                $("#srubriqueIncident").attr("name", "srubrique");
                if (data.length > 0) {
                    for (var i = 0; i < data.length; i++) {
                        if (data[i].name) {
                            $("#srubriqueIncident").append($("<option value='" + data[i].id + "'>" + data[i].name + "</option>"));
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

    });

    /**
     * @description initialisation  de la dual list
     */
    $('.duallistbox-custom-height').bootstrapDualListbox({
        moveOnSelect: false,
        selectorMinimalHeight: 250
    });
    /**
     * 
     * @type Array
     * @description tableau global pour gerer la selection des tableaux
     */
    var ArrayTable = [];

    /**
     * 
     * @returns {mainscript_L12.ManageApp.mainscriptAnonym$0}
     */
    function ManageApp() {
        return {
            /**
             * 
             * @param {String} Type type de message à afficher
             * @param {String} Title titre du message à afficher
             * @param {String} Body le corps du message
             * @returns {undefined}
             */
            Toast: function (Type, Title, Body) {
                switch (Type) {
                    case 'error' :
                        toastr.error(Body, Title, {"progressBar": true, "closeButton": true, "showMethod": "slideDown", "hideMethod": "slideUp", "timeOut": 0});
                        break;
                    case 'warning' :
                        toastr.warning(Body, Title, {"progressBar": true, "closeButton": true, "showMethod": "slideDown", "hideMethod": "slideUp", "timeOut": 0});
                        break;
                    case 'info' :
                        toastr.info(Body, Title, {"progressBar": true, "closeButton": true, "showMethod": "slideDown", "hideMethod": "slideUp", "timeOut": 0});
                        break;
                    case 'success' :
                        toastr.success(Body, Title, {"progressBar": true, "closeButton": true, "showMethod": "slideDown", "hideMethod": "slideUp", "timeOut": 0});
                        break;
                }

            }
        };
    }

    var Manage = new ManageApp();


    $(".print_one_element").click(function () {

        if (ArrayTable.length === 0) {
            toastr.error("Selectionner un element pour effectuer cette action", "Alert", {"progressBar": true, "closeButton": true, "showMethod": "slideDown", "hideMethod": "slideUp"});
        } else if (ArrayTable.length === 1) {

            var $id = $(ArrayTable[0]).text();
            getDataToPrint($id);

        } else {
            toastr.error("Impossible d'effectuer cette action sous plusieurs elements", "Alert", {"progressBar": true, "closeButton": true, "showMethod": "slideDown", "hideMethod": "slideUp"});
        }

    });

    $(".print_one_element_commande").click(function () {

        if (ArrayTable.length === 0) {
            toastr.error("Selectionner un element pour effectuer cette action", "Alert", {"progressBar": true, "closeButton": true, "showMethod": "slideDown", "hideMethod": "slideUp"});
        } else if (ArrayTable.length === 1) {

            var $id = $(ArrayTable[0]).text();
            if ($(this).hasClass("petit_format")) {
                getDataToPrintCommande($id, "A6");
            } else {
                getDataToPrintCommande($id, "A4");
            }


        } else {
            toastr.error("Impossible d'effectuer cette action sous plusieurs elements", "Alert", {"progressBar": true, "closeButton": true, "showMethod": "slideDown", "hideMethod": "slideUp"});
        }

    });
    $(".print_one_element_commande_caisse").click(function () {

        if (ArrayTable.length === 0) {
            toastr.error("Selectionner un element pour effectuer cette action", "Alert", {"progressBar": true, "closeButton": true, "showMethod": "slideDown", "hideMethod": "slideUp"});
        } else if (ArrayTable.length === 1) {

            var $id = $(ArrayTable[0]).text();
            if ($(this).hasClass("petit_format")) {
                getDataToPrintCommande($id, "A6", "caisse");
            } else {
                getDataToPrintCommande($id, "A4", "caisse");
            }


        } else {
            toastr.error("Impossible d'effectuer cette action sous plusieurs elements", "Alert", {"progressBar": true, "closeButton": true, "showMethod": "slideDown", "hideMethod": "slideUp"});
        }

    });
    $(".print_bordereau_caisse").click(function () {

        if (ArrayTable.length === 0) {
            toastr.error("Selectionner un element pour effectuer cette action", "Alert", {"progressBar": true, "closeButton": true, "showMethod": "slideDown", "hideMethod": "slideUp"});
        } else if (ArrayTable.length === 1) {

            var $id = $(ArrayTable[0]).text();
            getDataToPrintBordereauCommande($id, "caisse");



        } else {
            toastr.error("Impossible d'effectuer cette action sous plusieurs elements", "Alert", {"progressBar": true, "closeButton": true, "showMethod": "slideDown", "hideMethod": "slideUp"});
        }

    });
    $(".print_bordereau").click(function () {

        if (ArrayTable.length === 0) {
            toastr.error("Selectionner un element pour effectuer cette action", "Alert", {"progressBar": true, "closeButton": true, "showMethod": "slideDown", "hideMethod": "slideUp"});
        } else if (ArrayTable.length === 1) {

            var $id = $(ArrayTable[0]).text();
            getDataToPrintBordereauCommande($id);



        } else {
            toastr.error("Impossible d'effectuer cette action sous plusieurs elements", "Alert", {"progressBar": true, "closeButton": true, "showMethod": "slideDown", "hideMethod": "slideUp"});
        }

    });
    /**
     * @description bouton d'activation ou desactivation d'un ou plusieurs utilisateur
     */
    $(".btn-link-active").click(function () {
        var SelectDelete = $("<select multiple name=" + $(this).attr('name') + "></select>");
        if (ArrayTable.length === 0) {
            Manage.Toast("error", "Alert", "Selectionner au moins un element");
        } else {
            for (var i = 0; i < ArrayTable.length; i++) {
                SelectDelete.append(ArrayTable[i]);
            }
            //formulaire à soumettre pour la suppression
            var FormDelete = $('<form method="post" class="form hidden" action="' + $(this).attr('action') + '">' +
                    '</form>');
            FormDelete.append(SelectDelete);
            FormDelete.append($("<button type='submit'></button>"));

            $("body").append(FormDelete);
            FormDelete.submit();


        }

    });


    /**
     * @description bouton de reception des elements selectionné du tableau
     */
    $(".btn-link-reception").click(function () {
        var SelectDelete = $("<select multiple name=" + $(this).attr('name') + "></select>");
        if (ArrayTable.length === 0) {
            Manage.Toast("error", "Alert", "Selectionner au moins un element");
        } else {
            for (var i = 0; i < ArrayTable.length; i++) {
                SelectDelete.append(ArrayTable[i]);
            }
            //formulaire à soumettre pour la suppression
            var FormDelete = $('<form method="post" class="form hidden" action="' + $(this).attr('action') + '">' +
                    '</form>');
            FormDelete.append(SelectDelete);
            FormDelete.append($("<button type='submit'></button>"));


            if (confirm("voulez-vous Receptionner definitivement le(s) element(s) selectionné(s)?")) {
                $("body").append(FormDelete);
                FormDelete.submit();
            }

        }

    });
    /**
     * @description Encaisser la totalité d'une facture
     */
    $(".btn-link-encaisser-facture").click(function () {
        var SelectDelete = $("<select multiple name=" + $(this).attr('name') + "></select>");
        if (ArrayTable.length === 0) {
            Manage.Toast("error", "Alert", "Selectionner un element pour effectuer cette opération");
        } else {
            for (var i = 0; i < ArrayTable.length; i++) {
                SelectDelete.append(ArrayTable[i]);
            }
            //formulaire à soumettre pour la suppression
            var FormDelete = $('<form method="post"  class="form hidden" action="' + $(this).attr('action') + '">' +
                    '</form>');
            FormDelete.append(SelectDelete);
            FormDelete.append($("<button type='submit'></button>"));

            if (ArrayTable.length === 1) {
                if (confirm("ATTENTION\n confirmez vous la reception totale de cette facture?")) {
                    $("body").append(FormDelete);
                    FormDelete.submit();
                }
            } else {
                if (confirm("ATTENTION \n confirmez vous la reception totale de ces factures ?")) {
                    $("body").append(FormDelete);
                    FormDelete.submit();
                }

            }


        }

    });
    /**
     * @description bouton de suppression d'une facture
     */
    $(".btn-link-delete-facture").click(function () {
        var SelectDelete = $("<select name=" + $(this).attr('name') + "></select>");
        if (ArrayTable.length === 0) {
            Manage.Toast("error", "Alert", "Selectionner un element pour effectuer cette opération");
        } else if (ArrayTable.length === 1) {
            for (var i = 0; i < ArrayTable.length; i++) {
                SelectDelete.append(ArrayTable[i]);
            }
            //formulaire à soumettre pour la suppression
            var FormDelete = $('<form method="post" class="form hidden" action="' + $(this).attr('action') + '">' +
                    '</form>');
            FormDelete.append(SelectDelete);
            FormDelete.append($("<button type='submit'></button>"));


            if (confirm("vous êtes sur le point d'annuler une facture mais celà vas engendrer une sortie en caisse.\n Voulez-vous définitivement annuler vette facture?")) {
                $("body").append(FormDelete);
                FormDelete.submit();
            }

        }

    });
    /**
     * @description bouton de suppression des elements selectionné du tableau
     */
    $(".btn-link-delete").click(function () {
        var SelectDelete = $("<select multiple name=" + $(this).attr('name') + "></select>");
        if (ArrayTable.length === 0) {
            Manage.Toast("error", "Alert", "Selectionner au moins un element");
        } else {
            for (var i = 0; i < ArrayTable.length; i++) {
                SelectDelete.append(ArrayTable[i]);
            }
            //formulaire à soumettre pour la suppression
            var FormDelete = $('<form method="post" class="form hidden" action="' + $(this).attr('action') + '">' +
                    '</form>');
            FormDelete.append(SelectDelete);
            FormDelete.append($("<button type='submit'></button>"));


            if (confirm("voulez-vous supprimer definitivement le(s) element(s) selectionné(s)?")) {
                $("body").append(FormDelete);
                FormDelete.submit();
            }

        }

    });
    /**
     * @description bouton pour l'edition d'un element selectionné du Tableau
     */
    $(".btn-link-update").click(function () {
        var SelectUpdate = $("<select name=" + $(this).attr('name') + "></select>");

        if (ArrayTable.length === 0) {
            Manage.Toast("error", "Alert", "Selectionner un element pour effectuer cette action");
        } else if (ArrayTable.length === 1) {
            for (var i = 0; i < ArrayTable.length; i++) {
                SelectUpdate.append(ArrayTable[i]);
                //formulaire à soumettre pour la suppression
                var FormDelete = $('<form method="post" class="form hidden" action="' + $(this).attr('action') + '">' +
                        '</form>');
                FormDelete.append(SelectUpdate);
                FormDelete.append($("<button type='submit'></button>"));
                $("body").append(FormDelete);
                FormDelete.submit();
            }

        } else {
            Manage.Toast("error", "Alert", "Impossible d'effectuer cette action sous plusieurs elements");
        }

    });

    $(".btn-link-eye").click(function () {
        var SelectUpdate = $("<select name=" + $(this).attr('name') + "></select>");

        if (ArrayTable.length === 0) {
            Manage.Toast("error", "Alert", "Selectionner un element pour effectuer cette action");
        } else if (ArrayTable.length === 1) {
            for (var i = 0; i < ArrayTable.length; i++) {
                SelectUpdate.append(ArrayTable[i]);
                //formulaire à soumettre pour la suppression
                var FormDelete = $('<form method="post" class="form hidden" action="' + $(this).attr('action') + '">' +
                        '</form>');
                FormDelete.append(SelectUpdate);
                FormDelete.append($("<button type='submit'></button>"));
                $("body").append(FormDelete);
                FormDelete.submit();
            }

        } else {
            Manage.Toast("error", "Alert", "Impossible d'effectuer cette action sous plusieurs elements");
        }

    });
    /**
     * @description notification en cas d'erreur ou de succès d'une action
     */

    if ($("message").attr("type") === "error") {
        Manage.Toast("error", $("message").attr("title"), $("message").attr("content"));
    } else if ($("message").attr("type") === "info") {
        Manage.Toast("info", $("message").attr("title"), $("message").attr("content"));
    } else if ($("message").attr("type") === "success") {
        Manage.Toast("success", $("message").attr("title"), $("message").attr("content"));
    } else if ($("message").attr("type") === "warning") {
        Manage.Toast("warning", $("message").attr("title"), $("message").attr("content"));
    }
    /**
     * @description initialistion des dataTable
     */
    var filterAble, ordre;

    if ($(".datec").attr("pos") !== undefined) {
        ordre = parseInt($(".datec").attr("pos"));
        filterAble = {targets: ordre};
    } else {
        ordre = 1;
        filterAble = [ordre, "desc"];
    }
    var simpleTable = $('.dataex-colvis-restore').DataTable({
        dom: 'Bfrtip',
        lengthChange: true,
        //keys: true,
        //select: true,
        lengthMenu: [
            [10, 25, 50, -1],
            ['10', '25', '50', 'Tous']

        ],
        buttons: [
            {
                extend: 'colvis',
                text: '<icon class="fa fa-table" data-toggle="tooltips" title="Excel"></icon>'
            },
            {
                text: '<icon class="fa fa-window-maximize" data-toggle="tooltips" title="Tout selectionner"></icon>',
                action: function () {
                    simpleTable.rows().select();
                    ArrayTable = [];
                    for (var i = 0; i < simpleTable.rows().data().length; i++) {
                        ArrayTable.push(simpleTable.rows().data()[i][0]);

                    }
                }
            },
            {
                text: '<icon class="fa fa-window-minimize" data-toggle="tooltips" title="Tout deselectionner"></icon>',
                action: function () {
                    simpleTable.rows().deselect();
                    ArrayTable = [];
                }
            },
            {
                extend: 'excelHtml5',
                text: '<icon class="fa fa-file-excel-o" data-toggle="tooltips" title="Excel"></icon>',
                exportOptions: {
                },
                customize: function (xlsx) {
                    var sheet = xlsx.xl.worksheets['sheet1.xml'];

                    // Loop over the cells in column `F`
                    $('row c[r^="F"]', sheet).each(function () {
                        // Get the value and strip the non numeric characters
                        if ($('is t', this).text().replace(/[^\d]/g, '') * 1 >= 500000) {
                            $(this).attr('s', '20');
                        }
                    });
                }
            },
            'pageLength'

        ],
        columnDefs: [{
                orderable: false,
                className: 'select-checkbox',
                targets: 0

            }, {
                "visible": true
            },
            filterAble
        ],
        select: {
            style: 'multi',
            selector: 'td:first-child'
        },
        order: [[ordre, 'desc']],
        "displayLength": 25,
        language: {
            buttons: {
                copyTitle: 'Ajouté au presse-papiers',
                copyKeys: 'Appuyez sur <i>ctrl</i> ou <i>\u2318</i> + <i>C</i> pour copier les données du tableau à votre presse-papiers. <br><br>Pour annuler, cliquez sur ce message ou appuyez sur Echap.',
                copySuccess: {
                    _: '%d lignes copiées',
                    1: '1 ligne copiée',
                    pageLength: 'montrer'
                }
            },
            processing: "Traitement en cours...",
            search: "Rechercher&nbsp;:",
            lengthMenu: "Afficher _MENU_ &eacute;l&eacute;ments",
            info: "Affichage de l'&eacute;lement _START_ &agrave; _END_ sur _TOTAL_ &eacute;l&eacute;ments",
            infoEmpty: "Affichage de l'&eacute;lement 0 &agrave; 0 sur 0 &eacute;l&eacute;ments",
            infoFiltered: "(filtr&eacute; de _MAX_ &eacute;l&eacute;ments au total)",
            infoPostFix: "",
            loadingRecords: "Chargement en cours...",
            zeroRecords: "Aucun &eacute;l&eacute;ment &agrave; afficher",
            emptyTable: "Aucune donnée disponible dans le tableau",
            paginate: {
                first: "Premier",
                previous: "Pr&eacute;c&eacute;dent",
                next: "Suivant",
                last: "Dernier"
            },
            aria: {
                sortAscending: ": activer pour trier la colonne par ordre croissant",
                sortDescending: ": activer pour trier la colonne par ordre décroissant"
            }
        }
    });

    simpleTable
            .on('select', function (e, dt, type, indexes) {
                var rowData = simpleTable.rows(indexes).data().toArray();
                ArrayTable.push(rowData[0][0]);

            })
            .on('deselect', function (e, dt, type, indexes) {
                var rowData = simpleTable.rows(indexes).data().toArray();
                for (var i = 0; i < ArrayTable.length; i++) {
                    if (ArrayTable[i] === rowData[0][0]) {
                        ArrayTable.pop(ArrayTable[i]);
                    }
                }

            });


    var tableSelectItems = $('.dataex-select-checkbox').DataTable({
        dom: 'Bfrtip',
        lengthChange: true,
        //keys: true,
        //select: true,
        lengthMenu: [
            [10, 25, 50, -1],
            ['10', '25', '50', 'Tous']

        ],
        buttons: [
            {
                extend: 'colvis',
                text: '<icon class="fa fa-table" data-toggle="tooltips" title="Excel"></icon>'
            },
            {
                text: '<icon class="fa fa-window-maximize" data-toggle="tooltips" title="Tout selectionner"></icon>',
                action: function () {
                    tableSelectItems.rows().select();
                    ArrayTable = [];
                    for (var i = 0; i < tableSelectItems.rows().data().length; i++) {
                        ArrayTable.push(tableSelectItems.rows().data()[i][0]);

                    }
                }
            },
            {
                text: '<icon class="fa fa-window-minimize" data-toggle="tooltips" title="Tout deselectionner"></icon>',
                action: function () {
                    tableSelectItems.rows().deselect();
                    ArrayTable = [];
                }
            },
            {
                extend: 'excelHtml5',
                text: '<icon class="fa fa-file-excel-o" data-toggle="tooltips" title="Excel"></icon>',
                exportOptions: {
                },
                customize: function (xlsx) {
                    var sheet = xlsx.xl.worksheets['sheet1.xml'];

                    // Loop over the cells in column `F`
                    $('row c[r^="F"]', sheet).each(function () {
                        // Get the value and strip the non numeric characters
                        if ($('is t', this).text().replace(/[^\d]/g, '') * 1 >= 500000) {
                            $(this).attr('s', '20');
                        }
                    });
                }
            },
            {
                extend: 'print',
                text: '<icon class="fa fa-print" data-toggle="tooltips" title="Imprimer Tout"></icon>',
                exportOptions: {
                },
                customize: function (doc) {
                    doc.content.splice(0, 0, {
                        margin: [40, 0, 0, 0],
                        alignment: 'center',
                        text: "Lalanda Society"

                    });
                }
            },
            {
                extend: 'print',
                text: '<icon class="fa fa-print" data-toggle="tooltips" title="Imprimer la selection"></icon>',
                exportOptions: {
                    modifier: {
                        selected: true
                    }
                }
            },
            'pageLength'

        ],
        columnDefs: [{
                orderable: false,
                className: '',
                targets: 0
            }],
        select: {
            style: 'multi',
            selector: 'td:first-child'
        },
        order: [[1, 'asc']],
        language: {
            buttons: {
                copyTitle: 'Ajouté au presse-papiers',
                copyKeys: 'Appuyez sur <i>ctrl</i> ou <i>\u2318</i> + <i>C</i> pour copier les données du tableau à votre presse-papiers. <br><br>Pour annuler, cliquez sur ce message ou appuyez sur Echap.',
                copySuccess: {
                    _: '%d lignes copiées',
                    1: '1 ligne copiée',
                    pageLength: 'montrer'
                }
            },
            processing: "Traitement en cours...",
            search: "Rechercher&nbsp;:",
            lengthMenu: "Afficher _MENU_ &eacute;l&eacute;ments",
            info: "Affichage de l'&eacute;lement _START_ &agrave; _END_ sur _TOTAL_ &eacute;l&eacute;ments",
            infoEmpty: "Affichage de l'&eacute;lement 0 &agrave; 0 sur 0 &eacute;l&eacute;ments",
            infoFiltered: "(filtr&eacute; de _MAX_ &eacute;l&eacute;ments au total)",
            infoPostFix: "",
            loadingRecords: "Chargement en cours...",
            zeroRecords: "Aucun &eacute;l&eacute;ment &agrave; afficher",
            emptyTable: "Aucune donnée disponible dans le tableau",
            paginate: {
                first: "Premier",
                previous: "Pr&eacute;c&eacute;dent",
                next: "Suivant",
                last: "Dernier"
            },
            aria: {
                sortAscending: ": activer pour trier la colonne par ordre croissant",
                sortDescending: ": activer pour trier la colonne par ordre décroissant"
            }
        }
    });


    tableSelectItems
            .on('select', function (e, dt, type, indexes) {
                var rowData = tableSelectItems.rows(indexes).data().toArray();
                ArrayTable.push(rowData[0][0]);

            })
            .on('deselect', function (e, dt, type, indexes) {
                var rowData = tableSelectItems.rows(indexes).data().toArray();
                for (var i = 0; i < ArrayTable.length; i++) {
                    if (ArrayTable[i] === rowData[0][0]) {
                        ArrayTable.pop(ArrayTable[i]);
                    }
                }

            });

//update quantite article

    $(".last_stock").dblclick(function (ev) {
        ev.preventDefault();
        $(this).addClass("hidden");
        $(this).parent("tr").find("td.td_stock_update").removeClass("hidden");

        setTimeout(function () {
            $(".stock_update").focus();
        }, 200);
    });

    $(".stock_update").blur(function (e) {
        e.preventDefault();
        var td = $(this).parent("td");
        td.addClass("hidden");
        td.parent("tr").find("td.last_stock").removeClass("hidden");
    });

    $(".stock_update").keypress(function (e) {
        var code = e.which || e.keyCode;//Selon le navigateur c'est which ou keyCode
        var id = $(this).attr("idArt");
        var val = $(this).val();
        var q = "administration/article";
        if (code === 13) {
            var client = "";

            var form = $('<form class="form hidden" enctype="multipart/form-data" method="post" action="administration?action=model&model=Articles&q=' + q + '&isNew=1&article=' + id + '"></form>');
            var input = $('<input type="number" required id="quantite" value="' + val + '" name="stock"/>');
            form.append(input);
            $("body").append(form);
            form.submit();
        }
    });

    $("#marge_type").on("change", function () {
        if ($(this).val() === "0") {
            $(".marge_article").addClass("hidden");
            $(".marge_categorie").removeClass("hidden");

        } else {
            $(".marge_categorie").addClass("hidden");
            $(".marge_article").removeClass("hidden");
        }
    });
//vérification de l'image de l'article
    $('#photo_article').bind('change', function () {
//        alert('This file size is: ' + this.files[0].size / 1024 / 1024 + "MB");
        // console.log(this.files[0]);
        if (this.files[0].size / 1024 <= 500) {
            if (this.files[0].type === "image/png" || this.files[0].type === "image/jpeg" || this.files[0].type === "image/jpg") {
                readURL(this);
            } else {
                Manage.Toast("error", "Error", "Format de fichier non pris en compte");
            }
        } else {
            Manage.Toast("error", "Error", "Fichier trop volumineux veuillez choisir un fichier de taille requise (500 Ko)");
        }

    });
    function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();

            reader.onload = function (e) {
                $('.photo_display').attr('src', e.target.result);
            };

            reader.readAsDataURL(input.files[0]);
        }
    }
    /**
     * @description repeter les elements d'un formulaire
     */
    $('.repeater-default').repeater();
// Custom Show / Hide Configurations
    $('.file-repeater, .contact-repeater').repeater({
        show: function () {
            $(this).slideDown();
        },
        hide: function (remove) {
            if (confirm('Are you sure you want to remove this item?')) {
                $(this).slideUp(remove);
            }
        }
    });
    /**
     * @description select de formulaires
     */
    $('.select2-size-xs').select2({
        containerCssClass: 'select-xs form-control'
    });
    /**
     * @param {event} event 
     * @description confirmer la suppression d'un element
     */
    $(".confirm-delete").click(function (event) {
        event.preventDefault();
        if (confirm("voulez-vous supprimer definitivement cet element?")) {
            document.location = $(this).attr("href");
        }
    });
    /**
     * @description pour les modales
     */
    $('.autoshow').modal('toggle');
    $('#onshowbtn').on('click', function () {
        $('#onshow').on('show.bs.modal', function () {
            alert('onShow event fired.');
        });
    });
// onShown event
    $('#onshownbtn').on('click', function () {
        $('#onshown').on('shown.bs.modal', function () {
            alert('onShown event fired.');
        });
    });
// onHide event
    $('#onhidebtn').on('click', function () {
        $('#onhide').on('hide.bs.modal', function () {
            alert('onHide event fired.');
        });
    });
// onHidden event
    $('#onhiddenbtn').on('click', function () {
        $('#onhidden').on('hidden.bs.modal', function () {
            alert('onHidden event fired.');
        });
    });
});
