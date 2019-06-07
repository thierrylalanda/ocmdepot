/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {

    $("#find_magasin_to_stock").change(function () {
        if ($(this).val() !== "") {
            localStorage.setItem('magasinencours', $(this).val());
            $(".form_find_magasin").submit();
        }
    });

    $("#articles_inventaire").change(function () {
        if ($(this).val() !== "") {
            var article = $(this).val();
            var magasin = localStorage.getItem('magasinencours');
            $.ajax({
                type: "POST",
                url: 'inventaire?action=getStockArticle&q=jgddgg&article=' + article + '&magasin=' + magasin,
                data: {},
                success: function (data) {
                    if (data.length > 0) {
                        var stock = data[0];
                        $("#quantite").val(stock.stock);
                        $("#quantiteV").val(stock.stockV);
                    } else {
                        toastr.error("Une erreur est survenue", "Alert", {"progressBar": true, "closeButton": true, "showMethod": "slideDown", "hideMethod": "slideUp"});
                        $("#quantite").val("");
                        $("#quantiteV").val("");
                    }

                },
                error: function (error) {
                    $("#quantite").val("");
                    $("#quantiteV").val("");
                    toastr.error("Une erreur est survenue : ce magasin ne contient pas cet article", "Alert", {"progressBar": true, "closeButton": true, "showMethod": "slideDown", "hideMethod": "slideUp"});
                },
                dataType: 'json',
                processData: false,
                contentType: false,
                cache: false
            });
        }
    });
    
    $("#articles_stock_magasin").change(function () {
        if ($(this).val() !== "") {
            var article = $(this).val();
            var magasin = localStorage.getItem('magasinencours');
            $.ajax({
                type: "POST",
                url: 'inventaire?action=getStockArticle&q=jgddgg&article=' + article + '&magasin=' + magasin,
                data: {},
                success: function (data) {
                    if (data.length > 0) {
                        var stock = data[0];
                        $("#quantite").val(stock.stock);
                        $("#quantiteV").val(stock.stockV);
                        $("#prix").val(stock.prix);
                    } else {
                        toastr.error("Une erreur est survenue", "Alert", {"progressBar": true, "closeButton": true, "showMethod": "slideDown", "hideMethod": "slideUp"});
                        $("#quantite").val("");
                        $("#quantiteV").val("");
                        $("#prix").val("");
                    }

                },
                error: function (error) {
                    $("#quantite").val("");
                    $("#quantiteV").val("");
                    toastr.error("Une erreur est survenue : ce magasin ne contient pas encore cet article", "Alert", {"progressBar": true, "closeButton": true, "showMethod": "slideDown", "hideMethod": "slideUp"});
                },
                dataType: 'json',
                processData: false,
                contentType: false,
                cache: false
            });
        }
    });
    
    //transfert du stock
    $("#article_to_transfert").change(function(){
        var option_select=$(this).find("option:selected");
        if($(this).val()!== ""){
             var stock= option_select.attr("stock");
             $("#quantite_to_transfert").val(stock);
        }
       
    });
    
    // stock d'un article
    $("#articles_inventaire_societe").change(function(){
        var option_select=$(this).find("option:selected");
        if($(this).val()!== ""){
             var stock= option_select.attr("stock");
             $("#quantite_societe").val(stock);
        }
       
    });
});
