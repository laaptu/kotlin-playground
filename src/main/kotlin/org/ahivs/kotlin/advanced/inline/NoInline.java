package org.ahivs.kotlin.advanced.inline;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public class NoInline {
    public static final void noInline(@NotNull Function0 op) {
        Intrinsics.checkNotNullParameter(op, "op");
        String var1 = "NO INLINE";
        System.out.println(var1);
        var1 = "Before some operation";
        System.out.println(var1);
        op.invoke();
        var1 = "After some operation";
        System.out.println(var1);
    }

    public static final void main() {
        //noInline((Function0) null.INSTANCE);
    }
}
