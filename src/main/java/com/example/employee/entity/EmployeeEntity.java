package com.example.employee.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDate;

@SQLDelete(sql = "UPDATE EMP SET IS_DELETED = true WHERE ID = ? ")
@Where(clause = "IS_DELETED = false")
@Entity
@Table(name="EMP")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public enum Gender {
        MALE, FEMALE, OTHERS
    }

    @Column(unique=true, nullable=false, length=6)
    private String code;

    @Column(nullable=false, length=35)
    private String name;

    @Column(name="FATHER_NAME" , nullable=false, length=35)
    private String fatherName;

    @Column(nullable=false)
    private LocalDate dob;

    @Column(nullable=false)
    private LocalDate doj;

    @Column(unique = true, nullable=false, length=17)
    private String nid;

    @Enumerated(EnumType.STRING)
    @Column(length = 6, nullable = false)
    private Gender gender;

    @Email
    @Column(unique = true, length=50)
    private String email;

    @Column(name="MOBILE_NO", nullable = false, unique = true, length=14)
    private String mobileNo;

    @Column(name = "IS_ACTIVE", nullable = false)
    private boolean active;

    @Column(name = "IS_DELETED", nullable = false)
    private boolean deleted=false;


}