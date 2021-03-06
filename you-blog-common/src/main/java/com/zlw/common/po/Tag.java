package com.zlw.common.po;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Dirk
 * @date 2020-04-19 10:51
 */
@Entity
@Table(name = "t_tag")
@Getter
@Setter
@ToString
public class Tag implements Serializable {

    //标签ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tagId;
    //标签类别
    @Column(length = 40)
    private String type;
    //关联博客
    @OneToMany(mappedBy = "tag", fetch= FetchType.EAGER)
    private List<Blog> blogList = new ArrayList<>();

}
