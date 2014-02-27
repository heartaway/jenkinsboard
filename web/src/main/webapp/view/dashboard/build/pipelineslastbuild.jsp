<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="../common/header.jsp" %>
<%@ include file="../common/topbar.jsp" %>
<%@ include file="../common/sidebar.jsp" %>
<!--.start: Breadcrumb-->
<div class="breadcrumbs" id="breadcrumbs">
    <ul class="breadcrumb">
        <li>
            <i class="icon-home home-icon"></i>
            <a href="/">Home</a>
							<span class="divider">
								<i class="icon-angle-right arrow-icon"></i>
							</span>
        </li>
        <li>Pipelines</li>
    </ul>
    <!--.breadcrumb-->
</div>

<!-- start: Content -->
<div class="page-content">
    <div class="page-header position-relative">
        <h1>
            Pipelines
            <small>
                <i class="icon-double-angle-right"></i>
                last Build Info Order By Group
            </small>
        </h1>
    </div>

    <div class="row-fluid">
        <div class="span12">
            <c:forEach var="item" items="${displayPipelinesMap}">
                <div class="row-fluid">
                    <h4 class="lighter smaller purple">
                        <img src="/assets/img/icon_pipelinegroup.png" style="margin-top:-5px;">
                            ${item.key}
                    </h4>
                    <c:forEach var="pipelineBuild" items="${item.value}">
                        <!-- 单个pipeline 的最后一次构建信息 -->
                        <div class="span4">
                            <div class="widget-box">
                                <div class="widget-header widget-header-flat">
                                    <h5><img src="/assets/img/icon_pipeline.png" style="margin-top:-5px;">
                                            ${pipelineBuild.getPipelineDO().getName()}
                                    </h5>
                                     <span class="widget-toolbar">
                                             <c:if test="${pipelineBuild.getBuildPipelineDO().getStatus() == 1}">
                                                 <input type="hidden" name="pipelineLastBuildPolling"
                                                        value="${pipelineBuild.getBuildPipelineDO().getId()}"/>
                                                 <span id="pipelineBuildWaiting_${pipelineBuild.getBuildPipelineDO().getId()}">
                                                     <img id="" src="/assets/img/status_waiting.gif"
                                                          style="margin-top:-2px;">
                                                 </span>
                                             </c:if>
                                      </span>
                                </div>
                                <div class="widget-body" style="height:200px;">
                                    <div class="widget-main">
                                        <div class="row-fluid">
                                            <!-- 不存在构建信息 -->
                                            <c:if test="${empty pipelineBuild.getBuildGrid()}">
                                                <span>No historical data</span>
                                            </c:if>
                                            <!-- 存在构建信息 -->
                                            <c:if test="${!empty pipelineBuild.getBuildGrid()}">
                                                <!-- 构建基本信息 -->
                                                <div>
                                                    <span>
                                                        <i class="icon-time"></i>
                                                        ${pipelineBuild.getBuildPipelineDO().getGmtStart()}
                                                    </span>
                                                </div>
                                                <table style="width:100%">
                                                    <c:forEach var="row" begin="0"
                                                               end="${pipelineBuild.getGridRowSize() -1}"
                                                               step="1">
                                                        <tr>
                                                            <c:forEach var="col" begin="0"
                                                                       end="${pipelineBuild.getGridColumnSize() -1}"
                                                                       step="1">
                                                                <c:if test="${empty pipelineBuild.getBuildGrid().getData().get(row).get(col)}">
                                                                    <td></td>
                                                                </c:if>
                                                                <c:if test="${!empty  pipelineBuild.getBuildGrid().getData().get(row).get(col)}">
                                                                    <td style="width:${pipelineBuild.getGridPercent()}">
                                                                        <a href="/dashboard/build/projectbuilddetail?buildProjectId=${pipelineBuild.getBuildGrid().getData().get(row).get(col).getId()}&projectId=${pipelineBuild.getBuildGrid().getData().get(row).get(col).getProjectId()}"
                                                                           data-rel="tooltip"
                                                                           data-original-title="${pipelineBuild.getBuildGrid().getData().get(row).get(col).getProjectName()}">
                                                                            <!--  任务未触发时 状态  -->
                                                                            <c:if test="${pipelineBuild.getBuildGrid().getData().get(row).get(col).getStatus() == -1}">
                                                                                <div id="projectBuild_${pipelineBuild.getBuildGrid().getData().get(row).get(col).getId()}" class="progress"
                                                                                     style="margin-bottom: 10px;">
                                                                                    <div class="bar"
                                                                                         style="width: 0%;"></div>
                                                                                </div>
                                                                            </c:if>
                                                                            <!--  任务 进行中时 状态  -->
                                                                            <c:if test="${pipelineBuild.getBuildGrid().getData().get(row).get(col).getStatus() == 1}">
                                                                                <div  id="projectBuild_${pipelineBuild.getBuildGrid().getData().get(row).get(col).getId()}" class="progress progress-purple progress-striped active"
                                                                                     style="margin-bottom: 10px;">
                                                                                    <div class="bar"
                                                                                         style="width: 20%;"></div>
                                                                                </div>

                                                                            </c:if>
                                                                            <!--  任务 已结束 状态  -->
                                                                            <c:if test="${pipelineBuild.getBuildGrid().getData().get(row).get(col).getStatus() == 0}">

                                                                                <!-- 构建结果： 失败 -->
                                                                                <c:if test="${pipelineBuild.getBuildGrid().getData().get(row).get(col).getResult() == 0}">
                                                                                    <div   id="projectBuild_${pipelineBuild.getBuildGrid().getData().get(row).get(col).getId()}" class="progress progress-danger"
                                                                                         style="margin-bottom: 10px;">
                                                                                        <div class="bar"
                                                                                             style="width: 100%;"></div>
                                                                                    </div>
                                                                                </c:if>

                                                                                <!-- 构建结果： 成功 -->
                                                                                <c:if test="${pipelineBuild.getBuildGrid().getData().get(row).get(col).getResult() == 1}">
                                                                                    <div   id="projectBuild_${pipelineBuild.getBuildGrid().getData().get(row).get(col).getId()}" class="progress progress-success"
                                                                                         style="margin-bottom: 10px;">
                                                                                        <div class="bar"
                                                                                             style="width: 100%;"></div>
                                                                                    </div>
                                                                                </c:if>

                                                                                <!-- 构建结果： 不稳定 -->
                                                                                <c:if test="${pipelineBuild.getBuildGrid().getData().get(row).get(col).getResult() == 2}">
                                                                                    <div   id="projectBuild_${pipelineBuild.getBuildGrid().getData().get(row).get(col).getId()}" class="progress progress-warning"
                                                                                         style="margin-bottom: 10px;">
                                                                                        <div class="bar"
                                                                                             style="width: 100%;"></div>
                                                                                    </div>
                                                                                </c:if>

                                                                                <!-- 构建结果： 被中断 -->
                                                                                <c:if test="${pipelineBuild.getBuildGrid().getData().get(row).get(col).getResult() == 5}">
                                                                                    <div  id="projectBuild_${pipelineBuild.getBuildGrid().getData().get(row).get(col).getId()}"  class="progress "
                                                                                         style="margin-bottom: 10px;">
                                                                                        <div class="bar"
                                                                                             style="width: 0%;"></div>
                                                                                    </div>
                                                                                </c:if>

                                                                                <!-- 构建结果： 没有被构建 -->
                                                                                <c:if test="${pipelineBuild.getBuildGrid().getData().get(row).get(col).getResult() == 6}">
                                                                                    <div   id="projectBuild_${pipelineBuild.getBuildGrid().getData().get(row).get(col).getId()}" class="progress "
                                                                                         style="margin-bottom: 10px;">
                                                                                        <div class="bar blue"
                                                                                             style="width: 100%;"></div>
                                                                                    </div>
                                                                                </c:if>

                                                                                <!-- 构建结果： 未知 -->
                                                                                <c:if test="${pipelineBuild.getBuildGrid().getData().get(row).get(col).getResult() == 7}">
                                                                                    <div   id="projectBuild_${pipelineBuild.getBuildGrid().getData().get(row).get(col).getId()}" class="progress "
                                                                                         style="margin-bottom: 10px;">
                                                                                        <div class="bar"
                                                                                             style="width: 0%;"></div>
                                                                                    </div>
                                                                                </c:if>

                                                                            </c:if>
                                                                        </a>
                                                                    </td>
                                                                </c:if>
                                                            </c:forEach>
                                                        </tr>
                                                    </c:forEach>
                                                </table>
                                            </c:if>
                                            <hr>

                                            <c:if test="${ (!empty pipelineBuild.getBuildPipelineDO()) && pipelineBuild.getBuildPipelineDO().getStatus() == 1}">
                                                <button class="button" style="width: 40px;height: 24px;" disabled>
                                                    <span><i class="icon-play"></i></span>
                                                </button>
                                                <a href="/dashboard/build/stoppipelinebuild?buildPipelineId=${pipelineBuild.getBuildPipelineDO().getId()}">
                                                    <button class="button" style="width: 40px;height: 24px;">
                                                        <i class="icon-pause"></i></span>
                                                    </button>
                                                </a>
                                            </c:if>

                                            <c:if test="${(empty pipelineBuild.getBuildPipelineDO()) || pipelineBuild.getBuildPipelineDO().getStatus() == 0}">
                                                <a href="/dashboard/build/triggerpipelinebuild?pipelineId=${pipelineBuild.getPipelineDO().getId()}">
                                                    <button class="button" style="width: 40px;height: 24px;">
                                                        <span><i class="icon-play"></i></span>
                                                    </button>
                                                </a>
                                                <button class="button" style="width: 40px;height: 24px;" disabled>
                                                    <span><i class="icon-pause"></i></span><a> </a>
                                                </button>
                                            </c:if>

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <hr>
            </c:forEach>
        </div>
    </div>
</div>
<!-- end: Content -->
<%@ include file="../common/footer.jsp" %>
<script type="application/javascript">
    $('[data-rel=tooltip]').tooltip();
    $('[data-rel=popover]').popover({container: 'body'});
</script>
<script type="application/javascript" src="/assets/js/pipeline-last-build-polling.js"></script>

