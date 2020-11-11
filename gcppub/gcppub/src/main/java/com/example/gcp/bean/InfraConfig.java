package com.example.gcp.bean;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter@Setter
@NoArgsConstructor
@ToString
public class InfraConfig {
	
	private int appComplemxity;
	private int appAvaliablity;
	private int appSecurity;
	private int vpn;
	private int dbType;
	private int dbSize;
	

}
