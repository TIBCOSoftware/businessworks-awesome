package tcizendesk.module.common.vos;

import java.util.Date;

public class TicketVo {
	
		
	    private long Id=0l;
	    private long AssigneeId=0l;
	    private int GroupId=0;
	    private boolean HasIncidents=false;
	    private long BrandId=0l;	    
	    private String Url="";	    	    
	    public String getPriority() {
			return Priority;
		}
		public void setPriority(String priority) {
			Priority = priority;
		}
		private String subject="";
	    private String description="";
		private String status="";
		private long RequesterId=0l;
		private long OrganizationId=0l;
		private Date CreatedAt;
		private Date UpdatedAt;
		private Date DueAt; 
		private String Priority="";
		private String Type="";
		public String getType() {
			return Type;
		}
		public void setType(String type) {
			Type = type;
		}
		public Date getDueAt() {
			return DueAt;
		}
		public void setDueAt(Date dueAt) {
			DueAt = dueAt;
		}
		public long getId() {
			return Id;
		}
		public void setId(long id) {
			Id = id;
		}
		public long getAssigneeId() {
			return AssigneeId;
		}
		public void setAssigneeId(long assigneeId) {
			AssigneeId = assigneeId;
		}
		public int getGroupId() {
			return GroupId;
		}
		public void setGroupId(int groupId) {
			GroupId = groupId;
		}
		public boolean isHasIncidents() {
			return HasIncidents;
		}
		public void setHasIncidents(boolean hasIncidents) {
			HasIncidents = hasIncidents;
		}
		public long getBrandId() {
			return BrandId;
		}
		public void setBrandId(long brandId) {
			BrandId = brandId;
		}
		public String getUrl() {
			return Url;
		}
		public void setUrl(String url) {
			Url = url;
		}
		public String getSubject() {
			return subject;
		}
		public void setSubject(String subject) {
			this.subject = subject;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public long getRequesterId() {
			return RequesterId;
		}
		public void setRequesterId(long requesterId) {
			RequesterId = requesterId;
		}
		public long getOrganizationId() {
			return OrganizationId;
		}
		public void setOrganizationId(long organizationId) {
			OrganizationId = organizationId;
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
		
		
}
