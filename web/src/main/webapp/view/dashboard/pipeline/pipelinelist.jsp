<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../common/header.jsp" %>
<%@ include file="../common/topbar.jsp" %>
<%@ include file="../common/sidebar.jsp" %>

<div class="breadcrumbs" id="breadcrumbs">
    <ul class="breadcrumb">
        <li>
            <i class="icon-home home-icon"></i>
            <a href="#">Home</a>

							<span class="divider">
								<i class="icon-angle-right arrow-icon"></i>
							</span>
        </li>
        <li class="active">Pipeline List</li>
    </ul>
    <!--.breadcrumb-->

    <div class="nav-search" id="nav-search">
        <form class="form-search">
							<span class="input-icon">
								<input type="text" placeholder="Search ..." class="input-small nav-search-input"
                                       id="nav-search-input" autocomplete="off">
								<i class="icon-search nav-search-icon"></i>
							</span>
        </form>
    </div>
    <!--#nav-search-->
</div>

<!-- start: Content -->
<div class="page-content">
    <div class="page-header position-relative">
        <h1>
            Pipeline List
            <small>
                <i class="icon-double-angle-right"></i>
                Add & config Pipeline
            </small>
        </h1>
    </div>


    <c:forEach var="item" items="${pipelinesMap}">
    <div class="row-fluid">
        <div class="span12">

            <div class="widget-box">
                <div class="widget-header widget-header-small header-color-dark">
                    <h5 class="smaller">${item.key}</h5>

                    <div class="widget-toolbar">
                        <a href="#pipeline_add_${item.key}" title="Create a new pipeline in this group"
                           data-rel="tooltip"
                           data-toggle="modal" class="btn-setting"><i class="icon-plus"></i></a>
                        <a href="#" data-action="collapse"><i class="icon-chevron-up"></i></a>
                        <a href="#" data-action="close"><i class="icon-remove"></i></a>
                    </div>
                </div>

                <div class="widget-body">
                    <div class="widget-main">
                        <table class="table table-striped table-bordered">
                            <thead>
                            <tr>
                                <th>Pipeline</th>
                                <th>Show</th>
                                <th>Description</th>
                                <th>Actions</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="pipeline" items="${item.value}">
                                <tr>
                                    <td>${pipeline.getName()}</td>
                                    <td>${pipeline.getDisplay()}</td>
                                    <td class="center">${pipeline.getDescription()}</td>
                                    <td class="td-actions ">
                                        <div class="hidden-phone visible-desktop action-buttons">
                                                <%--<a class="blue"--%>
                                                <%--href="/dashboard/pipeline/pipelinesetting?pipelineId=${pipeline.getId()}">--%>
                                                <%--<i class="icon-sitemap bigger-130"></i>--%>
                                                <%--</a>--%>
                                            <a class="blue" href="#pipeline_refresh_${pipeline.getId()}"
                                               data-toggle="modal">
                                                <i class="icon-refresh bigger-130"></i>
                                            </a>

                                            <a class="green" href="#pipeline_edit_${pipeline.getId()}"
                                               data-toggle="modal">
                                                <i class="icon-pencil bigger-130"></i>
                                            </a>

                                            <a class="red" href="#pipeline_delete_${pipeline.getId()}"
                                               data-toggle="modal">
                                                <i class="icon-trash bigger-130"></i>
                                            </a>
                                        </div>
                                    </td>
                                </tr>


                                <!-- pipeline eidt -->
                                <div class="modal hide fade" id="pipeline_edit_${pipeline.getId()}">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal">×</button>
                                        <h3>Edit</h3>
                                    </div>
                                    <form class="form-default" method="post" action="/dashboard/pipeline/pipelineedit">
                                        <div class="modal-body">

                                            <input type="hidden" name="id" value="${pipeline.getId()}"/>

                                            <p>
                                                <span class="span3">Pipeline name:</span>
                                                <input type="text" name="name" value="${pipeline.getName()}"/>
                                            </p>
                                            <input type="hidden" name="groupId" value="${pipeline.getGroupId()}"/>

                                            <p><span class="span3">Pipeline description:</span>
                                                <input type="text" name="description"
                                                       value="${pipeline.getDescription()}"/>
                                            </p>

                                            <p><span class="span3">show in Pipelines:</span>
                                                <input id="display_${pipeline.getId()}" name="display" type="checkbox"
                                                       class="ace-switch ace-switch-5"
                                                       onclick="displaySwitch(${pipeline.getId()})"
                                                       <c:if test="${pipeline.getDisplay()}">value="true" checked</c:if>
                                                       <c:if test="${pipeline.getDisplay() eq false}">value="false"</c:if>/>
                                                <span class="lbl"></span>
                                            </p>

                                        </div>
                                        <div class="modal-footer">
                                            <a href="#" class="btn btn-small " data-dismiss="modal">Close</a>
                                            <input type="submit" class="btn btn-small  btn-primary" value="Save"/>
                                        </div>
                                    </form>
                                </div>

                                <!-- pipeline delete -->
                                <div class="modal hide fade" id="pipeline_delete_${pipeline.getId()}">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal">×</button>
                                        <h3>Delete</h3>
                                    </div>
                                    <div class="modal-body">
                                        <p>Are you sure delete this pipeline ?</p>
                                    </div>
                                    <div class="modal-footer">
                                        <a href="#" class="btn btn-small " data-dismiss="modal">Close</a>
                                        <a href="/dashboard/pipeline/pipelinedelete?id=${pipeline.getId()}"
                                           class="btn btn-small  btn-primary">Yes</a>
                                    </div>
                                </div>
                            </c:forEach>
                            </tbody>
                        </table>
                        <div><a href="#pipeline_add_${item.key}" data-toggle="modal"><span><img
                                src="/assets/img/icon_add.png" style="margin-top:-4px;"> Create a new pipeline within this group</span></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Pipeline Add-->
        <div class="modal hide fade" id="pipeline_add_${item.key}">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">×</button>
                <h3>Add Pipeline</h3>
            </div>
            <form class="form-default" method="post" action="/dashboard/pipeline/pipelineadd">
                <div class="modal-body">

                    <p>
                        <span class="span4">Pipeline name:</span>
                        <input type="text" name="name" value=""/>
                    </p>

                    <p>
                        <span class="span4">Pipeline group:</span>
                        <input type="text" name="groupName" readonly value="${item.key}"/>
                    </p>

                    <p>
                        <span class="span4">Initial Project:</span>
                        <select name="headProjectId">
                            <c:forEach var="project" items="${projectList}">
                                <option value="${project.getId()}">${project.getName()}</option>
                            </c:forEach>
                        </select>
                    </p>

                    <p><span class="span4">Pipeline description:</span>
                        <input type="text" name="description" value=""/>
                    </p>


                    <p><span class="span4">show in Pipelines:</span>
                        <input id="display_0" name="display" type="checkbox"
                               class="ace-switch ace-switch-5" value="false"
                               onclick="displaySwitch(0)"/>
                        <span class="lbl"></span>
                    </p>

                </div>
                <div class="modal-footer">
                    <a href="#" class="btn btn-small " data-dismiss="modal">Close</a>
                    <input type="submit" class="btn btn-small btn-primary" value="Add"/>
                </div>
            </form>
        </div>

        </c:forEach>

    </div>
    <!-- end: Content -->

</div>
<!--/fluid-row-->

<%@ include file="../common/footer.jsp" %>
<script type="application/javascript">
    $('[data-rel=tooltip]').tooltip();
    function displaySwitch(id) {
        var elementId = "display_" + id;
        var isDisplay = document.getElementById(elementId).value;
        if (isDisplay == "false") {
            document.getElementById(elementId).value = "true";
        } else {
            document.getElementById(elementId).value = "false";
        }
    }
</script>
