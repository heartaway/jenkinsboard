<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ page import="com.taobao.tae.ewall.project.ProjectType" %>
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
        <li class="active">Projects</li>
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
            Projects
            <small>
                <i class="icon-double-angle-right"></i>
                Jobs From Jenkins
            </small>
        </h1>
    </div>
    <div class="row-fluid">
        <div class="span12">
            <div style="margin-bottom: 5px;">
                <a href="#project_add" data-toggle="modal"> <button class="button" style="padding:6px 8px;"><i class="icon-plus"></i> Add Project</button></a>
            </div>
            <table class="table table-striped table-bordered bootstrap-datatable datatable">
                <thead>
                <tr>
                    <th>Project name</th>
                    <th>Jenkins home</th>
                    <th>Project type</th>
                    <th>Project status</th>
                    <th>Project Description</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="project" items="${projectlist}">
                    <tr>
                        <td><a href="/dashboard/build/projectbuildlist?projectId=${project.getId()}&pageIndex=1" >${project.getName()}</a></td>
                        <td class="center">${project.getJenkinsURL()}</td>
                        <td class="center">
                            <c:forEach var="type" items="${projectType}">
                                <c:if test="${type.key eq project.getProjectType()}">
                                    ${type.value}
                                </c:if>
                            </c:forEach>
                        </td>
                        <td class="center">
                            <span class="label">${project.getStatus()}</span>
                        </td>
                        <td class="center">${project.getDescription()}</td>
                        <td class="td-actions ">
                            <div class="hidden-phone visible-desktop action-buttons">
                                <a class="blue" href="${project.getJenkinsURL()}/job/${project.getName()}">
                                    <i class="icon-zoom-in bigger-130"></i>
                                </a>

                                <a class="green" href="#project_edit_${project.getId()}" data-toggle="modal">
                                    <i class="icon-pencil bigger-130"></i>
                                </a>

                                <a class="red" href="#project_delete_${project.getId()}" data-toggle="modal">
                                    <i class="icon-trash bigger-130"></i>
                                </a>
                            </div>
                        </td>
                    </tr>


                    <!-- Project eidt -->
                    <div class="modal hide fade" id="project_edit_${project.getId()}">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">×</button>
                            <h3>Edit</h3>
                        </div>
                        <form class="form-default" method="post" action="/dashboard/project/edit">
                            <div class="modal-body">

                                <input type="hidden" name="id" value="${project.getId()}"/>

                                <p>
                                    <span class="span3">Project name:</span>
                                    <input type="text" name="name" value="${project.getName()}"/>
                                </p>

                                <p><span class="span3">Jenkins home:</span>
                                    <input type="text" name="jenkinsURL" value="${project.getJenkinsURL()}"/>
                                </p>

                                <p><span class="span3">Project type:</span>
                                    <select name="projectType">
                                        <c:forEach var="type" items="${projectType}">
                                            <c:if test="${type.key eq project.getProjectType()}">
                                                <option value="${type.key}" selected>${type.value}</option>
                                            </c:if>
                                            <c:if test="${type.key != project.getProjectType()}">
                                                <option value="${type.key}">${type.value}</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </p>

                                <p><span class="span3">Project status:</span>
                                    <input type="text" name="status" value="${project.getStatus()}"/></p>

                                <p><span class="span3">Project description:</span>
                                    <input type="text" name="description" value="${project.getDescription()}"/>
                                </p>

                            </div>
                            <div class="modal-footer">
                                <a href="#" class="btn btn-small" data-dismiss="modal">Close</a>
                                <input type="submit" class="btn btn-small btn-primary" value="Save"/>
                            </div>
                        </form>
                    </div>

                    <!-- Project delete -->
                    <div class="modal hide fade" id="project_delete_${project.getId()}">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">×</button>
                            <h3>Delete</h3>
                        </div>
                        <div class="modal-body">
                            <p>Are you sure delete this project ?</p>
                        </div>
                        <div class="modal-footer">
                            <a href="#" class="btn btn-small" data-dismiss="modal">Close</a>
                            <a href="/dashboard/project/delete?id=${project.getId()}" class="btn btn-small btn-primary">Yes</a>
                        </div>
                    </div>


                </c:forEach>
                </tbody>
            </table>
        </div>
        <!--/span-->

    </div>
    <!--/row-->


