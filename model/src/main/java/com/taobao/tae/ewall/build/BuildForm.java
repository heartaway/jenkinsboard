package com.taobao.tae.ewall.build;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Centrum Systems
 * 
 *         Representation of a build results pipeline
 * 
 */
public class BuildForm {
    /**
     * logger
     */
    private static final Logger LOGGER = Logger.getLogger(BuildForm.class.getName());

    /**
     * status
     */
    private String status = "";

    /**
     * pipeline build
     */
    private PipelineBuild pipelineBuild;

    /**
     * id
     */
    private final Integer id;

    /**
     * downstream builds
     */
    private List<BuildForm> dependencies = new ArrayList<BuildForm>();

    /**
     * @param pipelineBuild
     *            pipeline build domain used to see the form
     */
    public BuildForm(final PipelineBuild pipelineBuild) {
        this.pipelineBuild = pipelineBuild;
        dependencies = new ArrayList<BuildForm>();
        id = hashCode();
    }

    public String getStatus() {
        return status;
    }

    public List<BuildForm> getDependencies() {
        return dependencies;
    }

    /**
     * @return All ids for existing depencies.
     */
    public List<Integer> getDependencyIds() {
        final List<Integer> ids = new ArrayList<Integer>();
        for (final BuildForm dependency : dependencies) {
            ids.add(dependency.getId());
        }
        return ids;
    }


    public int getId() {
        return id;
    }

}
