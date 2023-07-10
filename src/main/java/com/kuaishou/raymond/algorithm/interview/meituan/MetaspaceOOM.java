package com.kuaishou.raymond.algorithm.interview.meituan;

import java.util.ArrayList;
import java.util.List;

import org.springframework.asm.ClassWriter;
import org.springframework.asm.MethodVisitor;
import org.springframework.asm.Opcodes;

/**
 * Author: raymond
 * CreateTime: 2023/6/21 14:30
 * 题目：使用代码模拟 Metaspace 的 OOM
 * -XX:MaxMetaspaceSize=32m -XX:CompressedClassSpaceSize=256m
 * 但是一直无法实现，只提示 Exception in thread "main" java.lang.OutOfMemoryError: Compressed class space
 */
public class MetaspaceOOM {
    public static void main(String[] args) {
        List<Class<?>> classList = new ArrayList<>();

        try {
            while (true) {
                byte[] bytecode = generateBytecode(); // 生成类的字节码
                CustomClassLoader classLoader = new CustomClassLoader();
                Class<?> generatedClass = classLoader.defineClass("com.example.GeneratedClass", bytecode);
                classList.add(generatedClass);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class CustomClassLoader extends ClassLoader {
        public Class<?> defineClass(String name, byte[] bytecode) {
            return defineClass(name, bytecode, 0, bytecode.length);
        }
    }

    private static byte[] generateBytecode() {
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        classWriter.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, "com/example/GeneratedClass", null,
                "java/lang/Object", null);

        MethodVisitor methodVisitor = classWriter.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
        methodVisitor.visitCode();
        methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);
        methodVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
        methodVisitor.visitInsn(Opcodes.RETURN);
        methodVisitor.visitMaxs(1, 1);
        methodVisitor.visitEnd();

        classWriter.visitEnd();
        return classWriter.toByteArray();
    }
}