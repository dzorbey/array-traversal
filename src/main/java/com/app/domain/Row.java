package com.app.domain;

import java.util.List;

public class Row {

	private Integer index;
	private Integer size;
	private List<Integer> contents;

	private boolean first;
	private boolean last;

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

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public void decrementSize(Integer decrement) {
		this.setSize(this.getSize() - decrement);
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