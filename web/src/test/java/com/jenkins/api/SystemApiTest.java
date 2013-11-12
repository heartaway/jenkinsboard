package com.jenkins.api;

/**
 * User: xinyuan.ymm
 * Date: 13-11-12
 * Time: 下午5:26
 * 需要 admin 权限
 */
public class SystemApiTest {

    String homeUrl = "http://10.20.153.116:8080/";


    public void shutdown() {
        String url = homeUrl.concat("quietDown");
    }

    public void cancelShutDown() {
        String url = homeUrl.concat("cancelQuietDown");
    }

    public void restart() {
        String url = homeUrl.concat("restart");
    }

    public void safeRestart() {
        String url = homeUrl.concat("safeRestart");
    }

}
