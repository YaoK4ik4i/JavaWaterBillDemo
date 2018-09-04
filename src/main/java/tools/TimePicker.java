package tools;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.text.DateFormat;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

import com.github.lgooddatepicker.components.*;;


public class TimePicker {
	public static void main(String[] args) {
	 JFrame f = new JFrame("LoL");
     f.setSize(400, 300);
     f.setLocation(200, 200);
     f.setLayout(null);
     DatePicker datepick;
     DatePickerSettings datepicksetting;

     datepicksetting = new DatePickerSettings();
     datepicksetting.setAllowEmptyDates(false);

    datepick = new DatePicker(datepicksetting);
     
     //datepick.setFormats(DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.CHINA));
    // datepick.setUI(new BasicDatePickerUI());
//     DateFormat[] Formate = datepick.getFormats();
//     for(int i = 0;i<Formate.length;i++){
//    	 System.out.println(Formate[i]);
//    	 
//     }
     
     // 设置 date日期
    // datepick.setDate(date);
    // datepick.setLightWeightPopupEnabled(false);

     datepick.setBounds(137, 83, 177, 24);

     f.add(datepick);

     JButton b = new JButton("获取时间");
     b.setBounds(137, 183, 100, 30);
     f.add(b);

     b.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
             // 获取 日期
        	 String d = datepick.getDateStringOrEmptyString();
            //String df = (new SimpleDateFormat("yyyy-MM-dd")).format(d);
             JOptionPane.showMessageDialog(f, "获取控件中的日期 :" + d);

         }
     });

     f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

     f.setVisible(true);
 }

}
