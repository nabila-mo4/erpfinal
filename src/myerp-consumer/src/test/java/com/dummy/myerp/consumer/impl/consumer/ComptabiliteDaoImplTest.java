package com.dummy.myerp.consumer.impl.consumer;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;


import com.dummy.myerp.consumer.dao.impl.db.dao.ComptabiliteDaoImpl;
import com.dummy.myerp.model.bean.comptabilite.CompteComptable;
import com.dummy.myerp.model.bean.comptabilite.EcritureComptable;
import com.dummy.myerp.model.bean.comptabilite.JournalComptable;
import com.dummy.myerp.model.bean.comptabilite.LigneEcritureComptable;
import com.dummy.myerp.technical.exception.FunctionalException;
import com.dummy.myerp.technical.exception.NotFoundException;

import dummy.myerp.testconsumer.consumer.ConsumerTestCase;



public class ComptabiliteDaoImplTest extends ConsumerTestCase {
	
	private ComptabiliteDaoImpl dao = new ComptabiliteDaoImpl();

	
	@Test
	public void getListCompteComptable() {
		
		List<CompteComptable> cptliste = getDaoProxy().getComptabiliteDao().getListCompteComptable();
		Assert.assertNotNull(cptliste);
	
	}
	
	@Test
	public void getListJournalComptable() {
		List<JournalComptable> jrnliste = getDaoProxy().getComptabiliteDao().getListJournalComptable();
		Assert.assertNotNull(jrnliste);
		
	}
	
	@Test
	public void getListEcritureComptable() {
		List<EcritureComptable> ecriturelist = getDaoProxy().getComptabiliteDao().getListEcritureComptable();
		Assert.assertNotNull(ecriturelist);
		
	}
	
	@Test
	public void getEcritureComptable() throws ParseException {
		
			EcritureComptable ecriturecpt;
			try {
				ecriturecpt = getDaoProxy().getComptabiliteDao().getEcritureComptable(-2);
				Assert.assertEquals("VE", ecriturecpt.getJournal().getCode());
				Assert.assertEquals("VE-2016/00002", ecriturecpt.getReference());
				SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
				Date date = format.parse("30-12-2016");
				Assert.assertEquals(date, ecriturecpt.getDate());
				Assert.assertEquals("TMA Appli Xxx", ecriturecpt.getLibelle());
				Assert.assertNotNull(ecriturecpt.getListLigneEcriture());
				Assert.assertNotNull(ecriturecpt);
			} 
			catch (NotFoundException e) {
				System.out.println("ecriture comptable introuvable");
				e.printStackTrace();
			}
			
			
			
			
	}
	
	
	@Test
	public void getEcritureComptableByRef() throws ParseException {
		
			EcritureComptable ecriturecpt;
			try {
				ecriturecpt = getDaoProxy().getComptabiliteDao().getEcritureComptableByRef("VE-2016/00002");
				Assert.assertNotNull(ecriturecpt);
				Assert.assertEquals("VE", ecriturecpt.getJournal().getCode());
				Assert.assertEquals("VE-2016/00002", ecriturecpt.getReference());
				SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
				Date date = format.parse("30-12-2016");
				Assert.assertEquals(date, ecriturecpt.getDate());
				Assert.assertEquals("TMA Appli Xxx", ecriturecpt.getLibelle());
				Assert.assertNotNull(ecriturecpt.getListLigneEcriture());
			} 
			
			catch (NotFoundException e) {
				System.out.println("ecriture comptable non trouvee");
				e.printStackTrace();
			}
			
			
	}
	
	@Test
    public void insertEcritureComptable() {
    	
    		EcritureComptable vEcritureComptable;
            vEcritureComptable = new EcritureComptable();
            vEcritureComptable.setJournal(new JournalComptable("VE", "Vente"));
            vEcritureComptable.setDate(new Date());
            vEcritureComptable.setLibelle("Exemple Ecriture Compta1");
            SimpleDateFormat formater = new SimpleDateFormat("yyyy");
            String refannee= formater.format(vEcritureComptable.getDate());
            vEcritureComptable.setReference(vEcritureComptable.getJournal().getCode()+"-"+refannee+"/00011");
            vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(606),
                                                                                     null, new BigDecimal(200), null));
            vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(606),
                                                                                     null, null, new BigDecimal(100)));
            
            
		    dao.insertEcritureComptable(vEcritureComptable);
			
            Assert.assertNotNull(vEcritureComptable.getId());
    	
    }
	
	@Test
    public void updateEcritureComptable() 
	{
    	
			List<EcritureComptable> vEcritureComptableList = getDaoProxy().getComptabiliteDao().getListEcritureComptable();
			for(EcritureComptable vEcritureComptable : vEcritureComptableList) 
			{
				if(vEcritureComptable.getId()==-3) {
					vEcritureComptable.setLibelle("example3");
					getDaoProxy().getComptabiliteDao().updateEcritureComptable(vEcritureComptable);
					Assert.assertTrue("mise a jour reussie","example3".equals(vEcritureComptable.getLibelle()));
				}
			}
	
    }
	
	
	 
		 
		 
		 
		 
		 
	
	 
	 
	 }
	
