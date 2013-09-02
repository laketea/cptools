/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cptools.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author zhangwei <laketea@163.com>
 */
public class Utils {

    private static Properties loadp() {
        Properties pro = new Properties();
        File file = new File(new File("").getAbsolutePath() + "/config.properties");
        try {
            if (file.exists()) {
                FileInputStream is = new FileInputStream(file);
                pro.load(is);
                is.close();
                is = null;
            } else {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pro;
    }

    public static List getData(String name) {
        String pro = getParam(name);
        List list = new ArrayList();
        if (pro != null && !"".equals(pro)) {
            String[] pros = pro.split(";");
            for (String _pro : pros) {
                list.add(_pro.split(","));
            }
        }
        return list;
    }

    public static List getDirs() {
        String pro = getParam("dirs");
        List list = new ArrayList();
        if (pro != null && !"".equals(pro)) {
            String[] pros = pro.split(";");
            for (String _pro : pros) {
                list.add(_pro.split(","));
            }
        }
        return list;
    }

    public static String getParam(String name) {
        Properties pro = loadp();
        return pro.getProperty(name);
    }

    public static String getParam(String name, String defaultvalue) {
        String val = getParam(name);
        return val == null ? defaultvalue : val;
    }

    public static void push(String name, String path, String id) {
        String value = getParam(id);
        if (value != null && !"".equals(value)) {
            value += ";";
        } else {
            value = "";
        }
        value += (name + "," + path + ",0");
        saveParam(id, value);
    }

    public static void delete(String name, String path, String id) {
        String oldVlaue = getParam(id);
        String value = "";
        String[] arr = oldVlaue.split(";");

        for (int i = 0; i < arr.length; i++) {
            if (!arr[i].equals((name + "," + path + ",0"))) {
                value += (arr[i] + ";");
            }
        }
        if (value.length() != 0) {
            value = value.substring(0, value.length() - 1);
        }
        saveParam(id, value);
    }

    public static void saveParam(String name, String value) {
        FileOutputStream os = null;
        try {
            Properties prop = loadp();
            prop.setProperty(name, value);
            os = new FileOutputStream(new File(new File("").getAbsolutePath() + "/config.properties"));
            prop.store(os, "");
        } catch (Exception e) {
        } finally {
            if (os != null) {
                try {
                    os.close();
                    os = null;
                } catch (Exception e) {
                }
            }
        }
    }
}
