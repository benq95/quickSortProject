package pl.java.ee.quicksort.service;

import java.util.List;

import javax.ejb.Stateless;

/**
 * Sort service implementation with Quicksort algorithm.
 * @author Wojciech Bêdkowski
 * @version 1.0
 */
@Stateless
public class QuickSortService  implements SortService{
	
	/**
	 * Sort method which sorts list obtained with param by quicksort. This methods also eliminates null references from collection and checks if every element is Comparable.
	 */
	@Override
	public <T> List<Comparable<T>> sort(List<Comparable<T>> data) throws ServiceException{
		
		//check if list contains proper values to sort
		if(data==null){
			return null;
		}
		
		if(data.isEmpty()){
			return data;
		}
		
		if(data.size()==1){
			return data;
		}
		
		//elliminate any null values
		for(int i = 0; i < data.size(); i++){
			if(data.get(i) == null){
				data.remove(i);
				i--;
			}
		}
		
		//check if any object of the collection is Comparable
		for(Object i : data){
			if(!java.lang.Comparable.class.isInstance(i)){
				throw new ServiceException("The objects inside collection are not comparable!!");
			}
		}
		
		sortProcedure(data);
		
		return data;
	}
	
	private  <T> List<Comparable<T>> sortProcedure(List<Comparable<T>> data) {
				//select pivot in the middle of the collection
				Comparable<T> divisionPoint  = data.get((data.size() - 1) / 2);
				
				//select to iterated variable - to begin and end
				int i = 0;
				int j = data.size() - 1;
				
				//search for values which are greater than pivot on the left part of list, and lower on the right
				do {
					while(data.get(i).compareTo( (T)divisionPoint ) < 0){
						i++;
					}
					
					while(data.get(j).compareTo( (T)divisionPoint ) > 0){
						j--;
					}
					
					//swap found values
					if(i <= j){
						Comparable<T> temp = data.get(i);
						data.set(i, data.get(j));
						data.set(j, temp);
						i++;
						j--;
					}
				}while(i <= j);//continue until the both variables meet each other
				
				//sort the part to the left of pivot
				if(j>0){
					sortProcedure(data.subList(0, j+1));
				}
				//sort the part to the right of pivot
				if(i < data.size() - 1){
					sortProcedure(data.subList(i, data.size()));
				}
				
				return data;
	}
}
