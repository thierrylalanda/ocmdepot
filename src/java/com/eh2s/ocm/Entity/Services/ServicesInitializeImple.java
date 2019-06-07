/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Services;

import com.eh2s.ocm.Entity.AffectTournerUser;
import com.eh2s.ocm.Entity.Sessions.EmballageFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.FournisseurFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.MagasinFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.SocieteFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.SourceMouvementCaisseFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.SouscriptionLicenceFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.StatutIncidentFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TactionsFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TaffectzoneFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TarticlesFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TcategorieFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TclientsFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TdistrictsFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TetatcFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TgroupsFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TincidentsFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TlignecommandeFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TmenuFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TournerFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TpriorityFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TregionsFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TrubriquesFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TsecteursFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TservicesFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TsitesFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TsmenuFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TsourcesFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TsrubriquesFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TtypeclientsFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TusersFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TypeLicenceFacadeLocal;
import com.eh2s.ocm.Entity.SourceMouvementCaisse;
import com.eh2s.ocm.Entity.Taffectzone;
import com.eh2s.ocm.Entity.Tclients;
import com.eh2s.ocm.Entity.Tdistricts;
import com.eh2s.ocm.Entity.Tgroups;
import com.eh2s.ocm.Entity.TgroupsAssoc;
import com.eh2s.ocm.Entity.Tincidents;
import com.eh2s.ocm.Entity.Tlignecommande;
import com.eh2s.ocm.Entity.Tourner;
import com.eh2s.ocm.Entity.Tregions;
import com.eh2s.ocm.Entity.Tsecteurs;
import com.eh2s.ocm.Entity.Tusers;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import org.jboss.weld.util.collections.ArraySet;

/**
 *
 * @author messi
 */
@Stateless
public class ServicesInitializeImple implements ServicesInitialize {

    @EJB
    private SourceMouvementCaisseFacadeLocal sourceMouvementCaisseFacade;

    @EJB
    private FournisseurFacadeLocal fournisseurFacade;

    @EJB
    private EmballageFacadeLocal emballageFacade;

    @EJB
    private MagasinFacadeLocal magasinFacade;

    @EJB
    private TournerFacadeLocal tournerFacade;

    @EJB
    private TlignecommandeFacadeLocal tlignecommandeFacade;

    @EJB
    private TaffectzoneFacadeLocal taffectzoneFacade;

    @EJB
    private TcategorieFacadeLocal tcategorieFacade;

    @EJB
    private TetatcFacadeLocal tetatcFacade;

    @EJB
    private TarticlesFacadeLocal tarticlesFacade;

    @EJB
    private SouscriptionLicenceFacadeLocal souscriptionLicenceFacade;

    @EJB
    private TypeLicenceFacadeLocal typeLicenceFacade;

    @EJB
    private TincidentsFacadeLocal tincidentsFacade;

    @EJB
    private TsmenuFacadeLocal tsmenuFacade;

    @EJB
    private TmenuFacadeLocal tmenuFacade;

    @EJB
    private StatutIncidentFacadeLocal statutIncidentFacade;

    @EJB
    private TclientsFacadeLocal tclientsFacade;

    @EJB
    private TtypeclientsFacadeLocal ttypeclientsFacade;

    @EJB
    private TsrubriquesFacadeLocal tsrubriquesFacade;

    @EJB
    private TrubriquesFacadeLocal trubriquesFacade;

    @EJB
    private TpriorityFacadeLocal tpriorityFacade;

    @EJB
    private TsourcesFacadeLocal tsourcesFacade;

    @EJB
    private TusersFacadeLocal tusersFacade;

    @EJB
    private TactionsFacadeLocal tactionsFacade;

    @EJB
    private TgroupsFacadeLocal tgroupsFacade;

    @EJB
    private TsecteursFacadeLocal tsecteursFacade;

    @EJB
    private TdistrictsFacadeLocal tdistrictsFacade;

    @EJB
    private TservicesFacadeLocal tservicesFacade;

    @EJB
    private TsitesFacadeLocal tsitesFacade;

    @EJB
    private TregionsFacadeLocal tregionsFacade;

    @EJB
    private SocieteFacadeLocal societeFacade;

    @Override
    public void initTiketUser(HttpServletRequest request, int iduser) {
        List<Tincidents> l1;
        Tusers u = tusersFacade.findTusers(iduser);
        List<Tincidents> l2 = new ArrayList<>();
        u.getUserplainteList().stream().forEach((u1) -> {
            l2.add(u1.getIdplainte());
        });
        l1 = l2.stream().filter(in -> in.getState().getCode() == 501).collect(Collectors.toList());
        request.setAttribute("newticketcli", l1);
        l1 = l2.stream().filter(in -> in.getState().getCode() == 200).collect(Collectors.toList());
        request.setAttribute("ticketresolutcli", l1);
        l1 = l2.stream().filter(in -> in.getState().getCode() == 401).collect(Collectors.toList());
        request.setAttribute("ticketInsolvablecli", l1);
        l1 = l2.stream().filter(in -> in.getState().getCode() == 404).collect(Collectors.toList());
        request.setAttribute("ticketNonResolutcli", l1);
        request.setAttribute("totalticketcli", l2);
    }

