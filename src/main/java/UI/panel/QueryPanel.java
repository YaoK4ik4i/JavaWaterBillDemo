package UI.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.RandomAccessFile;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import org.apache.log4j.Logger;  
import org.apache.log4j.Priority;  
import org.apache.log4j.PropertyConfigurator;  

import UI.AppMainWindow;
import UI.ConstantsUI;
import UI.MyButtonBorder;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import tools.Accounts;
import tools.DateSplit;
import tools.MakePanel;
import tools.SqliteBaseManager;
import tools.ExportTable;

/**
 * 查询面板面板
 *
 * @author Yao
 * @param <StringPrivate>
 */

public class QueryPanel extends JPanel {
	SqliteBaseManager db = new SqliteBaseManager();  
    ResultSet rs;
    
//    private static final Logger logger = Logger.getLogger(QueryPanel.class);
    
    private JLabel labelSearchOrderId;
    private JLabel labelSearchFromTime;
    private JLabel labelSplit;
    private JLabel labelName;
    private JLabel labelExport;
    
    private JTextField textSearchOrderId;
    private JTextField textSearchTimeYear;
    private JTextField textSearchTimeMonthFrom;
    private JTextField textSearchTimeMonthTo;
    private JTextField textSearchName;
    
    
    private JButton searchFromIdBtn;
    private JButton searchFromTimeBtn;
    private JButton searchFromNameBtn;
    private JButton exportResults;
    
    private DatePicker datepick;
   // private DatePicker datepickTo;
    private DatePickerSettings datepicksetting;
    //private DatePickerSettings datepicksettingB;
    
    private JScrollPane tableScroll;
    
    public JTable dataTable;
    
    private Border border;
    
    public  ExportTable exTable;
    
    Vector<String>  dataColumn ;      //存放表列数据
    Vector dataRow ;    //行数据
//    Vector hang;
    
    DefaultTableModel model;  //JTable 数据模型类
    //DefaultTableModel model2;
    
    String strSQL = null;
    
    String yearA = null;;
	String monthA =  null;;
	String dayA =  null;;
    

    
    public QueryPanel() {
    	try{       
        initialize();
        addComponent();
        addListener();
    	}catch(Exception e){
    		 AppMainWindow.logger.error(e.toString());
    		e.printStackTrace();
    	}
           }
    
