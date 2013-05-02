package main.java.br.com.arida.ufc.mydbaasmonitor.agent.collector.database;

import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;

import main.java.br.com.arida.ufc.mydbaasmonitor.agent.collector.common.AbstractCollector;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity.ProcessStatusMetric;

/**
 * 
 * @author David Araújo - @araujodavid
 * @version 1.0
 * @since May 2, 2013
 * 
 */
public class ProcessStatusCollector extends AbstractCollector<ProcessStatusMetric> {

	public ProcessStatusCollector(int identifier) {
		super(identifier);
	}

	@Override
	public void loadMetric(Object[] args) throws Exception {
		// TODO Auto-generated method stub		
	}

	@Override
	public void run() {
		this.metric = ProcessStatusMetric.getInstance();
		HttpResponse response;
		List<NameValuePair> params = null;
		
		//Checking the DBMSs enabled for collection
		if (this.metric.getDBMSs().length > 0) {
			for (String dbms : this.metric.getDBMSs()) {
				
			}
		}
	}

}
