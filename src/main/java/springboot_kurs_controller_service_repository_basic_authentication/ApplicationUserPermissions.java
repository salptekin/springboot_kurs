package springboot_kurs_controller_service_repository_basic_authentication;

public enum ApplicationUserPermissions {
	
	STUDENT_READ("student:read"), STUDENT_WRITE("student:write");
	
	private final String permission;

	ApplicationUserPermissions(String permission) {
		this.permission = permission;
	}

	public String getPermission() {
		return permission;
	}
}
