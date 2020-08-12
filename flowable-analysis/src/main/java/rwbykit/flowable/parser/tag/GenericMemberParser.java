package rwbykit.flowable.parser.tag;

import org.jdom2.Element;
import rwbykit.flowable.parser.AbstractParser;

public abstract class GenericMemberParser<T> extends AbstractParser<T> {
    @Override
    public T parse(Element element) {
        T object = this.getObject();
        this.fillByAttribute(object, element);
        return object;
    }

    public abstract T getObject();

}
