package UI;

import UI.panel.*;

import org.apache.log4j.Logger;  
import org.apache.log4j.Priority;  
import org.apache.log4j.PropertyConfigurator;  
//import tools.PropertyUtil;











import tools.TestLog4;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;


/**
 * 程序入口，主窗口Frame
 *
 * @author Yao
 */
public class AppMainWindow {
     //定类初始化日志对象，在日志输出的时候，可以打印出日志信息所在类
	
	
	public static AppMainWindow window;
	public final static Logger logger = Logger.getLogger(AppMainWindow.class);  
	
	private static TrayIcon trayIcon = null;  
	
	public static SystemTray tray = SystemTray.getSystemTray();  
	
    private static JFrame frame;   //程序主敞口
    private AboutFrame about;

    private static JPanel mainPanel;   //主面板

    public static JMenuBar mainMenu;   //菜单工具栏对象

    public static JMenu SystemMenu;  
    public static JMenu HelpMenu;
    public static JMenu testMenu;

    public static JMenuItem ConifgMenuItem , ExitMenuItem, HelpMenuItem, ComandMenuItem;

    //public static JSplitPane mainSplitPanel;    //上面板
    public static JPanel mainInfoPanel;  //内容面板 
    

//    public static StatusPanel statusPanel;  //概况面板
//    public static QueryPanel queryPanel;            //查询
//    public static AddPanel addPanel;            //添加
//    public static UpdatePanel updatePanel;      //修改
//    public static StatisticsPanel statisticsPanel;   //统计

    public static void main(String[] args) {
    	System.setProperty("WORKDIR" , ConstantsUI.CURRENT_DIR);
    	PropertyConfigurator.configure(ConstantsUI.CURRENT_DIR+ File.separator + "logger" + File.separator + "log4j.properties" );
    	
    	 
        // TODO Auto-generated method stub
        EventQueue.invokeLater(new Runnable() {       //把这个事件（new Runnable(设置可见))添加到awt的事件处理线程当中去
            public void run() {
                try {
                window = new AppMainWindow();
                frame.setVisible(true);
                } catch (Exception e) {
                logger.error(e.toString());
                e.printStackTrace();
                }
            }
        });
    }

    /**
     * 构造，创建APP
     * @throws SQLException 
     */
    public AppMainWindow() throws SQLException {
        initialize();
        addListener();
        //StatusPanel.buttonStartSchedule.doClick();
    }

     private void initialize() throws SQLException {
        logger.info("==================AppInitStart");
        // 设置系统默认样式
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());   //UIManager管理Swing应用程序样式(当前系统的风格)
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException e) {
            // TODO Auto-generated catch block
        	logger.error(e.toString());
            e.printStackTrace();
        }

        // 初始化主窗口
        frame = new JFrame();
        //setBounds(x,y,width,height); (单位:px)
        //x:组件在容器X轴上的起点 y:组件在容器Y轴上的起点 width:组件的长度 height:组件的高度
//        if(frame.isShowing()){
        frame.setBounds(ConstantsUI.MAIN_WINDOW_X, ConstantsUI.MAIN_WINDOW_Y, ConstantsUI.MAIN_WINDOW_WIDTH,
                ConstantsUI.MAIN_WINDOW_HEIGHT); 
//        }

        frame.setTitle(ConstantsUI.APP_TITLE);
        frame.setIconImage(ConstantsUI.IMAGE_ICON);
        frame.setBackground(ConstantsUI.MAIN_BACK_COLOR);
        
        mainPanel = new JPanel(true);
        mainPanel.setBackground(ConstantsUI.MAIN_BACK_COLOR);
        mainPanel.setLayout(new BorderLayout());

        mainMenu = new JMenuBar();          //上册菜单  NORTH
        SystemMenu = new JMenu("系统");
        SystemMenu.setFont(new Font("微软雅黑", Font.PLAIN,15));
        HelpMenu = new JMenu("帮助");
        HelpMenu.setFont(new Font("微软雅黑", Font.PLAIN,15));
        testMenu =new JMenu("系统设置");
        testMenu.setFont(new Font("微软雅黑", Font.PLAIN,15));
//        ConifgMenuItem = new JMenuItem("系统设置");
        ExitMenuItem = new JMenuItem("退出");
        ExitMenuItem.setFont(new Font("微软雅黑", Font.PLAIN,15));
        HelpMenuItem = new JMenuItem("关于");
        HelpMenuItem.setFont(new Font("微软雅黑", Font.PLAIN,15));
        ComandMenuItem = new JMenuItem("调出数据测试台");
        ComandMenuItem.setFont(new Font("微软雅黑", Font.PLAIN,15));
        
        testMenu.add( ComandMenuItem);
        SystemMenu.add(testMenu);

