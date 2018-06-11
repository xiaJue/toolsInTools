package com.xj.toolsInTools.ui;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import com.xj.toolsInTools.bean.Info;
import com.xj.toolsInTools.bean.WindowInfo;
import com.xj.toolsInTools.manager.InfoParserException;
import com.xj.toolsInTools.ui.ToolsWindow.OnBottomMenuItemClickListener;
import com.xj.toolsInTools.utils.FileUtils;

public class ToolsUiManager {

	private static ToolsWindow window;

	public static void showView(List<Info> infos, final WindowInfo windowInfo,
			final Presenter presenter) {
		// 构造view
		window = new ToolsWindow(windowInfo.getTitle(), windowInfo.getBounds(),
				windowInfo.getIcon(), infos);

		// 显示view
		window.setVisible(true);
		// 设置事件
		window.getBottomItems().get(1).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

			}
		});

		window.setOnBottomMenuItemClickListener(new OnBottomMenuItemClickListener() {
			public void onClick(Component component, int position) {
				switch (position) {
				case 0:
					window.setShowMessage(!window.getShowMessage());
					break;

				case 1:
					refresh(windowInfo, presenter);
					break;

				default:
					break;
				}
			}

			/**
			 * 刷新
			 * 
			 * @param windowInfo
			 * @param presenter
			 */
			private void refresh(final WindowInfo windowInfo,
					final Presenter presenter) {
				// System.out.println("click");
				// 刷新的事件
				// 首先清空box
				window.getBox().removeAll();
				// 然后重绘一下
				window.getBox().repaint();
				// 再次解析配置文件
				List<Info> newInfos = null;
				try {
					newInfos = presenter.parserInfo(windowInfo);

					// 再次设置给界面
					window.setWindowInfo(windowInfo);
					window.addInfos(newInfos);
					// 重新显示一下
					window.setVisible(true);
				} catch (Exception e) {
					newInfos = new ArrayList<Info>();
					newInfos.add(new Info("error.txt", "详细信息请查看error.txt 点击打开",
							"解析文件出错"));
					FileUtils.printfErrorLog(e);
					//有时候解析文件错误却不会抛异常，然后显示时才报一个空指针,所以这里解决一下
					// 再次设置给界面
					window.setWindowInfo(windowInfo);
					window.addInfos(newInfos);
					// 重新显示一下
					window.setVisible(true);
				}
			}
		});

	}

	public static void setOnItemClickListener(OnItemClickListener listener) {
		window.setOnItemClickListener(listener);
	}

	interface OnItemClickListener {
		void onItemClick(Info info, int position);
	}

	public static void showError(String string, int position) {
		window.showError(string, position);
	}

}
