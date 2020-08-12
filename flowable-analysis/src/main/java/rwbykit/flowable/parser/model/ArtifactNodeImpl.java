package rwbykit.flowable.parser.model;

import rwbykit.flowable.core.model.ArtifactNode;
import rwbykit.flowable.core.model.Assignment;
import rwbykit.flowable.core.model.Property;
import rwbykit.flowable.core.model.ViewPage;

import java.util.List;

/**
 * 人工审批节点
 *
 * @author Cytus_
 */
public class ArtifactNodeImpl extends NodeImpl implements ArtifactNode {

    private Assignment assignment;

    private List<ViewPage> viewPages;

    private Property property;

    @Override
    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    @Override
    public List<ViewPage> getViewPages() {
        return viewPages;
    }

    public void setViewPages(List<ViewPage> viewPages) {
        this.viewPages = viewPages;
    }

    @Override
    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }
}
