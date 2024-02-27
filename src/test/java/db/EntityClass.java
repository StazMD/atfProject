package db;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class EntityClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String name;

    @Column
    private Integer age;

    @Column(length = 255)
    private String email;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public EntityClass() {
    }

    public EntityClass(String name, Integer age, String email, LocalDateTime createdAt) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
