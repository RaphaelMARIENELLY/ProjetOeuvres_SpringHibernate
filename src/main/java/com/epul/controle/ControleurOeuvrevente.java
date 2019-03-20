package com.epul.controle;

import com.epul.dao.ServiceOeuvreventeDAO;
import com.epul.meserreurs.MonException;
import com.epul.metier.OeuvreventeEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ControleurOeuvrevente {

    @RequestMapping(value = "listerOeuvreventes.htm")
    public ModelAndView listerOeuvreventes(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String destinationPage;
        try {
            // HttpSession session = request.getSession();
            ServiceOeuvreventeDAO unServiceOeuvreventeDAO = new ServiceOeuvreventeDAO();
            request.setAttribute("mesOeuvreventes", unServiceOeuvreventeDAO.consulterListeOeuvreventes());
            destinationPage = "vues/listerOeuvrevente";
        } catch (MonException e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "vues/Erreur";

        }
        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "insererOeuvrevente.htm")
    public ModelAndView insererOeuvrevente(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String destinationPage = "";
        try {
            OeuvreventeEntity uneOv = new OeuvreventeEntity();
            uneOv.setTitreOeuvrevente(request.getParameter("txttitre"));
            uneOv.setPrixOeuvrevente(Integer.parseInt(request.getParameter("txtprix")));
            ServiceOeuvreventeDAO unServiceOeuvreventeDAO = new ServiceOeuvreventeDAO();
            unServiceOeuvreventeDAO.insertOeuvrevente(uneOv);
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "vues/Erreur";
        }
        destinationPage = "index";
        return new ModelAndView(destinationPage);
    }
    @RequestMapping(value = "ajouterOeuvrevente.htm")
    public ModelAndView ajouterOeuvrevente(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String destinationPage = "";
        try {
            destinationPage = "vues/ajouterOeuvrevente";
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";
        }
        return new ModelAndView(destinationPage);
    }
}
