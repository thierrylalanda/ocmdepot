/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function getContentOneBordereauCommande(data) {
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


    var header = {
        margin: [30, 10, 0, 10],
        text: [
            {text: 'BORDEREAU  \t N° ' + data.numero, fontSize: 15, italics: true, bold: true, alignment: 'center'}

        ]
    };
    content.push(header);
    var colun = {
        columns: [
            {
                width: '*',
                text: [
                    {text: 'Fournisseur : \t', bold: true},
                    {text: data.nomfournisseur + ' \n'},
                    {text: 'Tel : \t', bold: true},
                    {text: data.telfournisseur},
                    {text: '\n Code : \t', bold: true},
                    {text: data.codefournisseur ? data.codefournisseur : "" + '\n'}
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
                    {text: data.datelivraison + ' \n'}
                    //{text: 'Statut : \t', bold: true},
                    // {text: data.etat + ' \n'}
                ]
            }
        ]

    };
    content.push(colun);





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
    for (var i = 0; i < data.articles.length; i++) {
        var cmd = [];
        var item = data.articles[i];
        cmd.push({text: "" + item.code, fontSize: 9});
        cmd.push({text: item.article, fontSize: 9});
        cmd.push({text: "" + formatNumber(item.pu), fontSize: 9});
        cmd.push({text: "" + formatNumber(item.quantite), fontSize: 9});
        cmd.push({text: "" + formatNumber(item.total), fontSize: 9});
        total += item.total;
        totalP += item.quantite;
        bodyCMD.push(cmd);
    }
    var cmd = [];
    cmd.push({text: " "});
    cmd.push({text: "", "bold": true});
    cmd.push({text: "Total", "bold": true});
    cmd.push({text: "" + formatNumber(totalP), "bold": true});
    cmd.push({text: "" + formatNumber(total), "bold": true});
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
    var total = data.total - data.transport;

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
                            {text: 'Montant TTC\n'},
                            {text: 'Transport'}
                        ]},
                    {width: 100, text: [
                            {text: formatNumber(total) + ' FCFA \n', "bold": true, alignment: 'right'},
                            {text: formatNumber(transport) + ' FCFA', "bold": true, alignment: 'right'}
                        ]}]
            }
        ]

    };
    content.push(colun);
    content.push({text: 'Net à payer ' + formatNumber(total + transport) + ' FCFA\n', fontSize: 15, "bold": true, alignment: 'left', decoration: 'underline'});

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
                    widths: ["*", "*", "*"],
                    body: [
                        [{text: "Magasignier", "bold": true}, {text: "Valideur", "bold": true}, {text: "Fournisseur", "bold": true}]
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

function printBordereauCommande(data, pageSize) {
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
        content: getContentOneBordereauCommande(firstdata),
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

function getDataToPrintBordereauCommande($id, caisse) {
    //  var EventEmitters = new EventEmitter();

    var url = 'gestionachat?q=qsdfsfvsfr&action=bordereau&id=' + $id;
    if (caisse !== undefined) {
        url = 'gestionachat?q=qsdfsfvsfr&action=bordereau&fromcaisse=yes&id=' + $id;
    }
    $.ajax({
        type: "POST",
        url: url,
        data: {},
        success: function (data) {
            console.log(data);
            var pdfObj;
            pdfObj = printBordereauCommande(data, "A4");
            pdfObj.open();

        },
        error: function (error) {
            alert(error);
        },
        dataType: 'json'
    });
}
var EventEmitters = new EventEmitter();
$(document).ready(function () {

//reception d'un produit
    $(".reception_produit").click(function (e) {
        e.preventDefault();
        var idCommande = $(this).attr("name");
        var form = $(".form" + idCommande);
        form.append("<input type='number' value='" + idCommande + "' class='hidden' name='reception'>");
        form.submit();
    });

//modification d'un produit
    $(".modifier_produit").click(function (e) {
        e.preventDefault();
        var idCommande = $(this).attr("name");
        var form = $(".form" + idCommande);
        var qte = $(".qte" + idCommande);
        if (qte.val()) {
            if (parseInt(qte.val()) > 0) {
                form.append("<input type='number' value='" + idCommande + "' class='hidden' name='update'>");
                form.submit();
            } else {
                toastr.error("Impossible de réceptionner cette quantite", "ATTENTION !!!", {"progressBar": true, "closeButton": true, "showMethod": "slideDown", "hideMethod": "slideUp", "timeOut": 0});
            }
        } else {
            form.append("<input type='number' value='" + idCommande + "' class='hidden' name='update'>");
            form.submit();
        }


    });

//suppression d'un produit
    $(".supprimer_produit").click(function (e) {
        e.preventDefault();
        var idCommande = $(this).attr("name");
        if (confirm("Attention!!! \nVoulez-vous réellement supprimer cette commande ?")) {
            var form = $(".form" + idCommande);
            form.append("<input type='number' value='" + idCommande + "' class='hidden' name='delete'>");
            form.submit();
        }
    });

//    $(".print_bordereau").click(function (e) {
//        e.preventDefault();
//        var id = $(this).attr("id");
//        getDataToPrintBordereauCommande(id);
//    });


});
