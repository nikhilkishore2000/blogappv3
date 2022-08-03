package com.blogapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="roles")
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, unique = true , length = 50)
	private String name;

	public Role(int id) {
		super();
		this.id = id;
	}

	public Role(String name) {
		super();
		this.name = name;
	}
	
	@Override
	public String toString() {
	    return this.name;
	}
	
	
}
