var app = angular.module('app');
app.service('loginService',['$http','$window', function ($http, $window) {
    var self = this;
    self.scope = null;
    var loginDto;
    self.setScope = function(scope){
        self.scope = scope;
        self.scope.invalidCreditentials = false;
    };
    this.loginAction = function () {
        console.log(document.getElementById("login").value);
        console.log(document.getElementById("pwd").value);

            console.log("Logged");
            loginDto ={ login: document.getElementById("login").value,
                password: document.getElementById("pwd").value
            };
            console.log(loginDto);
            $http.post("/login", loginDto).success(
                function (data) {
                    console.log(data);
                    if(data == ''){
                        self.scope.invalidCreditentials = true;
                    }
                    else{
                        self.scope.invalidCreditentials = false;
                        $window.sessionStorage.setItem('userInfo-token',data);
                        window.location.href="../../views/home.html";

                    }
                }
                ).error("GTOx2!");
    }

}]);