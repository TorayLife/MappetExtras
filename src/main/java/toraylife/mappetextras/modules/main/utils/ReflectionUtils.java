package toraylife.mappetextras.modules.main.utils;

import java.lang.reflect.Method;
import java.util.Arrays;

public class ReflectionUtils {

    public static Method getMethod(Class<?> clazz, String methodName, Object... args) {
        try {
            Method method = clazz.getDeclaredMethod(methodName, Arrays.stream(args).map(Object::getClass).toArray(Class[]::new));
            method.setAccessible(true);
            return method;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Method getMethod(Class<?> clazz, String methodName) {
        return getMethod(clazz, methodName, new Object[0]);
    }

    public static Object getAndInvokeMethod(Class<?> clazz, String methodName, Object target, Object... args) {
        try {
            Method method = getMethod(clazz, methodName, args);
            return method.invoke(target, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object getAndInvokeMethod(Class<?> clazz, String methodName, Object target) {
        return getAndInvokeMethod(clazz, methodName, target, new Object[0]);
    }
}
