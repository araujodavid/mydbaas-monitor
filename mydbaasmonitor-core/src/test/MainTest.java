package test;

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
		//System.out.println(repository.makeQuerySQL(Cpu.class, MetricRepository.METRIC_SINGLE_TYPE, 3, MetricRepository.LAST_COLLECTION, null, null));
		System.out.println(repository.makeQuerySQL(Cpu.class, MetricRepository.METRIC_MULTI_TYPE, 3, MetricRepository.LAST_COLLECTION, null, null));
	}

}
