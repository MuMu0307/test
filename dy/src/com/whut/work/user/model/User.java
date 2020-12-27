package com.whut.work.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.whut.work.scenic.model.Scenic;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="user")
public class User {

	@Id
	@GeneratedValue
	private Integer id;
	private String username;
	@JsonIgnore
	private String password;
    private String tel;
    private String email;
	@Column(name="create_time")
	private Date createTime;
	@Column(name="is_delete")
	private Boolean isDelete;
    /*@JsonIgnore
    @OneToOne(mappedBy = "user",fetch = FetchType.LAZY)
	private Scenic scenic;*/

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Boolean getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

	/*public Scenic getScenic() {
		return scenic;
	}

	public void setScenic(Scenic scenic) {
		this.scenic = scenic;
	}*/
}
