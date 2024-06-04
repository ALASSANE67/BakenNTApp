package com.NTApp.demo.Serviceimplenet;

import com.NTApp.demo.DTO.UtilisateurDto;
import com.NTApp.demo.Environement.Environnement;
import com.NTApp.demo.Models.Utilisateurs;
import com.NTApp.demo.Repository.UtilisateurRepository;
import com.NTApp.demo.Service.UtilisateurService;
import com.NTApp.demo.Utils.UtilisClasse;
import com.NTApp.demo.Validation.UtilisateurValidateur;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UtilisateurServiceImpl implements UtilisateurService {
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public ResponseEntity<String> creerutilisateur(UtilisateurDto utilisateurs) {
    if (!utilisateurs.getEmail().contains("@"))throw new RuntimeException("votre mail est invalide");
    if (UtilisateurValidateur.UtilisateurValidateur(utilisateurs)){
        Optional<Utilisateurs> utilisateurModel  = utilisateurRepository.findByEmail(utilisateurs.getEmail());
        if(utilisateurModel.isPresent()){
            throw new RuntimeException("email est deja utilisé");
        }
         String motdepasse = passwordEncoder.encode(utilisateurs.getMotdepasse());
         utilisateurs.setMotdepasse(motdepasse);
       Utilisateurs utilisateurs1 = modelMapper.map(utilisateurs, Utilisateurs.class);
       utilisateurRepository.save(utilisateurs1);
        return UtilisClasse.getResponseEntity(Environnement.UTILISATEUR_CREE, HttpStatus.CREATED);
    }
        return UtilisClasse.getResponseEntity(Environnement.UTILISATEUR_ERREUR, HttpStatus.BAD_REQUEST);
    }
}
