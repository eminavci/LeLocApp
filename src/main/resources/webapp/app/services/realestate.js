lelocAPP.service('featureService', ['$http',  "$rootScope",'$log', function($http, $location,  $log){

    var self = this;
    // ********* this service will not work now
    self.bycatid = function(catid){
       return $http.get('http://localhost:8080/user');
    };


   // ********* this service will not work now
   self.byopetypeid = function(opetypeid){
       return $http.get('http://localhost:8080/user');
    };
    self.bysubcatid = function(subcatid){
       return $http.get('http://localhost:8080/subcategory/features/bysubcategory/' + subcatid);
    };


    self.featureOptionsByF = function(fid){
        return $http.get('http://localhost:8080/subcategory/featureoptions/byfeature/' + fid);
    };


     self.detailTitles = function(subcatid){
        return $http.get('http://localhost:8080/subcategory/detailtitles/bysubcategory/' + subcatid);
    };

    self.detailDetails = function(detid){
        return $http.get('http://localhost:8080/subcategory/detaildetail/bydetailtitle/' + detid);
    };
}]);

lelocAPP.service('realEstatePostService', ['$http',  "$rootScope",'$log', function($http, $location,  $log){

    var self = this;
    self.url = "http://localhost:8080/realestate"

    self.saveRealEstate = function(userid, subcatid, realestate){
       return $http.post(self.url + "/" + userid + "/" + subcatid, realestate);
    };

    self.saveRealEstateAddress = function(realEstateId, townId, address){
       return $http.post(self.url + "/address/" + realEstateId + "/" + townId, address);
    };

    self.saveRealEstateDetails = function(realEstateId, details){
       return $http.post(self.url + "/detail/" + realEstateId, details);
    };

    self.saveRealEstateFeatures = function(realEstateId, features){
       return $http.post(self.url + "/feature/" + realEstateId, features);
    };

}]);

lelocAPP.service('realEstateService', ['$http',  "$rootScope",'$log', function($http, $location,  $log){

    var self = this;
    self.url = "http://localhost:8080/realestate"

    self.showCase = function(){
       return $http.get(self.url + "/showcase");
    };

    self.findById = function(realEstateId) {
        return $http.get(self.url + "/" + realEstateId);
    }

    self.findEverything = function(urli){
        return $http.get(urli, { cache: true});
    };

}]);



