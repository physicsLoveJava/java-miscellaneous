package com.lujian.spring.transaction.data.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "user")
@Data
public class User implements Serializable{

    @Id
    private Integer id;

    @Column
    private String name;

    @Column
    private String password;

    @Column
    private String isRookie;

}
