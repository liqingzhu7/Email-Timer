package zhu.aa.email.notsign;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import zhu.aa.dao.ORGDaoImpl;

public class CreateBillForNotSign {
	public static void main(String[] args) {

		ORGDaoImpl DaoImpl = new ORGDaoImpl();
		File file = new File("D:/org.txt");
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String s = null;
			int i = 0;

			while ((s = br.readLine()) != null) {
				System.out.println(s);
				i++;
				if (i < 8) {
					String billname = "2017-6-1至2017-6-30";
					String startdate = "2017-6-1 00:00:01";
					String lastdate = "2017-6-30 23:59:59";
					DaoImpl.createSpBill(billname, s, startdate, lastdate);
				}else {
					String billname = "2017-7-1至2017-7-31";
					String startdate = "2017-7-1 00:00:01";
					String lastdate = "2017-7-31 23:59:59";
					DaoImpl.createSpBill(billname, s, startdate, lastdate);
				}
			}
			System.out.println(i);
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
