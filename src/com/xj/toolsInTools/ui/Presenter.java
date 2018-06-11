package com.xj.toolsInTools.ui;

import java.util.List;

import com.xj.toolsInTools.bean.Info;
import com.xj.toolsInTools.bean.WindowInfo;
import com.xj.toolsInTools.manager.InfoParser;

public class Presenter {

	public List<Info> parserInfo(WindowInfo windowInfo) {
		return  InfoParser.parserInfoFromFile("info.txt",windowInfo);
	}
	
}
