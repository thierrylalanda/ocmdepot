/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Services;

import com.eh2s.ocm.Entity.Tclients;
import com.eh2s.ocm.Entity.Tlignecommande;
import com.eh2s.ocm.Entity.Tusers;
import java.util.List;
import java.util.Set;
import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author messi
 */
@Local
public interface ServicesInitialize {

    void initTiketUser(HttpServletRequest request, int iduser);

    void initTiketByRegionSociete(HttpServletRequest request, int iduser);

    void initTiketBySecteurSociete(HttpServletRequest request, int iduser, Set<Integer> secteur);

    void initTiketByDistrictSociete(HttpServletRequest request, int iduser, Set<Integer> district);

    void initTiketSociete(HttpServletRequest request, int iduser);

    void initCommandeByDistrict(HttpServletRequest request, Set<Integer> district, String societe);

    void initCommandeBySecteur(HttpServletRequest request, Set<Integer> secteur, String societe);

    void initCommandeByRegion(HttpServletRequest request, int iduser);

    void initCommandeSociete(HttpServletRequest request, String societe);

    List<Tlignecommande> getCommandeClients(int client);

    void initiationEntityAdmin(HttpServletRequest request, Tusers utilisateur);

    void initiationSuperAdmin(HttpServletRequest request);

    void initialize(HttpServletRequest request, Tusers utilisateur);

    void initiationClients(HttpServletRequest request, Tclients utilisateur);

    List<Tlignecommande> getCommandesByZoneAutorize(Tusers utilisateur);

    void initiationEntityAdminLocal(HttpServletRequest request, Tusers utilisateur);
}
