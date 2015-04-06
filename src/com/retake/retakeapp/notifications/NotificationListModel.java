package com.retake.retakeapp.notifications;

import java.util.ArrayList;
import java.util.List;

import com.retake.retakeapp.base.BaseModel;

public class NotificationListModel extends BaseModel {

	List<NotificationModel> notificationsList;

	public NotificationListModel() {
		this.notificationsList = new ArrayList<NotificationModel>();
	}

	public List<NotificationModel> getNotificationsList() {
		return notificationsList;
	}

	public void setList(List<NotificationModel> notificationsList) {
		this.notificationsList = notificationsList;
	}
}