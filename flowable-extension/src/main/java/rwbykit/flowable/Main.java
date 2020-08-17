package rwbykit.flowable;

import rwbykit.flowable.core.FlowableFactory;

public class Main {


    public static void main(String[] args) {
        FlowableFactory flowableFactory = Flowable.byDefaultRegister().configure().byDefaultConfiguration().getFlowableFactory();
        flowableFactory.getProcessParserService().parse();
    }

}
