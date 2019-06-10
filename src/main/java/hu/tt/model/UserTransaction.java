package hu.tt.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
public class UserTransaction {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String transactionType;
//	@ManyToOne	
//	@JoinColumn(name = "user" ,referencedColumnName = "id")
	private Long user;
	private BigDecimal value;
//	@OneToOne
	private Long toUser;
	@Temporal(TemporalType.TIMESTAMP)
	private Date transactionDate;
	
	
	public Date getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public Long getUser() {
		return user;
	}
	public void setUser(Long user) {
		this.user = user;
	}
	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	public Long getToUser() {
		return toUser;
	}
	public void setToUser(Long toUser) {
		this.toUser = toUser;
	}
	@Override
	public String toString() {
		return "UserTransaction [id=" + id + ", transactionType=" + transactionType + ", user=" + user + ", value="
				+ value + ", toUser=" + toUser + ", transactionDate=" + transactionDate + "]";
	}
	
	
	
	
}
