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
 * @date 2020-04-18 14:18
 */
@Entity
@Table(name = "t_notice")
@Getter
@Setter
@ToString
public class Notice implements Serializable{

    //公告ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer noticeId;
    // 公告内容
    @Column(length = 60)
    private String info;

    public Notice() {
    }

    public Notice(String info) {
        this.info = info;
    }
}
