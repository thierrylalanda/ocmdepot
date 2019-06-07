/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


(function () {
    "use strict";

    var ENTER_KEY_CODE = 13;
    var queryInput, resultDiv, accessTokenInput, sendqueryInput;

    window.onload = init;
    function init() {
        queryInput = document.getElementById("q");
        sendqueryInput = document.getElementById("sendRequest");
        resultDiv = document.getElementById("chats");
        accessTokenInput = document.getElementById("access_token");
        var setAccessTokenButton = document.getElementById("set_access_token");

        queryInput.addEventListener("keydown", queryInputKeyDown);
        sendqueryInput.addEventListener("click", queryInputSubmit);
        // setAccessTokenButton.addEventListener("click", setAccessToken);
        setAccessToken();
    }

    function setAccessToken() {
        //document.getElementById("placeholder").style.display = "none";
        //  document.getElementById("main-wrapper").style.display = "block";
        // window.init(accessTokenInput.value);
        window.init("63be609628454f329ea7c4ced878ca96");
    }
function queryInputSubmit(){
     var value = queryInput.value;
        if(value ==="") {
           return; 
        }
        queryInput.value = "";

        createQueryNode(value);
        var responseNode = createResponseNode();

        sendText(value)
                .then(function (response) {
                    var result;
                    try {
                        result = response.result.fulfillment.speech;
                    } catch (error) {
                        result = "";
                    }
                    // setResponseJSON(response);
                    setResponseOnNode(result, responseNode);
                },function(error){
                    setResponseOnNode("Oups !!! Quelque chose ne va pas.On n'a rencontré des problèmes : Echec d'authentification au serveur", responseNode);
                })
                .catch(function (err) {
                   // setResponseJSON(err);
                    setResponseOnNode("Oups !!! Quelque chose ne va pas.On n'a rencontré des problèmes", responseNode);
                });
}
    function queryInputKeyDown(event) {

        if (event.which !== ENTER_KEY_CODE) {
            return;
        }
        event.preventDefault();
        var value = queryInput.value;
        if(value ==="") {
           return; 
        }
        queryInput.value = "";

        createQueryNode(value);
        var responseNode = createResponseNode();

        sendText(value)
                .then(function (response) {
                    var result;
                    try {
                        result = response.result.fulfillment.speech;
                    } catch (error) {
                        result = "";
                    }
                    // setResponseJSON(response);
                    setResponseOnNode(result, responseNode);
                },function(error){
                    setResponseOnNode("Oups !!! Quelque chose ne va pas.On n'a rencontré des problèmes : Echec d'authentification au serveur", responseNode);
                })
                .catch(function (err) {
                    setResponseJSON(err);
                    setResponseOnNode("Something goes wrong", responseNode);
                });
    }

    function createQueryNode(query) {
        var node = document.createElement('div');
        node.className = "clearfix left-align left card-panel green accent-1";
        node.innerHTML = ' <div class="chat">' +
                '<div class="chat-avatar">' +
                '<a class="avatar" data-toggle="tooltip" href="#" data-placement="right" title="" data-original-title="">' +
                '<img src="StackAdmin/app-assets/images/portrait/small/avatar-s-1.png" alt="avatar"/>' +
                '</a>' +
                '</div>' +
                '<div class="chat-body">' +
                '<div class="chat-content">' +
                '<p>' + query + '</p>' +
                '</div>' +
                '</div>' +
                '</div>';
        resultDiv.appendChild(node);
    }

    function createResponseNode() {
        var node = document.createElement('div');
        node.className = "clearfix right-align right card-panel blue-text text-darken-2 hoverable";
        node.innerHTML = '<img src="assets/images/loader.gif" alt="avatar"/>';
        resultDiv.appendChild(node);
        return node;
    }

    function setResponseOnNode(response, node) {
        var resp=' <div class="chat chat-left">' +
                '<div class="chat-avatar">' +
                '<a class="avatar" data-toggle="tooltip" href="#" data-placement="right" title="" data-original-title="">' +
                '<img src="assets/images/ccmanager.png" alt="avatar"/>' +
                '</a>' +
                '</div>' +
                '<div class="chat-body">' +
                '<div class="chat-content">' +
                '<p>' + response + '</p>' +
                '</div>' +
                '</div>' +
                '</div>';
        node.innerHTML = response ? resp : "[empty response]";
        node.setAttribute('data-actual-response', response);
    }

    function setResponseJSON(response) {
        var node = document.getElementById("jsonResponse");
        node.innerHTML = JSON.stringify(response, null, 2);
    }

    function sendRequest() {

    }

})();