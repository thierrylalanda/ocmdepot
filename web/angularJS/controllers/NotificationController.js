
(function () {

    var app = angular.module('ccmanager.notification', ["ui-notification"]);


    function NotificationController($scope, $http, Notification) {
        // $location.path('/');
        $scope.AllNotification = [];
        $scope.IdUser = sessionStorage.getItem("iduser");

        function getAllNotification() {
            var model = "";
            if ($(".notification").attr("entity") === "user") {
                model = "getUserssNotification";
            } else {
                model = "getClientsNotification";
            }
            $http({url: "ticket?action=model&q=vue&model=" + model, method: 'post', params: {id: $scope.IdUser}}).then(function (response) {
                $scope.AllNotification = response.data.reverse();
            }, function (error) {
                Notification.error({message: 'erreur de communication avec le serveur', delay: 5000, positionY: 'bottom', positionX: 'right'});
            });
        }
        getAllNotification();

        /*$.post(
         'ticket',
         {"id": id, "action": "model", model: "getUserssNotification", "q": "vue"},
         function (data) {
         
         self.allNotification = data;
         console.log(self.allNotification);
         },
         'json'
         );*/
        $scope.checkbox = false;

        $scope.setAllNotification = function () {
            var i = 0;
            var allnotif = [];
            $("input[type=checkbox]:checked").each(function (index) {
                if (parseInt($(this).attr("value")) !== 0) {
                    allnotif.push($(this).attr("value"));
                }

            });

            if (allnotif.length === 0) {
                Notification.error({message: 'Sélectionner au moins un élément', delay: 5000, positionY: 'bottom', positionX: 'right'});
            } else {
                $http({url: "ticket?action=model&q=vue&model=setUserssNotification", method: 'post', params: {id: allnotif}}).then(function (response) {
                    getAllNotification();
                }, function (error) {
                    Notification.error({message: 'erreur de communication avec le serveur', delay: 5000, positionY: 'bottom', positionX: 'right'});
                });


            }

        };
        $scope.setNotification = function (id) {

        };
    }



    app.controller("NotificationController", ["$scope", "$http", "Notification", NotificationController]);

}());


