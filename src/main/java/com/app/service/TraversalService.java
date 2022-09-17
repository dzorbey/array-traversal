package com.app.service;

import java.util.List;

import com.app.domain.Row;
import com.app.domain.Solution;


public interface TraversalService {

	public List<Integer> traversed2DArray(List<Row> rowList);
	
	public List<Row> generate2DArrayMapping(int[][] dimensionalArray);
	
	public Solution process(int[][] dimensionalArray);
}