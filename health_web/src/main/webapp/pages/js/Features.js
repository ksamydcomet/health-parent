var dcomethealth = dcomethealth || {};
var sFeatureList = [];
dcomethealth.Features = (function () {
    var id = "Features";
    function init() {
        $("#main_navigation_bar").append('<div id="' + id + '" class="main_container">');
        $("#" + id).load("pages/html/" + id + ".html", function () {
            $('#curDate').text(moment().format('DD-MM-YYYY'));
            dcomethealth.datatypes.init($("#" + id));
            fetchFeatureList();
            setTargetEntity();
            $("#assignEntity").select2({width: 'resolve'});
            $('.featureValid').toggles({on: true}, "featureValid", 1);
            $('.featureIsParent').toggles({on: true}, "featureIsParent", 1);
            $("#edit_user_form").validate({
                rules: {
                    featureCode: "required",
                    featureName: "required",
                    featureParentGroup: "required",
                    featureGroup: "required",
                    featureProductRID: "required",
                },
                messages: {
                    featureCode: "Enter feature code",
                    featureName: "Enter feature name",
                    featureGroup: "Enter feature group",
                    featureParentGroup: "Enter feature parentgroup",
                    featureProductRID: "Enter Feature Product rid"
                },
                submitHandler: function (form) {
                    submit();
                }
            });
        });
        $("#giveMoreDetail").change(function () {
            if ($("#giveMoreDetail").is(":checked")) {
                $("#moreDetailsDiv1", "#moreDetailsDiv2", "#moreDetailsDiv3").removeClass("hidden");
            } else {
                $("#moreDetailsDiv1", "#moreDetailsDiv1").addClass("hidden");
            }
        });

    }
    function setTargetEntity() {
        dcomethealth.DataDictionaryResource.getEntities(function (data) {
            $.each(data, function (pIdx, units) {
                if (units.entityRootParentRID == 1) {
                    $("#assignEntity").append('<option value="' + units.id + '">' + units.entityShortName + '</option>');
                }
            });
        });
    }
    function check(e) {
        if (e.checked == true) {
            $("#moreDetailsDiv1,#moreDetailsDiv2,#moreDetailsDiv3").removeClass("hidden");
        } else {
            $("#moreDetailsDiv1,#moreDetailsDiv2,#moreDetailsDiv3").addClass("hidden");
        }
    }
    function checkParent(e) {
        if (e.checked == true) {
            $("#featureParentGroupDiv").removeClass("hidden");
        } else {
            $("#featureParentGroupDiv").addClass("hidden");
        }
    }
    function submit() {
        var features = {};
        if (!!$("#featureId").val()) {
            features.id = $("#featureId").val();
        }
        if (!!$("#featureCommand").val()) {
            features.featureCommand = $("#featureCommand").val();
        }
        if (!!$("#featureGroupDesc").val()) {
            features.featureGroupDesc = $("#featureGroupDesc").val();
        }
        if (!!$("#featureSeqNum").val()) {
            features.featureSeqNum = $("#featureSeqNum").val();
        }
        if (!!$("#featureBitmapFname").val()) {
            features.featureBitmapFname = $("#featureBitmapFname").val();
        }
        if (!!$("#featureHelp").val()) {
            features.featureHelp = $("#featureHelp").val();
        }
        if (!!$("#featureVersion").val()) {
            features.featureVersion = $("#featureVersion").val();
        }
        if (!!$("#featureReleaseDate").val()) {
            features.featureReleaseDate = $("#featureReleaseDate").val();
        }
        if (!!$("#featureIsUnitSpecific").val()) {
            features.featureIsUnitSpecific = $("#featureIsUnitSpecific").val();
        }
        if (!!$("#featureIcon").val()) {
            features.featureIcon = $("#featureIcon").val();
        }
        features.featureProductRID = $("#featureProductRID").val();
        features.featureCode = $("#featureCode").val();
        features.featureName = $("#featureName").val();
        if ($("#featureIsParent").val() == 0) {
            features.featureParentGroup = 0;
        } else {
            features.featureParentGroup = $("#featureParentGroup").val();
        }
        features.featureGroup = $("#featureGroup").val();
        if ($("#featureValid").val() == 1) {
            features.featureValid = 1;
        } else {
            features.featureValid = 0;
        }

        features.featureParentGroup = $("#featureParentGroup").val();
        dcomethealth.DataDictionaryResource.saveFeatures(features).done(function (data, textStatus, jqXHR) {
            alert("Data Saved");
            dcomethealth.util.loadpage('Features');
        }).fail(function (jqXHR, textStatus, errorThrown) {
            alert("failed to save data");
        });
    }
    function fetchFeatureList() {

//        var search = {featureValid: 1};
        dcomethealth.DataDictionaryResource.getFeatures(function (data) {
            console.log(data);
            $("#tbodyHead").empty();
            $.each(sFeatureList = data, function (idx, s_feature) {
                $("#tbodyHead").append('<tr onclick="dcomethealth.Features.showFeatureDetail(' + s_feature.id + ')" id="tr_head">\n\
                    <td>' + s_feature.featureCode + '</td><td>' + s_feature.featureName + '</td><td>' + s_feature.featureParentGroup + '</td></tr>');
            });
            $("#featureMasterTable").dataTable();
            $('.dataTables_filter input').addClass('form-control').attr('placeholder', 'Search...');
            $('.dataTables_length select').addClass('form-control');
            $.each(data, function (idx, parent) {
                if (parent.featureParentGroup == 0) {
                    $('#featureParentGroup').append('<option value="' + parent.id + '">' + parent.featureCode + '</option>');
                }
            });
        });
    }
    function showFeatureDetail(val) {
        $.each(sFeatureList, function (idx, s_feature) {
            if (s_feature.id == parseInt(val)) {
                $("#featureValidDiv").removeClass("hidden")
                $("#FeatureMasterHeader").removeClass("panel panel-primary").addClass("hidden");
                $("#add_new").removeClass("hidden").addClass("panel panel-primary");
                populateFields(s_feature);
            }
        });
    }
    function populateFields(data) {
        $("#featureId").val(data.id);
        $("#featureName").val(data.featureName);
        $("#featureCode").val(data.featureCode);
        $("#featureGroup").val(data.featureGroup);
        $("#featureProductRID").val(data.featureProductRID);
        $("#featureParentGroup").val(data.featureParentGroup);
        if (!!($("#featureGroupDesc,#featureCommand,#featureSeqNum,#featureBitmapFname,#featureHelp,#featureVersion,#featureReleaseDate,#featureIcon,#featureIsUnitSpecific")).val()) {
            $("#moreDetailsDiv1,#moreDetailsDiv2,#moreDetailsDiv3").removeClass("hidden");
        }
        if (!!$(data.featureGroupDesc).val()) {
            $("#featureGroupDesc").val(data.featureGroupDesc);
        }
        if (!!$(data.featureCommand).val()) {
            $("#featureCommand").val(data.featureCommand);
        }
        if (!!$(data.featureSeqNum).val()) {
            $("#featureSeqNum").val(data.featureSeqNum);
        }
        if (!!$(data.featureBitmapFname).val()) {
            $("#featureBitmapFname").val(data.featureBitmapFname);
        }
        if (!!$(data.featureHelp).val()) {
            $("#featureHelp").val(data.featureHelp);
        }
        if (!!$(data.featureVersion).val()) {
            $("#featureVersion").val(data.featureVersion);
        }
        if (!!$(data.featureReleaseDate).val()) {
            $("#featureReleaseDate").val(data.featureReleaseDate);
        }
        if (!!$(data.featureIcon).val()) {
            $("#featureIcon").val(data.featureIcon);
        }
        if (!!$(data.featureIsUnitSpecific).val()) {
            $("#featureIsUnitSpecific").val(data.featureIsUnitSpecific);
        }
        if (data.featureValid == 1) {
            $('.featureValid').toggles({on: true}, "featureValid");
        } else {
            $('.featureValid').toggles({on: false}, "featureValid");
        }
        if (data.featureParentGroup == 0) {
            $("#featureParentGroupDiv,#").addClass("hidden");
            $("#viewParentGroupDiv").addClass("hidden");
            $("#featureIsParent").prop("checked", false);

        } else {
            $("#featureIsParent").prop("checked", true);
            $("#viewParentGroupDiv").removeClass("hidden");
            $("#featureParentGroupDiv").removeClass("hidden");
        }
        $("#assignEntity").select2('data', unitList);
        $("#assignEntity").find(".select2-choices").find(".select2-search-choice-close").remove();
    }
    function refreshData() {
    }
    return {
        init: init,
        check: check,
        fetchFeatureList: fetchFeatureList,
        showFeatureDetail: showFeatureDetail,
        populateFields: populateFields,
        checkParent: checkParent,
        refreshData: refreshData
    };
}());
dcomethealth.Features.init();