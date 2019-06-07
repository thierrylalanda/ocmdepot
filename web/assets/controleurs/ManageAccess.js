/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
    var allAccess = [];

    function drawAccess(data) {
        $("#treeview").kendoTreeView({
            checkboxes: {
                checkChildren: true
            },
            check: onCheck,
            dataSource: data
        });
    }

    // function that gathers IDs of checked nodes
    function checkedNodeIds(nodes, checkedNodes) {
        
        for (var i = 0; i < nodes.length; i++) {
            if (nodes[i].checked) {
                checkedNodes.push(nodes[i].id);
                if (!nodes[i].hasChildren) {
                    allAccess.push(nodes[i].idpage);
                }

            }

            if (nodes[i].hasChildren) {
                checkedNodeIds(nodes[i].children.view(), checkedNodes);
            }
        }
    }

    /**
     * @description function qui se declanche au click sue un droit
     * @returns {undefined}
     */
    function onCheck() {
        allAccess = [];
        var checkedNodes = [],
                treeView = $("#treeview").data("kendoTreeView"),
                message;
        checkedNodeIds(treeView.dataSource.view(), checkedNodes);

        if (checkedNodes.length > 0) {
            message = "IDs of checked nodes: " + checkedNodes.join(",");
        } else {
            message = "No nodes checked.";
        }

        $("#result").html(message);
    }

    /**
     * @argument {type} idGroupe description
     * @description function permettant d'obtenir toutes les differentes actions
     * @returns {undefined}
     */
    function getAccess(idGroupe) {

        $.ajax({
            type: "POST",
            url: 'administration?action=getActionForGroupe&q=jgddgg&model=access&groupe=' + idGroupe,
            data: {},
            success: function (data) {
                drawAccess(data);
            },
            error: function (err) {
                toastr.error("authentification fail in the server please try again later or wait one more minute", "error", {"progressBar": false, "closeButton": true, "showMethod": "slideDown", "hideMethod": "slideUp"});
            },
            dataType: 'json'
        });
    }

    function SubmitAccess() {
        allAccess = [];
        onCheck();
        var select = $("<select name='access' multiple='multiple' class='hidden'></select>");
        var form = $("#form_access");
        for (var i = 0; i < allAccess.length; i++) {

            var option = $("<option selected value='" + allAccess[i] + "'>" + allAccess[i] + "</option>");
            select.append(option);
        }
        form.append(select);
      form.submit();
    }

    if (JSON.stringify($("#treeview")) === '{}') {

    } else {

        getAccess($("#example").attr("id_groupe"));
    }
    $("#btn_form_access").click(function (e) {
        e.preventDefault();
        SubmitAccess();
    });
});