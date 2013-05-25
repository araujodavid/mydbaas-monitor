package test;

import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.database.ActiveConnection;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.database.Size;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.host.HostStatus;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.machine.Cpu;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.repository.MetricRepository;

public class MainTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		SSHController sshController = SSHController.getInstance();
//		try {
//			Session session = sshController.createSessionWithKey("ec2-50-16-126-210.compute-1.amazonaws.com", "ubuntu", 22, "/home/david/Documents/davidtici.pem");
//			ChannelExec channelExec = sshController.openChannelShellCommands(session);
//			channelExec.setCommand("rm /home/ubuntu/tpch-alter.sql");
//			channelExec.connect();
//		} catch (JSchException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		Reflections reflections = new Reflections("main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.database");
//
//	    Set<Class<? extends AbstractDatabaseMetric>> subTypes = reflections.getSubTypesOf(AbstractDatabaseMetric.class);
//	    
//	    for (Class<? extends AbstractDatabaseMetric> class1 : subTypes) {
//			System.out.println(class1.getSimpleName());
//		}
		
		MetricRepository repository = new MetricRepository();
		
		String a = null;
		String b = null;
		String c = null;
		String d = null;
		
		try {
			a = repository.makeQuerySQL(Cpu.class, 1, "machine", MetricRepository.LAST_COLLECTION, null, null);
			b = repository.makeQuerySQL(HostStatus.class, 2, "host", MetricRepository.ALL_COLLECTION, "10-05-2013 12:15:00", "11-05-2013 12:15:00");
			c = repository.makeQuerySQL(ActiveConnection.class, 1, "dbms", MetricRepository.ALL_COLLECTION, "10-05-2013 12:15:00", null);
			d = repository.makeQuerySQL(Size.class, 11, "database", MetricRepository.ALL_COLLECTION, null, "11-05-2013 12:15:00");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(a);
		System.out.println("------------------------------");
		System.out.println(b);
		System.out.println("------------------------------");
		System.out.println(c);
		System.out.println("------------------------------");
		System.out.println(d);
		
		String e = "";
		String g = null;
		
		if (e.isEmpty()) {
			System.out.println("Is empty!");
		}
		if (g == null) {
			System.out.println("Is null!");
		}
	}

}
