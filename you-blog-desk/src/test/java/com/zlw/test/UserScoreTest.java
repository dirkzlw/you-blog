package com.zlw.test;

import com.zlw.common.po.Blog;
import com.zlw.common.po.User;
import com.zlw.desk.DeskApplication;
import com.zlw.desk.dao.UserRepository;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 统计用户积分
 *
 * @author Dirk
 * @date 2020-04-29 16:11
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DeskApplication.class)
public class UserScoreTest {

    @Autowired
    private UserRepository userRepository;

    //计算用户积分
    @Test
    public void compUserScore() {
        List<User> userList = userRepository.findAll();
        for (User user : userList) {
            int score = 0;
            List<Blog> blogList = user.getBlogList();
            for (Blog blog : blogList) {
                //计算规则1/2
                if (blog.getArtType() == 1 || blog.getArtType() == 3) {
                    score += 10;
                } else {
                    score += 2;
                }
                //规则3
                score += blog.getZanNum();
                //规则4
                score += blog.getViewNum() / 100;
            }
            user.setScore(score);
            userRepository.save(user);
        }
    }

}
