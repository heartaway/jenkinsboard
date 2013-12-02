/*
 * Copyright (c) 2013 Rising Oak LLC.
 *
 * Distributed under the MIT license: http://opensource.org/licenses/MIT
 */

package com.taobao.tae.ewall;

import com.google.common.collect.ImmutableMap;
import com.taobao.tae.ewall.client.JenkinsHttpClient;
import com.taobao.tae.ewall.model.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class JenkinsServerIntegration {

    private JenkinsHttpClient client;
    private JenkinsServer server;
    private ExecutorService executor;

    @Before
    public void setUp() throws Exception {
        client = new JenkinsHttpClient(new URI("http://10.20.153.116:8080"));
        server = new JenkinsServer(client);
        executor = Executors.newCachedThreadPool();
    }

    @Test
    public void shouldReturnListOfJobs() throws Exception {
        Map<String, Job> jobs = server.getJobs();
    }

    @Test
    public void shouldReturnBuildsForJob() throws Exception {
        JobWithDetails job = server.getJobs().get("deploycenter").details();
        System.out.println(job);
    }


    @Test
    public void shouldReturnListOfComputers() throws Exception {
        assertTrue(server.getComputers().containsKey("master"));
    }

    @Test
    public void shouldReturnDetailOfComputer() throws Exception {
        Map<String, Computer> computers =  server.getComputers();
        assertTrue(computers.get("master").details().getDisplayName().equals("master"));
    }

    @Test
    public void shouldReturnDetailOfLablel() throws Exception {
        assertTrue(server.getLabel("tae-138").getName().equals("tae"));
    }

    // Note this test depends upon the xml in job-template.xml being a valid job
    // description for the instance of jenkins you are running.
    @Test
    public void testGetJobXml() throws Exception {
        final String jobName = "deploycenter";

        String xmlReturned = server.getJobXml(jobName);

        assertTrue(xmlReturned.length() > 0);
    }

    @Test
    public void testGetJobByName() throws Exception {
        final String jobName = "trunk";

        JobWithDetails job = server.getJob(jobName);

        assertEquals("trunk",job.getName());
        assertEquals("trunk",job.getDisplayName());
    }

    @Test
    public void testGetJobByNameDoesntExist() throws Exception {
        final String jobName = "imprettysurethereisnojobwiththisname";

        JobWithDetails job = server.getJob(jobName);

        assertEquals(null, job);
    }

    // Note this test depends upon the "pr" job existing and successfully building
    @Test
    public void testCreateJob() throws Exception {
        final String sourceJob = "pr";
        final String jobName = "test-job-" + UUID.randomUUID().toString();

        String sourceXml = server.getJobXml(sourceJob);

        server.createJob(jobName, sourceXml);

        Map<String, Job> jobs = server.getJobs();
        assertTrue(jobs.containsKey(jobName));
        JobWithDetails thisJob = jobs.get(jobName).details();
        assertNotNull(thisJob);
        assertTrue(thisJob.getBuilds().size() == 0);
        thisJob.build(ImmutableMap.of("foo_param", "MUST_PROVIDE_VALUES_DEFAULTS_DONT_WORK"));

        // wait to see if the job finishes, but with a timeout
        Future<Void> future = executor.submit(new PerformPollingTest(server, jobName));

        // If this times out, either jenkins is slow or our test failed!
        // IME, usually takes about 10-15 seconds
        future.get(30, TimeUnit.SECONDS);

        Build b = server.getJobs().get(jobName).details().getLastSuccessfulBuild();
        assertTrue(b != null);
    }

    // Note this test depends upon the "pr" job existing and successfully building
    @Test
    public void testUpdateJob() throws Exception {
        final String sourceJob = "pr";
        final String description = "test-" + UUID.randomUUID().toString();

        String sourceXml = server.getJobXml(sourceJob);
        String newXml = sourceXml.replaceAll("<description>.*</description>", "<description>" + description + "</description>");
        server.updateJob(sourceJob, newXml);

        String confirmXml = server.getJobXml(sourceJob);
        assertTrue(confirmXml.contains(description));

    }

    private class PerformPollingTest implements Callable<Void> {
        private final JenkinsServer server;
        private final String jobName;
        public PerformPollingTest(JenkinsServer server, String jobName) {
            this.server = server;
            this.jobName = jobName;
        }
        public Void call() throws InterruptedException, IOException {
            while(true) {
                Thread.sleep(500);
                JobWithDetails jwd = server.getJobs().get(jobName).details();

                try {
                    // Throws NPE until the first build succeeds
                    jwd.getLastSuccessfulBuild();
                } catch (NullPointerException e) {
                    continue;
                }
                // build succeeded
                return null;
            }
        }
    }
}
