package soho.chloe.informationmanager.bean;

public class HouseMiniDomainBean extends JsonResultBean {
	private static final long serialVersionUID = 1L;
	private int id; // house id
	private String name; // host name

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
