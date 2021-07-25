package com.doubleview.test;

import com.doubleview.model.Emp;
import com.doubleview.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;

import java.util.Iterator;

/**
 * Created by 胡成超 on 2016/7/5.
 */
public class TestLazy {


    /**
     * load
     */
    @Test
    public void test1() {
        Session session = HibernateUtil.getSession();
        Emp emp = (Emp) session.load(Emp.class, 8);
        System.out.println("-------------");
        System.out.println(emp.getName());
        session.close();
    }

    /**
     * iterator
     */
    @Test
    public void test2() {
        String hql = "from Emp";
        Session session = HibernateUtil.getSession();
        Query query = session.createQuery(hql);

        Iterator<Emp> it = query.iterate();
        while (it.hasNext()) {
            Emp emp = it.next();
            System.out.println(emp.getName());
        }
        session.close();
    }

}