//        SystemMenu.add(ConifgMenuItem);
        SystemMenu.add(ExitMenuItem);
        HelpMenu.add(HelpMenuItem);
       
        mainMenu.add(SystemMenu);
        mainMenu.add(HelpMenu);

        about = new AboutFrame();

//        mainPanelCenter = new JPanel(true);
//        mainPanelCenter.setLayout(new BorderLayout());
//        mainPanelCenter.setBackground(Color.yellow);

        ToolBarTabPanel  tooltabbar = new  ToolBarTabPanel();  //上侧banner CENTER
        //UIManager.put("TabbedPane.selected", ConstantsUI.TOOL_BAR_SELECTED_COLOR);
//        statusPanel = new StatusPanel();
//        queryPanel = new QueryPanel();
//        addPanel = new AddPanel();
//        updatePanel = new UpdatePanel();
//        statisticsPanel = new StatisticsPanel();

        //mainPanelCenter.add(statusPanel, BorderLayout.CENTER);
        
        //mainSplitPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT,toolbar,mainPanelCenter);
        //mainSplitPanel.setDividerLocation(0.5);
        //mainSplitPanel.setTopComponent(toolbar);
        //mainSplitPanel.setBottomComponent(mainPanelCenter);
        //mainPanelUp.setLayout(new BorderLayout());
        
        //mainPanelUp.add(toolbar, BorderLayout.NORTH);
        //mainPanelUp.add(mainPanelCenter, BorderLayout.CENTER);
        mainInfoPanel = new JPanel(true);
        mainInfoPanel.setLayout(new BorderLayout());
        
        mainPanel.add(mainMenu, BorderLayout.NORTH);
        mainPanel.add(tooltabbar, BorderLayout.CENTER);
        mainPanel.add(mainInfoPanel, BorderLayout.SOUTH);


        frame.add(mainPanel);
        frame.addWindowListener(new WindowListener() {

            @Override
            public void windowOpened(WindowEvent e) {
                // TODO Auto-generated method stub
            	window.miniTray();  
            }

            @Override
            public void windowIconified(WindowEvent e) {
                // TODO Auto-generated method stub
            	 
//            	 frame.setExtendedState(JFrame.ICONIFIED);  
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void windowDeactivated(WindowEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void windowClosing(WindowEvent e) {
                
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                

            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }
        });
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        logger.info("==================AppInitEnd");

      

    }
     
     private static void miniTray() { // 窗口最小化到任务栏托盘  
    	  
         ImageIcon trayImg = ConstantsUI.ICON_MINTRAY ;// 托盘图标  
         PopupMenu popup = new PopupMenu();//这个是右键才能触发的菜单  
         MenuItem defaultItem = new MenuItem("显示主界面 return to mainWindow");  
         MenuItem exitItem = new MenuItem("关闭 closed");  
         popup.add(defaultItem);  
         popup.add(exitItem);  
         trayIcon = new TrayIcon(trayImg.getImage(), "test", popup);  
         trayIcon.setImageAutoSize(true);  
   
         trayIcon.addMouseListener(new MouseAdapter() {  
   
             public void mouseClicked(MouseEvent e) {  
   
                 if (e.getClickCount() == 2) {// 单击 1 双击 2  
   
//                     tray.remove(trayIcon);  
                     frame.setVisible(true);  
                     frame.setExtendedState(JFrame.NORMAL);  
                     frame.toFront();  
                 }  //检测鼠标右键单击
                	
                    
                     defaultItem.addActionListener(new ActionListener(){

						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							 tray.remove(trayIcon);  
		                     frame.setVisible(true);  
		                     frame.setExtendedState(JFrame.NORMAL);  
		                     frame.toFront();  
						}
                    	 
                     });  
                  
                     exitItem.addActionListener(new ActionListener() {  
           
                         public void actionPerformed(ActionEvent e) {  
                             System.exit(0);  
                         }  
                     });  
             }  
   
         });  
   
         try {  
   
             tray.add(trayIcon);  
   
         } catch (AWTException e1) {
        	 logger.info(e1.toString());
             e1.printStackTrace();  
         }  
   
     }  

    private void addListener() {
        // TODO Auto-generated method stub
    	ComandMenuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	try {
            		String [] cmd={"cmd","/C","start cd database"}; 

            	    Runtime rt = Runtime.getRuntime();
            	    Process pr = rt.exec(cmd);
            	} catch (IOException e1) {
            		logger.error(e1.toString());
            	    e1.printStackTrace();
            	}

            }
        });
        ExitMenuItem.addActionListener(new ActionListener() {  
            
            public void actionPerformed(ActionEvent e) {  
                System.exit(0);  
            }
        });
                
       
        HelpMenuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
              about.setVisible(true);

            }
        });

    }


}
