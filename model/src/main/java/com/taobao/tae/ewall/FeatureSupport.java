package com.taobao.tae.ewall;

import com.taobao.tae.common.util.JsonUtil;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * User: xinyuan.ymm
 * Date: 13-11-29
 * Time: 下午5:32
 * features字段的统一操作，
 * map 被json化存储
 */
public class FeatureSupport implements Serializable {

    private static final long serialVersionUID = -4700827684696991151L;

    protected Map<String, String> featureMap = null;

    /**
     * 初始化容器
     */
    private void initFeatureMap() {
        this.featureMap = new HashMap<String, String>(10);
    }

    public Map<String, String> getFeatureMap() {
        return featureMap;
    }

    public Map<String,String> getFeaturesAsMap(){
        return  getFeatureMap();
    }

    public void setFeatureMap(Map<String, String> featureMap) {
        this.featureMap = featureMap;
    }

    public void addFeature(String key, String value) {
        if (featureMap == null) {
            initFeatureMap();
        }
        featureMap.put(key, value);
    }

    public void addFeature(Map<String, String> add) {
        if (featureMap == null) {
            initFeatureMap();
        }
        featureMap.putAll(add);
    }

    public String getFeature(String key){
        if(featureMap == null){
            return null;
        }
        return featureMap.get(key);
    }

    public void removeFeature(String key) {
        if (featureMap != null) {
            featureMap.remove(key);
        }
    }

    //清空map,反应到数据库中为清空features的内容
    public void removeAll(){
        initFeatureMap();
    }

    public String getFeatures() {
        //如果featureMap为null，则表示不修改features字段，所以需要返回null
        if(featureMap==null){
            return null;
        }
        return JsonUtil.toJson(featureMap);
    }

    public void setFeatures(String features) {
        //容错一下，null传到JsonUtil.parseJson会抛nullPointer异常
        if (features == null) {
            return;
        }
        try {
            featureMap = (Map<String, String>) JsonUtil.parseJson(features, Map.class);
        } catch (IOException e) {
            ;
        }
    }
}
