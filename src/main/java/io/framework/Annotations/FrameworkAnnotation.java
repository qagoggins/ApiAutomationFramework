package io.framework.Annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({METHOD}) // You define on above of what things you can put this annotation
public @interface FrameworkAnnotation {
    String[] author() default "default author";
    String[] category() default "default category";
}
