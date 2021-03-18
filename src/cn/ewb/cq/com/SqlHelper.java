package cn.ewb.cq.com;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class SqlHelper {
//������Ҫ�ı���
	private static Connection ct=null;
	//����������£�����ʹ�õ���PreparedStatement����ֹsqlע��
	private static PreparedStatement ps=null;
	private static ResultSet rs=null;
	//�洢����
	private static CallableStatement cs =null;
	//�������ݿ����
	private static String url="";
	private static String username="";
	private static String driver="";
	private static String password="";
	

	private static FileInputStream fis=null;
	
	//������������ҪҪһ��
	static{
		try { 
			//��dbinfo.propertis�ļ��ж�ȡ��������
			url = SystemUtil.getProperties(null).getProperty("URL");
			username = SystemUtil.getProperties(null).getProperty("USERNAME");
			driver = SystemUtil.getProperties(null).getProperty("DRIVER");
			password = SystemUtil.getProperties(null).getProperty("PASSWORD");
			
			Class.forName(driver);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Connection getConnection(){
		try {
			if(ct==null){
			   ct = DriverManager.getConnection(url,username,password);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ct;
	}
	
	//����ж��  update / delete /insert ��䣬��Ҫ��������
	/*ct.setAutoCommit(false);	
	update���
	delete���
	insert���
	...
	ct.commit();*/
	public static void executeUpdate2(String [] sql,String [][] parameters){
		
		try {
			//����
			//1�������
			ct =getConnection();
			//��Ϊ��ʱ���û�����Ŀ����Ƕ��sql���
			ct.setAutoCommit(false);
			//...
			for(int i=0;i<sql.length;i++){
				if(parameters[i]!=null){
					ps=ct.prepareStatement(sql[i]);
					for(int j=0;j<parameters[i].length;j++){
						ps.setString(j+1, parameters[i][j]);
					}
					ps.executeUpdate();
				}			
			}
			ct.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//�ع�
			try {
				ct.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
			//�׳��쳣���׳������쳣�����Ը����øú���һ��ѡ��
			throw new RuntimeException(e.getMessage());
		}finally{
			close(rs,ps,ct);
		}
	}
	
	
	//��ҳ������
	public static ResultSet executeQuery2(){
		return null;
	}
	
	//���ô洢����
	//sql �� {call ����(?,?,?)}
	public static void callProl(String sql,String []parameters){
		try{
			ct = getConnection();
			ps=ct.prepareCall(sql);
			
			//?�Ÿ�ֵ
			if(parameters!=null){
				for(int i=0;i<parameters.length;i++){
					cs.setObject(i+1,parameters[i]);
				}
			}
			cs.execute();
		}catch(Exception e){
			e.printStackTrace();
			//�׳��쳣���׳������쳣�����Ը����øú���һ��ѡ��
			throw new RuntimeException(e.getMessage());
		}finally{
			//�ر���Դ
			close(rs,cs,ct);
		}	
	}
	
	//�����д洢���̵ķ���ֵ
	//sql call���� (?,?,?)
	public static void callPro2(String sql,String [] inparameters,Integer [] outparameters){
		try{
			ct = getConnection();
			ps=ct.prepareCall(sql);
			if(inparameters!=null){
				for(int i=0;i<inparameters.length;i++){
					cs.setObject(i+1,inparameters[i]);
				}
			}
			if(outparameters!=null){
				for(int i=0;i<outparameters.length;i++){
					cs.setObject(inparameters.length+i+1,outparameters[i]);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			//�׳��쳣���׳������쳣�����Ը����øú���һ��ѡ��
			throw new RuntimeException(e.getMessage());
		}finally{
			//�ر���Դ
	//		close(rs,ps,ct);
		}
	}
	
	//ͳһ��select
	public static ResultSet executeQuery(String sql,String [] parameters){
		
		try{
			ct = getConnection();
			ps=ct.prepareStatement(sql);
			if(parameters!=null&&parameters.equals("")){
				for(int i=0;i<parameters.length;i++){
					ps.setString(i+1, parameters[i]);
				}
			}
			rs=ps.executeQuery();
		}catch(Exception e){
			e.printStackTrace();
			//�׳��쳣���׳������쳣�����Ը����øú���һ��ѡ��
			throw new RuntimeException(e.getMessage());
		}finally{
			//�ر���Դ
	//		close(rs,ps,ct);
		}
		return rs;
	}
	
	
	//��дһ��update / delete /insert
	//sql��ʽ��update ���� set �ֶ��� =? where �ֶ�=��
	//parameters Ӧ����{"ab",123}
	public static void executeUpdate(String sql,String [] parameters){
		//1����һ��ps
		
		try {
			ct = getConnection();
			ps = ct.prepareStatement(sql);
			//������ֵ
			if(parameters!=null){
				for(int i = 0;i<parameters.length;i++){
					ps.setString(i+1, parameters[i]);
				}
			}
			//ִ��
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//�׳��쳣���׳������쳣�����Ը����øú���һ��ѡ��
			throw new RuntimeException(e.getMessage());
		}finally{
			//�ر���Դ
			//close(rs,ps,ct);
		}	
	}
	public static void close(ResultSet rs,Statement ps,Connection ct){
		if(rs!=null){
			try {
				rs.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(ps!=null){
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ps=null;
		}
		if(ct!=null){
			try {
				ct.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ct =null;
		}
	}
	
	public static void close(){
		if(rs!=null){
			try {
				rs.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(ps!=null){
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ps=null;
		}
		if(ct!=null){
			try {
				ct.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ct =null;
		}
	}
}
