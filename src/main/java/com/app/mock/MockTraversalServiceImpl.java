package com.app.mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import com.app.domain.Row;
import com.app.domain.Solution;
import com.app.service.TraversalServiceImpl;

public class MockTraversalServiceImpl extends TraversalServiceImpl {

	private static Random random = new Random();

	
	/*
	 * The process method of the traversal service is overloaded in the Mock service with the min and max values as input,
	 * which are required for the randomly generated 2dArray test scenarios. Also, in case we don't receive an int[][] object as input
	 * the control values required to be calculated is based on the created List of Row objects. 
	 * The approach differs between int[][] vs List<Rows>
	 * 
	 * For remaining cases in which the int[][] is used as input for the tests, the method in TraversalServiceImpl will be used as is.
	 */
	public Solution process(int min, int max) {
		List<Row> rows = generate2DArrayMapping(min, max);
		
		Integer initialSize = rows.
				stream().map(Row::getContentSize).reduce(0, Integer::sum);
		
		Integer totalSum = rows.
				stream().map(Row::getContentSum).reduce(0, Integer::sum);
		
		List<Integer> traversedList = traversed2DArray(rows, initialSize);
		
		return new Solution(traversedList, initialSize, totalSum);
	}
	

	/*
	 * The generate2DArrayMapping method of the traversal service is overloaded in the Mock service with the min and max values as input,
	 * in case we would like to create a list of Row objects which represents a 2dArray with random lengths in both directions. 
	 * 
	 * The min-max represents the range within which random lengths will be generated.
	 */
	public List<Row> generate2DArrayMapping(int min, int max) {
		List<Row> rows = new ArrayList<Row>();

		int currentValue = 1;
		int rowSize = random.nextInt(max - min) + min;

		for (int i = 0; i < rowSize; i++) {
			int columnSize = (random.nextInt(max - min) + min);

			Row currentRow = new Row();
			currentRow.setIndex(i);
			
			Integer rowSum = 0;
			List<Integer> currentContents = new ArrayList<Integer>();
			for (int j = 0; j < columnSize; j++) {
				currentContents.add(currentValue++);
			}
			currentRow.setContents(currentContents);
			currentRow.setContentSize(currentContents.size());

			if (currentRow.getIndex() == 0) {
				currentRow.setFirst(true);
			}
			if (currentRow.getIndex() == rowSize - 1) {
				currentRow.setLast(true);
			}
			rowSum = rowSum + currentContents.stream().mapToInt(Integer::intValue).sum();
			System.out.println(
					"[" + currentContents.stream().map(String::valueOf).collect(Collectors.joining(", ")) + "]");
			
			currentRow.setContentSum(rowSum);
			rows.add(currentRow);
		}
		linkRows(rows);
		return rows;
	}
}
