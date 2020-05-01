package com.zlw.common.vo;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Dirk
 * @date 2020-05-01 10:39
 */
@Getter
@Setter
public class ResultObj implements Serializable {
    private String status;
    private Integer objId;

    public ResultObj() {
    }

    public ResultObj(String status, Integer objId) {
        this.status = status;
        this.objId = objId;
    }
}
