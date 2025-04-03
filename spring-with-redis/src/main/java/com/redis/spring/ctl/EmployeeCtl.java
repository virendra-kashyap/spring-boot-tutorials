// package com.redis.spring.ctl;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.redis.spring.dto.EmployeeDTO;
// import com.redis.spring.service.EmployeeService;

// @RestController
// @RequestMapping("/api/employee/")
// public class EmployeeCtl {

// 	@Autowired
// 	private EmployeeService employeeService;
	
// 	@PostMapping("save")
// 	public String save(@RequestBody EmployeeDTO dto ) {
// 		employeeService.save(dto);
// 		return "Save Employee";
// 	}
	
// 	@GetMapping("list")
// 	public List<EmployeeDTO> list() {
// 		return employeeService.getEmployees();
// 	}

// }
