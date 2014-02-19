package com.taobao.tae.ewall.dashboard.polling;

import com.taobao.tae.ewall.JenkinsServer;
import com.taobao.tae.ewall.client.JenkinsHttpClient;
import com.taobao.tae.ewall.model.BuildResult;
import com.taobao.tae.ewall.model.BuildStatus;
import com.taobao.tae.ewall.model.BuildWithDetails;
import com.taobao.tae.ewall.service.BuildProjectService;
import org.apache.http.client.HttpResponseException;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.Callable;

/**
 * User: xinyuan.ymm
 * Date: 14-2-17
 * Time: 下午8:20
 */
public class ProjectPerformPolling implements Callable<BuildWithDetails> {

    private String jenkinsUrl;

    private String jobName;

    private Long buildNumber;

    private JenkinsServer jenkinsServer;

    public ProjectPerformPolling(String jenkinsUrl, String jobName, Long buildNumber) {
        this.jenkinsUrl = jenkinsUrl;
        this.jobName = jobName;
        this.buildNumber = buildNumber;
        try {
            JenkinsHttpClient client = new JenkinsHttpClient(new URI(this.jenkinsUrl));
            this.jenkinsServer = new JenkinsServer(client);
        } catch (URISyntaxException exception) {
            exception.printStackTrace();
        }
    }

    public BuildWithDetails call() throws InterruptedException, IOException {
        while (true) {
            Thread.sleep(2000);
            try {
                BuildWithDetails buildWithDetails = jenkinsServer.getJob(jobName).getBuildByBuildNumber(this.buildNumber, jenkinsUrl).details();
                if (!buildWithDetails.isBuilding()) {
                    return buildWithDetails;
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }
}
