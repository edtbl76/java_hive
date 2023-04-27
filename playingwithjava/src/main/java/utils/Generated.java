package utils;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

@Documented
@Retention(RUNTIME)
@Target({TYPE, METHOD, CONSTRUCTOR, LOCAL_VARIABLE})
public @interface Generated {
}
