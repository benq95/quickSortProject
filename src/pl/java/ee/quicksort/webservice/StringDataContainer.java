package pl.java.ee.quicksort.webservice;

import java.util.ArrayList;

/**
 * Structure used to map json request object to java class.
 * @author Wojciech Bêdkowski
 * @version 1.0
 */
public class StringDataContainer {
	
	public StringDataContainer(ArrayList<String> data, boolean isInteger){
		this.data = data;
		this.isInteger = isInteger;
	}
	
	/**
	 * Array of strings
	 */
	private ArrayList<String> data;
	
	/**
	 * Value to inform if data array contains integer values parsed as strings or normal string values.
	 */
	private boolean isInteger;

	public boolean getIsInteger() {
		return isInteger;
	}

	public void setIsInteger(boolean isInteger) {
		this.isInteger = isInteger;
	}

	public ArrayList<String> getData() {
		return data;
	}

	public void setData(ArrayList<String> data) {
		this.data = data;
	}
	
	public StringDataContainer(){
	}
	
}
