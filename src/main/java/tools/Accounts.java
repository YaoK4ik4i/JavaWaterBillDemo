package tools;

public class Accounts {
	public String AccountId;
	private String Year;
	private String Month;
	private String Day;
	private String PayMethod;
	private String IsPrivate;
	private String ItemName;
	private String ItemNumber;
	private String Price;
	private String Remarks;
	
	public Accounts (String AccountId,String Year, String Month, String Day,String PayMethod,String IsPrivate,String ItemName,String ItemNumber,String Price, String Remarks)
	{
		this.AccountId = null; //default
		this.Year = Year;
		this.Month = Month;
		this.Day = Day;
		this.PayMethod = PayMethod;
		this.IsPrivate = IsPrivate;
		this.ItemName = ItemName;
		this.ItemNumber = ItemNumber;
		this.Price = Price;
		this.Remarks = Remarks;

	}
	
	public String getAccountId(){
		return AccountId;
	}

	public void setAccountId(String sId){
		this.AccountId = sId;
	}

	public String getYear(){
		return Year;
	}

	public void setYear(String sYear){
		this.Year = sYear;
	}

	public String getMonth(){
		return Month;
	}

	public void setMonth(String sMonth){
		this.Month = sMonth;
	}

	public String getDay(){
		return Day;
	}

	public void setDay(String sDay){
		this.Day = sDay;
	}

	public String getPayMethod(){
		return PayMethod;
	}

	public void setPayMethod(String sPayMethod){
		this.PayMethod = sPayMethod;
	}

	public String getIsPrivate(){
		return IsPrivate;
	}

	public void setIsPrivate(String sIsPrivate){
		this.IsPrivate = sIsPrivate;
	}

	public String getItemName(){
		return ItemName;
	}

	public void setItemName(String sItemName){
		this.ItemName = sItemName;
	}

	public String getItemNumber(){
		return ItemNumber;
	}

	public void setItemNumber(String sItemNumber){
		this.ItemNumber = sItemNumber;
	}

	public String getPrice(){
		return Price;
	}

	public void setPrice(String sPrice){
		this.Price = sPrice;
	}

	public String getRemarks(){
		return Remarks;
	}

	public void setRemarks(String sRemarks){
		this.Remarks = sRemarks;
	}
	
}


