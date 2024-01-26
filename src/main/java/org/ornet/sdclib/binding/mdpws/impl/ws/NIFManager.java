/**
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Pulic License version 3.0.
 * http://www.gnu.org/licenses/gpl-3.0.de.html
 *
 */
/**
 * @author besting
 * @Copyright (C) SurgiTAIX AG
 */
package org.ornet.sdclib.binding.mdpws.impl.ws;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NIFManager {

    public interface NIFVisitor {

        void visit(NetworkInterface ni, InetAddress addr, String addrStr);
    }

    private final boolean ipv4, multicast;
    private final String bindInterface;

    public NIFManager(String bindInterface, boolean ipv4, boolean multicast) {
        this.ipv4 = ipv4;
        this.multicast = multicast;
        this.bindInterface = bindInterface;
    }

    public void applyVisitor(NIFVisitor visitor) {
        try {
            for (NetworkInterface ni : Collections.list(NetworkInterface.getNetworkInterfaces())) {
            	if (!ni.isUp()) {
            		continue;
            	}
            	
                if (ni.isLoopback() || (multicast && !ni.supportsMulticast())) {
                	checkIfIpIsBoundToInterfaceWriteLog(ni);
                	
                    continue;
                }
                for (InetAddress addr : Collections.list(ni.getInetAddresses())) {
                    if (ipv4 && !(addr instanceof Inet4Address)) {
                        continue;
                    }
                    final String addrStr = addr.toString().substring(1);
                    if ((addrStr.equals(bindInterface) || bindInterface.equals("0.0.0.0"))) {
                        visitor.visit(ni, addr, addrStr);
                    }
                }
            }
        } catch (SocketException ex) {
            Logger.getLogger(NIFManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

	private void checkIfIpIsBoundToInterfaceWriteLog(NetworkInterface ni) {
		for (InetAddress inetAddrs : Collections.list(ni.getInetAddresses())) {
			final String addrStr = inetAddrs.toString().substring(1);
			if (addrStr.equals(bindInterface)) {                			
				Logger.getLogger(NIFManager.class.getName()).severe(
						String.format("Ipadress %s found on interface %s but interface does "
						+ "not fullfil following requirements: is Up, is not Loopback "
						+ "and support Multicast", bindInterface, ni.getDisplayName()));
			}
		}
	}
}
