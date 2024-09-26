package com.lernercurve.course.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@ToString
public class CourseLinkedList {
	private Node<?> head;
	private Node<?> tail;

	public CourseLinkedList() {
		this.head = null;
		this.tail = null;
	}

	public <T> boolean addANewNodeToLast(T data) {
		boolean isAdded = false;
		try {
			Node<T> newNode=new Node<T>(data);
			if(this.head==null) {
				this.head=newNode;
				this.tail=newNode;
				isAdded=true;
			}else {
				this.tail.next = newNode;  // Link new node to the last node
	            newNode.prev = this.tail;   // Link last node to the new node
	            this.tail = newNode;        // Update tail to new node
	            isAdded=true;
	        }
        
		} catch (Exception e) {
         log.info("error while adding elemet to linked list:- {}",e.getMessage());
		}
		return isAdded;
	}
	
	public <T> boolean addANewNodeToFirst(T data) {
		boolean isAdded = false;
		try {
			Node<T> newNode=new Node<T>(data);
			if(this.head==null) {
				this.head=newNode;
				this.tail=newNode;
				isAdded=true;
			}else {
				this.head.next = newNode;  // Link new node to the last node
	            newNode.prev = this.head;   // Link last node to the new node
	            this.head = newNode;        // Update tail to new node
	            isAdded=true;
	        }
        
		} catch (Exception e) {
         log.info("error while adding elemet to linked list:- {}",e.getMessage());
		}
		return isAdded;
	}
	
	public List<Object> getLinkListInForwardDirection() {
		Node<?> current=this.head;
		List<Object> link=new ArrayList<>();
		while(current !=null) {
			link.add(current.storedData);
			current=current.next;
		}
		return link;
	}
	public List<Object> getLinkListInBackwardDirection() {
		Node<?> current=this.tail;
		List<Object> link=new ArrayList<>();
		while(current !=null) {
			link.add(current.storedData);
			current=current.prev;
		}
		return link;
	}
	
	
//	
//	public static void main(String[] args) {
//		var course= new CourseLinkedList();
//		course.addANewNodeToLast(1);
//		course.addANewNodeToLast("ppanda67");
//		course.addANewNodeToLast(Arrays.asList("ppanda"));
//		course.addANewNodeToLast("ppanda");
//		log.info("list in forward:-{}  ",course.getLinkListInForwardDirection());
//		log.info("list of backward:-{}  ",course.getLinkListInBackwardDirection());
//	}
	
}
