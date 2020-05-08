package com.zlw.common.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Dirk
 * @date 2020-04-26 19:23
 */
@Getter
@Setter
public class BlogInfo {

    private Integer blogId;
    private String coverImgUrl;
    private String title;
    private String text;
    private String createTime;
    private Integer userId;
    private String username;
    private Integer zanNum;
    private Integer viewNum;
    private String artType;
    private Integer tagId;
    private String tag;
}
