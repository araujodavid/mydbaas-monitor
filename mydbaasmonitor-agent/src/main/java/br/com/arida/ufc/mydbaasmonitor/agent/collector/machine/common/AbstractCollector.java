package main.java.br.com.arida.ufc.mydbaasmonitor.agent.collector.machine.common;

import java.util.TimerTask;

import main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity.common.AbstractMetric;

/**
 * 
 * @author Daivd Araújo
 * @version 1.2
 * @since March 5, 2013
 * 
 */

public abstract class AbstractCollector<T extends AbstractMetric> extends TimerTask {

	public abstract T loadMetric();	
	
}
