package com.taobao.tae.ewall.model;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;

import java.io.IOException;
import java.util.Map;

import static java.net.URLEncoder.encode;
import static org.apache.commons.lang.StringUtils.join;

public class Job extends BaseModel {
    private String name;
    private String url;

    public Job() {
    }

    public Job(String name, String url) {
        this();
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public JobWithDetails details() throws IOException {
        return client.getApi(url, JobWithDetails.class);
    }

    /**
     * Trigger a build without parameters
     */
    public void build() throws IOException {
        client.get(url + "build");
    }

    /**
     * Trigger a parameterized build
     *
     * @param params the job parameters
     * @throws java.io.IOException
     */
    public void build(Map<String, String> params) throws IOException {
        String qs = join(Collections2.transform(params.entrySet(), new MapEntryToQueryStringPair()), "&");
        client.post(url + "buildWithParameters?" + qs, null, null);
    }

    private static class MapEntryToQueryStringPair implements Function<Map.Entry<String, String>, String> {
        @Override
        public String apply(Map.Entry<String, String> entry) {
            return encode(entry.getKey()) + "=" + encode(entry.getValue());
        }
    }
}
