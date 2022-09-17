package com.app.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Solution {

	public List<Integer> traversedList;
		
	public Solution(List<Integer> result) {
		setTraversedList(result);
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

}
