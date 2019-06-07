/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.  
 */

function getAllNotifications() {
    // on lance une requête AJAX
    var model = "";
    if ($(".notification").attr("entity") === "user") {
        model = "getUserssNotification";
    } else {
        model = "getClientsNotification";
    }
    $.post(
            'ticket',
            {"id": $(".notification").attr("id"), "action": "model", model: model, "q": "vue"},
            function (data) {
                var notif = [];
                for (var i = 0; i < data.length; i++) {
                    if (data[i].etat === 0) {
                        notif.push(data[i]);
                    }
                }
                sessionStorage.setItem("notification", JSON.stringify(notif));
                sessionStorage.setItem("last-notif", JSON.stringify(notif));
            },
            'json'
            );
}

function setNotification(id_gritter, id_notif) {

    $.gritter.remove(id_gritter);

}
function setAllNotification() {
    $.gritter.removeAll();
}
function readNotification() {

    var notif = JSON.parse(sessionStorage.getItem("notification")) || [];

    if (notif.length !== 0) {
        if (!('Notification' in window)) {
            $.gritter.add({
                title: "Notification",
                text: "vous avez " + notif.length + " notification(s) non lue(s)",
                position: 'bottom-right',
                image: 'photo/logo.png',
                sticky: true,
                fade_in_speed: 500,
                fade_out_speed: 700,
                time: 6000
            });
            /* for (var i = 0; i < notif.length; i++) {
             
             id_gritter = $.gritter.add({
             title: notif[i].recepteur,
             // (string | mandatory) the text inside the notification
             text: notif[i].message + "<br> <a href='javascript:;' onclick='setNotification(" + (id_gritter + 1) + "," + notif[i].id + ")' style='currsor:pointer'>Marquer comme lue <span class='icon icon-eye-open'></span></a>" +
             "&nbsp;&nbsp;<a href='javascript:;' onclick='setAllNotification()' style='currsor:pointer'>Tout fermer <span class='icon icon-eye-close'></span></a>",
             position: 'bottom-right',
             // (string | optional) the image to display on the left
             image: 'photo/logo.png',
             //class_name: 'gritter-light',
             // (bool | optional) if you want it to fade out on its own or just sit there
             sticky: true,
             fade_in_speed: 500, // how fast notifications fade in
             fade_out_speed: 700, // how fast the notices fade out
             time: 6000 // hang on the screen for...
             
             });
             }*/
        } else {


            var title = "CCMANAGER";
            var options = {
                icon: "photo/logo.png",
                renotify: false,
                data: "",
                tag: true,
                requireInteraction: false,
                silent: false,
                body: "Vous avez " + notif.length + " notification(s) non lue(s)"
            };

            if (sessionStorage.getItem("size-notif") === null) {
                var notification2 = new Notification(title, options);

                getNotiBureau(notif);

            } else {
                getNewNoti();
            }
            /**
             * save the notification size for the next access to limit plemty notification
             */
            sessionStorage.setItem("size-notif", "taille notification");


        }

    }
}
var l = 0;

function getNotiBureau(notif) {

    setTimeout(function () {
        if (l === notif.length) {

        } else {
            var notification2 = new Notification(notif[l].titre, {icon: "assets/images/1.png", body: notif[l].message, requireInteraction: true});
            l++;
            getNotiBureau(notif);
        }

    }, 6000);
}

//charger les nouvelles notification toutes les 1s s'il y'en a
function updateNotif() {
    setTimeout(function () {
        var Lastnotif = JSON.parse(sessionStorage.getItem("last-notif")) || [];

        getAllNotifications();
        var NewNotification = JSON.parse(sessionStorage.getItem("last-notif")) || [];

        var newNotif = [];
        newNotif = NewNotification;

        if (newNotif.length !== 0) {
            for (var j = 0; j < newNotif.length; j++) {
                for (var i = 0; i < Lastnotif.length; i++) {
                    if (newNotif[j].id === Lastnotif[i].id) {
                        newNotif.splice(j, 1);
                    }
                }
            }


        }
        // alert(newNotif.length);
        if (newNotif.length !== 0) {
            var notification2 = new Notification(newNotif[newNotif.length - 1].recepteur, {silent: false, icon: "photo/logo.png", body: newNotif[newNotif.length - 1].message, requireInteraction: true});
        }

        updateNotif();
    }, 1000);
}
var m = 0;
function getNewNoti() {
    var Lastnotif = JSON.parse(sessionStorage.getItem("last-notif")) || [];

    getAllNotifications();
    var NewNotification = JSON.parse(sessionStorage.getItem("last-notif")) || [];

    var newNotif = [];
    newNotif = NewNotification;
    if (newNotif.length !== 0) {
        for (var j = 0; j < newNotif.length; j++) {
            for (var i = 0; i < Lastnotif.length; i++) {
                if (newNotif[j].id === Lastnotif[i].id) {
                    newNotif.splice(j, 1);
                }
            }
        }

    }
    readNewNotification(newNotif);
}

