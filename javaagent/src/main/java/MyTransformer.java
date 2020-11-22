import javassist.ByteArrayClassPath;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.LoaderClassPath;
import javassist.NotFoundException;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class MyTransformer implements ClassFileTransformer {
    public static int count = 0;
    private final ClassPool classPool;

    public MyTransformer() {
      classPool = ClassPool.getDefault();
    }

    @Override
    public byte[] transform(ClassLoader loader,
                            String className,
                            Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) {

        try {
            CtClass cc = classPool.get("TransactionProcessor");


            CtMethod mm = cc.getDeclaredMethod("main");

            cc.addField(CtField.make("minTime",CtClass.floatType));

            mm.addLocalVariable(
                    "minTime", CtClass.floatType);
            mm.addLocalVariable(
                    "maxSize", CtClass.floatType);

            mm.insertBefore(
                    "minTime =  Float.MAX_VALUE();");
            mm.insertBefore(
                    "startTime = Float.MIN_VALUE();");




            cc.instrument(
                new ExprEditor() {
                    public void edit(MethodCall m)
                            throws CannotCompileException {
                        if (m.getMethodName().equals("processTransaction")){
                            m.replace("{ $1 = $1 + 99; $_ = $proceed($$); }");
                        }
                    }
                });

            cc.writeFile();
            } catch(NotFoundException | CannotCompileException | IOException e){
                System.out.println("class: " + className);
                e.printStackTrace();
            }

        count++;
        return classfileBuffer;
    }
}

