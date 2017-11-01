package zhu.aa.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import zhu.aa.dao.ORGDao;
import zhu.aa.dao.ORGDaoImpl;
import zhu.aa.pojo.Bill;
import zhu.aa.pojo.Org;

public class ORGServiceImpl implements ORGService {

	ORGDao od = new ORGDaoImpl();

	// 获取服务商信息
	@Override
	public List<Org> getOrgMessage() {
		List<Org> org = od.getOrg();
		return org;
	}

	// 获取账单信息
	@Override
	public List<Bill> getMessage(String orgid) {
		List<Bill> billMessage = new ArrayList<Bill>();
		// 遍历服务商id 根据服务商id查询符合条件的工单id（条件：上一个自然月的服务商账单的工单号）
		List<String> orgBillboardId = od.getOrgBillboardId(orgid);
		if (orgBillboardId != null) {
			// 根据工单id查询账单明细
			for (String bid : orgBillboardId) {
				if (bid != null) {
					List<Bill> billMessage2 = od.getBillMessage(bid);
					billMessage.addAll(billMessage2);
					System.out.println(billMessage.size());
					billMessage.add(null);
					System.out.println(billMessage.size());
				}
			}
		}
		return billMessage;
	}

	// 存储生成账单信息
	public void createSpBill(String orgid) {
		// 获取当前时间
		Calendar cal = Calendar.getInstance();
		// 调到上个月
		cal.add(Calendar.MONTH, -1);
		// 按格式输出
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // HH:mm:ss
		// 获取前月的第一天
		cal.add(Calendar.MONTH, 0);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		String oneday = sdf.format(cal.getTime());
		//System.out.println(oneday);
		// 最后一天日期
		cal.add(Calendar.MONTH, 1);
		cal.set(Calendar.DAY_OF_MONTH, 0);
		String lastday = sdf.format(cal.getTime());
		//System.out.println(lastday);
		// 账单名称，服务商id，开始时间，结束时间
		String billname = oneday + "至" + lastday + "账单";
		String startdate = oneday + " 00:00:01";
		String lastdate = lastday + " 23:59:59";
		od.createSpBill(billname, orgid, startdate, lastdate);
	}

	public static void main(String[] args) {
		new ORGServiceImpl().createSpBill("1799");
	}
}
