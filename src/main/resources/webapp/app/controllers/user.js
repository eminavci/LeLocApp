(function () {
   'use strict';
   // this function is strict...
}());
lelocAPP.controller('registerController', ['$scope', '$location', '$filter', '$log', '$timeout', '$http', 'allCitiesService', 'userService', 'helperService', function($scope, $location, $filter, $log, $timeout, $http, allCitiesService, userService, helperService){

    $scope.user = {}; $scope.user.gender = 0;
    $scope.account = {};
    $scope.company = {}; $scope.company.type = "REAL_ESTATE_AGENCY";
    $scope.useraddress = {};
    $scope.companyaddress = {};

    $scope.cities = [];

   allCitiesService.getCities(76).then(function(cityData) {  // this is only run after $http completes
       $scope.cities = cityData.cities;
       $scope.useraddress.city = $scope.cities[0].oid;
       $scope.companyaddress.city = $scope.cities[0].oid;
    });


    $scope.regSubmit = function(){

        $scope.emailError = $scope.user.email !== $scope.user.confirmemail;
        $scope.passwordError = $scope.account.password !== $scope.account.confirmpassword;
        if($scope.passwordError)
            $scope.passwordErrorMsg = "Passwords do not equal eachother";
        else if ($scope.account.password.length < 6){
            $scope.passwordErrorMsg = "Password length must not be less than 6";
            $scope.passwordError = true;
        }
        if($scope.emailError || $scope.passwordError)
            return false;
        $log.debug("aaaa : " + JSON.stringify($scope.user));
        userService.saveUser($scope.user).success(function (response) {
            $scope.user.id = response.oid;
            $scope.account.regType = 1;
            // ############## SAVE ACCOUNT ######################
            if($scope.account.type)
                $scope.account.type = 1;
            else
                $scope.account.type = 0;
            userService.saveAccount($scope.user.id, $scope.account).success(function(response){
                $scope.account.id = response.oid;

                if($scope.account.type){ // So there s a company definition
                    // ################# SAVE COMPANY ####################
                    userService.saveCompany($scope.user.id, $scope.company).success(function(response){
                        $log.info("COMPANY REGISTERED : " + JSON.stringify(response));
                        $scope.company.id = response.oid;
                        // ################# SAVE COMPANY ADDRESS
                        userService.saveAddressForCompany($scope.company.id, $scope.companyaddress.city, $scope.companyaddress).success(function (response){
                            $log.info("saveAddressForCompany succesful");
                        }).error(function(response){
                            logError(response);
                        });
                    }).error(function(response){
                        logError(response);
                    });
                }
                // ######################## SAVE USER ADDRESS ##############################
                $log.info("UASERRRRR ADRES : " + JSON.stringify($scope.useraddress));
                userService.saveAddressForUser($scope.user.id, $scope.useraddress.city, $scope.useraddress).success(function (response){
                    $log.info("saveAddressForUser succesful");
                }).error(function(response){
                    logError(response);
                });

                helperService.initMSg(true, 1, "You are succeesfully registered. Now please check your email address : " + $scope.account.email + "  to acctive your account");
                $location.path('/');

            }).error(function(response) {
                helperService.initMSg(true, 4, response.data.apiMessage);
                logError(response);
                return false;
            });
        }).error(function(response) {
            helperService.initMSg(true, 4, response.data.apiMessage);
            logError(response);
            return false;
        });

        return false;
    };

}]);

lelocAPP.controller('loginController', ['$scope', '$log', '$http', '$rootScope', '$location', 'alert', 'loginService', 'authenticationService', 'helperService', 'userService', function ($scope, $log, $http, $rootScope, $location, alert, loginService, authenticationService, helperService, userService) {

    $scope.credentials = {};

    $scope.login = function(){
        $log.debug("Login starting + " + $scope.credentials);

        authenticationService.Login($scope.credentials,function(response){
                if(response.error){
                    $log.debug("LOGIN CONTROLLER ERROR : " + JSON.stringify(response));
                    helperService.initMSg(true, 4, "Youremail or password is invalid. LOGIN FALIED!");
                } else {
                    authenticationService.SetCredentials($scope.credentials.email, $scope.credentials.password, "TEST", "TEST");
                    $log.debug("LOGIN CONTROLLER SUCCES : " + JSON.stringify(response));
                    userService.fidByEmail($scope.credentials.email).success(function (response) {
                        authenticationService.SetCredentials($scope.credentials.email, $scope.credentials.password, response.firstName, response.lastName);
                        helperService.initMSg(true, 1, "Welcome " + response.firstName + " " + response.lastName);
                        $location.path("/");
                    }).error(function(err){
                        authenticationService.ClearCredentials();
                        $log.debug("USER FANELLYERROR : " + JSON.stringify(err));
                        helperService.initMSg(true, 4, "An error occured while logging you!!! Try again");
                    });
                }
        });

    };


    $scope.testim = function() {
        $http.get("http://localhost:8080/message/1").then(function(response){
            $log.info(JSON.stringify(response))
        }, function(errr){
            $log.error("LOOOOO : " + JSON.stringify(errr));
        });
    };


}]);

lelocAPP.controller('userBarController', ['$scope', '$log', '$rootScope', '$location', 'userService', 'helperService', function ($scope, $log, $rootScope, $location, userService, helperService) {

}]);
