package com.dummy.myerp.model.bean.comptabilite;

import java.util.ArrayList;
import java.util.List;


import org.junit.Assert;
import org.junit.Test;

public class JournalComptableTest {
	
	@Test
	public void getByCode() {
		
		JournalComptable j1= new JournalComptable("code1","libelle1");
		JournalComptable j2= new JournalComptable("code2","libelle2");
		JournalComptable j3= new JournalComptable("code3","libelle3");
		
		List<JournalComptable> list= new ArrayList<JournalComptable>();
		list.add(j1);
		list.add(j2);
		list.add(j3);
		
		JournalComptable j4=JournalComptable.getByCode(list,"code1");
		Assert.assertTrue(j4.getCode().equals("code1"));
		Assert.assertFalse(j4.getCode().equals("code2"));
		
		
	}
	
	
	
	
	
	
	/*public static JournalComptable getByCode(List<? extends JournalComptable> pList, String pCode) {
        JournalComptable vRetour = null;
        for (JournalComptable vBean : pList) {
            if (vBean != null && Objects.equals(vBean.getCode(), pCode)) {
                vRetour = vBean;
                break;
            }
        }
        return vRetour;
    }*/

}
