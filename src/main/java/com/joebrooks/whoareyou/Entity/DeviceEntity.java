package com.joebrooks.whoareyou.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "device")
public class DeviceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx")
    private Long idx;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name ="user_idx", referencedColumnName = "idx")
    private UserEntity user;

//  create table device(
//  idx int primary key auto_increment,
//  name varchar(30) not null,
//  user_idx int,
//  foreign key(user_idx)
//  references user(idx)
//  on update cascade
//  on delete cascade);




}
