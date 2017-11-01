package zhu.aa.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import zhu.aa.pojo.Bill;
import zhu.aa.pojo.Org;
import zhu.aa.utils.OracleConn;

public class ORGDaoImpl implements ORGDao {

	OracleConn oc = new OracleConn();

	// 1. 获取所有的符合条件的服务商的id,name,email
	public List<Org> getOrg() {
		List<Org> list = new ArrayList<Org>();
		// 获得连接
		Connection conn = oc.getConn();
		// 创建prepareStatement对象
		PreparedStatement stat = null;
		// 结果集
		ResultSet rs = null;
		// 记录一共多少条数据
		int i = 0;
		String sql = " select t.org_id，t.name，t.email from tp_org t left join tp_org_type ty on t.org_id=ty.org_id where delmark='0' "
				+ "and isnew = 'N' and type_id = '01' and t.email is not null";
		try {
			stat = conn.prepareStatement(sql);
			rs = stat.executeQuery();
			while (rs.next()) {
				Org org = new Org();
				org.setOrg_id(rs.getString("ORG_ID"));
				org.setName(rs.getString("NAME"));
				org.setEmail(rs.getString("EMAIL"));
				list.add(org);
				i++;
			}
			System.out.println("共有" + i + "条数据");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// 逐一将上面的几个对象关闭，因为不关闭的话会影响性能、并且占用资源
				// 注意关闭的顺序，最后使用的最先关闭
				if (rs != null)
					rs.close();
				if (stat != null)
					stat.close();
				if (conn != null)
					conn.close();
				System.out.println("数据库连接已关闭！");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;

	}

	// 2.获取所有的符合条件的服务商的工单的id
	public List<String> getOrgBillboardId(String orgid) {
		List<String> list = new ArrayList<String>();
		// 获得连接
		Connection conn = oc.getConn();
		// 创建prepareStatement对象
		PreparedStatement stat = null;
		// 结果集
		ResultSet rs = null;
		// 记录一共多少条数据
		int i = 0;
		// 当前时间上一个月的服务商对账账单
		String sql = "select f.work_item_id from td_fee_detail f right join td_rescue_work_item i on f.work_item_id = i.work_item_id"
				+ " where rescue_org_id = ? and to_char(rescue_time,'yyyy-mm')=to_char(add_months(sysdate,-1),'yyyy-mm')";
		try {
			stat = conn.prepareStatement(sql);
			stat.setString(1, orgid);
			rs = stat.executeQuery();
			while (rs.next()) {
				String billid = rs.getString(1);
				if (!list.contains(billid) && rs.getString(1) != null) {
					list.add(billid);
				}
				i++;
			}
			System.out.println("共有" + i + "条数据");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// 逐一将上面的几个对象关闭，因为不关闭的话会影响性能、并且占用资源
				// 注意关闭的顺序，最后使用的最先关闭
				if (rs != null)
					rs.close();
				if (stat != null)
					stat.close();
				if (conn != null)
					conn.close();
				System.out.println("数据库连接已关闭！");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;

	}

	// 根据账单id获取账单详细信息-->调视图。
	public List<Bill> getBillMessage(String billid) {
		List<Bill> list = new ArrayList<Bill>();
		// 获得连接
		Connection conn = oc.getConn();
		// 创建prepareStatement对象
		PreparedStatement stat = null;
		// 结果集
		ResultSet rs = null;
		// 记录一共多少条数据
		int i = 0;
		// 测试sql 因为测试库中没有当前时间上一个月的服务商对账账单
		String sql = "select * from ve_bill_fee_detail where work_item_id = ? and fee_type_desc like '%中联车盟向机构支付%'";
		try {
			stat = conn.prepareStatement(sql);
			stat.setString(1, billid);
			rs = stat.executeQuery();
			while (rs.next()) {
				// 创建一个新的账单雷
				// 封装相关的账单属性
				Bill bill = new Bill();
				// 服务号
				bill.setSERVICE_TRADE_ID(rs.getString("SERVICE_TRADE_ID"));
				// 救援时间
				bill.setRESCUE_TIME(rs.getString("RESCUE_TIME"));
				// 客户名称
				bill.setUSER_NAME(rs.getString("USER_NAME"));
				// 所属机构
				bill.setNAME_S(rs.getString("NAME_S"));
				// 省
				bill.setCITY(rs.getString("CITY"));
				// 市
				bill.setAREAS(rs.getString("AREAS"));
				// 县
				bill.setCOUNTRY(rs.getString("COUNTRY"));
				// 车牌
				bill.setLICENSE(rs.getString("LICENSE"));
				// 故障类型
				bill.setCASEDESC(rs.getString("CASEDESC"));
				// 救援结果
				bill.setSTATUS_DETAIL(rs.getString("STATUS_DETAIL"));
				// 小计金额
				bill.setCOMFIRMED_FEE(rs.getString("COMFIRMED_FEE"));
				// 拖车公里数
				bill.setTRAIL_RANGE_KM(rs.getString("TRAIL_RANGE_KM"));
				// 超范围补助公里数
				bill.setOUTER_RANGE_KM(rs.getString("OUTER_RANGE_KM"));
				// 费用类型
				bill.setFEE_TYPE_DESC(rs.getString("FEE_TYPE_DESC"));
				// 照片审核状态
				bill.setPIC_AUDIT_STATE(rs.getString("PIC_AUDIT_STATE"));
				// 救援单位名称
				bill.setName(rs.getString("NAME"));
				if (bill != null) {
					list.add(bill);
				}
				i++;
			}
			System.out.println("共有" + i + "条数据");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// 逐一将上面的几个对象关闭，因为不关闭的话会影响性能、并且占用资源
				// 注意关闭的顺序，最后使用的最先关闭
				if (rs != null)
					rs.close();
				if (stat != null)
					stat.close();
				if (conn != null)
					conn.close();
				System.out.println("数据库连接已关闭！");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;

	}

	// 查询省份
	public String findProvince(String orgid) {
		String province = null;
		// 获得连接
		Connection conn = oc.getConn();
		// 创建prepareStatement对象
		PreparedStatement stat = null;
		// 结果集
		ResultSet rs = null;
		// 记录一共多少条数据
		int i = 0;
		// 测试sql 因为测试库中没有当前时间上一个月的服务商对账账单
		String sql = "select a.full_name from tp_org o left join tp_area  a on o.area_id = a.area_id where o.org_id = ?";
		try {
			stat = conn.prepareStatement(sql);
			stat.setString(1, orgid);
			rs = stat.executeQuery();
			while (rs.next()) {
				province = rs.getString("full_name");
				System.out.println(province);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// 逐一将上面的几个对象关闭，因为不关闭的话会影响性能、并且占用资源
				// 注意关闭的顺序，最后使用的最先关闭
				if (rs != null)
					rs.close();
				if (stat != null)
					stat.close();
				if (conn != null)
					conn.close();
				System.out.println("数据库连接已关闭！");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return province;
	}

	// 存储发送明细
	public void sendDetail(String orgid, String province, String email, String mes, String date) {
		// 获得连接
		Connection conn = oc.getConn();
		// 创建prepareStatement对象
		PreparedStatement stat = null;
		// 结果集
		ResultSet rs = null;
		// 记录一共多少条数据
		int i = 0;
		// 测试sql 因为测试库中没有当前时间上一个月的服务商对账账单
		String sql = "insert into td_email_detail values(?,?,?,?,?,?,?,?,?,?,?)";
		try {
			stat = conn.prepareStatement(sql);
			stat.setString(1, orgid);
			stat.setString(2, province);
			stat.setString(3, email);
			stat.setString(4, mes);
			stat.setString(5, date);
			stat.setString(6, null);
			stat.setString(7, null);
			stat.setString(8, null);
			stat.setString(9, null);
			stat.setString(10, null);
			stat.setString(11, null);
			int iii = stat.executeUpdate();
			System.out.println(iii);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// 逐一将上面的几个对象关闭，因为不关闭的话会影响性能、并且占用资源
				// 注意关闭的顺序，最后使用的最先关闭
				if (rs != null)
					rs.close();
				if (stat != null)
					stat.close();
				if (conn != null)
					conn.close();
				System.out.println("数据库连接已关闭！");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// 调用存储过程生成账单
	public void createSpBill(String billname, String orgid, String startdate, String enddate) {
		// 获得连接
		Connection conn = oc.getConn();
		// 创建prepareStatement对象
		PreparedStatement stat = null;
		// 结果集
		ResultSet rs = null;
		SimpleDateFormat s1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			CallableStatement proc = null;
			proc = conn.prepareCall("{ call PK_BILL.CREATE_SP_BILL(?,?,?,?,?,?,?,?,?,?,?) }"); // 设置存储过程
			proc.setString(1, billname);// 设置第一个参数输入参数
			java.util.Date s2 = s1.parse(startdate);
			proc.setString(2, "O");
			proc.setString(3, orgid);// 服务商id
			proc.setString(4, "0");// 默认设置0
			proc.setDate(5, new java.sql.Date(s2.getTime()));
			java.util.Date s3 = s1.parse(enddate);
			proc.setDate(6, new java.sql.Date(s3.getTime()));
			System.out.println(s3.getTime());
			proc.setDate(7, null);// 和超超讨论设置为null
			proc.setDate(8, null);// 和超超讨论设置为null
			proc.setInt(9, -1);// 系统生成 没有操作员。。 操作员id
			proc.registerOutParameter(10, Types.INTEGER);// 第二个参数输出参数,是VARCHAR类型的
			proc.registerOutParameter(11, Types.VARCHAR);// 第二个参数输出参数,是VARCHAR类型的
			proc.execute();// 执行
			int num = proc.getInt(10);
			String mes = proc.getString(11);// 获得输出参数
			System.out.println("num的值是：" + num);
			System.out.println("mes的信息是：" + mes);
		} catch (SQLException ex2) {
			ex2.printStackTrace();
		} catch (Exception ex2) {
			ex2.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
					if (stat != null) {
						stat.close();
					}
					if (conn != null) {
						conn.close();
					}
				}
				System.out.println("yiguanbi");
			} catch (SQLException ex1) {
			}
		}
	}
}
