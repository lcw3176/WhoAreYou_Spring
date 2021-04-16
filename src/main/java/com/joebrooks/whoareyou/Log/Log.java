package com.joebrooks.whoareyou.Log;

import com.joebrooks.whoareyou.User.User;
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
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "logid")
    private Long id;

    @CreationTimestamp
    @Column(name = "time")
    private Date time;

    @Column(name = "state")
    private int state;

    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;

}
