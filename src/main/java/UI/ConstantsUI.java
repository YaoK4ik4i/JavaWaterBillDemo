package UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;


/**
 * UI相关的常量
 *
 * @author Yao
 */
public class ConstantsUI {
    
    /**
     * 软件名称,版本
     */
    public final static String APP_NAME = "Yao的账目记录系统";
    public final static String APP_VERSION = "v_1.01_180601";
    public final static String APP_TITLE = APP_NAME +"      (   " +APP_VERSION + ")" ;

    /**
     * 主窗口大小
     */
    public final static int MAIN_WINDOW_X = 280;
    public final static int MAIN_WINDOW_Y = 156;
    public final static int MAIN_WINDOW_WIDTH = 1360;
    public final static int MAIN_WINDOW_HEIGHT = 768;
    
    /**
     * 系统当前路径
     */
    public final static String CURRENT_DIR = System.getProperty("user.dir");   //获取本项目绝对路径根目录
    
    /**
     * 主窗口图标
     */
    // https://www.easyicon.net/1075796-item_list_icon.html
    public final static Image IMAGE_ICON = Toolkit.getDefaultToolkit().getImage(AppMainWindow.class.getResource("/icon/yaa_32px.png"));

    /**
     * 主窗口背景色
     */
    public final static Color MAIN_BACK_COLOR = new Color(240,240, 240);

    /**
     * 工具栏背景色
     */
    public final static Color TOOL_BAR_SELECTED_COLOR = new Color(184,184,184);
    /**
     * 表格线条背景色
     */
    public final static Color TABLE_LINE_COLOR = new Color(229, 229, 229);
    /**
     * 工具栏图标
     */
    // 状态 默认
    public final static ImageIcon ICON_STATUS = new ImageIcon(AppMainWindow.class.getResource("/icon/status_32px.png"));
    // 状态 激活
    public final static ImageIcon ICON_STATUS_ENABLE = new ImageIcon(
            AppMainWindow.class.getResource("/icon/status_enabled.png"));
    
    // 状态 默认
    public final static ImageIcon ICON_ADD = new ImageIcon(
            AppMainWindow.class.getResource("/icon/add_32px.png"));
    // 状态 激活
    public final static ImageIcon ICON_ADD_ENABLE = new ImageIcon(
            AppMainWindow.class.getResource("/icon/add_enabled.png"));
    
    public final static ImageIcon ICON_QUERY = new ImageIcon(
            AppMainWindow.class.getResource("/icon/query_32px.png"));
    // 状态 激活
    public final static ImageIcon ICON_QUERY_ENABLE = new ImageIcon(
            AppMainWindow.class.getResource("/icon/query_enabled.png"));
    
    public final static ImageIcon ICON_UPDATE = new ImageIcon(
            AppMainWindow.class.getResource("/icon/update_32px.png"));
    // 状态 激活
    public final static ImageIcon ICON_UPDATE_ENABLE = new ImageIcon(
            AppMainWindow.class.getResource("/icon/update_enabled.png"));
    
    public final static ImageIcon ICON_STATISTICS = new ImageIcon(
            AppMainWindow.class.getResource("/icon/statistics_32px.png"));
    // 状态 激活
    public final static ImageIcon ICON_STATISTICS_ENABLE = new ImageIcon(
            AppMainWindow.class.getResource("/icon/statistics_enabled.png"));
    //概况标签的图标
    public final static ImageIcon ICON_STATUS_YES = new ImageIcon(
            AppMainWindow.class.getResource("/icon/yes_32px.png"));
    //概况笑脸的图标
    public final static ImageIcon ICON_STATUS_HELLO = new ImageIcon(
            AppMainWindow.class.getResource("/icon/smiling_32px.png"));  //https://www.easyicon.net/1147451-smiling_icon.html
    
    //删除警告
    public final static ImageIcon ICON_ALERT = new ImageIcon(
            AppMainWindow.class.getResource("/icon/alert-32px.png"));
    //提示
    public final static ImageIcon ICON_ASK = new ImageIcon(
            AppMainWindow.class.getResource("/icon/ask_32px.png"));
    
    //最小化托盘
    public final static ImageIcon ICON_MINTRAY = new ImageIcon(
            AppMainWindow.class.getResource("/icon/yaa_32px.png"));
    //标题文字
    public final static Font FONT_TITLE = new Font("宋体",Font.BOLD,15);
    //内容字体
    public final static Font FONT_CONTENT= new Font("微软雅黑",Font.PLAIN,15);
    



}
