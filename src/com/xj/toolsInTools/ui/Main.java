package com.xj.toolsInTools.ui;

import java.io.File;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.xj.toolsInTools.bean.Info;
import com.xj.toolsInTools.bean.RegexInfo;
import com.xj.toolsInTools.bean.WindowInfo;
import com.xj.toolsInTools.manager.InfoParser;
import com.xj.toolsInTools.manager.InfoParserException;
import com.xj.toolsInTools.ui.ToolsUiManager.OnItemClickListener;
import com.xj.toolsInTools.utils.FileUtils;
import com.xj.toolsInTools.utils.StringUtils;

public class Main implements OnItemClickListener {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			new Main().startMain();
			System.out.println(StringUtils.getNowDate());
		} catch (Exception e) {
			//打印错误日志
			FileUtils.printfErrorLog(e);
		}
	}

	private void startMain() {
		//构造presenter>>>该对象只是一个中间人，方便UI和业务的调用
		Presenter presenter=new Presenter();
		//windowInfo是对窗体的基本信息的小封装
		WindowInfo windowInfo=new WindowInfo();
		//解析regex文件和info文件
		/**
		 * regex文件可定义的内容包括部分窗体信息,用于匹配info的正则,还有各种类型条目所显示的图标
		 * 
		 * info文件添加条目语法结构为>fileName>showName>message>
		 * 其中有一些预定义的条目类型:KEY：不对条目设置点击事件如：>KEY>创建日期>2018年6月4日16:07:54>
		 * HR：显示一条水平线>HR>>>
		 * NULL:显示一个空行 >NULL>>>
		 */
		List<Info> infos =presenter.parserInfo(windowInfo);
		//显示窗口
		ToolsUiManager.showView(infos,windowInfo,presenter);
		//设置条目点击事件
		ToolsUiManager.setOnItemClickListener(this);
	}

	public void onItemClick(Info info,int position) {
		//条目点击事件
		if(info.type.equals("FILE")){
			
			File directory = new File("");
			try {
				//打开文件
				FileUtils.openFile(directory.getAbsolutePath()+File.separator+info.getAbsName());
			} catch (Exception e) {
//				System.out.println("open error");
				ToolsUiManager.showError("打开文件失败>可能是文件不存在或name名拼写错误",position);
			}
		}
	}
}
