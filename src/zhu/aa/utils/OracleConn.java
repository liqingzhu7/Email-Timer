package zhu.aa.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OracleConn {

	/**
	 * 连接Oracle数据库的代码
	 */
	public Connection getConn() {
		Connection con = null;// 创建一个数据库连接
		PreparedStatement pre = null;// 创建预编译语句对象，一般都是用这个而不用Statement
		ResultSet result = null;// 创建一个结果集对象
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");// 加载Oracle驱动程序
			System.out.println("开始尝试连接数据库！");
			String url = "jdbc:oracle:" + "thin:@192.168.5.111:1521:AACHINA";// 127.0.0.1是本机地址，XE是精简版Oracle的默认数据库名
			String user = "aachina";// 用户名,系统默认的账户名
			String password = "hk9etiw2q72s";// 你安装时选设置的密码 hk9etiw2q72s
			con = DriverManager.getConnection(url, user, password);// 获取连接
			System.out.println("连接成功！");
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*
		 * finally { try { // 逐一将上面的几个对象关闭，因为不关闭的话会影响性能、并且占用资源 //
		 * 注意关闭的顺序，最后使用的最先关闭 if (result != null) result.close(); if (pre !=
		 * null) pre.close(); if (con != null) con.close();
		 * System.out.println("数据库连接已关闭！"); } catch (Exception e) {
		 * e.printStackTrace(); } }
		 */
		return con;
	}
}
