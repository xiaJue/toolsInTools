package com.xj.toolsInTools.bean;

import com.xj.toolsInTools.constant.Constants;

public class Info {

	private String name;// (�ļ�)����
	private String message;// ��Ϣ
	private String showName="";// ��ʵ��ʾ��name�����Ϊ��Ĭ����ʾname
	public String type="FILE";
	private String iconPath = Constants.SRC_DIR+"file.png";//��Ŀ��ʾ��ͼ�ꡣ��ĿĬ������Ϊtype

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
	 * ���showName��Ϊ�գ�˵��name����Ҫ���ⲿ���ʡ���֮��Ȼ
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
