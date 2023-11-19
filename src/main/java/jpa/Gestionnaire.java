package jpa;


import persistence.AutomateDao;
import persistence.FicheAutomateDao;
import persistence.ModuleTelecomDao;
import persistence.ProduitDao;

public class Gestionnaire {
	private static Gestionnaire instance = new Gestionnaire();
	private AutomateDao automateDao = new AutomateDao();
	private FicheAutomateDao ficheAutomateDao = new FicheAutomateDao();
	private ProduitDao produitDao = new ProduitDao();
	private ModuleTelecomDao moduleTelecomDao = new ModuleTelecomDao();
	
	public static Gestionnaire getInstance() { return instance; }
	public AutomateDao getAutomateDao() { return automateDao; }
	public FicheAutomateDao getFicheAutomateDao() { return ficheAutomateDao; }
	public ModuleTelecomDao getModuleTelecomDao() { return moduleTelecomDao; }
	
	public ProduitDao getProduitDao() { return produitDao; }
	
}
