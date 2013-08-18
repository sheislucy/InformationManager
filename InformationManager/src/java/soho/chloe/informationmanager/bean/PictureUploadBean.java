package soho.chloe.informationmanager.bean;

public class PictureUploadBean extends JsonResultBean {
	private static final long serialVersionUID = 1L;
	private Integer pictureId;
	private String fileName;

	public Integer getPictureId() {
		return pictureId;
	}

	public void setPictureId(Integer pictureId) {
		this.pictureId = pictureId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
