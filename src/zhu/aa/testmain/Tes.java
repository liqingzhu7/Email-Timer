package zhu.aa.testmain;

import java.io.IOException;
import java.util.Calendar;

public class Tes {
	public static void main(String[] args) throws IOException {
		Calendar cal = Calendar.getInstance();
		//cal.add(Calendar.MONTH, -1);
		int i=cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		System.out.println(i);
	}
}