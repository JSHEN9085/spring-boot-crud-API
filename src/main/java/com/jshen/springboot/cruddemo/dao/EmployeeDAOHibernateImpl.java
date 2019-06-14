package com.jshen.springboot.cruddemo.dao;

import java.util.List;
import com.jshen.springboot.cruddemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {

    //define field for entityManager
    private EntityManager entityManager;

    //set up constructor injection
    @Autowired
    public EmployeeDAOHibernateImpl(EntityManager theEntityManager){
        entityManager = theEntityManager;
    }

    @Override
    @Transactional
    public List<Employee> findAll(){

        //get the current Hibernate Session
        Session currentSession = entityManager unwrap(Session.class);

        //create query
        Query<Employee> theQuery = currentSession.createQuery("FROM employee" + Employee.class);

        //execute query and get result list
        List<Employee> employees = theQuery.getResultList();

        //return the result
        return employees;

    }
}
