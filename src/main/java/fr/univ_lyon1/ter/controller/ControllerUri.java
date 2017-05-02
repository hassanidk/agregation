package fr.univ_lyon1.ter.controller;

import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.faces.application.Resource;
import javax.servlet.ServletContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import fr.univ_lyon1.ter.modele.Aggregation;
import fr.univ_lyon1.ter.utilitaire.Debug;
import fr.univ_lyon1.ter.utilitaire.Utils;

import org.springframework.stereotype.Controller;

@Controller
public class ControllerUri {
	// Récupération du contexte de l'application
	
	@Autowired
	ServletContext context;
	Aggregation agg;
	// ------- Redirige vers l'index de l'application
	@RequestMapping(value = { "/", "/index" })
	public String index() {
		return "index";
	}
	@RequestMapping(value = {"/result"}, method = RequestMethod.POST ,produces = "application/json")
	public  String postResult(@RequestParam String preferences, 
			@RequestParam String dateJour, 
			@RequestParam String heureArrivee, 
			@RequestParam String heureDepart){
		
		
		ArrayList<String> pref = new ArrayList<String>();
		String [] elementJour = dateJour.split("/");
		String [] elementArrivee = heureArrivee.split(":");
		String [] elementDepart = heureDepart.split(":");
		Calendar arrivee = Utils.constructCalendar(elementJour, elementArrivee);
		Calendar depart = Utils.constructCalendar(elementJour, elementDepart);
		if (StringUtils.isNotBlank(preferences)){
			String [] prefs = preferences.split(",");
			for (int i = 0; i<prefs.length;++i)
				pref.add(prefs[i]);
		}
		agg = new Aggregation(arrivee, depart, pref);
		
		if (Utils._DEBUG_MODE){
			Debug.addDebug("--CONTROLLER URI--", "Début");
			Debug.addDebug("preferences", preferences);
			Debug.addDebug("dateJour", dateJour);
			Debug.addDebug("heureArrivee", heureArrivee);
			Debug.addDebug("heureDepart", heureDepart);
			Debug.addDebug("arrivee", arrivee);
			Debug.addDebug("prefs", pref);
			Debug.addDebug("--CONTROLLER URI--", "Fin");
			Debug.printDebug();
			
		}
		System.out.println(agg.toString());
		return "result";
		//return agg.affichageResultatWeb();	
	}
	
	@RequestMapping(value = {"/result"}, method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String getResult(){
		System.out.println(agg.toString());
		return agg.affichageResultatWeb();
	}


}