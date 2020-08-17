package rwbykit.flowable.core.model.parser;

import java.util.List;

public interface Node {

    public List<Link> getLinks();


    public String getId();


    public String getType();


    public String getName();


    public String getDescription();


    public List<Listener> getListeners();

}
