package com.xj.toolsInTools.bean;

import com.xj.toolsInTools.constant.Constants;

public class Info {

	private String name;// (文件)名称
	private String message;// 信息
	private String showName="";// 真实显示的name。如果为空默认显示name
	public String type="FILE";
	private String iconPath = Constants.SRC_DIR+"file.png";//条目显示的图标。条目默认类型为type

	public Info(String name, String message) {
		super();
		this.name = name;
		this.message = message;
	}
	
	

	public Info(String name, String message, String showName, String iconPath) {
		super();
		this.name = name;
		this.message = message;
		this.showName = showName;
		this.iconPath = iconPath;
	}

	public Info(String name, String message, String showName) {
		super();
		this.name = name;
		this.message = message;
		this.showName = showName;
	}


	public Info() {
	}

	/**
	 * 如果showName不为空，说明name不需要被外部访问。反之亦然
	 * 
	 * @return
	 */
	public String getName() {
		return showName.isEmpty() ? name : showName;
	}
	
	public String getAbsName(){
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setShowName(String showName) {
		this.showName = showName;
	}

	public String getShowName() {
		return showName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getIconPath() {
		return iconPath;
	}

	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}

}
