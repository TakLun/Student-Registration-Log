package driver;

import dataStore.PopulateWorker;
import dataStore.SearchWorker;

import util.Debug;

public class driver{
	public static void main(String args[]){
		
		try{
			String inputDataFileName = args[0];
			String NN_threads = args[1];
			String searchFileName = args[2];
			String MM_threads = args[3];
			String debug = args[4];
		
			int NN = Integer.parseInt(NN_threads);
			int MM = Integer.parseInt(MM_threads);
			Debug.setDebugValue(Integer.parseInt(debug));
		
			PopulateWorker populateWorker = new PopulateWorker(inputDataFileName, NN);
			SearchWorker searchWorker = new SearchWorker(searchFileName, MM, populateWorker);
			System.out.println(searchWorker);
			
		}catch(NumberFormatException e){
			e.printStackTrace();
			System.exit(0);
		}
	}
}
