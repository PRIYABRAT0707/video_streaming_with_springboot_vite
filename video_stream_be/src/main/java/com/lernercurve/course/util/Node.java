package com.lernercurve.course.util;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class Node<T> {
	 T storedData;
   	 Node<?> prev;
   	 Node<?> next;
   	
   	public Node(T storedData) {
   		this.storedData=storedData;
   		this.prev=null;
   		this.next=null;
   	}
}
