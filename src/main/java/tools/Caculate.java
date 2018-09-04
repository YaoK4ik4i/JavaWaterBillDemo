package tools;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Vector;

import UI.AppMainWindow;


public class Caculate {
	
	
	Vector <String> min;
	float i ;
	BigDecimal b;
	float j;
	public Caculate(){
		min = new  Vector <String>();
		i = 0;
	}
	
	public  Vector<Object>  getStatisticsResult(ResultSet rs){
		 Vector <Object> rowData = new  Vector <Object>();
		 
		 try{
		 while(rs.next()){
//				//rowData 可以存放多行
				Vector hang=new Vector<String>(); 
				hang.add(rs.getString(1));
				hang.add(rs.getString(7));
				hang.add(rs.getString(8)); 
				hang.add(rs.getString(9));
				hang.add(rs.getString(10));  
				
				min.add(rs.getString(9));
				rowData.add(hang);
				}
		return  rowData;
	 }catch (SQLException sqle) {
		 AppMainWindow.logger.error(sqle.toString());       //数据库异常信息
			return null;
		}
	 }
	 
	 public  float  getSumResult(){		
		
		 
		 try{
				 
		 Iterator<String> ite=min.iterator();
		 while(ite.hasNext())//判断下一个元素之后有值
	     {
	        i += Float.parseFloat(ite.next());
	     }
		 b = new BigDecimal(i);
		 j =  b.setScale(2,  BigDecimal.ROUND_HALF_UP).floatValue();    //德西码型四舍五入
		return  j;
	 }catch (Exception sqle) {
		 AppMainWindow.logger.error(sqle.toString());       //数据库异常信息
			return  0;
		}
	 }

}
