package com.brainwave.email_client;

import com.brainwave.mail.MailSystem;

import android.app.Application;

/**
 * 
 * @author Mustansar Saeed
 *
 */
public class MailSystemApplication extends Application {
	public static MailSystem mailSystem = MailSystem.getMailSystem();
}
