package com.app.domain;

import java.util.List;

/*
 * The initial array object is transformed into a List of Rows, 
 * each row has a current index, the contents of that particular row,
 * the sum all integers of the contents and the value size of the content list.
 * 
 * The object also has a state of it's current position in the stack whether being first/last row in the list, and 
 * also holds a reference to the root row of the current list, and references to the next and previous adjacent rows of itself.
 */
public class Row {

	private Integer index;
	private Integer contentSize;
	private Integer contentSum;
	private List<Integer> contents;

	private boolean first;
	private boolean last;

	private Row root;

	private Row nextRow;
	private Row previousRow;
	

	public Row getNextRow() {
		return nextRow;
	}

	public void setNextRow(Row nextRow) {
		this.nextRow = nextRow;
	}

	public Row getRoot() {
		return root;
	}

	public void setRoot(Row root) {
		this.root = root;
	}

	public boolean isFirst() {
		return first;
	}

	public void setFirst(boolean first) {
		this.first = first;
	}

	public boolean isLast() {
		return last;
	}

	public void setLast(boolean last) {
		this.last = last;
	}

	public Row getPreviousRow() {
		return previousRow;
	}

	public void setPreviousRow(Row previousRow) {
		this.previousRow = previousRow;
	}

	public Integer getContentSize() {
		return contentSize;
	}

	public void setContentSize(Integer contentSize) {
		this.contentSize = contentSize;
	}

	public Integer getContentSum() {
		return contentSum;
	}

	public void setContentSum(Integer contentSum) {
		this.contentSum = contentSum;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public void decrementSize(Integer decrement) {
		this.setContentSize(this.getContentSize() - decrement);
	}

	public Integer getByIndex(Integer index) {
		return this.getContents().get(index);
	}

	public List<Integer> getContents() {
		return contents;
	}

	public void setContents(List<Integer> contents) {
		this.contents = contents;
	}
}