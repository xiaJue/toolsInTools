package com.xj.toolsInTools.constant;

public class Constants {
	public static final String SRC_DIR = "res/";
	/**
	 * regex文件的预定义
	 */
	public static String[] ONE_KEYS = { "REGEX", "RES" };// 预定义解析 一级关键字
	public static String[] TWO_KEYS = { "KEY", "FILE", "ICON", "TITLE" };// 预定义解析 二级关键字
	public static String[] THREE_KEYS = { "%THIS%" };// 预定义解析 通常在使用时进行判断

	/**
	 * info文件的预定义
	 */
	public static String[] INFO_ONE_KEYS = { "KEY", "HR", "NULL" };// 预定义解析  一级关键字

	/**
	 * 一堆html的定义
	 */
	// 整个label条目的样式
	public static final String LABEL_HTML_FRONT = "<html>";
	public static final String LABEL_HTML_END = "</html>";
	// message文字的样式
	public static final String MESSAGE_HTML_FRONT = "<font color='#CC0000'>";
	public static final String MESSAGE_HTML_END = "</font>";
	// hr条目显示的样式
	private static final String HR_HTML_FRONT = "<html><div style=\"width:";
	private static final String HR_HTML_END = "px;height:1px; border:1px solid #CC0066; margin:0 auto; \"></div></html>";

	public static String getHrHtmlString(int widthPx) {
		return HR_HTML_FRONT + widthPx + HR_HTML_END;
	}
}
