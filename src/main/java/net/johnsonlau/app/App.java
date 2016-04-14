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
    public static void main(String[] args)
    {
        System.out.println("start...");

        // init elasticsearch transport client
        String clusterName = "johnsontest";
        String esHost = "localhost";
        int esPort = 9300;

        Settings settings = Settings.settingsBuilder()
            .put("cluster.name", clusterName)
            .put("client.transport.sniff", true)
            .put("client.transport.nodes_sampler_interval", "10s")
            .build();

        Client esclient = TransportClient.builder().settings(settings).build()
            .addTransportAddress(new InetSocketTransportAddress(new InetSocketAddress(esHost, esPort)));

        System.out.println("Worker elasticsearch client initialized.");
        esclient.close();

    }
}
