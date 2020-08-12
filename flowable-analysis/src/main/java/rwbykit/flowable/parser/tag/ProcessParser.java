package rwbykit.flowable.parser.tag;

import org.jdom2.Element;
import rwbykit.flowable.core.model.Node;
import rwbykit.flowable.core.model.Process;
import rwbykit.flowable.core.util.Lists;
import rwbykit.flowable.core.util.Maps;
import rwbykit.flowable.parser.AbstractParser;
import rwbykit.flowable.parser.NodeConstants;
import rwbykit.flowable.parser.NodeName;
import rwbykit.flowable.parser.model.ProcessImpl;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@NodeName(NodeConstants.NODE_NAME_PROCESS)
public class ProcessParser extends AbstractParser<Process> {

    @Override
    public Process parse(Element element) {
        ProcessImpl process = new ProcessImpl();
        this.fillByAttribute(process, element);
        process.setViewPages(Lists.immutable(this.parseChildrens(element.getChildren(NodeConstants.NODE_NAME_VIEW_PAGE))));
        process.setListeners(Lists.immutable(this.parseChildrens(element.getChildren(NodeConstants.NODE_NAME_LISTENER))));
        List<Node> nodes = this.parseChildrens(element.getChildren(NodeConstants.NODE_NAME_NODE));
        Map<String, Node> nodeMap = new ConcurrentHashMap<>(nodes.size());
        nodes.forEach(node -> nodeMap.put(node.getId(), node));
        process.setNodes(Maps.immutable(nodeMap));
        process.setProperty(this.parseChildren(element.getChild(NodeConstants.NODE_NAME_PROPERTY)));
        return process;
    }
}
