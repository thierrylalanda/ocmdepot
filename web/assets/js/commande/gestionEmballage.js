/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var allEmballage = [];
function removeEmballage(index) {
    var emb = allEmballage[index];

    $.ajax({
        type: "POST",
        url: 'gestionCommandes?action=model&q=jgddgg&model=deleteEmballageCommande&id=' + emb.id,
        data: {},
        success: function (data) {
            if (data[0] === "OK") {
                allEmballage.splice(index, 1);
                refreshTable();
            }
        },
        dataType: 'json',
        processData: false,
        contentType: false,
        cache: false
    });
}
function updateEmballage() {}
function saveEmballage(idModal) {
    $.ajax({
        type: "POST",
        url: 'gestionCommandes?action=model&q=jgddgg&model=saveEmballageCommande&transport=' + $("[name=transport]").val(),
        data: {},
        success: function (data) {

            if (data.length > 0) {
                $("#" + idModal).modal("hide");

            } else {
                toastr.error("Une erreur c'est produite lors de l'enregistrement", "ERROR", {"progressBar": false, "closeButton": true, "showMethod": "slideDown", "hideMethod": "slideUp", "timeOut": 0});
            }
        },
        dataType: 'json',
        processData: false,
        contentType: false,
        cache: false
    });
}
function refreshTable() {
    $(".table_add_emballage").find("tbody").html("");
    allEmballage.forEach(function (elt, index) {
        $(".table_add_emballage").find("tbody").append("<tr>" +
                "<td id='" + elt.id + "'>" + elt.article + "</td>" +
                "<td>" + elt.prix + "</td>" +
                "<td>" + elt.qte + "</td>" +
                "<td>" + elt.total + "</td>" +
                "<td onclick='removeEmballage(" + index + ")' style='cursor:pointer;color:blue'>supprimer</a></td>" +
                "</tr>");
    });

}
$(document).ready(function () {


    function addEmballage(form) {
        var emballage = form.find("[name=emballage]");
        var qte = form.find("[name=qte]");
        if (ValidateForm(emballage, qte)) {
            var stock = emballage.find("option:selected").attr("stock");
            var nom = emballage.find("option:selected").attr("nom");
            var prix = emballage.find("option:selected").attr("prix");
            var id = emballage.find("option:selected").val();
            if (!exist(id)) {
                $.ajax({
                    type: "POST",
                    url: 'gestionCommandes?action=model&q=jgddgg&model=addEmballageCommande&id=' + id + "&qte=" + qte.val(),
                    data: {},
                    success: function (data) {
                        if (data[0] === "OK") {
                            allEmballage.push({
                                article: nom,
                                id: id,
                                prix: prix,
                                stock: stock,
                                qte: qte.val(),
                                total: parseInt(qte.val()) * parseFloat(prix)
                            });
                            form.trigger("reset");
                            refreshTable();
                        }
                    },
                    dataType: 'json',
                    processData: false,
                    contentType: false,
                    cache: false
                });


            }

        }
    }
// when the modal has show
    $('#emballage_show').on('shown.bs.modal', function (event) {
        $('.allEmballage').select2({
            containerCssClass: 'select-xs form-control'
        });
    });




    $(".form_add_emballage").submit(function (e) {
        e.preventDefault();
        var form = $(this);
        addEmballage(form);


    });

    function exist(id) {
        var exist = false;
        allEmballage.forEach(function (elt, index) {
            if (elt.id === id) {
                exist = true;
            }
        });
        return exist;
    }
    function ValidateForm(emballage, qte) {
        if (emballage.value === "")
        {
            window.alert("Please enter your name.");
            emballage.focus();
            return false;
        }

        if (qte.value === "")
        {
            window.alert("Please enter your address.");
            qte.focus();
            return false;
        }


        return true;
    }
});


