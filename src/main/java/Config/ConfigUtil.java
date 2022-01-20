package Config;

import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;

/**
 * 读取配置文件
 * @author dai.zhihong
 * @date 2022年01月20日
 */
public class ConfigUtil {

    public static String getConfigValue(String tagName) {
        String tagValue = null;
        Properties prop = new Properties();
        try {
            ClassLoader classLoader = ConfigUtil.class.getClassLoader();
            // 读取配置文件dbconfig.properties
            InputStream in = classLoader.getResourceAsStream("dbconfig.properties");
            prop.load(in); // 加载属性列表
            Iterator<String> it = prop.stringPropertyNames().iterator();
            while (it.hasNext()) {
                if (it.next().equals(tagName)) {
                    tagValue = prop.getProperty(tagName);
                }
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tagValue;
    }

}