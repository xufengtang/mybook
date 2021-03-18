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
//定义需要的变量
	private static Connection ct=null;
	//大多数境况下，我们使用的是PreparedStatement来防止sql注入
	private static PreparedStatement ps=null;
	private static ResultSet rs=null;
	//存储过程
	private static CallableStatement cs =null;
	//连接数据库参数
	private static String url="";
	private static String username="";
	private static String driver="";
	private static String password="";
	

	private static FileInputStream fis=null;
	
	//加载驱动，需要要一次
	static{
		try { 
			//从dbinfo.propertis文件中读取配置信心
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
	
	//如果有多个  update / delete /insert 语句，需要考虑事务
	/*ct.setAutoCommit(false);	
	update语句
	delete语句
	insert语句
	...
	ct.commit();*/
	public static void executeUpdate2(String [] sql,String [][] parameters){
		
		try {
			//核心
			//1获得连接
			ct =getConnection();
			//因为这时，用户传入的可能是多个sql语句
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
			//回滚
			try {
				ct.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
			//抛出异常，抛出运行异常，可以给调用该函数一个选择
			throw new RuntimeException(e.getMessage());
		}finally{
			close(rs,ps,ct);
		}
	}
	
	
	//分页的问题
	public static ResultSet executeQuery2(){
		return null;
	}
	
	//调用存储过程
	//sql 像 {call 过程(?,?,?)}
	public static void callProl(String sql,String []parameters){
		try{
			ct = getConnection();
			ps=ct.prepareCall(sql);
			
			//?号赋值
			if(parameters!=null){
				for(int i=0;i<parameters.length;i++){
					cs.setObject(i+1,parameters[i]);
				}
			}
			cs.execute();
		}catch(Exception e){
			e.printStackTrace();
			//抛出异常，抛出运行异常，可以给调用该函数一个选择
			throw new RuntimeException(e.getMessage());
		}finally{
			//关闭资源
			close(rs,cs,ct);
		}	
	}
	
	//调用有存储过程的返回值
	//sql call过程 (?,?,?)
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
			//抛出异常，抛出运行异常，可以给调用该函数一个选择
			throw new RuntimeException(e.getMessage());
		}finally{
			//关闭资源
	//		close(rs,ps,ct);
		}
	}
	
	//统一的select
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
			//抛出异常，抛出运行异常，可以给调用该函数一个选择
			throw new RuntimeException(e.getMessage());
		}finally{
			//关闭资源
	//		close(rs,ps,ct);
		}
		return rs;
	}
	
	
	//先写一个update / delete /insert
	//sql格式：update 表名 set 字段名 =? where 字段=？
	//parameters 应该是{"ab",123}
	public static void executeUpdate(String sql,String [] parameters){
		//1创建一个ps
		
		try {
			ct = getConnection();
			ps = ct.prepareStatement(sql);
			//给？赋值
			if(parameters!=null){
				for(int i = 0;i<parameters.length;i++){
					ps.setString(i+1, parameters[i]);
				}
			}
			//执行
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//抛出异常，抛出运行异常，可以给调用该函数一个选择
			throw new RuntimeException(e.getMessage());
		}finally{
			//关闭资源
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
