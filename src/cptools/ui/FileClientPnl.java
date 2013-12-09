/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FileClientPnl.java
 *
 * Created on 2013-11-19, 17:41:15
 */
package cptools.ui;

import cptools.common.Console;
import cptools.common.ServerUtils;
import cptools.fileClient.Client;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author zhangwei <laketea@163.com>
 */
public class FileClientPnl extends javax.swing.JPanel {

    private String url = "127.0.0.1";
    private String port = "10010";
    private String path = "";
    private Client client = null;
    private Thread thread = null;

    /** Creates new form FileClientPnl */
    public FileClientPnl() {
        initComponents();
        Console.getCInstance().setTextArea(this.jtfConsole);
        url = Utils.getParam("serverUrl","127.0.0.1");
        this.jtfUrl.setText(url);
    }

    private void connect() {
        this.url = this.jtfUrl.getText();
        this.port = this.jtfPort.getText();
        if (ServerUtils.nullBank(url) || ServerUtils.nullBank(port)) {
            JOptionPane.showMessageDialog(this, "��ַ��˿ںŲ���Ϊ�գ�");
            return;
        }
        Console.getCInstance().log("��ʼ����:" + url + ":" + port);
        Utils.saveParam("serverUrl", url);
        this.client = new Client(url, Integer.parseInt(port));
        this.thread = new Thread(client);
        thread.start();

        this.jbtConnect.setEnabled(false);
        this.jbtStop.setEnabled(true);


    }

    private void stop() {
        if (this.client != null) {
            this.client.close();
        }
        this.jbtConnect.setEnabled(true);
        this.jbtStop.setEnabled(false);
    }

    private void upload() {
        String type = (String)this.jComboBox1.getSelectedItem();
        
        this.path = this.jtfPath.getText();
        if(ServerUtils.nullBank(path)){
            JOptionPane.showMessageDialog(this, "��ѡ����Ҫ���͵��ļ�!");
            return;
        }
        File file = new File(this.path);
        if(!file.exists() || !file.isFile()){
            JOptionPane.showMessageDialog(this, "�ļ���ַ����������ѡ����Ҫ�ϴ����ļ�!");
            return;
        }
        if(this.client!=null){
            this.client.send(path,this.getIntType(type));
        }
    }
    
    private int getIntType(String type){
        String[] arr = new String[]{"home","server","web"};
        for(int i=0;i<3;i++){
            if(arr[i].equals(type)){
                return (i+1);
            };
        }
        return 1;
    }

    private void setFilePath() {
        String path = this.selectDirectoryDialog();
        if(!ServerUtils.nullBank(path)){
            this.jtfPath.setText(path);
        }
       
    }

    private String selectDirectoryDialog() {
        String path = "";
        String name = "";
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
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
        jtfUrl = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jtfPort = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jbtConnect = new javax.swing.JToggleButton();
        jbtStop = new javax.swing.JToggleButton();
        jLabel3 = new javax.swing.JLabel();
        jtfPath = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtfConsole = new javax.swing.JTextArea();
        jComboBox1 = new javax.swing.JComboBox();

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jtfUrl.setText("127.0.0.1");

        jLabel1.setText(":");

        jtfPort.setText("10010");

        jLabel2.setText("��������");

        jbtConnect.setText("����");
        jbtConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtConnectActionPerformed(evt);
            }
        });

        jbtStop.setText("�Ͽ�");
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
                .addContainerGap()
                .add(jLabel2)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jtfUrl, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 147, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(2, 2, 2)
                .add(jLabel1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jtfPort, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 68, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(18, 18, 18)
                .add(jbtConnect)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jbtStop)
                .addContainerGap(193, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel2)
                    .add(jtfUrl, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel1)
                    .add(jtfPort, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jbtConnect)
                    .add(jbtStop))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel3.setText("�ļ���ַ��");

        jButton1.setText("..");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("�ϴ�");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jtfConsole.setColumns(20);
        jtfConsole.setRows(5);
        jScrollPane1.setViewportView(jtfConsole);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "home", "server", "web" }));

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .add(layout.createSequentialGroup()
                .add(12, 12, 12)
                .add(jLabel3)
                .add(18, 18, 18)
                .add(jtfPath, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 236, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jButton1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 43, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jComboBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jButton2)
                .addContainerGap(120, Short.MAX_VALUE))
            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 686, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(18, 18, 18)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jtfPath, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel3)
                    .add(jButton1)
                    .add(jComboBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jButton2))
                .add(18, 18, 18)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jbtConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtConnectActionPerformed
        // TODO add your handling code here:
        this.connect();
    }//GEN-LAST:event_jbtConnectActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.setFilePath();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.upload();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jbtStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtStopActionPerformed
        // TODO add your handling code here:
        this.stop();
    }//GEN-LAST:event_jbtStopActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToggleButton jbtConnect;
    private javax.swing.JToggleButton jbtStop;
    private javax.swing.JTextArea jtfConsole;
    private javax.swing.JTextField jtfPath;
    private javax.swing.JTextField jtfPort;
    private javax.swing.JTextField jtfUrl;
    // End of variables declaration//GEN-END:variables
}