package tools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.TableModel;

import org.apache.poi.*;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import UI.AppMainWindow;
import UI.ConstantsUI;


/**
* Java中将JTable中的数据导出到Excel中
* @author windpower3
*
*此类导入了5个包:poi-3.8-20120326.jar 
* poi-examples-3.8-20120326.jar
poi-ooxml-3.8-20120326.jar
poi-ooxml-schemas-3.8-20120326.jar
poi-scratchpad-3.8-20120326.jar 
*/
public class ExportTable {
JTable table;
FileOutputStream fos;
File file;
JFileChooser jfc=new JFileChooser();

public ExportTable(JTable table){
if(table.getRowCount()==0){
JOptionPane.showMessageDialog(table,"表格内容不能为空");
return;

}
this.table=table;
jfc.addChoosableFileFilter(new FileFilter() {

@Override
public String getDescription() {

return "Excel";
}

@Override
public boolean accept(File f) {
return f.getName().indexOf("xls")!=-1;
}
});

jfc.showSaveDialog(null);
file=jfc.getSelectedFile();

try{
this.fos=new FileOutputStream(file+".xls");
}catch(FileNotFoundException ex){
	 AppMainWindow.logger.error(ex.toString());
ex.printStackTrace();
}
}


public void export(){
//判断有没有选择文件
if(file==null){
JOptionPane.showMessageDialog(null, "没有选择要导出的文件路径","注意", JOptionPane.PLAIN_MESSAGE, ConstantsUI.ICON_ASK);
return;
}
//判断文件有没有被别的程序占用
//if (!file.renameTo(file)) {
//JOptionPane.showMessageDialog(null,
//"该表单号文件正在被别的程序占用，请先关闭该文件解除占用");
////return;
//}
HSSFWorkbook wb=new HSSFWorkbook();
HSSFSheet hs=wb.createSheet();
TableModel tm=table.getModel();
int row=table.getRowCount();
int column=table.getColumnCount();
//System.out.println("行："+row+" 列："+column);
HSSFCellStyle style=wb.createCellStyle();
style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
style.setBorderRight(HSSFCellStyle.BORDER_THIN);
style.setBorderTop(HSSFCellStyle.BORDER_THIN);
style.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
HSSFFont font=wb.createFont();
font.setFontHeightInPoints((short)11);
style.setFont(font);

HSSFCellStyle style1=wb.createCellStyle();
style1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
style1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
style1.setBorderRight(HSSFCellStyle.BORDER_THIN);
style1.setBorderTop(HSSFCellStyle.BORDER_THIN);
style1.setFillForegroundColor(HSSFColor.ORANGE.index);
style1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

HSSFFont font1 = wb.createFont();
font1.setFontHeightInPoints((short) 11);
font1.setBoldweight((short) 700);
style1.setFont(font1);

for(int i=0;i<row+1;i++){
HSSFRow hr=hs.createRow(i);
hr.setHeight((short) 500);
for (int j = 0; j < column; j++) {
if(i==0){
String value=tm.getColumnName(j);
// System.out.println("value " + value);
int len=value.length();
hs.setColumnWidth(j, len*1900);
HSSFRichTextString strs=new HSSFRichTextString(value);
HSSFCell hc=hr.createCell(j);
// hc.setEncoding((short) 1);
hc.setCellStyle(style1);
hc.setCellValue(strs); 
}else{
// System.out.println("vlue " + tm.getValueAt(i - 1, j));
if(tm.getValueAt(i-1, j)!=null){
String value=tm.getValueAt(i-1, j).toString();
HSSFRichTextString strs=new HSSFRichTextString(value);
HSSFCell hc=hr.createCell(j);
hc.setCellStyle(style);
if(value.equals("") || value==null){
hc.setCellValue(new HSSFRichTextString(""));
}else{
hc.setCellValue(strs);
}
}
}
}
}
try{
wb.write(fos);
JOptionPane.showMessageDialog(null, "导出成功","注意", JOptionPane.PLAIN_MESSAGE, ConstantsUI.ICON_ASK);

}catch(IOException e){
	AppMainWindow.logger.error(e.toString());
JOptionPane.showMessageDialog(null, "导出失败","注意", JOptionPane.PLAIN_MESSAGE, ConstantsUI.ICON_ASK);
//阻止下面的代码执行
throw new RuntimeException(e);

}finally{
try {
if(fos!=null){
fos.close();
//System.out.println("资源关闭成功。。。");
}
} catch (IOException e) {
	 AppMainWindow.logger.error(e.toString());
//System.out.println("资源关闭失败。。。");
throw new RuntimeException(e);
}
}
}
}