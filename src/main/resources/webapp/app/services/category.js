lelocAPP.service('searchCatTemplateService', function(){
    var self = this;
    self.myArr = [];
    self.myArr.push({id: 1, catName : "Residential",        pos_x: "-4",  pos_y: "-33", bgimage: '../../static/icons/konut.jpg'});
    self.myArr.push({id: 2, catName : "Commercial",         pos_x: "-31", pos_y: "-33", bgimage: '../../static/icons/is_yeri.jpg'});
    self.myArr.push({id: 3, catName : "Land",               pos_x: "-61", pos_y: "-33", bgimage: '../../static/icons/arsa.jpg'});
    self.myArr.push({id: 4, catName : "Buildings",          pos_x: "-90", pos_y: "-33", bgimage: '../../static/icons/bina.jpg'});
    self.myArr.push({id: 5, catName : "Time Share",         pos_x: "-121", pos_y: "-34", bgimage: '../../static/icons/devremulk.jpg'});
    self.myArr.push({id: 6, catName : "Tourism Facilities", pos_x: "-150", pos_y: "-33", bgimage: '../../static/icons/turistik_tesis.jpg'});
});
/*
* This is calledonly one time.hen promisekept. nd each tme called t will return same datawithouthttp call
*/
// ******** NON USABLE
lelocAPP.factory('opeTypeService', ['$http', '$log', function($http, $log){
    var last_request_failed = true;
    var promise = undefined;
    catOpeTypeService =  {
        async : function(catid) {
            if(!promise || last_request_failed) {
                promise = $http.get("http://localhost:8080/category/catopetype/bycategory/" + catid).then(
                function(response) {
                    last_request_failed = false;
                    return response.data;
                },function(response) {  // error
                    last_request_failed = true;
                    return $q.reject(response);
                });
            }
            return promise;
        },
    };
    return catOpeTypeService;
}]);

lelocAPP.factory('subCategoryService', ['$q', '$http', function($q, $http){
    var getSubCategories = function(opeTypeId) {
        return $http.get("http://localhost:8080/subcategory/bycatopetype/" + opeTypeId).then(function(response){
            return response.data;
        },function(response) {  // error
            logError(response);
            return $q.reject(response);
        });
    };
    return { getSubCategories: getSubCategories };
}]);

lelocAPP.factory('catOpeTypeService', ['$q', '$http', function($q, $http){
    var getOpeTypes = function(catid) {
        return $http.get("http://localhost:8080/category/catopetype/bycategory/" + catid).then(function(response){
            return response.data;
        },function(response) {  // error
            logError(response);
            return $q.reject(response);
        });
    };
    return { getOpeTypes : getOpeTypes };
}]);

// ******** NON USABLE
lelocAPP.service('catOpeTypeServicewwwwww', ['$http', '$log', 'localStorage', '$q', function($http, $log, localStorage, $q){
    var self = this;

    this.get = function(catid){
        var isim = 'catOpe' + catid;

        if(localStorage.getItem(isim)){
            return localStorage.getItem(isim);
        } else {
            $http.get("http://localhost:8080/category/catopetype/bycategory/" + catid).then(
                function (response) {
                    $log.debug("DATA : " + response.data);
                    localStorage.setItem(isim, JSON.stringify(response.data));
                    return response.data;
                },
                function (response) { // error
                    $log.error("ERROR : " + response.data);
                    last_request_failed = true;
                    return $q.reject(response);
                });
        }
    };

}]);

lelocAPP.service('catByIdService', ['$http',  "$rootScope",'$log', function($http, $location,  $log){

    var self = this;

    self.findCatById = function(catid){
       return $http.get('http://localhost:8080/category/' + catid);
    };

    self.findSubCatById = function(subcatid){
       return $http.get('http://localhost:8080/subcategory/'+subcatid);
    };

    self.findOpeTypeById = function(opeid){
       return $http.get('http://localhost:8080/category/catopetype/'+opeid);
    };
}]);

lelocAPP.service('allCategoryService', ['$http', '$log', function($http, $log){
    var self = this;
    self.getCats = function(usr){
       return $http.get('http://localhost:8080/category');
    };
}]);


