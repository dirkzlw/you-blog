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
 * @date 2020-05-07 14:51
 */
@Entity
@Table(name = "t_ico_info")
@Getter
@Setter
@ToString
public class IcoInfo implements Serializable {

    //管理员ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer icoId;
    // 管理员账户名
    @Column(length = 40)
    private String icoName;
    // 管理员密码
    @Column(length = 100)
    private String icoUrl;

    public IcoInfo() {
    }

    public IcoInfo(String icoName, String icoUrl) {
        this.icoName = icoName;
        this.icoUrl = icoUrl;
    }
}
