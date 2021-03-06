package org.sylrsykssoft.coreapi.framework.library.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

/**
 * Environment util.
 * 
 * @author juan.gonzalez.fernandez.jgf
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
@Component
public class EnvironmentUtil {

	@Autowired
	Environment environment;

	String port;
	String hostname;

	/**
	 * Get hostname.
	 *
	 * @return
	 */
	public String getHostname() throws UnknownHostException {
		// TODO ... would this cache cause issue, when network env change ???
		if (hostname == null) {
			hostname = InetAddress.getLocalHost().getHostAddress();
		}
		return hostname;
	}

	/**
	 * Get port.
	 *
	 * @return
	 */
	public String getPort() {
		if (port == null) {
			port = environment.getProperty("server.port");
		}
		return port;
	}

	/**
	 * Get port, as Integer.
	 *
	 * @return
	 */
	public Integer getPortAsInt() {
		return Integer.valueOf(getPort());
	}

	/**
	 * Getter server secure url prefix
	 * 
	 * @return
	 * @throws UnknownHostException
	 */
	public String getServerSecureUrlPrefi() throws UnknownHostException {
		return "https://" + getHostname() + ":" + getPort();
	}

	/**
	 * Getter server url prefix
	 * 
	 * @return
	 * @throws UnknownHostException
	 */
	public String getServerUrlPrefi() throws UnknownHostException {
		return "http://" + getHostname() + ":" + getPort();
	}
}
