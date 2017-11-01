package zhu.aa.email;

import java.io.File;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

public class EmailHelper {

	private String host;
	private String username;
	private String password;
	private String from;

	private String to;
	private String subject;
	private String htmlContent;
	private String attachedFileName;

	public EmailHelper(String host, String username, String password, String from)
			throws AddressException, MessagingException {
		this.host = host;
		this.username = username;
		this.password = password;
		this.from = from;
	}

	public String send() throws Exception {

		try {
			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.localhost", "127.0.0.1");
			final String username1 = username;
			final String password1 = password;

			Session session = Session.getInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username1, password1);
				}
			});

			Message message = new MimeMessage(session);

			message.setFrom(new InternetAddress(from));

			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

			message.setSubject(subject);

			Multipart multipart = new MimeMultipart();

			if (htmlContent != null) {
				System.out.println(" has html ");

				BodyPart htmlPart = new MimeBodyPart();
				htmlPart.setContent(htmlContent, "text/html;charset=UTF-8");
				multipart.addBodyPart(htmlPart);
			}

			if (attachedFileName != null) {
				System.out.println(" has attached file ");
				BodyPart attchmentPart = new MimeBodyPart();
				DataSource source = new FileDataSource(attachedFileName);
				attchmentPart.setDataHandler(new DataHandler(source));
				// 中文乱码问题
				attchmentPart.setFileName(MimeUtility.encodeText(attachedFileName));
				multipart.addBodyPart(attchmentPart);
			}
			//
			System.out.println(" 异常反馈附件 ");
			BodyPart attchmentPart = new MimeBodyPart();
			File f = new File("问题反馈表.xlsx");
			String path = Mainas.class.getClassLoader().getResource("问题反馈表.xlsx").getPath();
			DataSource source = new FileDataSource(path);
			attchmentPart.setDataHandler(new DataHandler(source));
			// 中文乱码问题
			attchmentPart.setFileName(MimeUtility.encodeText(f.toString()));
			multipart.addBodyPart(attchmentPart);
			//
			message.setContent(multipart);
			Transport.send(message);

			System.out.println(" Sent ");
			return "已发送";
		} catch (Exception e) {
			e.printStackTrace();
			return "发送失败" + e.getMessage();
		}

	}

	public void setTo(String to) {
		this.to = to;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setHtmlContent(String htmlContent) {
		this.htmlContent = htmlContent;
	}

	public void setAttachedFileName(String attachedFileName) {
		this.attachedFileName = attachedFileName;
	}
}