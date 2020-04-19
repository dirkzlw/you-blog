package com.zlw.manager.po;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Dirk
 * @date 2020-04-19 9:29
 */
@Entity
@Table(name = "t_staff")
@Getter
@Setter
@ToString
public class Staff implements Serializable {

    //员工ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer staffId;
    //工号
    @Column(length = 50)
    private String staffNo;
    //姓名
    @Column(length = 40)
    private String realname;
    //部门
    @Column(length = 40)
    private String department;
    //关联的用户
    @OneToOne(mappedBy = "staff")
    private User user;

    public Staff() {
    }

    public Staff(String staffNo, String realname, String department) {
        this.staffNo = staffNo;
        this.realname = realname;
        this.department = department;
    }
}
