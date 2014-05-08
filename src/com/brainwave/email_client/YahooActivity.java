package com.brainwave.email_client;

import com.brainwave.mail.MailSystem.AccountType;

import android.app.Activity;
import android.os.Bundle;

/**
 * 
 * @author Mustansar Saeed
 *
 */
public class YahooActivity extends EmailActivity {

	@Override
	public AccountType getAccountType() {
		// TODO Auto-generated method stub
		return AccountType.YAHOO;
	}
	
}
