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
        <li>
            <a href="/dashboard/build/projectbuildlist?projectId=${projectId}&pageIndex=1">Project Build List</a>

							<span class="divider">
								<i class="icon-angle-right arrow-icon"></i>
							</span>
        </li>
        <li class="active">Project Build Detail</li>
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
            Project Build Detail
            <small>
                <i class="icon-double-angle-right"></i>

            </small>
        </h1>
    </div>
    <div class="row-fluid">
        <div class="span12">

        </div>
        <!--/span-->
    </div>

</div>
<!-- end: Content -->

</div>
<!--/fluid-row-->

<%@ include file="../common/footer.jsp" %>
<script src="/assets/js/bootstrap-pagination.js" type="application/javascript"></script>
