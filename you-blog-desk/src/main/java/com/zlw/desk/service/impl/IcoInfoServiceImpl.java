package com.zlw.desk.service.impl;

import com.zlw.common.po.IcoInfo;
import com.zlw.desk.dao.IcoInfoRepository;
import com.zlw.desk.service.IcoInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Dirk
 * @date 2020-05-08 7:04
 */
@Service(value = "icoInfoService")
public class IcoInfoServiceImpl implements IcoInfoService {

    @Autowired
    private IcoInfoRepository icoInfoRepository;

    /**
     * 根据文件后缀名查询对应图标信息
     * @param suf
     * @return
     */
    @Override
    public String findIcoInfoByIcoName(String suf) {
        IcoInfo icoInfo = icoInfoRepository.findByIcoName(suf);
        if(icoInfo == null){
            icoInfo = icoInfoRepository.findByIcoName("unknow");
        }
        return icoInfo.getIcoUrl();
    }
}
