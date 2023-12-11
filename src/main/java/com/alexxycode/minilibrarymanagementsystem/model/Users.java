package com.alexxycode.minilibrarymanagementsystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = ("users"), uniqueConstraints = @UniqueConstraint(columnNames = ("email")))
public class Users {

    @Id
    @SequenceGenerator(name = "user_generator",
            sequenceName = "user_generator",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "user_generator")
    private int id;
    @Email(message = "please enter a valid email address")
    private String email;
    @Column(name = ("full_name"))
    @NotBlank(message = ("fullname cannot be blank"))
    @Length(min = 6, max = 15, message = ("fullname has to be atleast 6 and atmost 15 characters long"))
    private String fullName;
   // @NotBlank(message = "Please age cannot be blank")
    @Min(value = 18,message = "age cannot be less than 18 years")
    @Max(value = 70,message = "age cannot be greater than 70 years")
    private int age;
    @NotBlank(message = "Address cannot be blank")
    @Length(max = 50, message = "Address should not be morethan  50 characters long")
    private String address;


    public Users(String email, String fullName, int age, String address) {
        this.email = email;
        this.fullName = fullName;
        this.age = age;
        this.address = address;

    }
}

