package com.joebrooks.whoareyou.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "log")
public class LogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_idx")
    private Long id;

    @CreationTimestamp
    @Column(name = "time")
    private Date time;

    @Column(name = "state")
    private int state;

    @Column(name = "device_name")
    private String deviceName;

    @ManyToOne
    @JoinColumn(name ="user_idx", referencedColumnName = "idx")
    private UserEntity user;

//  create table log(
//  log_idx int primary key auto_increment,
//  time timestamp not null,
//  state int not null,
//  device_name varchar(20) not null,
//  user_idx int,
//  foreign key(user_idx)
//  references user(idx)
//  on update cascade
//  on delete cascade);
//
}
