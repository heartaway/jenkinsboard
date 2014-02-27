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
        <li class="active">Project Build List</li>
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
            Project Build List
            <small>
                <i class="icon-double-angle-right"></i>
                ${projectName}
            </small>
        </h1>
    </div>
    <div class="row-fluid">
        <div class="span12">
            <table class="table table-striped table-bordered bootstrap-datatable datatable">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Status</th>
                    <th>Result</th>
                    <th>Duration</th>
                    <th>Comment</th>
                    <th>Start Time</th>
                    <th>End Time</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="buildProject" items="${buildProjectDOPage.getDatas()}">
                    <tr>
                        <td>
                            <a href="/dashboard/build/projectbuilddetail?buildProjectId=${buildProject.getId()}&projectId=${projectId}">${buildProject.getId()}</a>
                        </td>
                        <td>
                            <c:if test="${buildProject.getStatus() == 0}">
                                未运行
                            </c:if>
                            <c:if test="${buildProject.getStatus() == 1}">
                                运行中...
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${buildProject.getResult() == 0}">
                                <span class="badge badge-important">构建失败</span>
                            </c:if>
                            <c:if test="${buildProject.getResult() == 1}">
                                <span class="badge badge-success">构建成功</span>
                            </c:if>
                            <c:if test="${buildProject.getResult() == 2}">
                                <span class="badge badge-warning">构建不稳定</span>
                            </c:if>
                            <c:if test="${buildProject.getResult() == 3}">
                                <span class="badge badge-info">构建运行中</span>
                            </c:if>
                            <c:if test="${buildProject.getResult() == 4}">
                                <span class="badge badge-purple">重新构建中</span>
                            </c:if>
                            <c:if test="${buildProject.getResult() == 5}">
                                <span class="badge badge-grey">被中断</span>
                            </c:if>
                            <c:if test="${buildProject.getResult() == 6}">
                                <span class="badge badge-inverse"></span>
                            </c:if>
                            <c:if test="${buildProject.getResult() == 7}">
                                <span class="badge badge-inverse">未知</span>
                            </c:if>
                        </td>
                        <td>${buildProject.getDuration()}</td>
                        <td>${buildProject.getComment()}</td>
                        <td>${buildProject.getGmtStart()}</td>
                        <td>${buildProject.getGmtEnd()}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <!--/span-->
    </div>

    <!-- page bar-->
    <div class="row-fluid">
        <div class="span12">
            <input id="pageIndex" type="hidden" value="${pageIndex}"/>
            <input id="totalPageSize" type="hidden" value="${totalPageSize}"/>
            <input id="pageUrl" type="hidden" value="/dashboard/build/projectbuildlist?projectId=${projectId}"/>

            <div id="pagination"></div>
        </div>
    </div>
</div>
<!-- end: Content -->

</div>
<!--/fluid-row-->

<%@ include file="../common/footer.jsp" %>
<script src="/assets/js/bootstrap-pagination.js" type="application/javascript"></script>
