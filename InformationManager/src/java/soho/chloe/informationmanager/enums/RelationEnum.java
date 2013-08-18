package soho.chloe.informationmanager.enums;

public enum RelationEnum {
	HOST(1), SPOUSE(2);

	private int code;

	private RelationEnum(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}
