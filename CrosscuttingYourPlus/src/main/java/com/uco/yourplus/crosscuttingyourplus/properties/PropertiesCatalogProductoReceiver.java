package com.uco.yourplus.crosscuttingyourplus.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "yourplus.management.producto.response")
public class PropertiesCatalogProductoReceiver {
    private String exchange;

    private QueueProperties queue = new QueueProperties();

    private RoutingKeyProperties routingkey = new RoutingKeyProperties();

    @Data
    public static class QueueProperties {
        private String save;
        private String delete;
        private String update;
        private String list;
    }

    @Data
    public static class RoutingKeyProperties {
        private String save;
        private String delete;
        private String update;
        private String list;
    }
}
