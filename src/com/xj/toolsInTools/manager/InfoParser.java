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
	// Ԥ�����������ʽ
	private static String regex = "(?<=>).*?(?=>)";// ƥ�����е�ÿһ��>����>������>

	private static RegexInfo regexInfo;

	public static List<Info> parserInfoFromFile(String filePath,
			WindowInfo windowInfo) throws InfoParserException{
		List<String> strings = FileUtils.readFileStrings(filePath);
		regexInfo = getRegexFromFile(Constants.SRC_DIR + "regex.txt");
		List<Info> list = parserInfos(strings, regexInfo.Regex);
		// ����windowInfo
		windowInfo.setIcon(new ImageIcon(regexInfo.resIcon).getImage());
		// ����λ�úʹ�С���߶�Ϊ��Ŀ����+3 ��Ŀ�߶�Ϊ28����
		int height = 28 * (list.size() + 3);
		windowInfo.setBounds(new Rectangle(500, 200, 500, height < 800 ? height
				: 800));
		windowInfo.setTitle(regexInfo.resTitle);

		return list;
	}

	/**
	 * �������������ļ�
	 * 
	 * @param filePath
	 * @return
	 */
	public static RegexInfo getRegexFromFile(String filePath) {
		List<String> list = FileUtils.readFileStrings(filePath);

		// ����regex
		RegexInfo info = new RegexInfo();

		for (int i = 0; i < list.size(); i++) {
			String lin = list.get(i);
			// ��������ƥ��
			Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
			// ����#�ſ�ͷ
			if (lin.matches("^(?!(\\ *#)).+$")) {
				// ����Ԥ�ü�
				lin = TransferenceUtils.trim(lin);
				// System.out.println(lin);
				Matcher matcher = pattern.matcher(lin);

				while (matcher.find()) { // >��һ��> // ����� >REGEX>
					try {

						if (matcher.group().trim()
								.equals(Constants.ONE_KEYS[0])) { // �ڶ���>REGEX>....>
							info.Regex = matcher.find() ? matcher.group()
									: regex;
							matcher.find();
						}

						// ��Դ >RES>
						if (matcher.group().trim()
								.equals(Constants.ONE_KEYS[1])) {
							matcher.find();
							if (matcher.group().equals(Constants.TWO_KEYS[0])) {
								// System.out.println("equals...key");
								// �ڶ��� >RES>KEY>
								info.resKey = matcher.find() ? matcher.group()
										: info.resKey;
							} else if (matcher.group().equals(
									Constants.TWO_KEYS[1])) {
								// �ڶ���>RES>FILE>
								info.resFile = matcher.find() ? matcher.group()
										: info.resFile;
							} else if (matcher.group().equals(
									Constants.TWO_KEYS[2])) {
								// �ڶ���>RES>ICON>
								info.resIcon = matcher.find() ? matcher.group()
										: info.resIcon;
							} else if (matcher.group().equals(
									Constants.TWO_KEYS[3])) {
								// �ڶ���>RES>TITLE>
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

		// ��ԭ�ַ���
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
			// ^(?!(\\ *#)).+$��ʾ��ͷ�����пո��ǿո��������#
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
			// ��ĿĬ��ΪFILE����
			// info.type = "FILE";
			// ����KEY����
			for (int i = 0; i < Constants.INFO_ONE_KEYS.length; i++) {
				if (matcher.group().equals(Constants.INFO_ONE_KEYS[i])) {
					info.type = Constants.INFO_ONE_KEYS[i];
				}
			}
			// ������Ŀ��ʾ��ͼ��
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
