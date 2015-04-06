package com.retake.retakeapp.notifications;

import com.retake.retakeapp.base.BaseModel;

public class NotificationModel extends BaseModel {
	private String notificationTitle;
	private String notificationMessage;

	public NotificationModel(String notificationTitle,
			String notificationMessage) {
		super();
		this.notificationTitle = notificationTitle;
		this.notificationMessage = notificationMessage;
	}

	public String getNotificationMessage() {
		return notificationMessage;
	}

	public void setNotificationMessage(String notificationMessage) {
		this.notificationMessage = notificationMessage;
	}

	public String getNotificationTitle() {
		return notificationTitle;
	}

	public void setNotificationTitle(String notificationTitle) {
		this.notificationTitle = notificationTitle;
	}

}
