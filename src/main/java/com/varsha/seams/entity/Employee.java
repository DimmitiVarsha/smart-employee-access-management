package com.varsha.seams.entity;
import jakarta.validation.constraints.*;
import com.varsha.seams.enums.EmployeeStatus;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Employee {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;
	@NotBlank(message = "Employee name is required")
    @Column(nullable = false)
    private String employeeName;

	@Email(message = "Invalid email")
	@NotBlank(message = "Email is required")
    @Column(nullable = false, unique = true)
    private String email;
	
	@NotBlank(message = "Phone is required")
    @Column(nullable = false)
    private String phone;

    private String designation;
    
    @NotNull(message = "Salary is required")
    private Double salary;

    @Enumerated(EnumType.STRING)
    private EmployeeStatus status;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
}
