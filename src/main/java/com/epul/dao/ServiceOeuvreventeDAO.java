package com.epul.dao;

import com.epul.meserreurs.MonException;
import com.epul.metier.OeuvreventeEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;
import java.util.List;

public class ServiceOeuvreventeDAO {
    /* Lister les oeuvres (ventes)
     * */
    public List<OeuvreventeEntity> consulterListeOeuvreventes() throws MonException {
        List<OeuvreventeEntity> mesOeuvreventes = null;
        String marequete = "SELECT ov FROM OeuvreventeEntity ov ORDER BY ov.idProprietaire";
        try {

            Session session = ServiceHibernate.currentSession();
            TypedQuery<OeuvreventeEntity> query = session.createQuery(marequete);
            mesOeuvreventes = query.getResultList();
            session.close();

        } catch (HibernateException ex) {
            throw new MonException("Impossible d'accèder à la SessionFactory: ",  ex.getMessage());
        }
        return mesOeuvreventes;
    }

    /* Consultation d'une oeuvrevente par son numéro
     */
    public OeuvreventeEntity oeuvreventeById(int numero) throws MonException {
        List<OeuvreventeEntity> mesOeuvreventes = null;
        OeuvreventeEntity uneOeuvrevente = new OeuvreventeEntity();
        String marequete ="SELECT ov FROM OeuvreventeEntity ov WHERE ov.idOeuvrevente="+numero;
        try {
            Session session = ServiceHibernate.currentSession();
            TypedQuery query =   session.createQuery(marequete);
            mesOeuvreventes =  query.getResultList();
            uneOeuvrevente = mesOeuvreventes.get(0);
            session.close();
        }
        catch (HibernateException ex) {
            throw new MonException("Impossible d'accèder à la SessionFactory: ",  ex.getMessage());
        }
        return uneOeuvrevente;
    }

    //  ***************************************
    //  On ajoute une oeuvrevente à la base
    //  ***************************************
    public void insertOeuvrevente(OeuvreventeEntity uneOv) throws MonException
    {
        Transaction tx = null;
        try {
            Session   session = ServiceHibernate.currentSession();
            tx = session.beginTransaction();
            // on transfère la nouvelle oeuvrevente à la base
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
    //  On supprime une oeuvrevente la base
    //  ***************************************
    public void deleteOeuvrevente(OeuvreventeEntity uneOv) throws MonException
    {
        Transaction tx = null;
        try {
            Session   session = ServiceHibernate.currentSession();
            tx = session.beginTransaction();
            // on supprime l'oeuvrevente de la base
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
    //  On modifie une oeuvrevente en base
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
