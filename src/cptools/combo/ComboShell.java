/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cptools.combo;

import cptools.StreamGobblers;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;

/**
 *
 * @author zhangwei <laketea@163.com>
 */
public class ComboShell extends Thread {

    private String cmd = "";
    
    private String path = "";
    
    private String directory = "";
    
    private String[] cmds = new String[]{};
    
   

    public ComboShell(String cmd,String path) {
        this.cmd = cmd;
        this.path = path;
    }

    

    



    private String getShCmds() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("#!/bin/bash\n");
        buffer.append("cd "+this.path+"\n");
        buffer.append(""+this.cmd+"\n");
        return buffer.toString();
    }

    public void run() {
        try {
            this.appendSh();
            this.directory = (new File("")).getAbsolutePath();
            this.cmds = new String[]{"./combo.sh"};
            execute();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void appendSh() throws IOException {
        File file = new File("combo.sh");
        if (!file.exists()) {
            file.createNewFile();
            this.directory = (new File("")).getAbsolutePath();
            this.cmds = new String[]{"chmod", "+x", "combo.sh"};
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

            StreamCombo errorGobbler = new StreamCombo(
                    process.getErrorStream(), "ERROR");

            // any output?
            StreamCombo outputGobbler = new StreamCombo(
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

