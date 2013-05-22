package main.java.br.com.arida.ufc.mydbaasframework.core.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import main.java.br.com.arida.ufc.mydbaasframework.common.metric.Machine;
import main.java.br.com.arida.ufc.mydbaasframework.common.resource.VirtualMachine;
import main.java.br.com.arida.ufc.mydbaasframework.core.repository.common.GenericRepository;

/**
 * @author David Araújo - @araujodavid
 * @version 1.0
 * @since May 22, 2013 
 */

public abstract class VirtualMachineRepository implements GenericRepository<VirtualMachine> {

	private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    
    public abstract List<VirtualMachine> getDBaaSMachines(int dbaasId);
    
    public abstract List<VirtualMachine> getHostMachines(int hostId);
    
    public abstract void saveWithoutHost(VirtualMachine resource);
    
    public abstract void updateWithoutHost(VirtualMachine resource);
    
    public abstract boolean updateSystemInformation(Machine system, int machine);
    
    public abstract void updatePassword(VirtualMachine resource);
}
