/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function () {


    var app = angular.module('notificationIndex', ["ngMaterial", "ngRoute", "ui.bootstrap", "ngAnimate", "subufu.notification"]);
    function DemandeController($scope, $http, $rootScope, NotificationUser) {
        // $location.path('/');
        $scope.setNotification = function (index) {
            var notif = $scope.AllNotification[index];
           
            /*  NotificationUser.setEtatNotification({servlet: "notifications", action: "notifications"}, {id_notif: notif.id, vue: "rien"}).$promise.then(function (data) {
             
             $rootScope.AllNotification = data.reverse();
             });*/

            $http({url: "notifications?action=setNotifications&vue=vue", method: 'get', params: {id_notif: notif.id}}).then(function (response) {
               
                $rootScope.AllNotification = response.data.reverse();
            }, function (error) {
                console.log("erreur de communication avec le serveur");
            });
        };
    }
    app.controller("DemandeController", ["$scope", "$http", '$rootScope', '$location', "NotificationUser", DemandeController]);
    app.directive('ngNotification', function () {
        return {
            restrict: 'E',
            templateUrl: 'angularFrontend/pages/Notifications/NotificationTemplate.jsp',
            link: function (scope, element, attrs) {

            }
        };
    });
    app.directive('ngNotificationsecond', function () {
        return {
            restrict: 'E',
            templateUrl: 'angularFrontend/pages/Notifications/NotificationTemplates.jsp',
            link: function (scope, element, attrs) {

            }
        };
    });
    app.run(['$location', '$rootScope', "NotificationUser", function ($location, $rootScope, NotificationUser) {
            NotificationUser.getAllNotificationByIdUser({servlet: "notifications", action: "AllNotifications"}, {}).$promise.then(function (data) {

                $rootScope.AllNotification = data.reverse();
            });
            $rootScope.$on('$routeChangeSuccess', function (event, current, previous) {
                $rootScope.title = current.$$route.title;
            });
        }]);
}());
