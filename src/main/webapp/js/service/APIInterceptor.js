function accessTokenHttpInterceptor($window) {
    return {
        //For each request the interceptor will set the bearer token header.
        request: function ($config) {
            console.log("access");
            //Fetch token from cookie
            var token = $window.sessionStorage.getItem('userInfo-token');

            //set authorization header
            $config.headers['Authorization'] = 'Bearer ' + token;
            return $config;
        },
        response: function (response) {
            //if you get a token back in your response you can use
            //the response interceptor to update the token in the
            //stored in the cookie
            if (response.config.url === 'api/token' && response.config.data.tokenData) {
                //fetch token
                var token = response.config.data.tokenData;

                //set token
                $window.sessionStorage.setItem('userInfo-token', token);
            }
            return response;
        }
    };
}

angular.module('app',[]).factory('APIInterceptor', accessTokenHttpInterceptor).
config(function($httpProvider){$httpProvider.loginService.push('APIInterceptor');
}).run(function($http){
    $http.get('http://localhost:8080/login').valueOf().
        then(function (response) {
            console.log(response.data.message)
    })
});