package org.techlab.labxpert.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.techlab.labxpert.entity.Utilisateur;
import org.techlab.labxpert.service.serviceImp.UtilisateurServiceImp;

import java.util.Collections;

@Service
public class UserDetailsService {
    @Autowired
    private UtilisateurServiceImp utilisateurServiceImp ;

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Utilisateur user = utilisateurServiceImp.loadUserByEmail(email);
        System.out.println(user);
        if (user == null) throw new UsernameNotFoundException("User Not Found");
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(user.getRole().name());
        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().name());
        return new User(user.getNomUtilisateur(),user.getPassword(), Collections.singleton(authority));

    }
}
