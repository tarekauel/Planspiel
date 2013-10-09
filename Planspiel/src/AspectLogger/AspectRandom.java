package AspectLogger;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public aspect AspectRandom {
	String fakeRandomName = FakeRandom.class.getCanonicalName();
	
	pointcut manipulateRandom() : 
		call( public static double java.lang.Math.random() );
	
	pointcut manipulateNextGaussian() : 
		call( public double java.util.Random.nextGaussian());
	
	pointcut manipulateNextInt() : 
		call( public int java.util.Random.nextInt(..) );

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
								String[] methodNames = fakeRandom.mathRandomMethodName();
								for( int l=0; l < methodNames.length; l++ ) {
									String callerMethodName = stack[2].getClassName() + "." + stack[2].getMethodName();
									if( callerMethodName.equals( methodNames[l])) {
										return fakeRandom.mathRandomNewRandom()[l];
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
	
	int around() : manipulateNextInt() {
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
								String[] methodNames = fakeRandom.randomNextIntMethodName();
								for( int l=0; l < methodNames.length; l++ ) {
									String callerMethodName = stack[2].getClassName() + "." + stack[2].getMethodName();
									if( callerMethodName.equals( methodNames[l])) {
										return fakeRandom.randomNextIntNewRandom()[l];
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
	
	double around() : manipulateNextGaussian() {
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
								String[] methodNames = fakeRandom.randomNextGaussianMethodName();
								for( int l=0; l < methodNames.length; l++ ) {
									String callerMethodName = stack[2].getClassName() + "." + stack[2].getMethodName();
									if( callerMethodName.equals( methodNames[l])) {
										return fakeRandom.randomNextGaussianNewRandom()[l];
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
