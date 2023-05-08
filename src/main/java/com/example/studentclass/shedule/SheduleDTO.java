package com.example.studentclass.shedule;

import com.example.studentclass.computers.ComputerDTO;
import com.example.studentclass.users.UserDTO;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "visit")
public class SheduleDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "visit_id")
    private Integer visit_id;

    @Column(name = "date")
    private Date date;

    @Column(name = "timed_blocks")
    private String timed_blocks;

    @Column(name = "status")
    private String status;

    @ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
    @JoinColumn(name = "comp_id")
    private ComputerDTO computer;

    @ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserDTO user;
}
