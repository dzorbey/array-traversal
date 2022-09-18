package com.app.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.app.domain.Row;
import com.app.domain.Solution;

public class TraversalServiceImpl implements TraversalService {
	

	/*
	 * The main method is responsible for coordination of the all other operations,
	 * first mapping the initial 2d array to the List of Row objects required for traversal logic.
	 * Processing initial data received as input for 2darray size and sum of values operations,
	 * and later on executing the traversal operation and mapping the result with the initial control values into the Solution object.
	 */
	public Solution process(int[][] array) {
		List<Row> rowList = generate2DArrayMapping(array);
		
		Integer initialSize = rowList.
				stream().map(Row::getContentSize).reduce(0, Integer::sum);
		
		IntStream stream = 
				Arrays.stream(array).flatMapToInt(x -> Arrays.stream(x));
		
		List<Integer> traversedList = traversed2DArray(rowList, initialSize);
		
		return new Solution(traversedList, initialSize, Integer.valueOf(stream.sum()));
	}

	/*
	 * Each row object of the initialized list is either the first row, the last row
	 * or any other row in between. From outer edges to the inner traversal of the 2d array has a characteristic recursive/repetitive pattern in it.
	 * 
	 * Once those initial first/last rows are processed the first and the last rows assignment of the traversal array gets shifted into the
	 * remaining rows of the list in their respective position. 
	 * 
	 * By following such pattern we make the algorithm to be applicable for any number of [n][m] 2D array size combinations.
	 */
	public List<Integer> traversed2DArray(List<Row> rowList, Integer initialSize) {
		List<Integer> traversedList = new ArrayList<Integer>();

		while (traversedList.size() < initialSize) {

			for (int currentIndex = 0; currentIndex < rowList.size(); currentIndex++) {
				Row currentRow = rowList.get(currentIndex);
				
				/*
				 * If a row is a first row in the given iteration, all the contents are
				 * transfered in order into the final traversed list.
				 */
				if (currentRow.isFirst() == true) {
					traversedList.addAll(currentRow.getContents());
					currentRow.setContentSize(0);
					currentRow.setFirst(false);
				}
				/*
				 * If a row is a last row in the current iteration, then all the contents are
				 * transfered into the final list in the reserve scheme. But once this is done,
				 * the last row also has the responsibility to find the remaining rows with nonEmpty content lists
				 * 
				 * while doing that, some of the new intermediate rows are allocated to be our
				 * new first and last row candidates based on their previous adjacent row's operational patterns.
				 * The new first and new last rows are reallocated and meanwhile the traversal logic worked towards the upper previous rows,
				 * removing the first un-visited values of the previous rows in order.
				 */
				else if (currentRow.isLast() == true) {

					Integer currentFinalRowSize = currentRow.getContentSize();
					for (int index = currentFinalRowSize - 1; index >= 0; index--) {
						traversedList.add(currentRow.getByIndex(index));
					}
					currentRow.setContentSize(0);
					currentRow.setLast(false);
					
					findNextFirstRow(rowList);
					findNextLastRow(traversedList, currentRow);
				}
			    /*
				 * if a row is not a first or a last row, then it is an intermediate row.
				 */
				else {
					Integer currentRowSize = currentRow.getContentSize();

					if (currentRowSize != 0) {
						Integer currentDataToRemove = currentRow.getByIndex(currentRowSize - 1);
						traversedList.add(currentDataToRemove);
						
						/*
						 * If an intermediate row is being processed by not following the process of a previous isLast row, 
						 * then the last object of the intermediate row is selected which is the current outer edge on the right size of the remaining 2d array.
						 */
						currentRow.getContents().remove(currentRowSize - 1);
						currentRow.decrementSize(1);		
					}
				}
			}
		}
		return traversedList;
	}

	
	/*
	 * The solution in mind requires to treat the rows of the 2D array as a solid data structure.
	 * Therefore initially the input content is transformed into a list of [Row] objects in a scheme that's required by the algorithm in mind.
	 */
	public List<Row> generate2DArrayMapping(int[][] dimensionalArray) {
		
		Stream.of(dimensionalArray).map(Arrays::toString).forEach(System.out::println);

		List<Row> rows = new ArrayList<Row>();
		for (int i = 0; i < dimensionalArray.length; i++) {
			Row row = new Row();
			row.setIndex(i);

			List<Integer> currentRow = new ArrayList<Integer>();
			for (int j = 0; j < dimensionalArray[i].length; j++) {
				currentRow.add(dimensionalArray[i][j]);
			}
			row.setContents(currentRow);
			row.setContentSize(currentRow.size());

			if (row.getIndex() == 0) {
				row.setFirst(true);
			}
			if (row.getIndex() == dimensionalArray.length - 1) {
				row.setLast(true);
			}
			rows.add(row);
		}
		for(Row row : rows) {
			Integer currentIndex = row.getIndex();
			
			if(row.isFirst() != true
					&& row.getPreviousRow() == null && rows.get(currentIndex - 1) != null) {
				row.setPreviousRow(rows.get(currentIndex - 1));
			}
		}
		return rows;
	}
	
	
	public static void findNextFirstRow(List<Row> rows) {
		for(Row row : rows) {
			if(row.getContentSize() != 0) {
				row.setFirst(true);
				break;
			}
		}
	}
	
	public static void findNextLastRow(List<Integer> traversedList, Row currentRow) {
		boolean lastFound = false;
		while(currentRow.getPreviousRow() != null) {
			Row previous = currentRow.getPreviousRow();

			if(previous.getContentSize() != 0) {
				Integer currentDataToRemove = previous.getByIndex(0);
				traversedList.add(currentDataToRemove);

				/*
				 * If an intermediate row is being processed by following the process of a previous isLast row, 
				 * then the first object of the intermediate row is selected which is the current outer edge on the left size of the remaining 2d array.
				 */
				previous.getContents().remove(0);
				previous.decrementSize(1);
				
				if(lastFound == false) {
					previous.setLast(true);
					lastFound=true;
				}
			}						
			currentRow = previous;
		}
	}
}
