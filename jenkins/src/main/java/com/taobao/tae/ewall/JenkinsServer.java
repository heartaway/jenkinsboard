package com.taobao.tae.ewall;


import com.google.common.base.Function;
import com.google.common.collect.Maps;
import com.taobao.tae.ewall.client.JenkinsHttpClient;
import com.taobao.tae.ewall.model.*;
import org.apache.commons.lang.StringUtils;
import org.apache.http.client.HttpResponseException;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * The main starting point for interacting with a Jenkins server.
 */
public class JenkinsServer {
    private final JenkinsHttpClient client;


    /**
     * Create a new Jenkins server reference given only the server address
     *
     * @param serverUri address of jenkins server (ex. http://localhost:8080/jenkins)
     */
    public JenkinsServer(URI serverUri) {
        this(new JenkinsHttpClient(serverUri));
    }

    /**
     * Create a new Jenkins server reference given the address and credentials
     *
     * @param serverUri       address of jenkins server (ex. http://localhost:8080/jenkins)
     * @param username        username to use when connecting
     * @param passwordOrToken password (not recommended) or token (recommended)
     */
    public JenkinsServer(URI serverUri, String username, String passwordOrToken) {
        this(new JenkinsHttpClient(serverUri, username, passwordOrToken));
    }

    /**
     * Create a new Jenkins server directly from an HTTP client (ADVANCED)
     *
     * @param client Specialized client to use.
     */
    public JenkinsServer(JenkinsHttpClient client) {
        this.client = client;
    }

    /**
     * Get a list of all the defined jobs on the server (at the summary level)
     *
     * @return list of defined jobs (summary level, for details @see Job#details
     * @throws java.io.IOException
     */
    public Map<String, Job> getJobs() throws IOException {
        List<Job> jobs = client.getApi("/", MainView.class).getJobs();
        return Maps.uniqueIndex(jobs, new Function<Job, String>() {
            @Override
            public String apply(Job job) {
                job.setClient(client);
                return job.getName().toLowerCase();
            }
        });
    }

    /**
     * Get a single Job from the server.
     *
     * @return A single Job, null if not present
     * @throws java.io.IOException
     */
    public JobWithDetails getJob(String jobName) throws IOException {
        try {
            JobWithDetails job = client.getApi("/job/" + encode(jobName), JobWithDetails.class);
            job.setClient(client);
            return job;
        } catch (HttpResponseException e) {
            if (e.getStatusCode() == 404) {
                return null;
            }
            throw e;
        }

    }

    /**
     * 获取测试报告
     *
     * @throws IOException
     */
    public TestReport getTestReport(String jobName, Long buildNumber) throws IOException {
        try {
            if (StringUtils.isBlank(jobName) || buildNumber == null) {
                return null;
            }
            TestReport testReport = client.getApi("/job/" + encode(jobName) + "/" + buildNumber.toString() + "/testReport/", TestReport.class);
            return testReport;
        } catch (HttpResponseException e) {
            if (e.getStatusCode() == 404) {
                return null;
            }
            throw e;
        }
    }


    /**
     * 通过JobName 获取 此 job 下游 工程构建触发阀值
     *
     * @param jobName
     * @return
     * @throws IOException
     */
    public TriggerThreshold getChildJobTriggerThreshold(String jobName) throws IOException {
        String jobXml = client.getApi("/job/" + encode(jobName) + "/config.xml");
        if (jobXml == null) {
            return TriggerThreshold.SUCCESS;
        }
        if (jobXml.contains("<hudson.tasks.BuildTrigger>")) {
            jobXml = jobXml.substring(jobXml.indexOf("<hudson.tasks.BuildTrigger>"), jobXml.length());
        }
        if (jobXml.contains("</hudson.tasks.BuildTrigger>")) {
            jobXml = jobXml.substring(0, jobXml.indexOf("</hudson.tasks.BuildTrigger>"));
        }
        if (jobXml.contains("<threshold>")) {
            jobXml = jobXml.substring(jobXml.indexOf("<threshold>"), jobXml.length());
        }
        if (jobXml.contains("</threshold>")) {
            jobXml = jobXml.substring(0, jobXml.indexOf("</threshold>"));
        }
        if (jobXml.contains("2")) {
            return TriggerThreshold.FAILURE;
        } else if (jobXml.contains("1")) {
            return TriggerThreshold.UNSTABLE;
        } else {
            return TriggerThreshold.SUCCESS;
        }
    }

    /**
     * Create a job on the server using the provided xml
     *
     * @return the new job object
     * @throws java.io.IOException
     */
    public void createJob(String jobName, String jobXml) throws IOException {
        client.post_xml("/createItem?name=" + encode(jobName), jobXml);
    }

    /**
     * Get the xml description of an existing job
     *
     * @return the new job object
     * @throws java.io.IOException
     */
    public String getJobXml(String jobName) throws IOException {
        return client.getApi("/job/" + encode(jobName) + "/config.xml");
    }

    /**
     * Get the description of an existing Label
     *
     * @return label object
     * @throws java.io.IOException
     */
    public LabelWithDetails getLabel(String labelName) throws IOException {
        return client.getApi("/label/" + encode(labelName), LabelWithDetails.class);
    }


    /**
     * Get a list of all the computers on the server (at the summary level)
     *
     * @return list of defined computers (summary level, for details @see Computer#details
     * @throws java.io.IOException
     */
    public Map<String, Computer> getComputers() throws IOException {
        List<Computer> computers = client.getApi("computer/", Computer.class).getComputers();
        return Maps.uniqueIndex(computers, new Function<Computer, String>() {
            @Override
            public String apply(Computer computer) {
                computer.setClient(client);
                return computer.getDisplayName().toLowerCase();
            }
        });
    }

    /**
     * Update the xml description of an existing job
     *
     * @return the new job object
     * @throws java.io.IOException
     */
    public void updateJob(String jobName, String jobXml) throws IOException {
        client.post_xml("/job/" + encode(jobName) + "/config.xml", jobXml);
    }

    private String encode(String pathPart) {
        // jenkins doesn't like the + for space, use %20 instead
        return URLEncoder.encode(pathPart).replaceAll("\\+", "%20");
    }

    public String getUrl() {
        return client.getUrl();
    }
}
