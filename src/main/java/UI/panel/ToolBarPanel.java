package UI.panel;

import UI.AppMainWindow;
import UI.ConstantsUI;
import UI.MyIconButton;
import tools.RoundBorder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 工具栏面板
 * 
 * @author Yao
 *
 */
public class ToolBarPanel  extends JPanel {
	private static MyIconButton buttonStatus;
	private static MyIconButton buttonAdd;
	private static MyIconButton buttonQuery;
	private static MyIconButton buttonUpdate;
	private static MyIconButton buttonStatistics;

	RoundBorder roundBorder;



	/**
	 * 构造
	 */
	public ToolBarPanel() {
		initialize();
		addButton();
		addListener();
	}

	/**
	 * 初始化
	 */
	private void initialize() {
		//生成一个 1行4列的区域
		Dimension preferredSize = new Dimension(34, ConstantsUI.MAIN_WINDOW_HEIGHT);
		this.setPreferredSize(preferredSize);
		this.setMaximumSize(preferredSize);
		this.setMinimumSize(preferredSize);
		this.setBackground(ConstantsUI.MAIN_BACK_COLOR);
		this.setLayout(new GridLayout(1, 1));
	}

	/**
	 * 添加工具按钮
	 */
	private void addButton() {

		JPanel panelUp = new JPanel();
		panelUp.setBackground(ConstantsUI.MAIN_BACK_COLOR);
		panelUp.setLayout(new FlowLayout(0, 2, 2));
		
		buttonStatus = new MyIconButton(ConstantsUI.ICON_STATUS_ENABLE, ConstantsUI.ICON_STATUS_ENABLE,
				ConstantsUI.ICON_STATUS, "最新的十条记录");
		buttonAdd = new MyIconButton(ConstantsUI.ICON_ADD, ConstantsUI.ICON_ADD_ENABLE,
				ConstantsUI.ICON_ADD, "查询记录");
		buttonQuery = new MyIconButton(ConstantsUI.ICON_QUERY, ConstantsUI.ICON_QUERY_ENABLE,
				ConstantsUI.ICON_QUERY, "查询记录");
		buttonUpdate = new MyIconButton(ConstantsUI.ICON_UPDATE, ConstantsUI.ICON_UPDATE_ENABLE,
				ConstantsUI.ICON_UPDATE, "修改记录");
		buttonStatistics = new MyIconButton(ConstantsUI.ICON_STATISTICS, ConstantsUI.ICON_STATISTICS_ENABLE,
				ConstantsUI.ICON_STATISTICS, "统计计算");
		
		panelUp.add(buttonStatus);
		panelUp.add(buttonAdd);
		panelUp.add(buttonQuery);
		panelUp.add(buttonUpdate);
		panelUp.add(buttonStatistics);

		
		this.add(panelUp);
	}

	/**
	 * 为各按钮添加事件动作监听
	 */
	private void addListener() {
		buttonStatus.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				roundBorder = new RoundBorder();
				buttonStatus.setBorder(roundBorder);
				buttonStatus.setIcon(ConstantsUI.ICON_STATUS_ENABLE);
				buttonAdd.setIcon(ConstantsUI.ICON_ADD);
				buttonQuery.setIcon(ConstantsUI.ICON_QUERY);
				buttonUpdate.setIcon(ConstantsUI.ICON_UPDATE);
				buttonStatistics.setIcon(ConstantsUI.ICON_STATISTICS);

//				AppMainWindow.mainPanelCenter.removeAll();
//				StatusPanel.setContent();
//				AppMainWindow.mainPanelCenter.add(AppMainWindow.statusPanel, BorderLayout.CENTER);
//
//				AppMainWindow.mainPanelCenter.updateUI();

			}
		});
	

	
		buttonAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				roundBorder = new RoundBorder();
				buttonStatus.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
				buttonAdd.setBorder(roundBorder);
				buttonStatus.setIcon(ConstantsUI.ICON_STATUS);
				buttonAdd.setIcon(ConstantsUI.ICON_ADD_ENABLE);
				buttonQuery.setIcon(ConstantsUI.ICON_QUERY);
				buttonUpdate.setIcon(ConstantsUI.ICON_UPDATE);
				buttonStatistics.setIcon(ConstantsUI.ICON_STATISTICS);

//				AppMainWindow.mainPanelCenter.removeAll();
//				AddPanel.setContent();
//				AppMainWindow.mainPanelCenter.add(AppMainWindow.addPanel, BorderLayout.CENTER);
//
//				AppMainWindow.mainPanelCenter.updateUI();

			}
		});
	

	/*
		buttonQuery.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				roundBorder = new RoundBorder();
				buttonAdd.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
				buttonQuery.setBorder(roundBorder);
				buttonStatus.setIcon(ConstantsUI.ICON_STATUS);
				buttonAdd.setIcon(ConstantsUI.ICON_ADD);
				buttonQuery.setIcon(ConstantsUI.ICON_QUERY_ENABLE);
				buttonUpdate.setIcon(ConstantsUI.ICON_UPDATE);
				buttonStatistics.setIcon(ConstantsUI.ICON_STATISTICS);

				AppMainWindow.mainPanelCenter.removeAll();
				//QueryPanel.setContent();
				AppMainWindow.mainPanelCenter.add(AppMainWindow.queryPanel, BorderLayout.CENTER);

				AppMainWindow.mainPanelCenter.updateUI();

			}
		});
	

	
		buttonUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				roundBorder = new RoundBorder();
				buttonQuery.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
				buttonUpdate.setBorder(roundBorder);
				buttonStatus.setIcon(ConstantsUI.ICON_STATUS);
				buttonAdd.setIcon(ConstantsUI.ICON_ADD);
				buttonQuery.setIcon(ConstantsUI.ICON_QUERY);
				buttonUpdate.setIcon(ConstantsUI.ICON_UPDATE_ENABLE);
				buttonStatistics.setIcon(ConstantsUI.ICON_STATISTICS);

				AppMainWindow.mainPanelCenter.removeAll();
				//AddPanel.setContent();
				AppMainWindow.mainPanelCenter.add(AppMainWindow.updatePanel, BorderLayout.CENTER);

				AppMainWindow.mainPanelCenter.updateUI();

			}
		});
	

	
		buttonStatistics.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				roundBorder = new RoundBorder();
				buttonUpdate.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
				buttonStatistics.setBorder(roundBorder);
				buttonStatus.setIcon(ConstantsUI.ICON_STATUS);
				buttonAdd.setIcon(ConstantsUI.ICON_ADD);
				buttonQuery.setIcon(ConstantsUI.ICON_QUERY);
				buttonUpdate.setIcon(ConstantsUI.ICON_UPDATE);
				buttonStatistics.setIcon(ConstantsUI.ICON_STATISTICS_ENABLE);

				AppMainWindow.mainPanelCenter.removeAll();
				//AddPanel.setContent();
				AppMainWindow.mainPanelCenter.add(AppMainWindow.statisticsPanel, BorderLayout.CENTER);

				AppMainWindow.mainPanelCenter.updateUI();

			}
		});
		*/
	}
	
	

}

