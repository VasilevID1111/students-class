package com.example.studentclass.computers;

import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "computer")
public class ComputerDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "compId")
    private Integer compId;

    @Column(name = "name")
    private String name;

    @Column(name = "purpose")
    private String purpose;

    @Column(name = "isWorkable")
    private boolean isWorkable;
}
