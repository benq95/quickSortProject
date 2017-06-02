package pl.java.ee.quicksort.service;

/**
 * Custom service exception class.
 * @author Wojciech B�dkowski
 * @version 1.0
 */
public class ServiceException extends Exception{
	public ServiceException (String message){
		super(message);
	}
}
