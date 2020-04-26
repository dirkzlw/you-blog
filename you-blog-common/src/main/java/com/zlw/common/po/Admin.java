package com.zlw.common.po;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Dirk
 * @date 2020-04-18 8:29
 */
@Entity
@Table(name = "t_admin")
@Getter
@Setter
@ToString
public class Admin implements Serializable {

    //管理员ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer adminId;
    // 管理员账户名
    @Column(length = 40)
    private String username;
    // 管理员密码
    @Column(length = 100)
    private String password;

}
