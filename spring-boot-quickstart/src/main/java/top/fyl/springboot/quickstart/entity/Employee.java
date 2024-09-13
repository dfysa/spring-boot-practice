package top.fyl.springboot.quickstart.entity;

/**
 * @author dfysa
 * @data 2024/9/5 下午9:37
 * @description
 */
public class Employee {
    private Long id;
    private String name;
    private String address;

    public Employee(Long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }
}
