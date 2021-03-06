package ex06.pyrmont.core;

import org.apache.catalina.Lifecycle;
import org.apache.catalina.LifecycleEvent;
import org.apache.catalina.LifecycleListener;


/**
 * 事件监听器 
 *
 */
public class SimpleContextLifecycleListener implements LifecycleListener {

	public void lifecycleEvent(LifecycleEvent event) {
		
		Lifecycle lifecycle = event.getLifecycle();
		//System.out.println(Thread.currentThread().getName() + " lifecycle = " + lifecycle);

		System.out.println(Thread.currentThread().getName() + " SimpleContextLifecycleListener's event " + event.getType().toString());
		
		if (Lifecycle.START_EVENT.equals(event.getType())) {
			System.out.println(Thread.currentThread().getName() + " Starting context.");
		} else if (Lifecycle.STOP_EVENT.equals(event.getType())) {
			System.out.println(Thread.currentThread().getName() + " Stopping context.");
		}
	}
}