package net.johnsonlau.app;

import java.net.InetSocketAddress;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.action.index.IndexResponse;
import static org.elasticsearch.common.xcontent.XContentFactory.*;
import org.elasticsearch.common.xcontent.XContentBuilder;

public class App 
{
    public static void main(String[] args) throws Exception
    {
        System.out.println("-- start ----");

        // init elasticsearch transport client
        String clusterName = "johnsontest";
        int esPort = 9300;
        String esHost = "localhost";
        //String keyStore = "/Users/gatement/app/search-guard-ssl/example-pki-scripts/kirk-keystore.jks";
        //String trustStore = "/Users/gatement/app/search-guard-ssl/example-pki-scripts/truststore.jks";
        //String pathHome = ".";
        String pathHome = ".";
        String keyStore = "kirk-keystore.jks";
        String trustStore = "truststore.jks";

        final Settings settings = Settings.settingsBuilder()
                .put("cluster.name", clusterName)
                .put("client.transport.sniff", true)
                .put("client.transport.nodes_sampler_interval", "10s")
                .build();

        Client esclient = TransportClient.builder().settings(settings).build()
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
