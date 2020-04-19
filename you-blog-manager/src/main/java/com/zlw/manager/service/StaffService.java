package com.zlw.manager.service;

import com.zlw.manager.po.Staff;

/**
 * @author Dirk
 * @date 2020-04-19 10:38
 */
public interface StaffService {

    Staff findStaffByStaffNo(String staffNo);

}
