var dcomethealth = dcomethealth || {};
dcomethealth.Pictures = (function () {
    var lightboxSettings = {
        imageLoading: '/health_web/css/images/close_icon.png',
        imageBtnPrev: '/health_web/css/images/backIcon.gif',
        imageBtnNext: '/health_web/css/images/nextIcon.gif',
        imageBtnClose: '/health_web/css/images/cancelIcon.gif',
        imageBlank: '/health_web/css/images/close_icon.png'
    };
    function addImageByUserId(imageId, caption, containerId) {
        var pictures = $("#" + containerId);
        if (pictures.length <= 0) {
            alert("Invalid Image Place Holder");
            return;
        }
        // Image display size definition
        var pictureSize = "tinythumbnail";
        var imageItemStyle = "style='width:120px;'";
        var imageItem = $("#" + imageId);
        if (imageItem.length === 0) {
            imageItem = $("<div class='imageItem' " + imageItemStyle + "></div>").attr("id", imageId).appendTo(pictures);
        }
        var ahref = $("<a href='#' title='" + caption + "'></a>").appendTo(imageItem);
        $("<img class='rounded-corners' src='/health_web/rest/file/user/photo?id=" + encodeURI(imageId) + "&size=" + pictureSize + "'/>").appendTo(ahref);
        $("<br />").appendTo(imageItem);
    }
    function addImageById(imageId, caption, containerId) {
        var pictures = $("#" + containerId);
        if (pictures.length <= 0) {
            alert("Invalid Image Place Holder");
            return;
        }
        // Image display size definition
        var pictureSize = "tinythumbnail";
        var imageItemStyle = "style='width:120px;'";
        var imageItem = $("#" + imageId);
        if (imageItem.length === 0) {
            imageItem = $("<div class='imageItem' " + imageItemStyle + "></div>").attr("id", imageId).appendTo(pictures);
        }
        var ahref = $("<a href='#'></a>").appendTo(imageItem);
        $("<img shouldPrint='false' class='rounded-corners' src='/health_web/rest/file/photo?id=" + encodeURI(imageId) + "&size=" + pictureSize + "'/>").appendTo(ahref);
//        $("#" + containerId + " a").lightBox(dcomethealth.user_profile.lightboxSettings);
    }

    function addImage(imageId, caption, containerId) {
        addImage(imageId, caption, containerId, null);
    }
    function addImage(imageId, caption, containerId, contentType) {
        var pictures = $("#" + containerId);
        if (pictures.length <= 0) {
            alert("Invalid Image Place Holder");
            return;
        }
        // Image display size definition
        var pictureSize = "thumbnail";
        var txtCtrlWidth = 200;
        var imageItemStyle = "";
        if ((containerId.match("^recImg_")) && (containerId.match("_containerImg$"))) {
            pictureSize = "tinythumbnail";
            txtCtrlWidth = 100;
            imageItemStyle = "style='width:80px;'";
        }
        var imageItem = $("#" + imageId);
        var isNonImageItem = false;
        var nonImageItemThumbnail = "";

        if (!!contentType && contentType.toLowerCase() === "application/pdf") {
            isNonImageItem = true;
            nonImageItemThumbnail = "/health_web/css/images/pdf.png";
        }
        if (!!contentType && contentType.toLowerCase() === "application/vnd.ms-excel") {
            isNonImageItem = true;
            nonImageItemThumbnail = "/health_web/css/images/xls.png";
        }
        if (imageItem.length === 0) {
            imageItem = $("<div class='imageItem' " + imageItemStyle + "></div>").attr("id", imageId).appendTo(pictures);
        }
        var tinythumbnail = "/health_web/rest/file/image?imageId=" + encodeURI(imageId) + "&size=screen'";
        var img = "";
        if (isNonImageItem) {
            tinythumbnail = nonImageItemThumbnail;
            img = $("<img class='rounded-corners' src='" + nonImageItemThumbnail + "'/>");
        } else {
            img = $("<img class='rounded-corners' src='/health_web/rest/file/image?imageId=" + encodeURI(imageId) + "&size=" + pictureSize + "'/>");
        }
        var ahref = $("<a href='" + tinythumbnail + "' title='" + caption + "'></a>").appendTo(imageItem);
        img.appendTo(ahref);

        var removeImgIcon = $("<img title='Click to remove image' class='remove-img-icon' style='position:absolute;display: block;  right:-10px; top:-10px;' src='/health_web/css/images/cancelIcon.gif'>").appendTo(ahref);
        removeImgIcon.click(function (event) {
            event.preventDefault();
            removeImage(imageId);
        });
        removeImgIcon.hide();
        $("<br />").appendTo(imageItem);
        //$("<img class='rounded-corners ui-for-print-only printViewImg' src='/health_web/rest/file/image?imageId=" + encodeURI(imageId) + "&size=screen'/>").appendTo(imageItem);
        var imgCtrl = "";
        var captionCtrl = "";
        // attached image and it's caption tag name definition
        if (containerId.match("_containerImg$")) {
            imgCtrl = containerId.replace("_containerImg", "");
            if (imgCtrl.match("^recImg_")) {
                imgCtrl = "recImg";
            }
            captionCtrl = imgCtrl + "Caption";
        }
        if (containerId === 'image_containerImg') {
            captionCtrl = "caption";
        }
        imageItem.hover(function () {
            removeImgIcon.show();
//            $("img.remove-img-icon", this).show();
        }, function () {
            removeImgIcon.hide();
//            $("img.remove-img-icon", this).hide();
        });
        $("<input type='hidden' name='" + imgCtrl + "' id='" + (imgCtrl + imageId) + "' shouldPrint='false'/>").val(imageId).appendTo(imageItem);
        $("<input type='text' name='" + captionCtrl + "' id='" + (captionCtrl + imageId) + "' style='width:" + txtCtrlWidth + "px;'/>").val(caption || "").appendTo(imageItem);
//        $("#" + containerId + " a").lightBox(dcomethealth.Patients.lightboxSettings); //dcomethealth.Patients.lightboxSettings
        if (isNonImageItem) {
            var download = $("<input type='button' value='download' class='binaryDlownloadBtn defaultEnabling' id='" + imageId + "'/>").appendTo(imageItem);
            download.click(function () {
                var imageId = $(this).attr("id");
                window.open("/health_web/rest/file/image?imageId=" + encodeURI(imageId) + "&size=download", imageId);
            });
        }
    }
    function loadImage(contextData, imgContainer, resourceId) {
        var pictures = $("#" + imgContainer);
        if (pictures.length) {
            pictures.empty();
            $("#resourceId").val(resourceId);
            imgArray = contextData.context.image;
            capArray = contextData.context.caption;
            if (contextData.context && imgArray) {
                var captions = capArray || [];
                captions = $.isArray(captions) ? captions : [captions];
                var images = imgArray || [];
                images = $.isArray(images) ? images : [images];
                $.each(images, function (index, image) {
                    if (dcomethealth.Pictures && addImage) {
                        var caption = captions[index];
                        if (("blank~" + image) === caption) {
                            caption = "";
                        }
                        if (dcomethealth.FileResource.getContentType(image, function (data) {
                            addImage(image, caption, imgContainer, data.mimeType);
                        }, function (jqXHR) {
                            addImage(image, caption, imgContainer);
                        }))
                            ;
                    }
                });
            }
        }
    }
    function removeImage(imageId) {
        $("#" + imageId).remove();
        dcomethealth.FileResource.deleteFile(imageId, function (data) {
        });
        updateContext();
    }
    function updateContext() {
        var resourceId = $("#resourceId").val();
        if (resourceId != null) {
            var imageContext = {};
            imageContext.id = resourceId;
            $('.ImgPlaceHolder').each(function () {
                var containerId = "image_containerImg";
                if ($(this).length) {
                    var imgandAndCaption = getImgandCaptionfromContainer(containerId);
                    var context = {};
                    context["image"] = imgandAndCaption["image"];
                    context["caption"] = imgandAndCaption["caption"];
                    imageContext.context = context;
                }
            });
            dcomethealth.FileResource.saveFileInfo(imageContext, function (data) {
            });
        }
    }
    function isAllowedImageExtenstionList(files) {
        var status = true;
        $.each(files, function (idx, file) {
            var fileName = file.name;
            if (!isAllowedImageExtenstion(fileName)) {
                status = false;
            }
        });
        return status;
    }
    function isAllowedImageExtenstion(filename) {
        var fileExt = filename.substring(filename.lastIndexOf('.') + 1).toUpperCase();
        var allowedExt = ["JPEG", "JPG", "PNG", "GIF"];
        if (allowedExt.indexOf(fileExt) < 0) {
            alert("Acceptable file format is JPEG(*.jpg ; *.jpeg),PNG(*.png),GIF(*.gif)");
            return false;
        }
        return true;
    }
    function isAllowedFileExtenstion(filename) {
        var fileExt = filename.substring(filename.lastIndexOf('.') + 1).toUpperCase();
        var allowedExt = ["JPEG", "JPG", "PNG", "GIF", "DOC", "DOCX", "XLS", "XLSX", "PDF", "TXT"];
        if (allowedExt.indexOf(fileExt) < 0) {
            alert("Acceptable file format is JPEG,JPG,PNG,GIF,DOC,DOCX,XLS,XLSX,PDF,TXT");
            return false;
        }
        return true;
    }
    function isAllowedFileSize(files) {
        var status = true;
        $.each(files, function (idx, file) {
            if (file.size > 2 * 1024 * 1000) {
                alert("Cannot accept file(s).  Files must be less than 2 MB in size.");
                status = false;
            }
        });
        return status;
    }
    function isAllowedImageUploadLimit(containerId, selectedFileToUploadCount) {
        var status = true, allowedImages = 4;
        var alreadyUploadedCount = $("div[id='" + containerId + "']").children(".imageItem").length;
        if ((containerId.match("^recImg_")) && (containerId.match("_containerImg$"))) {
            allowedImages = 3;
        }
        if ((alreadyUploadedCount + selectedFileToUploadCount) > allowedImages) {
            alert("Cannot upload more than " + allowedImages + " images in this section");
            status = false;
            return  false;
        }
        return status;
    }
    function getImgandCaptionfromContainer(containerId) {
        var imgandCaption = new Array();
        var img = "", imgCaption = "";
        var imgCount = $("div[id='" + containerId + "']").children(".imageItem").length;
        if (imgCount === 1) {
            img = $("#" + containerId + "  > .imageItem").attr("id");
            imgCaption = $("#" + containerId).find("input[type='text']").val();
            if (imgCaption === "") {
                imgCaption = "blank~" + img;
            }
        } else if (imgCount > 1) {
            img = new Array();
            imgCaption = new Array();
            $("#" + containerId + "  > .imageItem").each(function () {
                img.push($(this).attr("id"));
                var caption = $(this).find("input[type='text']").val();
                if (caption === "") {
                    caption = "blank~" + $(this).attr("id");
                }
                imgCaption.push(caption);
            });
        }
        imgandCaption = $.extend(imgandCaption, {"image": img});
        imgandCaption = $.extend(imgandCaption, {"caption": imgCaption});
        return imgandCaption;
    }
    function setDragNDropListeners(pageID) {
        var obj = $("#image_containerImg");
        obj.on("dragenter", function (event) {
            event.stopPropagation();
            event.preventDefault();
        });
        obj.on("dragover", function (event) {
            event.stopPropagation();
            event.preventDefault();
        });
        obj.on("drop", function (event) {
            event = event.originalEvent;
            event.stopPropagation();
            event.preventDefault();
            var files = event.dataTransfer.files;
            var imageContainerId = event.target.id;
            var fileCtrlName = event.srcElement.name;
            if (!imageContainerId.match("_containerImg$")) {
                if (!imageContainerId == "userImg" || !imageContainerId == "patImage") {
                    alert("Place holder for image not available.");
                    return;
                }
            }
            if ((imageContainerId === "") && (fileCtrlName !== "")) {

                imageContainerId = (fileCtrlName.replace("FileInputImg", "")) + "_containerImg";
            }
            var uploadStatus = false;
            if ((fileCtrlName === "imageFileInputImg") || (imageContainerId === "image_containerImg")) {
                uploadStatus = true;
            }
            var isAllowedFileLimit = isAllowedImageUploadLimit(imageContainerId, 1);
            var isValidExtension = isAllowedImageExtenstionList(files);
            var isValidFileSize = isAllowedFileSize(files);
            if (!uploadStatus) {
                if (!isAllowedFileLimit) {
                    return;
                }
            }
            if (imageContainerId == "userImg" || imageContainerId == "patImage") {
                uploadStatus = true;
            }
            if (uploadStatus) {
                if (isValidExtension && isValidFileSize && isAllowedFileLimit) {
                    uploadFiles(files, imageContainerId, pageID);
                }
            }
        });
    }
    function imageUpload(e) {
        var ctrlName = e.currentTarget.name;
        ctrlName = ctrlName.replace("UploadBtn", "");
        var uploadImageFormField = $("input[name='" + ctrlName + "FileInputImg']");
        var uploadStatus = false;
        if ("image_containerImg" === ctrlName + "_containerImg") {
            uploadStatus = true;
        }
        var filename = uploadImageFormField.val();
        if (filename === "") {
            return;
        }
        var isAllowedFileLimit = isAllowedImageUploadLimit(ctrlName + "_containerImg", 1);
        var isValidFileSize = isAllowedFileSize(uploadImageFormField[0].files);
        var isValidExtension = isAllowedImageExtenstion(filename);
        if (!uploadStatus) {
            if (!isAllowedFileLimit) {
                uploadImageFormField.val("");
                return;
            }
            if (isValidExtension && isAllowedFileLimit && isValidFileSize) { //for allowed file Limit
                uploadStatus = true;
            }
        }
        if (uploadStatus) {
            if (isValidExtension && isAllowedFileLimit && isValidFileSize) {
                uploadFiles(uploadImageFormField[0].files, ctrlName + "_containerImg");
            }
        }
        uploadImageFormField.val("");
    }
    function uploadFiles(files, containerId, patRID) {
        var imageItems = $("#" + containerId);
        var fkId = dcomethealth.loginuser.id;
        if (imageItems.length) {
            var children = imageItems.children();
            var nextId = children.length;
            // files is a FileList of File objects. List some properties.            
            $.each(files, function (index, file) {
                var imageId = "imageItem." + nextId;
                var imageIdSelector = "#imageItem\\." + nextId;
                $("<div class='imageItem'>Loading...<br/><progress></progress></div>").attr("id", imageId).appendTo(imageItems);
                if (containerId == "patImage") {
                    dcomethealth.PatientResource.uploadPatImage(file, patRID, function (data) {
                        $("#resourceId").val(data.imageId);
                        $('#patImage').attr('src', '/health_web/rest/patient/v1/photo?imgId=' + data.imageId);
                    });
                }
                if (containerId == "userImg") {
                    dcomethealth.FileResource.uploadUserImage(file, fkId, function (data) {
                        $('#userImg').attr('src', '/health_web/rest/file/photo?id=' + data.imageId);
                    });
                } else {
                    dcomethealth.FileResource.uploadFiles(file, function (data) {
                        var imageItem = $(imageIdSelector);
                        imageItem.remove();
                        dcomethealth.FileResource.getContentType(data.imageId, function (content) {
                            addImage(data.imageId, "", containerId, content.mimeType);
//                            $("#" + containerId + " a").lightBox(lightboxSettings);
                        }, function (jqXHR) {
                            addImage(data.imageId, "", containerId);
                        });
                    }, function (jqXHR) {
                    }, function (event) {
                        var progressNode = $(imageIdSelector + " progress").get(0);
                        if (event.lengthComputable) {
                            progressNode.max = event.total;
                            progressNode.value = event.loaded;
                        }
                    });
                }
                nextId++;
            });
        }
    }
//     function removeImage(imageId) { //Old function
//        $("#" + imageId).remove();
//    }
    return {
        addImage: addImage,
        loadImage: loadImage,
        removeImage: removeImage,
        updateContext: updateContext,
        addImageByUserId: addImageByUserId,
        addImageById: addImageById,
        isAllowedImageExtenstionList: isAllowedImageExtenstionList,
        isAllowedImageExtenstion: isAllowedImageExtenstion,
        isAllowedFileExtenstion: isAllowedFileExtenstion,
        isAllowedFileSize: isAllowedFileSize,
//        isAllowedImageUploadLimit: isAllowedImageUploadLimit,
        getImgandCaptionfromContainer: getImgandCaptionfromContainer,
        setDragNDropListeners: setDragNDropListeners,
        imageUpload: imageUpload,
        uploadFiles: uploadFiles
    };
}());
// @ sourceURL = server/pictures.js