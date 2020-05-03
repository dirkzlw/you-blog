package com.zlw.common.po;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * 博客评论
 * @author Dirk
 * @date 2020-05-03 16:08
 */
@Entity
@Table(name = "t_comment")
@Getter
@Setter
public class Comment implements Serializable {

    //主键id及生成策略
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;
    //评论时间
    @Column(length = 40)
    private String createTime;
    //评论的内容
    private String content;
    //与博客
    @ManyToOne
    @JoinColumn(name = "blogId")
    private Blog blog;
    //与用户
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    public Comment() {
    }

    public Comment(String createTime, String content, Blog blog, User user) {
        this.createTime = createTime;
        this.content = content;
        this.blog = blog;
        this.user = user;
    }
}
