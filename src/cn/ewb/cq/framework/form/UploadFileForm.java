package cn.ewb.cq.framework.form;

import java.util.ArrayList;
import java.util.List;
import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class UploadFileForm extends ActionForm {
    private List<FormFile> myFiles = new ArrayList<FormFile>();  // 用于保存不定数量的FormFile对象 
    private String status;
    private String flowInstanceMainId;
    private String flowInstanceStepUserId;
    private String returnValue;
    private String directFlg; // 资料管理FLG
    
    public FormFile getFile(int i)  // 索引属性  
    {  
        return myFiles.get(i);  
    }  
    public void setFile(int i, FormFile myFile)  // 索引属性  
    {  
        if (myFile.getFileSize() > 0)  // 只有上传文件的字节数大于0，才上传这个文件  
        {  
            myFiles.add(myFile);  
        }  
    }  
    public int getFileCount()  // 获得上传文件的个数  
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
