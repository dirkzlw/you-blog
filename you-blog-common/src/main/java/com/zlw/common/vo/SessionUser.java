package com.zlw.common.vo;

import com.zlw.common.po.Blog;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Dirk
 * @date 2020-05-01 9:31
 */
@Getter
@Setter
public class SessionUser implements Serializable {

    private Integer userId;
    private String username;
    private String email;
    private String headImgUrl;
    private String signStr;
    private Integer score;
    private Integer zanNum;
    private Set<Blog> blogSet;

    public SessionUser() {
    }

    public SessionUser(Integer userId, String username, String email, String headImgUrl, String signStr, Integer score, Integer zanNum, Set<Blog> blogSet) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.headImgUrl = headImgUrl;
        this.signStr = signStr;
        this.score = score;
        this.zanNum = zanNum;
        this.blogSet = blogSet;
    }
}
