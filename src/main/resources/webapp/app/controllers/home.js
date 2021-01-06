lelocAPP.controller('homeController', ['$scope', '$filter', '$log', '$timeout', '$http', 'searchCatTemplateService', '$location', 'catOpeTypeService', 'regionService', 'cityService', 'townService', 'subCategoryService', 'realEstateService', 'helperService', function($scope, $filter, $log, $timeout, $http, searchCatTemplateService, $location, catOpeTypeService, regionService, cityService, townService, subCategoryService, realEstateService, helperService) {

    $scope.category = { value: undefined };
    $scope.opeType;
    $scope.subcategory;
    $scope.region = null;
    $scope.city = "";
    $scope.town;
    $scope.fromwho = "INDIVIDUAL";
    $scope.minprice;
    $scope.minprice;
    $scope.maxprice;
    $scope.currency;

    $scope.catList = searchCatTemplateService.myArr;

    $scope.category = $scope.catList[0].id;


    $scope.regions = [];
    $scope.cities = [];
    $scope.towns = [];
    $scope.opeTypes = [];
    $scope.subCategories = [];

    $scope.catSelected = function(index){
        var selectedCat = $scope.catList[index];
        $scope.bgurlim = selectedCat.bgimage;
        // CatOpe Typefield
        if(selectedCat.id !== null){
            catOpeTypeService.getOpeTypes(selectedCat.id).then(function(opeTypesData) {  // this is only run after $http completes
               $scope.opeTypes = opeTypesData.catOpeTypeListRes;
            });
        }
    };

    // ************* Getting all subcategories of opetype
    $scope.catOpeTypeChanged = function(){
        if($scope.opeType !== null){
            subCategoryService.getSubCategories($scope.opeType).then(function(subCatData) {  // this is only run after $http completes
               $scope.subCategories = subCatData.subCategoryListRes;
            });
        }
    };

    // ************* Getting all regions of france
    regionService.async(76).then(function(result) {
        $scope.regions = result.regions;
    });


    // *************** Get cities by region selection
    $scope.regionSelected = function(){
        $scope.cities = [];
        $scope.towns = [];
        if($scope.region !== null){
            cityService.getCities($scope.region).then(function(cityData) {  // this is only run after $http completes
               $scope.cities = cityData.cities;
            });
        }
    };

    // *************** Get towns by city selection
    $scope.citySelected = function(){
        $scope.towns = [];
        if($scope.city !== null){
            townService.getTowns($scope.city).then(function(townData) {  // this is only run after $http completes
               $scope.towns = townData.towns;
            });
        }
    };


    $scope.catSelected(0);
    $scope.search = function(){
        $location.path('/search');
    };

    $scope.searchonmap = function() {
        $http.jsonp('http://www.reddit.com/r/cats.json?limit=50&jsonp=JSON_CALLBACK').success(function (data) {
            $log.log("AAAAAAAA : " + data.data)
        });
    };

    $scope.realEstates = {};

    realEstateService.showCase().success(function(response){
        $scope.realEstates = response.realEstateListRes;
        $log.debug("MY DATA : " + JSON.stringify(response));
    }).error(function(response){
        helperService.initMSg(true, 4, response.apiMessage);
    });


}]);
