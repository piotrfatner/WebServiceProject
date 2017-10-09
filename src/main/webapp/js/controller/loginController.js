var app = angular.module('app',[]);
app.controller('loginCtrl', ['$scope', 'loginService', function ($scope, loginService) {

    $scope.setScope = function(){
        loginService.setScope($scope);
    };
    $scope.loginAction = function () {
        loginService.loginAction();
    };
    $scope.setScope();

}]);