package com.xj.toolsInTools.ui;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Rectangle;
import java.awt.ScrollPane;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.xj.toolsInTools.bean.Info;
import com.xj.toolsInTools.bean.WindowInfo;
import com.xj.toolsInTools.constant.Constants;
import com.xj.toolsInTools.ui.ToolsUiManager.OnItemClickListener;

public class ToolsWindow extends Frame {

	private List<Info> infos;
	private Panel rootPane;
	private ScrollPane scrollPane;

	private Color clickColor = new Color(Integer.parseInt("CD919E", 16));

	private Color pressedColor = new Color(Integer.parseInt("CDC5BF", 16));
	private Color color = new Color(Integer.parseInt("CFCFCF", 16));
	private Box box;

	private Panel bottomPanel;

	private Checkbox checkbox;

	private OnItemClickListener listener;

	public ToolsWindow(String title, Rectangle bounds, Image icon,
			 List<Info> infos) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				System.exit(0);
			}
		});
		setTitle(title);
		setBounds(bounds);
		// ���ֶ����ϱ���
		setLayout(new BorderLayout());
		setIconImage(icon);

		rootPane = new Panel();
		rootPane.setBounds(bounds);
		rootPane.setLayout(new BorderLayout());
		rootPane.setBackground(color);

		scrollPane = new ScrollPane();
		rootPane.add(scrollPane, BorderLayout.CENTER);
		add(rootPane);

		// contentPane = new Panel();
		// contentPane.setLayout(new GridLayout(20, 1));
		box = Box.createVerticalBox();
		scrollPane.add(box);

		bottomPanel = new Panel();
		bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

		bottomItems = new ArrayList<Component>();

		// ��ӵײ�����
		checkbox = new Checkbox();
		checkbox.setLabel("��ʾmessage  ");
		addBottonItem(checkbox);

		addBottomIconItem("refresh.png");

		add(bottomPanel, BorderLayout.SOUTH);

		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent arg0) {
				for (int i = 0; i < ToolsWindow.this.infos.size(); i++) {
					Info info = ToolsWindow.this.infos.get(i);
					if (info.type.equals("HR")) {
						((JLabel) box.getComponent(i)).setText(getPackingText(
								info, isShowMessage));
					}
				}
			}
		});

		// ���item
		addInfos(infos);
	}

	public void addInfos(final List<Info> infos) {
		this.infos=infos;
		for (Info info : infos) {
			addItem(info);
		}
	}

	private void addBottonItem(Checkbox checkbox) {
		// TODO
		bottomPanel.add(checkbox);
		bottomItems.add(checkbox);
	}

	// ��ŵײ���Ŀ
	private List<Component> bottomItems;

	public List<Component> getBottomItems() {
		return bottomItems;
	}

	public Box getBox() {
		return box;
	}

	/**
	 * ��ӵײ���Ŀ
	 * 
	 * @param iconPath
	 */
	private void addBottomIconItem(String iconPath) {
		ImageIcon icon = new ImageIcon(Constants.SRC_DIR + iconPath);
		icon.setImage(icon.getImage().getScaledInstance(20, 20,
				Image.SCALE_DEFAULT));

		JLabel label = new JLabel();
		label.setToolTipText("ˢ��");
		label.setIcon(icon);
		bottomPanel.add(label);
		bottomItems.add(label);
	}

	private int addCount = 0;

	public void addItem(Info info) {
		// name label

		final JLabel label = new JLabel(getPackingText(info, isShowMessage));
		label.setFont(getLabelFont());
		// ����ͼ��
		ImageIcon icon;
		// if(info.getName().contains("###")){
		// icon = new ImageIcon("res/key.png");
		// }else{
		// icon = new ImageIcon("res/file.png");
		// }
		if (info.getIconPath() != null) {
			icon = new ImageIcon(info.getIconPath());

			icon.setImage(icon.getImage().getScaledInstance(28, 28,
					Image.SCALE_DEFAULT));
			label.setIcon(icon);
		} else {
			label.setSize(getSize().width, 50);
		}
		label.setOpaque(true);
		label.setBackground(color);
		if (info.type == "FILE") {
			// ���������ʽ
			label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}
		label.addMouseListener(new MouseEvent(label, info, addCount));

		// message label

		box.add(label);
		addCount++;
	}

	private Font getLabelFont() {
		return new Font("����", Font.PLAIN, 12);
	}

	/**
	 * ��װmessage�ַ���
	 * 
	 * @param info
	 * @return
	 */
	private String getShowMessageText(Info info, boolean b) {
		return b ? " >                 > " + Constants.MESSAGE_HTML_FRONT
				+ info.getMessage() + Constants.MESSAGE_HTML_END : "";
	}

	/**
	 * ��ð�װ��html���ַ���
	 * 
	 * @param info
	 * @return
	 */
	private String getPackingText(Info info, boolean b) {
		StringBuffer sb = new StringBuffer();
		sb.append(Constants.LABEL_HTML_FRONT + info.getName());
		if (info.type.equals("HR")) {
			// �����Ŀ������ˮƽ��
			return Constants
					.getHrHtmlString(getWidth() - (getWidth() / 11) * 3);
		} else if (!info.type.equals("NULL")) {
			// �����Ŀ�����ǲ�Ϊ���е���Ŀ
			sb.append(getShowMessageText(info, b));
		}
		return sb.append(Constants.LABEL_HTML_END).toString();
	}

	class MouseEvent extends MouseAdapter {

		private JLabel lJLabel;
		private Info info;
		private int position;

		public MouseEvent(JLabel lJLabel, Info info, int position) {
			this.lJLabel = lJLabel;
			this.info = info;
			this.position = position;
		}

		@Override
		public void mouseClicked(java.awt.event.MouseEvent arg0) {
			lJLabel.setBackground(color);
			// �������
			if (listener != null) {
				listener.onItemClick(info, position);
			}
		}

		@Override
		public void mousePressed(java.awt.event.MouseEvent arg0) {
			lJLabel.setBackground(clickColor);
		}

		@Override
		public void mouseEntered(java.awt.event.MouseEvent arg0) {
			lJLabel.setBackground(pressedColor);
			// ��������ʾmessage
//			if (!isShowMessage)
				lJLabel.setText(getPackingText(info, true));
		}

		@Override
		public void mouseExited(java.awt.event.MouseEvent arg0) {
			lJLabel.setBackground(color);
			// ����뿪����message
//			if (!isShowMessage)
				lJLabel.setText(getPackingText(info, isShowMessage));
		}
	}

	/**
	 * ��Ŀ����¼�
	 * 
	 * @param listener
	 */

	public void setOnItemClickListener(OnItemClickListener listener) {
		this.listener = listener;
	}

	/**
	 * �ײ��˵���Ŀ����¼�
	 */
	interface OnBottomMenuItemClickListener {
		void onClick(Component component, int position);
	}

	public void setOnBottomMenuItemClickListener(
			final OnBottomMenuItemClickListener onBottomMenuItemClickListener) {
		for (int i = 0; i < bottomItems.size(); i++) {
			final Component component = bottomItems.get(i);
			final int index = i;
			component.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(java.awt.event.MouseEvent arg0) {
					onBottomMenuItemClickListener.onClick(component, index);
				}
			});
		}

	}
	/**
	 * ��ʾһ��������Ϣ
	 * @param string
	 * @param position
	 */
	public void showError(String string, int position) {
		JLabel label = (JLabel) box.getComponent(position);
		label.setText(string);
		label.setBackground(clickColor);
	}

	/**
	 * ���ô�����Ϣ
	 * 
	 * @param windowInfo
	 */
	public void setWindowInfo(WindowInfo windowInfo) {
		setTitle(windowInfo.getTitle());
		setIconImage(windowInfo.getIcon());
		if (getBounds() == null)
			setBounds(windowInfo.getBounds());
	}

	private boolean isShowMessage = false;

	/**
	 * �����Ƿ�ֱ����ʾmessage
	 * 
	 * @param b
	 */
	public void setShowMessage(boolean b) {
		if (!(isShowMessage && b)) {
			isShowMessage = b;
			for (int i = 0; i < box.getComponentCount(); i++) {
				JLabel label = (JLabel) box.getComponent(i);
				Info info = infos.get(i);
				label.setText(getPackingText(info, isShowMessage));
			}
		}
	}

	public boolean getShowMessage() {
		return isShowMessage;
	}

}
