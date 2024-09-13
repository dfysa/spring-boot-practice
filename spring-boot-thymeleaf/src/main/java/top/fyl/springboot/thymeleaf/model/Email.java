package top.fyl.springboot.thymeleaf.model;

/**
 * @author dfysa
 * @data 2024/9/9 下午4:04
 * @description
 */

/**
 * @author dfysa
 * @data 2024/9/9 下午3:50
 * @description
 */
public class Email {
    private Long id;
    private String name;
    private String email;

    public Email(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
