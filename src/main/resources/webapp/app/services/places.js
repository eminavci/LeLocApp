
lelocAPP.factory('cityService', ['$q', '$http', function($q, $http){
    var getCities = function(regionid) {
        return $http.get("http://localhost:8080/city/byregion/" + regionid).then(function(response){
            return response.data;
        },function(response) {  // error
            logError(response);
            return $q.reject(response);
        });
    };
    return { getCities: getCities };
}]);


lelocAPP.factory('townService', ['$q', '$http', function($q, $http){
    var getTowns = function(cityid) {
        return $http.get("http://localhost:8080/town/bycity/" + cityid).then(function(response){
            return response.data;
        },function(response) {  // error
            logError(response);
            return $q.reject(response);LeLocApp/src/main/resources/application.properties
        });
    };
    return { getTowns : getTowns };
}]);


lelocAPP.factory('allCitiesService', ['$q', '$http', function($q, $http){
    var getCities = function(countryId) {
        return $http.get("http://localhost:8080/city/bycountry/" + countryId).then(function(response){
            return response.data;
        },function(response) {  // error
            logError(response);
            return $q.reject(response);
        });
    };
    return { getCities : getCities };
}]);


lelocAPP.factory('regionService', ['$http', '$log', '$q', function($http, $log, $q){
    var last_request_failed = true;
    var promise = undefined;
    catOpeTypeService =  {
        async : function(countryId) {
            if(!promise || last_request_failed) {
                promise = $http.get("http://localhost:8080/region//bycountry/" + countryId).then(
                function(response) {
                    last_request_failed = false;
                    return response.data;
                },function(response) {  // error
                    logError(response);
                    last_request_failed = true;
                    return $q.reject(response);
                });
            }
            return promise;
        },
    };
    return catOpeTypeService;
}]);
