/*
 * @project		MyEBook
 * @author		Oliver.xyy
 * @file_name	BlockingQueue.java
 * @pub_time	2015-6-8 14:39:07
 * @copyright	Oliver.xyy All Rights Reserved.
 */
package util;

import java.util.LinkedList;

public class BlockingQueue {

	private LinkedList<String> queue = new LinkedList<String>();
	private int limit = 100;

	public BlockingQueue(int limit) {
		this.limit = limit;
	}

	public synchronized void add(String item){
		while (this.queue.size() == this.limit) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (this.queue.size() == 0) {
			notifyAll();
		}
		this.queue.add(item);
		System.out.println("添加队列元素："+item);
	}

	public synchronized String remove(){
		while (this.queue.size() == 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (this.queue.size() == this.limit) {
			notifyAll();
		}
		System.out.println("删除队列元素");
		return this.queue.remove(0);
	}
	public synchronized boolean isEmpty(){
		return this.queue.isEmpty();
	}

}
