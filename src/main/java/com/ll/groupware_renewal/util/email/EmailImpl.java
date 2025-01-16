package com.ll.groupware_renewal.util.email;

import com.ll.groupware_renewal.constant.ConstantEmail;
import com.ll.groupware_renewal.entity.UserEmail;
import com.sun.jdi.connect.Transport;
import jakarta.websocket.Session;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.Store;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Component
@RequiredArgsConstructor
public class EmailImpl implements Email {
	private String Host;
	private String UserEmail;
	private String UserPwd;
	private String ToEmail;
	private String Subject;
	private String Content;
	private Store store;
	private List<UserEmail> list;

	@Override
	public void sendEmail(String email, int Num) {
		this.ToEmail = email;
		// 제목 설정
		this.Subject = ConstantEmail.getSubject();
		try {
			Subject = new String(Subject.getBytes("iso-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// 추후에 DB에 이문구도 저장하여 가져올 예정
		this.Content = ConstantEmail.getContentBeforeNum() + Num + ConstantEmail.getContentAfterNum();
		try {
			Content = new String(Content.getBytes("iso-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Properties Properties = new Properties();
		Properties.put(ConstantEmail.getMailSmtpAuth(), true);
		Session session = Session.getDefaultInstance(Properties);
		MimeMessage Msg = new MimeMessage(session);

		try {
			Msg.setSubject(Subject);
			Msg.setText(Content);
			Msg.setFrom(new InternetAddress(UserEmail));
			Msg.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(ToEmail));

			Transport transport = session.getTransport(ConstantEmail.getSmtps());
			transport.connect(Host, UserEmail, UserPwd);
			transport.sendMessage(Msg, Msg.getAllRecipients());
			transport.close();

		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}

	public boolean AuthNum(int authNum, int num) {
		if (authNum == num) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean CheckEmailLogin(String id, String pw) {
		boolean CheckEmailLogin = false;
		// Create empty properties
		Properties props = new Properties();
		// POP3 주소
		String host = ConstantEmail.getHostURL();
		// session생성
		Session session = Session.getDefaultInstance(props, null);
		// store생성
		this.store = null;

		try {
			store = session.getStore(ConstantEmail.getPop3Store());
			store.connect(host, id, pw);
			if (store.isConnected()) {
				CheckEmailLogin = true;
			} else {
				CheckEmailLogin = false;
			}
		} catch (Exception e) {
		}

		return CheckEmailLogin;
	}

	@Override
	public List<UserEmail> printEmailList() {
		list = new ArrayList<UserEmail>();
		// 게시판 번호 설정
		int counter = 1;
		// folder생성
		Folder folder = null;

		try {
			// Get folder
			folder = store.getFolder(ConstantEmail.getINBOX());
			folder.open(Folder.READ_ONLY);
			// 이메일 박스
			Message message[] = folder.getMessages();

			// Get directory(여기서 이메일 내용들 주르륵 보여주는 거임)
			for (int i = 0, n = message.length; i < n; i++) {
				// read를 하면서 userEmail을 reset해준다.
				UserEmail userEmail = new UserEmail();
				userEmail.setCounter(counter);
				int location = 0;
				int location2 = 0;

				Address from = message[i].getFrom()[0];
				String sFrom = from + "\t";
				// 온전한 이메일주소찾오는거

				if (sFrom.indexOf("<") != -1) {
					location = sFrom.indexOf("<");
					if (sFrom.indexOf(">") != -1) {
						location2 = sFrom.indexOf(">");
					}
				}

				if (location != 0 && location2 != 0 && sFrom.contains("<") && sFrom.contains(">")) {
					sFrom = sFrom.substring(location + 1, location2); // 그냥 location하면 /까지 출력됨
				}
				userEmail.setFrom(sFrom);

				String Title = message[i].getSubject();
				userEmail.setTitle(Title);

				String contentType = message[i].getContentType();
				String content = "";
				if (contentType.contains(ConstantEmail.getMultipart())) {
					Multipart multipart = (Multipart) message[i].getContent();
					int numofparts = multipart.getCount();
					for (int k = 0; k < numofparts; k++) {
						MimeBodyPart part = (MimeBodyPart) multipart.getBodyPart(k);
						content = part.getContent().toString();
					}
				} else if (contentType.contains(ConstantEmail.getTextPlain())
						|| contentType.contains(ConstantEmail.getTextHtml())) {
					String ObjectContent = message[i].getContent().toString();
					if (ObjectContent != null) {
						content = ObjectContent.toString();
					}
				}
				userEmail.setContent(content);

				String date = message[i].getSentDate().toString();

				date = date.replaceAll(ConstantEmail.getMon(), ConstantEmail.getKoreanMon());
				date = date.replaceAll(ConstantEmail.getTue(), ConstantEmail.getKoreanTue());
				date = date.replaceAll(ConstantEmail.getWed(), ConstantEmail.getKoreanWed());
				date = date.replaceAll(ConstantEmail.getThu(), ConstantEmail.getKoreanThu());
				date = date.replaceAll(ConstantEmail.getFri(), ConstantEmail.getKoreanFri());
				date = date.replaceAll(ConstantEmail.getSat(), ConstantEmail.getKoreanSat());
				date = date.replaceAll(ConstantEmail.getSun(), ConstantEmail.getKoreanSun());

				date = date.replaceAll(ConstantEmail.getJan(), ConstantEmail.getNumJan());
				date = date.replaceAll(ConstantEmail.getFeb(), ConstantEmail.getNumFeb());
				date = date.replaceAll(ConstantEmail.getMar(), ConstantEmail.getNumMar());
				date = date.replaceAll(ConstantEmail.getApr(), ConstantEmail.getNumApr());
				date = date.replaceAll(ConstantEmail.getMay(), ConstantEmail.getNumMay());
				date = date.replaceAll(ConstantEmail.getJun(), ConstantEmail.getNumJun());
				date = date.replaceAll(ConstantEmail.getJul(), ConstantEmail.getNumJul());
				date = date.replaceAll(ConstantEmail.getAug(), ConstantEmail.getNumAug());
				date = date.replaceAll(ConstantEmail.getSep(), ConstantEmail.getNumSep());
				date = date.replaceAll(ConstantEmail.getOct(), ConstantEmail.getNumOct());
				date = date.replaceAll(ConstantEmail.getNov(), ConstantEmail.getNumNov());
				date = date.replaceAll(ConstantEmail.getDec(), ConstantEmail.getNumDec());
				
				date = new String(date.getBytes("iso-8859-1"), "utf-8");
				// 일반 빈칸을 html이 인식할 수 있는 공백으로 변환
				date = date.replaceAll(" ", "&nbsp;");
				// KST를 제거한다.
				date = date.replaceAll("KST", "");

				userEmail.setDate(date);
				// 첨부파일이 있을 경우 파일에다가 mail을 붙인다.
				list.add(userEmail);
				counter++;
			}

			// Close connection
			folder.close(false);
			store.close();

		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
		}

		return list;

	}

	@Override
	public List<UserEmail> GetEmailList() {
		return list;
	}
}