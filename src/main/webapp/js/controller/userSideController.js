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

    $scope.getBooksForUser = function () {
        return userSideService.getBooksForUser();
    };

    $scope.getAllBooks = function () {
        return userSideService.getAllBooks();
    };
    $scope.checkboxChange= function (id) {
        userSideService.checkboxChange(id);
    };

    $scope.hireBook = function () {
        userSideService.hireBook();
    }
    function ckChange(ckType) {
        var ckName = document.getElementById(ckType.id);

        for (var i = 0; i < ckName.length; i++) {
            if (!ckType.checked) {
                ckName[i].disabled = false;
            } else {
                if (!ckName[i].checked) {
                    ckName[i].disabled = true;
                } else {
                    ckName[i].disabled = false;
                }
            }
        }

    };
    window.setInterval(function() {userSideService.rotateImages();},7000);


    $scope.setScope();


}]);
