package org.techlab.labxpert.service.serviceImp;


import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.techlab.labxpert.dtos.UtilisateurDTO;
import org.techlab.labxpert.entity.Utilisateur;
import org.techlab.labxpert.repository.UtilisateurRepository;
import org.techlab.labxpert.service.I_Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@ComponentScan(basePackages = "com.labtech.labxpert")
public class UtilisateurServiceImp implements I_Utilisateur {

    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder ;
    @Override
    public UtilisateurDTO addUser(UtilisateurDTO userdto) {
        String encodepassword = passwordEncoder.encode(userdto.getPassword());
        userdto.setPassword(encodepassword);
        Utilisateur user=utilisateurRepository.save(modelMapper.map(userdto,Utilisateur.class));
        user.setPassword("********");
        return modelMapper.map(user,UtilisateurDTO.class);
    }
    @Override
    public List<UtilisateurDTO> showUsers() {
        List<Utilisateur> users=utilisateurRepository.findByDeletedFalse();
        return users.stream().map(user->{
            UtilisateurDTO utilisateurDTO=modelMapper.map(user,UtilisateurDTO.class);
            utilisateurDTO.setPassword("**********");
            return utilisateurDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public UtilisateurDTO showUserwithid(Long id) {
        Utilisateur utilisateur =utilisateurRepository.findById(id).get();
        utilisateur.setPassword("********");
        return modelMapper.map(utilisateur,UtilisateurDTO.class);
    }

    @Override
    public UtilisateurDTO authentification(String username, String password) {

        Utilisateur user=utilisateurRepository.findUserByUsernameAndPassword(username,password);
        user.setPassword("********");
        return modelMapper.map(user,UtilisateurDTO.class);
    }

    @Override
    public UtilisateurDTO modUser(UtilisateurDTO userdto) {
        Utilisateur user=utilisateurRepository.save(modelMapper.map(userdto,Utilisateur.class));
        user.setPassword("********");
        return modelMapper.map(user,UtilisateurDTO.class);
    }

    @Override
    public boolean delUser(UtilisateurDTO userdto) {
        userdto.setDeleted(true);
        Utilisateur user=utilisateurRepository.save(modelMapper.map(userdto,Utilisateur.class));
        return user.isDeleted();
    }

    public Utilisateur loadUserByEmail(String email) {
        return utilisateurRepository.findByNomUtilisateur(email);
    }


}
