$(document).ready(function () {

    // SEARCH BOX
    $('input#searchText').focus(function() {
        $('input#searchSbt').css({"background":"transparent url('/static/icons/searchIconHover.png') 0 0 no-repeat"});
        $(this).css({"background":"#FFFFFF"});
        $('span#searchHint').hide(0);
    });
    $('input#searchText').blur(function() {
        $('input#searchSbt').css({"background":"transparent url('/static/icons/searchIcon.png') 0 0 no-repeat"});
        $(this).css({"background":"#3d424a","color":"#9EA0A1"});
        if($(this).val() == ""){
            $('span#searchHint').show(0);
        }
    });
    $('span#searchHint').click(function(){
        $('input#searchText').focus();
    });


    google.maps.event.addDomListener(window, 'load', initialize);

    function initialize() {

        /* position Amsterdam */
        var latlng = new google.maps.LatLng(48.856614, 2.3522219);

        var mapOptions = {
            center: latlng,
            scrollWheel: false,
            zoom: 13
        };

        var marker = new google.maps.Marker({
            position: latlng,
            url: '/',
            animation: google.maps.Animation.DROP
        });

        try {
            var map = new google.maps.Map(document.getElementById("map-canvas"), mapOptions);
            marker.setMap(map);
        }
        catch(err) {
            console.log("GOOGLE MAP HATA : " + err.message);
        }



    };

});

function initPhotoPlugin(){

    //Example 2
    $("#filer_input2").filer({
        limit: 10,
        maxSize: 3,
//        files: [  // Önceden eklenen varsa buraya eklenebilir
//            {
//                name: "appended_file.jpg",
//                size: 5453,
//                type: "image/jpg",
//                file: "http://dummyimage.com/720x480/f9f9f9/191a1a.jpg"
//            },
//            {
//                name: "appended_file_2.jpg",
//                size: 9453,
//                type: "image/jpg",
//                file: "http://dummyimage.com/640x480/f9f9f9/191a1a.jpg"
//            }
//        ],
        extensions: ['jpg', 'jpeg', 'png'],
        changeInput: '<div class="jFiler-input-dragDrop"><div class="jFiler-input-inner"><div class="jFiler-input-icon"><i class="icon-jfi-cloud-up-o"></i></div><div class="jFiler-input-text"><h3>Drag&Drop files here</h3> <span style="display:inline-block; margin: 15px 0">or</span></div><a class="jFiler-input-choose-btn blue">Browse Files</a></div></div>',
        showThumbs: true,
        theme: "dragdropbox",
        templates: {
            box: '<ul class="jFiler-items-list jFiler-items-grid"></ul>',
            item: '<li class="jFiler-item">\
<div class="jFiler-item-container">\
<div class="jFiler-item-inner">\
<div class="jFiler-item-thumb">\
<div class="jFiler-item-status"></div>\
<div class="jFiler-item-info">\
<span class="jFiler-item-title"><b title="[[fi-name]]">[[fi-name | limitTo: 25]]</b></span>\
<span class="jFiler-item-others">[[fi-size2]]</span>\
</div>\
[[fi-image]]\
</div>\
<div class="jFiler-item-assets jFiler-row">\
<ul class="list-inline pull-left">\
<li>[[fi-progressBar]]</li>\
</ul>\
<ul class="list-inline pull-right">\
<li><a class="icon-jfi-trash jFiler-item-trash-action"></a></li>\
</ul>\
</div>\
</div>\
</div>\
</li>',
            itemAppend: '<li class="jFiler-item">\
<div class="jFiler-item-container">\
<div class="jFiler-item-inner">\
<div class="jFiler-item-thumb">\
<div class="jFiler-item-status"></div>\
<div class="jFiler-item-info">\
<span class="jFiler-item-title"><b title="[[fi-name]]">[[fi-name | limitTo: 25]]</b></span>\
<span class="jFiler-item-others">[[fi-size2]]</span>\
</div>\
[[fi-image]]\
</div>\
<div class="jFiler-item-assets jFiler-row">\
<ul class="list-inline pull-left">\
<li><span class="jFiler-item-others">[[fi-icon]]</span></li>\
</ul>\
<ul class="list-inline pull-right">\
<li><a class="icon-jfi-trash jFiler-item-trash-action"></a></li>\
</ul>\
</div>\
</div>\
</div>\
</li>',
            progressBar: '<div class="bar"></div>',
            itemAppendToEnd: false,
            removeConfirmation: true,
            _selectors: {
            list: '.jFiler-items-list',
            item: '.jFiler-item',
            progressBar: '.bar',
            remove: '.jFiler-item-trash-action'
        }
    },
                             dragDrop: {
                             dragEnter: null,
                             dragLeave: null,
                             drop: null,
                             },
                             uploadFile: {
                             url: "http://localhost:8080/realestate/upload",
                             data: null,
                             type: 'POST',
                             enctype: 'multipart/form-data',
                             beforeSend: function(){},
        success: function(data, el){
            var parent = el.find(".jFiler-jProgressBar").parent();
            el.find(".jFiler-jProgressBar").fadeOut("slow", function(){
                $("<div class=\"jFiler-item-others text-success\"><i class=\"icon-jfi-check-circle\"></i> Success</div>").hide().appendTo(parent).fadeIn("slow");
            });
        },
            error: function(el){
                var parent = el.find(".jFiler-jProgressBar").parent();
                el.find(".jFiler-jProgressBar").fadeOut("slow", function(){
                    $("<div class=\"jFiler-item-others text-error\"><i class=\"icon-jfi-minus-circle\"></i> Error</div>").hide().appendTo(parent).fadeIn("slow");
                });
            },
                statusCode: null,
                    onProgress: null,
                        onComplete: null
},
    files: null,
        addMore: false,
            clipBoardPaste: true,
                excludeName: null,
                    beforeRender: null,
                        afterRender: null,
                            beforeShow: null,
                                beforeSelect: null,
                                    onSelect: null,
                                        afterShow: null,
                                            onRemove: function(itemEl, file, id, listEl, boxEl, newInputEl, inputEl){
                                                var fileName = file.name;
                                                var fileSize = file.size;
                                                $.post('http://localhost:8080/realestate/remove_file', {file: fileName, size: fileSize});
                                            },
                                                onEmpty: null,
                                                    options: null,
                                                        captions: {
                                                            button: "Choose Files",
                                                                feedback: "Choose files To Upload",
                                                                    feedback2: "files were chosen",
                                                                        drop: "Drop file here to Upload",
                                                                            removeConfirmation: "Are you sure you want to remove this file?",
                                                                                errors: {
                                                                                    filesLimit: "Only [[fi-limit]] files are allowed to be uploaded.",
                                                                                        filesType: "Only Images are allowed to be uploaded.",
                                                                                            filesSize: "[[fi-name]] is too large! Please upload file up to [[fi-maxSize]] MB.",
                                                                                                filesSizeAll: "Files you've choosed are too large! Please upload files up to [[fi-maxSize]] MB."
                                                                                }
                                                        }
});

}
