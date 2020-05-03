package com.zlw.common.vo;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Dirk
 * @date 2020-05-03 17:38
 */
@Getter
@Setter
public class CommentInfo implements Serializable {
    private Integer commentId;
    private String username;
    private String userHeadImgUrl;
    private String content;
    private String createTime;
    private String status = "success";

    public CommentInfo() {
    }

    public CommentInfo(Integer commentId, String username, String userHeadImgUrl,String content, String createTime) {
        this.commentId = commentId;
        this.username = username;
        this.userHeadImgUrl = userHeadImgUrl;
        this.content = content;
        this.createTime = createTime;
    }
}
