package com.example.studentclass.computers;

import javax.persistence.*;

import com.example.studentclass.shedule.SheduleDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY,
    mappedBy = "computer")
    private List<SheduleDTO> visits = new ArrayList<>();
}
