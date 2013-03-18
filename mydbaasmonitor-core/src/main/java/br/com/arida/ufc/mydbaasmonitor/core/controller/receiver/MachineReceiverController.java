package main.java.br.com.arida.ufc.mydbaasmonitor.core.controller.receiver;

import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.Cpu;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.Memory;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.OperatingSystem;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.repository.MachineMetricRepository;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.repository.VirtualMachineRepository;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.view.DefaultStatus;

/**
 * 
 * Class that handles requests sent by the monitoring agents about machine.
 * @author Daivd Araújo
 * @version 3.0
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

	/**
	 * Method that receives information about the operating system and machine settings.
	 * @param metric - object relative to a metric
	 * @param machine - machine identifier where the metric was collected
	 */
	@Post("machine/info")
	public void information(OperatingSystem metric, int machine) {
		if (machineRepository.updateSystemInformation(metric, machine)) {
			status.accepted();
		}
	}
	
	// To create a new method receiver is necessary to call the object "metric". 
	// Add the parameters: machine (int) and recordDate (String).
	// The monitoring agents will send the data in this format.
	
	/**
	 * Method that receives the collection of CPU.
	 * @param metric - object relative to a metric
	 * @param machine - machine identifier where the metric was collected
	 * @param recordDate - date when it was collected
	 */
	@Post("machine/cpu")
	public void cpu(Cpu metric, int machine, String recordDate) {
		if (metricRepository.saveCpuMetric(metric, machine, recordDate)) {
			status.accepted();
		}		
	}
	
	/**
	 * Method that receives collections of Memory.
	 * @param metric - object relative to a metric
	 * @param machine - machine identifier where the metric was collected
	 * @param recordDate - date when it was collected
	 */
	@Post("machine/memory")
	public void memory(Memory metric, int machine, String recordDate) {
		if (metricRepository.saveMemoryMetric(metric, machine, recordDate)) {
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
