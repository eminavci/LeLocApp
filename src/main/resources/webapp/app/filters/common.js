lelocAPP.filter('limit', function() {
    return function (input, value) {
        return input.substr(0,value);
    };
});
lelocAPP.filter('htmlize', ['$sce', function($sce){
    return function(val) {
        return $sce.trustAsHtml(val);
    };
}]);

lelocAPP.filter('lemodilize', [function(){
    return function (text) {
      var str = text.replace(/\s+/g, '');
      return str.toLowerCase();
    };
}]);

lelocAPP.filter('lelocapurlformatter', [function(){
    return function (text) {
      var str = text.replace(/\s+/g, '-');
      return str;
    };
}]);

lelocAPP.filter('orderObjectBy', function(){
 return function(input, attribute) {
    if (!angular.isObject(input)) return input;

    var array = [];
    for(var objectKey in input) {
        array.push(input[objectKey]);
    }

    array.sort(function(a, b){
        a = parseInt(a[attribute]);
        b = parseInt(b[attribute]);
        return a - b;
    });
    return array;
 }
});

