package com.ruoyi.system.domain.KgBuilderPojo.model;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class NodeItem{

	private String id;//节点唯一Id
	private Number x;//x坐标
	private Number y;//y坐标
	private String type;//制定节点类型
	private Number[] size;//节点大小
	private String label;//文本文字
	private String cluster;//节点类别
	private String domain;//所属领域
	private Map<String,Object> style;//节点样式属性
	private Map<String,Object> labelCfg;//文本配置项
	private Map<String,Object> stateStyles;//状态样式

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Number getX() {
		return x;
	}

	public void setX(Number x) {
		this.x = x;
	}

	public Number getY() {
		return y;
	}

	public void setY(Number y) {
		this.y = y;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Number[] getSize() {
		return size;
	}

	public void setSize(Number[] size) {
		this.size = size;
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


	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
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


	public NodeItem(){

	}

	public NodeItem(String id, String label, Map<String, Object> style) {
		this.id = id;
		this.label = label;
		this.style = style;
	}

	public NodeItem(String id, Number[] size, Map<String, Object> style, Map<String, Object> labelCfg) {
		this.id = id;
		this.size = size;
		this.style = style;
		this.labelCfg = labelCfg;
	}

	public NodeItem(String id, Number[] size, String domain, Map<String, Object> style, Map<String, Object> labelCfg) {
		this.id = id;
		this.size = size;
		this.domain = domain;
		this.style = style;
		this.labelCfg = labelCfg;
	}

	public NodeItem(String id, Number x, Number y, String type, Number[] size, String label, String cluster, String domain, Map<String, Object> style, Map<String, Object> labelCfg, Map<String, Object> stateStyles) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.type = type;
		this.size = size;
		this.label = label;
		this.cluster = cluster;
		this.domain = domain;
		this.style = style;
		this.labelCfg = labelCfg;
		this.stateStyles = stateStyles;
	}

	@Override
	public String toString() {
		return
				"id='" + id + '\'' +
				", x=" + x +
				", y=" + y +
				", type='" + type + '\'' +
				", size=" + Arrays.toString(size) +
				", label='" + label + '\'' +
				", domain='" + domain + '\'' +
				", style=" + style +
				", labelCfg=" + labelCfg +
				", stateStyles=" + stateStyles ;
	}
}
