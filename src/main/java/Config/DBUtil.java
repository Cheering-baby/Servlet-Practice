package Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 管理数据库连接
 * @author dai.zhihong
 * @date 2022年01月20日
 */
public class DBUtil {

    private static String user ="";
    private static String password = "";
    private static String url = "";
    private static String driver = "";
    private static Connection conn = null;

    /**
     * 获取数据库连接
     * @return Connection
     */
    public static Connection getConnection(){
        try{
            // 读取配置文件
            user = ConfigUtil.getConfigValue("jdbc.user");
            password = ConfigUtil.getConfigValue("jdbc.password");
            url = ConfigUtil.getConfigValue("jdbc.jdbcUrl");
            driver = ConfigUtil.getConfigValue("jdbc.driverClass");
            // 加载驱动
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
        }catch(Exception e){
            System.out.println("数据库连接异常");
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 关闭数据库连接
     * @param conn
     */
    public static void closeConnection(Connection conn){
        if(conn != null){
            try{
                conn.close();
            }catch(SQLException e){
                System.out.println("数据库关闭异常");
                e.printStackTrace();
            }
        }
    }

}