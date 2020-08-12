package rwbykit.flowable.parser;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
@Repeatable(NodeName.NodeNames.class)
public @interface NodeName {

    String value();

    @Documented
    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.CLASS)
    public @interface NodeNames {
        NodeName[] value();
    }

}
