package com.taobao.tae.ewall.project;

import com.taobao.tae.ewall.build.BuildGrid;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * User: xinyuan.ymm
 * Date: 13-12-27
 * Time: 上午12:01
 */

/**
 * {@link com.taobao.tae.ewall.project.ProjectGrid} that lays out things via upstream/downstream.
 */

public class ProjectGridImpl extends DefaultProjectGridImpl {

    /**
     * Project at the top-left corner. Initiator of the pipeline.
     */
    private ProjectDO start;

    private List<ProjectDO> allProjects;


    /**
     * @param start The first project to lead the pipeline.
     */
    public ProjectGridImpl(ProjectDO start, List<ProjectDO> allProjects) {
        this.start = start;
        this.allProjects = allProjects;
        placeProjectInGrid(0, 0, start);
    }

    /**
     * Function called recursively to place a project form in a grid
     *
     * @param startingRow    project will be placed in the starting row and 1st child as well. Each subsequent
     *                       child will be placed in a row below the previous.
     * @param startingColumn project will be placed in starting column. All children will be placed in next column.
     * @param projectDO      project to be placed
     */
    private void placeProjectInGrid(int startingRow, int startingColumn, ProjectDO projectDO) {
        if (projectDO == null) {
            return;
        }

        int row = getNextAvailableRow(startingRow, startingColumn);
        set(row, startingColumn, projectDO);

        final int childrensColumn = startingColumn + 1;
        for (final ProjectDO downstreamProject : getDownstreamProjects(projectDO)) {
            placeProjectInGrid(row, childrensColumn, downstreamProject);
            row++;
        }
    }

    @Override
    public ProjectDO get(int row, int col) {
        final Map<Integer, ProjectDO> cols = data.get(row);
        if (cols == null) {
            return null;
        }
        return cols.get(col);
    }

    /**
     * 根据 name 获取 ProjectDO对象
     *
     * @param name
     * @return
     */
    private ProjectDO getProjectByName(String name) {
        for (ProjectDO project : allProjects) {
            if (String.valueOf(project.getName()).equals(name)) {
                return project;
            }
        }
        return null;
    }


    /**
     * Given a Project get a List of all of its Downstream projects name
     *
     * @param currentProject Current project
     * @return List of Downstream projects
     */
    public List<ProjectDO> getDownstreamProjects(final ProjectDO currentProject) {
        String downStreamProjectsString = currentProject.getDownStreamProjects();
        if (StringUtils.isBlank(downStreamProjectsString)) {
            return new ArrayList<ProjectDO>();
        }
        if (downStreamProjectsString.endsWith(","))
            downStreamProjectsString = downStreamProjectsString.substring(0, downStreamProjectsString.length() - 1);
        if (downStreamProjectsString.startsWith(","))
            downStreamProjectsString = downStreamProjectsString.substring(1, downStreamProjectsString.length());
        String[] projectDONames = downStreamProjectsString.split(",");

        final List<ProjectDO> downstreamProjectsList = new ArrayList<ProjectDO>();
        for (int i = 0; i < projectDONames.length; i++) {
            downstreamProjectsList.add(getProjectByName(projectDONames[i]));
        }
        return downstreamProjectsList;
    }

    @Override
    public Iterable<BuildGrid> builds() {
        return null;
    }


}
