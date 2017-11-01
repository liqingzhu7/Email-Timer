package zhu.aa.email;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.TimerTask;
import java.util.logging.Logger;

import javax.servlet.ServletContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import oracle.net.aso.s;
import zhu.aa.dao.ORGDao;
import zhu.aa.dao.ORGDaoImpl;
import zhu.aa.excel.BillExcel;
import zhu.aa.pojo.Bill;
import zhu.aa.pojo.Org;
import zhu.aa.service.ORGServiceImpl;

/**
 * 
 * @ClassName: SendEmail
 * @Description: 账单email发送的主题类
 * @author liqz1504
 * @date 2017年10月30日 上午10:17:56
 *
 */
public class SendEmail extends TimerTask {
	private ServletContext context = null;

	public SendEmail(ServletContext context) {
		this.context = context;
	}

	private Log log = LogFactory.getLog(SendEmail.class);

	@Override
	public void run() {
		/**
		 * : dataservice@aachina.net : Adf&a234*D
		 */
		String host = "mail.aachina.net"; // use your smtp server host
		final String username = "dataservice@aachina.net"; // use your username
		final String password = "Adf&a234*D"; // use your password
		String from = "dataservice@aachina.net"; // use your sender email
													// address
		String name = null;
		// 加载配置文件
		InputStream is = SendEmail.class.getClassLoader().getResourceAsStream("config.properties");
		InputStreamReader is2 = new InputStreamReader(is);
		Properties p = new Properties();

		try {
			p.load(is2);
			ORGServiceImpl orgServiceImpl = new ORGServiceImpl();
			Calendar cal = Calendar.getInstance();
			// 每月1号执行任务
			if (cal.get(Calendar.DATE) == Integer.parseInt(p.getProperty("sendtime"))) {
				log.info("日期正确，执行任务");
				// 获取服务商信息
				ORGDao orgDaoImpl = new ORGDaoImpl();
				List<Org> list = orgDaoImpl.getOrg();
				// int i = 0;
				for (Org org : list) {
					String org_id = org.getOrg_id();
					String orgname = org.getName();
					System.out.println(org_id + "," + orgname);
					name = orgname.split("ID")[0];
					List<Bill> message = orgServiceImpl.getMessage(org_id);
					if (message.size() != 0) {
						new BillExcel().createExcel(message, name);
						// 获取服务商邮箱
						String email = org.getEmail();
						// String email = "liqz1504@aachina.net";
						EmailHelper emailHelper = new EmailHelper(host, username, password, from);
						emailHelper.setTo(email);
						emailHelper.setSubject("账单明细");
						emailHelper.setHtmlContent(p.getProperty("emaiContent"));
						int y = Calendar.getInstance().get(Calendar.YEAR);
						int m = Calendar.getInstance().get(Calendar.MONTH);
						if (m == 0) {
							m = 12;
							y = y - 1;
						}
						// 账单名称
						File file = new File(name + y + "年" + m + "月账单.xls");
						// 添加账单附件
						emailHelper.setAttachedFileName(file.toString());
						String mes = emailHelper.send();
						orgServiceImpl.createSpBill(org_id);
						// 含机构ID,省份、服务商对账邮箱、账单发送详情
						String province = orgDaoImpl.findProvince(org_id);
						Date da = new Date();
						DateFormat df = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
						String date = df.format(da);
						new ORGDaoImpl().sendDetail(org_id, province, email, mes, date);
						System.out.println("邮件已发送");
						file.delete();
						System.out.println("发送完成的附件已删除");
					}
				}
				// 关闭流
				is.close();
				is2.close();
				System.out.println("所有账单发送执行完成");
			} else {
				log.info("不是规定日期  不执行任务");
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}
}