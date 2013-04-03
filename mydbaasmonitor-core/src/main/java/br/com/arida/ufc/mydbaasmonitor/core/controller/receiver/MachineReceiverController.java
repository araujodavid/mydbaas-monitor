package main.java.br.com.arida.ufc.mydbaasmonitor.core.controller.receiver;

import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.Cpu;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.Machine;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.Memory;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.controller.receiver.common.AbstractReceiver;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.repository.MachineMetricRepository;
import main.java.br.com.arida.ufc.mydbaasmonitor.core.repository.VirtualMachineRepository;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.view.DefaultStatus;

/**
 * Class that handles requests sent by the monitoring agents about machine.
 * @author Daivd Araújo
 * @version 3.0
 * @since March 10, 2013 
 */

@Resource
@Path("/machine")
public class MachineReceiverController extends AbstractReceiver {
	
	private MachineMetricRepository metricRepository;
	private VirtualMachineRepository machineRepository;
	
	/**
	 * Constructor
	 * @param status
	 * @param metricRepository
	 * @param machineRepository
	 */
	public MachineReceiverController(DefaultStatus status, MachineMetricRepository metricRepository, VirtualMachineRepository machineRepository) {
		super(status);
		this.metricRepository = metricRepository;
		this.machineRepository = machineRepository;
	}

	/**
	 * Method that receives information about the operating system and machine settings.
	 * @param metric - object relative to a metric
	 * @param machine - machine identifier where the metric was collected
	 */
	@Post("/info")
	public void information(Machine metric, int identifier) {
		if (machineRepository.updateSystemInformation(metric, identifier)) {
			status.accepted();
		}
	}
	
	/*
	 * To create a new method receiver is necessary to call the object "metric". 
     * Add the parameters: machine (int) and recordDate (String).
     * The monitoring agents will send the data in this format.
	 */
	
	/**
	 * Method that receives the collection of CPU.
	 * @param metric - object relative to a metric
	 * @param machine - machine identifier where the metric was collected
	 * @param recordDate - date when it was collected
	 */
	@Post("/cpu")
	public void cpu(Cpu metric, int identifier, String recordDate) {
		if (metricRepository.saveCpuMetric(metric, identifier, recordDate)) {
			status.accepted();
		}		
	}
	
	/**
	 * Method that receives collections of Memory.
	 * @param metric - object relative to a metric
	 * @param machine - machine identifier where the metric was collected
	 * @param recordDate - date when it was collected
	 */
	@Post("/memory")
	public void memory(Memory metric, int identifier, String recordDate) {
		if (metricRepository.saveMemoryMetric(metric, identifier, recordDate)) {
			status.accepted();
		}
	}
	
	@Post("/disk")
	public void disk() {
		//TODO!
	}
	
	@Post("/network")
	public void network() {
		
	}

}
