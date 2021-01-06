(function () {
   'use strict';
   // this function is strict...
}());
lelocAPP.controller('selectCatControlller', ['$scope', '$location', '$filter', '$log', '$timeout', 'userService', 'allCategoryService', 'helperService', 'catOpeTypeService', 'subCategoryService', function($scope, $location, $filter, $log, $timeout, userService, allCategoryService, helperService, catOpeTypeService, subCategoryService){

    $scope.cats = [];
    $scope.opeTypes = [];
    $scope.subCategories = [];

    $scope.catSel;
    $scope.opeSel;
    $scope.subCatSel;
    $scope.constactedUrl = "#/addetail";


    allCategoryService.getCats().success(function (response) {
        $scope.cats = response.categoryRes;
    }).error(function (response){
        helperService.initMSg(true, 4, response.apiMessage);
    });

    $scope.catSelected = function(cat){
        catOpeTypeService.getOpeTypes(cat.oid).then(function(opeTypesData) {  // this is only run after $http completes
           $scope.subCategories = []; $scope.subCatSel = null; $scope.opeSel = null;
           $scope.opeTypes = opeTypesData.catOpeTypeListRes;
           $scope.catSel = cat;
            $scope.constactedUrl = "#/addetail/" +  $scope.catSel.oid + "/0/0";
        });
    };

    $scope.catOpeTypeSelected = function(ope){
        subCategoryService.getSubCategories(ope.oid).then(function(subCatData) {  // this is only run after $http completes
           $scope.subCatSel = null;
           $scope.subCategories = subCatData.subCategoryListRes;
           $scope.opeSel = ope;
            $scope.constactedUrl = "#/addetail/" +  $scope.catSel.oid +"/" +  $scope.opeSel.oid + "/0";
        });
    };

    $scope.subCatSelected = function(subc){
        $scope.subCatSel = subc;
        $scope.constactedUrl = "#/addetail/" +  $scope.catSel.oid +"/" +  $scope.opeSel.oid + "/" +  $scope.subCatSel.oid;
    };

}]);

