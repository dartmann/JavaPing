package de.davidartmann.javaping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ping {
	
	public static void main(String[] args) throws IOException, InterruptedException {
//		This does not work because of unsufficient rights:
//		----------------------------------------------------------------------------
//		InetAddress inetAddress = InetAddress.getByName("localhost");
//		System.out.println("Host is reachable: "+inetAddress.isReachable(1000));
//		System.out.println(inetAddress);
//		----------------------------------------------------------------------------
//		Instead we use the system existent resources:
//		
		String host = "192.168.0.1";
		String cmd = "";
		if(System.getProperty("os.name").startsWith("Windows")) {   
            // For Windows
            cmd = "ping -n 1 " + host;
	    } else {
            // For Linux and OSX
            cmd = "ping -c 1 " + host;
	    }
		Process myProcess = Runtime.getRuntime().exec(cmd);
	    myProcess.waitFor();
    	BufferedReader br = new BufferedReader(new InputStreamReader(myProcess.getInputStream()));
    	String line = null;
    	Boolean b = false;
    	while (true) {
    		line = br.readLine();
    		if (line == null) {
				break;
			} else {
				if (line.contains("TTL")) {
					b = true;
				}
			}
//    		System.out.println("line: "+line);
    	}
    	System.out.println("target reachable: "+b);
    	br.close();
	}
}
