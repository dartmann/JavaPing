package de.davidartmann.javaping;

import java.io.IOException;

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
	    if(myProcess.exitValue() == 0) {
	    	System.out.println(true);
	    } else {
	    	System.out.println(false);
	    }
	}
}