lelocAPP.controller('realestateDetController', ['$scope', '$routeParams', '$location', '$window', '$filter', '$log', '$timeout', 'userService', 'helperService', 'catOpeTypeService', 'subCategoryService', 'regionService', 'cityService', 'townService', 'featureService', '$q', 'catByIdService', 'realEstatePostService', function($scope, $routeParams, $location, $window, $filter, $log, $timeout, userService, helperService, catOpeTypeService, subCategoryService, regionService, cityService, townService, featureService, $q, catByIdService, realEstatePostService){
    initPhotoPlugin();

    $scope.catid = $routeParams.catid; $scope.category = {};
    $scope.opetypeid = $routeParams.opetypeid; $scope.catOpeType = {};
    $scope.subcatid = $routeParams.subcatid; $scope.subCategory = {};
    // **TODO** After putting feature to category and catopetypes you can open here
    if($scope.subcatid == undefined || $scope.subcatid == 0){
        helperService.initMSg(true, 3, "Please select subcategory which is coming after category and operation type!");
        $window.history.back();
    }

    catByIdService.findCatById($scope.catid).success(function (response) {
        $scope.category = response;
    }).error(function(response){helperService.initMSg(true, 3, "En error occured please try again later!"); $location.path("/");});
    catByIdService.findOpeTypeById($scope.opetypeid).success(function (response) {
        $scope.catOpeType = response;
    }).error(function(response){helperService.initMSg(true, 3, "En error occured please try again later!"); $location.path("/");});
    catByIdService.findSubCatById($scope.subcatid).success(function (response) {
        $scope.subCategory = response;
    }).error(function(response){helperService.initMSg(true, 3, "En error occured please try again later!"); $location.path("/");});




    $scope.hastMeterSquare = false;

    $scope.address = {};
    $scope.realestate = {};
    $scope.realestate.currency = "EUR";
    $scope.regions = [];
    $scope.cities = [];
    $scope.towns = [];
    $scope.features = [];
    $scope.details = [];

    $scope.ozellik = [];
    $scope.detay = [];

    $scope.$watch('details', function(newOne){
        newOne.forEach(function(item){
            if(item.type == 'METERSQUARE'){
                $timeout(function(){
                   $scope.hastMeterSquare = true;
                }, 3000);
            }
        });
   });

    // ************* Getting all regions of france
    regionService.async(76).then(function(result) {
        $scope.regions = result.regions;
        $scope.address.region = $scope.regions[0].oid;
        $scope.regionChanged();
    });

    $scope.regionChanged = function(){
         $scope.cities = [];
        $scope.towns = [];
        if($scope.address.region !== null && $scope.address.region !== undefined){
            cityService.getCities($scope.address.region).then(function(cityData) {  // this is only run after $http completes
               $scope.cities = cityData.cities;
                $scope.address.city = $scope.cities[0].oid;
                $scope.cityChanged();
            }, function(error){
                $log.debug("ERRORRRRRRRRRRr : " + JSON.stringify(error));
            });
        }
    };

    $scope.cityChanged = function(){
        $scope.towns = [];
        if($scope.address.city !== null && $scope.address.city !==undefined){
            townService.getTowns($scope.address.city).then(function(townData) {  // this is only run after $http completes
               $scope.towns = townData.towns;
                $scope.address.town = $scope.towns[0].oid;
                $scope.townChanged();
            });
        }
    };
    $scope.$watch('realEstateForm.$valid', function(newVal) {
            $log.debug("FORM IS VALID :");
        });
    // ********************** DETAIL OF REALESTATE *******************
    featureService.detailTitles($scope.subcatid).success(function(response){
         $scope.details = response.detailTitleListRes;

           $q.all($scope.details.map(function (item) {
                return featureService.detailDetails(item.oid);
            })).then(function (results) {
                var resultObj = {};
                results.forEach(function (val, i) {
                    $scope.details[i].options = val.data.detailDetailListRes;
                });
            }, function(error){
               $log.error("featureService.detailDetails ERR : " + error);
           });
    }).error(function(response){
        helperService.initMSg(true, 4, 'An error occured. Please try again later!');
        $location.path('/');
    });

    // ********************** FEATURES OF REALESTATE *******************
    featureService.bysubcatid($scope.subcatid).success(function(response){
        $scope.features = response.featureListRes;
          $q.all($scope.features.map(function (item) {
                return featureService.featureOptionsByF(item.oid);
            })).then(function (results) {
                var resultObj = {};
                results.forEach(function (val, i) {
                    $scope.features[i].options = val.data.fOptionsRes;
                });
            }, function(error){
               $log.error("featureService.detailDetails ERR : " + error);
           });
    }).error(function(response){
        helperService.initMSg(true, 4, 'An error occured. Please try again later!');
        $location.path('/');
    })

    $scope.townChanged = function(){

    };

    $scope.firstSubmit = false;

    $scope.reSubmit = function(isinValid){
        if(isinValid && false) {
            helperService.initMSg(true, 3, "Your form is not valid. Please fill all required fields!");
            $scope.firstSubmit = true;
            return false;
        }

        $log.debug("REAL ESTATE : " + JSON.stringify($scope.realestate));
        $log.debug("ADRESS : " + JSON.stringify($scope.address));

        var selectedFeature = [];
        var selectedDetails = [];

        for(i =0; i< $scope.detay.length; i++){
            if(!($scope.detay[i] === undefined || $scope.detay[i] === null)){
                selectedDetails.push($scope.detay[i]);
            }
        }


        for(j =0; j< $scope.ozellik.length; j++){
            if(!($scope.ozellik[j] === null || $scope.ozellik[j] === undefined)){
                angular.forEach($scope.ozellik[j], function(value, key) {
                    if(value){
                        selectedFeature.push(key);
                    }
                });
            }
        }

        $log.debug("DETSSSS : " + selectedDetails);
        $log.debug("FEATSSS : " + selectedFeature);



        realEstatePostService.saveRealEstate(1, $scope.subcatid, $scope.realestate).success(function (response) {
            $scope.dataRealEstate = response;

            realEstatePostService.saveRealEstateAddress($scope.dataRealEstate.oid, $scope.address.town, $scope.address).success(function(response){
                realEstatePostService.saveRealEstateDetails($scope.dataRealEstate.oid, selectedDetails).success(function(response){

                }).error(function(response){
                    helperService.initMSg(true, 4, response.apiMessage);
                });

                realEstatePostService.saveRealEstateFeatures($scope.dataRealEstate.oid, selectedFeature).success(function(response){
                    helperService.initMSg(true, 1, "Your real estate ad is posted successfuly :)");
                    $location.path("/");
                }).error(function(){
                    helperService.initMSg(true, 4, response.apiMessage);
                });
            }).error(function(response){
                helperService.initMSg(true, 4, response.apiMessage);
            });

        }).error(function(response){
            helperService.initMSg(true, 4, response.apiMessage);
        });

    };


}]);


