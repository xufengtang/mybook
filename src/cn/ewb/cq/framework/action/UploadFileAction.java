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
		/**�������Ϲ����޸�start **/
		String directFlg = request.getParameter("directFlg");
		/**�������Ϲ����޸�end **/
		uploadFileForm.setFlowInstanceStepUserId(flowInstanceStepUserId);
		uploadFileForm.setFlowInstanceMainId(flowInstanceMainId);
		/**�������Ϲ����޸�start **/
		uploadFileForm.setDirectFlg(directFlg);
		/**�������Ϲ����޸�end **/
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
			count = uploadFileForm.getFileCount();   // ����ϴ��ļ������� 			
			// ʵ��IDȡ��
			String flowInstanceMainId = uploadFileForm.getFlowInstanceMainId();
			// �û�ʵ��IDȡ��
			String flowInstanceStepUserId = uploadFileForm.getFlowInstanceStepUserId();
			// ���Ϲ���FLG
			String directFlg  = uploadFileForm.getDirectFlg();
			// �ϴ�·����ȡ
			String uploadFilePath ="";
			// ���Ϲ�������
			if(StringUtil.isNotNull(directFlg)){
				uploadFilePath = SystemUtil.getProperties(request).getProperty("DATA_FILE_PATH");
				uploadFilePath = uploadFilePath + "temp\\";
				File FilePath = new File(uploadFilePath);
				if(!FilePath.exists()&&!FilePath.isDirectory()){
					FilePath.mkdirs();
				}
			}else{
				uploadFilePath = SystemUtil.getProperties(request).getProperty("UPLOAD_FILE_PATH");
				
				// ���ʵ��IDȡ�ã����ļ��ϴ��������ļ����У�����ŵ���ʱ�ļ�����
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

			// ��ѡ���ϴ��ļ�
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
		                FileOutputStream fos = new FileOutputStream(uploadFilePath + file.getFileName()); //���������  
		                // �ļ����ֻ�ȡ
		               // returnValue = "<a href='#' style='cursor:hand;color: blue;'>["+file.getFileName()+"]</a><a href='#' style='cursor:hand;'>ɾ��</a>&nbsp;";
		               // returnValue = returnValue + "<input type='hidden' id='fileList[0].fileName' name='fileList[0].fileName' />";
		                if(StringUtil.isNotNull(returnValue)){
		                	returnValue = returnValue + "," + file.getFileName();
		                }else{
		                	returnValue = file.getFileName();
		                }
		                fos.write(file.getFileData()); //д��  
		                fos.flush();//�ͷ�  
		                fos.close(); //�ر�  
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
