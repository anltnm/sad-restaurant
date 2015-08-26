package com.se0865.sad.logic;

import com.se0865.sad.impl.EmployeeDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by AnLTNM-SE60906 on 20/05/2015.
 */
@Service
public class EmployeeManager {

    @Autowired
    private EmployeeDaoImpl employeeDao;

    public String getEmpName() {
        employeeDao.changeDepartment("abc");
        return "";
    }

}
