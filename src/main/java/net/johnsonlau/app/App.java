package net.johnsonlau.app;

import java.net.InetSocketAddress;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.action.index.IndexResponse;
import static org.elasticsearch.common.xcontent.XContentFactory.*;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.shield.ShieldPlugin;

public class App 
{
    public static void main(String[] args) throws Exception
    {
        System.out.println("-- start ----");

        // init elasticsearch transport client
        String clusterName = "johnsontest";
        String esHost = "localhost";
        int esPort = 9300;

        final Settings settings = Settings.settingsBuilder()
                .put("cluster.name", clusterName)
                .put("client.transport.sniff", true)
                .put("client.transport.nodes_sampler_interval", "10s")
                .put("shield.user", "client1:johnson")
                .put("shield.ssl.keystore.path", "./client.jks")
                .put("shield.ssl.keystore.password", "123456")
                .put("shield.ssl.hostname_verification", "false")
                .put("shield.transport.ssl", "true")
                .build();

        Client esclient = TransportClient.builder()
            .addPlugin(ShieldPlugin.class)
            .settings(settings).build()
            .addTransportAddress(new InetSocketTransportAddress(new InetSocketAddress(esHost, esPort)));

        System.out.println("Worker elasticsearch client initialized.");

        String indexName = "javaindex1";
        String typeName = "javatype1";
        String body = "{\"name\": \"johnson\"}";
        IndexResponse response = esclient.prepareIndex(indexName, typeName).setSource(body).get();

        Thread.sleep(2000);
        esclient.close();
        System.out.println("-- end ------");
    }
}
