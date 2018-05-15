package com.demo.security;

//@Table(name = "user")
public class SystemUser {
	    private Long id;

	    private String userName;
	    private String password;

	    public SystemUser(){}

	    public SystemUser(SystemUser user){
	        this.userName = user.getUserName();
	        this.password = user.getPassword();
	        this.id = user.getId();
	    }
	    
	    public Long getId() {
	        return id;
	    }
	    public void setId(Long id) {
	        this.id = id;
	    }
	    public String getUserName() {
	        return userName;
	    }
	    public void setUserName(String userName) {
	        this.userName = userName;
	    }
	    public String getPassword() {
	        return password;
	    }
	    public void setPassword(String password) {
	        this.password = password;
	    }

}
