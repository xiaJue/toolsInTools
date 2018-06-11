package com.xj.toolsInTools.utils;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.xj.toolsInTools.manager.InfoParserException;

public class FileUtils {
	
	public static void  printfErrorLog(InfoParserException e){
		StringBuffer sb=new StringBuffer();
		sb.append("ToolsInTools  error :\n");
		
		sb.append("message  :");
		sb.append(e.getMessage());
		sb.append("\n\n");
		
		sb.append("时间 ");
		sb.append(StringUtils.getNowDate());
		sb.append("\n\n");
		
		sb.append("toString ");
		sb.append(e.toString());
		sb.append("\n\n");
		
		FileUtils.writeToFile("error.txt", sb.toString());
	}
	public static void  printfErrorLog(Exception e){
		StringBuffer sb=new StringBuffer();
		sb.append("ToolsInTools  error :\n");
		
		sb.append("message  :");
		sb.append(e.getMessage());
		sb.append("\n\n");
		
		sb.append("时间 ");
		sb.append(StringUtils.getNowDate());
		sb.append("\n\n");
		
		sb.append("toString ");
		sb.append(e.toString());
		sb.append("\n\n");
		
		FileUtils.writeToFile("error.txt", sb.toString());
	}
	
	/**
	 * 读取文件
	 * 
	 * @param path
	 * @return
	 */
	public static String readFileString(String path) {
		try {
			String encoding = "GBK";
			File file = new File(path);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				StringBuffer stringBuffer = new StringBuffer();
				while ((lineTxt = bufferedReader.readLine()) != null) {
					// System.out.println(lineTxt);
					stringBuffer.append(lineTxt);
					stringBuffer.append("\n");
				}
				read.close();
				return stringBuffer.toString();
			} else {
				System.out.println("找不到指定的文件");
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 写文件
	 */
	public static void writeToFile(String path, String content) {
		try {
			File file = new File(path);
			// 文件不存在时候，主动穿件文件。
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file, false);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();
			fw.close();

		} catch (Exception e) {
		}
	}

	public static List<String> readFileStrings(String path) {
		try {
			List<String> strings = new ArrayList<String>();
			String encoding = "GBK";
			File file = new File(path);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					// System.out.println(lineTxt);
					if (!lineTxt.isEmpty())
						strings.add(lineTxt);
				}
				read.close();
				return strings;
			} else {
				System.out.println("找不到指定的文件");
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}
		return null;
	}

	// 打开其他任意格式的文件，比如txt,word等
	public static void openFile(String fileString) throws IOException{
		/*final Runtime runtime = Runtime.getRuntime();
		String exec = "rundll32 url.dll FileProtocolHandler file://"
				+ fileString;
		System.out.println(exec);
		runtime.exec(exec);*/
		//上面的方法在打开失败的时候可能不会抛异常~
		Desktop desktop=Desktop.getDesktop();
		desktop.open(new File(fileString));
	
	}

	public static String getThisDirName() {
		return new File("").getAbsoluteFile().getName();
	}

}
