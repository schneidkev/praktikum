package de.hfu;
import org.junit.Assert;
import org.junit.Test;

public class QueueTest {

	@Test
	public void testKonstruktor() {
		//Konstruktor Test
		int size = 3;
		Queue q1 = new Queue(size);
		for(int i = 0; i<q1.queue.length;i++) {
			Assert.assertEquals(q1.queue[0],0);
		}
		//laenge Test
		Assert.assertEquals(q1.queue.length, size);
		
		
	}
	
	@Test
	public void testEnqueue() {
		Queue q1 = new Queue(3);
		q1.enqueue(1);
		q1.enqueue(1);
		q1.enqueue(1);
		Assert.assertEquals(q1.queue[0],1);
		Assert.assertEquals(q1.queue[1],1);
		Assert.assertEquals(q1.queue[2],1);

	}
	@Test
	public void testDequeue() {
		Queue q1 = new Queue(3);
		q1.enqueue(1);
		q1.enqueue(2);
		q1.enqueue(3);
		Assert.assertEquals(q1.dequeue(), 1);
		Assert.assertEquals(q1.dequeue(), 2);
		Assert.assertEquals(q1.dequeue(), 3);
		q1.enqueue(1);
		q1.enqueue(2);
		q1.enqueue(3);
		q1.enqueue(4);
		Assert.assertEquals(q1.dequeue(), 1);
		q1.enqueue(5);
		q1.enqueue(6);
		Assert.assertEquals(q1.dequeue(), 2);
		
	}
	@Test
	public void testdequeueEmptyArray() {
		try {
			Queue q1 = new Queue(3);
			q1.dequeue();
			Assert.fail();
		} catch(IllegalStateException e) {
			Assert.assertEquals(e.getMessage(), "dequeue on empty queue");
		}
	}
	@Test
	public void testRingspeicher() {
		Queue q1 = new Queue(3);
		q1.enqueue(1);
		q1.enqueue(2);
		q1.enqueue(3);
		q1.dequeue();
		q1.enqueue(4);
		Assert.assertEquals(q1.queue[0], 4);
		q1.enqueue(5);
		Assert.assertEquals(q1.queue[0],5);
	}
}
