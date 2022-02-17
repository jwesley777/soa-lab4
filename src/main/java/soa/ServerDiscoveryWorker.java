package soa;

import com.google.common.net.HostAndPort;
import com.orbitz.consul.AgentClient;
import com.orbitz.consul.Consul;
import com.orbitz.consul.NotRegisteredException;
import com.orbitz.consul.model.agent.ImmutableRegistration;
import com.orbitz.consul.model.agent.Registration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collections;

//@Service
public class ServerDiscoveryWorker {
    private Consul client = null;
    @Value("${service.id}")
    private String serviceId = "";
    @Value("${server.port}")
    private int port = 0;
    @Value("${consul.host}")
    private String consulHost = "";


    @PostConstruct
    public void init()
    {
        try {
            client = Consul.builder().withHostAndPort(HostAndPort.fromParts(consulHost, 8500)).build();
            System.out.printf("Service id %s\n", serviceId);
            AgentClient agentClient = client.agentClient();
            Registration service = ImmutableRegistration.builder()
                    .id(serviceId)
                    .name("soa-lab2-server-1")
                    .port(port)
                    .check(Registration.RegCheck.ttl(30L)) // registers with a TTL of 3 seconds
                    //.meta(Collections.singletonMap("api", "api/cities"))
                    .build();

            agentClient.register(service);
            System.out.println("Init completed");
        } catch (Exception e) {
            System.err.println("Consul is unavailable");
        }
    }

//    @Scheduled(fixedDelay = 15000)
    public void checkIn() throws NotRegisteredException {
        AgentClient agentClient = client.agentClient();
        agentClient.pass(serviceId);
    }

}
