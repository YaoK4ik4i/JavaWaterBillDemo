package tools;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;

public class TestSQLiteJDBC
{
  public static void main( String args[] )
  {
    SqliteBaseManager db = new SqliteBaseManager(); 
    ResultSetMetaData m=null;
    boolean f = false;
  
    try {
     //String strSQL ="insert into accounts (orderid,year,month,day,paymethod,isprivate,itemname,itemnumber,price,remarks)" +
     //"values (?,?,?,?,?,?,?,?,?,?) , (,'2018','05','16','cash','否','qq币', '1','30','123');";
      
      //f =db.insertTable(new Accounts(null,"2018","05","16","cash","否","qq币", "1","98","123"));   //利用bean插入数据库数据
      //System.out.print(f + "\n");
      
      //String strSQL2  = ".schema ACCOUNTS;";
      
      String strSQL = "drop table if exists accounts";
      
      db.setTable(strSQL);
      //String strSQL2 = "select * from accounts  where itemname like \"%奶%\"   ";

      //ResultSet rs = db.getResult(strSQL2);
      //m=rs.getMetaData();     //获取 列信息
      
//     // int columns=m.getColumnCount();
//      for(int i=1;i<=columns;i++)     //显示列,表格的表头
//      {
//       System.out.print(m.getColumnName(i));
//       System.out.print("\t\t");
//      }
//      System.out.println(columns);
//     while ( rs.next() ) {
//       for(int i=1;i<=columns;i++)    //显示表格内容
//          {
//           System.out.print(rs.getString(i));
//           System.out.print("\t\t");
//          }
//          System.out.println();
//      }
//      rs.close();
      
      db.closeConnection();
    } catch ( Exception e ) {
      System.err.println( e.toString() );
     e.printStackTrace();
      System.exit(0);
    }
    System.out.println("Operation done successfully");
  }
}