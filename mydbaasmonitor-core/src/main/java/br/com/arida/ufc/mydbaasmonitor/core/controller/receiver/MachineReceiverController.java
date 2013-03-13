package main.java.br.com.arida.ufc.mydbaasmonitor.core.controller.receiver;

import main.java.br.com.arida.ufc.mydbaasmonitor.core.entity.VirtualMachine;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.entity.metric.Cpu;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.entity.metric.Memory;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.repository.MachineMetricRepository;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.repository.VirtualMachineRepository;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.view.DefaultStatus;

/**
 * 
 * Class that handles requests sent by the monitoring agents about machine.
 * @author Daivd Araújo
 * @version 2.0
 * @since March 10, 2013
 * 
 */

@Resource
public class MachineReceiverController {
	
	private MachineMetricRepository metricRepository;
	private VirtualMachineRepository machineRepository;
	private DefaultStatus status;
	
	public MachineReceiverController(MachineMetricRepository metricRepository, VirtualMachineRepository machineRepository, DefaultStatus status) {
		this.metricRepository = metricRepository;
		this.machineRepository = machineRepository;
		this.status = status;
	}
	
	@Post("machine/info")
	public void information(VirtualMachine virtualMachine) {
		if (machineRepository.updateSystemInformation(virtualMachine)) {
			status.accepted();
		}
	}
	
	@Post("machine/cpu")
	public void cpu(Cpu cpu) {
		if (metricRepository.saveCpuMetric(cpu)) {
			status.accepted();
		}		
	}
	
	@Post("machine/memory")
	public void memory(Memory memory) {
		if (metricRepository.saveMemoryMetric(memory)) {
			status.accepted();
		}
	}
	
	@Post("machine/disk")
	public void disk() {
		//TODO!
	}
	
	@Post("machine/network")
	public void network() {
		
	}

}
