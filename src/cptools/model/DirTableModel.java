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
public class DirTableModel extends AbstractTableModel {
    

    private String id;
    List<String[]> dataList = new ArrayList<String[]>();
    public String[] columnNames = {"ÐòºÅ", "ËµÃ÷", "Â·¾¶", "×´Ì¬"};

    public DirTableModel(List<String[]> lst, String id) {
        if (lst != null) {
            this.dataList = lst;
        }
        this.id = id;
    }

    public void addDataList(List<String[]> lst) {
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

    public void insertRow(int row, String[] bean) {
        dataList.add(row, bean);
        fireTableDataChanged();
    }

    public void insertRow(String[] bean) {
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
        if (dataList == null || dataList.get(row) == null) {
            return null;
        }
        String[] arr = dataList.get(row);
        if (col == 0) {
            return (row + 1) + "";
        }
        if(col == 3){
            return "";
        }
        return arr[col-1];
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

    }

//    public void listSort() {
//        Collections.sort(dataList, new Comparator() {
//
//            public int compare(Object a, Object b) {
//                String one = ((String[]) a)(1);
//                String two = ((String[]) a)(1);
//                return one - two;
//            }
//        });
//
//    }
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

    public String[] getRowObject(int row) {
        return dataList.get(row);
    }

    public void updateSelectedRow(int selectedRow, String[] data) {
//        PictureBean pb = getRowObject(selectedRow);
//        pb.setId(data.getId());
//        pb.setPicname(data.getPicname());
//        pb.setPicurl(data.getPicurl());
//        pb.setOrderid(data.getOrderid());
//        pb.setIdentify(data.getIdentify());
//        pb.setDescription(data.getDescription());
        fireTableDataChanged();
    }

    public void reload(List<String[]> toSavelist) {
        dataList.clear();
        dataList.addAll(toSavelist);
        fireTableDataChanged();
    }

    public void remove(int index) {
        dataList.remove(index);
        fireTableDataChanged();
    }

    public List<String[]> getDataList() {
        if (dataList == null) {
            return new ArrayList<String[]>();
        }
        return dataList;
    }

    public void setDataList(List<String[]> dataList) {
        this.dataList = dataList;
    }
}
