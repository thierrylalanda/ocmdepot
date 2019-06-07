/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    content.push({text: $(".title_table").length !== 0 ? $(".title_table").text() : $("section .card-title:last").text(),decoration: 'underline', style: 'title'});

    var tableToPrint = $(".marge_table").length !== 0 ? $(".marge_table") : $(".dataex-colvis-restore");
    var Thead = tableToPrint.find("thead");
    var Tbody = tableToPrint.find("tbody");
    var Tfoot = tableToPrint.find("tfoot");
    var $Thead = [];
    var $Tbody = [];
    var $Tfoot = [];
    var BodyToPrint = [];
    var tableWidth = [];
    Thead.find("tr").find("th").each(function (index) {
        $Thead.push({text: $(this).text(), fontSize: 12, bold: true, color: "#2371a9"});
        tableWidth.push("*");
    });
    tableWidth[0] = "auto";
    tableWidth[1] = "auto";
    tableWidth[tableWidth.length - 1] = "auto";
    BodyToPrint.push($Thead);
    Tbody.find("tr").each(function (index) {
        var tr = [];
        $(this).find("td").each(function () {
            tr.push({text: $(this).text()});
        });
        BodyToPrint.push(tr);
    });
    Tfoot.find("tr").each(function (index) {
        var tr = [];
        $(this).find("td").each(function () {
            tr.push({text: $(this).text(), fontSize: 12, bold: true, color: "#2371a9"});
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
        pageOrientation: 'landscape',
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

    $(".print_marge").click(function (e) {
        e.preventDefault();
        PrintAlltable();
    });
    // dataTable pour les marges et ristournes
    var ArrayTable = [];
    var tableMarge = $('.marge_table').DataTable({
        dom: 'Bfrtip',
        lengthChange: true,
        //keys: true,
        title: $(".title_table").text(),
        lengthMenu: [
            [10, 25, 50, -1],
            ['10', '25', '50', 'Tous']

        ],
        buttons: [
            {
                extend: 'colvis',
                title: $(".title_table").text(),
                text: '<icon class="fa fa-table" data-toggle="tooltips" title="Excel"></icon>',
                 exportOptions: {
                    columns: ':visible'
                }
            },
            {
                text: '<icon class="fa fa-window-maximize" data-toggle="tooltips" title="Tout selectionner"></icon>',
                action: function () {
                    tableMarge.rows().select();
                    ArrayTable = [];
                    for (var i = 0; i < tableMarge.rows().data().length; i++) {
                        ArrayTable.push(tableMarge.rows().data()[i][0]);
                    }
                }
            },
            {
                text: '<icon class="fa fa-window-minimize" data-toggle="tooltips" title="Tout deselectionner"></icon>',
                action: function () {
                    tableMarge.rows().deselect();
                    ArrayTable = [];
                }
            },
            {
                extend: 'excelHtml5',
                title: $(".title_table").text(),
                text: '<icon class="fa fa-file-excel-o" data-toggle="tooltips" title="Excel"></icon>',
                exportOptions: {
                    columns: ':visible'
                },
                customize: function (xlsx) {
                   // var new_style = '<?xml version="1.0" encoding="UTF-8"?><styleSheet xmlns="http://schemas.openxmlformats.org/spreadsheetml/2006/main" xmlns:mc="http://schemas.openxmlformats.org/markup-compatibility/2006" mc:Ignorable="x14ac" xmlns:x14ac="https://schemas.microsoft.com/office/spreadsheetml/2009/9/ac"><numFmts count="2"><numFmt numFmtId="171" formatCode="d/mm/yyyy;@"/><numFmt numFmtId="172" formatCode="m/d/yyyy;@"/></numFmts><fonts count="10" x14ac:knownFonts="1"><font><sz val="11"/><color theme="1"/><name val="Calibri"/><family val="2"/><scheme val="minor"/></font><font><sz val="11"/><color theme="1"/><name val="Calibri"/><family val="2"/><scheme val="minor"/></font><font><b/><sz val="11"/><color theme="1"/><name val="Calibri"/><family val="2"/><scheme val="minor"/></font><font><sz val="11"/><color theme="0"/><name val="Calibri"/><family val="2"/><scheme val="minor"/></font><font><i/><sz val="11"/><color theme="1"/><name val="Calibri"/><family val="2"/><scheme val="minor"/></font><font><sz val="11"/><color rgb="FFC00000"/><name val="Calibri"/><family val="2"/><scheme val="minor"/></font><font><sz val="11"/><color rgb="FF006600"/><name val="Calibri"/><family val="2"/><scheme val="minor"/></font><font><sz val="11"/><color rgb="FF990033"/><name val="Calibri"/><family val="2"/><scheme val="minor"/></font><font><sz val="11"/><color rgb="FF663300"/><name val="Calibri"/><family val="2"/><scheme val="minor"/></font><font><b/><sz val="11"/><color rgb="FFC00000"/><name val="Calibri"/><family val="2"/><scheme val="minor"/></font></fonts><fills count="16"><fill><patternFill patternType="none"/></fill><fill><patternFill patternType="gray125"/></fill><fill><patternFill patternType="solid"><fgColor rgb="FFC00000"/><bgColor indexed="64"/></patternFill></fill><fill><patternFill patternType="solid"><fgColor rgb="FFFF0000"/><bgColor indexed="64"/></patternFill></fill><fill><patternFill patternType="solid"><fgColor rgb="FFFFC000"/><bgColor indexed="64"/></patternFill></fill><fill><patternFill patternType="solid"><fgColor rgb="FFFFFF00"/><bgColor indexed="64"/></patternFill></fill><fill><patternFill patternType="solid"><fgColor rgb="FF92D050"/><bgColor indexed="64"/></patternFill></fill><fill><patternFill patternType="solid"><fgColor rgb="FF00B050"/><bgColor indexed="64"/></patternFill></fill><fill><patternFill patternType="solid"><fgColor rgb="FF00B0F0"/><bgColor indexed="64"/></patternFill></fill><fill><patternFill patternType="solid"><fgColor rgb="FF0070C0"/><bgColor indexed="64"/></patternFill></fill><fill><patternFill patternType="solid"><fgColor rgb="FF002060"/><bgColor indexed="64"/></patternFill></fill><fill><patternFill patternType="solid"><fgColor rgb="FF7030A0"/><bgColor indexed="64"/></patternFill></fill><fill><patternFill patternType="solid"><fgColor theme="1"/><bgColor indexed="64"/></patternFill></fill><fill><patternFill patternType="solid"><fgColor rgb="FF99CC00"/><bgColor indexed="64"/></patternFill></fill><fill><patternFill patternType="solid"><fgColor rgb="FFFF9999"/><bgColor indexed="64"/></patternFill></fill><fill><patternFill patternType="solid"><fgColor rgb="FFFFCC00"/><bgColor indexed="64"/></patternFill></fill></fills><borders count="2"><border><left/><right/><top/><bottom/><diagonal/></border><border><left style="thin"><color indexed="64"/></left><right style="thin"><color indexed="64"/></right><top style="thin"><color indexed="64"/></top><bottom style="thin"><color indexed="64"/></bottom><diagonal/></border></borders><cellStyleXfs count="2"><xf numFmtId="0" fontId="0" fillId="0" borderId="0"/><xf numFmtId="9" fontId="1" fillId="0" borderId="0" applyFont="0" applyFill="0" applyBorder="0" applyAlignment="0" applyProtection="0"/></cellStyleXfs><cellXfs count="70"><xf numFmtId="0" fontId="0" fillId="0" borderId="0" xfId="0"/><xf numFmtId="0" fontId="0" fillId="0" borderId="0" xfId="0" applyAlignment="1"><alignment vertical="top"/></xf><xf numFmtId="0" fontId="2" fillId="0" borderId="0" xfId="0" applyFont="1" applyAlignment="1"><alignment vertical="top"/></xf><xf numFmtId="0" fontId="0" fillId="0" borderId="0" xfId="0" applyAlignment="1"><alignment horizontal="center" vertical="top"/></xf><xf numFmtId="0" fontId="0" fillId="0" borderId="0" xfId="0" applyAlignment="1"><alignment horizontal="right" vertical="top"/></xf><xf numFmtId="0" fontId="4" fillId="0" borderId="0" xfId="0" applyFont="1" applyAlignment="1"><alignment vertical="top"/></xf><xf numFmtId="0" fontId="4" fillId="0" borderId="0" xfId="0" applyFont="1" applyAlignment="1"><alignment horizontal="center" vertical="top"/></xf><xf numFmtId="0" fontId="4" fillId="0" borderId="0" xfId="0" applyFont="1" applyAlignment="1"><alignment horizontal="right" vertical="top"/></xf><xf numFmtId="0" fontId="0" fillId="0" borderId="1" xfId="0" applyBorder="1" applyAlignment="1"><alignment vertical="top"/></xf><xf numFmtId="0" fontId="0" fillId="0" borderId="1" xfId="0" applyBorder="1" applyAlignment="1"><alignment horizontal="center" vertical="top"/></xf><xf numFmtId="0" fontId="0" fillId="0" borderId="1" xfId="0" applyBorder="1" applyAlignment="1"><alignment horizontal="right" vertical="top"/></xf><xf numFmtId="0" fontId="0" fillId="0" borderId="0" xfId="0" applyAlignment="1"><alignment vertical="top" wrapText="1"/></xf><xf numFmtId="0" fontId="0" fillId="0" borderId="1" xfId="0" applyBorder="1" applyAlignment="1"><alignment vertical="top" wrapText="1"/></xf><xf numFmtId="0" fontId="3" fillId="2" borderId="0" xfId="0" applyFont="1" applyFill="1" applyAlignment="1"><alignment vertical="top"/></xf><xf numFmtId="0" fontId="3" fillId="3" borderId="0" xfId="0" applyFont="1" applyFill="1" applyAlignment="1"><alignment vertical="top"/></xf><xf numFmtId="0" fontId="0" fillId="4" borderId="0" xfId="0" applyFill="1" applyAlignment="1"><alignment vertical="top"/></xf><xf numFmtId="0" fontId="0" fillId="5" borderId="0" xfId="0" applyFill="1" applyAlignment="1"><alignment vertical="top"/></xf><xf numFmtId="0" fontId="0" fillId="6" borderId="0" xfId="0" applyFill="1" applyAlignment="1"><alignment vertical="top"/></xf><xf numFmtId="0" fontId="3" fillId="7" borderId="0" xfId="0" applyFont="1" applyFill="1" applyAlignment="1"><alignment vertical="top"/></xf><xf numFmtId="0" fontId="0" fillId="8" borderId="0" xfId="0" applyFill="1" applyAlignment="1"><alignment vertical="top"/></xf><xf numFmtId="0" fontId="3" fillId="9" borderId="0" xfId="0" applyFont="1" applyFill="1" applyAlignment="1"><alignment vertical="top"/></xf><xf numFmtId="0" fontId="3" fillId="10" borderId="0" xfId="0" applyFont="1" applyFill="1" applyAlignment="1"><alignment vertical="top"/></xf><xf numFmtId="0" fontId="3" fillId="11" borderId="0" xfId="0" applyFont="1" applyFill="1" applyAlignment="1"><alignment vertical="top"/></xf><xf numFmtId="0" fontId="3" fillId="12" borderId="0" xfId="0" applyFont="1" applyFill="1" applyAlignment="1"><alignment vertical="top"/></xf><xf numFmtId="0" fontId="3" fillId="2" borderId="0" xfId="0" applyFont="1" applyFill="1" applyAlignment="1"><alignment horizontal="center" vertical="top"/></xf><xf numFmtId="0" fontId="3" fillId="3" borderId="0" xfId="0" applyFont="1" applyFill="1" applyAlignment="1"><alignment horizontal="center" vertical="top"/></xf><xf numFmtId="0" fontId="0" fillId="4" borderId="0" xfId="0" applyFill="1" applyAlignment="1"><alignment horizontal="center" vertical="top"/></xf><xf numFmtId="0" fontId="0" fillId="5" borderId="0" xfId="0" applyFill="1" applyAlignment="1"><alignment horizontal="center" vertical="top"/></xf><xf numFmtId="0" fontId="0" fillId="6" borderId="0" xfId="0" applyFill="1" applyAlignment="1"><alignment horizontal="center" vertical="top"/></xf><xf numFmtId="0" fontId="3" fillId="7" borderId="0" xfId="0" applyFont="1" applyFill="1" applyAlignment="1"><alignment horizontal="center" vertical="top"/></xf><xf numFmtId="0" fontId="0" fillId="8" borderId="0" xfId="0" applyFill="1" applyAlignment="1"><alignment horizontal="center" vertical="top"/></xf><xf numFmtId="0" fontId="3" fillId="9" borderId="0" xfId="0" applyFont="1" applyFill="1" applyAlignment="1"><alignment horizontal="center" vertical="top"/></xf><xf numFmtId="0" fontId="3" fillId="10" borderId="0" xfId="0" applyFont="1" applyFill="1" applyAlignment="1"><alignment horizontal="center" vertical="top"/></xf><xf numFmtId="0" fontId="3" fillId="11" borderId="0" xfId="0" applyFont="1" applyFill="1" applyAlignment="1"><alignment horizontal="center" vertical="top"/></xf><xf numFmtId="0" fontId="3" fillId="12" borderId="0" xfId="0" applyFont="1" applyFill="1" applyAlignment="1"><alignment horizontal="center" vertical="top"/></xf><xf numFmtId="0" fontId="3" fillId="2" borderId="0" xfId="0" applyFont="1" applyFill="1" applyAlignment="1"><alignment horizontal="right" vertical="top"/></xf><xf numFmtId="0" fontId="3" fillId="3" borderId="0" xfId="0" applyFont="1" applyFill="1" applyAlignment="1"><alignment horizontal="right" vertical="top"/></xf><xf numFmtId="0" fontId="0" fillId="4" borderId="0" xfId="0" applyFill="1" applyAlignment="1"><alignment horizontal="right" vertical="top"/></xf><xf numFmtId="0" fontId="0" fillId="5" borderId="0" xfId="0" applyFill="1" applyAlignment="1"><alignment horizontal="right" vertical="top"/></xf><xf numFmtId="0" fontId="0" fillId="6" borderId="0" xfId="0" applyFill="1" applyAlignment="1"><alignment horizontal="right" vertical="top"/></xf><xf numFmtId="0" fontId="3" fillId="7" borderId="0" xfId="0" applyFont="1" applyFill="1" applyAlignment="1"><alignment horizontal="right" vertical="top"/></xf><xf numFmtId="0" fontId="0" fillId="8" borderId="0" xfId="0" applyFill="1" applyAlignment="1"><alignment horizontal="right" vertical="top"/></xf><xf numFmtId="0" fontId="3" fillId="9" borderId="0" xfId="0" applyFont="1" applyFill="1" applyAlignment="1"><alignment horizontal="right" vertical="top"/></xf><xf numFmtId="0" fontId="3" fillId="10" borderId="0" xfId="0" applyFont="1" applyFill="1" applyAlignment="1"><alignment horizontal="right" vertical="top"/></xf><xf numFmtId="0" fontId="3" fillId="11" borderId="0" xfId="0" applyFont="1" applyFill="1" applyAlignment="1"><alignment horizontal="right" vertical="top"/></xf><xf numFmtId="0" fontId="3" fillId="12" borderId="0" xfId="0" applyFont="1" applyFill="1" applyAlignment="1"><alignment horizontal="right" vertical="top"/></xf><xf numFmtId="0" fontId="0" fillId="0" borderId="1" xfId="0" applyNumberFormat="1" applyBorder="1" applyAlignment="1"><alignment horizontal="center" vertical="top" textRotation="90"/></xf><xf numFmtId="0" fontId="0" fillId="0" borderId="1" xfId="0" applyNumberFormat="1" applyBorder="1" applyAlignment="1"><alignment horizontal="center" textRotation="255"/></xf><xf numFmtId="0" fontId="0" fillId="0" borderId="1" xfId="0" applyNumberFormat="1" applyBorder="1" applyAlignment="1"><alignment textRotation="45"/></xf><xf numFmtId="0" fontId="5" fillId="0" borderId="0" xfId="0" applyNumberFormat="1" applyFont="1" applyAlignment="1"><alignment vertical="top"/></xf><xf numFmtId="0" fontId="5" fillId="0" borderId="0" xfId="0" applyNumberFormat="1" applyFont="1" applyAlignment="1"><alignment horizontal="center" vertical="top"/></xf><xf numFmtId="0" fontId="5" fillId="0" borderId="0" xfId="0" applyNumberFormat="1" applyFont="1" applyAlignment="1"><alignment horizontal="right" vertical="top"/></xf><xf numFmtId="0" fontId="5" fillId="0" borderId="1" xfId="0" applyNumberFormat="1" applyFont="1" applyBorder="1" applyAlignment="1"><alignment vertical="top"/></xf><xf numFmtId="0" fontId="5" fillId="0" borderId="1" xfId="0" applyNumberFormat="1" applyFont="1" applyBorder="1" applyAlignment="1"><alignment horizontal="center" vertical="top"/></xf><xf numFmtId="0" fontId="5" fillId="0" borderId="1" xfId="0" applyNumberFormat="1" applyFont="1" applyBorder="1" applyAlignment="1"><alignment horizontal="right" vertical="top"/></xf><xf numFmtId="0" fontId="6" fillId="13" borderId="0" xfId="0" applyNumberFormat="1" applyFont="1" applyFill="1" applyAlignment="1"><alignment vertical="top"/></xf><xf numFmtId="0" fontId="6" fillId="13" borderId="1" xfId="0" applyNumberFormat="1" applyFont="1" applyFill="1" applyBorder="1" applyAlignment="1"><alignment vertical="top"/></xf><xf numFmtId="0" fontId="7" fillId="14" borderId="0" xfId="1" applyNumberFormat="1" applyFont="1" applyFill="1" applyAlignment="1"><alignment vertical="top"/></xf><xf numFmtId="0" fontId="7" fillId="14" borderId="1" xfId="0" applyNumberFormat="1" applyFont="1" applyFill="1" applyBorder="1" applyAlignment="1"><alignment vertical="top"/></xf><xf numFmtId="0" fontId="8" fillId="15" borderId="0" xfId="0" applyNumberFormat="1" applyFont="1" applyFill="1" applyAlignment="1"><alignment vertical="top"/></xf><xf numFmtId="0" fontId="8" fillId="15" borderId="1" xfId="0" applyNumberFormat="1" applyFont="1" applyFill="1" applyBorder="1" applyAlignment="1"><alignment vertical="top"/></xf><xf numFmtId="0" fontId="0" fillId="0" borderId="0" xfId="0" applyBorder="1" applyAlignment="1"><alignment vertical="top"/></xf><xf numFmtId="171" fontId="0" fillId="0" borderId="0" xfId="0" applyNumberFormat="1" applyAlignment="1"><alignment horizontal="center" vertical="top"/></xf><xf numFmtId="172" fontId="0" fillId="0" borderId="0" xfId="0" applyNumberFormat="1" applyAlignment="1"><alignment horizontal="center" vertical="top"/></xf><xf numFmtId="171" fontId="0" fillId="0" borderId="1" xfId="0" applyNumberFormat="1" applyBorder="1" applyAlignment="1"><alignment horizontal="center" vertical="top"/></xf><xf numFmtId="172" fontId="0" fillId="0" borderId="1" xfId="0" applyNumberFormat="1" applyBorder="1" applyAlignment="1"><alignment horizontal="center" vertical="top"/></xf><xf numFmtId="171" fontId="9" fillId="0" borderId="1" xfId="0" applyNumberFormat="1" applyFont="1" applyBorder="1" applyAlignment="1"><alignment horizontal="center" vertical="top"/></xf><xf numFmtId="172" fontId="9" fillId="0" borderId="1" xfId="0" applyNumberFormat="1" applyFont="1" applyBorder="1" applyAlignment="1"><alignment horizontal="center" vertical="top"/></xf><xf numFmtId="171" fontId="9" fillId="0" borderId="0" xfId="0" applyNumberFormat="1" applyFont="1" applyAlignment="1"><alignment horizontal="center" vertical="top"/></xf><xf numFmtId="172" fontId="9" fillId="0" borderId="0" xfId="0" applyNumberFormat="1" applyFont="1" applyAlignment="1"><alignment horizontal="center" vertical="top"/></xf></cellXfs><cellStyles count="2"><cellStyle name="Procent" xfId="1" builtinId="5"/><cellStyle name="Standaard" xfId="0" builtinId="0"/></cellStyles><dxfs count="0"/><tableStyles count="0" defaultTableStyle="TableStyleMedium2" defaultPivotStyle="PivotStyleLight16"/><colors><mruColors><color rgb="FF663300"/><color rgb="FFFFCC00"/><color rgb="FF990033"/><color rgb="FF006600"/><color rgb="FFFF9999"/><color rgb="FF99CC00"/></mruColors></colors><extLst><ext uri="{EB79DEF2-80B8-43e5-95BD-54CBDDF9020C}" xmlns:x14="https://schemas.microsoft.com/office/spreadsheetml/2009/9/main"><x14:slicerStyles defaultSlicerStyle="SlicerStyleLight1"/></ext></extLst></styleSheet>';
                            // xlsx.xl['styles.xml'] = $.parseXML(new_style);
                    var sheet = xlsx.xl.worksheets['sheet1.xml'];
                    // $('row:first c', sheet).attr('s', '48');
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
        order: [[0, 'asc']]
    });
    tableMarge
            .on('select', function (e, dt, type, indexes) {
                var rowData = tableMarge.rows(indexes).data().toArray();
                ArrayTable.push(rowData[0][0]);
            })
            .on('deselect', function (e, dt, type, indexes) {
                var rowData = tableMarge.rows(indexes).data().toArray();
                for (var i = 0; i < ArrayTable.length; i++) {
                    if (ArrayTable[i] === rowData[0][0]) {
                        ArrayTable.pop(ArrayTable[i]);
                    }
                }

            });
});

