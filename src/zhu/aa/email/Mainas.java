package zhu.aa.email;

import javax.servlet.http.HttpServletRequest;

public class Mainas {
	public static void main(String[] args) throws Exception {
		String path = Mainas.class.getClassLoader().getResource("问题反馈表.xls").getPath();
		System.out.println(path);
	}
}
