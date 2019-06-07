/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var EventEmitters = new EventEmitter();
$(document).ready(function () {
    EventEmitters.on('data:miniPrinter', function (data) {
 var pdfObj = printCommande(data, {width: 58, height: 1000});
                pdfObj.open();
    });
    function formatNumber(number) {
    var formated = "";
    var format = number.toString();
    var count = 0;
    for (var i = format.length - 1; i >= 0; i--) {
        if (count % 3 === 0) {
            if (i === format.length - 1) {
                formated = format[i];
            } else {
                formated = format[i] + "." + formated;
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
   
    
content.push({text: data.nomsociete+"\n",  italics: true, bold: true});
content.push({text: data.cni+"\n", italics: true, bold: true});
content.push({text: "TEL : "+data.telsociete+"\n", italics: true, bold: true});
content.push({text: 'FACTURE N° \n\n' + data.numero, italics: true, bold: true});

content.push({text:data.nomclient.replace("null", " ")+"\n", italics: true, bold: true});
content.push({text: data.codeclient ? data.codeclient : "" + '\n', italics: true, bold: true});




    var total = 0;
    var totalP = 0;
    var bodyCMD = [
        [
            {text: "Code", bold: true},
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
    for (var i = 0; i < trueArticle.length; i++) {
        var cmd = [];
        var item = trueArticle[i];
        // cmd.push({text: item.categorie});
        cmd.push({text: "" + item.code});
        cmd.push({text: "" + formatNumber(item.pu)});
        cmd.push({text: "" + formatNumber(item.quantite)});
        cmd.push({text: "" + formatNumber(item.total)});
        total += item.total;
        totalP += item.quantite;
        bodyCMD.push(cmd);
    }
    var cmd = [];
    cmd.push({text: " "});
    cmd.push({text: "Total", "bold": true});
    cmd.push({text: "" + formatNumber(totalP), "bold": true});
    cmd.push({text: "" + formatNumber(total), "bold": true});
    bodyCMD.push(cmd);
    var description = {
        table: {
            widths: ["auto", "auto", "auto", "auto"],
            body: bodyCMD,
            pageBreak: 'after', margin: [0, 0, 20, 0]
        }
    };
    var total = data.total;
    const pourcentage = 1.2425;
    var totalHT = Math.round(total / pourcentage);
    var tva = Math.round(totalHT * (19.25 / 100));
     var prov=Math.round(totalHT*(5/100));

    content.push(description);
    content.push({"text": "Emballage", "bold": true});
    var total1 = 0;
    var totalP1 = 0;
    var bodyCMD = [
        [
            {text: "Code", bold: true},
            {text: "P.U", bold: true},
            {text: "Qte", bold: true},
            {text: "P.T", bold: true}]
    ];
    if (falseArticle.length !== 0) {

        for (var i = 0; i < falseArticle.length; i++) {
            var cmd = [];
            var item = falseArticle[i];
            // cmd.push({text: item.categorie});
            cmd.push({text: "" + item.code});
            cmd.push({text: "" + formatNumber(item.pu)});
            cmd.push({text: formatNumber(item.quantite)});
            cmd.push({text: formatNumber(item.total)});
            total1 += item.total;
            totalP1 += item.quantite;
            bodyCMD.push(cmd);
        }
        var cmd = [];
        cmd.push({text: " "});
        cmd.push({text: "Total", "bold": true});
        cmd.push({text: formatNumber(totalP1), "bold": true});
        cmd.push({text: formatNumber(total1), "bold": true});
        bodyCMD.push(cmd);
        var description = {
            style: 'headertable',
            table: {
                widths: ["auto", "auto", "auto", "auto"],
                body: bodyCMD,
                pageBreak: 'after', margin: [0, 0, 20, 0]
            }
        };
        content.push(description);
    } else {
        if (data.casier) {
                var cmd = [];
                cmd.push({text: "XXXXX"});
                cmd.push({text: "Consignation"});
                cmd.push({text: "3600"});
                cmd.push({text: formatNumber(data.casier)});
                cmd.push({text: formatNumber(data.casier*3600)});
                total1 += data.casier*3600;
                totalP1 += data.casier;
                bodyCMD.push(cmd);
                var cmd = [];
                cmd.push({text: "XXXXX"});
                cmd.push({text: "Déconsignation"});
                cmd.push({text: "3600"});
                cmd.push({text: "-"+formatNumber(data.casier)});
                cmd.push({text: "-"+formatNumber(data.casier*3600)});
                total1 += -data.casier*3600;
                totalP1 += -data.casier;
                bodyCMD.push(cmd);
            var cmd = [];
            cmd.push({text: " "});
            cmd.push({text: "", "bold": true});
            cmd.push({text: "Total", "bold": true});
            cmd.push({text: formatNumber(totalP1), "bold": true});
            cmd.push({text: formatNumber(total1), "bold": true});
            bodyCMD.push(cmd);
            var description = {
                table: {
                    widths: ["auto", "*", "auto", "auto", "auto"],
                    body: bodyCMD,
                    pageBreak: 'after', margin: [0, 0, 20, 0]
                }
            };
            content.push(description);
            }
        }
    content.push({text: "\n*******************************\n"});
    content.push({text: 'Montant HT '+formatNumber(totalHT) + ' FCFA \n'});
    content.push({text: 'Retenu TVA(19,25%) '+formatNumber(tva) + ' FCFA \n'});
    content.push({text: 'PSA(5%) '+formatNumber(prov) + ' FCFA \n'});
    content.push({text: 'Montant TTC '+formatNumber(total) + ' FCFA \n'});
    content.push({text: 'Net à payer ' + formatNumber(total)+' FCFA', "bold": true, alignment: 'left', decoration: 'underline'});
   
    if (data.gestmarge && data.gestmarge === 1) {
        content.push({text: "\n**************************\n"});
        content.push({text: 'Rist encours '+formatNumber(data.totalMargeClient) + ' FCFA \n'});
        content.push({text: 'Solde Rist encours '+formatNumber(data.allMargeClient) + ' FCFA \n'});
        content.push({text: "\n"});
    }
 content.push({text: "Vendeur       Client  \n\n\n\n\n"});
    return content;
}

    function printCommande(data, pageSize) {
    var docDefinition;
    var firstdata = data[0];

    docDefinition = {
        info: {
            title: 'CCMANAGER DOCUMENT',
            author: 'EH2S software solution',
            subject: 'subject of document',
            keywords: 'keywords for document'
        },
        pageMargins: [5, 20, 5, 20],
        pageSize: pageSize,

        
        content: getContentOneCommande(firstdata),
        pageBreakBefore: function (currentNode, followingNodesOnPage, nodesOnNextPage, previousNodesOnPage) {
            return currentNode.headlineLevel === 1 && followingNodesOnPage.length === 0;
        },
        
        defaultStyle: {
             alignment: 'left',fontSize: 5
        }
    };
    // pdfMake.createPdf(docDefinition).open();
    return  pdfMake.createPdf(docDefinition);
}
});