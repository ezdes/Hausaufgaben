package com.example.demo.Entity;
import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;
@Entity
@Table(name = "student")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "name", nullable = false)
    String name;
    @Column(name = "age", nullable = false)
    Integer age;
    @Column(name = "email", nullable = false, unique = true)
    String email;
    @Column(name = "phone_number", nullable = false, unique = true)
    String phoneNumber;
    @Column(name = "gender", nullable = false)
    String gender;
}
