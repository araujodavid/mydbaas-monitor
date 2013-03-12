package main.java.br.com.arida.ufc.mydbaasmonitor.core.controller.receiver;

import main.java.br.com.arida.ufc.mydbaasmonitor.core.entity.metric.Cpu;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.entity.metric.Memory;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.repository.MachineMetricRepository;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.view.DefaultStatus;

/**
 * 
 * Class that handles requests sent by the monitoring agents about machine.
 * @author Daivd Araújo
 * @version 1.0
 * @since March 10, 2013
 * 
 */

@Resource
public class MachineReceiverController {
	
	private MachineMetricRepository repository;
	private DefaultStatus status;
	
	public MachineReceiverController(MachineMetricRepository repository, DefaultStatus status) {
		this.repository = repository;
		this.status = status;
	}
	
	@Post("machine/cpu")
	public void cpu(Cpu cpu) {
		if (repository.saveCpuMetric(cpu)) {
			status.created();
		}		
	}
	
	@Post("machine/memory")
	public void memory(Memory memory) {
		if (repository.saveMemoryMetric(memory)) {
			status.created();
		}
	}
	
	@Post("machine/disk")
	public void disk() {
		//TODO!
	}

}
