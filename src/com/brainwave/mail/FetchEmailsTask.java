package com.brainwave.mail;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

import com.brainwave.email_client.GmailActivity;
import com.brainwave.email_client.MailSystemApplication;
import com.brainwave.mail.MailSystem.AccountType;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

/**
 * 
 * @author Mustansar Saeed
 *
 */
public class FetchEmailsTask extends AsyncTask {
	ProgressDialog statusDialog;
	GmailActivity callerActivity;
	
	public FetchEmailsTask(GmailActivity activity)
	{
		this.callerActivity = activity;
	}
	
	@Override
	protected void onPreExecute() {
		statusDialog = new ProgressDialog(callerActivity);
		statusDialog.setMessage("Preparing to fetch emails ...");
		statusDialog.setIndeterminate(false);
		statusDialog.setCancelable(false);
		statusDialog.show();
	}
	
	@Override
	protected void onProgressUpdate(Object... values) {
		// TODO Auto-generated method stub
		statusDialog.setMessage(values[0].toString());
	}
	
	
	@Override
	protected Message[] doInBackground(Object... params) {
		// TODO Auto-generated method stub
		
		String username = params[0].toString();
		String password = params[1].toString();
		AccountType accountType = (AccountType) params[2];
		String folderName = params[3].toString();
		String host = params[4].toString();
		
		Message[] mails = null;
		publishProgress("Fetching emails ...");
		List<Message> messages = MailSystemApplication.mailSystem.fetchEmailMessages(username, password, 
				folderName, accountType, host);
		publishProgress("Fetching completed.");
		mails = (Message[])messages.toArray();
		return Arrays.copyOfRange(mails, 0, 4);
	}
	
	@Override
	protected void onPostExecute(Object result) {
		statusDialog.dismiss();
//		callerActivity.emailsFetched((Message[])result);
	}
}
