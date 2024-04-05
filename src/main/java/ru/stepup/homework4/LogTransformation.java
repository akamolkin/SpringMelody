package ru.stepup.homework4;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.METHOD;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface LogTransformation {
    String value() default "C:\\Temp\\logsTrans.log";
}
