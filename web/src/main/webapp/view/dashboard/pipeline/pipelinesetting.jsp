<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="../common/header.jsp" %>
<%@ include file="../common/topbar.jsp" %>
<%@ include file="../common/sidebar.jsp" %>
<!-- start: Content -->
<div class="page-content">
    <div class="row-fluid">
        <table>
            <c:forEach var="row" begin="0" end="${rowSize -1}" step="1">
                <tr>
                    <c:forEach var="col" begin="0" end="${columnSize -1}" step="1">
                    <div class="row-fluid">
                        <c:if test="${empty projectGrid.getData().get(row).get(col)}">
                            <td></td>
                        </c:if>
                        <c:if test="${!empty  projectGrid.getData().get(row).get(col)}">
                            <td class="span3 smallstat box mobileHalf">
                                <div class="span10">
                                        ${projectGrid.getData().get(row).get(col).getName()}
                                </div>

                                <div class="span2">
                                    <table>
                                        <tr>
                                            <td>
                                                <a href="#"><span class="icon-edit" style=""></span></a>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <a href="#add_down_stream_${ projectGrid.getData().get(row).get(col).getId()}"
                                                   data-toggle="modal"><span class="icon-plus"></span></a>
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                            </td>

                            <!-- Project Add In Pipeline -->
                            <div class="modal hide fade"
                                 id="add_down_stream_${ projectGrid.getData().get(row).get(col).getId()}">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal">×</button>
                                    <h3>Add/Edit Down Stream Project</h3>
                                </div>
                                <form class="form-default" method="post"
                                      action="/dashboard/project/projectDependencyAdd">
                                    <div class="modal-body">
                                        <input type="hidden" name="pipelineId"
                                               value="${pipelineId}"/>
                                        <input type="hidden" name="projectId"
                                               value="${ projectGrid.getData().get(row).get(col).getId()}"/>
                                        <input type="hidden"
                                               id="selectedDownStreamProjects_${ projectGrid.getData().get(row).get(col).getId()}"
                                               name="selectedDownStreamProjects_${ projectGrid.getData().get(row).get(col).getId()}"
                                               value="${ projectGrid.getData().get(row).get(col).getDownStreamProjects()}"/>

                                        <p>
                                            <span class="span4">Name:</span>
                                            <input type="text" readonly
                                                   value="${projectGrid.getData().get(row).get(col).getName()}"/>
                                        </p>

                                        <p>

                                            <span class="span4">Down Stream Project:</span>

                                        <div class="chzn-container chzn-container-single">
                                            <input type="text"
                                                   id="dependencyGraph_${ projectGrid.getData().get(row).get(col).getId()}"
                                                   name="dependencyGraph_${ projectGrid.getData().get(row).get(col).getId()}"
                                                   onload="mdropdownlist({
                                                           'select_id': 'dependencyList_${ projectGrid.getData().get(row).get(col).getId()}',
                                                           'selected_ids': 'selectedDownStreamProjects_${ projectGrid.getData().get(row).get(col).getId()}',
                                                           'list_container_id': 'dependencyListContainer_${ projectGrid.getData().get(row).get(col).getId()}',
                                                           'onupdate': function () {
                                                           q('#dependencyGraph_${ projectGrid.getData().get(row).get(col).getId()}').value = this.text();
                                                           }
                                                           })"
                                                   onfocus="mdropdownlist({
                                                           'select_id': 'dependencyList_${ projectGrid.getData().get(row).get(col).getId()}',
                                                           'selected_ids': 'selectedDownStreamProjects_${ projectGrid.getData().get(row).get(col).getId()}',
                                                           'list_container_id': 'dependencyListContainer_${ projectGrid.getData().get(row).get(col).getId()}',
                                                           'onupdate': function () {
                                                           q('#dependencyGraph_${ projectGrid.getData().get(row).get(col).getId()}').value = this.text();
                                                           }
                                                           }).show()"
                                                   vaule="${projectUtil.covertProjectDownStreamIdString2NameString(projectGrid.getData().get(row).get(col).getDownStreamProjects(),projectService)}"
                                                    >

                                            <div id="dependencyListContainer_${ projectGrid.getData().get(row).get(col).getId()}"></div>
                                            <select id="dependencyList_${ projectGrid.getData().get(row).get(col).getId()}"
                                                    name="dependencyList_${ projectGrid.getData().get(row).get(col).getId()}"
                                                    multiple="true"
                                                    style="display:none">
                                                <c:forEach var="project" items="${allProject}">
                                                    <option type="checkbox"
                                                            value="${project.getId()}">${project.getName()}</option>
                                                </c:forEach>
                                            </select>
                                        </div>

                                        </p>
                                        <div style="height: 100px;">

                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <a href="#" class="btn" data-dismiss="modal">Close</a>
                                        <input type="submit" class="btn btn-primary" value="Add"/>
                                    </div>
                                </form>
                            </div>
                        </c:if>
                        </c:forEach>
                    </div>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
<!-- end: Content -->

</div><!--/fluid-row-->


<script type="text/javascript" src="../../../assets/js/mdropdownlist.js"></script>

<%@ include file="../common/footer.jsp" %>

