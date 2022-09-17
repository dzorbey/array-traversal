package com.app.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.app.domain.Row;
import com.app.domain.Solution;
import com.app.mock.MockTraversalServiceImpl;

@Component
@Profile("test")
public class TraversalServiceTest {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private static Random random = new Random();

	private TraversalService service;

	public TraversalServiceTest() {
		service = new MockTraversalServiceImpl();
	}

    @Test
	public void requestedScenarioSatisfied() throws Exception {

		System.out.println("");
		int[][] initial2dArray = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 } };
		String conditionToVarify = "1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10";

		Stream.of(initial2dArray).map(Arrays::toString).forEach(System.out::println);
		
		Solution solution = service.process(initial2dArray); 
		logger.info("\u001B[32m" + "result: " + solution.toString() + "\u001B[0m");
		
		
		assertTrue(solution.getTraversedList().size() == 16);
		assertTrue(conditionToVarify.equals(solution.toString()));
		
		
		/*
		 * Matrix matrix = new Matrix(initial2dArray, 16);
		 * Stream.of(matrix.get()).map(Arrays::toString).forEach(System.out::println);
		 * 
		 * Solution solution = service.process(matrix.get()); logger.info("\u001B[32m" +
		 * "result: " + solution.toString() + "\u001B[0m");
		 * 
		 * assertTrue(solution.getTraversedList().size() == matrix.getSize());
		 * assertTrue(conditionToVarify.equals(solution.toString()));
		 */
	}

	@Test
	public void randomlyAllocatedScenarioSatisfied() throws Exception {

		// for (int i = 0; i < 5; i++) {
		System.out.println("");

		List<Row> dimendisonalArray = random2dArrayGenerator(3, 8);
		for (Row row : dimendisonalArray) {
			String currentRow = "[" + row.getContents().stream().map(String::valueOf).collect(Collectors.joining(", ")) + "]";
			System.out.println(currentRow);
		}
		Integer arraySize = dimendisonalArray.stream().map(Row::getSize).reduce(0, Integer::sum);
		
		Solution solution = new Solution(service.traversed2DArray(dimendisonalArray));
		logger.info("\u001B[32m" + "result: " + solution.toString() + "\u001B[0m");
		assertTrue(solution.getTraversedList().size() == arraySize);
	}



	public List<Row> random2dArrayGenerator(int min, int max) {

		List<Row> rows = new ArrayList<Row>();

		int currentValue = 1;
		int row = random.nextInt(max - min) + min;

		for (int i = 0; i < row; i++) {
			int column = (random.nextInt(max - min) + min);
			
			Row currentRow = new Row();
			currentRow.setIndex(i);
			
			List<Integer> currentContents = new ArrayList<Integer>();
			for (int j = 0; j < column; j++) {
				currentContents.add(currentValue++);
			}
			currentRow.setContents(currentContents);
			currentRow.setSize(currentContents.size());

			if (currentRow.getIndex() == 0) {
				currentRow.setFirst(true);
			}
			if (currentRow.getIndex() == row - 1) {
				currentRow.setLast(true);
			}
			rows.add(currentRow);
		}
		return rows;
	}

}