package rwbykit.flowable.core.model.parser;

import java.util.List;

/**
 * 人工审批节点
 * @author Cytus_
 */
public interface ArtifactNode extends Node {

    public Assignment getAssignment();


    public List<ViewPage> getViewPages();


    public Property getProperty();

}
