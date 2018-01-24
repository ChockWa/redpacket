package com.hdh.redpacket.core.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.Assert;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * BeanUtils
 * 
 */
public abstract class BeanUtils {
	private static Logger LOGGER = LoggerFactory.getLogger(BeanUtils.class);

	/**
	 * bean对象转换成map<br>
	 * 入参类支持com.fasterxml.jackson.annotation包下的注解
	 * 
	 * @param obj
	 *            例如实体类
	 * @return 返回Map对象
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> beanToMap(Object obj) {
		if (obj == null) {
			return null;
		}
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> objectAsMap = objectMapper.convertValue(obj, Map.class);
		return objectAsMap;
	}

	@SuppressWarnings("unchecked")
	public static Map<String, Object> beanToMapDeep(Object obj) {
		if (obj == null) {
			return null;
		}
		if (obj instanceof Map) {
			TempBean tb = new TempBean();
			tb.setObj(obj);
			ObjectMapper objectMapper = new ObjectMapper();
			Map<String, Object> objectAsMap = objectMapper.convertValue(tb, Map.class);
			return (Map<String, Object>) objectAsMap.get("obj");
		} else {
			return beanToMap(obj);
		}
	}

	private static class TempBean {
		private Object obj;

		@SuppressWarnings("unused")
		public Object getObj() {
			return obj;
		}

		public void setObj(Object obj) {
			this.obj = obj;
		}
	}

	/**
	 * bean对象转换成map,忽略指定字段<br>
	 * 入参类支持com.fasterxml.jackson.annotation包下的注解
	 * 
	 * <pre>
	 *	注： 请尽量使用com.ting.common.util.BeanUtils.beanToMap(Object) 并配合JsonIgnore注解排除字段
	 * </pre>
	 * 
	 * @param obj
	 *            实体对象
	 * @param ignoreFields
	 *            忽略掉的字段
	 * @return
	 */
	public static Map<String, Object> beanToMap(Object obj, List<String> ignoreFields) {
		if (obj == null) {
			return null;
		}
		Map<String, Object> objectAsMap = beanToMap(obj);

		if (ignoreFields != null) {
			for (String fieldName : ignoreFields) {
				objectAsMap.remove(fieldName);
			}
		}
		return objectAsMap;
	}

