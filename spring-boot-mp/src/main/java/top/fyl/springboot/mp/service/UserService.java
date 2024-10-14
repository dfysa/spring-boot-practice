package top.fyl.springboot.mp.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.fyl.springboot.mp.mapper.UserMapper;
import top.fyl.springboot.mp.entity.User;

import java.util.List;

/**
 * @author dfysa
 * @data 2024/10/14 下午1:52
 * @description
 */
@Service
public class UserService extends ServiceImpl<UserMapper, User> {
    // 创建⽤户，⾃动填充创建时间和更新时间
    @Transactional
    public boolean createUser(User user) {
        user.setId(null); // 确保 ID 为 null
        user.setDeleted(0); // 确保 deleted 字段设置为 0
        try {
            return this.save(user);
        } catch (Exception e) {
            e.printStackTrace(); // 打印异常信息
            return false;
        }
    }

    // 更新⽤户，⾃动填充更新时间
    public boolean updateUser(User user) {
        return this.updateById(user);
    }

    // 逻辑删除⽤户
    public boolean deleteUserById(Long id) {
        // 使⽤逻辑删除
        return this.removeById(id);
    }

    // 根据ID查询⽤户
    public User getUserById(Long id) {
        // 查询单个⽤户
        return this.getById(id);
    }

    // 按名字模糊查询⽤户
    public List<User> getUsersByName(String name) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        // 查询未逻辑删除的数据
        queryWrapper.like(User::getName, name).eq(User::getDeleted, 0);
        return this.list(queryWrapper);
    }
    // 按名字模糊查询⽤户
    public List<User> getUsersByEmail(String email) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        // 查询未逻辑删除的数据
        queryWrapper.like(User::getEmail, email).eq(User::getDeleted, 0);
        return this.list(queryWrapper);
    }
    // 分⻚查询⽤户
    public Page<User> getUsersByPage(int currentPage, int pageSize) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        // 查询未逻辑删除的数据
        queryWrapper.eq(User::getDeleted, 0);
        return this.page(new Page<>(currentPage, pageSize), queryWrapper);
    }
}