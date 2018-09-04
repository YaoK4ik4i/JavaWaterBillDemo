package UI;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class AboutFrame extends JFrame {
	private JPanel mainPanel;
	private JLabel aboutContentLabel;
	private JLabel aboutDateLabel;
	private String text1,text2,text;
	public  AboutFrame(){
		try{       
	        initialize();	       
	    	}catch(Exception e){
	    		 AppMainWindow.logger.error(e.toString());
	    		e.printStackTrace();
	    	}
	    }
	public void  initialize(){
		this.setBounds(ConstantsUI.MAIN_WINDOW_X*2, ConstantsUI.MAIN_WINDOW_Y*2, ConstantsUI.MAIN_WINDOW_WIDTH/2,
                ConstantsUI.MAIN_WINDOW_HEIGHT/2); 
		this.setTitle("关于");
		this.setBackground(ConstantsUI.MAIN_BACK_COLOR);
		this.setIconImage(ConstantsUI.IMAGE_ICON);
		
		mainPanel = new JPanel(true);
		mainPanel.setLayout(new BorderLayout());
		StringBuffer strBuf = new StringBuffer(); 
		text1 = "<html>本系统基于javaSwing+sqlite3JDBC构建<br>使用的是Myeclipse编辑并测试各个程序和选项卡页面。"
				+ "<br>在反复学习sqlite用法以后，结合官方管理程序实现了账目数据存储读写修改等功能。</html>";
		
		
//		text = "<html>这是个JLabel<br>当其上面的字符长度宽于JLabel宽度时，自动换行。</html>";
			
		aboutContentLabel = new JLabel(text1);
		aboutContentLabel.setHorizontalAlignment(SwingConstants.CENTER); 
		aboutContentLabel.setFont(new Font("微软雅黑", Font.PLAIN,15));
		
		text2 = "<html>单位: 个人<br>制作: Jacob.Yao<br>日期: 2018年5月<br>"
				+ "qq邮箱: 363907@qq.com</html>";
	
		
		aboutDateLabel = new JLabel(text2);
		aboutDateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		aboutDateLabel.setFont(new Font("微软雅黑", Font.PLAIN,14));
		
		mainPanel.add(aboutContentLabel,BorderLayout.NORTH);
		mainPanel.add(aboutDateLabel,BorderLayout.CENTER);
		this.add(mainPanel);
	}
	}