    @Override
    public void initTiketByRegionSociete(HttpServletRequest request, int iduser) {
        Tusers user = tusersFacade.find(iduser);
        List<Tincidents> l = tincidentsFacade.findAll(user.getSociete().getImmatriculation());
        List<Tincidents> l1;
        l1 = l.stream().filter(in -> in.getIsaffect() == 0 && in.getClientid().getSecteurid().getDistrictid().getRegionid().equals(user.getServiceid().getSite().getRegionid()))
                .collect(Collectors.toList());
        request.setAttribute("newticket", l1);
        l1 = l.stream().filter(in -> in.getState().getCode() == 200 && in.getClientid().getSecteurid().getDistrictid().getRegionid().equals(user.getServiceid().getSite().getRegionid()))
                .collect(Collectors.toList());
        request.setAttribute("ticketresolut", l1);
        l1 = l.stream().filter(in -> in.getState().getCode() == 401 && in.getClientid().getSecteurid().getDistrictid().getRegionid().equals(user.getServiceid().getSite().getRegionid()))
                .collect(Collectors.toList());
        request.setAttribute("ticketInsolvable", l1);
        l1 = l.stream().filter(in -> in.getState().getCode() == 404 && in.getClientid().getSecteurid().getDistrictid().getRegionid().equals(user.getServiceid().getSite().getRegionid()))
                .collect(Collectors.toList());
        request.setAttribute("ticketNonResolut", l1);
        l1 = l.stream().filter(in -> in.getIsaffect() != 0 && in.getState().getCode() == 501 && in.getClientid().getSecteurid().getDistrictid().getRegionid().equals(user.getServiceid().getSite().getRegionid()))
                .collect(Collectors.toList());
        request.setAttribute("ticketEncours", l1);
        l1 = l.stream().filter(in -> in.getState().getCode() == 502 && in.getClientid().getSecteurid().getDistrictid().getRegionid().equals(user.getServiceid().getSite().getRegionid()))
                .collect(Collectors.toList());
        request.setAttribute("ticketFermeture", l1);
        l1 = l.stream().filter(in -> in.getClientid().getSecteurid().getDistrictid().getRegionid().equals(user.getServiceid().getSite().getRegionid()))
                .collect(Collectors.toList());
        request.setAttribute("totalticket", l1);
    }

    @Override
    public void initTiketBySecteurSociete(HttpServletRequest request, int iduser, Set<Integer> secteur) {
        Tusers user = tusersFacade.find(iduser);
        List<Tincidents> l = tincidentsFacade.findAll(user.getSociete().getImmatriculation());
        List<Tincidents> l1;
        l1 = l.stream().filter(in -> in.getIsaffect() == 0 && secteur.contains(in.getClientid().getSecteurid().getId()))
                .collect(Collectors.toList());
        request.setAttribute("newticket", l1);
        l1 = l.stream().filter(in -> in.getState().getCode() == 200 && secteur.contains(in.getClientid().getSecteurid().getId()))
                .collect(Collectors.toList());
        request.setAttribute("ticketresolut", l1);
        l1 = l.stream().filter(in -> in.getState().getCode() == 401 && secteur.contains(in.getClientid().getSecteurid().getId()))
                .collect(Collectors.toList());
        request.setAttribute("ticketInsolvable", l1);
        l1 = l.stream().filter(in -> in.getState().getCode() == 404 && secteur.contains(in.getClientid().getSecteurid().getId()))
                .collect(Collectors.toList());
        request.setAttribute("ticketNonResolut", l1);
        l1 = l.stream().filter(in -> in.getIsaffect() != 0 && in.getState().getCode() == 501 && secteur.contains(in.getClientid().getSecteurid().getId()))
                .collect(Collectors.toList());
        request.setAttribute("ticketEncours", l1);
        l1 = l.stream().filter(in -> in.getState().getCode() == 502 && secteur.contains(in.getClientid().getSecteurid().getId()))
                .collect(Collectors.toList());
        request.setAttribute("ticketFermeture", l1);
        l1 = l.stream().filter(in -> secteur.contains(in.getClientid().getSecteurid().getId()))
                .collect(Collectors.toList());
        request.setAttribute("totalticket", l1);
    }

