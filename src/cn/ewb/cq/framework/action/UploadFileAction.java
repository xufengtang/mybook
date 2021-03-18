package cn.ewb.cq.framework.action;
import java.io.File;
import java.io.FileOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;

import cn.ewb.cq.com.StringUtil;
import cn.ewb.cq.com.SystemUtil;
import cn.ewb.cq.framework.form.UploadFileForm;

public class UploadFileAction extends DispatchAction {
	private static Logger logger = Logger.getLogger(LoginAction.class);
	private static String[] fileType = {"gif","GIF","bmp","BMP","jpg","JPG","jpeg","JPEG","tiff","TIFF","png","PNG","xls","XLS","xlsx","XLSX","doc","DOC","docx","DOCX","ppt","PPT","pptx","PPTX","rar","RAR","zip","ZIP","txt","TXT","html","HTML","htm","HTM","pdf","PDF"};

	public ActionForward uploadFileInit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String methodName = "uploadFileInit()";
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(), methodName,true));
		UploadFileForm uploadFileForm = (UploadFileForm) form;
		String flowInstanceStepUserId = request.getParameter("flowInstanceStepUserId");
		String flowInstanceMainId = request.getParameter("flowInstanceMainId");
		/**关于资料管理修改start **/
		String directFlg = request.getParameter("directFlg");
		/**关于资料管理修改end **/
		uploadFileForm.setFlowInstanceStepUserId(flowInstanceStepUserId);
		uploadFileForm.setFlowInstanceMainId(flowInstanceMainId);
		/**关于资料管理修改start **/
		uploadFileForm.setDirectFlg(directFlg);
		/**关于资料管理修改end **/
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(),methodName,false));
		return mapping.findForward("success");
	}
	
	public ActionForward uploadFile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final String methodName = "uploadFile()";
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(), methodName,true));
		UploadFileForm uploadFileForm = (UploadFileForm) form;
		try{
			String returnValue = "";
			int count = 0;  
			count = uploadFileForm.getFileCount();   // 获得上传文件的总数 			
			// 实例ID取得
			String flowInstanceMainId = uploadFileForm.getFlowInstanceMainId();
			// 用户实例ID取得
			String flowInstanceStepUserId = uploadFileForm.getFlowInstanceStepUserId();
			// 资料管理FLG
			String directFlg  = uploadFileForm.getDirectFlg();
			// 上传路径读取
			String uploadFilePath ="";
			// 资料管理的情况
			if(StringUtil.isNotNull(directFlg)){
				uploadFilePath = SystemUtil.getProperties(request).getProperty("DATA_FILE_PATH");
				uploadFilePath = uploadFilePath + "temp\\";
				File FilePath = new File(uploadFilePath);
				if(!FilePath.exists()&&!FilePath.isDirectory()){
					FilePath.mkdirs();
				}
			}else{
				uploadFilePath = SystemUtil.getProperties(request).getProperty("UPLOAD_FILE_PATH");
				
				// 如果实例ID取得，将文件上传到自身文件夹中，否则放到临时文件夹中
				if(StringUtil.isNotNull(flowInstanceMainId)){
					uploadFilePath = uploadFilePath + flowInstanceMainId + "\\" + flowInstanceStepUserId + "\\";
					File FilePath = new File(uploadFilePath);
					if(!FilePath.exists()&&!FilePath.isDirectory()){
						FilePath.mkdirs();
					}
				}else{
					uploadFilePath = uploadFilePath + "temp\\";
					File FilePath = new File(uploadFilePath);
					if(!FilePath.exists()&&!FilePath.isDirectory()){
						FilePath.mkdirs();
					}
				}
			}

			// 请选择上传文件
			ActionMessages msgs = new ActionMessages();
			if(count==0){
				msgs.add("failMsg", new ActionMessage("M008"));
				saveErrors(request, msgs);
			}else{
				boolean isFileError = false;
	            for (int i = 0; i < count; i++){
	                FormFile file = uploadFileForm.getFile(i);   
	                String fileName = file.getFileName();
	                String patternName = fileName.split("\\.")[fileName.split("\\.").length-1];
	                if(fileTypeCheck(patternName)){
	                	isFileError = true;
	                	break;
	                }
	            }
	            if(isFileError){
					msgs.add("failMsg", new ActionMessage("M009"));
					saveErrors(request, msgs);
	            }else{
		            for (int i = 0; i < count; i++){  
		                FormFile file = uploadFileForm.getFile(i);   
		                FileOutputStream fos = new FileOutputStream(uploadFilePath + file.getFileName()); //创建输出流  
		                // 文件名字获取
		               // returnValue = "<a href='#' style='cursor:hand;color: blue;'>["+file.getFileName()+"]</a><a href='#' style='cursor:hand;'>删除</a>&nbsp;";
		               // returnValue = returnValue + "<input type='hidden' id='fileList[0].fileName' name='fileList[0].fileName' />";
		                if(StringUtil.isNotNull(returnValue)){
		                	returnValue = returnValue + "," + file.getFileName();
		                }else{
		                	returnValue = file.getFileName();
		                }
		                fos.write(file.getFileData()); //写入  
		                fos.flush();//释放  
		                fos.close(); //关闭  
		            } 
		            uploadFileForm.setStatus("success");
		            uploadFileForm.setReturnValue(returnValue);
	            }
			}
		}catch(Exception ex){
			logger.error(SystemUtil.getErrorLog(request,this.getClass().getName(),methodName,ex.getMessage()));
			throw ex;
		}
		logger.info(SystemUtil.getInfoLog(request, this.getClass().getName(),methodName,false));
		return mapping.findForward("success");
	}
	
	private boolean fileTypeCheck(String patternName){
		boolean isFileError = true;
		for(int i=0;i<fileType.length;i++){
			if(fileType[i].equals(patternName)){
				isFileError = false;
				break;
			}
		}
		return isFileError;
	}
	
}
