package ClientTools;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IP_Print {

	public static void main(String[] args) throws UnknownHostException {
		InetAddress addr = InetAddress.getLocalHost();
		System.out.println(addr.getHostAddress()+" "+addr.getHostName());
		
	}

}
