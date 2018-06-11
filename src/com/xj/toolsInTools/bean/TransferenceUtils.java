package com.xj.toolsInTools.bean;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TransferenceUtils {
	public static String[] Transferences = { "\\>", "{CompilerArrow}",
			"\\%THIS%", "{CompilerThis}" };// 保留符号转义的替换符

	/**
	 * 将转义字符替换
	 */
	public static String trim(String lin) {
		String string = lin;
		for (int i = 0; i <= Transferences.length / 2; i++) {
			// 需要转义的字符通常用\进行转义。但是在正则中\本身也需要\转义
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
	 * 还原因为转义被替换掉的字符串
	 */
	public static String reduction(String str) {
		String string = null;
		for (int i = 0; i <= Transferences.length / 2; i++) {
			if (i % 2 == 0) {
				if (str.contains(Transferences[i + 1])) {
					string = str.replace(
							Transferences[i + 1],
							Transferences[i].substring(1,
									Transferences[i].length()));//字符串进行替换。去掉转义字符中的\
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
