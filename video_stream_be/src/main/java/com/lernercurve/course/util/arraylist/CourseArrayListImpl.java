package com.lernercurve.course.util.arraylist;

import java.util.Arrays;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class CourseArrayListImpl<T> implements CourseArrayList {
	private volatile Object[] courseArrayList;
	private int sizeOfcourseArrayList;
	private int INITIAL_CAPACITY = 10;

	public CourseArrayListImpl() {
		this.courseArrayList = new Object[this.INITIAL_CAPACITY];
		this.sizeOfcourseArrayList = 0;
	}

	public int getSize() {
		return this.sizeOfcourseArrayList;
	}

	public <T> boolean addElements(T element) {
		boolean isElementAdded = false;
		try {
			this.increaseArraySize();
			this.courseArrayList[this.sizeOfcourseArrayList++] = element;
		} catch (Exception e) {
			log.info("unable to add elements to list:- {} ", e.getMessage());
		}
		return isElementAdded;
	}

	@SuppressWarnings("unchecked")
	public T getElementsByIndex(int index) {
		T element = null;
		try {
           element=(T) this.courseArrayList[index];
		} catch (Exception e) {
			log.info("unable to retrive elements to list:- {} ", e.getMessage());
		}
		return element;
	}

	
	public T removeElementByIndex(int index) {
		T element = null;
		try {
            element = this.getElementsByIndex(index);
           if(element !=null) {
        	   int numMoved = this.sizeOfcourseArrayList - index - 1;
        	   if (numMoved > 0) {
                   System.arraycopy(this.courseArrayList, index + 1, this.courseArrayList, index, numMoved);
               }
               this.courseArrayList[--this.sizeOfcourseArrayList] = null; // Clear the last element
           }
           
		} catch (Exception e) {
			log.info("unable to delete elements from list:- {} ", e.getMessage());
		}
		return element;
	}

	
	private void increaseArraySize() {
		if (this.sizeOfcourseArrayList == this.courseArrayList.length) {
			this.courseArrayList = Arrays.copyOf(this.courseArrayList, this.getSize() + this.INITIAL_CAPACITY);
		}
	}

	public Object getElements() {
		return this.courseArrayList;
	}

	public static void main(String[] args) {
		var list = new CourseArrayListImpl<Object>();

		for (int i = 0; i < 500; i++) {
			list.addElements("ppanda:- " + i);
		}

		log.info("items:- {}", list.getElements());
		log.info("items:- {}", list.getSize());
		log.info("remove items:- {}", list.removeElementByIndex(400));
		log.info("items size after remove:- {}", list.getSize());
	}

}
