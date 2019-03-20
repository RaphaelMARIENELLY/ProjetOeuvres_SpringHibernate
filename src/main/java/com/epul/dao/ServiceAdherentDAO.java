package com.epul.dao;


import java.util.*;

import com.epul.metier.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import com.epul.meserreurs.*;
import org.hibernate.Transaction;

import javax.persistence.Query;
import javax.persistence.TypedQuery;


public class ServiceAdherentDAO {

	/* Lister les adherents
	 * */
	public List<AdherentEntity> consulterListeAdherents() throws MonException {
		List<AdherentEntity> mesAdherents = null;
		String marequete = "SELECT a FROM AdherentEntity a ORDER BY a.nomAdherent";
		try {

			Session session = ServiceHibernate.currentSession();
			TypedQuery<AdherentEntity> query = session.createQuery(marequete);
			 mesAdherents = query.getResultList();
			session.close();

		} catch (HibernateException ex) {
			throw new MonException("Impossible d'accèder à la SessionFactory: ",  ex.getMessage());
		}
			return mesAdherents;

	}

	/* Consultation d'une adherent par son numéro
	*/
	public AdherentEntity adherentById(int numero) throws MonException {
		List<AdherentEntity> mesAdherents = null;
		AdherentEntity adherent = new AdherentEntity();
		String marequete ="SELECT a FROM AdherentEntity a WHERE a.idAdherent="+numero;
		try {
			Session session = ServiceHibernate.currentSession();

			TypedQuery query =   session.createQuery(marequete);
			mesAdherents =  query.getResultList();
			adherent = mesAdherents.get(0);
			session.close();
		}
	 catch (HibernateException ex) {
		throw new MonException("Impossible d'accèder à la SessionFactory: ",  ex.getMessage());
	}

		return adherent;
	}


	//  ***************************************
	//  On ajoute un adhérent à la base
	//  ***************************************
	public void insertAdherent(AdherentEntity unAdh) throws MonException
	{
		Transaction tx = null;
		try {
			Session   session = ServiceHibernate.currentSession();
			tx = session.beginTransaction();
			// on transfère le nouvel adhérent à la base
			session.save(unAdh);
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
	//  On supprime un adhérent la base
	//  ***************************************
	public void deleteAdherent(AdherentEntity unAdh) throws MonException
	{
		Transaction tx = null;
		try {
			Session   session = ServiceHibernate.currentSession();
			tx = session.beginTransaction();
			// on transfère le nouvel adhérent à la base
			session.delete(unAdh);
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
	//  On modifie un adhérent la base
	//  ***************************************
	public void updateAdherent(AdherentEntity unAdh) throws MonException
	{
		Transaction tx = null;
		try {
			Session   session = ServiceHibernate.currentSession();
			tx = session.beginTransaction();
			// on transfère le nouvel adhérent à la base
			session.merge(unAdh);
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
	//  Authentification
	//  ***************************************

	public UtilisateurEntity getUtilisateur( String login) throws MonException
	{
		UtilisateurEntity unUtilisateur=null;
		try {
			Session session = ServiceHibernate.currentSession();

			TypedQuery query =   session.createNamedQuery("UtilisateurEntity.rechercheNom");
			query.setParameter("name", login);
			unUtilisateur = (UtilisateurEntity) query.getSingleResult();
			if (unUtilisateur == null) {
				new MonException("Utilisateur Inconnu", "Erreur ");
			}
			session.close();

		}
		catch(RuntimeException e)
		{
			new MonException("Erreur de lecture", e.getMessage());
		} catch (Exception e){
			new MonException("Erreur de lecture", e.getMessage());
		}
		return unUtilisateur;
	}

}
