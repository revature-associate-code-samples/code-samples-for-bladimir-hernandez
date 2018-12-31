package beans;

import java.io.Serializable;

public class Reimbursement implements Serializable{


	private static final long serialVersionUID = 1L;
	private int id;
	private String details;
	private int amount;
	private int r_employeeID;
	private int r_managerID;
	private String status;
	
	public Reimbursement() {
		super();
	}

	public Reimbursement(int id, String details, int amount, int r_employeeID, int r_managerID, String status) {
		super();
		this.id = id;
		this.details = details;
		this.amount = amount;
		this.r_employeeID = r_employeeID;
		this.r_managerID = r_managerID;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getR_employeeID() {
		return r_employeeID;
	}

	public void setR_employeeID(int r_employeeID) {
		this.r_employeeID = r_employeeID;
	}

	public int getR_managerID() {
		return r_managerID;
	}

	public void setR_managerID(int r_managerID) {
		this.r_managerID = r_managerID;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + amount;
		result = prime * result + ((details == null) ? 0 : details.hashCode());
		result = prime * result + id;
		result = prime * result + r_employeeID;
		result = prime * result + r_managerID;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimbursement other = (Reimbursement) obj;
		if (amount != other.amount)
			return false;
		if (details == null) {
			if (other.details != null)
				return false;
		} else if (!details.equals(other.details))
			return false;
		if (id != other.id)
			return false;
		if (r_employeeID != other.r_employeeID)
			return false;
		if (r_managerID != other.r_managerID)
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", details=" + details + ", amount=" + amount + ", r_employeeID="
				+ r_employeeID + ", r_managerID=" + r_managerID + ", status=" + status + "]";
	}

	
}
