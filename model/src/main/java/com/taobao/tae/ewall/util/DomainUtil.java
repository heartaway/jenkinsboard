package com.taobao.tae.ewall.util;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: xinyuan.ymm
 * Date: 14-2-13
 * Time: 上午10:48
 */
public class DomainUtil {

    /**
     * 根据URL获取domain * @param url * @return
     */
    public static String getDomainForUrl(String url) {
        String domainUrl = null;
        if (url == null) {
            return null;
        } else {
            if (url.startsWith("http://")) {
                domainUrl = url.substring(7, url.length());
                domainUrl = domainUrl.split("/")[0];
                domainUrl = "http://".concat(domainUrl);
            } else if (url.startsWith("https://")) {
                domainUrl = url.substring(8, url.length());
                domainUrl = domainUrl.split("/")[0];
                domainUrl = "https://".concat(domainUrl);
            } else {
                domainUrl = domainUrl.split("/")[0];
                domainUrl = "http://".concat(domainUrl);
            }
            return domainUrl;
        }
    }

}
