<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../common/header.jsp" %>
<%@ include file="../common/topbar.jsp" %>
<%@ include file="../common/sidebar.jsp" %>

<!-- start: Content -->
<div id="content" class="span10">


    <div class="row-fluid">
        <div class="box span12">
            <div class="box-header" data-original-title="">
                <h2><i class="icon-align-justify"></i><span class="break"></span>Jobs</h2>

                <div class="box-icon">
                    <a href="#job_add"  data-toggle="modal"  class="btn-setting"><i class="icon-plus"></i></a>
                    <a href="#job_setting"  data-toggle="modal"  class="btn-setting"><i class="icon-wrench"></i></a>
                    <a href="#" class="btn-minimize"><i class="icon-chevron-up"></i></a>
                    <a href="#" class="btn-close"><i class="icon-remove"></i></a>
                </div>
            </div>
            <div class="box-content">
                <table class="table table-striped table-bordered bootstrap-datatable datatable">
                    <thead>
                    <tr>
                        <th>Job name</th>
                        <th>Jenkins home</th>
                        <th>Job type</th>
                        <th>Job status</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="job" items="${joblist}">
                        <tr>
                            <td>${job.getName()}</td>
                            <td class="center">${job.getJenkinsURL()}</td>
                            <td class="center">${job.getJobType()}</td>
                            <td class="center">
                                <span class="label">${job.getStatus()}</span>
                            </td>
                            <td class="center">
                                <a data-toggle="modal" class="btn btn-success" href="#">
                                    <i class="icon-zoom-in "></i>
                                </a>
                                <a data-toggle="modal" class="btn btn-info" href="#job_edit_${job.getId()}">
                                    <i class="icon-edit "></i>
                                </a>
                                <a data-toggle="modal" class="btn btn-danger" href="#job_delete_${job.getId()}">
                                    <i class="icon-trash "></i>
                                </a>
                            </td>
                        </tr>


                        <!-- Job eidt -->
                        <div class="modal hide fade" id="job_edit_${job.getId()}">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal">×</button>
                                <h3>Edit</h3>
                            </div>
                            <form class="form-default" method="post" action="/dashboard/job/edit">
                                <div class="modal-body">

                                    <input type="hidden" name="id" value="${job.getId()}"/>

                                    <p>
                                        <span class="span3">Job name:</span>
                                        <input type="text" name="name" value="${job.getName()}"/>
                                    </p>

                                    <p><span class="span3">Jenkins home:</span>
                                        <input type="text" name="jenkinsURL" value="${job.getJenkinsURL()}"/>
                                    </p>

                                    <p><span class="span3">Job type:</span>
                                        <input type="text" name="jobType" value="${job.getJobType()}"/></p>

                                    <p><span class="span3">Job status:</span>
                                        <input type="text" name="status" value="${job.getStatus()}"/></p>

                                    <p><span class="span3">Job description:</span>
                                        <input type="text" name="description" value="${job.getDescription()}"/>
                                    </p>

                                </div>
                                <div class="modal-footer">
                                    <a href="#" class="btn" data-dismiss="modal">Close</a>
                                    <input type="submit" class="btn btn-primary" value="Save"/>
                                </div>
                            </form>
                        </div>

                        <!-- Job delete -->
                        <div class="modal hide fade" id="job_delete_${job.getId()}">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal">×</button>
                                <h3>Delete</h3>
                            </div>
                            <div class="modal-body">
                                <p>Are you sure delete this job ?</p>
                            </div>
                            <div class="modal-footer">
                                <a href="#" class="btn" data-dismiss="modal">Close</a>
                                <a href="/dashboard/job/delete?id=${job.getId()}" class="btn btn-primary">Yes</a>
                            </div>
                        </div>


                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <!--/span-->

    </div>
    <!--/row-->


</div>
<!-- end: Content -->

</div><!--/fluid-row-->

<!-- Job List Setting-->
<div class="modal hide fade" id="job_setting">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">×</button>
        <h3>Settings</h3>
    </div>
    <div class="modal-body">
        <p>Here settings can be configured...</p>
    </div>
    <div class="modal-footer">
        <a href="#" class="btn" data-dismiss="modal">Close</a>
        <a href="#" class="btn btn-primary">Save changes</a>
    </div>
</div>

<!-- Job List Setting-->
<div class="modal hide fade" id="job_add">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">×</button>
        <h3>Add Job</h3>
    </div>
    <form class="form-default" method="post" action="/dashboard/job/add">
        <div class="modal-body">

            <p>
                <span class="span2">Job name:</span>
                <input type="text" name="name" value=""/>
            </p>

            <p><span class="span2">Jenkins home:</span>
                <input type="text" name="jenkinsURL" value=""/>
            </p>

            <p><span class="span2">Job type:</span>
                <input type="text" name="jobType" value=""/></p>

            <p><span class="span2">Job status:</span>
                <input type="text" name="status" value=""/></p>

            <p><span class="span2">Job description:</span>
                <input type="text" name="description" value=""/>
            </p>

        </div>
        <div class="modal-footer">
            <a href="#" class="btn" data-dismiss="modal">Close</a>
            <input type="submit" class="btn btn-primary" value="Add"/>
        </div>
    </form>
</div>

<%@ include file="../common/footer.jsp" %>

