/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * AppFrame.java
 *
 * Created on 2013-8-26, 14:09:31
 */
package cptools;

import cptools.ui.ConsolePnl;
import cptools.ui.EditPnl;
import cptools.ui.TablePnl;
import cptools.ui.Utils;
import java.awt.BorderLayout;
import java.awt.Font;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.UIManager;

/**
 *
 * @author zhangwei <laketea@163.com>
 */
public class AppFrame extends javax.swing.JFrame {

    private TablePnl proPnl;
    private TablePnl dirPnl;
    private ConsolePnl consolePnl;

    /** Creates new form AppFrame */
    public AppFrame() {
        initComponents();
        this.setTitle("编译部署工具 (Beta)");
        init();
    }

    private void init() {
        proPnl = new TablePnl("projects");
        dirPnl = new TablePnl("dirs");
        consolePnl = new ConsolePnl();
        this.dirContainer.add(dirPnl, BorderLayout.CENTER);
        this._projectContainer.add(proPnl, BorderLayout.CENTER);
        this.OutputContainer.add(consolePnl, BorderLayout.CENTER);
        StreamGobblers.setTextArea(consolePnl.getTextArea());
    }

    private void addProject() {
        edit("projects");
        proPnl.reflush();
    }

    private void addDir() {
        edit("dirs");
        dirPnl.reflush();
    }

    private void edit(String id) {
        EditPnl pnl = new EditPnl(id);
        JDialog dlg = new JDialog(this, "添加" + ("projects".equals(id) ? "项目" : "目录"), true);
        dlg.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dlg.setLayout(new BorderLayout());
        dlg.add(pnl, BorderLayout.CENTER);
        dlg.setSize(pnl.getPreferredSize().width, pnl.getPreferredSize().height + 30);
        dlg.setLocationRelativeTo(null);
        dlg.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MainTabPnl = new javax.swing.JTabbedPane();
        projectContainer = new javax.swing.JPanel();
        OutputContainer = new javax.swing.JPanel();
        _projectContainer = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        dirContainer = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        OutputContainer.setLayout(new java.awt.BorderLayout());

        _projectContainer.setLayout(new java.awt.BorderLayout());

        jButton1.setText("更新");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("编译");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("更新编译");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("清空");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("全部更新");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("全部更新编译");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout projectContainerLayout = new org.jdesktop.layout.GroupLayout(projectContainer);
        projectContainer.setLayout(projectContainerLayout);
        projectContainerLayout.setHorizontalGroup(
            projectContainerLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(OutputContainer, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 869, Short.MAX_VALUE)
            .add(_projectContainer, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 869, Short.MAX_VALUE)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, projectContainerLayout.createSequentialGroup()
                .addContainerGap(177, Short.MAX_VALUE)
                .add(jButton1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jButton2)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jButton3)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jButton5)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jButton6)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jButton4)
                .add(153, 153, 153))
        );
        projectContainerLayout.setVerticalGroup(
            projectContainerLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, projectContainerLayout.createSequentialGroup()
                .add(_projectContainer, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(projectContainerLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jButton1)
                    .add(jButton2)
                    .add(jButton3)
                    .add(jButton5)
                    .add(jButton6)
                    .add(jButton4))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(OutputContainer, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 225, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );

        MainTabPnl.addTab("工程列表", projectContainer);

        dirContainer.setLayout(new java.awt.BorderLayout());
        MainTabPnl.addTab("常用目录", dirContainer);

        jMenu1.setText("系统");

        jMenuItem1.setText("退出");
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("项目管理");

        jMenuItem2.setText("新增项目");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("目录管理");

        jMenuItem3.setText("添加目录");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(MainTabPnl, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 890, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(MainTabPnl, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        addProject();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        addDir();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.proPnl.gitUpdate();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.proPnl.ant();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        this.proPnl.updateAndAnt();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        this.proPnl.getUpdateAll();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        this.proPnl.updateAndAntAll();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        this.consolePnl.clearTextArea();
    }//GEN-LAST:event_jButton4ActionPerformed

    public static void main(String args[]) {

        Font myFont = new Font("宋体", Font.PLAIN, 12);
        javax.swing.plaf.FontUIResource fontRes = new javax.swing.plaf.FontUIResource(myFont);
        java.util.Enumeration keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof javax.swing.plaf.FontUIResource) {
                UIManager.put(key, fontRes);
            }
        }
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                AppFrame frame = new AppFrame();
                frame.setDefaultCloseOperation(EXIT_ON_CLOSE); 
                
                frame.setSize(890,642);
                frame.setResizable(false); 
                frame.setLocationRelativeTo(frame.getOwner()); 
                frame.setVisible(true);
                
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane MainTabPnl;
    private javax.swing.JPanel OutputContainer;
    private javax.swing.JPanel _projectContainer;
    private javax.swing.JPanel dirContainer;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel projectContainer;
    // End of variables declaration//GEN-END:variables
}
