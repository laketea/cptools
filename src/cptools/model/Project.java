/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cptools.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author zhangwei <laketea@163.com>
 */
public class Project {

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private String name;
    private String dir;
    private String status = "";
    private String lastUpdate = "";
    private String lastBuild = "";

    public static Project create(String[] arr) {
        Project prj = new Project();
        prj.setName(arr[0]);
        prj.setDir(arr[1]);
        prj.setStatus(arr[2]);
        if (arr.length >= 4) {
            prj.setLastUpdate(arr[3]);
        }
        if (arr.length >= 5) {
            prj.setLastBuild(arr[4]);
        }
        return prj;
    }

    public Project() {
    }

    public Project(String name, String dir) {
        this.name = name;
        this.dir = dir;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getLastBuild() {
        return lastBuild;
    }

    public void setLastBuild(String lastBuild) {
        this.lastBuild = lastBuild;
    }

    public void resetLastBuildTime() {
        this.lastBuild = this.getTime();
    }
    
    public void resetLastUpdateTime() {
        this.lastUpdate = this.getTime();
    }

    public String getTime() {
        return dateFormat.format(new Date());
    }

    public String toString() {
        return this.name + "," + this.dir + "," + this.status + "," + this.lastUpdate + "," + this.lastBuild;
    }
}
