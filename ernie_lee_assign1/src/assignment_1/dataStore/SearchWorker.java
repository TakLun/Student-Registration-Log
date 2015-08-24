package dataStore;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

import util.Debug;
import util.Results;
import util.FileRead;

public class SearchWorker implements Runnable, FileRead{
	
	private String searchFile;
	private int MM;
	private PopulateWorker pop_worker;
	private Results results_store;
	private BufferedReader br;
	private Vector<String> ifRead;
	
	/**
	 * default constructor
	 */
	public SearchWorker(){
		searchFile = "";
		MM = 0;
		pop_worker = new PopulateWorker();
		results_store = new Results(100);
		ifRead = new Vector<String>(100);
		
		Debug.dump(4, "dump SearchWorker()");
	}
	
	/**
	 * Constructor
	 * 
	 * Initializes the SearchWorker object with the file, number of threads, and the data structure to be searched
	 * 
	 * @param file
	 * @param MM
	 * @param student_store
	 */
	public SearchWorker(String file, int MM, PopulateWorker student_store){
		
		Debug.dump(4, "dump PopulateWorker(String file, int MM, PopulateWorker student_store)");
		
		searchFile = file;
		this.MM = MM;
		pop_worker = student_store;
		results_store = new Results(100);
		ifRead = new Vector<String>(100);
		
		start();
	}
	
	/**
	 * Constructor
	 * 
	 * Initializes the SearchWorker object for the thread to run by with the file, the data structure to be searched, 
	 * the data structure to store matching entries, and the vector that checks is line in file is already read 
	 * 
	 * @param file
	 * @param reg_store
	 * @param results_store
	 * @param strRead
	 */
	public SearchWorker(String file, PopulateWorker reg_store, Results results_store, Vector<String> strRead){
		
		Debug.dump(4, "dump PopulateWorker(String file, PopulateWorker reg_store, Results results_store, Vector<String> strRead) Thread Constructor");
		
		searchFile = file;
		this.pop_worker = reg_store;
		this.results_store = results_store;
		ifRead = strRead;
	}
	
	/**
	 * method that starts to search the data structure that stores student information
	 */
	private void start(){
		try{
			Thread t[] = new Thread[MM];
			
			for(int i=0;i<MM;i++){
				t[i] = new Thread(new SearchWorker(searchFile, pop_worker, results_store, ifRead));
				t[i].start();
			}
			
			for(int i=0;i<MM;i++){
				t[i].join();
			}
			
		}catch(InterruptedException e){
			e.printStackTrace();
			System.exit(0);			
		}
		
		
	}
	
	/**
	 * Searches the data structure that stores the student information and inserts matching entries into a results data structure 
	 * 
	 * @param line
	 */
	private void search(String line){
		try{
			
			Debug.dump(1, "dump search " + line);
			
			if(pop_worker.getRegistrationStore().containsString(line)){
				results_store.add(pop_worker.getRegistrationStore().getStudentContainsString(line));
			}
		}catch(NullPointerException e){
			e.printStackTrace();
			System.exit(0);			
		}
	}
	
	@Override
	public void readFile(){
		try {
			String line = "";
			
			while((line=br.readLine()) != null){
				synchronized (ifRead) {
					if(!ifRead.contains(line)){
						ifRead.add(line);
						search(line);
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
			Debug.dump(3, "dump SearchWorker Thread");
			
			br = new BufferedReader(new FileReader(searchFile));
			readFile();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	/**
	 * toString method
	 * 
	 * returns a string that contains the matching student information 
	 * 
	 * @return String
	 */
	public String toString(){
		return results_store.toString();		
	}
}
