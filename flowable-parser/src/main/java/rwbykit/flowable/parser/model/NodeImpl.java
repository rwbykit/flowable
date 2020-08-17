package rwbykit.flowable.parser.model;

import rwbykit.flowable.core.model.parser.Link;
import rwbykit.flowable.core.model.parser.Listener;
import rwbykit.flowable.core.model.parser.Node;

import java.util.List;

public class NodeImpl implements Node {

    private String id;

    private String type;

    private String name;

    private String description;

    private List<Listener> listeners;

    private List<Link> links;

    @Override
    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public List<Listener> getListeners() {
        return listeners;
    }

    public void setListeners(List<Listener> listeners) {
        this.listeners = listeners;
    }

}
