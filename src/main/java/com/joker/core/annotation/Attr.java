package com.joker.core.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME) // 注解会在class字节码文件中存在，在运行时可以通过反射获取到  
public @interface Attr {

	String dataType() default "varchar";

	int length() default 100;

	String column();

	String desc() default "";

	boolean pk() default false;

	String defaultValue() default "";

	boolean isNull() default true;

}
