package dataStore;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;

import util.Debug;

public class RegistrationStore implements List<StudentInfo>{

	private List<StudentInfo> infoStore;
	
	/**
	 * default constructor
	 */
	public RegistrationStore(){
		infoStore = new Vector<StudentInfo>();
		
		Debug.dump(4, "dump RegistrationStore()");
	}
	
	/**
	 * Constructor
	 * Initializes the data structure that stores StudentInfo entries
	 * 
	 * @param i
	 */
	public RegistrationStore(int i){
		infoStore = new Vector<StudentInfo>(i);
		
		Debug.dump(4, "dump SearchWorker(int i)");

	}
	
	/**
	 * Searches the data structure for the string passed into the method
	 * Returns a boolean
	 * 
	 * @param info
	 * @return boolean
	 */
	public boolean containsString(String info){

		boolean contains_info = false;
		
		try{
			for(int i=0;i<infoStore.size();i++){
				if(infoStore.get(i).containsName(info)){
					contains_info = true;
				}
			}
		}catch(NullPointerException e){
			e.printStackTrace();
			System.exit(0);
		}
		
		return contains_info;
	}
	
	/**
	 * Gets the StudentInfo object that contains the string passed into the method
	 * 
	 * @param info
	 * @return StudentInfo
	 */
	public StudentInfo getStudentContainsString(String info){
		StudentInfo output = new StudentInfo();
		
		try{
			
			for(int i=0;i<infoStore.size();i++){
				if(infoStore.get(i).containsName(info)){
					output.setFirstName(infoStore.get(i).getFirstName());
					output.setLastName(infoStore.get(i).getLastName());
					output.setInstructor(infoStore.get(i).getInstructor());
					output.setCourseNumber(infoStore.get(i).getCourseNumber());
				}
			}
		}catch(NullPointerException e){
			e.printStackTrace();
			System.exit(0);
		}
		return output;
	}
	
	/**
	 * Displays the student information contained in the data structure
	 */
	public void displayData(){
		try{
			
			for(int i=0;i<infoStore.size();i++){
				System.out.println(i + ": " + infoStore.get(i));
			}
			
		}catch(NullPointerException e){
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	/**
	 * toString method
	 * 
	 * returns the string that contains the student information stored in the data structure
	 * 
	 * @return String
	 */
	public String toString(){
		
		String output = "";
		
		for(int i=0;i<infoStore.size();i++){
			output = output + infoStore.get(i) + "\n";
		}
	
		return output;
	}

	@Override
	public boolean add(StudentInfo e) {
		return 	infoStore.add(e);
	}

	@Override
	public void add(int index, StudentInfo element) {
		
		infoStore.add(index, element);
		
	}

	@Override
	public boolean addAll(Collection<? extends StudentInfo> c) {

		return infoStore.addAll(c);
	}

	@Override
	public boolean addAll(int index, Collection<? extends StudentInfo> c) {

		
		return infoStore.addAll(index, c);
	}

	@Override
	public void clear() {
		for(int i=0;i<infoStore.size();i++){
			infoStore.get(i).setFirstName("");
			infoStore.get(i).setLastName("");
			infoStore.get(i).setInstructor("");
			infoStore.get(i).setCourseNumber(-1);
		}
	}

	@Override
	public boolean contains(Object o) {
		boolean contains_object = false;

		try{

			for(int i=0;i<infoStore.size();i++){
				if(infoStore.get(i).equals((StudentInfo)o)){
					contains_object = true;
				}
			}
		}catch(NullPointerException e){
			e.printStackTrace();
			System.exit(0);
		}
		return contains_object;
	}

	@Override
	public boolean containsAll(Collection<?> c) {

		return infoStore.containsAll(c);
	}

	@Override
	public StudentInfo get(int index) {
		return infoStore.get(index);
	}

	@Override
	public int indexOf(Object o) {
		int index = 0;
		
		try{
		
			for(int i=0;i<infoStore.size();i++){
				if(infoStore.get(i).isEqual((StudentInfo)o)){
					index = i;
					break;
				}
			}
		}catch(NullPointerException e){
			e.printStackTrace();
			System.exit(0);
		}
		return index;
	}

	@Override
	public boolean isEmpty() {
		
		return infoStore.isEmpty();
	}

	@Override
	public Iterator<StudentInfo> iterator() {

		return infoStore.iterator();
	}

	@Override
	public int lastIndexOf(Object o) {

		return infoStore.lastIndexOf(o);
	}

	@Override
	public ListIterator<StudentInfo> listIterator() {

		return infoStore.listIterator();
	}

	@Override
	public ListIterator<StudentInfo> listIterator(int index) {

		return infoStore.listIterator(index);
	}

	@Override
	public boolean remove(Object o) {

		boolean removed = false;
		
		for(int i=0;i<infoStore.size();i++){
			if(infoStore.get(i).equals(o)){
				infoStore.remove(i);
				removed = true;
			}
		}
		
		return removed;
	}

	@Override
	public StudentInfo remove(int index) {

		if(infoStore.get(index) != null){
			infoStore.remove(index);
		}
		
		return null;
	}

	@Override
	public boolean removeAll(Collection<?> c) {

		return infoStore.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {

		return infoStore.retainAll(c);
	}

	@Override
	public StudentInfo set(int index, StudentInfo element) {

		infoStore.get(index).setFirstName(element.getFirstName());
		infoStore.get(index).setLastName(element.getLastName());
		infoStore.get(index).setInstructor(element.getInstructor());
		infoStore.get(index).setCourseNumber(element.getCourseNumber());
				
		return infoStore.get(index);
	}

	@Override
	public int size() {

		return infoStore.size();
	}

	@Override
	public List<StudentInfo> subList(int fromIndex, int toIndex) {

		return infoStore.subList(fromIndex, toIndex);
	}

	@Override
	public Object[] toArray() {
		
		return infoStore.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {

		return infoStore.toArray(a);
	}
}