    @Override
    public void initTiketByDistrictSociete(HttpServletRequest request, int iduser, Set<Integer> district) {
        Tusers user = tusersFacade.find(iduser);
        List<Tincidents> l = tincidentsFacade.findAll(user.getSociete().getImmatriculation());
        List<Tincidents> l1;
        l1 = l.stream().filter(in -> in.getIsaffect() == 0 && district.contains(in.getClientid().getSecteurid().getDistrictid().getId()))
                .collect(Collectors.toList());
        request.setAttribute("newticket", l1);
        l1 = l.stream().filter(in -> in.getState().getCode() == 200 && district.contains(in.getClientid().getSecteurid().getDistrictid().getId()))
                .collect(Collectors.toList());
        request.setAttribute("ticketresolut", l1);
        l1 = l.stream().filter(in -> in.getState().getCode() == 401 && district.contains(in.getClientid().getSecteurid().getDistrictid().getId()))
                .collect(Collectors.toList());
        request.setAttribute("ticketInsolvable", l1);
        l1 = l.stream().filter(in -> in.getState().getCode() == 404 && district.contains(in.getClientid().getSecteurid().getDistrictid().getId()))
                .collect(Collectors.toList());
        request.setAttribute("ticketNonResolut", l1);
        l1 = l.stream().filter(in -> in.getIsaffect() != 0 && in.getState().getCode() == 501 && district.contains(in.getClientid().getSecteurid().getDistrictid().getId()))
                .collect(Collectors.toList());
        request.setAttribute("ticketEncours", l1);
        l1 = l.stream().filter(in -> in.getState().getCode() == 502 && district.contains(in.getClientid().getSecteurid().getDistrictid().getId()))
                .collect(Collectors.toList());
        request.setAttribute("ticketFermeture", l1);
        l1 = l.stream().filter(in -> district.contains(in.getClientid().getSecteurid().getDistrictid().getId()))
                .collect(Collectors.toList());
        request.setAttribute("totalticket", l1);
    }

    @Override
    public void initTiketSociete(HttpServletRequest request, int iduser) {
        Tusers user = tusersFacade.find(iduser);
        List<Tincidents> l = tincidentsFacade.findAll(user.getSociete().getImmatriculation());
        List<Tincidents> l1;
        l1 = l.stream().filter(in -> in.getIsaffect() == 0).collect(Collectors.toList());
        request.setAttribute("newticket", l1);
        l1 = l.stream().filter(in -> in.getState().getCode() == 200).collect(Collectors.toList());
        request.setAttribute("ticketresolut", l1);
        l1 = l.stream().filter(in -> in.getState().getCode() == 401).collect(Collectors.toList());
        request.setAttribute("ticketInsolvable", l1);
        l1 = l.stream().filter(in -> in.getState().getCode() == 404).collect(Collectors.toList());
        request.setAttribute("ticketNonResolut", l1);
        l1 = l.stream().filter(in -> in.getIsaffect() != 0 && in.getState().getCode() == 501).collect(Collectors.toList());
        request.setAttribute("ticketEncours", l1);
        l1 = l.stream().filter(in -> in.getState().getCode() == 502).collect(Collectors.toList());
        request.setAttribute("ticketFermeture", l1);
        request.setAttribute("totalticket", l);
    }

    //initialisation des commandes
    @Override
    public void initCommandeSociete(HttpServletRequest request, String societe) {
        List<Tlignecommande> lv = tlignecommandeFacade.findAll(societe);
        List<Tlignecommande> l1 = new ArrayList<>();
        l1 = lv.stream().filter(in -> in.getEtatc().getCode() == 200).collect(Collectors.toList());
        request.setAttribute("commandesAcheve", l1);
        l1 = lv.stream().filter(in -> in.getEtatc().getCode() == 502).collect(Collectors.toList());
        request.setAttribute("commandesEncours", l1);
        l1 = lv.stream().filter(in -> in.getEtatc().getCode() == 201).collect(Collectors.toList());
        request.setAttribute("commandesLivraison", l1);
        l1 = lv.stream().filter(in -> in.getEtatc().getCode() == 401).collect(Collectors.toList());
        request.setAttribute("commandesValideClient", l1);
        l1 = lv.stream().filter(in -> in.getEtatc().getCode() == 501).collect(Collectors.toList());
        request.setAttribute("commandesNew", l1);
        request.setAttribute("commandesAll", lv);
    }

    @Override
    public void initCommandeByRegion(HttpServletRequest request, int iduser) {
        Tusers user = tusersFacade.find(iduser);
        List<Tlignecommande> ll = tlignecommandeFacade.findAll(user.getServiceid().getSite().getRegionid().getSociete().getImmatriculation());

        List<Tlignecommande> lv = ll.stream().filter(cc -> cc.getClient().getSecteurid().getDistrictid().getRegionid().equals(user.getServiceid().getSite().getRegionid()))
                .collect(Collectors.toList());

        List<Tlignecommande> l1 = lv.stream().filter(in -> in.getEtatc().getCode() == 200).collect(Collectors.toList());
        request.setAttribute("commandesAcheve", l1);
        l1 = lv.stream().filter(in -> in.getEtatc().getCode() == 502).collect(Collectors.toList());
        request.setAttribute("commandesEncours", l1);
        l1 = lv.stream().filter(in -> in.getEtatc().getCode() == 201).collect(Collectors.toList());
        request.setAttribute("commandesLivraison", l1);
        l1 = lv.stream().filter(in -> in.getEtatc().getCode() == 401).collect(Collectors.toList());
        request.setAttribute("commandesValideClient", l1);
        l1 = lv.stream().filter(in -> in.getEtatc().getCode() == 501).collect(Collectors.toList());
        request.setAttribute("commandesNew", l1);
        request.setAttribute("commandesAll", lv);
    }

