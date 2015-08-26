package com.se0865.sad.impl;

import com.se0865.sad.dao.AbstractDAO;
import com.se0865.sad.dao.EmployeeDao;
import com.se0865.sad.entities.Employee;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by AnLTNM-SE60906 on 19/05/2015.
 */
@Repository("employeeDaoImpl")
public class EmployeeDaoImpl extends AbstractDAO<Employee, Long> implements EmployeeDao {

    public void changeDepartment(String department) {
        String q = "SELECT * FROM employee";
//        Query query = currentSession().createSQLQuery(q).setParameter("empDepartment", department);
        Query query = currentSession().createSQLQuery(q).addEntity(Employee.class);
        Employee employeeList = (Employee) query.list().get(0);
        System.out.println(employeeList.getName());
    }

}