    private void initialize(){
    	 this.setLayout(new BorderLayout());
    	 
    	 labelSearchOrderId = new JLabel("根据账目id查询(需要精确的账目编号):",JLabel.CENTER);
    	 labelSearchFromTime = new JLabel("根据时间范围查询(仅输入数字如2017、03、04):  从",JLabel.CENTER);
    	 labelSplit = new JLabel("到",JLabel.CENTER);
     	 labelSplit.setFont(new Font("微软雅黑", Font.PLAIN,18));
     	 
     	labelExport = new JLabel("导出Excel表格文件:",JLabel.CENTER);
     	labelExport.setFont(new Font("微软雅黑", Font.PLAIN,18));
     	 
     	labelName = new JLabel("根据物品名称查询(支持模糊搜索):",JLabel.CENTER);
    	 
    	 textSearchOrderId = new JTextField(20);
    	 textSearchOrderId.setFont(new Font("宋体", Font.PLAIN,18));
    	 
    	 textSearchName = new JTextField(25);
    	 textSearchName.setFont(new Font("宋体", Font.PLAIN,18));
    	 
    	 datepicksetting = new DatePickerSettings();
    	 datepicksetting.setAllowEmptyDates(false);    	 
    	 datepicksetting.setFontValidDate(new Font("宋体", Font.PLAIN,18));
    	 
    	 //datepicksettingB = new DatePickerSettings();
    	// datepicksettingB.setAllowEmptyDates(false);    	 
    	 //datepicksettingB.setFontValidDate(new Font("宋体", Font.PLAIN,18));
    	 
    	 datepick = new DatePicker(datepicksetting);
    	 //datepickTo = new DatePicker(datepicksettingB);
    		DateSplit dateSplit = new DateSplit(datepick);
			String [] date = dateSplit.Splitt();
			yearA = date[0];
			monthA = date[1];
			dayA = date[2];
			
		textSearchTimeYear = new JTextField(yearA,20);
		textSearchTimeYear.setFont(new Font("宋体", Font.ITALIC,18));
		textSearchTimeYear.setForeground(new Color(171,173,179));
		
		textSearchTimeMonthFrom = new JTextField(monthA,15);
		textSearchTimeMonthFrom.setFont(new Font("宋体", Font.ITALIC,18));
		textSearchTimeMonthFrom.setForeground(new Color(171,173,179));
		
		textSearchTimeMonthTo = new JTextField(monthA,15);
		textSearchTimeMonthTo.setFont(new Font("宋体", Font.ITALIC,18));
		textSearchTimeMonthTo.setForeground(new Color(171,173,179));
		
    	 
    	 searchFromIdBtn = new JButton("查询");
    	 searchFromIdBtn.setPreferredSize(new Dimension(80,30));
    	 searchFromIdBtn.setBorder(new MyButtonBorder());
    	 searchFromIdBtn.setFont(new Font("微软雅黑", Font.PLAIN,20));
    	 
    	 searchFromTimeBtn = new JButton("查询");
    	 searchFromTimeBtn.setPreferredSize(new Dimension(80,30));
    	 searchFromTimeBtn.setBorder(new MyButtonBorder());
    	 searchFromTimeBtn.setFont(new Font("微软雅黑", Font.PLAIN,20));
    	 
    	 searchFromNameBtn = new JButton("查询");
    	 searchFromNameBtn.setPreferredSize(new Dimension(80,30));
    	 searchFromNameBtn.setBorder(new MyButtonBorder());
    	 searchFromNameBtn.setFont(new Font("微软雅黑", Font.PLAIN,20));
    	 
    	 exportResults = new JButton("导出");
    	 exportResults.setPreferredSize(new Dimension(45,30));
    	 exportResults.setBorder(new MyButtonBorder());
    	 exportResults.setFont(new Font("微软雅黑", Font.PLAIN,19));
    	 
    	
    			 
    	 
    	 
    	 
    	 
    }
    
    private void addComponent(){
    	 this.add(getUpPanel(),BorderLayout.NORTH);
         this.add(getBottomPanel(),BorderLayout.CENTER);
    }
    
    private JPanel getUpPanel() {
    	 JPanel searchPanel = new JPanel();
    	 searchPanel.setLayout(new GridLayout(4,3));
    	 
    	 border = new EtchedBorder(EtchedBorder.LOWERED);
    	 searchPanel.setBorder(border);    
    	 
    	 JPanel fromOrderId = new 	MakePanel(labelSearchOrderId,textSearchOrderId );
    	 fromOrderId.add(searchFromIdBtn);
    	 
    	 searchPanel.add(fromOrderId);
    	 
    	 JPanel fromOrderName = new MakePanel(labelName,textSearchName);
    	 fromOrderName.add(searchFromNameBtn);
    	 
    	 searchPanel.add(fromOrderName);
    	 
    	 
    	 JPanel fromDate = new 	MakePanel(labelSearchFromTime , textSearchTimeYear);
    	 fromDate.add(textSearchTimeMonthFrom);
    	 fromDate.add(labelSplit);
    	 fromDate.add(textSearchTimeMonthTo);
    	 fromDate.add(searchFromTimeBtn);
    	 
    	 JPanel panelExport = new JPanel();
    	 panelExport.setLayout(new FlowLayout(0,25,5));
    	 panelExport.add(labelExport);
    	 panelExport.add(exportResults);
    	 
    	 
    	 searchPanel.add(fromDate);
    	 
    	 searchPanel.add(panelExport);
    	 
    	 
    	 
		return searchPanel;
    	
    }
    
