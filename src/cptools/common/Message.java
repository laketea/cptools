/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cptools.common;

/**
 *
 * @author zhangwei <laketea@163.com>
 */
public class Message {

    private int dataType;		//��Ϊҵ���ж�����
    private Object data;		//�洢ҵ������

    public Message(int dataType, Object data) {
        this.dataType = dataType;
        this.data = data;
    }

    public Message() {
    }

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
