package com.zlw.manager.service.impl;

import com.zlw.manager.dao.StaffRepository;
import com.zlw.common.po.Staff;
import com.zlw.manager.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Dirk
 * @date 2020-04-19 10:38
 */
@Service(value = "staffService")
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffRepository staffRepository;

    /**
     * 根据工号获取员工信息
     * @param staffNo
     * @return
     */
    @Override
    public Staff findStaffByStaffNo(String staffNo) {
        return staffRepository.findByStaffNo(staffNo);
    }

}
