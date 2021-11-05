package com.example.aistudymachine.widget.UploadUtils;

public interface ProgressListener {
    void onProgress(long currentBytes, long contentLength, boolean done);
}