function readNewNotification(newNotif) {
    setTimeout(function () {
        if (m === newNotif.length) {
            getNewNoti();
        } else {
            var notification2 = new Notification(newNotif[m].recepteur, {silent: false, icon: "photo/logo.png", body: newNotif[m].message, requireInteraction: true});
            m++;
            readNewNotification(newNotif);
        }

    }, 6000);
}
// charger tous les differents nouvelles notifications

function drawNotification(data) {
    var mainNotif = $(".main_notification");
    mainNotif.html("");
    var $head = '<li class="dropdown-menu-header">' +
            '<h6 class="dropdown-header m-0">' +
            '<span class="grey darken-2">Notifications</span>' +
            '<span class="notification-tag badge badge-default badge-danger float-right m-0 notif_number">' + data.length + '</span>' +
            '</h6>' +
            ' </li>';
    mainNotif.append($head);
    var link = "";
    if ($(".notification").attr("entity") === "user") {
        link = "ticket";
    } else {
        link = "ticketClient";
    }
    
    if (data.length <= 4) {


        for (var i = 0; i < data.length; i++) {
            var $message;
            if (data[i].message.length > 100) {
                $message = data[i].message.substring(0, 99) + " <strong>lire la suite ...</strong>";
            } else {
                $message = data[i].message;
            }

            var $body = '<li class="scrollable-container media-list">' +
                    '<a href="' + link + '?q=notifications&action=jkdhfoldg458dgbjdg478962">' +
                    '<div class="media">' +
                    '<div class="media-left align-self-center"><i class="ft-plus-square icon-bg-circle bg-cyan"></i></div>' +
                    '<div class="media-body">' +
                    ' <h6 class="media-heading">' + data[i].titre + '</h6>' +
                    '<p class="notification-text font-small-3 text-muted">' + $message + '</p>' +
                    ' <small>' +
                    '   <time class="media-meta text-muted" datetime="2015-06-11T18:29:20+08:00">' + data[i].dateNotif + '</time>' +
                    '  </small>' +
                    '</div>' +
                    '</div>' +
                    '</a>' +
                    ' </li>';
            mainNotif.append($body);
        }
    } else {
        for (var i = 0; i < 5; i++) {
            var $message;
            if (data[i].message.length > 100) {
                $message = data[i].message.substring(0, 99) + " <strong>lire la suite ...</strong>";
            } else {
                $message = data[i].message;
            }
            var $body = '<li class="scrollable-container media-list">' +
                    '<a href="' + link + '?q=notifications&action=jkdhfoldg458dgbjdg478962">' +
                    '<div class="media">' +
                    '<div class="media-left align-self-center"><i class="ft-plus-square icon-bg-circle bg-cyan"></i></div>' +
                    '<div class="media-body">' +
                    ' <h6 class="media-heading">' + data[i].titre + '</h6>' +
                    '<p class="notification-text font-small-3 text-muted">' + $message + '</p>' +
                    ' <small>' +
                    '   <time class="media-meta text-muted" datetime="2015-06-11T18:29:20+08:00">' + data[i].dateNotif + '</time>' +
                    '  </small>' +
                    '</div>' +
                    '</div>' +
                    '</a>' +
                    ' </li>';
            mainNotif.append($body);
        }
    }

    var $footer = '<li class="dropdown-menu-footer"><a class="dropdown-item text-muted text-center" href="' + link + '?q=notifications&action=jkdhfoldg458dgbjdg478962">tout lire</a></li>';
    mainNotif.append($footer);

}
function getNewNotifications(IdUser) {

    var $ul = $(".notification");
    setTimeout(function () {
        // on lance une requête AJAX
        var model = "";
        if ($ul.attr("entity") === "user") {
            model = "getUserssNotification";
        } else {
            model = "getClientsNotification";
        }
        $.post(
                'ticket',
                {"id": IdUser, "action": "model", model: model, "q": "vue"},
                function (data) {
                    var notif = [];
                    for (var i = 0; i < data.length; i++) {
                        if (data[i].etat === 0) {
                            notif.push(data[i]);
                        }
                    }
                    $(".notif_number").html(notif.length);
                    drawNotification(notif);

                },
                'json'
                );
        getNewNotifications(IdUser); // on relance la fonction
    }, 3000); // on exécute le chargement toutes les 5 secondes


}

$(function () {
    sessionStorage.setItem("iduser", $(".notification").attr("id"));
    var IdUser = sessionStorage.getItem("iduser");


    getNewNotifications(IdUser);
    getAllNotifications();


    setTimeout(function () {
        readNotification();
    }, 3000);

});