    @Override
    public void initCommandeBySecteur(HttpServletRequest request, Set<Integer> secteur, String societe) {
        List<Tlignecommande> ll = tlignecommandeFacade.findAll(societe);
        List<Tlignecommande> lv = ll.stream().filter(cc -> secteur.contains(cc.getClient().getSecteurid().getId()))
                .collect(Collectors.toList());

        List<Tlignecommande> l1 = lv.stream().filter(in -> in.getEtatc().getCode() == 200).collect(Collectors.toList());
        request.setAttribute("commandesAcheve", l1);
        l1 = lv.stream().filter(in -> in.getEtatc().getCode() == 502).collect(Collectors.toList());
        request.setAttribute("commandesEncours", l1);
        l1 = lv.stream().filter(in -> in.getEtatc().getCode() == 201).collect(Collectors.toList());
        request.setAttribute("commandesLivraison", l1);
        l1 = lv.stream().filter(in -> in.getEtatc().getCode() == 401).collect(Collectors.toList());
        request.setAttribute("commandesValideClient", l1);
        l1 = lv.stream().filter(in -> in.getEtatc().getCode() == 501).collect(Collectors.toList());
        request.setAttribute("commandesNew", l1);
        request.setAttribute("commandesAll", lv);
    }

    @Override
    public void initCommandeByDistrict(HttpServletRequest request, Set<Integer> district, String societe) {
        List<Tlignecommande> ll = tlignecommandeFacade.findAll(societe);

        List<Tlignecommande> lv = ll.stream().filter(cc -> district.contains(cc.getClient().getSecteurid().getDistrictid().getId()))
                .collect(Collectors.toList());

        List<Tlignecommande> l1 = lv.stream().filter(in -> in.getEtatc().getCode() == 200).collect(Collectors.toList());
        request.setAttribute("commandesAcheve", l1);
        l1 = lv.stream().filter(in -> in.getEtatc().getCode() == 502).collect(Collectors.toList());
        request.setAttribute("commandesEncours", l1);
        l1 = lv.stream().filter(in -> in.getEtatc().getCode() == 201).collect(Collectors.toList());
        request.setAttribute("commandesLivraison", l1);
        l1 = lv.stream().filter(in -> in.getEtatc().getCode() == 401).collect(Collectors.toList());
        request.setAttribute("commandesValideClient", l1);
        l1 = lv.stream().filter(in -> in.getEtatc().getCode() == 501).collect(Collectors.toList());
        request.setAttribute("commandesNew", l1);
        request.setAttribute("commandesAll", lv);

    }

    @Override
    public void initiationEntityAdmin(HttpServletRequest request, Tusers utilisateur) {
        request.setAttribute("societe", utilisateur.getSociete());
        request.setAttribute("regions", tregionsFacade.findAll(utilisateur.getSociete().getImmatriculation()));
        request.setAttribute("sites", tsitesFacade.findAll(utilisateur.getSociete().getImmatriculation()));
        request.setAttribute("services", tservicesFacade.findAll(utilisateur.getSociete().getImmatriculation()));
        request.setAttribute("districts", tdistrictsFacade.findAll(utilisateur.getSociete().getImmatriculation()));
        request.setAttribute("secteurs", tsecteursFacade.findAll(utilisateur.getSociete().getImmatriculation()));
        request.setAttribute("sources", tsourcesFacade.findAll(utilisateur.getSociete().getImmatriculation()));
        request.setAttribute("priorites", tpriorityFacade.findAll(utilisateur.getSociete().getImmatriculation()));
        request.setAttribute("users", tusersFacade.findAll(utilisateur.getSociete().getImmatriculation()));
        request.setAttribute("groupes", tgroupsFacade.findAll(utilisateur.getSociete().getImmatriculation()));
        request.setAttribute("srubriques", tsrubriquesFacade.findAll(utilisateur.getSociete().getImmatriculation()));
        request.setAttribute("rubriques", trubriquesFacade.findAll(utilisateur.getSociete().getImmatriculation()));
        request.setAttribute("actions", tactionsFacade.findAll());
        request.setAttribute("typeclients", ttypeclientsFacade.findAll(utilisateur.getSociete().getImmatriculation()));
        request.setAttribute("clients", tclientsFacade.findAll(utilisateur.getSociete().getImmatriculation()));
        request.setAttribute("statuts", statutIncidentFacade.findAll());
        request.setAttribute("utilisateur", tusersFacade.findTusers(utilisateur.getId()));
        request.setAttribute("menus", tmenuFacade.findAll());
        request.setAttribute("smenus", tsmenuFacade.findAll());
        request.setAttribute("typelicences", typeLicenceFacade.findAll());
        request.setAttribute("souscriptions", souscriptionLicenceFacade.findAll(utilisateur.getSociete().getImmatriculation()));
        request.setAttribute("articles", tarticlesFacade.findAll(utilisateur.getSociete().getImmatriculation()));
        request.setAttribute("emballages", emballageFacade.findAll(utilisateur.getSociete().getId()));
        request.setAttribute("categories", tcategorieFacade.findAll(utilisateur.getSociete().getImmatriculation()));
        request.setAttribute("statutCommandesadmin", tetatcFacade.findAll());
        request.setAttribute("tourners", tournerFacade.findAll(utilisateur.getSociete().getId()));
        request.setAttribute("magasins", magasinFacade.findMagasinBySociete(utilisateur.getSociete().getId()));
        request.setAttribute("fournisseurs", fournisseurFacade.findFournisseurBySociete(utilisateur.getSociete().getId()));
        request.setAttribute("sourcesMouvement",sourceMouvementCaisseFacade.findAll());

    }

