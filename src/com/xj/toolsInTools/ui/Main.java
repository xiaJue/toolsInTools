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
			//��ӡ������־
			FileUtils.printfErrorLog(e);
		}
	}

	private void startMain() {
		//����presenter>>>�ö���ֻ��һ���м��ˣ�����UI��ҵ��ĵ���
		Presenter presenter=new Presenter();
		//windowInfo�ǶԴ���Ļ�����Ϣ��С��װ
		WindowInfo windowInfo=new WindowInfo();
		//����regex�ļ���info�ļ�
		/**
		 * regex�ļ��ɶ�������ݰ������ִ�����Ϣ,����ƥ��info������,���и���������Ŀ����ʾ��ͼ��
		 * 
		 * info�ļ������Ŀ�﷨�ṹΪ>fileName>showName>message>
		 * ������һЩԤ�������Ŀ����:KEY��������Ŀ���õ���¼��磺>KEY>��������>2018��6��4��16:07:54>
		 * HR����ʾһ��ˮƽ��>HR>>>
		 * NULL:��ʾһ������ >NULL>>>
		 */
		List<Info> infos =presenter.parserInfo(windowInfo);
		//��ʾ����
		ToolsUiManager.showView(infos,windowInfo,presenter);
		//������Ŀ����¼�
		ToolsUiManager.setOnItemClickListener(this);
	}

	public void onItemClick(Info info,int position) {
		//��Ŀ����¼�
		if(info.type.equals("FILE")){
			
			File directory = new File("");
			try {
				//���ļ�
				FileUtils.openFile(directory.getAbsolutePath()+File.separator+info.getAbsName());
			} catch (Exception e) {
//				System.out.println("open error");
				ToolsUiManager.showError("���ļ�ʧ��>�������ļ������ڻ�name��ƴд����",position);
			}
		}
	}
}
