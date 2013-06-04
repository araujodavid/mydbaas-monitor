package main.java.br.com.arida.ufc.mydbaasmonitor.agent.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 
 * @author Daivd Araújo - @araujodavid
 * @version 2.0
 * @since April 29, 2013
 * 
 */

public class ShellCommand {
	
	/**
	 * Method given a domain name is returned your pid
	 * @param nameDomain
	 * @return pid process
	 */
    public static long getDomainPid(String nameDomain) {
    	long result = 0;
        ProcessBuilder process = new ProcessBuilder(new String[]{"bash", "-c", "ps aux | awk '/kvm/ && /"+nameDomain+"/ && !/awk/ {print $2}'"});
        try {
            Process p = process.start();
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            //BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            String string = stdInput.readLine();
            return Long.parseLong(string);
        } catch (Exception ex) {
            return result;
        }
    }
    
	/**
	 * Method given a param is returned a list of active domains pid
	 * @param param
	 * @return a list with all active domains pid
	 */
    public static List<Integer> getAllDomainsPid(String param) {
    	List<Integer> allDomainsPid = new ArrayList<Integer>();
        ProcessBuilder process = new ProcessBuilder(new String[]{"bash", "-c", "ps aux | awk '/kvm/ && /"+param+"/ && !/awk/ {print $2}'"});
        try {
            Process p = process.start();
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            //BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            String string = "";
            int l = 0;
            while ((string = stdInput.readLine()) != null) {
                if (l == 0 && string != null) {
                    allDomainsPid.add(Integer.parseInt(string.trim()));
                }
                l++;
            }
        } catch (Exception ex) {
            return allDomainsPid;
        }
        return allDomainsPid;
    }
    
    /**
     * Method given a process pid is returned your cpu usage
     * @param domainPid
     * @return
     */
    public static double getProcessCpuPercentage(long domainPid) {
        double result = 0;
        ProcessBuilder process = new ProcessBuilder(new String[]{"bash", "-c", "top -p "+domainPid+" -b -n1 | tail -n+8 | sort -nr -k9 | awk '{print $9}'"});
        try {
            Process p = process.start();
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            //BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            String string = stdInput.readLine();
            return Double.parseDouble(string);
        } catch (Exception ex) {
            return result;
        }
    }
    
    /**
     * Method given a process pid is returned your memory usage
     * @param domainPid
     * @return
     */
    public static double getProcessMemPercentage(long domainPid) {
        double result = 0;
        ProcessBuilder process = new ProcessBuilder(new String[]{"bash", "-c", "top -p "+domainPid+" -b -n1 | tail -n+8 | sort -nr -k9 | awk '{print $10}'"});
        try {
            Process p = process.start();
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            //BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            String string = stdInput.readLine();
            return Double.parseDouble(string);
        } catch (Exception ex) {
            return result;
        }
    }
    
    public static double getPostgreSQLMemPercentage() {
        double result = 0;
        ProcessBuilder process = new ProcessBuilder(new String[]{"bash", "-c", "top -b -n1 -u postgres | awk 'NR>7 { sum += $10; } END { print sum; }'"});
        try {
            Process p = process.start();
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            //BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            String string = stdInput.readLine();
            return Double.parseDouble(string);
        } catch (Exception ex) {
            return result;
        }
    }
    
    public static double getPostgreSQLCpuPercentage() {
        double result = 0;
        ProcessBuilder process = new ProcessBuilder(new String[]{"bash", "-c", "top -b -n1 -u postgres | awk 'NR>7 { sum += $9; } END { print sum; }'"});
        try {
            Process p = process.start();
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            //BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            String string = stdInput.readLine();
            return Double.parseDouble(string);
        } catch (Exception ex) {
            return result;
        }
    }
    
    public static String[] getPostgreSQLStatus() {
    	String[] result = new String[2];
        ProcessBuilder process = new ProcessBuilder(new String[]{"bash", "-c", "top -b -n1 -u postgres | awk 'NR>7 { cpu += $9; mem += $10; } END { print cpu\" \"mem; }'"});
        try {
            Process p = process.start();
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            //BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            String string = stdInput.readLine();
            return string.split(Pattern.quote(" "));
        } catch (Exception ex) {
            return result;
        }
    }
    
    public static String[] getProcessStatus(long domainPid) {
    	String[] result = new String[2];
        ProcessBuilder process = new ProcessBuilder(new String[]{"bash", "-c", "top -p "+domainPid+" -b -n1 | tail -n+8 | sort -nr -k9 | awk '{print $9\" \"$10}'"});
        try {
            Process p = process.start();
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            //BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            String string = stdInput.readLine();
            return string.split(Pattern.quote(" "));
        } catch (Exception ex) {
            return result;
        }
    }
    
    public static String[] getPidsFromName(String processName) {
    	String[] result = null;
        ProcessBuilder process = new ProcessBuilder(new String[]{"bash", "-c", "pidof "+processName});
        try {
            Process p = process.start();
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            //BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            String string = stdInput.readLine();
            return string.split(Pattern.quote(" "));
        } catch (Exception ex) {
            return result;
        }
    }
    
    
}
