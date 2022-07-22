package com.ruoyi.system.domain.KgBuilderPojo.request;

import java.util.Map;


public class EdgeItem {

    //边Id
    private String id;

    //起始点
    private String source;

    //结束点
    private String target;

    //所属领域
    private String domain;

    //边类型
    private String type;

    //显示文字
    private String label;

    //关系类别
    private String cluster;

    //样式属性
    private Map<String,Object> style;

    //文本配置属性
    private Map<String,Object> labelCfg;

    //状态样式配置
    private Map<String,Object> stateStyles;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getCluster() {
        return cluster;
    }

    public void setCluster(String cluster) {
        this.cluster = cluster;
    }

    public Map<String, Object> getStyle() {
        return style;
    }

    public void setStyle(Map<String, Object> style) {
        this.style = style;
    }

    public Map<String, Object> getLabelCfg() {
        return labelCfg;
    }

    public void setLabelCfg(Map<String, Object> labelCfg) {
        this.labelCfg = labelCfg;
    }

    public Map<String, Object> getStateStyles() {
        return stateStyles;
    }

    public void setStateStyles(Map<String, Object> stateStyles) {
        this.stateStyles = stateStyles;
    }

    public EdgeItem() {
    }

    public EdgeItem(String id, String source, String target, String domain, String type, String label, String cluster, Map<String, Object> style, Map<String, Object> labelCfg, Map<String, Object> stateStyles) {
        this.id = id;
        this.source = source;
        this.target = target;
        this.domain = domain;
        this.type = type;
        this.label = label;
        this.cluster = cluster;
        this.style = style;
        this.labelCfg = labelCfg;
        this.stateStyles = stateStyles;
    }

}
