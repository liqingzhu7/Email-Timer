package zhu.aa.dao;

import java.util.List;

import zhu.aa.pojo.Bill;
import zhu.aa.pojo.Org;

public interface ORGDao {
	public List<Org> getOrg();

	public List<String> getOrgBillboardId(String orgid);

	public List<Bill> getBillMessage(String billid);

	public String findProvince(String orgid);

	public void sendDetail(String orgid, String province, String email, String mes, String date);

	public void createSpBill(String billname, String orgid, String startdate, String enddate);
}