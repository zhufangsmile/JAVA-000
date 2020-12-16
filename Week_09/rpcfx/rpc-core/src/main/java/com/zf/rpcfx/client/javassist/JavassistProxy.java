package com.zf.rpcfx.client.javassist;

import com.zf.rpcfx.client.Invoker;
import com.zf.rpcfx.client.Proxy;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtField;
import javassist.CtMethod;
import javassist.Modifier;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhufang
 * @date 2020/12/15 11:03 上午
 */
public class JavassistProxy<T> implements Proxy<T> {
    private static final Map<Class, Class> PROXY_CLASS_MAP = new ConcurrentHashMap();


    private static final String PROXY_CLASSNAME_PREFIX = "$Proxy_";

    @Override
    public T create(Class<T> serviceClass, Invoker<T> invoker) {
        ClassLoader classLoader = serviceClass.getClassLoader();
        try {
            Class<T> proxyClass = PROXY_CLASS_MAP.get(serviceClass);
            if (proxyClass == null) {
                ClassPool classPool = ClassPool.getDefault();
                classPool.importPackage(Invoker.class.getName());
                classPool.importPackage(Method.class.getName());

                CtClass targetCtClass = classPool.get(serviceClass.getName());
                // 检查被代理类是否是 final的
                if ((targetCtClass.getModifiers() & Modifier.FINAL) == Modifier.FINAL) {
                    throw new IllegalArgumentException("class is final");
                }
                // 新建代理类
                CtClass proxyCtClass = classPool.makeClass(generateProxyClassName(serviceClass));
                proxyCtClass.setInterfaces(new CtClass[]{targetCtClass});
                // 设置描述符
                proxyCtClass.setModifiers(Modifier.PUBLIC | Modifier.FINAL);

                proxyCtClass.addField(CtField.make("private Invoker invoker;", proxyCtClass));
                // 添加构造器
                addConstructor(classPool, proxyCtClass);
                // 添加方法
                addMethod(proxyCtClass, targetCtClass, serviceClass, classPool);
                System.out.println(proxyCtClass.toString());
                // 从指定ClassLoader加载Class
                proxyClass = proxyCtClass.toClass(classLoader, null);
                // 缓存
                PROXY_CLASS_MAP.put(serviceClass, proxyClass);

                // 输出到文件保存，用于debug调试
                File outputFile = new File("$Proxy_UserService.class");
                proxyCtClass.writeFile(outputFile.getAbsolutePath());
                System.out.println(outputFile.getAbsolutePath());
                proxyCtClass.defrost();
            }

            // 实例化代理对象

            Class[] parameterTypes = new Class[1];
            parameterTypes[0] = Invoker.class;
            Constructor<?> constructor = proxyClass.getConstructor(parameterTypes);
            Object[] paramter = new Object[1];
            paramter[0] = invoker;
            return (T)constructor.newInstance(paramter);
        } catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }


    private static void addConstructor(ClassPool pool, CtClass proxyClass) throws Exception {

        CtClass[] parameterTypes = new CtClass[1];
        parameterTypes[0] = pool.get(Invoker.class.getName());
        CtConstructor ctConstructor = new CtConstructor(parameterTypes, proxyClass);
        ctConstructor.setBody("{this.invoker = $1;}");
        proxyClass.addConstructor(ctConstructor);
    }

//    class com.zf.xx.$Proxy_UserService implement UserService {
//        private Invoker invoker;
//        private static Method method1 = Class.forName("").getMethods()[0];
//        private static Method method2 = Class.forName("").getMethods()[1];
//
//        private com.zf.UserService method_1 =
//
//        public com.zf.xx.$Proxy_UserService(Invoker invoker) {
//            this.invoker = invoker;
//        }
//
//        public User findById(int id) {
//            invoker.invoke(method11, params1);
//        }
//
//        public List<User> findByName(String name) {
//
//            invoker.invoke(method2, params1);
//        }
//
//    }




    private static <T> void addMethod(CtClass proxyCtClass, CtClass targetCtClass, Class<T> targetClass, ClassPool classPool) throws Exception {

        Method[] methods = targetClass.getMethods();

        for (int i = 0; i < methods.length; i++) {
            Method method = methods[i];

            if (!Modifier.isAbstract(method.getModifiers())) {
                continue;
            }

            StringBuffer methodField = new StringBuffer();
            methodField.append("private static final Method method").append(i).append(" = ")
                    .append("Class.forName(\"").append(targetCtClass.getName())
                    .append("\")").append(".getMethods()[").append(i).append("];");


            CtField ctField = CtField.make(methodField.toString(), proxyCtClass);
            proxyCtClass.addField(ctField);


            Class<?>[] parameterTypes = method.getParameterTypes();
            CtClass[]  ctParameterTypes = new CtClass[parameterTypes.length];
            for (int j = 0; j < ctParameterTypes.length; j ++) {
                ctParameterTypes[i] = classPool.get(parameterTypes[i].getName());
            }


            CtMethod newCtMethod = new CtMethod(classPool.get(method.getReturnType().getName()), method.getName(), ctParameterTypes, proxyCtClass);

            StringBuffer methodBody = new StringBuffer();
            methodBody.append("return ($r)invoker.invoke(method").append(i).append(", $args);");

            newCtMethod.setBody(methodBody.toString());


            newCtMethod.setModifiers(Modifier.setPublic(1));

            proxyCtClass.addMethod(newCtMethod);
        }
    }



    private static String generateProxyClassName(Class<?> targetClass) {
        return targetClass.getPackage().getName() + "." + PROXY_CLASSNAME_PREFIX + targetClass.getSimpleName();
    }

}
