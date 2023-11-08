package com.uco.yourplus.crosscuttingyourplus.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "yourplus.management.laboratorio")
public class PropertiesCatalogLaboratorioProducer {
    private String exchange;
    private Queue queue = new Queue();
    private RoutingKey routingKey = new RoutingKey();

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public Queue getQueue() {
        return queue;
    }

    public void setQueue(Queue queue) {
        this.queue = queue;
    }

    public RoutingKey getRoutingKey() {
        return routingKey;
    }

    public void setRoutingKey(RoutingKey routingKey) {
        this.routingKey = routingKey;
    }

    public class Queue {
        private String save;
        private String delete;
        private String update;
        private String list;

        public String getSave() {
            return save;
        }

        public void setSave(String save) {
            this.save = save;
        }

        public String getDelete() {
            return delete;
        }

        public void setDelete(String delete) {
            this.delete = delete;
        }

        public String getUpdate() {
            return update;
        }

        public void setUpdate(String update) {
            this.update = update;
        }

        public String getList() {
            return list;
        }

        public void setList(String list) {
            this.list = list;
        }
    }

    public class RoutingKey {
        private String save;
        private String delete;
        private String update;
        private String list;

        public String getSave() {
            return save;
        }

        public void setSave(String save) {
            this.save = save;
        }

        public String getDelete() {
            return delete;
        }

        public void setDelete(String delete) {
            this.delete = delete;
        }

        public String getUpdate() {
            return update;
        }

        public void setUpdate(String update) {
            this.update = update;
        }

        public String getList() {
            return list;
        }

        public void setList(String list) {
            this.list = list;
        }
    }
}
