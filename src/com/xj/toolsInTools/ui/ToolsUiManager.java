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
		// ����view
		window = new ToolsWindow(windowInfo.getTitle(), windowInfo.getBounds(),
				windowInfo.getIcon(), infos);

		// ��ʾview
		window.setVisible(true);
		// �����¼�
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
			 * ˢ��
			 * 
			 * @param windowInfo
			 * @param presenter
			 */
			private void refresh(final WindowInfo windowInfo,
					final Presenter presenter) {
				// System.out.println("click");
				// ˢ�µ��¼�
				// �������box
				window.getBox().removeAll();
				// Ȼ���ػ�һ��
				window.getBox().repaint();
				// �ٴν��������ļ�
				List<Info> newInfos = null;
				try {
					newInfos = presenter.parserInfo(windowInfo);

					// �ٴ����ø�����
					window.setWindowInfo(windowInfo);
					window.addInfos(newInfos);
					// ������ʾһ��
					window.setVisible(true);
				} catch (Exception e) {
					newInfos = new ArrayList<Info>();
					newInfos.add(new Info("error.txt", "��ϸ��Ϣ��鿴error.txt �����",
							"�����ļ�����"));
					FileUtils.printfErrorLog(e);
					//��ʱ������ļ�����ȴ�������쳣��Ȼ����ʾʱ�ű�һ����ָ��,����������һ��
					// �ٴ����ø�����
					window.setWindowInfo(windowInfo);
					window.addInfos(newInfos);
					// ������ʾһ��
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
