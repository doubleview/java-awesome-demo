package com.doubleview.test;

import com.doubleview.model.Emp;
import com.doubleview.util.HibernateUtil;
import org.hibernate.Session;
import org.junit.Test;

/**
 * 测试一级缓存
 */
public class TestFirstCache {

    /**
     * 验证一级缓存
     */
    @Test
    public void test1() {
        Session session = HibernateUtil.getSession();
        Emp e1 = (Emp) session.get(Emp.class, 8);
        System.out.println(e1.getName());
        System.out.println("----------");
        Emp e2 = (Emp) session.get(Emp.class, 8);
        System.out.println(e2.getName());
        session.close();
    }

    /**
     * 验证一级缓存
     */
    @Test
    public void test2() {
        Session session = HibernateUtil.getSession();
        Emp e1 = (Emp) session.get(Emp.class, 8);
        System.out.println(e1.getName());
        Session session1 = HibernateUtil.getSession();
        Emp e2 = (Emp) session1.get(Emp.class, 8);
        System.out.println(e2.getName());
        session.close();
        session1.close();
    }


    /**
     * 验证缓存管理的方法
     */
    @Test
    public void test3() {
        Session session = HibernateUtil.getSession();
        Emp e1 = (Emp) session.get(Emp.class, 8);
        System.out.println(e1.getName());
        session.evict(e1);
        Emp e2 = (Emp) session.get(Emp.class, 8);
        System.out.println(e2.getName());
        session.close();
    }

    /**
     * 验证缓存管理的方法clear
     */
    @Test
    public void test4() {
        Session session = HibernateUtil.getSession();
        Emp e1 = (Emp) session.get(Emp.class, 8);
        System.out.println(e1.getName());
        session.clear();
        Emp e2 = (Emp) session.get(Emp.class, 8);
        System.out.println(e2.getName());
        session.close();
    }
}
