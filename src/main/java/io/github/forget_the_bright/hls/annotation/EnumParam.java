package io.github.forget_the_bright.hls.annotation;


import java.lang.annotation.*;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnumParam {
    String value() default "";
}
