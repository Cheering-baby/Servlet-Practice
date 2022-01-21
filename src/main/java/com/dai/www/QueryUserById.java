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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

// Ctrl-Alt-L format
// /j
/**
  * @author dai.zhihong
  * @date 2011/01/21
  * @description
  */
public class QueryUserById extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    try {
      if (req.getParameter("id") == null) {
        throw new ServletException();
      }

      int id = Integer.valueOf(req.getParameter("id")).intValue();

      System.out.println(id);

      Connection conn = null;
      Statement stmt = null;
      conn = DBUtil.getConnection();
      stmt = conn.createStatement();
      String SQL = "SELECT id, user_code FROM user_profile where id=" + id;
      ResultSet rs = stmt.executeQuery(SQL);
      User user = new User();
      while (rs.next()) {
        // 通过字段检索
        if (rs.getInt("id") == id) {
          String user_code = rs.getString("user_code");
          user.setUserCode(user_code);
          user.setId(rs.getInt("id"));
        }
      }

      // 完成后关闭
      stmt.close();
      conn.close();

      String userJson = JSON.toJSONString(user);
      OutputStream out = resp.getOutputStream();
      out.write(userJson.getBytes(StandardCharsets.UTF_8));
      out.flush();
      out.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
