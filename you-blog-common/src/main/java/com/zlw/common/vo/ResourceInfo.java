package com.zlw.common.vo;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Dirk
 * @date 2020-05-08 10:38
 */
@Getter
@Setter
public class ResourceInfo implements Serializable {

    private Integer resourceId;
    private String coverImgUrl;
    private String fileName;
    private String info;
    private String username;
    private Integer downScore;
    private String fileSize;
    private Integer downNum;

}
