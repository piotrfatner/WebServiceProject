var app = angular.module('app',['ngRoute']);

app.controller('userSideCtrl', ['$scope', 'userSideService', function ($scope, userSideService) {

    $scope.setScope = function(){
        userSideService.setScope($scope);

    };

    $scope.checkTokenValid = function () {
        userSideService.checkTokenValid();
    };

    $scope.logoutClearTokenFromWindow = function () {
        userSideService.logoutClearTokenFromWindow();
    };

    $scope.getUserDatas = function () {
        userSideService.getUserDatas();
    };

    window.setInterval(function() {userSideService.rotateImages();},7000);


    $scope.setScope();


}]);
