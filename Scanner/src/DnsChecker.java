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
		int webmail = MX;
		Lookup lookup = new Lookup(zone, webmail);
		org.xbill.DNS.Record[] web = lookup.run();
		for (int i = 0; i < web.length; i++) {
			System.out.println(web[i].getAdditionalName());
		}

		System.out.println();
		System.out.println("DNS-Server:");
		// DNS-SERVER
		int dns_1 = NS;
		lookup = new Lookup(zone, dns_1);
		org.xbill.DNS.Record[] dns = lookup.run();
		for (int i = 0; i < dns.length; i++) {
			System.out.println(dns[i].getAdditionalName());
		}

		System.out.println();
		System.out.println("WWW-Server:");
		zone = "www." + zone;

		// IPv4
		int ipv4 = A;
		lookup = new Lookup(zone, ipv4);
		org.xbill.DNS.Record[] ip4 = lookup.run();
		for (int i = 0; i < ip4.length; i++) {
			System.out.println("IPv4: " + ((ARecord) ip4[i]).getAddress().getHostAddress());
		}

		// IPv6
		int ipv6 = AAAA;
		lookup = new Lookup(zone, ipv6);
		org.xbill.DNS.Record[] ip6 = lookup.run();
		if (ip6.length > 0) {
			for (int i = 0; i < ip6.length; i++) {
				System.out.println("IPv6: " + ((AAAARecord) ip6[i]).getAddress().getHostAddress());
			}
		}
	}

}
