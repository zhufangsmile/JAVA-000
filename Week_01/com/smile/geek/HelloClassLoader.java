package com.smile.geek;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class HelloClassLoader extends ClassLoader {
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        InputStream in = getClass().getResourceAsStream("Hello.xlass");
        try {
            byte[] bytes = new byte[in.available()];
            in.read(bytes);

            for (int i = 0; i < bytes.length; i++) {
                byte b = bytes[i];
                bytes[i] = (byte)(255 - b);
            }
            return defineClass(name, bytes, 0, bytes.length);
        } catch (IOException e) {
            throw new ClassNotFoundException(name);
        }
    }
    public static void main(String[] args) {
        HelloClassLoader classLoader = new HelloClassLoader();
        try {
            Class<?> aClass = classLoader.loadClass("Hello");
            Object instance = aClass.newInstance();
            Method method = aClass.getDeclaredMethod("hello");
            Object invoke = method.invoke(instance);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}