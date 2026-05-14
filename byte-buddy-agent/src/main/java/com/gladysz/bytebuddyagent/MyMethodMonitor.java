package com.gladysz.bytebuddyagent;

import net.bytebuddy.asm.Advice;

public class MyMethodMonitor {

    @Advice.OnMethodEnter
    public static void enter(@Advice.Origin Class clazz, @Advice.Origin("#m") String methodName) {
        System.out.println("Method called: " + clazz.getName() + "." + methodName);
    }

    @Advice.OnMethodExit
    public static void exit() {
        System.out.println("Exiting method.");
    }
}