    private JPanel getBottomPanel() {
    	 JPanel mainPanel = new JPanel();
    	 mainPanel.setLayout(new BorderLayout());
    	 
    	 dataColumn = new Vector <String>();
    	 dataColumn.add("账目编号");   //1
    	 dataColumn.add("年");				//2
    	 dataColumn.add("月");				//3
    	 dataColumn.add("日");				//4
    	 dataColumn.add("物品名称");				//7
    	 dataColumn.add("物品数量");				//8
    	 dataColumn.add("物品价格");				//9
    	 dataColumn.add("支付方式");		//5
    	 dataColumn.add("是否私人物品");				//6
    	 dataColumn.add("备注");				//10
    	 
    	
    	 dataRow = new  Vector<String>();
    	 model = new DefaultTableModel(dataRow, dataColumn);
    	
    	 dataTable = new JTable(model);
    	 
    	 dataTable.setFont(new Font("宋体", Font.PLAIN,21));
    	 dataTable.setRowHeight(23);
    	 dataTable.getTableHeader().setFont(new Font("宋体", Font.BOLD,18));    //设置表头
//    	 dataTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    	 
//    	 dataTable.getTableHeader().setBorder(border);
	     tableScroll =new JScrollPane();
//	     tableScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);	
//	     tableScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	     tableScroll.setViewportView(dataTable);
	     
	     mainPanel.add(tableScroll,BorderLayout.CENTER);
    	 
		return mainPanel;
    	
    }
    
    public static void FitTableColumns(JTable myTable){  
        JTableHeader header = myTable.getTableHeader();  
         int rowCount = myTable.getRowCount();  
      
         Enumeration columns = myTable.getColumnModel().getColumns();  
         while(columns.hasMoreElements()){  
             TableColumn column = (TableColumn)columns.nextElement();  
             int col = header.getColumnModel().getColumnIndex(column.getIdentifier());  
             int width = (int)myTable.getTableHeader().getDefaultRenderer()  
                     .getTableCellRendererComponent(myTable, column.getIdentifier()  
                             , false, false, -1, col).getPreferredSize().getWidth();  
             for(int row = 0; row<rowCount; row++){  
                 int preferedWidth = (int)myTable.getCellRenderer(row, col).getTableCellRendererComponent(myTable,  
                   myTable.getValueAt(row, col), false, false, row, col).getPreferredSize().getWidth();  
                 width = Math.max(width, preferedWidth);  
             }  
             header.setResizingColumn(column); // 此行很重要  
             column.setWidth(width+myTable.getIntercellSpacing().width);  
         }  
    }  
    
    
    private void addListener() {
    	searchFromIdBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try{if( textSearchOrderId.getText().trim().equals("")){
					JOptionPane.showMessageDialog(null,"您没有键入任何账目编号。","注意", JOptionPane.PLAIN_MESSAGE, ConstantsUI.ICON_ASK);
				}else{
					 textSearchName.setText("");
					 textSearchTimeYear.setText("");
					 textSearchTimeMonthFrom.setText("");
					 textSearchTimeMonthTo.setText("");
					 
					model.setRowCount(0);
					String orderid = textSearchOrderId.getText().trim();
//					int id=Integer.parseInt(orderid);
					strSQL = "select * from accounts where orderid =\""+ orderid +"\"";
					
					rs= db.getResult(strSQL);
					//int c = rs.getRow();
				   // if(rs.next()){
					while(rs.next()){
//						//rowData 可以存放多行
						Vector hang=new Vector(); 
						hang.add(rs.getString(1));
						hang.add(rs.getString(2));  
						hang.add(rs.getString(3));  
						hang.add(rs.getString(4));
						hang.add(rs.getString(7));
						hang.add(rs.getString(8)); 
						hang.add(rs.getString(9));  
						hang.add(rs.getString(5));						
						hang.add(rs.getString(6));  
						hang.add(rs.getString(10));  
						
						dataRow.add(hang);
						}
					
					if(!dataRow.isEmpty()){
					
//					dataRow = db.getRowResult(sqlstr);
					
					model = new DefaultTableModel(dataRow, dataColumn);
					
					dataTable.setModel(model);
				    }else{
				    	JOptionPane.showMessageDialog(null,"没有搜索到相关记录。","注意", JOptionPane.PLAIN_MESSAGE, ConstantsUI.ICON_ASK);
				    }
				}
				}catch(Exception sqle){
					 AppMainWindow.logger.error(sqle.toString());
					sqle.printStackTrace();
				}
			}
    		
    	});
    	
    	searchFromNameBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try{if( textSearchName.getText().trim().equals("")){
					JOptionPane.showMessageDialog(null,"您没有输入任何名称检索信息。","注意", JOptionPane.PLAIN_MESSAGE, ConstantsUI.ICON_ASK);
				}else{
					 textSearchOrderId.setText("");
					 textSearchTimeYear.setText("");
					 textSearchTimeMonthFrom.setText("");
					 textSearchTimeMonthTo.setText("");
					model.setRowCount(0);
					String ordername = textSearchName.getText().trim();
//					int id=Integer.parseInt(orderid);
					strSQL = "select * from accounts where ITEMNAME like  \"%"+  ordername +"%\"";
					
					rs= db.getResult(strSQL);
					//int c = rs.getRow();
				   // if(rs.next()){
					while(rs.next()){
//						//rowData 可以存放多行
						Vector hang=new Vector(); 
						hang.add(rs.getString(1));
						hang.add(rs.getString(2));  
						hang.add(rs.getString(3));  
						hang.add(rs.getString(4));
						hang.add(rs.getString(7));
						hang.add(rs.getString(8)); 
						hang.add(rs.getString(9));  
						hang.add(rs.getString(5));						
						hang.add(rs.getString(6));  
						hang.add(rs.getString(10));  
						
						dataRow.add(hang);
						}
					
					if(!dataRow.isEmpty()){
					
//					dataRow = db.getRowResult(sqlstr);
					
					model = new DefaultTableModel(dataRow, dataColumn);
					
					dataTable.setModel(model);
				    }else{
				    	JOptionPane.showMessageDialog(null,"没有搜索到相关记录。","注意", JOptionPane.PLAIN_MESSAGE, ConstantsUI.ICON_ASK);
				    }
				}
				}catch(Exception sqle){
					 AppMainWindow.logger.error(sqle.toString());
					sqle.printStackTrace();
				}
			}
    		
    	});
    	
    	searchFromTimeBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
