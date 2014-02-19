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
        <li class="active">Pipeline Group</li>
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
            Pipeline Groups
            <small>
                <i class="icon-double-angle-right"></i>
            </small>
        </h1>
    </div>
    <div class="row-fluid">
        <div class="span12">
            <div style="margin-bottom: 5px;">
                <a href="#group_add" data-toggle="modal"> <button class="button" style="padding:6px 8px;"><i
                        class="icon-plus"></i> Add Group</button></a>
            </div>

            <table class="table table-striped table-bordered bootstrap-datatable datatable">
                <thead>
                <tr>
                    <th>Group name</th>
                    <th>Show</th>
                    <th>Description</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="group" items="${grouplist}">
                    <tr>
                        <td>${group.getName()}</td>
                        <td>${group.getDisplay()}</td>
                        <td>${group.getDescription()}</td>

                        <td class="td-actions ">
                            <div class="hidden-phone visible-desktop action-buttons">

                                <a class="green" href="#group_edit_${group.getId()}" data-toggle="modal">
                                    <i class="icon-pencil bigger-130"></i>
                                </a>

                                <a class="red" href="#group_delete_${group.getId()}" data-toggle="modal">
                                    <i class="icon-trash bigger-130"></i>
                                </a>
                            </div>
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

                                <p><span class="span3">show in Pipelines:</span>
                                    <input id="display_${group.getId()}" name="display" type="checkbox"
                                           class="ace-switch ace-switch-5"
                                           onclick="displaySwitch(${group.getId()})"
                                           <c:if test="${group.getDisplay()}">value="true" checked </c:if>
                                           <c:if test="${group.getDisplay() eq false}">value="false"</c:if>/>
                                    <span class="lbl"></span>
                                </p>

                            </div>
                            <div class="modal-footer">
                                <a href="#" class="btn btn-small" data-dismiss="modal">Close</a>
                                <input type="submit" class="btn btn-small btn-primary" value="Save"/>
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
                            <a href="#" class="btn btn-small" data-dismiss="modal">Close</a>
                            <a href="/dashboard/pipeline/groupdelete?id=${group.getId()}"
                               class="btn btn-small btn-primary">Yes</a>
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

</div><!--/fluid-row-->

<!-- Group List Add-->
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

            <p><span class="span2">show in Pipelines:</span>
                <select name="display">
                    <option value="0">No</option>
                    <option value="1">Yes</option>
                </select>
            </p>

        </div>
        <div class="modal-footer">
            <a href="#" class="btn btn-small" data-dismiss="modal">Close</a>
            <input type="submit" class="btn btn-small btn-primary" value="Add"/>
        </div>
    </form>
</div>

<%@ include file="../common/footer.jsp" %>
<script type="application/javascript">
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

