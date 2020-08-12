package rwbykit.flowable.core.model;

import java.util.List;

public interface Process {

    public List<ViewPage> getViewPages();

    public List<Listener> getListeners();

    public String getId();

    public String getName();

    public String getStatus();

    public String getVersion();

    public String getDescription();

    public Property getProperty();

    public List<Node> getNodes();

}
