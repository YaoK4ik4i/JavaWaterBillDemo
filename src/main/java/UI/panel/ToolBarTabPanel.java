package UI.panel;

import UI.AppMainWindow;
import UI.ConstantsUI;
import UI.JTabPanelUi;
import UI.MyIconButton;
import UI.MyIconTab;
import tools.RoundBorder;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.apache.log4j.Logger;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

/**
 * 工具栏面板
 * 
 * @author Yao
 *
 */
public class ToolBarTabPanel  extends JPanel {
//	private static final Logger logger = Logger.getLogger(ToolBarTabPanel.class);
	private JTabbedPane tabbedpanelUp;
	
	public static StatusPanel statusPanel;  //概况面板
	public static QueryPanel queryPanel;            //查询
	public static AddPanel addPanel;            //添加
	public static UpdatePanel updatePanel;      //修改
	public static StatisticsPanel statisticsPanel;   //统计
	
	



	/**
	 * 构造
	 * @throws SQLException 
	 */
	public ToolBarTabPanel() throws SQLException {
		initialize();
		addPanel();
		addListener();
	}

	/**
	 * 初始化
	 */
	private void initialize() {
		try{
		//生成一个 1行4列的区域
		Dimension preferredSize = new Dimension(34, ConstantsUI.MAIN_WINDOW_HEIGHT);
		//this.setPreferredSize(preferredSize);
//		this.setMaximumSize(preferredSize);
//		this.setMinimumSize(preferredSize);
//		this.setBackground(ConstantsUI.MAIN_BACK_COLOR);
		this.setLayout(new GridLayout(1, 1));
			} catch (Exception e) {
            e.printStackTrace();
            AppMainWindow.logger.error(e.toString());
            }
		
		//this.setLayout(new BorderLayout());
	}

	/**
	 * 添加工具按钮
	 * @throws SQLException 
	 */
	private void addPanel() throws SQLException {
		statusPanel = new StatusPanel();
        queryPanel = new QueryPanel();
        addPanel = new AddPanel();
        updatePanel = new UpdatePanel();
        statisticsPanel = new StatisticsPanel();

		tabbedpanelUp = new JTabbedPane();
		//tabbedpanelUp.setLayout(new BorderLayout());
		tabbedpanelUp.setUI(new JTabPanelUi("#b8b8b8", "#b8b8b8"));
		
		
		tabbedpanelUp.addTab("最近概况",ConstantsUI.ICON_STATUS, statusPanel ,"最新的十条记录");
	
		
		tabbedpanelUp.addTab("添加账目",ConstantsUI.ICON_ADD, addPanel,"增加");
		tabbedpanelUp.addTab("查询账目",ConstantsUI.ICON_QUERY, queryPanel,"查询");
				
		tabbedpanelUp.addTab("修改账目",ConstantsUI.ICON_UPDATE, updatePanel,"修改");
				
		tabbedpanelUp.addTab("统计账目",ConstantsUI.ICON_STATISTICS, statisticsPanel,"统计");
		
		tabbedpanelUp.setFont(new Font("微软雅黑", Font.PLAIN,25));
		
		
		
		

		
		this.add(tabbedpanelUp);
	}
	
	 private void addListener() {
		 //添加鼠标监听事件
		 tabbedpanelUp.addMouseListener(new MouseAdapter() {
	            public void mouseClicked(MouseEvent e) { //响应鼠标点击事件
	                p(e);
	            }
	            private void p(MouseEvent e)  {
//	                for (int i = 0; i < tabbedpanelUp.getTabCount(); i++) {
	                    Rectangle rect = tabbedpanelUp.getBoundsAt(0); 	//拿到标签的边界
	                    if (rect.contains(e.getX(), e.getY())) { 			//判断是否点在边界内
	                    	try{
	                    	statusPanel.removeAll();
	                    	statusPanel.initialize();
	                    	statusPanel.addComponent();
	                    	} catch(SQLException eq){
	                    		  eq.printStackTrace();
	                    	}
//	                }
	            }
	            }
	        });
	 }
}

