package com.doubleview.spring;

import org.springframework.core.ResolvableType;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;
import org.testng.annotations.Test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * AbstractApplicationEventMulticaster 265 L
 */
public class ResolvableTypeTest {

    @Test
    public void test() {
        //原生方法获取泛型
        ParameterizedType parameterizedType = (ParameterizedType) ServiceImpl.class.getGenericInterfaces()[0];
        Type genericType = parameterizedType.getActualTypeArguments()[1];
        assertEquals(genericType, Integer.class);

        //使用ResolvedType
        ResolvableType serviceImplType = ResolvableType.forClass(ServiceImpl.class);
        assertEquals(serviceImplType.as(Service.class).resolveGeneric(1), Integer.class);

        //获取字段上的泛型
        ResolvableType serviceType = ResolvableType.forField(ReflectionUtils.findField(ServiceImpl.class, "service"));
        assertEquals(serviceType.resolveGeneric(1), Integer.class);

        ResolvableType listType = ResolvableType.forField(ReflectionUtils.findField(ServiceImpl.class, "list"));
        assertEquals(listType.resolveGeneric(0, 0), String.class);

        ResolvableType mapType = ResolvableType.forField(ReflectionUtils.findField(ServiceImpl.class, "map"));
        assertEquals(mapType.resolveGeneric(1, 1), Integer.class);

        //方法返回值的泛型信息
        ResolvableType returnType = ResolvableType.forMethodReturnType(ReflectionUtils.findMethod(ServiceImpl.class, "method"));
        assertEquals(returnType.resolveGeneric(1, 0), String.class);

        //构造器参数
        ResolvableType constructorType = ResolvableType.forConstructorParameter(ClassUtils.getConstructorIfAvailable(ServiceImpl.class, Map.class, List.class), 0);
        assertEquals(constructorType.resolveGeneric(1, 0), String.class);

        //数组
        ResolvableType arrayType = ResolvableType.forField(ReflectionUtils.findField(ServiceImpl.class, "array"));
        assertTrue(arrayType.isArray());
        assertEquals(arrayType.getComponentType().resolveGeneric(0), String.class);

        //自定义泛型
        ResolvableType customType = ResolvableType.forClassWithGenerics(List.class, String.class);
        ResolvableType customArrayType = ResolvableType.forArrayComponent(customType);
        assertEquals(customArrayType.getComponentType().getGeneric(0).resolve(), String.class);

        //泛型比较
        assertTrue(ResolvableType.forClass(Service.class).isAssignableFrom(ResolvableType.forClass(ServiceImpl.class)));
    }


    public interface Service<A, B>{
    }


    public static class ServiceImpl implements Service<String, Integer> {
        private Service<String, Integer> service;
        private List<List<String>> list;
        private Map<String, Map<String, Integer>> map;
        private List<String>[] array;

        public ServiceImpl() {
            super();
        }

        public ServiceImpl(Map<String, Map<String, Integer>> map, List<List<String>> list) {
            this.map = map;
            this.list = list;
        }

        private Map<String, List<String>> method() {
            return null;
        }
    }

}
