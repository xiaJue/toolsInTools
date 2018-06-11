package com.xj.toolsInTools.bean;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TransferenceUtils {
	public static String[] Transferences = { "\\>", "{CompilerArrow}",
			"\\%THIS%", "{CompilerThis}" };// ��������ת����滻��

	/**
	 * ��ת���ַ��滻
	 */
	public static String trim(String lin) {
		String string = lin;
		for (int i = 0; i <= Transferences.length / 2; i++) {
			// ��Ҫת����ַ�ͨ����\����ת�塣������������\����Ҳ��Ҫ\ת��
			if (i % 2 == 0) {
				String regex = ".*\\" + Transferences[i] + ".*";
				Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
				Matcher matcher = pattern.matcher(lin);
				if (matcher.matches()) {
					string = string.replace(Transferences[i],
							Transferences[i + 1]);
				}
			}
		}
		return string;
	}

	/**
	 * ��ԭ��Ϊת�屻�滻�����ַ���
	 */
	public static String reduction(String str) {
		String string = null;
		for (int i = 0; i <= Transferences.length / 2; i++) {
			if (i % 2 == 0) {
				if (str.contains(Transferences[i + 1])) {
					string = str.replace(
							Transferences[i + 1],
							Transferences[i].substring(1,
									Transferences[i].length()));//�ַ��������滻��ȥ��ת���ַ��е�\
				}
			}
		}
		return string == null ? str : string;
	}

	/**
	 * 
	 */
	public static void consultRegexInfo(RegexInfo info) {
		info.Regex = reduction(info.Regex);
		info.resKey = reduction(info.resKey);
		info.resFile = reduction(info.resFile);
		info.resTitle = reduction(info.resTitle);
	}
}
