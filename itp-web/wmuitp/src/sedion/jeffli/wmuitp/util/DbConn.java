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
 * Ϊ����������������ķ��������޸ģ�connection���ӱ���ŵ����÷����У��������ھ���Ĳ�ѯ
 * �ȷ����У������Ȳ��ܿ�������Ҳ�ܷ���Դ��ÿ�����Ӷ��Ǻܺķ���Դ�ģ�
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
	 * ���ڲ�����ֵ�ǿ��ⲿservlet���д��ݵģ�������ȴ��Ҫ���ʹ�ã������Ϊȫ�ֱ���
	 * ��������servlet����ֵֻ��������������ʱ��,�����Ϊstatic
	 * */
	public static String classNames = null;
	public static String url = null;
	public static String user = null;
	public static String psw = null;
	/**
	 * ���в�ѯ��sql��ʵ��
	 * */
	@SuppressWarnings("unchecked")
	public static String[][] query(String sql){
		getData(sql);
		System.out.println("���Ҽ������� = "+rowCount);
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
	 * ִ�г���ѯ�������sql���
	 * */
	public static int exceptQuery(String sql){
		//System.out.println("��ȡ�����в���2 = "+ classNames+ url+ user + psw);
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
	 * ִ�в�ѯ��sql���
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
	 *���ұ�ļ�¼���� 
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
	 * ������ݿ��ṹ�����ֶΣ�
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
		System.out.println("����/******/getColumns()"+columnsList.size());
        return columnsList;
	}
	/**
	 * �������ݿ�Ļ�������
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
	 * �������ݿ����ӵĹر�
	 * */
	public static void closeConn(Connection conn){
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
