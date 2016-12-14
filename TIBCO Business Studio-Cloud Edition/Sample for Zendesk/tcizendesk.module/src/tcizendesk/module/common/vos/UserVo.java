package tcizendesk.module.common.vos;

import java.util.Date;

public class UserVo {
	private long Id=0l;
	private String Url="";
	private Date CreatedAt;
	private Date UpdatedAt;
	private boolean Active=false;
	private boolean Verified=false;
	private boolean Shared=false;
	private long LocaleId=0l;
	private String TimeZone="";
	private long LastLoginAt=0l;
	private long OrganizationId=0l;
	private String Role="";
	private String Name="";
	private String Email="";
	private boolean Suspended=false;
	public long getId() {
		return Id;
	}
	public void setId(long id) {
		Id = id;
	}
	public String getUrl() {
		return Url;
	}
	public void setUrl(String url) {
		Url = url;
	}
	public Date getCreatedAt() {
		return CreatedAt;
	}
	public void setCreatedAt(Date createdAt) {
		CreatedAt = createdAt;
	}
	public Date getUpdatedAt() {
		return UpdatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		UpdatedAt = updatedAt;
	}
	public boolean isActive() {
		return Active;
	}
	public void setActive(boolean active) {
		Active = active;
	}
	public boolean isVerified() {
		return Verified;
	}
	public void setVerified(boolean verified) {
		Verified = verified;
	}
	public boolean isShared() {
		return Shared;
	}
	public void setShared(boolean shared) {
		Shared = shared;
	}
	public long getLocaleId() {
		return LocaleId;
	}
	public void setLocaleId(long localeId) {
		LocaleId = localeId;
	}
	public String getTimeZone() {
		return TimeZone;
	}
	public void setTimeZone(String timeZone) {
		TimeZone = timeZone;
	}
	public long getLastLoginAt() {
		return LastLoginAt;
	}
	public void setLastLoginAt(long lastLoginAt) {
		LastLoginAt = lastLoginAt;
	}
	public long getOrganizationId() {
		return OrganizationId;
	}
	public void setOrganizationId(long organizationId) {
		OrganizationId = organizationId;
	}
	public String getRole() {
		return Role;
	}
	public void setRole(String role) {
		Role = role;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public boolean isSuspended() {
		return Suspended;
	}
	public void setSuspended(boolean suspended) {
		Suspended = suspended;
	}
	
}
