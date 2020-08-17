package rwbykit.flowable.parser.model;

import rwbykit.flowable.core.model.parser.ViewPage;

/**
 * 审批业务挂在页面配置
 *
 * @author rwbykit
 */
public class ViewPageImpl implements ViewPage, Comparable<ViewPage> {

    private int order;

    /**
     * 页面路径
     */
    private String path;

    @Override
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public int compareTo(ViewPage o) {
        if (o instanceof ViewPageImpl) {
            return Integer.compare(this.order, ((ViewPageImpl) o).getOrder());
        }
        return -1;
    }
}
