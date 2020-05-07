package com.zlw.common.po;

import com.zlw.common.utils.DateUtils;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Ranger
 * @create 2019-06-04 19:44
 * 积分规则
 * 1、每发布一篇原创或者翻译文章：可获得10分；
 * 2、每发布一篇转载文章：可获得2分；
 * 3、文章被点赞一次，获得1分
 * 4、文章阅读次数每超过100次，可获得1分
 */
@Entity
@Table(name = "t_user")
@Getter
@Setter
@ToString
public class User implements Serializable {
    //用户ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    //用户名
    @Column(length = 30, unique = true)
    private String username;
    //密码
    @Column(length = 100)
    private String password;
    //邮箱
    @Column(length = 40, unique = true)
    private String email;
    //积分，默认为0
    private Integer score = 0;
    //账户创建时间，默认为当前时间
    @Column(length = 40)
    private String createTime = DateUtils.getStringTime2();
    //用户签名
    @Column(length = 80)
    private String signStr = "这个人很懒，什么也没留下~";
    //头像URL
    @Column(length = 100)
    private String headImgUrl;
    //账户状态--1：可用  2：黑名单；默认为1
    private Integer status = 1;
    //关联的员工信息
    @OneToOne
    @JoinColumn(name = "staffId")
    private Staff staff;
    //关联的博客集合,new避免空指针异常
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<Blog> blogSet = new HashSet<>();
    //关联资源
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<Resource> resourceSet = new HashSet<>();

    public User() {
    }

    public User(String username, String password, String email, String headImgUrl, Staff staff) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.headImgUrl = headImgUrl;
        this.staff = staff;
    }
}
