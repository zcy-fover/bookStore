package com.zcyfover.bookStore.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 反射的utils函数集合，提供访问私有变量，获取泛型类型class， 提取集合中元素属性等utils函数
 * 
 * @author zcy-fover
 *
 */
public class ReflectionUtils {

	/**
	 * 通过反射，获得定义Class时声明的父类的泛型参数的类型
	 * 
	 * @param clazz
	 * @param index
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Class getSuperClassGenricType(Class clazz, int index) {

		Type genType = clazz.getGenericSuperclass();
		if (!(genType instanceof ParameterizedType)) {
			return Object.class;
		}

		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

		if (index >= params.length || index < 0) {
			return Object.class;
		}

		if (!(params[index] instanceof Class)) {
			return Object.class;
		}

		return (Class) params[index];
	}

	/**
	 * 通过反射, 获得 Class 定义中声明的父类的泛型参数类型 如: public EmployeeDao extends
	 * BaseDao<Employee, String>
	 * 
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> Class<T> getSuperGenericType(@SuppressWarnings("rawtypes") Class clazz) {
		return getSuperClassGenricType(clazz, 0);
	}

	/**
	 * 循环向上转型，获取对象的DeclaredMethod
	 * 
	 * @param object
	 * @param methodName
	 * @param parameterTypes
	 * @return
	 */
	public static Method getDeclaredMethod(Object object, String methodName, Class<?>[] parameterTypes) {

		for (Class<?> superClass = object.getClass(); superClass != Object.class; superClass = superClass
				.getSuperclass()) {

			try {
				return superClass.getDeclaredMethod(methodName, parameterTypes);
			} catch (NoSuchMethodException e) {
				// Method 不在当前类定义，继续向上转型
			}

		}

		return null;
	}

	/**
	 * 时field变为可访问
	 * 
	 * @param field
	 */
	public static void makeAccessible(Field field) {
		if (!Modifier.isPublic(field.getModifiers())) {
			field.setAccessible(true);
		}
	}

	/**
	 * 循环型上转型，获取对象的DeclaredField
	 * 
	 * @param object
	 * @param fieldName
	 * @return
	 */
	public static Field getDeclaredField(Object object, String fieldName) {

		for (Class<?> superClass = object.getClass(); superClass != Object.class; superClass = superClass
				.getSuperclass()) {

			try {
				return superClass.getDeclaredField(fieldName);
			} catch (NoSuchFieldException e) {
				// Field不在当前类定义，继续向上转型
			}
		}

		return null;
	}

	/**
	 * 直接调用对象方法，而忽略修饰符(private, protected)
	 * 
	 * @param object
	 * @param methodName
	 * @param parameterTypes
	 * @param parameters
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static Object invokeMethod(Object object, String methodName, Class<?>[] parameterTypes, Object[] parameters)
			throws IllegalAccessException, InvocationTargetException {

		Method method = getDeclaredMethod(object, methodName, parameterTypes);

		if (method == null) {
			throw new IllegalArgumentException("没有发现方法 [" + methodName + "] 在目标对象 [" + object + "] 中！");
		}

		method.setAccessible(true);

		try {
			return method.invoke(object, parameters);
		} catch (IllegalArgumentException e) {
			System.out.println("不可能抛出的异常");
		}

		return null;

	}

	/**
	 * 直接设置对象属性值，忽略private和protected刺史府，也不经过setter
	 * 
	 * @param object
	 * @param fieldName
	 * @param value
	 * @throws IllegalAccessException
	 */
	public static void setFieldValue(Object object, String fieldName, Object value) throws IllegalAccessException {

		Field field = getDeclaredField(object, fieldName);

		if (field == null) {
			throw new IllegalAccessException("不能发现域 [" + fieldName + "] 在目标方法 [" + object + "]");
		}

		makeAccessible(field);

		try {
			field.set(object, value);
		} catch (IllegalAccessException e) {
			System.out.println("不可能抛出的异常");
		}

	}

	/**
	 * 直接读取对象的属性值, 忽略 private/protected 修饰符, 也不经过 getter
	 * @param object
	 * @param fieldName
	 * @return
	 */
	public static Object getFieldValue(Object object, String fieldName) throws IllegalAccessException {

		Field field = getDeclaredField(object, fieldName);

		if (field == null) {
			throw new IllegalAccessException("不能发现域 [" + fieldName + "] 在目标方法 [" + object + "]");
		}

		makeAccessible(field);

		Object result = null;

		try {
			result = field.get(object);
		} catch (IllegalAccessException e) {
			System.out.println("不可能抛出的异常！");
		}

		return result;

	}

}
