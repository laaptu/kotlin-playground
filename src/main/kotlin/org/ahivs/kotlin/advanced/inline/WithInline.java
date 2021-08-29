package org.ahivs.kotlin.advanced.inline;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public class WithInline {
    public static final void withInline(@NotNull Function0 op) {
        Intrinsics.checkNotNullParameter(op, "op");
        String var2 = "NO INLINE";
        System.out.println(var2);
        var2 = "Before some operation";
        System.out.println(var2);
        op.invoke();
        var2 = "After some operation";
        System.out.println(var2);
    }

    public static final void main() {
        String var1 = "NO INLINE";
        System.out.println(var1);
        var1 = "Before some operation";
        System.out.println(var1);
        String var4 = "This is the main operation";
        System.out.println(var4);
        var1 = "After some operation";
        System.out.println(var1);
    }

    // $FF: synthetic method
    public static void main(String[] var0) {
        main();
    }
}
