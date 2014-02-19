package com.taobao.tae.ewall.project;

import com.taobao.tae.ewall.build.BuildGrid;
import com.taobao.tae.ewall.build.BuildProjectDO;
import com.taobao.tae.ewall.build.DefaultBuildGridImpl;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * User: xinyuan.ymm
 * Date: 13-12-27
 * Time: 上午12:01
 */

/**
 * {@link ProjectGrid} that lays out things via upstream/downstream.
 */

public class BuildProjectGridImpl extends DefaultBuildGridImpl {

    /**
     * Project at the top-left corner. Initiator of the pipeline.
     */
    private BuildProjectDO start;

    private List<BuildProjectDO> buildProjectDOs;


    /**
     * @param start The first project to lead the pipeline.
     */
    public BuildProjectGridImpl(BuildProjectDO start, List<BuildProjectDO> buildProjectDOs) {
        this.start = start;
        this.buildProjectDOs = buildProjectDOs;
        placeProjectInGrid(0, 0, start);
    }

    /**
     * Function called recursively to place a project form in a grid
     *
     * @param startingRow    project will be placed in the starting row and 1st child as well. Each subsequent
     *                       child will be placed in a row below the previous.
     * @param startingColumn project will be placed in starting column. All children will be placed in next column.
     * @param BuildProjectDO      project to be placed
     */
    private void placeProjectInGrid(int startingRow, int startingColumn, BuildProjectDO BuildProjectDO) {
        if (BuildProjectDO == null) {
            return;
        }

        int row = getNextAvailableRow(startingRow, startingColumn);
        set(row, startingColumn, BuildProjectDO);

        final int childrensColumn = startingColumn + 1;
        for (final BuildProjectDO downstreamProject : getDownstreamProjects(BuildProjectDO)) {
            placeProjectInGrid(row, childrensColumn, downstreamProject);
            row++;
        }
    }

    @Override
    public BuildProjectDO get(int row, int col) {
        final Map<Integer, BuildProjectDO> cols = data.get(row);
        if (cols == null) {
            return null;
        }
        return cols.get(col);
    }

    /**
     * 根据 name 获取 BuildProjectDO对象
     *
     * @param name
     * @return
     */
    private BuildProjectDO getProjectByName(String name) {
        for (BuildProjectDO project : buildProjectDOs) {
            if (String.valueOf(project.getProjectName()).equals(name)) {
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
    public List<BuildProjectDO> getDownstreamProjects(final BuildProjectDO currentProject) {
        String downStreamProjectsString = currentProject.getDownStreamProjects();
        if (StringUtils.isBlank(downStreamProjectsString)) {
            return new ArrayList<BuildProjectDO>();
        }
        if (downStreamProjectsString.endsWith(","))
            downStreamProjectsString = downStreamProjectsString.substring(0, downStreamProjectsString.length() - 1);
        if (downStreamProjectsString.startsWith(","))
            downStreamProjectsString = downStreamProjectsString.substring(1, downStreamProjectsString.length());
        String[] BuildProjectDONames = downStreamProjectsString.split(",");

        final List<BuildProjectDO> downstreamProjectsList = new ArrayList<BuildProjectDO>();
        for (int i = 0; i < BuildProjectDONames.length; i++) {
            downstreamProjectsList.add(getProjectByName(BuildProjectDONames[i]));
        }
        return downstreamProjectsList;
    }

}
