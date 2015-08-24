package dataStore;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

import util.Debug;
import util.FileRead;

public class PopulateWorker implements Runnable, FileRead {

	private String dataFile;
	private int NN;
	private RegistrationStore store;
	private BufferedReader br;
	private Vector<String> ifRead;
	
	/**
	 * default constructor
	 */
	public PopulateWorker(){
		dataFile = "";		
		store = null;
		store = new RegistrationStore(100);
		ifRead = new Vector<String>(100);
		
		Debug.dump(4, "dump PopulateWorker()");
	}
	
	/**
	 * Initializes the PopulateWorker object with the file, and the number of threads
	 * 
	 * @param file
	 * @param NN
	 */
	public PopulateWorker(String file, int NN){
		
		Debug.dump(4, "dump PopulateWorker(String file, int NN)");
		
		dataFile = file;
		this.NN = NN;
		store = new RegistrationStore(100);
		ifRead = new Vector<String>(100);
		
		start();
	}
	
	/**
	 * Initializes the PopulateWorker object for the thread to run by with the file, the data structure to insert student information, 
	 * and the vector that checks is line in file is already read
	 * 
	 * @param file
	 * @param student_store
	 * @param strRead
	 */
	
	private PopulateWorker(String file, RegistrationStore student_store, Vector<String> strRead){
		
		Debug.dump(4, "dump PopulateWorker(String file, RegistrationStore student_store, Vector<String> strRead) Thread Constructor");
		
		dataFile = file;
		store = student_store;
		ifRead = strRead; 
		
	}
	
	/**
	 * method that starts to insert student information into the data structure.
	 */
	private void start(){

		try {
			Thread t[] = new Thread[NN];
			
			for(int i=0;i<NN;i++){
				t[i] = new Thread(new PopulateWorker(dataFile, store, ifRead));
				t[i].start();
			}
			
			for(int i=0;i<NN;i++){
				t[i].join();
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
	}
	
	/**
	 * returns the data structure that contains the student information
	 * 
	 * @return RegistrationStore
	 */
	public RegistrationStore getRegistrationStore(){
		return store;
	}
	
	/**
	 * Reads the line taken from the data file and parses it. 
	 * The strings that were parsed from the line is set into a StudentInfo object 
	 * which is added to the student information data structure
	 * 
	 * @param line
	 * @return StudentInfo
	 */
	private StudentInfo getInfo(String line){
		StudentInfo student = new StudentInfo();
		
		try{
			String data[] = line.split(" ");
			
			student.setFirstName(data[0]);
			student.setLastName(data[1]);
			student.setInstructor(data[2]);
			student.setCourseNumber(Integer.parseInt(data[3]));
			
			store.add(student);
		
		}catch(NullPointerException e){
			e.printStackTrace();
			System.exit(0);
		}
		return student;
	}
	
	@Override
	public void readFile() {
		try {
			String line = "";
			
			while((line=br.readLine()) != null){
				synchronized (ifRead) {
					if(!ifRead.contains(line)){
						ifRead.add(line);
						getInfo(line);
					}	
				}
			}
			
			br.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
			
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
			
		}
		
	}
	
	@Override
	public void run() {
		try {
			
			Debug.dump(3, "dump PopulationWorker Thread");
			
			br = new BufferedReader(new FileReader(dataFile));
			readFile();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
	}
	
	/**
	 * toString method
	 * 
	 * returns a string that contains the student information that is stored in the data structure
	 * 
	 * @return String
	 */
	public String toString(){
		return store.toString();
	}
}
