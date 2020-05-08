package com.zlw.common.po;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Dirk
 * @date 2020-05-07 7:42
 */
@Entity
@Table(name = "t_resource")
@Getter
@Setter
@ToString
public class Resource implements Serializable {

    //资源ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer resourceId;
    //资源名称
    @Column(length = 40)
    private String fileName;
    //下载需要的积分
    private Integer downScore;
    //资源描述
    @Column(length = 100)
    private String info;
    //文件大小
    @Column(length = 20)
    private String fileSize;
    //资源url
    @Column(length = 100)
    private String downUrl;
    //资源封面URL
    @Column(length = 100)
    private String coverImgUrl;
    //下载次数--默认为0
    private Integer downNum = 0;
    //关联用户
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

}
