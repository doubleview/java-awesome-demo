package com.doubleview.test;

import com.doubleview.model.Emp;
import com.doubleview.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;

import java.util.List;


/**
 * 测试二级缓存
 */
@SuppressWarnings("JpaQlInspection")
public class TestSecondCache {


    /**
     * 二级缓存
     */
    @Test
    public void test1() {
        Session session1 = HibernateUtil.getSession();
        Emp e1 = (Emp) session1.get(Emp.class, 2);
        System.out.println(e1);
        System.out.println("-----------------");

        HibernateUtil.getSessionFactory().evict(Emp.class);


        Session session2 = HibernateUtil.getSession();
        Emp e2 = (Emp) session2.get(Emp.class, 2);
        System.out.println(e2);

        session1.close();
        session2.close();
    }


    /**
     * 查询缓存
     */
    @Test
    public void test2() {
        Session session = HibernateUtil.getSession();
        String hql = "from Emp";
        Query query = session.createQuery(hql);
        //开启查询缓存
        query.setCacheable(true);
        List<Emp> emps = query.list();
        for (Emp e : emps) {
            System.out.println(e);
        }

        System.out.println("----------------");

        //清除查询缓存
        HibernateUtil.getSessionFactory().evictQueries();

        hql = "from Emp";
        query = session.createQuery(hql);
        //开启查询缓存
        query.setCacheable(true);
        emps = query.list();
        for (Emp e : emps) {
            System.out.println(e);
        }
        session.close();
    }

}
