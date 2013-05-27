package main.java.br.com.arida.ufc.mydbaasmonitor.core.util;

import java.util.Comparator;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.resource.DBaaS;

/**
 * Class to compare a DBaaS.
 * @author David Araújo - @araujodavid
 * @version 2.0
 * @since March 21, 2013
 * 
 */

public class DBaaSComparator implements Comparator<DBaaS> {

	@Override
	public int compare(DBaaS firstDBaaS, DBaaS secondDBaaS) {
		if (firstDBaaS.getHosts().size() == secondDBaaS.getHosts().size()) {
			if (firstDBaaS.getMachines().size() > secondDBaaS.getMachines().size()) {
				return 1;
			} else if (firstDBaaS.getMachines().size() < secondDBaaS.getMachines().size()) {
				return -1;
			} else {
				if (firstDBaaS.getDbmss().size() > secondDBaaS.getDbmss().size()) {
					return 1;
				} else if (firstDBaaS.getDbmss().size() < secondDBaaS.getDbmss().size()) {
					return -1;
				} else {
					if (firstDBaaS.getDatabases().size() > secondDBaaS.getDatabases().size()) {
						return 1;
					} else if (firstDBaaS.getDatabases().size() < secondDBaaS.getDatabases().size()) {
						return -1;
					} else {
						return 0;
					}
				}
			}			
		} else if (firstDBaaS.getHosts().size() > secondDBaaS.getHosts().size()) {
			return 1;
		} else {
			return -1;
		}	
	}
}
