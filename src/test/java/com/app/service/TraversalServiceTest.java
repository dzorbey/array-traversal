package com.app.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.app.domain.Solution;
import com.app.mock.MockTraversalServiceImpl;

@Component
@Profile("test")
public class TraversalServiceTest {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private MockTraversalServiceImpl service;

	public TraversalServiceTest() {
		service = new MockTraversalServiceImpl();
	}

    @Test
	public void requestedScenarioSatisfied() throws Exception {

		System.out.println("");
		int[][] initial2dArray = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 } };

		String conditionToVarify = "1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10";

		Solution solution = service.process(initial2dArray);
		logger.info("\u001B[32m" + "result: " + solution.toString() + "\u001B[0m");

		assertTrue(solution.getSolutionSize().equals(solution.getInitialArraySize()));
		assertTrue(solution.getSolutionSum().equals(solution.getInitialArraySum()));

		assertTrue(conditionToVarify.equals(solution.toString()));
	}

	@Test
	public void unEvenRowLenghtsScenarioSatisfied() throws Exception {

		System.out.println("");
		int[][] initial2dArray = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9 }, { 10, 11, 12, 13, 14, 15, 16 },
				{ 17, 18, 19, 20 } };

		Solution solution = service.process(initial2dArray);
		logger.info("\u001B[32m" + "result: " + solution.toString() + "\u001B[0m");

		assertTrue(solution.getSolutionSize().equals(solution.getInitialArraySize()));
		assertTrue(solution.getSolutionSum().equals(solution.getInitialArraySum()));
	}

	@Test
	public void unEvenRowLenghtsScenarioSatisfied2() throws Exception {

		System.out.println("");
		int[][] initial2dArray = { { 1, 2, 3, 4, 5, 6, 7 }, { 8 }, { 9, 10, 11, 12, 13, 14, 15, 16 },
				{ 17, 18, 19, 20 }, { 21 }, { 22, 23, 24, 25 } };

		Solution solution = service.process(initial2dArray);
		logger.info("\u001B[32m" + "result: " + solution.toString() + "\u001B[0m");

		assertTrue(solution.getSolutionSize().equals(solution.getInitialArraySize()));
		assertTrue(solution.getSolutionSum().equals(solution.getInitialArraySum()));
	}

    @Test
	public void randomlyAllocatedScenarioSatisfied() throws Exception {

		// for (int i = 0; i < 5; i++) {
		System.out.println("");

		Solution solution = service.process(1, 10);
		logger.info("\u001B[32m" + "result: " + solution.toString() + "\u001B[0m");

		assertTrue(solution.getSolutionSize().equals(solution.getInitialArraySize()));
		assertTrue(solution.getSolutionSum().equals(solution.getInitialArraySum()));
	}

}