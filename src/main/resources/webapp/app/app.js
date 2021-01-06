"use strict";
angular.module('LeLocApp', ['ngRoute', 'ngResource', 'chieffancypants.loadingBar', 'ngSanitize', 'fcsa-number', 'ui.bootstrap', 'ngStorage', 'ngMessages', 'ngPromiseExtras', 'ngOnlyNumberApp', 'ngCookies']); // hateoas // there is $localStorage and $sessionStore in ngStore module
var lelocAPP = angular.module('LeLocApp');
lelocAPP.value('alert', window.alert);
lelocAPP.value('localStorage', window.localStorage);

    lelocAPP.config(function ($routeProvider, $provide, $httpProvider, cfpLoadingBarProvider, $logProvider) {

        $routeProvider
            .when('/', {
                title : 'LeLocApp - Best application to find a home for yourself',
                templateUrl : '../app/view/pages/home.html'
                //controller : 'homeController'
            })
            .when('/login', {
                title : 'LeLocApp - Login',
                templateUrl : '../app/view/pages/login.html'
                //controller : 'loginController'
            })
            .when('/register', {
                title : 'Create An Account easly',
                templateUrl : '../app/view/pages/register.html'
                //controller : 'registerController'
            })
            .when('/search', {
                title : 'Search by criterias',
                templateUrl : '../app/view/pages/search.html'
                //controller : 'barSearchController'
            })
            .when('/adsforfree', {
                title : '',
                templateUrl : '../app/view/pages/advert_step1.html',
                //controller : 'selectCatControlller'
            })
            .when('/addetail/:catid/:opetypeid/:subcatid', {
                title : '',
                templateUrl : '../app/view/pages/advert_step2.html',
                //controller : 'adDetailController'
            })
            .when('/ad/:realestateid/:name', {
                title : '',
                templateUrl : '../app/view/pages/ad.html'
                //controller : 'adController'
            }).otherwise('/');

        /* .when('/ad', {     '/ad/:parameter koyabilirsin buraya' ve sonra bu view in controlunda $routeParams inject edip değişkene ulaşabilirsin
                            // yani '/ad/adtitle/..' :> $routeParams.aditle yakalanır ve sayafaya bu string basılır
                title : '',
                templateUrl : 'addetail/ad.html',
                controller : 'adController'
            });*/

        /*
        * The custom “X-Requested-With” is a conventional header sent by browser clients, and it used to be the default in Angular but they took it out in 1.3.0. Spring Security responds to it by not sending a “WWW-Authenticate” header in a 401 response, and thus the browser will not pop up an authentication dialog (which is desirable in our app since we want to control the authentication).
        */
        //$httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
        //$routeProvider.otherwise({redirectTo: '/'});
        //$httpProvider.interceptors.push('authInterceptor');


      /*  $httpProvider.defaults.useXDomain = true;
        delete $httpProvider.defaults.headers.common['X-Requested-With'];
*/
        $provide.decorator("$sanitize", function ($delegate, $log) {
            return function (text, target) {

                var result = $delegate(text, target);
                $log.info("$sanitize input: " + text);
                $log.info("$sanitize output: " + result);

                return result;
            };
        });
           // $httpProvider.defaults.headers.put['Content-Type'] = 'application/text/plain;charset=UTF-8';
          $httpProvider.defaults.headers.common = {};
          $httpProvider.defaults.headers.post = {};
          $httpProvider.defaults.headers.put = {};
          $httpProvider.defaults.headers.patch = {};

        $logProvider.debugEnabled(true);// **TODO** set false in production mode
        cfpLoadingBarProvider.includeSpinner = false;
         /* HateoasInterceptorProvider.transformAllResponses();
        HateoasInterfaceProvider.setLinksKey("links");//This is the default one also*/
    }).run(['$rootScope', '$location', '$cookieStore', '$http', '$log',
        function ($rootScope, $location, $cookieStore, $http, $log) {
            // keep user logged in after page refresh
            $rootScope.globals = $cookieStore.get('globals') || {};
            if ($rootScope.globals.currentUser) {
                $log.debug("FOUND AUTHHHHHHHH : " + JSON.stringify($rootScope.globals.currentUser));
                $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata; // jshint ignore:line
            } else{
                $log.error("AUTH NON FOUND!!!!!!!!!!!!!!!!!!!!!!!!!!");
            }
            $http.defaults.headers.put['Content-Type'] = 'application/json;charset=UTF-8';
            $http.defaults.headers.post['Content-Type'] = 'application/json;charset=UTF-8';
            $rootScope.$on('$locationChangeStart', function (event, next, current) {
                // redirect to login page if not logged in
               /* if ($location.path() !== '/login' && !$rootScope.globals.currentUser) {
                    $location.path('/home');
                } else if($location.path() === '/login' && $rootScope.globals.currentUser){
                    $location.path('/home');
                }*/
            });
        }]);

lelocAPP.controller('msgController', ['$scope', '$filter', '$log', '$timeout', 'helperService', function($scope, $filter, $log, $timeout, helperService) {
   $scope.msgData = {};


  $scope.$watch(function () { return helperService.getisShowMsg(); }, function (newValue, oldValue) {
      if(newValue){
          $scope.msgData = helperService.getMSgData();
          $timeout(function(){
            helperService.setisShowMsgFalsaBack();
        }, 7000); // After 7 second... message box will close by itself;
      }
  });


}]);
function arrayContains(obj, list) {
    var x;
    for (x in list) {
        if (list.hasOwnProperty(x) && list[x] === obj) {
            return true;
        }
    }

    return false;
};

function logError(response){
    console.log("CUSTOM ERR CODE: " + response.data.apiErrCode + " ERRMSG : " + response.data.apiMessage + " EXCETIONMSG : " + response.data.message);
};
