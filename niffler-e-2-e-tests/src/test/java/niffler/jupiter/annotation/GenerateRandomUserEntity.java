package niffler.jupiter.annotation;

import niffler.jupiter.extension.DBType;
import niffler.jupiter.extension.GenerateRandomUserEntityExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static niffler.jupiter.extension.DBType.HIBERNATE;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface GenerateRandomUserEntity {
    DBType dbType() default HIBERNATE;
}
