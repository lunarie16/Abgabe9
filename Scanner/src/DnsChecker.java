import org.xbill.DNS.AAAARecord;
import org.xbill.DNS.ARecord;
import org.xbill.DNS.Lookup;
import org.xbill.DNS.TextParseException;
import static org.xbill.DNS.Type.*;

public class DnsChecker {
	private static String zone;

	public static void main(String[] args) throws TextParseException {
	zone = args[0];
	//	zone = "beuth-hochschule.de";

		// WEBMAIL
		System.out.println("Mailserver:");
		Lookup lookup = new Lookup(zone, MX);
		org.xbill.DNS.Record[] web = lookup.run();
		for (int i = 0; i < web.length; i++) {
			System.out.println(web[i].getAdditionalName());
		}

		System.out.println();
		System.out.println("DNS-Server:");
		// DNS-SERVER
		lookup = new Lookup(zone, NS);
		org.xbill.DNS.Record[] dns = lookup.run();
		for (int i = 0; i < dns.length; i++) {
			System.out.println(dns[i].getAdditionalName());
		}

		System.out.println();
		System.out.println("WWW-Server:");
		zone = "www." + zone;

		// IPv4
		lookup = new Lookup(zone, A);
		org.xbill.DNS.Record[] ipv4 = lookup.run();
		for (int i = 0; i < ipv4.length; i++) {
			System.out.println("IPv4: " + ((ARecord) ipv4[i]).getAddress().getHostAddress());
		}

		// IPv6
		lookup = new Lookup(zone, AAAA);
		org.xbill.DNS.Record[] ipv6 = lookup.run();
		if (ipv6.length > 0) {
			for (int i = 0; i < ipv6.length; i++) {
				System.out.println("IPv6: " + ((AAAARecord) ipv6[i]).getAddress().getHostAddress());
			}
		}
	}

}
