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
                <h2><i class="icon-align-justify"></i><span class="break"></span>Pipeline Groups</h2>

                <div class="box-icon">
                    <a href="#group_add" data-toggle="modal" class="btn-setting"><i class="icon-plus"></i></a>
                    <a href="#group_setting" data-toggle="modal" class="btn-setting"><i class="icon-wrench"></i></a>
                    <a href="#" class="btn-minimize"><i class="icon-chevron-up"></i></a>
                    <a href="#" class="btn-close"><i class="icon-remove"></i></a>
                </div>
            </div>
            <div class="box-content">
                <table class="table table-striped table-bordered bootstrap-datatable datatable">
                    <thead>
                    <tr>
                        <th>Group name</th>
                        <th>Description</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="group" items="${grouplist}">
                        <tr>
                            <td>${group.getName()}</td>
                            <td class="center">${group.getDescription()}</td>
                            <td class="center">
                                <a data-toggle="modal" class="btn btn-info" href="#group_edit_${group.getId()}">
                                    <i class="icon-edit "></i>
                                </a>
                                <a data-toggle="modal" class="btn btn-danger" href="#group_delete_${group.getId()}">
                                    <i class="icon-trash "></i>
                                </a>
                            </td>
                        </tr>


                        <!-- Group eidt -->
                        <div class="modal hide fade" id="group_edit_${group.getId()}">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal">×</button>
                                <h3>Edit</h3>
                            </div>
                            <form class="form-default" method="post" action="/dashboard/pipeline/groupedit">
                                <div class="modal-body">

                                    <input type="hidden" name="id" value="${group.getId()}"/>

                                    <p>
                                        <span class="span3">Group name:</span>
                                        <input type="text" name="name" value="${group.getName()}"/>
                                    </p>

                                    <p><span class="span3">Group description:</span>
                                        <input type="text" name="description" value="${group.getDescription()}"/>
                                    </p>

                                </div>
                                <div class="modal-footer">
                                    <a href="#" class="btn" data-dismiss="modal">Close</a>
                                    <input type="submit" class="btn btn-primary" value="Save"/>
                                </div>
                            </form>
                        </div>

                        <!-- Group delete -->
                        <div class="modal hide fade" id="group_delete_${group.getId()}">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal">×</button>
                                <h3>Delete</h3>
                            </div>
                            <div class="modal-body">
                                <p>Are you sure delete this group ?</p>
                            </div>
                            <div class="modal-footer">
                                <a href="#" class="btn" data-dismiss="modal">Close</a>
                                <a href="/dashboard/pipeline/groupdelete?id=${group.getId()}" class="btn btn-primary">Yes</a>
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

<!-- Group List Setting-->
<div class="modal hide fade" id="group_setting">
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

<!-- Group List Setting-->
<div class="modal hide fade" id="group_add">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">×</button>
        <h3>Add Group</h3>
    </div>
    <form class="form-default" method="post" action="/dashboard/pipeline/groupadd">
        <div class="modal-body">

            <p>
                <span class="span2">Group name:</span>
                <input type="text" name="name" value=""/>
            </p>

            <p><span class="span2">Group description:</span>
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

