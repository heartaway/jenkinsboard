<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<!-- start: Main Menu -->
<div id="sidebar-left" class="span2">

    <div class="row-fluid actions">

        <%--<input type="text" class="search span12" placeholder="..."/>--%>

    </div>

    <div class="nav-collapse sidebar-nav">
        <ul class="nav nav-tabs nav-stacked main-menu">
            <li><a href="/"><i class="icon-dashboard"></i><span class="hidden-tablet"> Dashboard</span></a></li>
            <li><a href="/"><i class="icon-bar-chart"></i><span class="hidden-tablet"> Pipelines</span></a></li>
            <li><a href="/dashboard/job/joblist"><i class="icon-tasks"></i><span class="hidden-tablet">Original Job</span></a></li>
            <li><a href="/dashboard/pipeline/grouplist"><i class="icon-sitemap"></i><span class="hidden-tablet">Pipeline Group</span></a>
            <li><a href="/dashboard/pipeline/pipelinelist"><i class="icon-cogs"></i><span class="hidden-tablet">Pipeline Setting</span></a>
            </li>
        </ul>
    </div>
</div>
<!-- end: Main Menu -->
