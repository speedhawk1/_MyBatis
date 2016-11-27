package service;

import mapper.UserMapper;
import model.User;
import org.apache.ibatis.session.SqlSession;
import util.MyBatisSession;

/**
 *
 * Created by Administrator on 2016/11/27.
 */
public class UserService {

    private static int createUserViaXML() {
        try (SqlSession sqlSession = MyBatisSession.getSqlSession(true)) {
            return sqlSession.insert("user.create", new UserService());
        }
    }

    private static int createUser() { // CoC Convention over Configuration 约定优于配置
        try (SqlSession sqlSession = MyBatisSession.getSqlSession(true)) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            return userMapper.create(new User(null, "user2", "password2"));
        }
    }

    public static void main(String[] args) {
        System.out.println(createUserViaXML());
//        createUser();
    }
}
