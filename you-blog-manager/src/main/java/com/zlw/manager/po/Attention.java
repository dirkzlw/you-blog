package com.zlw.manager.po;

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
 * @date 2020-04-18 19:56
 */
@Entity
@Table(name = "t_attention")
@Getter
@Setter
@ToString
public class Attention implements Serializable {

    //管理员ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer attentionId;
    // 管理员账户名
    @Column(length = 100)
    private String imgUrl;

    public Attention() {
    }

    public Attention(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
