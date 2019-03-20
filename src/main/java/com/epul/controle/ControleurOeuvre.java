package com.epul.controle;

import com.epul.dao.ServiceAdherentDAO;
import com.epul.dao.ServiceOeuvreDAO;
import com.epul.meserreurs.MonException;
import com.epul.metier.AdherentEntity;
import com.epul.metier.OeuvreventeEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ControleurOeuvre {

    @RequestMapping(value = "listerOeuvres.htm")
    public ModelAndView listerOeuvres(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String destinationPage;
        try {
            // HttpSession session = request.getSession();
            ServiceOeuvreDAO unServiceOeuvreDAO = new ServiceOeuvreDAO();
            request.setAttribute("mesOeuvres", unServiceOeuvreDAO.consulterListeOeuvres());
            destinationPage = "vues/listerOeuvre";
        } catch (MonException e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "vues/Erreur";

        }
        return new ModelAndView(destinationPage);
    }


    @RequestMapping(value = "insererOeuvre.htm")
    public ModelAndView insererOeuvre(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String destinationPage = "";
        try {
            OeuvreventeEntity uneOv = new OeuvreventeEntity();
            uneOv.setTitreOeuvrevente(request.getParameter("txttitre"));
            uneOv.setPrixOeuvrevente(Integer.parseInt(request.getParameter("txtprix")));
            ServiceOeuvreDAO unServiceOeuvreDAO = new ServiceOeuvreDAO();
            unServiceOeuvreDAO.insertOeuvre(uneOv);
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "vues/Erreur";
        }
        destinationPage = "index";
        return new ModelAndView(destinationPage);
    }
    @RequestMapping(value = "ajouterOeuvre.htm")
    public ModelAndView ajouterOeuvre(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String destinationPage = "";
        try {
            destinationPage = "vues/ajouterOeuvre";
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";
        }

        return new ModelAndView(destinationPage);
    }

}
