package com.dai.www;

import Config.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

public class QueryUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT * FROM user_profile";
            stmt = conn.createStatement();
            String SQL = "SELECT id, user_code FROM user_profile order by id limit 3 ";
            ResultSet rs = stmt.executeQuery(SQL);
            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                int id  = rs.getInt("id");
                String user_code = rs.getString("user_code");

                // 输出数据
                System.out.print("ID: " + id + " User Code: " + user_code);
                System.out.print("\n");
            }
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
