package soho.chloe.informationmanager.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Request bean used for jgGrid response
 * 
 * @author lucy
 * 
 */
public class GridJsonResponseBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private int page = 0; // current page number
	private int total = 0;// total page count
	private int records = 0;// total record count
	private List<Object> rows = new ArrayList<Object>();

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getRecords() {
		return records;
	}

	public void setRecords(int records) {
		this.records = records;
	}

	public List<Object> getRows() {
		return rows;
	}

	public void setRows(List<Object> rows) {
		this.rows = rows;
	}

}
