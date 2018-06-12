package com.xj.toolsInTools.manager;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;

import com.xj.toolsInTools.bean.Info;
import com.xj.toolsInTools.bean.RegexInfo;
import com.xj.toolsInTools.bean.WindowInfo;
import com.xj.toolsInTools.constant.Constants;
import com.xj.toolsInTools.utils.FileUtils;

public class InfoParser {
	// 预定义的正则表达式
	private static String regex = "(?<=>).*?(?=>)";// 匹配行中的每一个>包裹>的内容>

	private static RegexInfo regexInfo;

	public static List<Info> parserInfoFromFile(String filePath,
			WindowInfo windowInfo) throws InfoParserException{
		List<String> strings = FileUtils.readFileStrings(filePath);
		regexInfo = getRegexFromFile(Constants.SRC_DIR + "regex.txt");
		List<Info> list = parserInfos(strings, regexInfo.Regex);
		// 设置windowInfo
		windowInfo.setIcon(new ImageIcon(regexInfo.resIcon).getImage());
		// 设置位置和大小。高度为条目个数+3 条目高度为28像素
		int height = 28 * (list.size() + 3);
		windowInfo.setBounds(new Rectangle(500, 200, 500, height < 800 ? height
				: 800));
		windowInfo.setTitle(regexInfo.resTitle);

		return list;
	}

	/**
	 * 解析程序配置文件
	 * 
	 * @param filePath
	 * @return
	 */
	public static RegexInfo getRegexFromFile(String filePath) {
		List<String> list = FileUtils.readFileStrings(filePath);

		// 构造regex
		RegexInfo info = new RegexInfo();

		for (int i = 0; i < list.size(); i++) {
			String lin = list.get(i);
			// 进行正则匹配
			Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
			// 不以#号开头
			if (lin.matches("^(?!(\\ *#)).+$")) {
				// 进行预裁剪
				lin = TransferenceUtils.trim(lin);
				// System.out.println(lin);
				Matcher matcher = pattern.matcher(lin);

				while (matcher.find()) { // >第一级> // 正则句 >REGEX>
					try {

						if (matcher.group().trim()
								.equals(Constants.ONE_KEYS[0])) { // 第二级>REGEX>....>
							info.Regex = matcher.find() ? matcher.group()
									: regex;
							matcher.find();
						}

						// 资源 >RES>
						if (matcher.group().trim()
								.equals(Constants.ONE_KEYS[1])) {
							matcher.find();
							if (matcher.group().equals(Constants.TWO_KEYS[0])) {
								// System.out.println("equals...key");
								// 第二级 >RES>KEY>
								info.resKey = matcher.find() ? matcher.group()
										: info.resKey;
							} else if (matcher.group().equals(
									Constants.TWO_KEYS[1])) {
								// 第二级>RES>FILE>
								info.resFile = matcher.find() ? matcher.group()
										: info.resFile;
							} else if (matcher.group().equals(
									Constants.TWO_KEYS[2])) {
								// 第二级>RES>ICON>
								info.resIcon = matcher.find() ? matcher.group()
										: info.resIcon;
							} else if (matcher.group().equals(
									Constants.TWO_KEYS[3])) {
								// 第二级>RES>TITLE>
								info.resTitle = matcher.find() ? matcher
										.group() : info.resTitle;
							}

						}
					} catch (Exception e) {
						throw new InfoParserException(i+1, "regex.txt",
								e.toString());
					}
				}
			}
		}
		keywordTransference(info);

		// 还原字符串
		TransferenceUtils.consultRegexInfo(info);
		return info;
	}

	private static void keywordTransference(RegexInfo info) {
		if (info.resTitle.equals(Constants.THREE_KEYS[0])) {
			info.resTitle = FileUtils.getThisDirName();
		}
	}

	public static List<Info> parserInfos(List<String> infoStrings, String regex) {
		List<Info> infos = new ArrayList<Info>();

		for (int i = 0; i < infoStrings.size(); i++) {
			String linString = infoStrings.get(i);
			// System.out.println(linString.trim()+">>>>>>");
			// System.out.println(linString.matches("^(?!(\\ *#)).+$"));
			// ^(?!(\\ *#)).+$表示开头可以有空格但是空格过后不能是#
			if (linString.matches("^(?!(\\ *#)).+$") && !linString.isEmpty()) {
				try {
					infos.add(parserInfo(linString.trim(), regex));
				} catch (Exception e) {
					throw new InfoParserException(i+1, "info.txt", e.toString());
				}
			}
		}
		return infos;
	}

	private static Info parserInfo(String linString, String regexs) {
		Matcher matcher = Pattern.compile(regexs).matcher(linString);

		if (matcher.find()) {
			Info info = new Info();
			// 条目默认为FILE类型
			// info.type = "FILE";
			// 处理KEY类型
			for (int i = 0; i < Constants.INFO_ONE_KEYS.length; i++) {
				if (matcher.group().equals(Constants.INFO_ONE_KEYS[i])) {
					info.type = Constants.INFO_ONE_KEYS[i];
				}
			}
			// 设置条目显示的图标
			info.setIconPath(regexInfo.getResPath(info.type));
			// set name
			if (info.type == Constants.INFO_ONE_KEYS[1]) {
				info.setName("");
			} else if (info.type == Constants.INFO_ONE_KEYS[2]) {
				info.setName(" ");
			} else {
				info.setName(matcher.group());
			}
			// System.out.println(matcher.group());
			// set show name
			matcher.find();
			info.setShowName(matcher.group());
			// System.out.println(matcher.group());
			// set message
			matcher.find();
			info.setMessage(matcher.group());
			// System.out.println(matcher.group());
			// System.out.println(">>>>>>>>>>>>>>>\n");
			return info;
		}
		return null;
	}
}
