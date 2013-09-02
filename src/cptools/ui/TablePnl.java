/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * TablePnl.java
 *
 * Created on 2013-8-26, 14:38:44
 */
package cptools.ui;

import cptools.Shell;
import cptools.model.DirTableModel;
import cptools.model.ProjectTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author zhangwei <laketea@163.com>
 */
public class TablePnl extends javax.swing.JPanel {

    private JTable table;
    private AbstractTableModel model;
    private List<String[]> data;
    private String id;

    /** Creates new form TablePnl */
    public TablePnl(String id) {
        initComponents();
        this.data = Utils.getData(id);
        this.id = id;
        initTable();
    }

    private void initTable() {
        reloadData();
        table = new JTable();
        table.setModel(model);
        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        table.setColumnSelectionAllowed(false);
        table.setRowSelectionAllowed(true);
        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(1).setPreferredWidth(200);
        table.getColumnModel().getColumn(2).setPreferredWidth(400);
        table.getColumnModel().getColumn(3).setPreferredWidth(100);
        table.addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent event) {


                if (SwingUtilities.isRightMouseButton(event)) {
                    ShowMenu(event.getX(), event.getY());
                }
            }

            public void mouseClicked(MouseEvent e) {
                if ("projects".equals(id)) {
                    return;
                }
                if (e.getButton() == MouseEvent.BUTTON1) {
                    if (e.getClickCount() > 1) {
                        int row = table.rowAtPoint(e.getPoint());// 得到table的行号
                        if (row > -1) {
                            try {
                                Shell shell = new Shell(Shell.CPCMD, "./", (String) model.getValueAt(row, 2));
                                shell.execute();
                            } catch (IOException ex) {
                                Logger.getLogger(TablePnl.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }
            }
        });

        //table.getColumnModel().getColumn(3).setPreferredWidth(100);
        table.setRowHeight(
                25);
        jScrollPane1.setViewportView(table);
    }

    public void reloadData() {
        this.data = Utils.getData(id);
        if ("projects".equals(id)) {
            model = new ProjectTableModel(this.data, id);
        } else {
            model = new DirTableModel(this.data, id);
        }

    }

    private void ShowMenu(int x, int y) {

        JMenuItem updateItem = new JMenuItem("GIT更新");
        JMenuItem antItem = new JMenuItem("编译");
        JMenuItem allItem = new JMenuItem("更新并编译");
        JMenuItem openItem = new JMenuItem("打开所在目录");
        JMenuItem delItem = new JMenuItem("删除");


        JPopupMenu popupMenu = new JPopupMenu();
        if ("projects".equals(id)) {
            popupMenu.add(updateItem);
            popupMenu.add(antItem);
            popupMenu.add(allItem);
            
        }
        popupMenu.add(delItem);
        popupMenu.add(openItem);
        popupMenu.show((JComponent) table, x, y);

        ActionListener Listeners = new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                if (((JMenuItem) e.getSource()).getText().equals("删除")) {
                    if ((table.getSelectedRows().length) >= 1) {
                        delete();
                    }
                } else if (((JMenuItem) e.getSource()).getText().equals("GIT更新")) {
                    if ((table.getSelectedRows().length) >= 1) {
                        gitUpdate();
                    }
                } else if (((JMenuItem) e.getSource()).getText().equals("编译")) {
                    if ((table.getSelectedRows().length) >= 1) {
                        ant();
                    }
                } else if (((JMenuItem) e.getSource()).getText().equals("更新并编译")) {
                    if ((table.getSelectedRows().length) >= 1) {
                        updateAndAnt();
                    }
                } else if (((JMenuItem) e.getSource()).getText().equals("打开所在目录")) {
                    if ((table.getSelectedRows().length) >= 1) {
                        openDir();
                    }
                }
            }
        };
        updateItem.addActionListener(Listeners);
        antItem.addActionListener(Listeners);
        allItem.addActionListener(Listeners);
        delItem.addActionListener(Listeners);
        openItem.addActionListener(Listeners);
    }

    public void reflashUI() {
        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(1).setPreferredWidth(200);
        table.getColumnModel().getColumn(2).setPreferredWidth(400);
        table.getColumnModel().getColumn(3).setPreferredWidth(100);
        table.updateUI();
    }

    public void openDir() {
        int[] selections = table.getSelectedRows();
        if (selections.length == 0) {
            return;
        }
        List<String[]> list = new ArrayList<String[]>();
        for (int i = selections.length - 1; i >= 0; i--) {
             String path = (String) model.getValueAt(selections[i], 2);
            try {
                (new Shell(Shell.CPCMD,"./",path)).execute();
            } catch (IOException ex) {
                Logger.getLogger(TablePnl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        new Thread(new Shell(list)).start();
    }
    
    public void gitUpdate() {
        int[] selections = table.getSelectedRows();
        if (selections.length == 0) {
            return;
        }
        List<String[]> list = new ArrayList<String[]>();
        for (int i = selections.length - 1; i >= 0; i--) {
            String path = (String) model.getValueAt(selections[i], 2);
            list.add(new String[]{path,"0"});
        }
         new Thread(new Shell(list)).start();
        
    }

    public void ant() {
        int[] selections = table.getSelectedRows();
        if (selections.length == 0) {
            return;
        }
        List<String[]> list = new ArrayList<String[]>();
        for (int i = selections.length - 1; i >= 0; i--) {
             String path = (String) model.getValueAt(selections[i], 2);
            list.add(new String[]{path,"1"});
        }
        new Thread(new Shell(list)).start();
    }
    
    
    
    public void getUpdateAll() {
        
        List<String[]> list = new ArrayList<String[]>();
        for (String[] value:this.data) {
             String path = value[1];
            list.add(new String[]{path,"0"});
        }
        new Thread(new Shell(list)).start();
    }
    
    public void antAll() {
        
        List<String[]> list = new ArrayList<String[]>();
        for (String[] value:this.data) {
             String path = value[1];
            list.add(new String[]{path,"1"});
        }
        new Thread(new Shell(list)).start();
    }
    
    public void updateAndAntAll() {
        
        List<String[]> list = new ArrayList<String[]>();
        for (String[] value:this.data) {
             String path = value[1];
             list.add(new String[]{path,"0"});
            list.add(new String[]{path,"1"});
        }
        new Thread(new Shell(list)).start();
    }

    public void updateAndAnt() {
        int[] selections = table.getSelectedRows();
        if (selections.length == 0) {
            return;
        }
        List<String[]> list = new ArrayList<String[]>();
        for (int i = selections.length - 1; i >= 0; i--) {
            String path = (String) model.getValueAt(selections[i], 2);
            list.add(new String[]{path,"0"});
            list.add(new String[]{path,"1"});
        }
        new Thread(new Shell(list)).start();            
    }

    private void delete() {

        int[] selections = table.getSelectedRows();
        if (selections.length == 0) {
            JOptionPane.showMessageDialog(this, "请先选择您需要删除的数据!");
            return;
        }
        if (JOptionPane.showConfirmDialog(null, "您确定要删除么？", "删除", JOptionPane.YES_NO_OPTION) == 1) {
            return;
        }

        for (int i = selections.length - 1; i >= 0; i--) {
            Utils.delete((String) model.getValueAt(selections[i], 1), (String) model.getValueAt(selections[i], 2), id);
        }
        this.reflush();
    }

    public void reflush() {

        reloadData();
        table.setModel(model);
        reflashUI();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
