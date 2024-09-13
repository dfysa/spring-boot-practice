package top.fyl.springboot.quickstart.service;

import org.springframework.stereotype.Service;
import top.fyl.springboot.quickstart.entity.Employee;

import java.util.List;

/**
 * @author dfysa
 * @data 2024/9/2 下午4:42
 * @description
 */
@Service
public class EmployeeService {

    private final List<Employee> employees = List.of(
            new Employee(1L, "小红", "上海"),
            new Employee(2L, "小明", "北京"),
            new Employee(3L, "小张", "重庆")
    );

    public String getAddressDetails(Long employeeId) {
        for (Employee employee : employees) {
            if (employee.getId().equals(employeeId)) {
                return """
                        员工户籍地:
                        %s
                        """.formatted(employee.getAddress());

            }
        }
        return "找不到员工";
    }
}