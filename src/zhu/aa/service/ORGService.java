package zhu.aa.service;

import java.util.List;

import zhu.aa.pojo.Bill;
import zhu.aa.pojo.Org;

public interface ORGService {

	public List<Bill> getMessage(String orgid);

	List<Org> getOrgMessage();

	public void createSpBill(String orgid);
}
