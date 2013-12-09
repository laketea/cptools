/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cptools.common;

/**
 *
 * @author zhangwei <laketea@163.com>
 */
public class FileBean {

    private int fileSize;//文件大小
    private int type;
    private String fileName;	//文件名称
    private byte[] fileContent;//文件byte数组
    

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getFileContent() {
        return fileContent;
    }

    public void setFileContent(byte[] fileContent) {
        this.fileContent = fileContent;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    
    
}
