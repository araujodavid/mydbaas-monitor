package main.java.br.com.arida.ufc.mydbaasmonitor.core.util;

import java.util.Comparator;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.entity.DBaaS;

/**
 * Class to compare a DBaaS.
 * @author David Araújo
 * @version 1.0
 * @since March 21, 2013
 * 
 */

public class DBaaSComparator implements Comparator<DBaaS> {

	@Override
	public int compare(DBaaS firstDBaaS, DBaaS secondDBaaS) {
		if (firstDBaaS.getMachines().size() == secondDBaaS.getMachines().size()) {
			return 0;
		} else if (firstDBaaS.getMachines().size() > secondDBaaS.getMachines().size()) {
			return 1;
		} else {
			return -1;
		}		
	}

}
