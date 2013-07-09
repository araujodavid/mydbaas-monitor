package main.java.br.com.arida.ufc.mydbaasmonitor.agent.collector.database;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.util.EntityUtils;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.collector.common.AbstractCollector;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.entity.ProcessStatusMetric;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.util.DatabaseConnection;
import main.java.br.com.arida.ufc.mydbaasmonitor.agent.util.ShellCommand;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.resource.DBMS;

/**
 * @author David Araújo - @araujodavid
 * @version 1.0
 * @since May 2, 2013
 */
public class ProcessStatusCollector extends AbstractCollector<ProcessStatusMetric> {

	public ProcessStatusCollector(int identifier, String type) {
		super(identifier, type);
	}

	@Override
	public void loadMetric(Object[] args) throws Exception {
		this.metric = ProcessStatusMetric.getInstance();
		DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
		for (DBMS dbms : databaseConnection.getDBMSs()) {
			if (dbms.getId() == args[0]) {
				switch (dbms.getType()) {
				case "MySQL":
					String[] mysql = ShellCommand.getProcessStatus(Long.parseLong(ShellCommand.getPidsFromName("mysqld")[0]));
					this.metric.setProcessStatusCpu(Double.valueOf(mysql[0]));
					this.metric.setProcessStatusMemory(Double.valueOf(mysql[1]));
					break;
				case "PostgreSQL":
					this.metric.setProcessStatusCpu(ShellCommand.getPostgreSQLCpuPercentage());
					this.metric.setProcessStatusMemory(ShellCommand.getPostgreSQLMemPercentage());
					break;
				}
			}
		}
	}

	@Override
	public void run() {
		this.metric = ProcessStatusMetric.getInstance();
		HttpResponse response;
		List<NameValuePair> params = null;
		
		//Checking the DBMSs enabled for collection
		if (this.metric.getDBMSs().length > 0) {
			for (String dbms : this.metric.getDBMSs()) {
				try {
					this.loadMetric(new Object[] {Integer.parseInt(dbms)});
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (Exception e) {
					System.out.println("Problem loading the ProcessStatus metric value (DBMS)");
					e.printStackTrace();
				}
				
				try {
					params = this.loadRequestParams(new Date(), Integer.parseInt(dbms), 0);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				try {
					response = this.sendMetric(params);
					System.out.println(response.getStatusLine());
					if (response.getStatusLine().getStatusCode() != 202) {
						System.out.println("Process status request error!");
						EntityUtils.consume(response.getEntity());
					}
					EntityUtils.consume(response.getEntity());
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
