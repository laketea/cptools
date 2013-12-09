/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FileServerPnl.java
 *
 * Created on 2013-11-15, 10:48:23
 */
package cptools.ui;

import cptools.common.Console;
import cptools.common.ServerUtils;
import cptools.fileserver.FileServer;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author zhangwei <laketea@163.com>
 */
public class FileServerPnl extends javax.swing.JPanel {

    private String sPath = "/Users/yfcd/copy";
    private String wPath = "/Users/yfcd/copy";
    private String hPath = "/Users/yfcd/copy";
    private Thread thread = null;
    private FileServer server = null;

    /** Creates new form FileServerPnl */
    public FileServerPnl() {
        initComponents();
        this.sPath = Utils.getParam("sPath","");
        this.hPath = Utils.getParam("hPath","");
        this.wPath = Utils.getParam("wPath","");

        this.serverPathJtf.setText(this.sPath);
        this.webPathJtf.setText(this.wPath);
        this.jtfHome.setText(this.hPath);
        this.jbtStop.setEnabled(false);
        Console.getSInstance().setTextArea(this.jtxConsole);
        this.shutDownHook();
    }

    private void shutDownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                System.out.println("shutting down");
                if(server!=null){
                    server.stop();
                }
            }
        });
    }

    private void startFileServer() {
        this.sPath = this.serverPathJtf.getText();
        this.wPath = this.webPathJtf.getText();
        this.hPath = this.jtfHome.getText();
        
        Utils.saveParam("sPath", sPath);
        Utils.saveParam("wPath", wPath);
        Utils.saveParam("hPath", hPath);

        if (ServerUtils.nullBank(sPath) || ServerUtils.nullBank(wPath)|| ServerUtils.nullBank(hPath)) {
            JOptionPane.showMessageDialog(this, "·������Ϊ�գ�");
            return;
        }

        server = new FileServer(sPath, wPath,hPath);
        this.thread = new Thread(server);
        this.thread.start();
        this.jbtStart.setEnabled(false);
        this.jbtStop.setEnabled(true);
        this.jbtHome.setEnabled(false);
        this.jButton3.setEnabled(false);
        this.jButton4.setEnabled(false);
    }

    private void stopFileServer() {

        if (server != null) {
            server.stop();
        }
//        if (this.thread == null) {
//            return;
//        }
//        this.thread.interrupt();
        Console.getSInstance().log("�رշ���");
        this.jbtStart.setEnabled(true);
        this.jbtStop.setEnabled(false);
        this.jbtHome.setEnabled(true);
        this.jButton3.setEnabled(true);
        this.jButton4.setEnabled(true);
    }

    private void setServerlibPath() {
        String path = this.selectDirectoryDialog();
        if(!ServerUtils.nullBank(path)){
            this.serverPathJtf.setText(path);
        }
    }

    private void setWeblibPath() {
        String path = this.selectDirectoryDialog();
        if(!ServerUtils.nullBank(path)){
            this.webPathJtf.setText(path);
        }
    }
    
    private void setHomePath(){
        String path = this.selectDirectoryDialog();
        if(!ServerUtils.nullBank(path)){
            this.jtfHome.setText(path);
        }
    }

    private String selectDirectoryDialog() {
        String path = "";
        String name = "";
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//        chooser.setCurrentDirectory(new File(path));
        chooser.setDialogType(JFileChooser.OPEN_DIALOG);
        int retval = chooser.showOpenDialog(this);//��ʾ�����ļ����Ի���
        if (retval == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            name = file.getName();
            path = file.getAbsolutePath();
        }
        return path;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jbtStart = new javax.swing.JButton();
        jbtStop = new javax.swing.JButton();
        serverPathJtf = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        webPathJtf = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtxConsole = new javax.swing.JTextArea();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jtfHome = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jbtHome = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jbtStart.setText("��������");
        jbtStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtStartActionPerformed(evt);
            }
        });

        jbtStop.setText("ֹͣ����");
        jbtStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtStopActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(9, 9, 9)
                .add(jbtStart)
                .add(18, 18, 18)
                .add(jbtStop)
                .addContainerGap(434, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jbtStart)
                    .add(jbtStop))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        serverPathJtf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                serverPathJtfActionPerformed(evt);
            }
        });

        jLabel1.setText("Server-Lib·��:");

        jLabel2.setText("Web-Lib·��:");

        webPathJtf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                webPathJtfActionPerformed(evt);
            }
        });

        jtxConsole.setColumns(20);
        jtxConsole.setRows(5);
        jScrollPane1.setViewportView(jtxConsole);

        jButton3.setText("...");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("...");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel3.setText("Home·��:");

        jbtHome.setText("...");
        jbtHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtHomeActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jLabel1)
                    .add(jLabel2)
                    .add(jLabel3))
                .add(18, 18, 18)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jtfHome)
                    .add(webPathJtf)
                    .add(serverPathJtf, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jbtHome, 0, 0, Short.MAX_VALUE)
                    .add(jButton4, 0, 0, Short.MAX_VALUE)
                    .add(jButton3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 44, Short.MAX_VALUE))
                .addContainerGap(207, Short.MAX_VALUE))
            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 653, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(serverPathJtf, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jButton3))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(webPathJtf, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jButton4))
                    .add(jLabel2))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jtfHome, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel3)
                    .add(jbtHome))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void serverPathJtfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_serverPathJtfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_serverPathJtfActionPerformed

    private void webPathJtfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_webPathJtfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_webPathJtfActionPerformed

    private void jbtStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtStartActionPerformed
        // TODO add your handling code here:
        this.startFileServer();
    }//GEN-LAST:event_jbtStartActionPerformed

    private void jbtStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtStopActionPerformed
        // TODO add your handling code here:
        this.stopFileServer();

    }//GEN-LAST:event_jbtStopActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        this.setServerlibPath();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        this.setWeblibPath();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jbtHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtHomeActionPerformed
        // TODO add your handling code here:
        this.setHomePath();
    }//GEN-LAST:event_jbtHomeActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtHome;
    private javax.swing.JButton jbtStart;
    private javax.swing.JButton jbtStop;
    private javax.swing.JTextField jtfHome;
    private javax.swing.JTextArea jtxConsole;
    private javax.swing.JTextField serverPathJtf;
    private javax.swing.JTextField webPathJtf;
    // End of variables declaration//GEN-END:variables
}