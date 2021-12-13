package springboot_kurs_controller_service_repository_basic_authentication;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.google.common.collect.Sets;
import static springboot_kurs_controller_service_repository_basic_authentication.ApplicationUserPermissions.*;

public enum ApplicationUserRoles {
	
	STUDENT(Sets.newHashSet(STUDENT_READ)), ADMIN(Sets.newHashSet(STUDENT_READ, STUDENT_WRITE));
	
	private final Set<ApplicationUserPermissions> permissions;

	private ApplicationUserRoles(Set<ApplicationUserPermissions> permissions) {
		this.permissions = permissions;
	}

	public Set<ApplicationUserPermissions> getPermissions() {
		return permissions;
	}
	
	public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
		
		Set<SimpleGrantedAuthority> permissions = getPermissions().
															stream().
															map(permission -> new SimpleGrantedAuthority(permission.getPermission())).
															collect(Collectors.toSet());
		
		permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
		
		return permissions;
	}

}
