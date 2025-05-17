package com.hussain.entity;

import java.util.Date;

//public class Todo {
//
//}


import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Builder
//@Entity
//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
//@EntityListeners(AuditingEntityListener.class)
//public class Todo extends BaseModel {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Integer id;
//
//	private String title;
//
//	@Column(name = "status")
//	private Integer statusId;
//	
//	
//	
//	
//	public Todo() {
//		super();
//	}
//
//	public Todo(Integer createdBy, Date createdOn, Integer updatedBy, Date updatedOn, Integer id, String title,
//			Integer statusId) {
//		super(createdBy, createdOn, updatedBy, updatedOn);
//		this.id = id;
//		this.title = title;
//		this.statusId = statusId;
//	}
//
//	public Todo(Integer createdBy, Date createdOn, Integer updatedBy, Date updatedOn) {
//		super(createdBy, createdOn, updatedBy, updatedOn);
//	}
//
//	@Override
//	public Integer getCreatedBy() {
//		// TODO Auto-generated method stub
//		return super.getCreatedBy();
//	}
//
//	@Override
//	public void setCreatedBy(Integer createdBy) {
//		// TODO Auto-generated method stub
//		super.setCreatedBy(createdBy);
//	}
//
//	@Override
//	public Date getCreatedOn() {
//		// TODO Auto-generated method stub
//		return super.getCreatedOn();
//	}
//
//	@Override
//	public void setCreatedOn(Date createdOn) {
//		// TODO Auto-generated method stub
//		super.setCreatedOn(createdOn);
//	}
//
//	@Override
//	public Integer getUpdatedBy() {
//		// TODO Auto-generated method stub
//		return super.getUpdatedBy();
//	}
//
//	@Override
//	public void setUpdatedBy(Integer updatedBy) {
//		// TODO Auto-generated method stub
//		super.setUpdatedBy(updatedBy);
//	}
//
//	@Override
//	public Date getUpdatedOn() {
//		// TODO Auto-generated method stub
//		return super.getUpdatedOn();
//	}
//
//	@Override
//	public void setUpdatedOn(Date updatedOn) {
//		// TODO Auto-generated method stub
//		super.setUpdatedOn(updatedOn);
//	}
//
//	@Override
//	public int hashCode() {
//		// TODO Auto-generated method stub
//		return super.hashCode();
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		// TODO Auto-generated method stub
//		return super.equals(obj);
//	}
//
//	@Override
//	protected Object clone() throws CloneNotSupportedException {
//		// TODO Auto-generated method stub
//		return super.clone();
//	}
//
//	@Override
//	public String toString() {
//		// TODO Auto-generated method stub
//		return super.toString();
//	}
//
//	
//
//	
//
//}

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Todo extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    
    @Column(name = "status")
    private Integer statusId;

    // Constructors
    public Todo() {
        super();
    }

    public Todo(Integer id, String title, Integer statusId) {
        this.id = id;
        this.title = title;
        this.statusId = statusId;
    }

    // Manual getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }
}
