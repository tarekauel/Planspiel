package AspectLogger;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public aspect AspectRandom {
	pointcut manipulateRandom() : 
		call( public static double java.lang.Math.random() );

	double around() : manipulateRandom() {
		StackTraceElement[] stack = Thread.currentThread().getStackTrace();
		for (int i = 0; i < stack.length; i++) {
			String classname = stack[i].getClassName();
			String methodName = stack[i].getMethodName();
			try {
				Class<?> c = Class.forName(classname);
				Method[] methodArray = c.getMethods();
				for (int j = 0; j < methodArray.length; j++) {
					Method m = methodArray[j];
					if (m.getName().equals(methodName)) {
						Annotation[] annArray = m.getAnnotations();
						for (int k = 0; k < annArray.length; k++) {
							Annotation a = annArray[k];
							Class<?> ac = a.annotationType();
							if (ac.getName().equals("AspectLogger.FakeRandom")) {
								return m.getAnnotation(FakeRandom.class).newRandom();
							}
						}
					}
				}
			} catch (ClassNotFoundException e) {

			}

		}
		return proceed();
	}

}
