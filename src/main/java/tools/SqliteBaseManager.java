package tools;

import java.awt.Container;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;

import UI.AppMainWindow;

public class SqliteBaseManager {
	//创建成员变量
	Connection con;					//数据库连接对象
	ResultSet rs;					//数据集对象
	Statement stmt;    				//数据状态对象
	PreparedStatement pstmt = null ;	// 数据库操作
	Vector <Object> rowData = new Vector<Object>();   //列数据 Vector集合
	Vector hang ;
	List<Accounts> list;
	public SqliteBaseManager(){		//构造函数
		try{
			//加载驱动器
			Class.forName("org.sqlite.JDBC");
			//获取数据库连接对象
			con = DriverManager.getConnection("jdbc:sqlite:"+ ConstantsTools.SQLITE_DATABASE);
			//数据状态对象
		    stmt = con.createStatement();
		  
		    
		    String sql="CREATE TABLE IF NOT EXISTS ACCOUNTS" + 
                      "(ORDERID  INTEGER PRIMARY KEY ," +   //sqlite 自增不能用auto_increment 要用INTEGER
                       "YEAR CHAR(50)  NOT NULL,"+
					   "MONTH CHAR(50)  NOT NULL," + 
                       "DAY CHAR(50)  NOT NULL," + 
                       "PAYMETHOD CHAR(150) NOT NULL ," +
                       "ISPRIVATE CHAR(50) NOT NULL ," +
                       "ITEMNAME CHAR(150) NOT NULL ," +
                       "ITEMNUMBER CHAR(50)  NOT NULL," +
                       "PRICE CHAR(50)  NOT NULL," +
                       "REMARKS CHAR(150) )"; 
			stmt.executeUpdate(sql);
			
      		
		} catch (ClassNotFoundException cnfex) {
			//抛出异常
			 AppMainWindow.logger.error("Failed to load JDBC/sqlitebc driver.");
			cnfex.printStackTrace();
			System.exit(1);
		} catch (SQLException sqle) {
			AppMainWindow.logger.error(sqle.toString());       //数据库异常信息
		}
	}

	public boolean setTable (String strSQL){
		boolean i = false ;
		try{
			String sql= strSQL;
			
//			stmt.executeUpdate(sql);
			if(stmt.executeUpdate(sql) >= 0){
				i = true;
				AppMainWindow.logger.info("Table has been changed successfull !");
			}else{
				i = false;
			}
		}catch (SQLException sqle){
			AppMainWindow.logger.error(sqle.toString()); 
			sqle.printStackTrace();
		} 
		return i;
	}
	
	public boolean insertTable(Accounts account){
		 boolean i = false ;
		String sql = "insert into accounts (orderid,year,month,day,paymethod,isprivate,itemname,itemnumber,price,remarks)" +
			     "values (?,?,?,?,?,?,?,?,?,?);";
		try{
			pstmt = (PreparedStatement) con.prepareStatement(sql);
			pstmt.setString(1, account.getAccountId());
			pstmt.setString(2, account.getYear());
			pstmt.setString(3, account.getMonth());
			pstmt.setString(4, account.getDay());
			pstmt.setString(5, account.getPayMethod());
			pstmt.setString(6, account.getIsPrivate());
			pstmt.setString(7, account.getItemName());
			pstmt.setString(8, account.getItemNumber());
			pstmt.setString(9, account.getPrice());
			pstmt.setString(10, account.getRemarks());
			if(pstmt.executeUpdate() >= 0){
				i = true;
			}else{
				i = false;
			}
			pstmt.close();
			
			
		}catch (SQLException e){
			e.printStackTrace();
			AppMainWindow.logger.error(e.toString()); 
		}
		return i;
	}

	public ResultSet getResult(String strSQL) {      //获取数据集对象
		try{
			//pstmt = (PreparedStatement) con.prepareStatement(strSQL);
			rs = stmt.executeQuery(strSQL);
			//pstmt.close();
			return rs;

			
		}catch (SQLException sqle) {
			AppMainWindow.logger.error(sqle.toString());       //数据库异常信息
			return null;
		}
	}
	
	public Vector<Object>  getRowResult(String strSQL) {      //获取数据集对象
//		list=new ArrayList <Accounts>();

		try{
			//pstmt = (PreparedStatement) con.prepareStatement(strSQL);
			rs = stmt.executeQuery(strSQL);
//			int col = rs.getMetaData().getColumnCount();
//		    if(col >0){
//			JOptionPane.showMessageDialog(null, "已按要求检索账目并列出");
				while(rs.next()){
//					//rowData 可以存放多行
					hang=new Vector <String> (); 
					hang.add(rs.getString(1));
					hang.add(rs.getString(2));  
					hang.add(rs.getString(3));  
					hang.add(rs.getString(4));  
					hang.add(rs.getString(5));  
					hang.add(rs.getString(6));  
					hang.add(rs.getString(7));  
					hang.add(rs.getString(8));  
					hang.add(rs.getString(9));  
					hang.add(rs.getString(10));  
					
					rowData.add(hang);
					
//					Accounts accounts = new Accounts(null,null,null,null,null,null,null,null,null,null);
//
//					accounts.AccountId = rs.getString("orderid");
//					accounts.setYear(rs.getString("year")) ;
//					accounts.setMonth(rs.getString("month")) ;
//					accounts.setDay(rs.getString("day")) ;
//					accounts.setPayMethod(rs.getString("paymethod")) ;
//					accounts.setIsPrivate(rs.getString("isprivate")) ;
//					accounts.setItemName(rs.getString("itemname")) ;
//					accounts.setItemNumber(rs.getString("itemnumber")) ;
//					accounts.setPrice(rs.getString("price")) ;
//					accounts.setRemarks(rs.getString("remarks")) ;
//
//					
//
//					list.add(accounts);
//					}
//		    }
//			pstmt.close();
//			 
//			System.out.println("list数据有" + list.size() + "条");
//			for(int i=0;i<list.size();i++){
//	            for(int j=0;j<col;j++){
//	                hang.add(list.get(i).getAccountId());
//	                hang.add(list.get(i).getYear());
//	                hang.add(list.get(i).getMonth());
//	                hang.add(list.get(i).getDay());
//	                hang.add(list.get(i).getPayMethod());
//	                hang.add(list.get(i).getIsPrivate());
//	                hang.add(list.get(i).getItemName());
//	                hang.add(list.get(i).getItemNumber());
//	                hang.add(list.get(i).getPrice());
//	                hang.add(list.get(i).getRemarks());
//	            }
//	            rowData.add(hang);
//	        }
					

				}
				return  rowData;
			

			
		}catch (SQLException sqle) {
			AppMainWindow.logger.error(sqle.toString());       //数据库异常信息
			return null;
		}
	}



	public boolean updateSql( String strSQL) {		//实现更新数据方法
		try{
			stmt.executeUpdate(strSQL);		
			con.commit();							//执行提交
			return true;
		} catch (SQLException sqle) {
			AppMainWindow.logger.error(sqle.toString());       //数据库异常信息
			return false;
		}
	}

	public void closeConnection() {
		try{
			con.close();
		}catch (SQLException sqle){
			AppMainWindow.logger.error(sqle.toString());       //数据库异常信息
		}
	}	
}
