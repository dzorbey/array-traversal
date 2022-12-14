package com.app.domain;

import java.util.List;
import java.util.stream.Collectors;


/*
 * The Solution object holds the new traversed List and the initial data about the input 2d array, 
 * which allows us to check the outcome against the initial expectation in the test scenarios in a structured way.
 */
public class Solution {

	private Integer initialArraySize;
	private Integer initialArraySum;
	private List<Integer> traversedList;
	
	
	public Solution(List<Integer> result, Integer initialArraySize, Integer initialArraySum) {
		setTraversedList(result);
		setInitialArraySize(initialArraySize);
		setInitialArraySum(initialArraySum);
	}
	

	public Integer getInitialArraySize() {
		return initialArraySize;
	}

	public void setInitialArraySize(Integer initialArraySize) {
		this.initialArraySize = initialArraySize;
	}

	public Integer getInitialArraySum() {
		return initialArraySum;
	}

	public void setInitialArraySum(Integer initialArraySum) {
		this.initialArraySum = initialArraySum;
	}

	public List<Integer> getTraversedList() {
		return traversedList;
	}

	public void setTraversedList(List<Integer> traversedList) {
		this.traversedList = traversedList;
	}

	public String toString() {
		return getTraversedList().
				stream().map(String::valueOf).collect(Collectors.joining(","));
	}
	
	public Integer getSolutionSum() {
		return traversedList.stream().mapToInt(Integer::intValue).sum();
	}
	
	public Integer getSolutionSize() {
		return getTraversedList().size();
	}
}
