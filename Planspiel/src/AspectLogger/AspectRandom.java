package AspectLogger;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public aspect AspectRandom {
	String fakeRandomName = FakeRandom.class.getCanonicalName();
	
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

							if (ac.getName().equals(fakeRandomName)) {
								FakeRandom fakeRandom = m.getAnnotation(FakeRandom.class);
								String[] methodNames = fakeRandom.methodName();
								for( int l=0; l < methodNames.length; l++ ) {
									if( stack[2].getMethodName().equals( methodNames[l])) {
										return fakeRandom.newRandom()[l];
									}
								}											
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

