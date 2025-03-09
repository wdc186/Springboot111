package org.example.springboot3.Controller;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data   // 自动生成 getter、setter、toString、equals 和 hashCode 方法
@NoArgsConstructor // 自动生成无参构造函数
@AllArgsConstructor // 自动生成全参构造函数
public class User {
    private Long id; // 用户唯一标识，通常为数据库中的主键
    private String name; //用户名
    private String email; //用户邮箱
    private String password; // 用户密码，注意：实际开发中需要加密存储
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") // 自定义日期格式
    private LocalDateTime registrationDate; // 用户注册时间
    private String status; // 用户状态（例如：激活、禁用）
}