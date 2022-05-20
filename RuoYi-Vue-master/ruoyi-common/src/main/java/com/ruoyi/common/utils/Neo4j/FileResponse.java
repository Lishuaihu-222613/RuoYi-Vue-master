package com.ruoyi.common.utils.Neo4j;

import java.util.List;

public class FileResponse {

	private int success;    //成功标准 0失败 1成功

	private List<FileResult> results;//文件results

    private String message; //错误信息

    public FileResponse() {
    }

    public FileResponse(int success, List<FileResult> results, String message) {
        this.success = success;
        this.results = results;
        this.message = message;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

	public List<FileResult> getResults() {
		return results;
	}

	public void setResults(List<FileResult> results) {
		this.results = results;
	}
}
