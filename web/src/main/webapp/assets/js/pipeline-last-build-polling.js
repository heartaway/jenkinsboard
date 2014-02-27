!function ($) {
    var pipelineLastBuildPolling = document.getElementsByName("pipelineLastBuildPolling");
    if (pipelineLastBuildPolling.length == 0) {
        return;
    }
    var buildPipelineIds = "";
    for (var i = 0; i < pipelineLastBuildPolling.length; i++) {
        buildPipelineIds = buildPipelineIds + "," + pipelineLastBuildPolling[i].value;
    }
    if (buildPipelineIds.substring(0, 1) == ",") {
        buildPipelineIds = buildPipelineIds.substring(1, buildPipelineIds.length);
    }
//    deployAppIntervalID = setInterval(getPipelineLastBuildPollingResult, 2000);

}(window.jQuery);