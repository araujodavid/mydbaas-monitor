package main.java.br.com.arida.ufc.mydbaasmonitor.agent.collector.database;

import main.java.br.com.arida.ufc.mydbaasmonitor.agent.collector.common.AbstractCollector;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity.database.ActiveConnectionMetric;

/**
 * 
 * @author David Araújo - @araujodavid
 * @version 1.0
 * @since April 17, 2013
 * 
 */
public class ActiveConnectionController extends AbstractCollector<ActiveConnectionMetric> {

	public ActiveConnectionController(int identifier) {
		super(identifier);
	}

	@Override
	public void loadMetric(Object[] args) throws Exception {
		// TODO Auto-generated method stub		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub		
	}

}
