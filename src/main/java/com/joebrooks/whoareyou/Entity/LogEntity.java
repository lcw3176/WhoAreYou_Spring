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
    @Column(name = "idx")
    private Long idx;

    @CreationTimestamp
    @Column(name = "time")
    private Date time;

    @Column(name = "state")
    private int state;

    @ManyToOne
    @JoinColumn(name ="device_idx", referencedColumnName = "idx")
    private DeviceEntity device;

//  create table log(
//  idx int primary key auto_increment,
//  time timestamp not null,
//  state int not null,
//  device_idx int,
//  foreign key(device_idx)
//  references device(idx)
//  on update cascade
//  on delete cascade);

//  ALTER TABLE log CHANGE time time TIMESTAMP ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL;
}