//				String monthTo;
				try{
					if(textSearchTimeYear.getText().trim().equals("")){
						JOptionPane.showMessageDialog(null,"请输入搜索年份。","注意", JOptionPane.PLAIN_MESSAGE, ConstantsUI.ICON_ASK);
					}else if(textSearchTimeMonthFrom.getText().trim().equals("")){
						JOptionPane.showMessageDialog(null,"请输入搜索时间起始月份。","注意", JOptionPane.PLAIN_MESSAGE, ConstantsUI.ICON_ASK);					 
					}else{
						 textSearchOrderId.setText("");
						 textSearchName.setText(""); 
						model.setRowCount(0);
						
//						System.out.println(textSearchTimeMonthFrom.getText().trim());
						if(textSearchTimeMonthTo.getText().trim().equals("")){
//						 monthTo = textSearchTimeMonthFrom.getText().trim();
						 strSQL ="SELECT * FROM accounts WHERE year  = \""+ textSearchTimeYear.getText().trim() 
								 +"\" AND  month BETWEEN  \""+ textSearchTimeMonthFrom.getText().trim() +"\" AND \""+ textSearchTimeMonthFrom.getText().trim() 
								 +"\" ORDER BY case when PAYMETHOD = '信用卡' then 0 else 1 end,case when ISPRIVATE  = '店内' then 0 else 1 end ASC" ;
						}else{
						 strSQL ="SELECT * FROM accounts WHERE year  = \""+ textSearchTimeYear.getText().trim() 
									 +"\" AND  month BETWEEN  \""+ textSearchTimeMonthFrom.getText().trim() +"\" AND \""+ textSearchTimeMonthTo.getText().trim() 
									 +"\"  ORDER BY case when PAYMETHOD = '信用卡' then 0 else 1 end,case when ISPRIVATE   = '店内' then 0 else 1 end ASC" ;
						}
//						
//						DateSplit dateSplit2 = new DateSplit(datepickTo);
//						String [] dateTo = dateSplit2.Splitt();
//						String yearB = dateTo[0];
//						String monthB = dateTo[1];
//						String dayB = dateTo[2];
						
						
						
						rs= db.getResult(strSQL);
						//int c = rs.getRow();
					    //if(rs.is){
						while(rs.next()){
//							//rowData 可以存放多行
							Vector hang =new Vector(); 
							hang.add(rs.getString(1));
							hang.add(rs.getString(2));  
							hang.add(rs.getString(3));  
							hang.add(rs.getString(4));
							hang.add(rs.getString(7));
							hang.add(rs.getString(8)); 
							hang.add(rs.getString(9));  
							hang.add(rs.getString(5));						
							hang.add(rs.getString(6));  
							hang.add(rs.getString(10));  
							
							dataRow.add(hang);
							}
						 //model.setRowCount(100);
						 //model.addRow(dataRow);
						if(!dataRow.isEmpty()){
//							
//							for (int i = 0; i < hang.size(); i++) {  
//					            System.out.print(hang.get(i));   
//					        }  
						
//						dataRow = db.getRowResult(sqlstr);
						
						model.setDataVector(dataRow,dataColumn);
						FitTableColumns( dataTable);
//						dataTable.setModel(model);
						}else{
					    	JOptionPane.showMessageDialog(null,"没有搜索到相关记录。","注意", JOptionPane.PLAIN_MESSAGE, ConstantsUI.ICON_ASK);
					    }
					}
					}catch(Exception sqle){
						 AppMainWindow.logger.error(sqle.toString());
						sqle.printStackTrace();
					}
				}
				
			});
    	
    	textSearchTimeYear.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				textSearchTimeYear.setText(yearA);
				textSearchTimeYear.setFont(new Font("宋体", Font.PLAIN,18));
				textSearchTimeYear.setForeground(Color.BLACK);
		        
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
			
    	}); 
    	
    	textSearchTimeMonthFrom.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				textSearchTimeMonthFrom.setText("");
				textSearchTimeMonthFrom.setFont(new Font("宋体", Font.PLAIN,18));
				textSearchTimeMonthFrom.setForeground(Color.BLACK);
		        
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
			
    	}); 
    	
       	textSearchTimeMonthTo.addFocusListener(new FocusListener(){

    			@Override
    			public void focusGained(FocusEvent arg0) {
    				// TODO Auto-generated method stub
    				textSearchTimeMonthTo.setText(monthA);
    				textSearchTimeMonthTo.setFont(new Font("宋体", Font.PLAIN,18));
    				textSearchTimeMonthTo.setForeground(Color.BLACK);
    		        
    			}

    			@Override
    			public void focusLost(FocusEvent e) {
    				// TODO Auto-generated method stub
    				
    			}
    			
        	}); 
       	
       	exportResults.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
//				String monthTo;
				try{
//					File csvFile = null;
//			        BufferedWriter csvWtriter = null;
//			        String filename = textSearchOrderId.getText() +textSearchName.getText() 
//			        		+textSearchTimeYear.getText() + textSearchTimeMonthFrom.getText() + "--" +textSearchTimeMonthTo.getText();
//			            csvFile = new File(ConstantsUI.CURRENT_DIR + File.separator + filename +".xls");
//			            File parent = csvFile.getParentFile();
//			            if (parent != null && !parent.exists()) {
//			                parent.mkdirs();
//			            }
//			            csvFile.createNewFile();

			         exTable = new ExportTable(dataTable);
			         exTable.export();
					 
//					 String[] cmd = new String[5];    
//					 String url =ConstantsUI.CURRENT_DIR + File.separator ;
//					 cmd[0] = "cmd";
//					 cmd[1] = "/c";
//					 cmd[2] = "start";
//					 cmd[3] = " ";
//					 cmd[4] = url;
//					 
//					 Runtime.getRuntime().exec(cmd);
					
				}catch(Exception sqle){
					 AppMainWindow.logger.error(sqle.toString());
					sqle.printStackTrace();
				}
			}
			
		});
				
    	
    }
}
