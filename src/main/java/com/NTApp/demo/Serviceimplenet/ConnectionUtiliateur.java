package com.NTApp.demo.Serviceimplenet;

import com.NTApp.demo.Repository.UtilisateurRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ConnectionUtiliateur implements UserDetailsService {
    private UtilisateurRepository utilisateurRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return  this.utilisateurRepository
                .findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("Aucun utilisateur exister su ses coordonne!"));
    }
}
