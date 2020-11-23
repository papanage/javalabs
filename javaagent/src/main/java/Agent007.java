import java.lang.instrument.Instrumentation;

public class Agent007 {
    public static void premain(String args, Instrumentation instrumentation) {

        System.out.println("Hello! I`m java agent");
        MyTransformer myTransformer = new MyTransformer();
        instrumentation.addTransformer(myTransformer);
        System.out.println("count load: " + instrumentation.getAllLoadedClasses().length);
    }
}
