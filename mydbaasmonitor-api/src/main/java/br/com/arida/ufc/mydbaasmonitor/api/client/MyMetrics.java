package main.java.br.com.arida.ufc.mydbaasmonitor.api.client;

/**
 * Class that controls access to resource metrics.
 * @author Daivd Araújo - @araujodavid
 * @version 1.0
 * @since May 8, 2013
 */
public class MyMetrics {

	private MyDBaaSMonitorClient client;
	
	/**
	 * Default constructor
	 * @param client - MyDBaaSMonitorClient object
	 */
	public MyMetrics(MyDBaaSMonitorClient client) {
		this.client = client;
	}
}
