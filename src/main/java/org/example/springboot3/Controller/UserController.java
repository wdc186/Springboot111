package org.example.springboot3.Controller;

import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController // 用于定义该类为一个RESTful API控制器
@RequestMapping("/chapter02") // 映射路径，所有路径都将以 "/chapter02" 开头
public class UserController {
    // 模拟数据库，使用List<User>作为内存数据库来存储用户数据
    private List<User> users = new ArrayList<>();
    // 构造函数，用于初始化模拟数据
    public UserController() {
        // 初始化一些用户数据
        users.add(new User(1L, "u1", "u1@example.com", "123", LocalDateTime.now(), "ACTIVE"));
        users.add(new User(2L, "u2", "u2@example.com", "123", LocalDateTime.now().minusDays(5), "ACTIVE"));
        users.add(new User(3L, "u3", "u3@example.com", "123", LocalDateTime.now().minusDays(365), "ACTIVE"));
        users.add(new User(4L, "u4", "u4@example.com", "123", LocalDateTime.now().minusDays(100), "ACTIVE"));
        users.add(new User(5L, "u5", "u5@example.com", "123", LocalDateTime.now().minusDays(365*2), "ACTIVE"));
    }
    // 获取所有用户的列表
    @GetMapping("/users") // 映射GET请求，获取所有用户
    public List<User> getAllUsers() {
        return users; // 返回200状态码和用户列表
    }

    // 创建用户
    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        // 假设用户的ID是自增长的
        user.setId((long) (users.size() + 1)); // 自动生成ID
        users.add(user); // 将新用户添加到列表
        return user; // 直接返回创建的用户
    }

    // 更新指定ID的用户
    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        // 查找用户并更新其信息
        User existingUser = users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElse(null);
        if (existingUser == null) {
            // 用户未找到，返回404
            throw new RuntimeException("不存在的用户ID: " + id);
        }
        // 更新用户数据
        existingUser.setName(updatedUser.getName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPassword(updatedUser.getPassword());
        existingUser.setStatus(updatedUser.getStatus());
        existingUser.setRegistrationDate(LocalDateTime.now());
        // 返回更新后的用户数据
        return existingUser;
    }

    // 删除指定ID的用户
    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable Long id) {
        // 查找并删除指定ID的用户
        boolean removed = users.removeIf(user -> user.getId().equals(id));
        if (!removed) {
            return "不存在的用户ID: " + id; // 如果未找到用户，返回错误信息
        }
        return "用户ID【 " + id + " 】已删除"; // 返回删除成功的信息
    }
}