    @Override
    public void initiationEntityAdminLocal(HttpServletRequest request, Tusers utilisateur) {
        List<Tregions> r = new ArrayList<>();

        Tregions r1 = tregionsFacade.findTregions(utilisateur.getServiceid().getSite().getRegionid().getId());
        List<Tgroups> gr = tgroupsFacade.findAll(r1.getSociete().getImmatriculation()).stream()
                .filter(g -> g.getSociete().getTregionsList().contains(r1)).collect(Collectors.toList());
        request.setAttribute("societe", utilisateur.getSociete());
        request.setAttribute("regions", r.add(r1));
        request.setAttribute("sites", r1.getTsitesList());
        request.setAttribute("services", tservicesFacade.findAllByRegion(r1.getId()));
        request.setAttribute("districts", r1.getTdistrictsList());
        request.setAttribute("secteurs", tsecteursFacade.findAllByRegion(r1.getId()));
        request.setAttribute("sources", tsourcesFacade.findAll(utilisateur.getSociete().getImmatriculation()));
        request.setAttribute("priorites", tpriorityFacade.findAll(utilisateur.getSociete().getImmatriculation()));
        request.setAttribute("users", tusersFacade.findTclientsByRegion(r1.getId(), r1.getSociete().getImmatriculation()));
        request.setAttribute("groupes", gr);
        request.setAttribute("srubriques", tsrubriquesFacade.findAll(utilisateur.getSociete().getImmatriculation()));
        request.setAttribute("rubriques", trubriquesFacade.findAll(utilisateur.getSociete().getImmatriculation()));
        request.setAttribute("actions", tactionsFacade.findAll());
        request.setAttribute("typeclients", ttypeclientsFacade.findAll(utilisateur.getSociete().getImmatriculation()));
        request.setAttribute("clients", tclientsFacade.findTclientsByRegion(r1.getId(), r1.getSociete().getImmatriculation(), 0));
        request.setAttribute("statuts", statutIncidentFacade.findAll());
        request.setAttribute("utilisateur", tusersFacade.findTclientsByRegion(r1.getId(), r1.getSociete().getImmatriculation()));
        request.setAttribute("menus", tmenuFacade.findAll());
        request.setAttribute("smenus", tsmenuFacade.findAll());
        request.setAttribute("typelicences", typeLicenceFacade.findAll());
        request.setAttribute("souscriptions", souscriptionLicenceFacade.findAll(utilisateur.getSociete().getImmatriculation()));
        request.setAttribute("articles", tarticlesFacade.findAll(utilisateur.getSociete().getImmatriculation()));
        request.setAttribute("emballages", emballageFacade.findAll(utilisateur.getSociete().getId()));
        request.setAttribute("categories", tcategorieFacade.findAll(utilisateur.getSociete().getImmatriculation()));
        request.setAttribute("statutCommandesadmin", tetatcFacade.findAll());
        request.setAttribute("statutCommandes", tetatcFacade.findAll());
        request.setAttribute("magasins", magasinFacade.findMagasinBySociete(utilisateur.getSociete().getId()));
        request.setAttribute("fournisseurs", fournisseurFacade.findFournisseurBySociete(utilisateur.getSociete().getId()));
        request.setAttribute("sourcesMouvement",sourceMouvementCaisseFacade.findAll());
       // request.setAttribute("tourners", tournerFacade.findAll(utilisateur.getSociete().getId()));

    }

