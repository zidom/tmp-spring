package org.demo.ring;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.RingBuffer;

/**
 * @author zidom
 *
 */
public class Main {
	
	public static void main(String[] args) {
		EventFactory<Long> factory = null;
		
		RingBuffer<Long> buf= RingBuffer.createSingleProducer(factory, 2<<10);
		
		
	}

}
