package com.zlw.common.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Dirk
 * @date 2020-04-19 19:04
 */
@Getter
@Setter
@ToString
public class Page<T> implements Serializable {
    //页面的内容集合
    public List<T> content = new ArrayList<T>();
    //页面的当前页--从0开始计数
    public Integer page;
    //页面共多少页
    public Integer totalPages;
    //页面共多少数据
    private Integer totalElements;
    //一页显示多少数据
    private Integer pageSize;

    public Page() {
    }

    public Page(List<T> content, Integer page, Integer totalPages, Integer totalElements, Integer pageSize) {
        this.content = content;
        this.page = page;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.pageSize = pageSize;
    }
}
