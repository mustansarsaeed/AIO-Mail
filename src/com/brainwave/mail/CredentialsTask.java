package com.brainwave.mail;

import java.util.Properties;

import javax.mail.AuthenticationFailedException;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;

import com.brainwave.mail.MailSystem.AccountType;
import com.brainwave.main.AddAccountActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

/**
 * 
 * @author Mustansar Saeed
 *
 */
public class CredentialsTask extends AsyncTask{
	private ProgressDialog statusDialog;
	private AddAccountActivity callerActivity;
	
	public CredentialsTask(AddAccountActivity activity)
	{
		this.callerActivity = activity;
	}
	
	@Override
	protected void onPreExecute() {
		statusDialog = new ProgressDialog(callerActivity);
		statusDialog.setMessage("Verifying credentials ...");
		statusDialog.setIndeterminate(false);
		statusDialog.setCancelable(false);
		statusDialog.show();
	}
	
	@Override
	protected Object doInBackground(Object... object) {
		String username = object[0].toString();
		String password = object[1].toString();
		AccountType accountType = (AccountType)object[2];
		final String emailPort = "587";// gmail's smtp port
    	final String smtpAuth = "true";
    	final String starttls = "true";
    	final String emailHost = "smtp.gmail.com";
    	
    	Properties props = new Properties();
    	props.put("mail.store.protocol", "imaps");
    	props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.port", emailPort);
	    props.put("mail.smtp.auth", smtpAuth);
	    props.put("mail.smtp.starttls.enable", starttls);
	    
		Session session = Session.getInstance(props, null);
		
	    Transport transport;
		try {
			transport = session.getTransport("smtp");
			transport.connect(getHost(accountType), username, password);
			
		    transport.close();
		    Log.i("mustang", "Authenticaion succeeded.");
		    return true;
		} 
		catch(AuthenticationFailedException ex)
		{
			Log.i("mustang", "Authenticaion failed.");
			return false;
		}
		catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	protected void onPostExecute(Object result) {
		// TODO Auto-generated method stub
//		super.onPostExecute(result);
		
		Boolean ret = (Boolean) result;
		Log.i("mustang", "Authentication: " + ret.booleanValue());
		
		statusDialog.dismiss();
		callerActivity.invalidUserOrPassword(ret.booleanValue());
		
	}
	
	private String getHost(AccountType accountType) {
    	switch(accountType)
    	{
    	case GMAIL:
    	case YAHOO:
    		return "imap.gmail.com";
    	default:
    			return "imap.gmail.com";
    			
    	}
    }

}
