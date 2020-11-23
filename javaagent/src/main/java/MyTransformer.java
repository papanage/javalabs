import javassist.*;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class MyTransformer implements ClassFileTransformer {
    private final ClassPool classPool;
    boolean isAdding = false;

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

                if (!isAdding) {
                    CtField minTime = new CtField(CtClass.floatType, "minTime", cc);
                    CtField maxTime = new CtField(CtClass.floatType, "maxTime", cc);
                    CtField avTime = new CtField(CtClass.floatType, "avTime", cc);
                    CtField c = new CtField(CtClass.intType, "c", cc);

                    minTime.setModifiers(Modifier.STATIC);
                    maxTime.setModifiers(Modifier.STATIC);
                    avTime.setModifiers(Modifier.STATIC);
                    c.setModifiers(Modifier.STATIC);


                    cc.addField(minTime);
                    cc.addField(maxTime);
                    cc.addField(avTime);
                    cc.addField(c);
                    isAdding = true;



                    CtMethod mm = cc.getDeclaredMethod("main");
                    mm.insertBefore("minTime =  Float.MAX_VALUE;");
                    mm.insertBefore("maxTime =  Float.MIN_VALUE;");
                    mm.insertBefore("avTime =  0.0;");
                    mm.insertBefore("c =  0;");


                    CtMethod pmm = cc.getDeclaredMethod("processTransaction");
                    pmm.addLocalVariable(
                            "startTime", CtClass.longType);
                    pmm.insertBefore(
                            "startTime = System.currentTimeMillis();");
                    pmm.insertAfter("c = c + 1;");

                    StringBuilder endBlock = new StringBuilder();

                    pmm.addLocalVariable("endTime", CtClass.longType);
                    pmm.addLocalVariable("opTime", CtClass.floatType);
                    endBlock.append(
                            "endTime = System.currentTimeMillis();");
                    endBlock.append(
                            "opTime = (float)(endTime-startTime)/1000.0;");
                    endBlock.append(
                            "if (opTime < minTime) {minTime = opTime;}");
                    endBlock.append(
                            "if (opTime > maxTime) {maxTime = opTime;}");
                    endBlock.append(
                            "avTime += opTime;");



                    pmm.insertAfter(endBlock.toString());
                    mm.insertAfter("{ System.out.println( \"mintime:\" + minTime); }");
                    mm.insertAfter("{ System.out.println( \"maxtime:\" + maxTime); }");
                    mm.insertAfter("{ System.out.println( \"avtime:\" + avTime/c); }");
            }
                cc.instrument(
                        new ExprEditor() {
                            public void edit(MethodCall m)
                                    throws CannotCompileException {
                                if (m.getMethodName().equals("processTransaction")) {
                                    m.replace("{ $1 = $1 + 99; $_ = $proceed($$); }");
                                }
                            }
                        });

                cc.writeFile();

            } catch(NotFoundException | CannotCompileException | IOException e){
                System.out.println("class: " + className);
                e.printStackTrace();
            }

        return classfileBuffer;
    }
}

