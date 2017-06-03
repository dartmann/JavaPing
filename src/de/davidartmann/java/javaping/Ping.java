package de.davidartmann.java.javaping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;

/**
 * Runnable class which calls system resources in dependence of the host
 * operating system to start an ICMP ping command.
 * 
 * @author david
 *
 */
public class Ping {

	private static final Logger LOG = Logger.getLogger(Ping.class.getSimpleName());

	public static void main(String[] args) throws IOException, InterruptedException {
		final String host = "192.168.0.1";
		String cmd = "";
		if (System.getProperty("os.name").startsWith("Windows")) {
			// For Windows
			cmd = "ping -n 1 " + host;
		} else {
			// For Linux and OSX
			cmd = "ping -c 1 " + host;
		}
		final Process myProcess = Runtime.getRuntime().exec(cmd);
		myProcess.waitFor();
		final BufferedReader br = new BufferedReader(new InputStreamReader(myProcess.getInputStream()));
		String line = null;
		Boolean b = false;
		LOG.info("Trying to ping host: " + host);
		while (true) {
			line = br.readLine();
			if (line == null) {
				break;
			} else {
				if (line.contains("TTL")) {
					b = true;
				}
			}
		}
		LOG.info("target reachable: " + b);
		br.close();
	}
}
