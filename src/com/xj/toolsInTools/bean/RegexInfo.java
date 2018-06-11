package com.xj.toolsInTools.bean;

import com.xj.toolsInTools.constant.Constants;

public class RegexInfo {
	public String Regex;

	public String resKey = Constants.SRC_DIR+"key.png";
	public String resFile = Constants.SRC_DIR+"file.png";
	public String resIcon = Constants.SRC_DIR+"icon.png";
	public String resTitle = "%THIS%";//THIS代表当前目录的名称

	public String getResPath(String type) {
		if (type.equals("KEY")) {
			return resKey;
		} else if(type.equals("HR")){
			return null;
		} else if(type.equals("NULL")){
			return null;
		} else{
			return resFile;
		}
	}
}
