package tools;

import com.github.lgooddatepicker.components.DatePicker;

/**
 * 
 */
public class DateSplit extends DatePicker {
	DatePicker datePicker;
	String [] Date;
	String localDate;
	private static final long serialVersionUID = 1L;
	public DateSplit (DatePicker datePicker){
		this.datePicker = datePicker;
		
	}
	
	public String[] Splitt (){
		localDate = datePicker.getDateStringOrEmptyString();
		Date= localDate.split("-");    	
		return Date;
		
	}
}
