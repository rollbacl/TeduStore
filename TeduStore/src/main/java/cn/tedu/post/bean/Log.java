package cn.tedu.post.bean;

import java.io.Serializable;

public class Log implements Serializable {
 
	private static final long serialVersionUID = -3218738067070204589L;
	
	private Integer id;
	private Integer userId;
	private String log;
	
	public Log() {
	}

	public Log(Integer id, Integer userId, String log) {
		super();
		this.id = id;
		this.userId = userId;
		this.log = log;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
	}

	@Override
	public String toString() {
		return "Log [id=" + id + ", userId=" + userId + ", log=" + log + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Log other = (Log) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
