/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cptools.ui;

import cd.youngfriend.web.utils.yjson.YJson;
import cptools.model.Project;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author zhangwei <laketea@163.com>
 */
public class Utils {

    public static List<Project> projects;

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

    public static void saveProjects() {
        if (projects != null && !projects.isEmpty()) {
            String data = "";
            for (Project pro : projects) {
                data += (pro.toString() + ";");
            }
            if (data.length() != 0) {
                data = data.substring(0, data.length() - 1);
            }
            saveParam("projects", data);
        }
    }

    public static List getData(String name) {
        if (projects != null) {
            return projects;
        }
        String pro = getParam(name);
        projects = new ArrayList<Project>();
        if (pro != null && !"".equals(pro)) {
            String[] pros = pro.split(";");
            for (String _pro : pros) {
                projects.add(Project.create(_pro.split(",")));
            }

        }
        return projects;
    }

    public static String getJsonProjects() {
        List list = getData("projects");
        String projects = "";

        if (list != null && !list.isEmpty()) {
            projects = YJson.listToJson(list);
        }

        return projects;
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
        value =(name + "," + path + ",0"+","+(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date())+","+(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()));
        projects.add(Project.create(value.split(",")));
        saveProjects();
    }

    public static void delete(String name, String path, String id) {
        if (projects != null && !projects.isEmpty()) {
            for (int i = projects.size()-1; i >= 0; i--) {
                if (path.equals(projects.get(i).getDir())) {
                    projects.remove(i);
                }
            }
        }
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
