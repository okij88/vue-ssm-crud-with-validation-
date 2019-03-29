package com.atguigu.crud.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.crud.bean.Department;
import com.atguigu.crud.bean.Employee;
import com.atguigu.crud.bean.Msg;
import com.atguigu.crud.service.DepartmentService;
import com.atguigu.crud.service.EmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;

	@RequestMapping("/emps/select")
	@ResponseBody
	public Msg getEmpsWithJson(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
		System.out.println(pn);
		PageHelper.startPage(pn, 15);
		List<Employee> emps = employeeService.getAll();
		PageInfo page = new PageInfo(emps, 5);
		return Msg.success().add("pageInfo", page);
	}

	@RequestMapping("/emps/add")
	@ResponseBody
	public Msg saveEmp(@RequestBody @Valid Employee employee,BindingResult result) {
		if(result.hasErrors()) {
			Map<String,Object> map = new HashMap<String, Object>();
			List<FieldError> errors = result.getFieldErrors();
			for(FieldError fieldError : errors) {
				System.out.println("´íÎó×Ö¶ÎÃû£º"+fieldError.getField());
				System.out.println("´íÎóÐÅÏ¢£º"+fieldError.getDefaultMessage());
				map.put(fieldError.getField(),fieldError.getDefaultMessage());
			}
			return Msg.fail().add("errorFields", map);
		}else {
			employeeService.saveEmp(employee);
			return Msg.success();
		}
	}

	@RequestMapping("/emp/select")
	@ResponseBody
	public Msg getEmp(@RequestParam(value = "empId") Integer empId) {
		Employee employee = employeeService.getEmp(empId);
		return Msg.success().add("emp", employee);
	}

	@RequestMapping("/emp/update")
	@ResponseBody
	public Msg updateEmp(@RequestBody Employee employee) {
		employeeService.updateEmp(employee);
		return Msg.success();
	}

	@RequestMapping("/emp/delete")
	@ResponseBody
	public Msg deleteEmp(@RequestParam(value = "empId") Integer empId) {
		employeeService.deleteEmp(empId);
		return Msg.success();
	}
	@RequestMapping("emps/delete")
	@ResponseBody
	public Msg deleteEmps(@RequestParam(value = "empIdList[]") List<Integer> empIdList) {
		System.out.println(empIdList);
		employeeService.deleteEmps(empIdList);
		return Msg.success();
	}
	
	@RequestMapping("/emp/checkuser")
	@ResponseBody
	public Msg checkuser(@RequestParam("empName")String empName) {
		boolean checkResult = employeeService.checkuser(empName);
		if(checkResult) {
			return Msg.success();
		}else {
			return Msg.fail();
		}
	}
//	@RequestMapping("/emps")
//	public String getEmps(@RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model) {
//		PageHelper.startPage(pn, 5);
//		List<Employee> emps = employeeService.getAll();
//		PageInfo page = new PageInfo(emps, 5);
//		model.addAttribute("pageInfo", page);
//
//		return "list";
//	}
}
