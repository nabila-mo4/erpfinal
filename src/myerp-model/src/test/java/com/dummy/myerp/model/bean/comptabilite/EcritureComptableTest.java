package com.dummy.myerp.model.bean.comptabilite;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Assert;
import org.junit.Test;


public class EcritureComptableTest {

    private LigneEcritureComptable createLigne(Integer pCompteComptableNumero, String pDebit, String pCredit) {
        BigDecimal vDebit = pDebit == null ? null : new BigDecimal(pDebit);
        BigDecimal vCredit = pCredit == null ? null : new BigDecimal(pCredit);
        String vLibelle = ObjectUtils.defaultIfNull(vDebit, BigDecimal.ZERO)
                                     .subtract(ObjectUtils.defaultIfNull(vCredit, BigDecimal.ZERO)).toPlainString();
        LigneEcritureComptable vRetour = new LigneEcritureComptable(new CompteComptable(pCompteComptableNumero),
                                                                    vLibelle,
                                                                    vDebit, vCredit);
        return vRetour;
    }

    @Test
    public void isEquilibree() {
        EcritureComptable vEcriture;
        vEcriture = new EcritureComptable();
        vEcriture.setLibelle("Equilibrée");
        vEcriture.getListLigneEcriture().add(this.createLigne(1, "200.50", null));
		vEcriture.getListLigneEcriture().add(this.createLigne(1, "100.50", "33"));
		vEcriture.getListLigneEcriture().add(this.createLigne(2, null, "301"));
		vEcriture.getListLigneEcriture().add(this.createLigne(2, "40", "7"));
       
       Assert.assertTrue(vEcriture.toString(), vEcriture.isEquilibree());
        
         
        vEcriture.getListLigneEcriture().clear();
        vEcriture.setLibelle("Non équilibrée");
        vEcriture.getListLigneEcriture().add(this.createLigne(1, "10", null));
        vEcriture.getListLigneEcriture().add(this.createLigne(1, "20", "1"));
        vEcriture.getListLigneEcriture().add(this.createLigne(2, null, "30"));
        vEcriture.getListLigneEcriture().add(this.createLigne(2, "1", "2"));
        
        
        Assert.assertFalse(vEcriture.toString(), vEcriture.isEquilibree());
    	
    	
    }
    
    
    @Test
    public void getTotalDebit() {
    	
    	EcritureComptable vEcriture;
        vEcriture = new EcritureComptable();
        vEcriture.setLibelle("Ecriture1");
        vEcriture.getListLigneEcriture().add(this.createLigne(1, "100", null));
		vEcriture.getListLigneEcriture().add(this.createLigne(1, "200.50", "33"));
		vEcriture.getListLigneEcriture().add(this.createLigne(2, "30", "301"));
		vEcriture.getListLigneEcriture().add(this.createLigne(2, "40", "7"));
       
       Assert.assertTrue(vEcriture.toString(), vEcriture.getTotalDebit().equals(new BigDecimal("370.50")));
        
         
        vEcriture.getListLigneEcriture().clear();
        vEcriture.setLibelle("Ecriture");
        vEcriture.getListLigneEcriture().add(this.createLigne(1, "10", null));
        vEcriture.getListLigneEcriture().add(this.createLigne(1, "20", "1"));
        vEcriture.getListLigneEcriture().add(this.createLigne(2, null, "30"));
        vEcriture.getListLigneEcriture().add(this.createLigne(2, "60", "2"));
        
        
        Assert.assertFalse(vEcriture.toString(), vEcriture.getTotalDebit().equals(new BigDecimal("50")));
    	
    	
    	
    }
    
    
    
    @Test
    public void getTotalCredit() {
    	
    	EcritureComptable vEcriture;
        vEcriture = new EcritureComptable();
        vEcriture.setLibelle("Ecriture2");
        vEcriture.getListLigneEcriture().add(this.createLigne(1, "100", "33"));
		vEcriture.getListLigneEcriture().add(this.createLigne(1, "200.50", "33"));
		
       
       Assert.assertTrue(vEcriture.toString(), vEcriture.getTotalCredit().equals(new BigDecimal("66")));
        
         
        vEcriture.getListLigneEcriture().clear();
        vEcriture.setLibelle("Ecriture3");
        vEcriture.getListLigneEcriture().add(this.createLigne(1, "10", "41"));
        vEcriture.getListLigneEcriture().add(this.createLigne(1, "20", "1"));
        
        
        
        Assert.assertFalse(vEcriture.toString(), vEcriture.getTotalCredit().equals(new BigDecimal("40")));
    	
    	
    	
    }
    
    @Test
    public void toStringTest() {
        
        EcritureComptable vEcriture;
        vEcriture = new EcritureComptable();
        //Integer id=5;
        //String reference="reference1";
        //Date date = DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH);
        //String libelle="libelle1";
        vEcriture.setId(5);
        vEcriture.setReference("reference1");
        vEcriture.setDate(DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH));
        vEcriture.setLibelle("libelle1");
        vEcriture.getListLigneEcriture().add(this.createLigne(1, "200.50", null));
		vEcriture.getListLigneEcriture().add(this.createLigne(1, "100.50", "33"));
		JournalComptable j=new JournalComptable("codej","libellej");
		j.setCode("codej");
		
        System.out.println(vEcriture);
        String text="";
        
        Assert.assertTrue("correct", vEcriture.toString().equals(text));
    }
    
    

    

}