    @Override
    public void initiationSuperAdmin(HttpServletRequest request) {
        request.setAttribute("societes", societeFacade.findAll());
        request.setAttribute("regions", tregionsFacade.findTregionsEntities());
        request.setAttribute("sites", tsitesFacade.findTsitesEntities());
        request.setAttribute("services", tservicesFacade.findTservicesEntities());
        request.setAttribute("districts", tdistrictsFacade.findTdistrictsEntities());
        request.setAttribute("secteurs", tsecteursFacade.findTsecteursEntities());
        request.setAttribute("sources", tsourcesFacade.findTsourcesEntities());
        request.setAttribute("priorites", tpriorityFacade.findTpriorityEntities());
        request.setAttribute("users", tusersFacade.findTusersEntities());
        request.setAttribute("groupes", tgroupsFacade.findTgroupsEntities());
        request.setAttribute("srubriques", tsrubriquesFacade.findTsrubriquesEntities());
        request.setAttribute("rubriques", trubriquesFacade.findTrubriquesEntities());
        request.setAttribute("actions", tactionsFacade.findAll());
        request.setAttribute("typeclients", ttypeclientsFacade.findTtypeclientsEntities());
        request.setAttribute("clients", tclientsFacade.findTclientsEntities());
        request.setAttribute("statuts", statutIncidentFacade.findAll());
        request.setAttribute("utilisateurs", tusersFacade.findTusersEntities());
        request.setAttribute("menus", tmenuFacade.findAll());
        request.setAttribute("smenus", tsmenuFacade.findAll());
        request.setAttribute("typelicences", typeLicenceFacade.findAll());
        request.setAttribute("souscriptions", souscriptionLicenceFacade.findSouscriptionLicenceEntities());
        request.setAttribute("articles", tarticlesFacade.findAll());
        request.setAttribute("emballages", emballageFacade.findAll());
        request.setAttribute("categories", tcategorieFacade.findAll());
        request.setAttribute("statutCommandes", tetatcFacade.findAll());
        request.setAttribute("incidentsadmin", tincidentsFacade.findTincidentsEntities());
        request.setAttribute("tourners", tournerFacade.findAll());
        request.setAttribute("magasins", magasinFacade.findAll());
        request.setAttribute("fournisseurs", fournisseurFacade.findAll());
        request.setAttribute("sourcesMouvement",sourceMouvementCaisseFacade.findAll());
    }

    @Override
    public void initiationClients(HttpServletRequest request, Tclients utilisateur) {
        request.setAttribute("societe", utilisateur.getSociete());
        request.setAttribute("sources", tsourcesFacade.findAll(utilisateur.getSociete().getImmatriculation()));
        request.setAttribute("priorites", tpriorityFacade.findAll(utilisateur.getSociete().getImmatriculation()));
        request.setAttribute("client", utilisateur);
        request.setAttribute("srubriques", tsrubriquesFacade.findAll(utilisateur.getSociete().getImmatriculation()));
        request.setAttribute("rubriques", trubriquesFacade.findAll(utilisateur.getSociete().getImmatriculation()));
        request.setAttribute("statuts", statutIncidentFacade.findAll());
        request.setAttribute("incidents", tclientsFacade.findTclients(utilisateur.getId()).getTincidentsList());
        request.setAttribute("articles", tarticlesFacade.findAll(utilisateur.getSociete().getImmatriculation()));
        request.setAttribute("emballages", emballageFacade.findAll(utilisateur.getSociete().getId()));
        request.setAttribute("categories", tcategorieFacade.findAll(utilisateur.getSociete().getImmatriculation()));
        request.setAttribute("commandesclients", getCommandeClients(utilisateur.getId()));

    }

