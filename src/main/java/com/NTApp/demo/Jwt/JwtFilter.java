package com.NTApp.demo.Jwt;


import com.NTApp.demo.Models.Jwt;
import com.NTApp.demo.Serviceimplenet.ConnectionUtiliateur;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@AllArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
private JwtService jwtService;
private ConnectionUtiliateur connectionUtiliateur;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token  = null;
        Jwt tokenDB  = null;
        String nomutilisateur=null;
        boolean istokenexpire = true;

              try {
                  if (request.getServletPath().matches("/connection")){
                     filterChain.doFilter(request,response);
                  }else {
                      final  String requetheader = request.getHeader("Authorization");

                      if (requetheader != null && requetheader.startsWith("Bearer ")){

                          token = requetheader.substring(7);
                          tokenDB = this.jwtService.tokenByValeur(token);
                          nomutilisateur = jwtService.extraUsername(token);
                          istokenexpire = jwtService.istokenExpire(token);


                          if (!istokenexpire && tokenDB.getUtilisateurs().getEmail().equals(nomutilisateur) && SecurityContextHolder.getContext().getAuthentication() ==null){

                              UserDetails userDetails = connectionUtiliateur.loadUserByUsername(nomutilisateur);

                              if (jwtService.ValidationToken(token,userDetails)){

                                  UsernamePasswordAuthenticationToken authenticationFilter = new UsernamePasswordAuthenticationToken(token,null,userDetails.getAuthorities());
                                  authenticationFilter.setDetails(
                                          new WebAuthenticationDetailsSource().buildDetails(request)
                                  );
                                  SecurityContextHolder.getContext().setAuthentication(authenticationFilter);
                              }
                          }
                      }
                      filterChain.doFilter(request,response);
                  }


              }catch (Exception e){
                  e.printStackTrace();
              }
    }
}
