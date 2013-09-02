/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cptools;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zhangwei <laketea@163.com>
 */
public class Shell extends Thread {

    private String[] cmds = new String[]{};
    private String directory;
    private int state = 0;
    public static String CPCMD = "";
    
    
    static {
        String name =  System.getProperty("os.name");
        if(name.indexOf("Mac")>-1||name.indexOf("mac")>-1){
            CPCMD = "open";
        }else{
            CPCMD = "nautilus";
        }
        
        
    }
    private List<String[]> lst;

    public Shell(List<String[]> lst) {
        this.lst = lst;
    }

    public Shell(String cmmnd, String directory) {
        this(new String[]{cmmnd}, directory);
    }

    public Shell(String cmmnd, String param, String directory) {
        this(new String[]{cmmnd, param}, directory);
    }

    public Shell(String[] cmmnd, String directory) {
        this.cmds = cmmnd;
        if (cmmnd[0].equals("ant")) {
            if (!bdFileExists(directory)) {
                directory = directory + File.separator + "src";
            }
        }
        this.directory = directory;
    }

    public void setStart() {
        state = 1;
    }

    public void setNext() {
        state = 2;
    }

    private String getShCmds() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("#!/bin/bash\n");

        for (String[] arr : lst) {
            buffer.append("cd ");
            String path = arr[0];
            if (!bdFileExists(path) && arr[1].equals("1")) {
                path = path + File.separator + "src";
            }
            buffer.append(path);
            buffer.append("\n");

            if (arr[1].equals("0")) {//0 git, 1 ant
                buffer.append("git pull");
            } else {
                buffer.append("ant");
            }
            buffer.append("\n");
        }

        return buffer.toString();
    }

    public void run() {
        try {
            this.appendSh();
            this.directory = (new File("")).getAbsolutePath();
            this.cmds = new String[]{"./run.sh"};
            execute();
        } catch (IOException ex) {
            Logger.getLogger(Shell.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void appendSh() throws IOException {
        File file = new File("run.sh");
        if (!file.exists()) {
            file.createNewFile();
            this.directory = (new File("")).getAbsolutePath();
            this.cmds = new String[]{"chmod", "+x", "run.sh"};
            this.execute();
        }
        FileWriter fileWriter = new FileWriter(file);
        String content = this.getShCmds();
        fileWriter.write(content);
        fileWriter.flush();
        fileWriter.close();
    }

    public boolean bdFileExists(String path) {
        return (new File(path + File.separator + "build.xml")).exists();
    }

    public String execute()
            throws IOException {
        String result = "";
        try {
            ProcessBuilder builder = new ProcessBuilder(cmds);

            if (directory != null) {
                builder.directory(new File(directory));
            }
            builder.redirectErrorStream(true);
            Process process = builder.start();

            StreamGobblers errorGobbler = new StreamGobblers(
                    process.getErrorStream(), "ERROR");

            // any output?
            StreamGobblers outputGobbler = new StreamGobblers(
                    process.getInputStream(), "OUTPUT");

            // kick them off
            errorGobbler.start();
            outputGobbler.start();

            // any error???
            int exitVal = process.waitFor();

            builder.command("ls");
            builder.start();

            System.out.println("ExitValue: " + exitVal);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
