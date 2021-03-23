package es.urjc.etsii.co.clickandbuyweb.authenticationProvider;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import es.urjc.etsii.co.clickandbuyweb.dao.AdminDAO;
import es.urjc.etsii.co.clickandbuyweb.dao.UserDAO;
import es.urjc.etsii.co.clickandbuyweb.models.Admin;
import es.urjc.etsii.co.clickandbuyweb.models.User;

@Component
public class UserRepositoryAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	private UserDAO udao;
	@Autowired
	private AdminDAO admindao;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		Admin admin = admindao.findByEmail(authentication.getName());
		User u=udao.findByUserEmail(authentication.getName());
		String password = (String) authentication.getCredentials();
		
		if(admin!=null && new BCryptPasswordEncoder().matches(password, admin.getPassword())) {
			List<GrantedAuthority> roles = new ArrayList<>();
			for (String role : admin.getRoles()) {
				System.out.println(role);
				roles.add(new SimpleGrantedAuthority(role));
			}
			return new UsernamePasswordAuthenticationToken(admin.getEmail(), password, roles);
		}
		if (u==null) {
			 throw new BadCredentialsException("No user present with this email");
		}

		if (!new BCryptPasswordEncoder().matches(password, u.getPassword())) {
			throw new BadCredentialsException("Bad password");
		}
		List<GrantedAuthority> roles = new ArrayList<>();
		for (String role : u.getRoles()) {
			roles.add(new SimpleGrantedAuthority(role));
		}
		return new UsernamePasswordAuthenticationToken(u.getEmail(), password, roles);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return true;
	}

}
