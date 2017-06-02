package pl.java.ee.quicksort.test.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Test;

import pl.java.ee.quicksort.service.QuickSortService;
import pl.java.ee.quicksort.service.ServiceException;

public class QuickSortServiceTest {
	
	/**
	 * Test sorting algorithm with random integers
	 */
	@Test
	public void sortTestInteger(){
		QuickSortService quickSortService = new QuickSortService();
		ArrayList<Integer> testList = new ArrayList<Integer>();
		Random generator = new Random();
		
		final int elementsCount = 10;
		
		for(int i = 0; i < elementsCount; i++){
			testList.add(generator.nextInt(10));
		}
		
		try{
			quickSortService.sort((List)testList);
		} catch(ServiceException e){
			fail("Service expcetion thrown");
		}
		
		for(int i = 0; i < testList.size() - 1; i++){
			if(testList.get(i) > testList.get(i + 1)){
				fail("Collection sorted incorrectly");
			}
		}
		
	}
	
	/**
	 * Test sorting algorithm with random strings
	 */
	@Test
	public void sortTestString(){
		QuickSortService quickSortService = new QuickSortService();
		ArrayList<String> testList = new ArrayList<String>();
		Random generator = new Random();
		
		final int elementsCount = 10;
		
		for(int i = 0; i < elementsCount; i++){
			testList.add(Integer.toString(generator.nextInt()));
		}
		
		try{
			quickSortService.sort((List)testList);
		} catch(ServiceException e){
			fail("Service expcetion thrown");
		}
		
		for(int i = 0; i < testList.size() - 1; i++){
			if(testList.get(i).compareTo(testList.get(i + 1)) > 0){
				fail("Collection sorted incorrectly");
			}
		}
		
	}
	
	/**
	 * Test sorting algorithm with random integers and null value inside
	 */
	@Test
	public void sortTestIntegerWithNull(){
		QuickSortService quickSortService = new QuickSortService();
		ArrayList<Integer> testList = new ArrayList<Integer>();
		Random generator = new Random();
		
		final int elementsCount = 10;
		
		for(int i = 0; i < elementsCount; i++){
			testList.add(generator.nextInt(10));
		}
		
		testList.set(0, null);
		testList.set(1, null);
		testList.set(4, null); //add null pointer in the middle of the collection
		
		try{
			quickSortService.sort((List)testList);
		} catch(ServiceException e){
			fail("Service expcetion thrown");
		}
		
		for(int i = 0; i < testList.size() - 1; i++){
			if(testList.get(i) > testList.get(i + 1)){
				fail("Collection sorted incorrectly");
			}
		}
		
	}
	
	/**
	 * Test sorting algorithm with random strings and null value inside
	 */
	@Test
	public void sortTestStringWithNull(){
		QuickSortService quickSortService = new QuickSortService();
		ArrayList<String> testList = new ArrayList<String>();
		Random generator = new Random();
		
		final int elementsCount = 10;
		
		for(int i = 0; i < elementsCount; i++){
			testList.add(Integer.toString(generator.nextInt()));
		}
		
		testList.set(4, null); //add null pointer in the middle of the collection
		try{
			quickSortService.sort((List)testList);
		} catch(ServiceException e){
			fail("Service expcetion thrown");
		}
		
		for(int i = 0; i < elementsCount - 2; i++){
			if(testList.get(i).compareTo(testList.get(i + 1)) > 0){
				fail("Collection sorted incorrectly");
			}
		}
		
	}
	
	@Test
	public void noComparableTest(){
		QuickSortService quickSortService = new QuickSortService();
		final int elementsCount = 10;
		ArrayList<ServiceException> testList = new ArrayList<ServiceException>();
		for(int i = 0; i < elementsCount; i++){
			testList.add(new ServiceException("")); //fill container with no comparable elements to check if method will notice it
		}
		try{
			quickSortService.sort((List)testList);
			fail("Service expcetion no thrown");
		} catch(ServiceException e){
		}
	}

}
