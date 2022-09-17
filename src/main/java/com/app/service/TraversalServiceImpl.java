package com.app.service;

import java.util.ArrayList;
import java.util.List;

import com.app.domain.Row;
import com.app.domain.Solution;

public class TraversalServiceImpl implements TraversalService {
	

	public Solution process(int[][] array) {
		List<Row> rowList = generate2DArrayMapping(array);
		
		return new Solution(traversed2DArray(rowList));
	}

	/*
	 * Each row object of the initialized list is either the first row, the last row
	 * or any other row in between. From outer edges to the inner traversal of the matrix has a characteristic recursive/repetitive pattern in it.
	 * 
	 * Once those are processed the first and the last rows assignment of the traversal matrix gets shifted into the
	 * remaining rows of the list in their respective position. 
	 * 
	 * By following such pattern we make the algorithm to be applicable for any number of [n][m] 2D array size combinations.
	 */
	public List<Integer> traversed2DArray(List<Row> rowList) {
		List<Integer> traversedList = new ArrayList<Integer>();
		
		Integer initialSize = rowList.
				stream().map(Row::getSize).reduce(0, Integer::sum);

		while (traversedList.size() < initialSize) {

			for (int currentIndex = 0; currentIndex < rowList.size(); currentIndex++) {
				Row currentRow = rowList.get(currentIndex);

				/*
				 * If a row is a first row in the given iteration, all the contents are
				 * transfered in order into the final MatrixList.
				 */
				if (currentRow.isFirst() == true) {
					traversedList.addAll(currentRow.getContents());
					currentRow.setSize(0);
					currentRow.setFirst(false);

				}
				/*
				 * If a row is a last row in the current iteration, then all the contents are
				 * transfered into the final list in the reserve scheme. But once this is done,
				 * the last row also has the responsibility to find the remaining rows with nonEmpty content lists
				 * 
				 * while doing that, some of the new intermediate rows are allocated to be our
				 * new first and last row candidates based on their previous adjacent row's previous operational patterns.
				 */
				else if (currentRow.isLast() == true) {

					Integer currentFinalRowSize = currentRow.getSize();
					for (int index = currentFinalRowSize - 1; index >= 0; index--) {
						traversedList.add(currentRow.getByIndex(index));
					}
					Integer currentFinalIndex = currentRow.getIndex();
					currentRow.setSize(0);
					currentRow.setLast(false);

					Integer backwardIterateIndex = currentFinalIndex - 1;

					while (rowList.get(backwardIterateIndex).getSize() != 0) {
						Row previousRow = rowList.get(backwardIterateIndex);

						if (rowList.get(backwardIterateIndex - 1).getSize() == 0) {
							previousRow.setFirst(true);
						}
						if (rowList.get(backwardIterateIndex + 1).getSize() == 0) {
							previousRow.setLast(true);
						}
						Integer currentDataToRemove = previousRow.getByIndex(0);
						traversedList.add(currentDataToRemove);

						/*
						 * If an intermediate row is being processed by following the process of a previous isLast row, 
						 * then the first object of the intermediate row is selected which is the current inner edge of the matrix.
						 */
						previousRow.getContents().remove(0);
						previousRow.decrementSize(1);

						backwardIterateIndex--;
					}
				}
			    /*
				 * if a row is not a first or a last row, then it is an intermediate row.
				 */
				else {
					Integer currentRowSize = currentRow.getSize();

					if (currentRowSize != 0) {
						Integer currentDataToRemove = currentRow.getByIndex(currentRowSize - 1);
						traversedList.add(currentDataToRemove);
						
						/*
						 * If an intermediate row is being processed by not following the process of a previous isLast row, 
						 * then the last object of the intermediate row is selected which is the current outer edge of the matrix.
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
	 * The solution in mind requires to treat the rows of the 2D array as a solid
	 * data structure Therefore initially the input content is transformed into a list of [Row] objects.
	 */
	public List<Row> generate2DArrayMapping(int[][] dimensionalArray) {

		List<Row> rows = new ArrayList<Row>();
		for (int i = 0; i < dimensionalArray.length; i++) {
			Row row = new Row();
			row.setIndex(i);

			List<Integer> currentRow = new ArrayList<Integer>();
			for (int j = 0; j < dimensionalArray[i].length; j++) {
				currentRow.add(dimensionalArray[i][j]);
			}
			row.setContents(currentRow);
			row.setSize(currentRow.size());

			if (row.getIndex() == 0) {
				row.setFirst(true);
			}
			if (row.getIndex() == dimensionalArray.length - 1) {
				row.setLast(true);
			}
			rows.add(row);
		}
		return rows;
	}
}
