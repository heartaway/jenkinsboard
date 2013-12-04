<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../common/header.jsp" %>
<%@ include file="../common/topbar.jsp" %>
<%@ include file="../common/sidebar.jsp" %>

<!-- start: Content -->
<div id="content" class="span10">


    <c:forEach var="item" items="${pipelinesMap}">
        <div class="row-fluid">
            <div class="box span12">
                <div class="box-header" data-original-title="">
                    <h2><i class="icon-align-justify"></i><span class="break"></span>${item.key}</h2>

                    <div class="box-icon">
                        <a href="#pipeline_add_${item.key}" title="Create a new pipeline in this group" data-rel="tooltip"
                           data-toggle="modal" class="btn-setting"><i class="icon-plus"></i></a>
                        <a href="#" class="btn-minimize"><i class="icon-chevron-up"></i></a>
                        <a href="#" class="btn-close"><i class="icon-remove"></i></a>
                    </div>
                </div>
                <div class="box-content">
                    <table class="table table-striped table-bordered">
                        <thead>
                        <tr>
                            <th>Pipeline</th>
                            <th>Description</th>
                            <th>Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="pipeline" items="${item.value}">
                            <tr>
                                <td>${pipeline.getName()}</td>
                                <td class="center">${pipeline.getDescription()}</td>
                                <td class="center">
                                    <a data-toggle="modal" class="btn btn-info"
                                       href="#pipeline_edit_${pipeline.getId()}">
                                        <i class="icon-edit "></i>
                                    </a>
                                    <a data-toggle="modal" class="btn btn-danger"
                                       href="#pipeline_delete_${pipeline.getId()}">
                                        <i class="icon-trash "></i>
                                    </a>
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
                                            <input type="text" name="description" value="${pipeline.getDescription()}"/>
                                        </p>

                                    </div>
                                    <div class="modal-footer">
                                        <a href="#" class="btn" data-dismiss="modal">Close</a>
                                        <input type="submit" class="btn btn-primary" value="Save"/>
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
                                    <a href="#" class="btn" data-dismiss="modal">Close</a>
                                    <a href="/dashboard/pipeline/pipelinedelete?id=${pipeline.getId()}"
                                       class="btn btn-primary">Yes</a>
                                </div>
                            </div>
                        </c:forEach>
                        </tbody>
                    </table>
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
                        <input type="text" name="groupName" readonly  value="${item.key}"/>
                    </p>

                    <p><span class="span4">Pipeline description:</span>
                        <input type="text" name="description" value=""/>
                    </p>

                </div>
                <div class="modal-footer">
                    <a href="#" class="btn" data-dismiss="modal">Close</a>
                    <input type="submit" class="btn btn-primary" value="Add"/>
                </div>
            </form>
        </div>

    </c:forEach>

</div>
<!-- end: Content -->

</div><!--/fluid-row-->


<%@ include file="../common/footer.jsp" %>

