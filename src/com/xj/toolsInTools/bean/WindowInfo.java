package com.xj.toolsInTools.bean;

import java.awt.Image;
import java.awt.Rectangle;

public class WindowInfo {
	//窗口标题
	private String title;
	//窗口位置大小信息
	private Rectangle bounds;
	//图标
	private Image icon;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Rectangle getBounds() {
		return bounds;
	}
	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}
	public Image getIcon() {
		return icon;
	}
	public void setIcon(Image icon) {
		this.icon = icon;
	}
}
