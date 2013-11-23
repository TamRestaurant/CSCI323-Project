import javax.swing.JComboBox;

/***
 * This class populates month, day, year arrays (for combo boxes, but could be adapted for other purposes)
 * Split into separate class to use for multiple purposes
 * 10.26.2013
 * 
 * @author Austin
 *
 */
public class DateComboFill {

	private String[] states = {"AL","AK","AZ","AR","CA","CO","CT","DE","FL","GA","HI","ID","IL","IN","IA","KS","KY","LA","ME","MD","MA","MI","MN","MS","MO","MT","NE","NV","NH","NJ","NM","NY","NC","ND","OH","OK","OR","PA","RI","SC","SD","TN","TX","UT","VT","VA","WA","WV","WI","WY"};
	private String[] day = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};

	public DateComboFill(){}
	
	
	public boolean updateDayCombo(JComboBox comboBoxMonth,JComboBox comboBoxDay,JComboBox comboBoxYear){
		boolean isSuccess = false;
		
		String tempMonth = comboBoxMonth.getSelectedItem().toString();

		if (tempMonth == "January" || tempMonth == "March" || tempMonth == "May" || tempMonth == "July" || tempMonth == "August " || tempMonth == "October" || tempMonth == "December"){	
			comboBoxDay.removeAllItems();
			for (int i = 0; i < 31; i++){
				comboBoxDay.addItem(day[i]);
			}

		}
		else if(tempMonth == "April" || tempMonth == "June" || tempMonth == "September" || tempMonth == "Novermber"){
			comboBoxDay.removeAllItems();

			for (int i = 0; i < 30; i++){
				comboBoxDay.addItem(day[i]);
			}
		}
		else{//FEB
			try{
			int year = Integer.parseInt(comboBoxYear.getSelectedItem().toString());
			
			if (isLeapYear(year)){
				for (int i = 0; i < 29; i++){
					comboBoxDay.addItem(day[i]);
				}
			}
			else{//Not leap year
				for (int i = 0; i < 28; i++){
					comboBoxDay.addItem(day[i]);
				}
			}
			isSuccess = true;
			
			}//close try
			
			catch(Exception e){
				

				//comboBoxDay = new JComboBox(day);
				comboBoxDay.removeAllItems();
				//labelError.setText("Year Exception: non-leap year selected without calculation.");
				for (int i = 0; i < 28; i++){
					comboBoxDay.addItem(day[i]);
					return isSuccess;
				}
			}//close catch
			
			//Let calling method know if success
			

		}//close Feb
		return isSuccess;
		
	}// close update month
	
	
	private boolean isLeapYear(int year){
		boolean temp = false;
		
		if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)){
			temp = true;
		}
		
		return temp;
		
	}
	
	
	
}