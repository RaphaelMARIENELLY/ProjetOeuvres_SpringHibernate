package com.epul.dao;

import com.epul.meserreurs.MonException;
import com.epul.metier.AdherentEntity;
import com.epul.metier.OeuvreventeEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;
import java.util.List;

public class ServiceOeuvreDAO {
    /* Lister les oeuvres
     * */
    public List<OeuvreventeEntity> consulterListeOeuvres() throws MonException {
        List<OeuvreventeEntity> mesOeuvres = null;
        String marequete = "SELECT a FROM OeuvreventeEntity a ORDER BY a.titreOeuvrevente";
        try {

            Session session = ServiceHibernate.currentSession();
            TypedQuery<OeuvreventeEntity> query = session.createQuery(marequete);
            mesOeuvres = query.getResultList();
            session.close();

        } catch (HibernateException ex) {
            throw new MonException("Impossible d'accèder à la SessionFactory: ",  ex.getMessage());
        }
        return mesOeuvres;

    }

    /* Consultation d'une oeuvre par son numéro
     */
    public OeuvreventeEntity oeuvreById(int numero) throws MonException {
        List<OeuvreventeEntity> mesOeuvres = null;
        OeuvreventeEntity oeuvre = new OeuvreventeEntity();
        String marequete ="SELECT o FROM OeuvreventeEntity o WHERE o.idOeuvrevente="+numero;
        try {
            Session session = ServiceHibernate.currentSession();

            TypedQuery query =   session.createQuery(marequete);
            mesOeuvres =  query.getResultList();
            oeuvre = mesOeuvres.get(0);
            session.close();
        }
        catch (HibernateException ex) {
            throw new MonException("Impossible d'accèder à la SessionFactory: ",  ex.getMessage());
        }

        return oeuvre;
    }


    //  ***************************************
    //  On ajoute un adhérent à la base
    //  ***************************************
    public void insertOeuvre(OeuvreventeEntity uneOv) throws MonException
    {
        Transaction tx = null;
        try {
            Session   session = ServiceHibernate.currentSession();
            tx = session.beginTransaction();
            // on transfère la nouvelle oeuvre à la base
            session.save(uneOv);
            tx.commit();
            session.close();
        }
        catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
        }
        catch (Exception e) {
            throw new MonException("Impossible d'accèder à la SessionFactory: ",  e.getMessage());
        }
    }

    //  ***************************************
    //  On supprime une oeuvre la base
    //  ***************************************
    public void deleteAdherent(OeuvreventeEntity uneOv) throws MonException
    {
        Transaction tx = null;
        try {
            Session   session = ServiceHibernate.currentSession();
            tx = session.beginTransaction();
            // on supprime l'oeuvre de la base
            session.delete(uneOv);
            tx.commit();
            session.close();
        }
        catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
        }
        catch (Exception e) {
            throw new MonException("Impossible d'accèder à la SessionFactory: ",  e.getMessage());
        }
    }

    //  ***************************************
    //  On modifie une oeuvre la base
    //  ***************************************
    public void updateOeuvre(OeuvreventeEntity uneOv) throws MonException
    {
        Transaction tx = null;
        try {
            Session   session = ServiceHibernate.currentSession();
            tx = session.beginTransaction();
            // on transfère la nouvelle oeuvre à la base
            session.merge(uneOv);
            tx.commit();
            session.close();
        }
        catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
        }
        catch (Exception e) {
            throw new MonException("Impossible d'accèder à la SessionFactory: ",  e.getMessage());
        }
    }
}