lelocAPP.controller('adController', ['$scope', '$filter', '$log', '$timeout', '$http', '$location', '$modal', '$routeParams', 'realEstateService', 'helperService', '$q', function($scope, $filter, $log, $timeout, $http, $location, $modal, $routeParams, realEstateService, helperService, $q){
    $scope.realEstateid = $routeParams.realestateid;
    $scope.imgArr = [];
    $scope.realEstate = {};

    // carousel initilization
    $scope.selectedImg = {};
    $scope.realEstateOK = false;
    $scope.details = {};
    $scope.features = {};


    realEstateService.findById($scope.realEstateid).success(function(response){
        $scope.realEstate = response;
        $scope.selectedImg = {name: $scope.realEstate.pictureUrl};
        var myLinks = [];
         angular.forEach($scope.realEstate._links, function(value, key){
             if(key !== 'self')
                 myLinks.push(value.href);
         });

        $q.all(myLinks.map(function (item) {
                return realEstateService.findEverything(item);
            })).then(function (results) {
                results.forEach(function (val, index) {
                    if(index == 0){ // images
                        $scope.realEstate.images = val.data.imgListRes;
                    } else if(index == 1){// Address
                        $scope.realEstate.address = val.data;
                        $scope.realEstateOK = true;
                    } else if(index == 2){ // details
                        $scope.realEstate.details = val.data.detailDetailListRes;
                    } else if(index == 3){// Features
                        $scope.realEstate.features = val.data.fOptionsRes;
                    }
                });
            }, function(error){
               $log.error("featureService.detailDetails ERR : " + error);$location.path("/");
           });
    }).error(function(response){
        helperService.initMSg(true, 4, response.apiMessage);
        $location.path("/");
    });

        $scope.$watch('realEstateOK', function (newV, oldV) {
            if(newV && !oldV){
                var myLinks = [];
                 angular.forEach($scope.realEstate.address._links, function(value, key){
                     if(key !== 'self')
                         myLinks.push(value.href);
                 });
                // ###################### ADDRESS COUNTRY REGION bla bla initilized #####################
                $q.all(myLinks.map(function (item) {
                        return realEstateService.findEverything(item);
                    })).then(function (results) {
                        results.forEach(function (val, index) {
                            if(index == 0){ // Country
                                $scope.realEstate.address.country = val.data;
                            } else if(index == 1){ // REgion
                                $scope.realEstate.address.region = val.data;
                            } else if(index == 2){ // City
                                $scope.realEstate.address.city = val.data;
                            } else if(index == 3){ // Town
                                $scope.realEstate.address.town = val.data;
                            }
                        });
                    }, function(error){
                       $log.error("featureService.detailDetails ERR : " + error);$location.path("/");
                   });
                // ############################ DETAIl TITLE ###################################

                 var detailsLink = [];
                 angular.forEach($scope.realEstate.details, function(value, key){
                     angular.forEach(value._links, function(v, k){
                         if(k !== 'self'){
                             detailsLink.push(v.href);
                         }
                     });
                 });
                $q.all(detailsLink.map(function (item) {
                        return realEstateService.findEverything(item);
                    })).then(function (results) {
                        var mm = 0;
                        results.forEach(function (val, index) {
                            $scope.realEstate.details[mm].title = val.data;
                            mm = mm+1;
                        });
                    }, function(error){
                       $log.error("featureService.detailDetails ERR : " + error);$location.path("/");
                   });
            }
        });



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
