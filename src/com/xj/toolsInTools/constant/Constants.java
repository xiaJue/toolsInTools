package com.xj.toolsInTools.constant;

public class Constants {
	public static final String SRC_DIR = "res/";
	/**
	 * regex�ļ���Ԥ����
	 */
	public static String[] ONE_KEYS = { "REGEX", "RES" };// Ԥ������� һ���ؼ���
	public static String[] TWO_KEYS = { "KEY", "FILE", "ICON", "TITLE" };// Ԥ������� �����ؼ���
	public static String[] THREE_KEYS = { "%THIS%" };// Ԥ������� ͨ����ʹ��ʱ�����ж�

	/**
	 * info�ļ���Ԥ����
	 */
	public static String[] INFO_ONE_KEYS = { "KEY", "HR", "NULL" };// Ԥ�������  һ���ؼ���

	/**
	 * һ��html�Ķ���
	 */
	// ����label��Ŀ����ʽ
	public static final String LABEL_HTML_FRONT = "<html>";
	public static final String LABEL_HTML_END = "</html>";
	// message���ֵ���ʽ
	public static final String MESSAGE_HTML_FRONT = "<font color='#CC0000'>";
	public static final String MESSAGE_HTML_END = "</font>";
	// hr��Ŀ��ʾ����ʽ
	private static final String HR_HTML_FRONT = "<html><div style=\"width:";
	private static final String HR_HTML_END = "px;height:1px; border:1px solid #CC0066; margin:0 auto; \"></div></html>";

	public static String getHrHtmlString(int widthPx) {
		return HR_HTML_FRONT + widthPx + HR_HTML_END;
	}
}