</div>
<!-- end: Content -->

<div class="ace-settings-container" id="ace-settings-container">
    <div class="btn btn-app btn-mini btn-warning ace-settings-btn" id="ace-settings-btn">
        <i class="icon-cog bigger-150"></i>
    </div>

    <div class="ace-settings-box" id="ace-settings-box">
        <div>
            <div class="pull-left">
                <select id="skin-colorpicker" class="hide" style="display: none;">
                    <option data-class="default" value="#438EB9">#438EB9
                    </option>
                    <option data-class="skin-1" value="#222A2D">#222A2D
                    </option>
                    <option data-class="skin-2" value="#C6487E">#C6487E
                    </option>
                    <option data-class="skin-3" value="#D0D0D0">#D0D0D0
                    </option>
                </select>

                <div class="dropdown dropdown-colorpicker"><a data-toggle="dropdown" class="dropdown-toggle"
                                                              href="#"><span class="btn-colorpicker"
                                                                             style="background-color:#438EB9"></span></a>
                    <ul class="dropdown-menu dropdown-caret">
                        <li><a class="colorpick-btn selected" href="#" style="background-color:#438EB9;"
                               data-color="#438EB9"></a></li>
                        <li><a class="colorpick-btn" href="#" style="background-color:#222A2D;"
                               data-color="#222A2D"></a></li>
                        <li><a class="colorpick-btn" href="#" style="background-color:#C6487E;"
                               data-color="#C6487E"></a></li>
                        <li><a class="colorpick-btn" href="#" style="background-color:#D0D0D0;"
                               data-color="#D0D0D0"></a></li>
                    </ul>
                </div>
            </div>
            <span>&nbsp; Choose Skin</span>
        </div>

        <div>
            <input type="checkbox" class="ace-checkbox-2" id="ace-settings-header">
            <label class="lbl" for="ace-settings-header"> Fixed Header</label>
        </div>

        <div>
            <input type="checkbox" class="ace-checkbox-2" id="ace-settings-sidebar">
            <label class="lbl" for="ace-settings-sidebar"> Fixed Sidebar</label>
        </div>

        <div>
            <input type="checkbox" class="ace-checkbox-2" id="ace-settings-breadcrumbs">
            <label class="lbl" for="ace-settings-breadcrumbs"> Fixed Breadcrumbs</label>
        </div>

        <div>
            <input type="checkbox" class="ace-checkbox-2" id="ace-settings-rtl">
            <label class="lbl" for="ace-settings-rtl"> Right To Left (rtl)</label>
        </div>
    </div>
</div>

</div><!--/fluid-row-->

<!-- Project List Add-->
<div class="modal hide fade in" id="project_add" style="display: none; ">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">×</button>
        <h3>Add Project</h3>
    </div>
    <form class="form-default" method="post" action="/dashboard/project/add">
        <div class="modal-body">

            <p>
                <span class="span2">Project name:</span>
                <input type="text" name="name" value=""/>
            </p>

            <p><span class="span2">Jenkins home:</span>
                <input type="text" name="jenkinsURL" value=""/>
            </p>

            <p><span class="span2">Project type:</span>
                <select name="projectType">
                    <c:forEach var="type" items="${projectType}">
                        <option value="${type.key}">${type.value}</option>
                    </c:forEach>
                </select>
            </p>

            <p><span class="span2">Project status:</span>
                <input type="text" name="status" value=""/></p>

            <p><span class="span2">Project description:</span>
                <input type="text" name="description" value=""/>
            </p>

        </div>
        <div class="modal-footer">
            <a href="#" class="btn btn-small" data-dismiss="modal">Close</a>
            <input type="submit" class="btn  btn-small btn-primary" value="Add"/>
        </div>
    </form>
</div>

<%@ include file="../common/footer.jsp" %>

