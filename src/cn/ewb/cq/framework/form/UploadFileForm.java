package cn.ewb.cq.framework.form;

import java.util.ArrayList;
import java.util.List;
import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class UploadFileForm extends ActionForm {
    private List<FormFile> myFiles = new ArrayList<FormFile>();  // ���ڱ��治��������FormFile���� 
    private String status;
    private String flowInstanceMainId;
    private String flowInstanceStepUserId;
    private String returnValue;
    private String directFlg; // ���Ϲ���FLG
    
    public FormFile getFile(int i)  // ��������  
    {  
        return myFiles.get(i);  
    }  
    public void setFile(int i, FormFile myFile)  // ��������  
    {  
        if (myFile.getFileSize() > 0)  // ֻ���ϴ��ļ����ֽ�������0�����ϴ�����ļ�  
        {  
            myFiles.add(myFile);  
        }  
    }  
    public int getFileCount()  // ����ϴ��ļ��ĸ���  
    {  
        return myFiles.size();  
    }
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getFlowInstanceMainId() {
		return flowInstanceMainId;
	}
	public void setFlowInstanceMainId(String flowInstanceMainId) {
		this.flowInstanceMainId = flowInstanceMainId;
	}
	public String getFlowInstanceStepUserId() {
		return flowInstanceStepUserId;
	}
	public void setFlowInstanceStepUserId(String flowInstanceStepUserId) {
		this.flowInstanceStepUserId = flowInstanceStepUserId;
	}
	public String getReturnValue() {
		return returnValue;
	}
	public void setReturnValue(String returnValue) {
		this.returnValue = returnValue;
	}
	public String getDirectFlg() {
		return directFlg;
	}
	public void setDirectFlg(String directFlg) {
		this.directFlg = directFlg;
	}
	
	
}
