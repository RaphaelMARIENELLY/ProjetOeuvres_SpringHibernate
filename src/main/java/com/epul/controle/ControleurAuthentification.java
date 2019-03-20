package com.epul.controle;

import com.epul.dao.ServiceAdherentDAO;
import com.epul.meserreurs.MonException;
import com.epul.metier.UtilisateurEntity;
import com.epul.utilitaires.FonctionsUtiles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;


@Controller
public class ControleurAuthentification {

    ///
    /// Login
    ////


    @RequestMapping(value = "login.htm", method = RequestMethod.GET)
    public ModelAndView pageLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return new ModelAndView("vues/formLogin");
    }

    ///
    //
    //// Contrôle Login
    ///
    ////
    @RequestMapping(value = "controleLogin.htm")
    public ModelAndView controleLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String destinationPage;
        {
            String login = request.getParameter("login");
            String pwd = request.getParameter("pwd");
            String message ="";
            try {

                ServiceAdherentDAO unServiceAdherentDAO = new ServiceAdherentDAO();
                UtilisateurEntity unUtilisateur = unServiceAdherentDAO.getUtilisateur(login);
                if (unUtilisateur != null) {
                    try {
                        String pwdmd5 = FonctionsUtiles.md5(pwd);
                        if (unUtilisateur.getMotPasse().equals(pwdmd5)) {
                            HttpSession session = request.getSession();
                            session.setAttribute("id", unUtilisateur.getNumUtil());
                            destinationPage = "/index";
                        } else {
                            message = "mot de passe erroné";
                            request.setAttribute("message", message);
                            destinationPage = "/vues/formLogin";
                        }
                    } catch (NoSuchAlgorithmException e) {
                        request.setAttribute("MesErreurs", e.getMessage());
                        destinationPage = "/vues/Erreur";
                    }
                } else {
                    message = "login erroné";
                    request.setAttribute("message", message);
                    destinationPage = "/vues/formLogin";
                }
            } catch (MonException e) {
                request.setAttribute("MesErreurs", e.getMessage());
                destinationPage = "/vues/Erreur";
            }
        }
        return new ModelAndView(destinationPage);
    }

}
