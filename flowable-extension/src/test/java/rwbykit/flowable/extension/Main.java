package rwbykit.flowable.extension;

import rwbykit.flowable.Flowable;
import rwbykit.flowable.core.FlowableFactory;

public class Main {


    public static void main(String[] args) {
        FlowableFactory flowableFactory = Flowable.byDefaultRegister().configure().byDefaultConfiguration().getFlowableFactory();
        flowableFactory.getProcessParserService().parse();
    }

}
