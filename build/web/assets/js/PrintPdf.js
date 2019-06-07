/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function getDate() {
    var date = new Date();
    var jour = date.getDate();
    var mois = (date.getMonth() + 1);
    if ((date.getDate() + 1) < 10) {
        jour = "0" + date.getDate();
    }

    if ((date.getMonth() + 1) < 10) {
        mois = "0" + (date.getMonth() + 1);
    }
    return jour + "/" + mois + "/" + date.getFullYear() + "  " + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();
}
function getContent(data) {
    var content = new Array();
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
    var tel = "";
    if (data.telsociete) {
        tel = ",Tél :" + data.telsociete;
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
                        {text: "Email :" + data.mailsociete + tel, alignment: 'center'}],
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

    content.push({text: $(".title_table").text(), style: 'title'});

    var tableToPrint = $(".table");
    var Thead = tableToPrint.find("thead");
    var Tbody = tableToPrint.find("tbody");
    var $Thead = [];
    var $Tbody = [];
    var BodyToPrint = [];
    var tableWidth = [];
    Thead.find("tr").find("th").each(function (index) {
        $Thead.push({text: $(this).text(), fontSize: 12, bold: true, color: "#2371a9"});
        tableWidth.push("auto");
    });
    BodyToPrint.push($Thead);
    Tbody.find("tr").each(function (index) {
        var tr = [];
        $(this).find("td").each(function () {
            tr.push({text: $(this).text()});
        });
        BodyToPrint.push(tr);

    });
    content.push({
        style: 'tableExample',
        table: {
            widths: tableWidth,
            body: BodyToPrint
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
function printPdf(data) {
    var docDefinition;


    docDefinition = {
        info: {
            title: 'CCMANAGER DOCUMENT',
            author: 'EH2S software solution',
            subject: 'subject of document',
            keywords: 'keywords for document'
        },
        pageMargins: [20, 30, 20, 30],
        pageSize: 'A4',
        // watermark: {text: 'OCMANAGER', color: 'blue', opacity: 0.1, bold: true, italics: false},
//        header: function (currentPage, pageCount) {
//            var tel = "";
//            if (data.telsociete) {
//                tel = ",Tél :" + data.telsociete;
//            }
//            var tfoo = {
//                columns: [
//                    {text: "Email :" + data.mailsociete + tel, alignment: 'center', margin: [20, 5, 0, 0]}
//
//
//
//                ]};
//            return tfoo;
//        },
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
function  PrintAlltable() {
    $.ajax({
        type: "POST",
        url: 'jtzxbkui4528f725jhf?action=model&model=getTicketmodelPrintSociete&q=jgddgg',
        data: {},
        success: function (data) {
            var firstdata = data[0];
            printPdf(firstdata);
            
        },
        dataType: 'json'
    });

}


$(document).ready(function () {
    var ArrayTable = [];



    var tableSelectItems = $('.reporting_table').DataTable({
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
                    var sheet = xlsx.xl.worksheets['reporting.xml'];

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
                orderable: true,
                className: '',
                targets: 0
            }, {
                "visible": true
            }],
        select: {
            style: 'multi',
            selector: 'td:first-child'
        },
        order: [[1, 'asc']]
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
});