package com.taobao.tae.ewall.model;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;

/**
 * All job info view.
 */
public class MainView extends BaseModel {
    private List<Job> jobs;

    /* default constructor needed for Jackson */
    public MainView() {
        this(Lists.<Job>newArrayList());
    }

    public MainView(List<Job> jobs) {
        this.jobs = jobs;
    }

    public MainView(Job... jobs) {
        this(Arrays.asList(jobs));
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }
}
