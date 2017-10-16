var app = angular.module('app');


app.service('userSideService',['$http','$window', function ($http, $window) {
    var self = this;
    var rotator;
    var rotatedImageId = 'rotator';
    var images = ['/images/1.jpg','/images/2.jpg', '/images/3.jpg','/images/4.jpg'];
    var num =0;
    self.scope = null;
    self.setScope = function(scope){
        self.scope = scope;
        this.checkTokenValid();
    };

    this.checkTokenValid = function () {
        if(!$window.sessionStorage.getItem('userInfo-token')){
            $window.sessionStorage.setItem('userInfo-token','undefined');
        }
        console.log("i am in");
        console.log($window.sessionStorage.getItem('userInfo-token'));
        $http.post("/checkTokenValid", $window.sessionStorage.getItem('userInfo-token')).success(
            function(data){
                console.log(data);
            console.log("hello");
            return true;
    }
).error(
    function(){
        console.log("goodbye1!");
        window.location.href = "../../index.html";
        return false;

    })
    };

    this.logoutClearTokenFromWindow = function () {
        $window.sessionStorage.setItem('userInfo-token','');
        window.location.href = "../../index.html";
    };

    this.getUserDatas = function () {
        $http.get("/getUserDatas/"+$window.sessionStorage.getItem('userInfo-token')).success(
            function (data) {
                self.scope.userDatas = data;
                console.log(data);
            }
        ).error(
            function () {
                return "";
            }
        )
    };

    this.getAllBooks = function () {
        $http.get("/getAllBooks/"+$window.sessionStorage.getItem('userInfo-token')).success(
            function (data) {
                self.scope.allBooks = data;
                console.log(data);
            }
        ).error(
        function () {
            return "";
        }
    )};

    this.checkboxChange = function (id) {
        var ckName = document.getElementsByName("checkedBooks");
        for (var i = 0; i<ckName.length;i++){
            console.log(id);
            console.log(id.book);
            console.log(id.checked);
            console.log(ckName[i].id);
            if(document.getElementById(id.book.bookId).checked){
                if (id.book.bookId == ckName[i].id) {
                }
                else {
                    console.log("dwaaa");
                    document.getElementById(ckName[i].id).disabled = true;
                }
                document.getElementById("hireBookButton").style.display = 'block';
            }
            else {
                if (id.book.bookId == ckName[i].id) {
                }
                else {
                    document.getElementById(ckName[i].id).disabled = false;
                }
                document.getElementById("hireBookButton").style.display = 'none';
            }
        }
    };

    this.rotateImages = function () {

        if (!rotator)
            rotator = document.getElementById(rotatedImageId);
        if (!rotator)
            return;

        var len = images.length;
        rotator.src = images[num++];
        if (num == len) {
            num = 0;
        }
    };

    this.getBooksForUser = function () {
        $http.get("/getBookHireForUser/"+$window.sessionStorage.getItem('userInfo-token')).success(
            function (data) {
                console.log(data);
                self.scope.allBooksForUser = data;
                console.log(self.scope.allBooksForUser);
            }
        ).error(
            function () {
                return "";
            }
    )
    }
}]);