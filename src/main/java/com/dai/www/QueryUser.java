package com.dai.www;

import Config.DBUtil;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QueryUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json; charset=utf-8");
        resp.setCharacterEncoding("UTF-8");

        Connection conn = null;
        Statement stmt = null;
        List<User> users = new ArrayList<User>();
        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT * FROM user_profile";
            stmt = conn.createStatement();
            String SQL = "SELECT id, user_code FROM user_profile order by id limit 3 ";
            ResultSet rs = stmt.executeQuery(SQL);
            // 展开结果集数据库
            while(rs.next()) {
                // 通过字段检索
                int id = rs.getInt("id");
                String user_code = rs.getString("user_code");

                User user = new User();
                user.setId(id);
                user.setUserCode(user_code);
                users.add(user);
            }
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();

            String userJson = JSON.toJSONString(users);
            OutputStream out = resp.getOutputStream();
            out.write(userJson.getBytes(StandardCharsets.UTF_8));
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
