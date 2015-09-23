package sedion.jeffli.wmuitp.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
/**
 * 为了引入事务处理，这里的方法必须修改，connection连接必须放到调用方法中，而不是在具体的查询
 * 等方法中，这样既不能开启事务也很费资源（每次连接都是很耗费资源的）
 * 
 * */
public class DbConn {
	@SuppressWarnings("unchecked")
	private static Vector dsVector;
	static int rowCount = 0;
	static int colCount = 0;
	public static String[] type = null;
	static boolean flag = true;
	/**
	 * 由于参数的值是靠外部servlet进行传递的，而变量却需要多次使用，因此设为全局变量
	 * 并且由于servlet传入值只会在容器启动的时候,因此设为static
	 * */
	public static String classNames = null;
	public static String url = null;
	public static String user = null;
	public static String psw = null;
	/**
	 * 进行查询的sql的实现
	 * */
	@SuppressWarnings("unchecked")
	public static String[][] query(String sql){
		getData(sql);
		System.out.println("查找集合里面 = "+rowCount);
		String dsString[][] = new String[rowCount][colCount];
		if(flag == true){
			dsString = null;
		}else{
			for (int i = 0; i< rowCount; i++){
					Vector row = new Vector();
					row = (Vector)dsVector.get(i);
				for(int j = 0; j<colCount; j++){
					dsString[i][j] = (String)row.get(j);
				}
			}
		}
		dsVector.clear();
		return dsString;
	}
	
	
	/**
	 * 执行除查询外的所有sql语句
	 * */
	public static int exceptQuery(String sql){
		//System.out.println("获取的所有参数2 = "+ classNames+ url+ user + psw);
		Connection conn=DbConn.getconn(classNames, url, user, psw);
		Statement stmt=null;
		int count=0;
		try {
			conn.setAutoCommit(false);
			stmt=conn.createStatement();
			count=stmt.executeUpdate(sql);
			conn.commit();
		} catch (SQLException e) {
			try{
				conn.rollback();
			}catch(SQLException e1){
				e1.printStackTrace();
			}
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}finally{
			DbConn.closeConn(conn);
		}
		return count;
	}
	/**
	 * 执行查询的sql语句
	 * */
	@SuppressWarnings("unchecked")
	public static int getData(String sql){
		Connection conn = DbConn.getconn(classNames, url, user, psw);
		dsVector = new Vector();
		Statement stmt = null;
		ResultSet rs = null;
		rowCount = 0;
		colCount = 0;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			flag = true;
			while(rs.next()){
				flag = false;
				rowCount++;
				ResultSetMetaData rsmd = rs.getMetaData();
				Vector row = new Vector();
				colCount = rsmd.getColumnCount();
				for( int i = 0; i< colCount; i++){
					row.add(rs.getString(i+1));
				}
				dsVector.add(row);
			}
		} catch (SQLException e) {
			System.out.print(e.getErrorCode());
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}finally{
			DbConn.closeConn(conn);
		}
		System.out.println(rowCount);
		return rowCount;
	}
	/**
	 *查找表的记录行数 
	 * */
	public static int getDataNum(String sql){
		Connection conn = DbConn.getconn(classNames, url, user, psw);
		Statement stmt = null;
		ResultSet rs = null;
		int resultCount = 0;
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(sql);
			rs.last(); 
			resultCount = rs.getRow();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}finally{
			DbConn.closeConn(conn);
		}
		return resultCount;
	}
	/**
	 * 获得数据库表结构（表字段）
	 * @throws SQLException 
	 * */
	public static List<String> getColumns(String tableName) throws SQLException{
		Connection conn = DbConn.getconn(classNames, url, user, psw);
		DatabaseMetaData dbmd = conn.getMetaData();
		ResultSet tableMessage = dbmd.getColumns(null,null,tableName,null);
		int countTable=0; 
		List<String> columnsList = new ArrayList<String>();
		while(tableMessage.next()){
			if(countTable >= 3){
				columnsList.add(tableMessage.getString(4));
			}
			//System.out.println(tableMessage.getString(4));    
			countTable++;   
		}
        //System.out.println("==============="+countTable);
		System.out.println("测试/******/getColumns()"+columnsList.size());
        return columnsList;
	}
	/**
	 * 进行数据库的基本连接
	 * */
	public static Connection getconn(String className, String connUrl, String uname, String upsw){
		classNames = className;
		url = connUrl; 
		user = uname;
		psw = upsw;
		try {
			Class.forName(classNames).newInstance();
		} catch (InstantiationException e) {
			System.out.println("wrong");
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, psw);
		} catch (SQLException e) {
			e.printStackTrace();	
		}
		return conn;
	}
	/**
	 * 进行数据库连接的关闭
	 * */
	public static void closeConn(Connection conn){
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
