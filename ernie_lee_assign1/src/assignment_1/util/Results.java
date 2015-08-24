package util;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;

import dataStore.StudentInfo;
import util.Debug;

public class Results implements List<StudentInfo>{

	private List<StudentInfo> results;
	
	/**
	 * default constructor
	 */
	public Results(){
		results = new Vector<StudentInfo>();
		
		Debug.dump(4, "dump Results()");

	}
	
	/**
	 * Constructor
	 * Initialized the Results data structure that stores StudentInfo matches
	 * 
	 * @param i
	 */
	public Results(int i){
		results = new Vector<StudentInfo>(i);
		
		Debug.dump(4, "dump Results(int i)");

	}
	
	/**
	 * toString method
	 * returns a String that contains the entries stored in its data structure
	 * 
	 * @return String
	 */
	public String toString(){
		String output = "";
		
		for(int i=0;i<results.size();i++){
			output = output + results.get(i) + "\n";
		}
	
		return output;
	}
	
	@Override
	public boolean add(StudentInfo e) {
		
		Debug.dump(2, "dump Element " + e.toString());
		
		return results.add(e);
	}

	@Override
	public void add(int index, StudentInfo element) {
		
		results.add(index, element);
		
	}

	@Override
	public boolean addAll(Collection<? extends StudentInfo> c) {
		
		return results.addAll(c);
	}

	@Override
	public boolean addAll(int index, Collection<? extends StudentInfo> c) {
		
		return results.addAll(index, c);
	}

	@Override
	public void clear() {

		for(int i=0;i<results.size();i++){
			results.get(i).setFirstName("");
			results.get(i).setLastName("");
			results.get(i).setInstructor("");
			results.get(i).setCourseNumber(-1);
		}
		
	}

	@Override
	public boolean contains(Object o) {

		boolean contains_object = false;

		try{

			for(int i=0;i<results.size();i++){
				if(results.get(i).equals((StudentInfo)o)){
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

		return results.containsAll(c);
	}

	@Override
	public StudentInfo get(int index) {

		return results.get(index);
	}

	@Override
	public int indexOf(Object o) {
		int index = 0;
		
		try{
		
			for(int i=0;i<results.size();i++){
				if(results.get(i).isEqual((StudentInfo)o)){
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

		return results.isEmpty();
	}

	@Override
	public Iterator<StudentInfo> iterator() {

		return results.iterator();
	}

	@Override
	public int lastIndexOf(Object o) {
		
		return results.lastIndexOf(o);
	}

	@Override
	public ListIterator<StudentInfo> listIterator() {

		return results.listIterator();
	}

	@Override
	public ListIterator<StudentInfo> listIterator(int index) {

		return results.listIterator(index);
	}

	@Override
	public boolean remove(Object o) {
		boolean removed = false;
		
		for(int i=0;i<results.size();i++){
			if(results.get(i).equals(o)){
				results.remove(i);
				removed = true;
			}
		}
		
		return removed;
	}

	@Override
	public StudentInfo remove(int index) {

		if(results.get(index) != null){
			results.remove(index);
		}
		
		return null;
	}

	@Override
	public boolean removeAll(Collection<?> c) {

		return results.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {

		return results.retainAll(c);
	}

	@Override
	public StudentInfo set(int index, StudentInfo element) {
		
		results.get(index).setFirstName(element.getFirstName());
		results.get(index).setLastName(element.getLastName());
		results.get(index).setInstructor(element.getInstructor());
		results.get(index).setCourseNumber(element.getCourseNumber());
				
		return results.get(index);
	}

	@Override
	public int size() {

		return results.size();
	}

	@Override
	public List<StudentInfo> subList(int fromIndex, int toIndex) {

		return results.subList(fromIndex, toIndex);
	}

	@Override
	public Object[] toArray() {

		return results.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {

		return results.toArray(a);
	}
}
