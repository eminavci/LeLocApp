var myApp = angular.module('LeLocApp', ['ngRoute', 'ngResource', 'chieffancypants.loadingBar', 'ngSanitize', 'fcsa-number', 'ui.bootstrap']);

myApp.config(function ($routeProvider, $provide, cfpLoadingBarProvider) {
    $routeProvider
        .when('/', {
            title : '',
            templateUrl : 'view/home/home.html',
            controller : 'homeController'
        })
        .when('/login', {
            title : '',
            templateUrl : 'view/signin/login.html',
            controller : 'loginController'
        })
        .when('/register', {
            title : '',
            templateUrl : 'view/signup/register.html',
            controller : 'registerController'
        })
        .when('/search', {
            title : '',
            templateUrl : 'view/search/barsearchresult.html',
            controller : 'barSearchController'
        })
        .when('/adsforfree', {
            title : '',
            templateUrl : 'view/advert/step1/pa_step1.html',
            controller : 'selectCatControlller'
        })
        .when('/addetail', {
            title : '',
            templateUrl : 'view/advert/step2/pa_step2.html',
            controller : 'adDetailController'
        })
        .when('/ad', {
            title : '',
            templateUrl : 'addetail/ad.html',
            controller : 'adController'
        });

    /* .when('/ad', {     '/ad/:parameter koyabilirsin buraya' ve sonra bu view in controlunda $routeParams inject edip değişkene ulaşabilirsin
                        // yani '/ad/adtitle/..' :> $routeParams.aditle yakalanır ve sayafaya bu string basılır
            title : '',
            templateUrl : 'addetail/ad.html',
            controller : 'adController'
        });*/

    $routeProvider.otherwise({redirectTo: '/'});

    $provide.decorator("$sanitize", function ($delegate, $log) {
        return function (text, target) {

            var result = $delegate(text, target);
            $log.info("$sanitize input: " + text);
            $log.info("$sanitize output: " + result);

            return result;
        };
    });


    cfpLoadingBarProvider.includeSpinner = false;

});

myApp.service('searchCatTemplateService', function(){

    this.myArr = [];
    this.myArr.push({id: 1, catName : "Residential",        pos_x: "-4",  pos_y: "-33", bgimage: '/static/icons/konut.jpg'});
    this.myArr.push({id: 2, catName : "Commercial",         pos_x: "-31", pos_y: "-33", bgimage: '/static/icons/is_yeri.jpg'});
    this.myArr.push({id: 3, catName : "Land",               pos_x: "-61", pos_y: "-33", bgimage: '/static/icons/arsa.jpg'});
    this.myArr.push({id: 4, catName : "Buildings",          pos_x: "-90", pos_y: "-33", bgimage: '/static/icons/bina.jpg'});
    this.myArr.push({id: 5, catName : "Time Share",         pos_x: "-121", pos_y: "-34", bgimage: '/static/icons/devremulk.jpg'});
    this.myArr.push({id: 6, catName : "Tourism Facilities", pos_x: "-150", pos_y: "-33", bgimage: '/static/icons/turistik_tesis.jpg'});
});

myApp.filter('limit', function() {
    return function (input, value) {
        return input.substr(0,value);
    };
});
myApp.filter('htmlize', ['$sce', function($sce){
    return function(val) {
        return $sce.trustAsHtml(val);
    };
}]);


myApp.controller('homeController', ['$scope', '$filter', '$log', '$timeout', '$http', 'searchCatTemplateService', '$location', 'cfpLoadingBar', function($scope, $filter, $log, $timeout, $http, searchCatTemplateService, $location, cfpLoadingBar){

    $scope.reRadioModel ={
        value: undefined
    };
    $scope.catList = searchCatTemplateService.myArr;

    $scope.initCatTypeRadio = $scope.catList[0];
    $scope.reRadioModel = $scope.initCatTypeRadio.id;

    $scope.bgurlim = $scope.initCatTypeRadio.bgimage;

    $scope.changeSrcBg = function(imgName){
        $scope.bgurlim = imgName;
    };

    $scope.search = function(){
        $location.path('/search');
    };

    $scope.searchonmap = function(){
            // Deneme Amaçlı. progres bar için
        $http.jsonp('http://www.reddit.com/r/cats.json?limit=50&jsonp=JSON_CALLBACK').success(function(data) {
            $log.log("AAAAAAAA : " + data.data)
        });

        //cfpLoadingBar.start();
        //alert("Oleyyy MApppp");
    };




}]);

myApp.controller('loginController', ['$scope', '$filter', '$log', '$timeout', '$http', function($scope, $filter, $log, $timeout, $http){


}]);

myApp.controller('registerController', ['$scope', '$filter', '$log', '$timeout', '$http', function($scope, $filter, $log, $timeout, $http){


}]);

myApp.controller('barSearchController', ['$scope', '$filter', '$log', '$timeout', '$http', function($scope, $filter, $log, $timeout, $http){


}]);

myApp.controller('selectCatControlller', ['$scope', '$filter', '$log', '$timeout', '$http', function($scope, $filter, $log, $timeout, $http){

}]);

myApp.controller('adDetailController', ['$scope', '$filter', '$log', '$timeout', '$http', function($scope, $filter, $log, $timeout, $http){
    initPhotoPlugin();
}]);

myApp.controller('adController', ['$scope', '$filter', '$log', '$timeout', '$http', '$modal', function($scope, $filter, $log, $timeout, $http, $modal){

    $scope.imgArr = [];

    for(var i = 0 ; i < 7 ; i++){
        //src attr is a MUST, caption and thumb is optional
        var img = {
            src       : '/static/adimages/emlak' + i + '.jpg',
            caption   : 'Ad Title is here',
            thumb     : '/static/adimages/emlak' + i + '.jpg',
        };
        $scope.imgArr.push(img);
    }

    // carousel initilization
    $scope.selectedImg = {src:  $scope.imgArr[0].src};

    $scope.showTheNext = function(){
        for(var i = 0 ; i < $scope.imgArr.length ; i++){
            if($scope.imgArr[i].src === $scope.selectedImg.src){
                if(i == $scope.imgArr.length - 1){
                    $scope.selectedImg = {src:  $scope.imgArr[0].src};
                } else {
                    $scope.selectedImg = {src:  $scope.imgArr[i+1].src};
                }
                break;
            }
        }
    };

    $scope.open=function(){

        $scope.modalInstance=$modal.open({
            animation: true,
            templateUrl: 'template/pic-modal.html',
            scope: $scope
        });
    };
    $scope.ok = function () {
        $scope.modalInstance.close();
    };

}]);

myApp.controller('alertDiv', ['$scope', '$filter', '$log', '$timeout', '$http', function($scope, $filter, $log, $timeout, $http){

}]);







