package com.zlw.common.po;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * @author Dirk
 * @date 2020-04-26 8:20
 */
@Entity
@Table(name = "t_blog")
@Getter
@Setter
public class Blog implements Serializable {

    //博客ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer blogId;
    //博客标题
    @Column(length = 40)
    private String title;
    //博客内容
    @Column(length = 10000)
    private String content;
    //博客内容简介
    @Column(length = 100)
    private String text;
    //创建时间
    @Column(length = 40)
    private String createTime;
    //文章类型：1-3 ：原创、转发、翻译
    private Integer artType;
    //封面图片的URL
    @Column(length = 100)
    private String coverImgUrl;
    //点赞数量，默认为0
    private Integer zanNum = 0;
    //阅读数量，默认为0
    private Integer viewNum = 0;
    //关联博客标签
    @ManyToOne
    @JoinColumn(name = "tagId")
    private Tag tag;
    //关联用户
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    //关联评论，级联删除
    @OneToMany(mappedBy = "blog", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Comment> commentList = new ArrayList<>();
}
