package com.atguigu.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.crud.bean.DepartmentExample.Criteria;
import com.atguigu.crud.bean.Employee;
import com.atguigu.crud.bean.EmployeeExample;
import com.atguigu.crud.dao.EmployeeMapper;
@Service
public class EmployeeService {
	@Autowired
	EmployeeMapper employeeMapper;
	
	public List<Employee> getAll() {
		// TODO Auto-generated method stub
		return employeeMapper.selectByExampleWithDept(null);
	}

	public void saveEmp(Employee employee) {
		// TODO Auto-generated method stub
		employeeMapper.insertSelective(employee);
	}

	public Employee getEmp(Integer empId) {
		// TODO Auto-generated method stub
		return employeeMapper.selectByPrimaryKeyWithDept(empId);
	}

	public void updateEmp(Employee employee) {
		// TODO Auto-generated method stub
		employeeMapper.updateByPrimaryKeySelective(employee);
	}

	public void deleteEmp(Integer empId) {
		// TODO Auto-generated method stub
		employeeMapper.deleteByPrimaryKey(empId);
	}

	public void deleteEmps(List<Integer> empIdList) {
		// TODO Auto-generated method stub
		EmployeeExample example = new EmployeeExample();
		com.atguigu.crud.bean.EmployeeExample.Criteria criteria = example.createCriteria();
		criteria.andEmpIdIn(empIdList);
		employeeMapper.deleteByExample(example);
	}

	public boolean checkuser(String empName) {
		// TODO Auto-generated method stub
		EmployeeExample example = new EmployeeExample();
		com.atguigu.crud.bean.EmployeeExample.Criteria criteria = example.createCriteria();
		criteria.andEmpNameEqualTo(empName);
		long count = employeeMapper.countByExample(example);
		return count == 0;
	}

	
}
