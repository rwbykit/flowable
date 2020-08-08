package rwbykit.flowable.model;

import java.util.List;

/**
 * 人工审批节点
 * @author Cytus_
 */
public class ArtifactNode extends Node {

    private Assignment assignment;

    private List<ViewPage> viewPages;

    private Property property;

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    public List<ViewPage> getViewPages() {
        return viewPages;
    }

    public void setViewPages(List<ViewPage> viewPages) {
        this.viewPages = viewPages;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }
}
