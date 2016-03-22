package my.elasticsearch.test.es_shaded;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

// ES 1.7.4
//import org.elasticsearch.common.settings.ImmutableSettings;

public class AppTest {

	private static Client client;

	public static void main(String[] args) {

		String esNodesString = "hdp01.localdomain,hdp02.localdomain,hdp03.localdomain,hdp04.localdomain,hdp05.localdomain";
		List<String> esNodesList = Arrays.asList(esNodesString.split("\\s*,\\s*"));
		// List<InetSocketTransportAddress> esNodes = new
		// ArrayList<InetSocketTransportAddress>();

		// ES 1.7
		// Settings settings =
		// ImmutableSettings.settingsBuilder().put("cluster.name",
		// "elasticsearch").build();
		// TransportClient transportClient = new TransportClient(settings);

		// ES 2.2
		final Settings settings = Settings.settingsBuilder().put("cluster.name", "eshdp").build();
		TransportClient transportClient = TransportClient.builder().settings(settings).build();

		for (String esNode : esNodesList) {
			// ES 1.7
			// transportClient.addTransportAddress(new
			// InetSocketTransportAddress(esNode, 9300));
			try {
				// ES 2.2
				transportClient
						.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(esNode), 9300));
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
		}

		// for (DiscoveryNode dNode: transportClient.connectedNodes()) {
		// System.out.println(dNode.toString());
		// }

		client = transportClient;
		// client.prepareIndex();

		String toBeIndexed = "{\"sysID\":\"wds\",\"logType\":\"ui\",\"logTime\":\"2016-03-14T13:46:31.924+0800\",\"apID\":\"UIApp01V4\",\"functID\":\"FUNC_10002\",\"who\":\"機器人\",\"from\":\"iis\",\"at\":\"websphere\",\"to\":\"postgres\",\"action\":\"訂單成立\",\"result\":\"失敗\",\"kw\":\"玩命關頭\",\"msgLevel\":\"ERROR\",\"msg\":\"Unsufficient privilege\",\"msgCode\":\"5260\",\"table\":\"CODES\",\"dataCnt\":176,\"procTime\":80}";

		IndexResponse response = client.prepareIndex("aplog_aes3g_20160314", "batch").setSource(toBeIndexed).get();

		if (response.isCreated()) {
			String documentIndexId = response.getId();
			// Anchored
			System.out.println("OK");
		} else {
			System.out.println("FAILED");
		}

		client.close();
	}
}