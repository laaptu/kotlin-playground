package org.ahivs.java.streams;

import org.ahivs.kotlin.advanced.inline.Seconds;

import kotlin.time.Duration;

import static org.ahivs.kotlin.advanced.inline._05_inline_value_classKt.operateOnSeconds;


public class CallingInlineValue {
    public static void main(String... args) {
        // unable to create object for inline value class
        //Seconds seconds = new Seconds(1000);
        operateOnSeconds(1000);
    }
}