    @Override
    public void initialize(HttpServletRequest request, Tusers utilisateur) {

        if (utilisateur.getId() == -1) {
            initiationSuperAdmin(request);
        } else {
            if (getInitialize(utilisateur, 123)) {
                request.setAttribute("clients", tclientsFacade.findAll(utilisateur.getSociete().getImmatriculation()));

            } else {
                // request.setAttribute("clients", getClientZone(utilisateur));
                getClientZone(utilisateur, request);

            }
            if (getInitialize(utilisateur, 123)) {
                initTiketSociete(request, utilisateur.getId());
            } else if (getInitialize(utilisateur, 124)) {
                initTiketByRegionSociete(request, utilisateur.getId());
            } else if (getInitialize(utilisateur, 125)) {
                Set<Integer> zone = new HashSet<>();
                for (Taffectzone aff : utilisateur.getTaffectzoneList()) {
                    if (aff.getDistrict() != null) {
                        zone.add(aff.getDistrict());
                    }
                }
                initTiketByDistrictSociete(request, utilisateur.getId(), zone);

            } else if (getInitialize(utilisateur, 126)) {
                Set<Integer> zone = new HashSet<>();
                for (Taffectzone aff : utilisateur.getTaffectzoneList()) {
                    if (aff.getSecteur() != null) {
                        zone.add(aff.getSecteur());
                    }
                }
                initTiketBySecteurSociete(request, utilisateur.getId(), zone);

            }
            initTiketUser(request, utilisateur.getId());

            if (getInitialize(utilisateur, 123)) {
                initiationEntityAdmin(request, utilisateur);
            }
            if (getInitialize(utilisateur, 123)) {
                request.setAttribute("commandesclients", tlignecommandeFacade.findAll(utilisateur.getSociete().getImmatriculation()));
                initCommandeSociete(request, utilisateur.getServiceid().getSite().getRegionid().getSociete().getImmatriculation());
            } else if (getInitialize(utilisateur, 124)) {
                List<Tlignecommande> l1 = new ArrayList<>();
                List<Taffectzone> taff = taffectzoneFacade.findByUser(utilisateur.getId());
                for (Taffectzone taffectzone : taff) {
                    if (taffectzone.getRegion() != null) {
                        l1.addAll(tlignecommandeFacade.findAll(utilisateur.getSociete().getImmatriculation())
                                .stream().filter(cc -> cc.getClient().getSecteurid().getDistrictid().getRegionid().getId()
                                .compareTo(taffectzone.getRegion()) == 1)
                                .collect(Collectors.toList()));
                    }

                }
                request.setAttribute("commandesclients", l1);
                initCommandeByRegion(request, utilisateur.getId());
            } else if (getInitialize(utilisateur, 125)) {
                Set<Integer> zone = new HashSet<>();
                for (Taffectzone aff : utilisateur.getTaffectzoneList()) {
                    if (aff.getDistrict() != null) {
                        zone.add(aff.getDistrict());
                    }
                }
                initCommandeByDistrict(request, zone, utilisateur.getServiceid().getSite().getRegionid().getSociete().getImmatriculation());
            } else if (getInitialize(utilisateur, 126)) {
                Set<Integer> zone = new HashSet<>();
                for (Taffectzone aff : utilisateur.getTaffectzoneList()) {
                    if (aff.getSecteur() != null) {
                        zone.add(aff.getSecteur());
                    }

                }
                initCommandeBySecteur(request, zone, utilisateur.getServiceid().getSite().getRegionid().getSociete().getImmatriculation());
            }
        }

        request.setAttribute("articles", tarticlesFacade.findAll(utilisateur.getSociete().getImmatriculation()));
        request.setAttribute("categories", tcategorieFacade.findAll(utilisateur.getSociete().getImmatriculation()));
        request.setAttribute("societe", utilisateur.getSociete());
        request.setAttribute("sources", tsourcesFacade.findAll(utilisateur.getSociete().getImmatriculation()));
        request.setAttribute("priorites", tpriorityFacade.findAll(utilisateur.getSociete().getImmatriculation()));
        request.setAttribute("srubriques", tsrubriquesFacade.findAll(utilisateur.getSociete().getImmatriculation()));
        request.setAttribute("rubriques", trubriquesFacade.findAll(utilisateur.getSociete().getImmatriculation()));
        request.setAttribute("statuts", statutIncidentFacade.findAll());
        request.setAttribute("statutCommandes", tetatcFacade.findAll());
        // request.setAttribute("tourners", tournerFacade.findAll());
    }

    Set<Tourner> getTournerZone(int entity, int who) {
        List<Tclients> cli;
        Set<Tourner> trs = new ArraySet<>();
        switch (who) {
            case 0: {
                Tregions reg = tregionsFacade.findTregions(entity);
                cli = tclientsFacade.findTclientsByRegion(reg.getId(), reg.getSociete().getImmatriculation(), who);
                for (Tclients cl : cli) {
                    trs.add(cl.getTourner());
                }
                break;
            }
            case 1: {
                Tdistricts reg = tdistrictsFacade.findTdistricts(entity);
                cli = tclientsFacade.findTclientsByRegion(reg.getId(), reg.getRegionid().getSociete().getImmatriculation(), who);
                for (Tclients cl : cli) {
                    trs.add(cl.getTourner());
                }
                break;
            }
            case 2: {
                Tsecteurs reg = tsecteursFacade.findTsecteurs(entity);
                cli = tclientsFacade.findTclientsByRegion(reg.getId(), reg.getDistrictid().getRegionid().getSociete().getImmatriculation(), who);
                for (Tclients cl : cli) {
                    trs.add(cl.getTourner());
                }
                break;
            }
            case -1: {
                Tusers reg = tusersFacade.findTusers(entity);
                for (AffectTournerUser af : reg.getAffectTournerUserList()) {
                    trs.add(af.getTourner());
                }
                break;
            }
            default:
                break;
        }

        return trs;
    }

