lelocAPP.factory('helperService', ['$http', "$location",'$log', '$rootScope', function($http, $location, $log, $rootScope){

    var msgData = {};

    msgData.isShowMsg = false;
    msgData.msgType = 0; // 1:success, 2:infomation, 3:warning, 4: Error
    msgData.customMsg = "An Error Occured! Sorry!";

    return {
        initMSg : function(isShow, msgTip, cstMSg){
            if(!isShow){
                $log.error("helperService.initMSg can not be initilized with false value!");
                return;
            }
           msgData.isShowMsg = isShow;
           msgData.customMsg = cstMSg;
           msgData.msgType = msgTip;
        },
        getisShowMsg: function(){
            return msgData.isShowMsg;
        },
        setisShowMsgFalsaBack: function(){
          msgData.isShowMsg = false;
        },
        getMSgData: function(){
            return msgData;
        }
    };

}]);
