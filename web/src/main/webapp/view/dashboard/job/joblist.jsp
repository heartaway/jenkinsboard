<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/header.jsp" %>
<%@ include file="../common/topbar.jsp" %>
<%@ include file="../common/sidebar.jsp" %>

<!-- start: Content -->
<div id="content" class="span10">


    <div class="row-fluid">
        <div class="box span12">
            <div class="box-header" data-original-title="">
                <h2><i class="icon-user"></i><span class="break"></span>Jobs</h2>

                <div class="box-icon">
                    <a href="#" class="btn-setting"><i class="icon-wrench"></i></a>
                    <a href="#" class="btn-minimize"><i class="icon-chevron-up"></i></a>
                    <a href="#" class="btn-close"><i class="icon-remove"></i></a>
                </div>
            </div>
            <div class="box-content">
                <table class="table table-striped table-bordered bootstrap-datatable datatable">
                    <thead>
                    <tr>
                        <th>Job name</th>
                        <th>Jenkins Home</th>
                        <th>Job type</th>
                        <th>Job status</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>

                    <%
                    for(int i=0;i<$joblist.size();i++){

                    %>
                    <tr>
                        <td>$joblist.getName()</td>
                        <td class="center">$joblist.getJenkinsURL()</td>
                        <td class="center">$joblist.getJobType</td>
                        <td class="center">
                            <span class="label">$joblist.getStatus</span>
                        </td>
                        <td class="center">
                            <a class="btn btn-success" href="#">
                                <i class="icon-zoom-in "></i>
                            </a>
                            <a class="btn btn-info" href="#">
                                <i class="icon-edit "></i>
                            </a>
                            <a class="btn btn-danger" href="#">
                                <i class="icon-trash "></i>
                            </a>
                        </td>
                    </tr>
                    <%
                        }
                    %>
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

<div class="modal hide fade" id="myModal">
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
<%@ include file="../common/footer.jsp" %>

