package com.jov.isaac.is;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jov.isaac.is.util.CommonUtil;
import com.jov.isaac.is.util.MailSender;

@SuppressLint("NewApi")
public class AboutActivity extends Activity {
	private Button sendMsgBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		initView();
	}

	private void initView() {
		sendMsgBtn = (Button) findViewById(R.id.send_suggest_btn);
		final EditText tx = (EditText) findViewById(R.id.suggest);
		sendMsgBtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (!CommonUtil.isNetWorkConnected(AboutActivity.this)) {
					showAlert("亲，木有网络……");
					return;
				}
				String msg = tx.getText().toString();
				if (CommonUtil.isEmpty(msg)) {
					showAlert("亲，你忘了什么东西吧？");
					return;
				} else if (msg.length() < 10) {
					showAlert("亲，咱们不能太短啊...");
					return;
				}
				SenderRunnable senderRunnable = new SenderRunnable(
						"funny_ba@163.com", "funny_ba@163");
				senderRunnable.setMail("new suggest", msg, "funny_ba@163.com",
						"");
				new Thread(senderRunnable).start();
				showAlert("亲，谢谢你的意见，我一定改……");
				sendMsgBtn.setClickable(false);
				tx.setEnabled(false);
			}
		});
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == android.R.id.home) {
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void showAlert(String msg) {
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}
}

class SenderRunnable implements Runnable {

	private String user;
	private String password;
	private String subject;
	private String body;
	private String receiver;
	private MailSender sender;
	private String attachment;

	public SenderRunnable(String user, String password) {
		this.user = user;
		this.password = password;
		sender = new MailSender(user, password);
		String mailhost = user.substring(user.lastIndexOf("@") + 1,
				user.lastIndexOf("."));
		if (!mailhost.equals("gmail")) {
			mailhost = "smtp." + mailhost + ".com";
			sender.setMailhost(mailhost);
		}
	}

	public void setMail(String subject, String body, String receiver,
			String attachment) {
		this.subject = subject;
		this.body = body;
		this.receiver = receiver;
		this.attachment = attachment;
	}

	public void run() {
		try {
			sender.sendMail(subject, body, user, receiver, attachment);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
