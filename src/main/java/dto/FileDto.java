package dto;

import java.io.Serializable;

public class FileDto  implements Serializable{

	private static final long serialVersionUID = 1L;
	/**
	 * 文件名
	 */
	private String fileName;
	/**
	 * 文件路径
	 */
	private String fileUrl;
	
	public FileDto() {
		super();
	}
	
	public FileDto(String fileName, String fileUrl) {
		super();
		this.fileName = fileName;
		this.fileUrl = fileUrl;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	
}