	/**
	 * bean对象转换成map,仅包含指定字段<br>
	 * 入参类支持com.fasterxml.jackson.annotation包下的注解
	 * 
	 * <pre>
	 *  注：请尽量使用com.ting.common.util.BeanUtils.beanToMap(Object) 并配合JsonIgnore注解排除字段
	 * </pre>
	 * 
	 * @param obj
	 *            实体对象
	 * @param inFields
	 *            包含字段
	 * @return
	 */
	public static Map<String, Object> beanToMapInFields(Object obj, List<String> inFields) {
		if (obj == null) {
			return null;
		}
		Map<String, Object> objectAsMap = beanToMap(obj);
		Map<String, Object> result = new HashMap<>();
		if (inFields != null) {
			for (String fieldName : inFields) {
				if (objectAsMap.containsKey(fieldName)) {
					result.put(fieldName, objectAsMap.get(fieldName));
				}
			}
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	public static <T> List<Map<String, Object>> beanToMapList(List<T> beans) {
		Map<String, Object> tmp = new HashMap<String, Object>();
		tmp.put("beans", beans);
		tmp = beanToMap(tmp);
		if (tmp.get("beans") == null) {
			return new ArrayList<Map<String, Object>>();
		} else {

			return (List<Map<String, Object>>) tmp.get("beans");
		}
	}

	@SuppressWarnings("unchecked")
	public static List<Map<String, Object>> beanToMapList(Object[] beans) {
		Map<String, Object> tmp = new HashMap<String, Object>();
		tmp.put("beans", beans);
		tmp = beanToMap(tmp);
		if (tmp.get("beans") == null) {
			return new ArrayList<Map<String, Object>>();
		} else {
			return (List<Map<String, Object>>) tmp.get("beans");
		}
	}

	/**
	 * 将map转换成bean
	 * 
	 * @param properties
	 * @param clazz
	 * @return
	 */
	public static <T> T mapToBean(Map<String, Object> properties, Class<T> clazz) {
		if (properties == null || properties.isEmpty()) {
			return null;
		}
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return objectMapper.convertValue(properties, clazz);
	}

	/***
	 * 将一个Bean或者Map复制到Map对象中
	 * 
	 * @param source
	 * @param target
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void copyToMap(Object source, Map target) {
		Map m = null;
		if (source instanceof Map) {
			m = (Map) source;
		} else {
			m = beanToMap(source);
		}
		target.putAll(m);
	}

	/**
	 * 复制bean属性到另一个bean
	 * 
	 * @param source
	 *            bean或者map
	 * @param target
	 *            只能是bean
	 */
	public static void copyToBean(Object source, Object target) {
		copyProperties(source, target, null, null);
	}

	/**
	 * 实例化知道的Class，并复制bean属性到该实例
	 * 
	 * @param <T>
	 * 
	 * @param source
	 *            bean或者map
	 * @param valueType
	 *            目标Class
	 */
	public static <T> T copyToNewBean(Object source, Class<T> valueType) {
		T target = org.springframework.beans.BeanUtils.instantiate(valueType);
		copyProperties(source, target, null, null);
		return target;
	}

	/***
	 * 将beanList转化为另外一个beanList
	 * 
	 * @param sourceList
	 * @param clazz
	 * @return
	 */
	public static <TReturnType, TSourceType> List<TReturnType> toBeanList(List<TSourceType> sourceList,
			Class<TReturnType> clazz) {
		List<TReturnType> resultList = new ArrayList<>();
		if (sourceList == null) {
			return resultList;
		}
		copyBeanList(sourceList, resultList, clazz);
		return resultList;
	}

	/***
	 * 复制beanList
	 * 
	 * @param sourceList
	 * @param returnList
	 * @param clazz
	 *            返回值类型： 必须有一个无参数的构造函数
	 */
	@SuppressWarnings("unchecked")
	public static <TReturnType, TSourceType> void copyBeanList(List<TSourceType> sourceList,
			List<TReturnType> returnList, Class<TReturnType> clazz) {
		if (clazz == null) {
			throw new RuntimeException("传入的返回值类型不能为空");
		}
		if (sourceList == null || returnList == null) {
			return;
		}
		Constructor<?> constructor;
		try {
			constructor = ((Class<? extends Object>) clazz).getDeclaredConstructor(new Class[] {});
			constructor.setAccessible(true);
			for (TSourceType s : sourceList) {
				TReturnType target = (TReturnType) constructor.newInstance();
				BeanUtils.copyToBean(s, target);
				returnList.add(target);
			}
		} catch (Exception e) {
			throw new RuntimeException("转化过程异常");
		}
	}

	/**
	 * 复制bean属性到另一个bean
	 * 
	 * @param source
	 *            bean或者map
	 * @param target
	 *            只能是bean
	 */
	public static void copyToBeanNotNullPreperties(Object source, Object target) {
		copyNotNullProperties(source, target, null, null);
	}

	/**
	 * 复制bean属性到另一个bean,仅包含指定字段
	 * 
	 * @param source
	 *            bean或者map
	 * @param target
	 *            只能是bean
	 * @param infields
	 */
	public static void copyToBeanInFields(Object source, Object target, List<String> infields) {

		copyProperties(source, target, infields, null);

	}

	/**
	 * 复制bean属性到另一个bean,忽略指定字段
	 * 
	 * @param source
	 *            bean或者map
	 * @param target
	 *            只能是bean
	 * @param ignoreFields
	 */
	public static void copyToBeanIgnoreFields(Object source, Object target, List<String> ignoreFields) {
		copyProperties(source, target, null, ignoreFields);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static void copyProperties(Object source, Object target, List<String> infields,
			List<String> ignoreProperties) {
		if (source == null || target == null) {
			return;
		}
		Object sourceObj = source;
		if (source instanceof Map) {
			Map sourceCopyMap = new HashMap((Map) source);// 复制副本

			Map map = sourceCopyMap;
			if (infields != null) {
				map = new HashMap<>();
				for (String infield : infields) {
					map.put(infield, sourceCopyMap.get(infield));
				}
			} else if (ignoreProperties != null) {
				for (String string : ignoreProperties) {
					map.remove(string);
				}
			}

			sourceObj = mapToBean(map, target.getClass());
		}

		copyProperties0(sourceObj, target, infields, ignoreProperties);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static void copyNotNullProperties(Object source, Object target, List<String> infields,
			List<String> ignoreProperties) {
		if (source == null || target == null) {
			return;
		}
		Object sourceObj = source;
		if (source instanceof Map) {
			Map sourceCopyMap = new HashMap((Map) source);// 复制副本

			Map map = sourceCopyMap;
			if (infields != null) {
				map = new HashMap<>();
				for (String infield : infields) {
					if (sourceCopyMap.get(infield) != null) {
						map.put(infield, sourceCopyMap.get(infield));
					}
				}
			} else if (ignoreProperties != null) {
				for (String string : ignoreProperties) {
					map.remove(string);
				}
			}

			sourceObj = mapToBean(map, target.getClass());
		}

		copyProperties0(sourceObj, target, infields, ignoreProperties);
	}

	/**
	 * 
	 * @param source
	 * @param target
	 * @param infields
	 * @param ignoreProperties
	 * 
	 * @see org.springframework.beans.BeanUtils.copyProperties(Object, Object,
	 *      Class<?>, String...)
	 */
	private static void copyProperties0(Object source, Object target, List<String> infields,
			List<String> ignoreProperties) {

		Assert.notNull(source, "Source must not be null");
		Assert.notNull(target, "Target must not be null");

		Class<?> actualEditable = target.getClass();
		// if (editable != null) {
		// if (!editable.isInstance(target)) {
		// throw new IllegalArgumentException("Target class [" +
		// target.getClass().getName()
		// + "] not assignable to Editable class [" + editable.getName() + "]");
		// }
		// actualEditable = editable;
		// }
		PropertyDescriptor[] targetPds = org.springframework.beans.BeanUtils.getPropertyDescriptors(actualEditable);

		for (PropertyDescriptor targetPd : targetPds) {
			if (targetPd.getWriteMethod() != null && (infields == null || infields.contains(targetPd.getName()))
					&& (ignoreProperties == null || (!ignoreProperties.contains(targetPd.getName())))) {

				PropertyDescriptor sourcePd = org.springframework.beans.BeanUtils
						.getPropertyDescriptor(source.getClass(), targetPd.getName());
				if (sourcePd != null && sourcePd.getReadMethod() != null) {
					try {
						Method readMethod = sourcePd.getReadMethod();
						if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
							readMethod.setAccessible(true);
						}
						Object value = readMethod.invoke(source);
						Method writeMethod = targetPd.getWriteMethod();
						if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
							writeMethod.setAccessible(true);
						}
						writeMethod.invoke(target, value);
					} catch (Throwable ex) {
						LOGGER.error("Could not copy properties from source to target", ex);
						throw new FatalBeanException("Could not copy properties from source to target", ex);
					}
				}

			}
		}
	}
}
