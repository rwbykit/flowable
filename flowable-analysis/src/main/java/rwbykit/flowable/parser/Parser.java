package rwbykit.flowable.parser;

import org.jdom2.Element;

public interface Parser<T> {

    T parse(Element element);

}
