package rwbykit.flowable.parser.model;

import rwbykit.flowable.core.model.Listener;
import rwbykit.flowable.core.model.Node;
import rwbykit.flowable.core.model.Process;
import rwbykit.flowable.core.model.Property;
import rwbykit.flowable.core.model.QuickQuery;
import rwbykit.flowable.core.model.ViewPage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProcessImpl implements Process, QuickQuery {

    private String id;

    private String name;

    private String status;

    private String version;

    private String description;

    private Property property;

    private List<ViewPage> viewPages;

    private List<Listener> listeners;

    private Map<String, Node> nodes;

    @Override
    public List<ViewPage> getViewPages() {
        return viewPages;
    }

    public void setViewPages(List<ViewPage> viewPages) {
        this.viewPages = viewPages;
    }

    @Override
    public List<Listener> getListeners() {
        return listeners;
    }

    public void setListeners(List<Listener> listeners) {
        this.listeners = listeners;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    @Override
    public List<Node> getNodes() {
        return new ArrayList<>(nodes.values());
    }

    public void setNodes(Map<String, Node> nodes) {
        this.nodes = nodes;
    }

    @Override
    public <T> T find(String property, String id) {
        if ("nodes".equals(property)) {
            return (T) nodes.get(id);
        }
        return null;
    }
}
