package dataStore;

import util.Debug;

public final class StudentInfo {

	private String firstName;
	private String lastName;
	private String instructor;
	private int course_number;
	
	/**
	 * default constructor
	 */
	public StudentInfo(){
		firstName = "";
		lastName = "";
		instructor = "";
		course_number = -1;
		
		Debug.dump(4, "dump StudentInfo()");
	}
	
	/**
	 * Constructor
	 * Initialized object with information obtained by the data file 
	 * 
	 * @param first
	 * @param last
	 * @param inst
	 * @param num
	 */
	public StudentInfo(String first, String last, String inst, int num){
		firstName = first;
		lastName = last;
		instructor = inst;
		course_number = num;
		
		Debug.dump(4, "dump StudentInfo(String first, String last, String inst, int num)");
	}
	
	
	/**
	 * returns the first name of the student
	 * 
	 * @return String
	 */
	public String getFirstName(){
		return firstName;
		
	}
	
	/**
	 * modifier that sets the first name of the student
	 * 
	 * @param name
	 */
	public void setFirstName(String name){
		firstName = name; 
		
	}
	
	/**
	 * returns the last name of the student
	 * 
	 * @return String
	 */
	public String getLastName(){
		return lastName;
		
	}
	
	/**
	 * modifier that sets the last name of the student
	 * 
	 * @param name
	 */
	public void setLastName(String name){
		lastName = name;
		
	}
	
	/**
	 * return the name of the instructor of the student
	 * 
	 * @return String
	 */
	public String getInstructor(){
		return instructor;
		
	}

	/**
	 * modifier that sets the instructor name
	 * 
	 * @param name
	 */
	public void setInstructor(String name){
		instructor = name;
		
	}
	
	/**
	 * returns the course number the student is taking
	 * 
	 * @return int
	 */
	public int getCourseNumber(){
		return course_number;
		
	}

	/**
	 * modifier that sets the course number that student is taking
	 * 
	 * @param number
	 */
	public void setCourseNumber(int number){
		course_number = number;
		
	}
	
	/**
	 * checks if the object passed is equal to the current object
	 * 
	 * @param info
	 * @return boolean
	 */
	public boolean isEqual(StudentInfo info){
		boolean isEqual = false;
		
		if(firstName.equals(info.firstName) && lastName.equals(info.lastName) 
				&& instructor.equals(info.instructor) && (course_number == info.course_number)){
			
			isEqual = true;
		}
		return isEqual;
	}
	
	/***
	 * Checks if the current object contains the string passed into the method
	 * 
	 * @param info
	 * @return boolean
	 */
	public boolean containsName(String info){
		boolean isEqual = false;
		
		if(firstName.equals(info) || lastName.equals(info) || instructor.equals(info)){
			isEqual = true;			
		}
		return isEqual;
	}
	
	
	/**
	 * toString method
	 * returns the string that contains the first name and last name of the student, the instructor, and the course number
	 * 
	 * @return String
	 */
	public String toString(){
		return firstName+ " " + lastName+ " " + instructor + " " + course_number;
	}
}
