package com.vic.aop.concurrent.test2;

/***************************************
 * @author:Alex Wang
 * @Date:2017/3/22 QQ:532500648
 * QQ交流群:286081824
 ***************************************/

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Future        ->代表的是未来的一个凭据
 * FutureTask    ->将你的调用逻辑进行了隔离
 * FutureService ->桥接 Future和 FutureTask
 */
public class SyncInvoker {

    public static void main(String[] args) throws InterruptedException {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            Unsafe unsafe = (Unsafe) field.get(null);
            System.out.println(unsafe);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}