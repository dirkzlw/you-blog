package com.zlw.test;

import com.zlw.manager.ManagerApplication;
import com.zlw.manager.dao.StaffRepository;
import com.zlw.common.po.Staff;
import java.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Dirk
 * @date 2020-04-19 10:06
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ManagerApplication.class)
public class StaffTest {

    @Autowired
    private StaffRepository staffRepository;

    private static String[] surnames = {"赵", "钱", "孙", "李", "周", "吴", "郑", "王", "冯", "陈", "褚", "卫", "蒋", "沈", "韩", "杨", "朱", "秦", "尤", "许",
            "何", "吕", "施", "张", "孔", "曹", "严", "华", "金", "魏", "陶", "姜", "戚", "谢", "邹", "喻", "柏", "水", "窦", "章", "云", "苏", "潘", "葛", "奚", "范", "彭", "郎",
            "鲁", "韦", "昌", "马", "苗", "凤", "花", "方", "俞", "任", "袁", "柳", "酆", "鲍", "史", "唐", "费", "廉", "岑", "薛", "雷", "贺", "倪", "汤", "滕", "殷",
            "罗", "毕", "郝", "邬", "安", "常", "乐", "于", "时", "傅", "皮", "卞", "齐", "康", "伍", "余", "元", "卜", "顾", "孟", "平", "黄", "和",
            "穆", "萧", "尹", "姚", "邵", "湛", "汪", "祁", "毛", "禹", "狄", "米", "贝", "明", "臧", "计", "伏", "成", "戴", "谈", "宋", "茅", "庞", "熊", "纪", "舒",
            "屈", "项", "祝", "董", "梁", "杜", "阮", "蓝", "闵", "席", "季"};
    private static String[] names = {"碧凡", "夏菡", "曼香", "若烟", "半梦", "雅绿", "冰蓝", "灵槐", "平安", "书翠", "翠风", "香巧", "代云", "友巧", "听寒",
            "梦柏", "醉易", "访旋", "亦玉", "凌萱", "访卉", "怀亦", "笑蓝", "春翠", "靖柏", "书雪", "乐枫", "念薇", "靖雁", "寻春", "恨山", "从寒", "忆香",
            "觅波", "静曼", "凡旋", "新波", "代真", "新蕾", "雁玉", "冷卉", "紫山", "千琴", "恨天", "傲芙", "盼山", "怀蝶", "冰兰", "问旋", "从南", "白易",
            "问筠", "如霜", "半芹", "寒雁", "怜云", "寻文", "谷雪", "乐萱", "涵菡", "海莲", "傲蕾", "青槐", "冬儿", "易梦", "惜雪", "宛海", "之柔", "夏青"};

    private static String[] departments = {"市场部", "设计部", "开发部", "运营部", "测试部"};

    /**
     * 生成员工保存至数据库
     */
    @Test
    public void initStaff() {

        for (int i = 1; i <= 50; i++) {
            //不足3位补0
            String staffNo = String.format("%03d", i);
            //Math.random() * names.length外面的()是不可少的，不然会先执行(int)然后运运算*
            String realname = surnames[(int) (Math.random() * surnames.length)] + names[(int) (Math.random() * names.length)];
            String department = departments[(int) (Math.random() * departments.length)];

            Staff staff = new Staff(staffNo, realname, department);
            staffRepository.save(staff);

        }
    }

}
