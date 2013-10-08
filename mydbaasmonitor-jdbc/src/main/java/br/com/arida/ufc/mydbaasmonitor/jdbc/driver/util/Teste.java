package main.java.br.com.arida.ufc.mydbaasmonitor.jdbc.driver.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.database.WorkloadStatus;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.resource.DBMS;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.resource.Database;
import main.java.br.com.arida.ufc.mydbaasmonitor.jdbc.driver.client.MyDriverClient;
import main.java.br.com.arida.ufc.mydbaasmonitor.jdbc.driver.collector.WorkloadStatusCollector;


public class Teste {
	
	public static String formatDate(Date date) {
		String formatedDate;
		Format formatter;
		formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		formatedDate = formatter.format(date);
		return formatedDate;		
	}

	public static void main(String[] args) {
		DBMS dbms = new DBMS();
		dbms.setId(1);
		dbms.setAddress("localhost");
		dbms.setPassword("root");
		dbms.setPort(3306);
		dbms.setType("MySQL");
		dbms.setUser("root");
		Database database = new Database();
		database.setId(1);
		database.setDbms(dbms);
		database.setName("tpch");
		
		String sql1 = "select s_acctbal, s_name, n_name, p_partkey, p_mfgr, s_address, s_phone, s_comment from part, supplier, partsupp, nation, region " +
					  "where p_partkey = ps_partkey and s_suppkey = ps_suppkey and p_size = 15 and p_type like '%BRASS' and s_nationkey = n_nationkey and n_regionkey = r_regionkey " +
				      "and r_name = 'EUROPE' and ps_supplycost = (select min(ps_supplycost) from partsupp, supplier, nation, region where p_partkey = ps_partkey and s_suppkey = ps_suppkey " +
				      "and s_nationkey = n_nationkey and n_regionkey = r_regionkey and r_name = 'EUROPE') order by s_acctbal desc, n_name, s_name, p_partkey;";
		
		String sql2 = "select l_orderkey, sum(l_extendedprice * (1 - l_discount)) as revenue, o_orderdate, o_shippriority from customer, orders, lineitem where c_mktsegment = 'BUILDING' " +
					  "and c_custkey = o_custkey and l_orderkey = o_orderkey and o_orderdate < date '1995-03-15' and l_shipdate > date '1995-03-15' group by l_orderkey, o_orderdate, o_shippriority " +
					  "order by revenue desc, o_orderdate;";
		
		String sql3 = "select supp_nation, cust_nation, l_year, sum(volume) as revenue from (select n1.n_name as supp_nation, n2.n_name as cust_nation, extract(year from l_shipdate) as l_year, " +
					  "l_extendedprice * (1 - l_discount) as volume from supplier, lineitem, orders, customer, nation n1, nation n2 where s_suppkey = l_suppkey and o_orderkey = l_orderkey and c_custkey = o_custkey " +
					  "and s_nationkey = n1.n_nationkey and c_nationkey = n2.n_nationkey and ((n1.n_name = 'FRANCE' and n2.n_name = 'GERMANY') or (n1.n_name = 'GERMANY' and n2.n_name = 'FRANCE'))) as shipping " +
					  "group by supp_nation, cust_nation, l_year order by supp_nation, cust_nation, l_year;";
		
		MyDriverClient client = new MyDriverClient("http://localhost:8080/mydbaasmonitor/");
		WorkloadStatusCollector workloadStatusCollector = new WorkloadStatusCollector(client, "workload/workloadstatus");
		
		
		String sql4 = "select * from lineitem limit 2000;";
		String sql5 = "select * from lineitem limit 3000;";
		String sql6 = "select * from lineitem limit 5000;";
		
		Object[] objects = null;
		try {
			objects = workloadStatusCollector.executeQuery(database, sql4);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		WorkloadStatus workloadStatus1 = (WorkloadStatus) objects[3];
		try {
			((ResultSet) objects[2]).close();
			((Statement) objects[1]).close();
			((Connection) objects[0]).close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		System.out.println("Query: "+workloadStatus1.getWorkloadStatusQuery());
		System.out.println("Tempo de resposta: "+workloadStatus1.getWorkloadStatusResponseTime());
		System.out.println("Seletividade: "+workloadStatus1.getWorkloadStatusSelectivity());
		System.out.println("Vazão: "+workloadStatus1.getWorkloadStatusThroughput());
		
		try {
		    Thread.sleep(4000);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		
		
		Object[] objects1 = null;
		try {
			objects1 = workloadStatusCollector.executeQuery(database, sql5);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		WorkloadStatus workloadStatus2 = (WorkloadStatus) objects1[3];
		try {
			((ResultSet) objects1[2]).close();
			((Statement) objects1[1]).close();
			((Connection) objects1[0]).close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		System.out.println("Query: "+workloadStatus2.getWorkloadStatusQuery());
		System.out.println("Tempo de resposta: "+workloadStatus2.getWorkloadStatusResponseTime());
		System.out.println("Seletividade: "+workloadStatus2.getWorkloadStatusSelectivity());
		System.out.println("Vazão: "+workloadStatus2.getWorkloadStatusThroughput());
		
		try {
		    Thread.sleep(4000);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		
		Object[] objects2 = null;
		try {
			objects2 = workloadStatusCollector.executeQuery(database, sql6);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		WorkloadStatus workloadStatus3 = (WorkloadStatus) objects2[3];
		try {
			((ResultSet) objects2[2]).close();
			((Statement) objects2[1]).close();
			((Connection) objects2[0]).close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		System.out.println("Query: "+workloadStatus3.getWorkloadStatusQuery());
		System.out.println("Tempo de resposta: "+workloadStatus3.getWorkloadStatusResponseTime());
		System.out.println("Seletividade: "+workloadStatus3.getWorkloadStatusSelectivity());
		System.out.println("Vazão: "+workloadStatus3.getWorkloadStatusThroughput());
		
		
		
		
//		System.out.println("Início: "+Teste.formatDate(new Date()));		
//		Object[] objects1 = null;
//		try {
//			objects1 = workloadStatusCollector.executeQuery(database, sql1);
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		WorkloadStatus workloadStatus1 = (WorkloadStatus) objects1[3];
//		try {
//			((ResultSet) objects1[2]).close();
//			((Statement) objects1[1]).close();
//			((Connection) objects1[0]).close();
//		} catch (SQLException e1) {
//			e1.printStackTrace();
//		}
//		System.out.println("Tempo de resposta: "+workloadStatus1.getWorkloadStatusResponseTime());
//		System.out.println("Seletividade: "+workloadStatus1.getWorkloadStatusSelectivity());
//		System.out.println("Vazão: "+workloadStatus1.getWorkloadStatusThroughput());
//		System.out.println("---------------------------------------------------------------------");
//		
//		
//		System.out.println("Início: "+Teste.formatDate(new Date()));		
//		Object[] objects2 = null;
//		
//		try {
//			objects2 = workloadStatusCollector.executeQuery(database, sql2);
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		WorkloadStatus workloadStatus2 = (WorkloadStatus) objects2[3];
//		try {
//			((ResultSet) objects2[2]).close();
//			((Statement) objects2[1]).close();
//			((Connection) objects2[0]).close();
//		} catch (SQLException e1) {
//			e1.printStackTrace();
//		}
//		System.out.println("Tempo de resposta: "+workloadStatus2.getWorkloadStatusResponseTime());
//		System.out.println("Seletividade: "+workloadStatus2.getWorkloadStatusSelectivity());
//		System.out.println("Vazão: "+workloadStatus2.getWorkloadStatusThroughput());
//		System.out.println("---------------------------------------------------------------------");
//		
//		System.out.println("Início: "+Teste.formatDate(new Date()));		
//		Object[] objects3 = null;
//		
//		try {
//			objects3 = workloadStatusCollector.executeQuery(database, sql3);
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		WorkloadStatus workloadStatus3 = (WorkloadStatus) objects3[3];
//		try {
//			((ResultSet) objects3[2]).close();
//			((Statement) objects3[1]).close();
//			((Connection) objects3[0]).close();
//		} catch (SQLException e1) {
//			e1.printStackTrace();
//		}
//		System.out.println("Tempo de resposta: "+workloadStatus3.getWorkloadStatusResponseTime());
//		System.out.println("Seletividade: "+workloadStatus3.getWorkloadStatusSelectivity());
//		System.out.println("Vazão: "+workloadStatus3.getWorkloadStatusThroughput());
	}	
}
