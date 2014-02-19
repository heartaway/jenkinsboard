package com.taobao.tae.ewall.project;

import com.taobao.tae.ewall.build.BuildGrid;
import com.taobao.tae.ewall.pipeline.Grid;

/**
 * Two-dimensional placement of {@link com.taobao.tae.ewall.project.ProjectForm}s into a grid/matrix layout.
 *
 * This class is also responsible for producing a sequence of {@link com.taobao.tae.ewall.build.BuildGrid}s
 * that are the instances of the pipelines.
 *
 * @author Kohsuke Kawaguchi
 */
public abstract class ProjectGrid extends Grid<ProjectDO> {
    /**
     * Iterates instances of the pipeline grid view from this project layout.
     *
     * The caller is only going to iterate {@link com.taobao.tae.ewall.build.BuildGrid}s up to a certain number
     * that the user has configured.
     *
     *
     * @return never null.
     */
    public abstract Iterable<BuildGrid> builds();
}
