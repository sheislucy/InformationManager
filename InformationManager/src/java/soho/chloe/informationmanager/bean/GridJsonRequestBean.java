package soho.chloe.informationmanager.bean;

import java.io.Serializable;

/**
 * Request bean used for jgGrid request
 * 
 * @author lucy
 *
 */
public class GridJsonRequestBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private int page;
	private int rows;
	private String sidx = null;
	private String sord = null;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public String getSidx() {
		return sidx;
	}

	public void setSidx(String sidx) {
		this.sidx = sidx;
	}

	public String getSord() {
		return sord;
	}

	public void setSord(String sord) {
		this.sord = sord;
	}

}