    void getClientZone(Tusers user, HttpServletRequest request) {
        Set<Integer> zone = new HashSet<>();
        List<Tclients> cli = new ArrayList<>();
        List<Tourner> trs = new ArrayList<>();
        for (Taffectzone aff : user.getTaffectzoneList()) {
            if (aff.getRegion() != null) {
                zone.add(aff.getRegion());
            }
        }
        if (zone.isEmpty()) {
            for (Taffectzone aff : user.getTaffectzoneList()) {
                if (aff.getSecteur() != null) {
                    zone.add(aff.getSecteur());
                    trs.addAll(getTournerZone(aff.getSecteur(), 2));
                }
            }

        } else {
            for (Taffectzone aff : user.getTaffectzoneList()) {
                if (aff.getRegion() != null) {
                    zone.add(aff.getRegion());
                    trs.addAll(getTournerZone(aff.getRegion(), 0));
                }
            }
            if (!zone.isEmpty()) {
                cli = tclientsFacade.findTclientsByRegion(user.getServiceid().getSite().getRegionid().getId(), user.getSociete().getImmatriculation(), 0).
                        stream().filter(cl -> zone.contains(cl.getSecteurid().getDistrictid().getRegionid().getId())).collect(Collectors.toList());
            }
            //trs.addAll(getTournerZone(user.getServiceid().getSite().getRegionid().getId(), 0));
        }
        if (zone.isEmpty()) {
            for (Taffectzone aff : user.getTaffectzoneList()) {
                if (aff.getDistrict() != null) {
                    zone.add(aff.getDistrict());
                    trs.addAll(getTournerZone(aff.getDistrict(), 1));
                }
            }
            if (!zone.isEmpty()) {
                cli = tclientsFacade.findTclientsByRegion(user.getServiceid().getSite().getRegionid().getId(), user.getSociete().getImmatriculation(), 0).
                        stream().filter(cl -> zone.contains(cl.getSecteurid().getDistrictid().getId())).collect(Collectors.toList());
            }
        } else {
            if (!zone.isEmpty()) {
                cli = tclientsFacade.findTclientsByRegion(user.getServiceid().getSite().getRegionid().getId(), user.getSociete().getImmatriculation(), 0).
                        stream().filter(cl -> zone.contains(cl.getSecteurid().getId())).collect(Collectors.toList());
            }
        }
        if (zone.isEmpty()) {
            trs.addAll(getTournerZone(user.getId(), -1));
        }

        request.setAttribute("tourners", trs);
        request.setAttribute("clients", cli);
    }

    boolean getInitialize(Tusers t, int code) {
        boolean verifi = false;
        for (TgroupsAssoc str : t.getGroupeid().getTgroupsAssocList()) {
            if (str.getAction().getCodeAction() == code) {
                verifi = true;
                break;
            }
        }
        return verifi;
    }

    @Override
    public List<Tlignecommande> getCommandeClients(int client) {
        Tclients cli = tclientsFacade.findTclients(client);
        return cli.getTlignecommandeList();
    }

    @Override
    public List<Tlignecommande> getCommandesByZoneAutorize(Tusers utilisateur) {
        List<Tlignecommande> li = tlignecommandeFacade.findAll(utilisateur.getSociete().getImmatriculation());
        List<Tlignecommande> lv = new ArrayList<>();
        if (getInitialize(utilisateur, 123)) {
            lv = tlignecommandeFacade.findAll(utilisateur.getSociete().getImmatriculation());
        } else if (getInitialize(utilisateur, 124)) {
            List<Tlignecommande> l1 = new ArrayList<>();
            List<Taffectzone> taff = taffectzoneFacade.findByUser(utilisateur.getId());
            for (Taffectzone taffectzone : taff) {
                if (taffectzone.getRegion() != null) {
                    lv.addAll(li.stream().filter(cc -> cc.getClient().getSecteurid().getDistrictid().getRegionid().getId()
                            .compareTo(taffectzone.getRegion()) == 1)
                            .collect(Collectors.toList()));
                }

            }
        } else if (getInitialize(utilisateur, 125)) {
            Set<Integer> zone = new HashSet<>();
            for (Taffectzone aff : utilisateur.getTaffectzoneList()) {
                if (aff.getDistrict() != null) {
                    zone.add(aff.getDistrict());
                }
            }
            lv = li.stream().filter(cc -> zone.contains(cc.getClient().getSecteurid().getDistrictid().getId()))
                    .collect(Collectors.toList());
        } else if (getInitialize(utilisateur, 126)) {
            Set<Integer> zone = new HashSet<>();
            for (Taffectzone aff : utilisateur.getTaffectzoneList()) {
                if (aff.getSecteur() != null) {
                    zone.add(aff.getSecteur());
                }

            }
            lv = li.stream().filter(cc -> zone.contains(cc.getClient().getSecteurid().getId()))
                    .collect(Collectors.toList());
        }
        return lv;
    }
}
