package com.fullerton.olp.settings.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fullerton.olp.model.account.Account;
import com.fullerton.olp.repository.account.AccountRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
    private AccountRepository userRepository;

    @Autowired
    public CustomUserDetailsService(AccountRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Account account = userRepository.findByUsernameOrEmail(username);

        if (account == null) {
            // Not found...
            throw new UsernameNotFoundException(
                    "User " + username + " not found.");
        }

        if (account.getRole() == null ) {
            // No Roles assigned to user...
            throw new UsernameNotFoundException("User not authorized.");
        }


        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        grantedAuthorities.add(new SimpleGrantedAuthority(account.getRole().getName()));

        User userDetails = new User(account.getEmail(),
                account.getPassword(), account.getActive(),
                !/*account.isExpired()*/false, !/*account.isExpired()*/false,
                !/*account.isLocked()*/false, grantedAuthorities);

        return userDetails;
    }

}
