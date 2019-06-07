/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function ManageNotification() {
    var self = this;
    self.allNotification = [];
    return {
        getAllNotificationUser: function (id) {
            $.post(
                    'ticket',
                    {"id": id, "action": "model", model: "getUserssNotification", "q": "vue"},
                    function (data) {

                        self.allNotification = data;
                        console.log( self.allNotification);
                    },
                    'json'
                    );

        }
    };
}

$(document).ready(function () {
    var IdUser = sessionStorage.getItem("iduser");
    var notif = new ManageNotification();
    notif.getAllNotificationUser(IdUser);
    console.log(ManageNotification().allNotification);




});