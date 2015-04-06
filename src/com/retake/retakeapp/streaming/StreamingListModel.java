package com.retake.retakeapp.streaming;

import java.util.ArrayList;
import java.util.List;

import com.retake.retakeapp.base.BaseModel;

public class StreamingListModel extends BaseModel {

	List<StreamingModel> streamingList;

	public StreamingListModel() {
		this.streamingList = new ArrayList<StreamingModel>();
	}

	public List<StreamingModel> getStreamingList() {
		return streamingList;
	}

	public void setList(List<StreamingModel> streamingList) {
		this.streamingList = streamingList;
	}
}