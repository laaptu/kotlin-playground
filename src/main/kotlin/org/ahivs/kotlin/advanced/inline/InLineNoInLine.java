package org.ahivs.kotlin.advanced.inline;

import org.jetbrains.annotations.NotNull;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

class InLineNoInLine {
    public static final void callTwo(@NotNull Function0 fn1, @NotNull Function1 fn2) {
        String var3 = "CallTwo Start";
        System.out.println(var3);
        fn1.invoke();
        fn2.invoke(10);
        var3 = "CallTwo End";
        System.out.println(var3);
    }

    public static final void main() {
        //Function0 fn1$iv = (Function0) null.INSTANCE;
        String var2 = "CallTwo Start";
        System.out.println(var2);
        //fn1$iv.invoke();
        int it = 10;
        String var6 = "This is fn2 input = " + it + " returning some string";
        System.out.println(var6);
        String.valueOf(it);
        var2 = "CallTwo End";
        System.out.println(var2);
    }
    // $FF: synthetic method
}
