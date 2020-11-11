package com.example.gcp.bean;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter@Setter
@NoArgsConstructor
public class InfraConfig {
	
	private int appComplemxity;
	private int appAvaliablity;
	private int appSecurity;
	private int vpn;
	private int dbType;
	private int dbSize;
	

}
