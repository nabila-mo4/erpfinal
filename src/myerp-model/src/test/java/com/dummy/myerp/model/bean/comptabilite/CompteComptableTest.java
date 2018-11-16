package com.dummy.myerp.model.bean.comptabilite;

import java.util.ArrayList;

import java.util.List;

import org.junit.Assert;

import org.junit.Test;



public class CompteComptableTest {
	
	 
	
	  @Test
	  public   void getByNumero() {
		  
		 
		 
		 CompteComptable c1= new CompteComptable(1);
		  CompteComptable c2= new CompteComptable(2);
		  CompteComptable c3= new CompteComptable(3);
		  List<CompteComptable> pList=new ArrayList<>();
		 
			 
		  pList.add(c1);
		  pList.add(c2);
		  pList.add(c3);
		 
		  CompteComptable compte= CompteComptable.getByNumero(pList,1);
		  CompteComptable compte1= CompteComptable.getByNumero(pList,2);
		 Assert.assertTrue("correct", compte.getNumero()==1);
		 Assert.assertFalse("incorrect", compte1.getNumero()==3);
		  
		  
	  }
	 /* public static CompteComptable getByNumero(List<? extends CompteComptable> pList, Integer pNumero) {
	        CompteComptable vRetour = null;
	        for (CompteComptable vBean : pList) {
	            if (vBean != null && Objects.equals(vBean.getNumero(), pNumero)) {
	                vRetour = vBean;
	                break;
	            }
	        }
	        return vRetour;
	    }
*/
}
