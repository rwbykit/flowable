package rwbykit.flowable.parser;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import rwbykit.flowable.core.FlowableRuntimeException;
import rwbykit.flowable.core.model.parser.Process;
import rwbykit.flowable.core.service.ProcessParseService;
import rwbykit.flowable.core.util.Asserts;
import rwbykit.flowable.core.util.Strings;
import rwbykit.flowable.parser.tag.ProcessParser;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ParserServiceImpl implements ProcessParseService {

    private static final ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();

    private String[] paths;

    public ParserServiceImpl(String[] paths) {
        this.paths = paths;
    }

    @Override
    public void parse() {
        Asserts.nonEmpty(Arrays.asList(paths), " The configured resolution path cannot be empty !");
        parse(paths);
    }

    @Override
    public void parse(String... paths) {
        Asserts.nonEmpty(Arrays.asList(paths), " Resolution path cannot be empty !");
        doParse(paths);
    }

    protected static Map<String, List<Process>> doParse(String[] paths) {
        Map<String, List<Process>> processMap = doParse0(paths).stream()
                .sorted(Comparator.comparing(process -> Strings.builder(process.getId(), process.getVersion())))
                .collect(Collectors.groupingBy(process -> Strings.builder(process.getId(), "-", process.getVersion()), Collectors.toList()));

        List<String> repeatKeys = processMap.entrySet().stream()
                .filter(entry -> entry.getValue().size() != 1)
                .map(Map.Entry::getKey)
                .map(key -> Strings.replace(key, "-", "->"))
                .collect(Collectors.toList());
        if (repeatKeys.size() > 0) {

            throw new FlowableRuntimeException(" There are currently duplicate process versions as follows: \n " + repeatKeys);
        }
        return processMap;
    }

    private static List<Process> doParse0(String[] paths) {
        return new Parser(paths).parse();
    }


    static class Parser {
        private String[] paths;

        public Parser(String[] paths) {
            this.paths = paths;
        }

        public List<Process> parse() {
            Resource[] resource = resolveProcessLocations();
            return Arrays.asList(resource).stream().map(this::parse).collect(Collectors.toList());
        }

        private Process parse(Resource resource) {
            try {
                Document document = new SAXBuilder().build(resource.getInputStream());
                Element element = document.getRootElement();
                ProcessParser parser = ParserFactory.factory().getParser(element.getName());
                return parser.parse(element);
            } catch (Exception e) {
                throw new FlowableRuntimeException(e);
            }
        }

        private Resource[] resolveProcessLocations() {
            return Stream.of(Optional.ofNullable(this.paths).orElse(Strings.EMPTY_ARRAY))
                    .flatMap(location -> Stream.of(getResources(location)))
                    .toArray(Resource[]::new);
        }

        private Resource[] getResources(String location) {
            try {
                return resourceResolver.getResources(location);
            } catch (IOException e) {
                return new Resource[0];
            }
        }
    }
}
