package com.retake.retakeapp.streaming;

import android.net.Uri;

import com.retake.retakeapp.base.BaseModel;

public class StreamingModel extends BaseModel {

	private String streamName;
	private Uri streamingLink;

	public StreamingModel(String streamName, Uri streamingLink) {
		super();
		this.streamName = streamName;
		this.streamingLink = streamingLink;
	}

	public String getStreamName() {
		return streamName;
	}

	public void setStreamName(String streamName) {
		this.streamName = streamName;
	}

	public Uri getStreamingLink() {
		return streamingLink;
	}

	public void setStreamingLink(Uri streamingLink) {
		this.streamingLink = streamingLink;
	}

}
