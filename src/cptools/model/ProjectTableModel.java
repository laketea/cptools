/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cptools.model;

/**
 *
 * @author zhangwei <laketea@163.com>
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author yfcd
 */
public class ProjectTableModel extends AbstractTableModel {

    private String id;
    List<Project> dataList = new ArrayList<Project>();
    public String[] columnNames = {"序号", "项目名称", "路径", "状态","更新时间","编译时间"};

    public ProjectTableModel(List<Project> lst, String id) {
        if (lst != null) {
            this.dataList = lst;
        }
        this.id = id;
    }

    public void addDataList(List<Project> lst) {
        if (dataList != null) {
            dataList.addAll(lst);
        } else {
            dataList = lst;
        }
        fireTableDataChanged();
    }

    public int getRowCount() {
        return dataList.size();
    }

    public void insertRow(int row, Project bean) {
        dataList.add(row, bean);
        fireTableDataChanged();
    }

    public void insertRow(Project bean) {
        dataList.add(bean);
        fireTableDataChanged();
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        Object value = "";
        if (dataList == null || dataList.get(row) == null) {
            return null;
        }
        Project arr = dataList.get(row);
        if (col == 0) {
            return (row + 1) + "";
        }
        switch (col) {
            case 0:
                value = (row + 1) + "";
                break;
            case 1:
                value = arr.getName();
                break;
            case 2:
                value = arr.getDir();
                break;
            case 3:
                value = arr.getStatus();
                break;
            case 4:
                value = arr.getLastUpdate();
                break;
            case 5:
                value = arr.getLastBuild();
                break;
            default:
                break;
        }
        return value;
    }

    public boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("^-?[0-9]*$");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            if (!isNumeric((String) aValue)) {
                return;
            }
        }
        if (columnIndex == 4) {
            this.getRowObject(rowIndex).resetLastUpdateTime();

        }
        if (columnIndex == 5) {
            this.getRowObject(rowIndex).resetLastBuildTime();
        }
        this.fireTableRowsUpdated(rowIndex, rowIndex);
    }

    /*  
     *   JTable   uses   this   method   to   determine   the   default   renderer/  
     *   editor   for   each   cell.     If   we   didn't   implement   this   method,  
     *   then   the   last   column   would   contain   text   ("true"/"false"),  
     *   rather   than   a   check   box.  
     */
    public Class getColumnClass(int c) {
        return String.class;
    }

    /*  
     *   Don't   need   to   implement   this   method   unless   your   table's  
     *   editable.  
     */
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    public Project getRowObject(int row) {
        return dataList.get(row);
    }

    public void updateSelectedRow(int selectedRow, Project pro) {
        Project select = getRowObject(selectedRow);
        select.setLastBuild(pro.getLastBuild());
        select.setLastUpdate(pro.getLastUpdate());
        fireTableDataChanged();
    }

    public void reload(List<Project> toSavelist) {
        dataList.clear();
        dataList.addAll(toSavelist);
        fireTableDataChanged();
    }

    public void remove(int index) {
        dataList.remove(index);
        fireTableDataChanged();
    }

    public List<Project> getDataList() {
        if (dataList == null) {
            return new ArrayList<Project>();
        }
        return dataList;
    }

    public void setDataList(List<Project> dataList) {
        this.dataList = dataList;
    }
}
