package rwbykit.flowable.model;

import java.util.List;
import java.util.Map;

public class Process {

    private String id;

    private String name;

    private String status;

    private String version;

    private String description;

    private Property property;

    private List<ViewPage> viewPages;

    private List<Listener> listeners;

    private Map<String, Node> nodes;

    public List<ViewPage> getViewPages() {
        return viewPages;
    }

    public void setViewPages(List<ViewPage> viewPages) {
        this.viewPages = viewPages;
    }

    public List<Listener> getListeners() {
        return listeners;
    }

    public void setListeners(List<Listener> listeners) {
        this.listeners = listeners;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public Map<String, Node> getNodes() {
        return nodes;
    }

    public void setNodes(Map<String, Node> nodes) {
        this.nodes = nodes;
    }
}
