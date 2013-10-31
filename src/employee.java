import java.util.Date;

/**
 * This class is represents an employee
 * 
 * @author Austin
 *
 */
public class employee {
	private String firstName, lastName, employeeRole;
	private int empId;
	private String pin;
	//TODO: consider adding address into employee object, but only if needed
	private boolean isActive; // This can be used for future purposes, but lise address will not be used in main constructor
	private Date hireDate, termDate; //These are just here in case someone were to need them (use getters and setters or build alternate constructor)
	
	
	/**
	 * Constructor
	 * @param empId
	 * @param firstName
	 * @param lastName
	 * @param employeeRole
	 * @param pin
	 */
	public employee(int empId, String firstName, String lastName, String employeeRole, String pin){
		
		setEmpId(empId);
		setFirstName(firstName);setLastName(lastName);
		setEmployeeRole(employeeRole);
		setPin(pin);
		setActive(isActive);
		
	}



	//Getters and setters
	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getEmployeeRole() {
		return employeeRole;
	}



	public void setEmployeeRole(String employeeRole) {
		this.employeeRole = employeeRole;
	}



	public int getEmpId() {
		return empId;
	}



	public void setEmpId(int empId) {
		this.empId = empId;
	}



	public String getPin() {
		return pin;
	}



	public void setPin(String pin) {
		this.pin = pin;
	}



	public Date getHireDate() {
		return hireDate;
	}



	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}



	public Date getTermDate() {
		return termDate;
	}



	public void setTermDate(Date termDate) {
		this.termDate = termDate;
	}



	public boolean isActive() {
		return isActive;
	}



	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}




}

