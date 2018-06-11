package com.xj.toolsInTools.manager;

public class InfoParserException extends RuntimeException {
	public int errorLin;
	public String errorFile;
	public String errorMessage;

	public InfoParserException(int errorLin, String errorFile,
			String errorMessage) {
		super();
		this.errorLin = errorLin;
		this.errorFile = errorFile;
		this.errorMessage = errorMessage;
	}

	@Override
	public String getMessage() {
		return errorFile + "�ļ����� ����\n�ڵ�" + errorLin + "��  \n ����"
				+ errorMessage;
	}

}
