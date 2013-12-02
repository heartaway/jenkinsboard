package com.taobao.tae.ewall.model;

import com.google.common.base.Function;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static java.net.URLEncoder.encode;

/**
 * 所有节点 管理
 */
public class Computer extends BaseModel {
    private String displayName;

    public List<Computer> getComputers() {
        return computer;
    }

    public void setComputer(List<Computer> computer) {
        this.computer = computer;
    }

    List<Computer> computer;

    public Computer() {
    }

    public Computer(String displayName) {
        this();
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public ComputerWithDetails details() throws IOException {
        return client.get("/computer/" + displayName.replaceAll("master", "(master)"), ComputerWithDetails.class);
    }

    private static class MapEntryToQueryStringPair implements Function<Map.Entry<String, String>, String> {
        @Override
        public String apply(Map.Entry<String, String> entry) {
            return encode(entry.getKey()) + "=" + encode(entry.getValue());
        }
    }
}
