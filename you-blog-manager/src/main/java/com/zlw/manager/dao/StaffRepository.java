package com.zlw.manager.dao;

import com.zlw.manager.po.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Dirk
 * @date 2020-04-19 10:07
 */
public interface StaffRepository extends JpaRepository<Staff,Integer> {

    Staff findByStaffNo(String staffNo);

}
