package pl.java.ee.quicksort.service;

import java.util.List;

import javax.ejb.Local;

/**
 * Universal interface which provides sort algorithm.
 * @author Wojciech Bêdkowski
 * @version 1.0
 */
@Local
public interface SortService {
	/**
	 * Sort method which sorts requested container. Values inside container have to implement Comparable interface.
	 * @param data Container to sort.
	 * @return Sorted container.
	 * @throws ServiceException - when given collection contains object which is not Comparable.
	 */
	public <T> List<Comparable<T>> sort(List<Comparable<T>> data) throws ServiceException;
}
