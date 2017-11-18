/**
 * Created by Michał on 2017-11-01.
 */

var mainApp = angular.module("viewer", []);

mainApp.controller('guestController', function($scope) {
    $scope.guest = {
        name: "Nieznajomy",

        getName: function() {
            var studentObject;
            return $scope.guest.name;
        }
    };
});