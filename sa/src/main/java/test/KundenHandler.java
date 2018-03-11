package test;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.RollbackException;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.UserTransaction;
import javax.ws.rs.NotSupportedException;

import org.omg.CORBA.SystemException;

public class KundenHandler {

	@PersistenceContext
	private EntityManager em;

	@Resource
	private UserTransaction utx;

	private DataModel<Kunde> kunden;
	private DataModel<ArtikelDaten> artikeln;
	private DataModel<ArtikelDaten> filterArtikeln;
	private ArtikelDaten selectedArtikel;
	private DataModel<ArtikelDaten> warenkorb;

	private Kunde merkeKunde = new Kunde();
	private ArtikelDaten merkeArtikel = new ArtikelDaten();

	private double gesamtpreis;
	private double gesamt;
	private int gesamtanzahl;
	private String text;
	private String username;
	private String passwort;
	private Kunde kunde;
	private List<String> images;
	private String langCode = "en";
	private String langerCode = "de";
	private int number;
	private int index;

	// Methoden zur Verwaltung der Kunden

	/**
	 * Die Daten der jeweiligen Kunden und Artikel werden aufgerufen sobald man
	 * als Admin auf die ansicht aller Kunden geht
	 */

	@PostConstruct
	public void init() {
		images = new ArrayList<String>();
		try {
			utx.begin();
		} catch (NotSupportedException e) {
			e.printStackTrace();
		} catch (SystemException e) {
			e.printStackTrace();
		} catch (javax.transaction.NotSupportedException e) {
			e.printStackTrace();
		} catch (javax.transaction.SystemException e) {
			e.printStackTrace();
		}
		// em.persist(new Kunde(Anrede.HERR, "Yasar", "Wieck", new
		// GregorianCalendar(1993, 01, 06).getTime(),
		// "Prager Str. 12", "27568", "Bremerhaven", "yw@ey.de", "ywieck",
		// "ywieck", Rolle.ADMIN));
		// em.persist(new Kunde(Anrede.HERR, "Enno", "Münsterberg", new
		// GregorianCalendar(1990, 11, 26).getTime(),
		// "Lodjeweg. 98", "27568", "Bremerhaven", "em@ey.de", "emünsterberg",
		// "emünsterberg", Rolle.ADMIN));
		// em.persist(new Kunde(Anrede.FRAU, "Kathleen", "Hasselhoff", new
		// GregorianCalendar(1994, 10, 3).getTime(),
		// "Verdenerstr. 103", "27568", "Bremerhaven", "khase@bla.de",
		// "khasselhoff", "khasselhoff", Rolle.KUNDE));
		// em.persist(new Kunde(Anrede.HERR, "Alfred-Admin", "Schmidt", new
		// GregorianCalendar(1990, 1, 1).getTime(),
		// "Feldweg 5", "27568", "Bremerhaven", "aschmidt@hs-bremerhaven.de",
		// "aschmidta", "aschmidta",
		// Rolle.ADMIN));
		// em.persist(new Kunde(Anrede.HERR, "Alfred-Kunde", "Schmidt", new
		// GregorianCalendar(1990, 1, 1).getTime(),
		// "Feldweg 5", "27568", "Bremerhaven", "aschmidt@hs-bremerhaven.de",
		// "aschmidtk", "aschmidtk",
		// Rolle.KUNDE));
		// em.persist(new Kunde(Anrede.HERR, "Marcel-Kunde", "Malitz", new
		// GregorianCalendar(1990, 1, 1).getTime(),
		// "Alfredstr. 2", "27568", "Bremerhaven", "mmalitz@hs-bremerhaven.de",
		// "mmalitzk", "mmalitzk",
		// Rolle.KUNDE));
		// em.persist(new Kunde(Anrede.HERR, "Marcel-Admin", "Malitz", new
		// GregorianCalendar(1990, 1, 1).getTime(),
		// "Alfredstr. 2", "27568", "Bremerhaven", "mmalitz@hs-bremerhaven.de",
		// "mmalitza", "mmalitza",
		// Rolle.ADMIN));

		kunden = new ListDataModel<Kunde>();
		kunden.setWrappedData(em.createNamedQuery("SelectKunden").getResultList());

		try {
			utx.commit();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (RollbackException e) {
			e.printStackTrace();
		} catch (HeuristicMixedException e) {
			e.printStackTrace();
		} catch (HeuristicRollbackException e) {
			e.printStackTrace();
		} catch (SystemException e) {
			e.printStackTrace();
		} catch (javax.transaction.RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (javax.transaction.SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			utx.begin();
		} catch (javax.transaction.NotSupportedException | javax.transaction.SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// em.persist(new ArtikelDaten("Macbook Pro 2017", "Apple", "macOS,
		// Intel i7, 16 GB DDR RAM, 512 GB SSD, Retina",
		// 2199.00, "macbook.jpg"));
		// em.persist(
		// new ArtikelDaten("hp-630", "HP", "Windows 8, Intel core i3, Radeon
		// R9, 500 GB", 399.00, "hp-630..jpg"));
		// em.persist(new ArtikelDaten("asus-relax", "ASUS", "Windows 10, Intel
		// core i7, Geforce GTX 890, 500 GB", 549.95,
		// "asus2.jpg"));
		// em.persist(new ArtikelDaten("Chromebook", "Samsung", "Windows 10, AMD
		// FX, Geforce GTX 580, 750 GB", 349.95,
		// "chrome.jpg"));
		//
		// em.persist(new ArtikelDaten("Biker Lederjacke", "Jack & Jones",
		// "Slim-fit, echtes Lammleder", 199.95,
		// "lederjacke.jpg"));
		// em.persist(new ArtikelDaten("Tailliertes Kleid", "NEXT", "Materialmix
		// mit Stretch", 51.00, "kleid.jpg"));
		// em.persist(new ArtikelDaten("Tommy Hilfiger Multifunktionsuhr",
		// "Tommy Hilfiger",
		// "Edelstahlgehäuse, Armband aus echtem Leder", 199.95,
		// "lederjacke.jpg"));
		//
		// em.persist(new ArtikelDaten("Magnifica ESAM 3200.s / 3240",
		// "De'Longhi",
		// "Mahlt Bohnen frisch, mit Michschäumdüse ausgestattet", 339.00,
		// "kaffeemaschine.jpg"));
		// em.persist(new ArtikelDaten("HARD TITANIUM", "Tefal", "Pfannenset aus
		// echtem Aluminium, Anti-Haft-Versiegelung",
		// 69.99, "pfannen.jpg"));
		//
		// em.persist(new ArtikelDaten("Monopoli Classic", "Hasbro",
		// "Familienspiel, für 2-6 Spieler", 34.99,
		// "monopoli.jpg"));
		// em.persist(new ArtikelDaten("Fifa 17 für PlayStation 4", "EA Games",
		// "Fussball für die Konsole, Mehrspieler",
		// 64.99, "fifa.jpg"));
		//
		// em.persist(new ArtikelDaten("Fifty Shades of Grey - Gefährliche
		// Liebe", "Goldman",
		// "Romane & Erzählungen, Erscheinungsjahr: 2017", 9.99,
		// "shadesofgrey.jpg"));
		// em.persist(new ArtikelDaten("Grundkurs SAP ERP", "vieweg",
		// "Einführung in SAP ERP, Fallstudie auf Basis ECC 5.0", 42.99,
		// "sapbuch.jpg"));
		//
		// em.persist(new ArtikelDaten("Nike Libero knit Short Herren", "Nike",
		// "Effektives Feuchtigekeitsmanagement, Elasitscher Bund", 24.95,
		// "nikeshorts.jpg"));
		// em.persist(new ArtikelDaten("CL Finale Berlin Fussball", "adidas",
		// "optimale Spieleigenschaft, Hohe Abriebfestigkeit", 29.95,
		// "ball.jpg"));

		artikeln = new ListDataModel<ArtikelDaten>();
		artikeln.setWrappedData(em.createNamedQuery("SelectArtikel").getResultList());

		try {
			try {
				utx.commit();
			} catch (javax.transaction.RollbackException | javax.transaction.SystemException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SecurityException | IllegalStateException | HeuristicMixedException | HeuristicRollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Array der auszuwählenden Rollen (Enum-Klasse Rolle)
	 */
	public Rolle[] getRolleValues() {
		return Rolle.values();
	}

	/**
	 * Durch den klick auf //neuer Kunde// als Admin in der Ansicht (alleKunde)
	 * wird ein neues Objekt ereugt um die Daten zu sammeln
	 */
	public String neuK() {
		merkeKunde = new Kunde();
		return "registrieren";
	}

	/**
	 * Durch den klick auf //Speichern// (kundeBearbeiten) werden die Daten in
	 * die Datenbank gespeichert
	 * 
	 * @throws HeuristicRollbackException
	 * @throws HeuristicMixedException
	 * @throws IllegalStateException
	 * @throws SecurityException
	 */
	public String speichern() {
		try {
			utx.begin();
			merkeKunde = em.merge(merkeKunde);
			em.persist(merkeKunde);
			kunden.setWrappedData(em.createNamedQuery("SelectKunden").getResultList());
			utx.commit();
		} catch (SecurityException | IllegalStateException | RollbackException | HeuristicRollbackException
				| SystemException | NotSupportedException | HeuristicMixedException
				| javax.transaction.NotSupportedException | javax.transaction.SystemException
				| javax.transaction.RollbackException e) {
			e.printStackTrace();
		}
		return "alleKunden";
	}

	public String speichernProfil() {
		try {
			utx.begin();
			merkeKunde = em.merge(merkeKunde);
			em.persist(merkeKunde);
			kunden.setWrappedData(em.createNamedQuery("SelectKunden").getResultList());
			utx.commit();
		} catch (SecurityException | IllegalStateException | RollbackException | HeuristicRollbackException
				| SystemException | NotSupportedException | HeuristicMixedException
				| javax.transaction.NotSupportedException | javax.transaction.SystemException
				| javax.transaction.RollbackException e) {
			e.printStackTrace();
		}
		return "index";
	}

	/**
	 * durch den klick auf //Löschen// als Admin in der Ansicht (alleKunden)
	 * wird der jenige aus der Datenbank gelöscht
	 */
	public String delete() {
		merkeKunde = kunden.getRowData();
		try {
			try {
				utx.begin();
			} catch (javax.transaction.NotSupportedException | javax.transaction.SystemException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (NotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		merkeKunde = em.merge(merkeKunde);
		em.remove(merkeKunde);
		kunden.setWrappedData(em.createNamedQuery("SelectKunden").getResultList());
		try {
			try {
				utx.commit();
			} catch (javax.transaction.RollbackException | javax.transaction.SystemException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (HeuristicMixedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (HeuristicRollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "alleKunden";
	}

	public String edit() {
		merkeKunde = kunden.getRowData();
		return "kundenDatenBearbeiten";
	}

	// Methoden zur Verwaltung der Artikel

	/**
	 * Durch den Klick auf //neuer Artikel// als Admin in der Ansicht
	 * (alleArtikel) wird ein neues Objekt erzeugt um die Daten später
	 * abzuspeichern
	 */
	public String Artikelneu() {
		merkeArtikel = new ArtikelDaten();
		return "neuerArtikel";
	}

	/**
	 * Durch den klick auf //Bearbeiten//in der Ansicht (alleArtikel) werden die
	 * ArtikelDaten aufgerufen
	 */
	public String ArtikelBearbeiten() {
		merkeArtikel = artikeln.getRowData();
		return "produkteBearbeiten";
		// return "produktVerwaltung";
	}

	/**
	 * Durch den klick auf //Speichern// (artikelBearbeiten) werden die
	 * Artikeldaten in die Datenbak gespeichert
	 */
	public String Artikelspeichern() {
		merkeArtikel.setPreis(gesamt = Math.floor(merkeArtikel.getPreis() * 100) / 100.0);
		try {
			try {
				utx.begin();
			} catch (javax.transaction.NotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (javax.transaction.SystemException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (NotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		merkeArtikel = em.merge(merkeArtikel);
		em.persist(merkeArtikel);
		artikeln.setWrappedData(em.createNamedQuery("SelectArtikel").getResultList());
		try {
			try {
				utx.commit();
			} catch (javax.transaction.RollbackException | javax.transaction.SystemException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (HeuristicMixedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (HeuristicRollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		artikelGespeichert(null);
		return "produktVerwaltung";
	}

	/**
	 * Durch den klick auf //Löschen// (alleArtikel) wird der Artikel aus der
	 * Datenbank gelöscht
	 */
	public String ArtikelLoeschen() {
		merkeArtikel = artikeln.getRowData();
		try {
			try {
				utx.begin();
			} catch (javax.transaction.NotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (javax.transaction.SystemException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (NotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		merkeArtikel = em.merge(merkeArtikel);
		em.remove(merkeArtikel);
		artikeln.setWrappedData(em.createNamedQuery("SelectArtikel").getResultList());

		try {
			try {
				utx.commit();
			} catch (javax.transaction.RollbackException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (javax.transaction.SystemException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (HeuristicMixedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (HeuristicRollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	//
	// // Methoden zur Verwaltung des Webshops
	//
	// /**
	// * Durch das klicken auf //Bezahlen// in der Ansicht (zurKasse) werden
	// * Kreditkartendaten aufgerufen. Durch das klicken auf //Bezahlen// in der
	// * Ansicht (Kreditkartendaten) werden die Artikel in die Datenbank in
	// * Beziehung zu dem zugehörigen Kunden aufgerufen
	// */
	// public String Bezahlen() {
	// if (gesamtpreis == 0) {
	// keineArtikel(null);
	// return null;
	// }
	//
	// if (merkeKunde.getKreditkarte() == null)
	// merkeKunde.setKreditkarte(merkeKreditkarte);
	// merkeKunde.getArtikelDaten();
	// try {
	// try {
	// utx.begin();
	// } catch (javax.transaction.NotSupportedException |
	// javax.transaction.SystemException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// } catch (NotSupportedException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (SystemException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// merkeKunde = em.merge(merkeKunde);
	// merkeKreditkarte = em.merge(merkeKreditkarte);
	// merkeArtikel = em.merge(merkeArtikel);
	// em.persist(merkeKunde);
	// em.persist(merkeKreditkarte);
	// em.persist(merkeArtikel);
	// kunden.setWrappedData(em.createNamedQuery("SelectKunden").getResultList());
	//
	// try {
	// try {
	// utx.commit();
	// } catch (javax.transaction.RollbackException |
	// javax.transaction.SystemException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// } catch (SecurityException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (IllegalStateException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (RollbackException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (HeuristicMixedException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (HeuristicRollbackException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (SystemException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// gesamtpreis = 0;
	// gesamt = 0;
	//
	// bezahlt(null);
	// return null;
	//
	// }

	/**
	 * Durch den klick auf //zum Einkauf// gelangt der Admin mit sein Daten zur
	 * Shop ansicht
	 */
	public String zumEinkauf() {
		if (kunden.isRowAvailable())
			merkeKunde = kunden.getRowData();

		return "index";
	}

	/**
	 * Durch den klick auf //Persönliche Daten// in der Ansicht (shop) kann der
	 * Kunde seine eigenen Daten bearbeiten
	 */
	public String eigeneDatenBearbeiten() {
		return "eigeneDatenBearbeiten";
	}

	/**
	 * Durch den klick auf //warenkorb leeren// in der Ansicht (shop) werden
	 * alle Artikel aus dem Warenkorb gelöscht
	 */
	public String warenkorbLeeren() {
		merkeKunde.getArtikelDaten().clear();
		gesamtpreis = 0;
		gesamtanzahl = 0;
		return null;
	}

	/**
	 * durch das Klicken auf //löschen// in der Ansicht (shop) wird der
	 * auserwählte Artikel aus dem Warenkorb gelöscht
	 */
	public String ausWarenkorblöschen() {
		merkeArtikel = warenkorb.getRowData();
		try {
			try {
				utx.begin();
			} catch (javax.transaction.NotSupportedException | javax.transaction.SystemException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (NotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		merkeArtikel = em.merge(merkeArtikel);
		em.remove(merkeArtikel);
		warenkorb.setWrappedData(em.createNamedQuery("SelectArtikel").getResultList());
		try {
			try {
				utx.commit();
			} catch (javax.transaction.RollbackException | javax.transaction.SystemException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (HeuristicMixedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (HeuristicRollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "cart";
		// if (index >= merkeKunde.getArtikelDaten().size()) {
		// keinePosition(null);
		// return null;
		// } else {
		// gesamtpreis = gesamtpreis -
		// merkeKunde.getArtikelDaten().get(index).getPreis();
		// gesamt = Math.floor(gesamt * 100) / 100.0;
		//
		// gesamtanzahl = gesamtanzahl -
		// merkeKunde.getArtikelDaten().get(index).getAnzahl();
		// merkeKunde.getArtikelDaten().remove(index);
		// positionGeloescht(null);
		// for (int i = index; i < merkeKunde.getArtikelDaten().size(); i++) {
		// merkeKunde.getArtikelDaten().get(i).setNummer(i);
		// }
		// return null;
		// }
	}

	/**
	 * Durch das Klicken auf //in den Warenkorb// nach dem Klicken auf
	 * //Kaufen// in der Ansicht(shop) wird der Artikel dem Warenkorb
	 * hinzugefügt und in der Liste (artikeldaten(klasse: Kunde)) abgespeichert.
	 * Zusätzlich werden noch Gesamtpreis und Anzahl mitberechnet
	 */
	public String artikelInWarenkorb() {
		// merkeKunde.getArtikelDaten().add(merkeArtikel);
		//
		// merkeArtikel.setNummer(number);
		// number++;
		//
		// gesamtpreis = gesamtpreis + (merkeArtikel.getAnzahl() *
		// merkeArtikel.getPreis());
		// gesamtpreis = Math.floor(gesamtpreis * 100) / 100.0;
		//
		// gesamt = merkeArtikel.getAnzahl() * merkeArtikel.getPreis();
		// gesamt = Math.floor(gesamt * 100) / 100.0;
		// merkeArtikel.setGesamt(gesamt);
		//
		// gesamtanzahl = gesamtanzahl + merkeArtikel.getAnzahl();
		//
		// System.out.println(merkeArtikel.getGesamt());
		// merkeArtikel = new ArtikelDaten();
		try {
			try {
				utx.begin();
			} catch (javax.transaction.NotSupportedException | javax.transaction.SystemException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (NotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		merkeArtikel = em.merge(merkeArtikel);
		// merkeArtikel = em.merge(merkeArtikel);
		// em.persist(merkeKunde);
		em.persist(merkeArtikel);
		warenkorb = new ListDataModel<ArtikelDaten>();
		warenkorb.setWrappedData(em.createNamedQuery("SelectArtikel").getResultList());

		try {
			try {
				utx.commit();
			} catch (javax.transaction.RollbackException | javax.transaction.SystemException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (HeuristicMixedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (HeuristicRollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		gesamt = 0;
		return "index";
	}

	/**
	 * Diese Nachricht erscheint nach dem klicken auf Löschen in der Ansicht
	 * (shop) nach der eingabe einer bestimmten Position
	 */
	public void positionGeloescht(ActionEvent actionevent) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null,
				new FacesMessage("Artikel gelöscht", "Sie haben den Artikel aus Ihrem warenkorb gelöscht"));
	}

	/**
	 * diese Nachricht erscheint nach der Eingabe der Kreditkarte nach dem
	 * Klicken auf //Bezahlen// in der Ansicht (zurKasse)
	 */
	public void bezahlt(ActionEvent actionevent) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Bezahlt", "Sie haben die/den Artikel soeben Bezahlt"));
	}

	/**
	 * diese Ansicht erscheint wenn der Kunde auf den Button //in den
	 * warenkorb// klickt
	 */
	public void inDenWarenkorb(ActionEvent event) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Warenkorb", "Artikel ist jetzt in ihrem Warenkorb"));
	}

	/**
	 * diese Nachricht erscheint, wenn der Kunde eine ungültige Position angibt
	 */
	public void keinePosition(ActionEvent actionEvent) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
				"ungültige Position", "diese Position ist in Ihrem warenkorb nicht vorhanden"));

	}

	/**
	 * Diese Nachricht erscheint, wenn der Kunde ein falsches Passwort oder
	 * falschen Benutzernamen angibt
	 */
	public void falschesPasswort(ActionEvent actionEvent) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
				"Falsches Passwort", "Ihr Passwort oder Benutzername ist falsch"));
	}

	/**
	 * diese Nachricht erscheint, wenn der Kunde ohne ware zur Kasse geht und
	 * dann auf //Bezahlen// klickt oder wenn er schon bereits gezahlt hat
	 */
	public void keineArtikel(ActionEvent actionEvent) {
		if (gesamtanzahl == 0) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Keine Artikel", "Sie haben keine Artikel im Warenkorb, bitte gehen Sie zurück zum shop"));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Ware wurde schon bezahlt", "Wir freuen uns auf ihren nächsten Einkauf in unserem webshop"));
		}
	}

	/**
	 * diese Nachricht erscheint, wenn man auf //Speichern// klickt
	 */
	public void gespeichert(ActionEvent event) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Daten gespeichert", "Sie haben Ihre Daten erfolgreich gespeichert"));
	}

	/**
	 * diese Nachricht erscheint, wenn man nach der Artikelbearbeitung auf
	 * //speichern// klickt
	 */
	public void artikelGespeichert(ActionEvent actionEvent) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Daten gespeichert", "Artikel erfolgreich Gespeichert"));

	}

	/**
	 * ein neues Objekt wird erzeugt um die Daten nachher zu speichern, wenn auf
	 * //Registrieren// klickt
	 */

	// public String Registrieren() {
	// merkeKunde = new Kunde();
	// return "alleKunden";
	// }
	public String registrieren() {
		try {
			utx.begin();
			merkeKunde = em.merge(merkeKunde);
			em.persist(merkeKunde);
			kunden.setWrappedData(em.createNamedQuery("SelectKunden").getResultList());
			utx.commit();
		} catch (SecurityException | IllegalStateException | RollbackException | HeuristicRollbackException
				| SystemException | NotSupportedException | HeuristicMixedException
				| javax.transaction.NotSupportedException | javax.transaction.SystemException
				| javax.transaction.RollbackException e) {
			e.printStackTrace();
		}
		return "login";
	}

	/**
	 * wenn der Kunde auf //speichern// klickt (registrierung)dann werden seine
	 * Daten gespeichert und die Rolle wird automatisch auf KUNDE gesetzt. Man
	 * gelangt nach der Registrierung zur Login seite
	 */
	public String registrierungSpeichern() {
		if (merkeKunde.getPasswort() == null) {
			merkeKunde.setRolle(Rolle.KUNDE);
			try {
				try {
					utx.begin();
				} catch (javax.transaction.NotSupportedException | javax.transaction.SystemException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (NotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SystemException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			merkeKunde = em.merge(merkeKunde);
			em.persist(merkeKunde);
			kunden.setWrappedData(em.createNamedQuery("SelectKunden").getResultList());
			try {
				try {
					utx.commit();
				} catch (javax.transaction.RollbackException | javax.transaction.SystemException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RollbackException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (HeuristicMixedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (HeuristicRollbackException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SystemException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return "PasswortBearbeiten";
		} else {
			try {
				try {
					utx.begin();
				} catch (javax.transaction.NotSupportedException | javax.transaction.SystemException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (NotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SystemException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			merkeKunde = em.merge(merkeKunde);
			em.persist(merkeKunde);
			kunden.setWrappedData(em.createNamedQuery("SelectKunden").getResultList());
			try {
				try {
					utx.commit();
				} catch (javax.transaction.RollbackException | javax.transaction.SystemException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RollbackException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (HeuristicMixedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (HeuristicRollbackException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SystemException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "login";
		}
	}

	/**
	 * Wenn der Kunde auf den Button //zur Kasse// in der Ansicht (shop) klickt,
	 * werden diese Daten aufgerufen bzw gesetzt
	 */
	public String zurKasse() {

		merkeKunde.getArtikelDaten();
		System.out.println(gesamtanzahl);
		merkeArtikel.setGesamtanzahl(gesamtanzahl);
		System.out.println(gesamtpreis);
		gesamtpreis = Math.floor(gesamtpreis * 100) / 100.0;
		merkeArtikel.setGesamtprice(gesamtpreis);
		return "zurKasse";

	}

	/**
	 * falls der Kunde auf den Button //zurück// klickt in der Ansicht
	 * (PasswortBearbeiten), dann gelangt er falls er Schon ein Passwort hat zur
	 * seite (eigeneDatenBearbeiten)
	 */
	public String zurueckVonPasswort() {
		if (merkeKunde.getPasswort() == null) {
			return "registrieren";
		} else {
			return "eigeneDatenBearbeiten";
		}
	}

	/**
	 * wenn der Kunde auf //eigenes Passwort bearbeiten // klickt in der Ansicht
	 * (eigeneDatenBearbeiten)
	 */
	public String eigenesPasswortBearbeiten() {
		return "PasswortBearbeiten";
	}

	/**
	 * wenn der Kunde auf //zurück// klickt in der Ansicht(zurKasse)oder
	 * (eigeneDatenBearbeiten), dann gelangt er zur shop seite, wo noch seine
	 * Ware im Warenkorb sich befindet, falls welche vorhanden waren
	 */
	public String zurückZumShop() {
		if (gesamtpreis == 0 && gesamt == 0)
			merkeKunde.setArtikelDaten(new ArrayList<ArtikelDaten>());
		merkeArtikel = new ArtikelDaten();
		if (kunde.getRolle() == Rolle.ADMIN) {
			return "alleKunden";
		} else {
			return "index";
		}
	}

	// Methoden zum Login

	/**
	 * wenn der Kunde oder Admin sich einloggt gelangt er auf die jeweilige
	 * seite
	 */
	@SuppressWarnings("unchecked")
	public String login() {

		Query query = em
				.createQuery("select k from Kunde k " + "where k.username = :username and k.passwort = :passwort ");
		query.setParameter("username", username);
		query.setParameter("passwort", passwort);

		// if (kunden.isRowAvailable()) {
		// merkeKunde = kunden.getRowData();
		// merkeKunde.setArtikelDaten(new ArrayList<ArtikelDaten>());
		// }

		System.out.println(username + " " + passwort);

		List<Kunde> kunden = query.getResultList();
		if (kunden.size() == 1) {
			kunde = kunden.get(0);
			// merkeKunde = kunde;

			// if (kunde.getRolle() == Rolle.ADMIN) {

			return "index";
			// } else {
			// return "index";
			// }

		} else {
			falschesPasswort(null);
			return null;
		}
	}

	/**
	 * 
	 * 
	 */
	public boolean loggedIn() {
		if (kunde == null) {
			return true;
		}
		return false;
	}

	public boolean loggedNotIn() {
		if (kunde != null) {
			return true;
		}
		return false;
	}

	public boolean checkAdmin() {
		if (kunde != null && kunde.getRolle() == Rolle.ADMIN) {
			return true;
		}
		return false;
	}

	/**
	 * manche Seiten sind ohne Persönliche Daten nicht zulässig. daher wird,
	 * bevor die gewünschte Seite aufgerufen wird erstmal geprüft ob Daten vom
	 * Objekt vorhanden sind
	 */
	public void checkLoggedIn(ComponentSystemEvent cse) {
		FacesContext context = FacesContext.getCurrentInstance();

		if (kunde == null) {
			context.getApplication().getNavigationHandler().handleNavigation(context, null, "login");
		}
	}

	/**
	 * wenn man sich abmeldet dann gelangt man zur Login Seite
	 */
	public String logout() {
		kunde = null;
		return "login";
	}

	// Methoden zum Verwalten der Sprache

	/**
	 * Die Klassen changeLang und wechselLang dienen der Änderung der Sprachen,
	 * durch das Klicken auf die jeweiligen Button //English// oder //Deutsch//
	 */
	public String changeLang() {
		FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(this.langCode));
		return null;
	}

	public String wechselLang() {
		FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(this.langerCode));
		return null;

	}

	// Getter und Setter

	public DataModel<ArtikelDaten> getArtikeln() {
		return artikeln;
	}

	public void setArtikeln(DataModel<ArtikelDaten> artikeln) {
		this.artikeln = artikeln;
	}

	// public Kreditkarte getMerkeKreditkarte() {
	// return merkeKreditkarte;
	// }
	//
	// public void setMerkeKreditkarte(Kreditkarte merkeKreditkarte) {
	// this.merkeKreditkarte = merkeKreditkarte;
	// }

	public DataModel<ArtikelDaten> getWarenkorb() {
		return warenkorb;
	}

	public void setWarenkorb(DataModel<ArtikelDaten> warenkorb) {
		this.warenkorb = warenkorb;
	}

	public ArtikelDaten getMerkeArtikel() {
		return merkeArtikel;
	}

	public void setMerkeArtikel(ArtikelDaten merkeArtikel) {
		this.merkeArtikel = merkeArtikel;
	}

	public DataModel<ArtikelDaten> getFilterArtikeln() {
		return filterArtikeln;
	}

	public void setFilterArtikeln(DataModel<ArtikelDaten> filterArtikeln) {
		this.filterArtikeln = filterArtikeln;
	}

	public ArtikelDaten getSelectedArtikel() {
		return selectedArtikel;
	}

	public void setSelectedArtikel(ArtikelDaten selectedArtikel) {
		this.selectedArtikel = selectedArtikel;
	}

	public double getGesamtpreis() {
		return gesamtpreis;
	}

	public void setGesamtpreis(double gesamtpreis) {
		this.gesamtpreis = gesamtpreis;
	}

	public double getGesamt() {
		return gesamt;
	}

	public void setGesamt(double gesamt) {
		this.gesamt = gesamt;
	}

	public int getGesamtanzahl() {
		return gesamtanzahl;
	}

	public void setGesamtanzahl(int gesamtanzahl) {
		this.gesamtanzahl = gesamtanzahl;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswort() {
		return passwort;
	}

	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}

	public Kunde getKunde() {
		return kunde;
	}

	public void setKunde(Kunde kunde) {
		this.kunde = kunde;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public String getLangCode() {
		return langCode;
	}

	public void setLangCode(String langCode) {
		this.langCode = langCode;
	}

	public String getLangerCode() {
		return langerCode;
	}

	public void setLangerCode(String langerCode) {
		this.langerCode = langerCode;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public DataModel<Kunde> getKunden() {
		return kunden;
	}

	public void setKunden(DataModel<Kunde> kunden) {
		this.kunden = kunden;
	}

	public Kunde getMerkeKunde() {
		return merkeKunde;
	}

	public void setMerkeKunde(Kunde merkeKunde) {
		this.merkeKunde = merkeKunde;
	}

	public Anrede[] getAnredeValues() {
		return Anrede.values();
	}

}