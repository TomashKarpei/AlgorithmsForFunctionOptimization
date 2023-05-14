import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public class AlgorithmOptimization{
	
    static double funkcja(double x, int A, double w){
        double fx = x*x - A*Math.cos(w*x);

        return fx;
    }
    
    static void wypiszFx(double ffx, int lWym, int i){
        System.out.print("f(");
            for (int p = 1; p <= lWym; p++) {
                if (p == lWym) {
                    System.out.print("x" + p + ") = ");
                }
                else {
                    System.out.print("x" + p + ", ");
                }
            }
            System.out.println(ffx);
    }
    
//////////////////////////////////////////////Turniejowa
    
    static void Turniejowa (int mm, int n, double ffx[],  double [][] x, int lWym, double ffxSum, int t[], double liczbaD[][], int[][][] populacja, int sumaT, int A, double w, int[] a, int[] b,  boolean suk, int ilIter, int nrIter) {
    	Random rand = new Random();
    	int sortMin = 3;
    	double srednia = 0;
        double LosLiczby[] = new double [sortMin];
        double TurnLiczby[] = new double [n];
        double liczbaDTurn[][] = new double [n][lWym];
        int populacjaTurn[][][] = new int [n][lWym][sumaT];
        if (mm == 0) {
        	if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {
        		System.out.println("//////////////////////////////////Selekcja turniejowa Minimum: ");
        	}
            for (int i = 0; i < n; i++) {
            	int xxx;
            	//System.out.println((i+1) + ") Wybor miedzy: ");
            	for (int j = 0; j < sortMin; j++) {
            		xxx = rand.nextInt(n);
            		LosLiczby[j] = ffx[xxx];
            		if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {
            			System.out.println("Nr " + (xxx+1) + " - " + LosLiczby[j]);
            		}
            	}
            	Arrays.sort(LosLiczby);
            	TurnLiczby[i] = LosLiczby[0];
            	for (int k = 0; k < n; k++) {
            		if (TurnLiczby[i] == ffx[k]) {
            			if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {
            				System.out.print((i+1) + ". " );
            			}
            			for (int wym = 0; wym < lWym; wym++) {
                            for (int j = 0; j < t[wym]; j++) {
                            	if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {
                            		System.out.print(populacja[k][wym][j]);
                            	}
                                populacjaTurn[i][wym][j] = populacja[k][wym][j];
                                liczbaDTurn[i][wym] += populacjaTurn[i][wym][j] * Math.pow(2, t[wym] - j - 1);
                            }
                            if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {
                            	System.out.print(" ");
                            }
                        }
            			if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {
            				System.out.println(" = " + TurnLiczby[i]);
            				srednia += TurnLiczby[i];
            			}
            		}
            	}
            }
        }
        else {
        	if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {
        		System.out.println("////////////////////////////////////////////Selekcja turniejowa Maximum: ");
        	}
            for (int i = 0; i < n; i++) {
            	int xxx;
            	if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {
            		System.out.println((i+1) + ") Wybor miedzy: ");
            	}
            	for (int j = 0; j < sortMin; j++) {
            		xxx = rand.nextInt(n);
            		LosLiczby[j] = ffx[xxx];
            		if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {
            			System.out.println("Nr " + (xxx+1) + " - " + LosLiczby[j]);
            		}
            	}
            	Arrays.sort(LosLiczby);
            	TurnLiczby[i] = LosLiczby[sortMin-1];
            	for (int k = 0; k < n; k++) {
            		if (TurnLiczby[i] == ffx[k]) {
            			if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {
            				System.out.print((i+1) + ". " );
            			}
            			for (int wym = 0; wym < lWym; wym++) {
                            for (int j = 0; j < t[wym]; j++) {
                            	if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {
                            		System.out.print(populacja[k][wym][j]);
                            	}
                                populacjaTurn[i][wym][j] = populacja[k][wym][j];
                                liczbaDTurn[i][wym] += populacjaTurn[i][wym][j] * Math.pow(2, t[wym] - j - 1);
                            }
                            if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {
                            	System.out.print(" ");
                            }
                        }
            			if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {            				
            				System.out.println(" = " + TurnLiczby[i]);
            				srednia += TurnLiczby[i];
            			}
            		}
            	}
            }
        }
        if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {
        	System.out.println("Srednia = " + (srednia/n));
        }
        if (suk == true) {
        	Mutacja (n, TurnLiczby, x, ffxSum, lWym, t, liczbaDTurn, populacjaTurn, sumaT, A, w, a, b, true, ilIter, nrIter,mm);  
        }
    }
    
//////////////////////////////////////////////Rankingowa
    
    static void Rankingowa(int l, int n, double ffx[],  double [][] x, int lWym, double ffxSum, int t[], double liczbaD[][], int[][][] populacja, int sumaT, int A, double w, int[] a, int[] b,  boolean suk, int ilIter, int nrIter) {
    	Random rand = new Random();
    	int sortMin = 2;
    	double srednia = 0;
        double RankLiczby[] = new double [n];
        if (l == 0) {
        	if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {
        		System.out.println("/////////////////////////////////////////Selekcja rankingowa Minimum: ");
        	}
        	for (int i = 0; i < n; i++) {
        		RankLiczby[i] = ffx[i];
        	}
        	Arrays.sort(RankLiczby);
        	if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {
	        	System.out.println("Po sortowaniu minimum: ");
	        	for (int i = 0; i < n; i++) {
	        		System.out.println((i+1) + " - " + RankLiczby[i]);
	        	}
        	}
        	
        	for (int i = 0; i < n; i++) {
        		int xxx = rand.nextInt(1+rand.nextInt(n));
	        		System.out.print((xxx+1) + ". " );
	    			for (int wym = 0; wym < lWym; wym++) {
	                    for (int j = 0; j < t[wym]; j++) {
	                        System.out.print(populacja[xxx][wym][j]);
	                        liczbaD[xxx][wym] += populacja[xxx][wym][j] * Math.pow(2, t[wym] - j - 1);
	                    }
	                    System.out.print(" ");
	                }
	    			System.out.println(" = " + RankLiczby[xxx]);   
	    			srednia += RankLiczby[xxx];
        	}
        }
        else {
        	System.out.println("////////////////////////////////////Selekcja rankingowa Maximum: ");
        	for (int i = 0; i < n; i++) {
        		RankLiczby[i] = ffx[i];
        	}
        	Arrays.sort(RankLiczby);
        	double RankLMax [] = new double [n];
        	int p = 0;
        	for (int i = n; i > 0; i--) {
        		RankLMax[i-1] = RankLiczby[p];
        		p++;
        	}
        	System.out.println("Po sortowaniu maximum: ");
        	for (int i = 0; i < n; i++) {
        		System.out.println((i+1) + " - " + RankLMax[i]);
        	}
        	for (int i = 0; i < n; i++) {
        		int xxx = rand.nextInt(1+rand.nextInt(n));
	        		System.out.print((xxx+1) + ". " );
	    			for (int wym = 0; wym < lWym; wym++) {
	                    for (int j = 0; j < t[wym]; j++) {
	                        System.out.print(populacja[xxx][wym][j]);
	                        liczbaD[xxx][wym] += populacja[xxx][wym][j] * Math.pow(2, t[wym] - j - 1);
	                    }
	                    System.out.print(" ");
	                }
	    			System.out.println(" = " + RankLMax[xxx]);
	    			srednia += RankLMax[xxx];
        	}
        }
        if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {
        	System.out.println("Srednia = " + (srednia/n));
        }
        if (suk == true) {
        	Mutacja (n, ffx, x, ffxSum, lWym, t, liczbaD, populacja, sumaT, A, w, a, b, true, ilIter, nrIter, l);  
        }
		
	}

///////////////////////////////////////////////Ruletki    
    
    static void Ruletki(int l, int n, double ffx[],  double [][] x, int lWym, double ffxSum, int t[], double liczbaD[][], int[][][] populacja, int sumaT, int A, double w, int[] a, int[] b,  boolean suk, int ilIter, int nrIter) {
    	Random rand = new Random();
    	double p []  = new double [n];
    	double q []  = new double [n];
    	double r []  = new double [n];
    	int rulet []  = new int [n];
    	double srednia = 0;
    	System.out.println("\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\Selekcja Ruletki: ");
    	for (int i = 0; i < n; i++) {
    		p[i] = ffx[i]/ffxSum;
    		//System.out.println(" P = " + p[i]); 
    		if (i == 0) {
    			q[i] = p[i];
    		}
    		else {
    			q[i] = p[i] + q[i-1];
    		}
    		
    		//System.out.println(" Q = " + q[i]);  
    	}
    	for (int i = 0; i < n; i++) {
    		r[i] = ((double) rand.nextInt(100))/100;
    		//System.out.println(" Nr = " + r[i]);
    		int j = 1;
    		while(j <= n) {
    			if (r[i] > q[j-1] && r[i] <= q[j]) {
    				rulet[i]= j;
    				//System.out.println(" R = " + rulet[i]);  
    				break;
    			}
    			else {
    				j++;
    			}
    		}
    	}
    	for (int i = 0; i < n; i++) {
    		System.out.println((i+1) + " - Wymiar " + (rulet[i]+1));
    		for (int wym = 0; wym < lWym; wym++) {
                for (int j = 0; j < t[wym]; j++) {
                    System.out.print(populacja[rulet[i]][wym][j]);
                    liczbaD[rulet[i]][wym] += populacja[rulet[i]][wym][j] * Math.pow(2, t[wym] - j - 1);
                }
                System.out.print(" ");
            }
    		System.out.println(" = " + ffx[rulet[i]]); 
    		srednia += ffx[rulet[i]];
    	}
    	if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {
        	System.out.println("Srednia = " + (srednia/n));
        }
    	if (suk == true) {
    		Mutacja (n, ffx, x, ffxSum, lWym, t, liczbaD, populacja, sumaT, A, w, a, b, true, ilIter, nrIter, l);  
    	}
    }
    
//////////////////////////////////////////////Mutacja
    
    static void Mutacja(int n, double[] ffx,  double [][] x, double ffxSum, int lWym, int[] t, double[][] liczbaD, int[][][] populacja, 
    int sumaT, int A, double w, int[] a, int[] b, boolean suk, int ilIter, int nrIter, int mm) {
    	if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {
    	 System.out.println("\n\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\Mutacja: ");
    	}
    	 Random rand = new Random();
         double pm = 0.2;
 		double r []  = new double [sumaT];
 		double ffxMut []  = new double [n];
 		double xMut [][]  = new double [n][lWym];
 		int populacjaMut [][][]  = new int [n][lWym][sumaT];
 		double liczbaDMut [][] = new double [n][lWym];
 		double srednia = 0;
 		for (int i = 0; i < n; i++) {
     		
     		for (int wym = 0; wym < lWym; wym++) {


                 for (int j = 0; j < t[wym]; j++) {
                 	r[j] = ((double) rand.nextInt(100))/100;
             		//System.out.println(" Nr = " + r[j]);
             		if (r[j] < pm) {
             			if(populacja[i][wym][j] == 0) {
             				populacjaMut[i][wym][j] = 1;
             			}
             			else {
             				populacjaMut[i][wym][j] = 0;
             			}
             			if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {
             				System.out.print(populacjaMut[i][wym][j]);
             			}
                         liczbaDMut[i][wym] += populacjaMut[i][wym][j]*Math.pow(2,t[wym]-j-1);
             		}
             		else {
             			populacjaMut[i][wym][j] = populacja[i][wym][j];
             			if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {
             				System.out.print(populacjaMut[i][wym][j]);
             			}
                         liczbaDMut[i][wym] += populacjaMut[i][wym][j]*Math.pow(2,t[wym]-j-1);
             		}
                 }
                 if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {
                	 System.out.print(" ");
                 }
                 //System.out.println("x': " +liczbaD[i][wym]);
                 //System.out.println("Liczba x'" + (i+1) +"." + (wym+1) +" : " + liczbaD);
                 xMut[i][wym] = a[wym] + ((b[wym]-a[wym])*liczbaDMut[i][wym])/(Math.pow(2, t[wym]) - 1);
                 //System.out.println("X =  " + x[i][wym]);

                 ffxMut[i] += funkcja(xMut[i][wym], A, w);
             }
             ffxMut[i] += A*lWym;
             srednia += ffxMut[i];
             if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {
	             System.out.println("\nBez Mutacji: ");
	             wypiszFx(ffx[i], lWym, i);
	             System.out.println("Po mutacji: ");
	             wypiszFx(ffxMut[i], lWym, i);
	             System.out.println();
             }
     	}
 		System.out.println("Srednia: " + (srednia/n));
 		if (suk == true) {
 			if (nrIter < ilIter-1) {
 				nrIter++;
 			} 			
        	Inwersja (n, ffxMut, ffxSum, lWym, t, liczbaDMut, populacjaMut, sumaT, A, w, a, b, true, ilIter, nrIter, mm);  
        }
 		if(nrIter < ilIter && suk == false){
 			nrIter++;
 	 		Mutacja (n, ffxMut, x, ffxSum, lWym, t, liczbaDMut, populacjaMut, sumaT, A, w, a, b, false, ilIter, nrIter, mm);
 	 		//Inwersja (n, ffxMut, ffxSum, lWym, t, liczbaDMut, populacjaMut, sumaT, A, w, a, b, false, ilIter, nrIter, mm);
 	 		//KrzyzRown (n,2, ffxMut, xMut, ffxSum, lWym, t, liczbaDMut, populacjaMut, sumaT, A, w, a, b, false, ilIter, nrIter, mm);
 		}
    }

////////////////////////////////////inwersja
    
    static void Inwersja(int n, double[] ffx, double ffxSum, int lWym, int[] t, double[][] liczbaD, int[][][] populacja, 
    	    int sumaT, int A, double w, int[] a, int[] b, boolean suk, int ilIter, int nrIter, int mm) {
    	if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {
    		System.out.println("\n//////////////////////////////////////////Inwersja: ");
    	}
   	    Random rand = new Random();
        double pi = 0.2;
        double srednia = 0;
		double r []  = new double [n];
		int pa []  = new int [n];
		int pb []  = new int [n];
		double ffxInw []  = new double [n];
		double xInw [][]  = new double [n][lWym];
		int populacjaInw [][][]  = new int [n][lWym][sumaT];
		int populacjaX [][]  = new int [n][sumaT];
		double liczbaDInw [][] = new double [n][lWym];
		for (int i = 0; i < n; i++) {
			int o = 0;
			for (int wym = 0; wym < lWym; wym++) {
				for (int j = 0; j < t[wym]; j++) {
					
					populacjaX [i][o] = populacja [i][wym][j];
					o++;
				}
			}
		}

		
		for (int i = 0; i < n; i++) {
			int wybrP = 0;
			int odejm = 0;
			r[i] = ((double) rand.nextInt(100))/100;
			//System.out.println(" Nr = " + r[wym]);
			if (r[i] < pi) {	 			

	 	    int p [] = new int [3];        
	 	       Set<Integer>set2 = new LinkedHashSet<Integer>();
     	      while (set2.size() < 2) {
     	         set2.add(rand.nextInt(sumaT-2)+1);
     	      }
     	      ArrayList<Integer> al = new ArrayList<>(set2);
     	      Collections.sort(al);
     	      int o = 0;
     	      for (int lczb : al) {     	    	  
     	    	p[o] = lczb;
     	    	o++;
     	      }
	 	      pa[i]= p[0];
	 	      pb[i]= p[1];
	 	     if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {
	 			System.out.println("Punkt A: " + (pa[i]+1) + " && Punkt B: " + (pb[i]+1));
	 	     }
			}
			
			int o = 0;
    		for (int wym = 0; wym < lWym; wym++) {
    			
    			if (r[i] < pi) {
         			
         			for (int j = 0; j < t[wym]; j++) {
         				if(wybrP == pa[i] || wybrP-1 == pb[i]) {
         					if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {
         						System.out.print(" | ");
         					}
         				}
         				if(wybrP >= pa[i] && wybrP <= pb[i]) {
         					
             				populacjaInw[i][wym][j] = populacjaX[i][pb[i]-odejm];
             				odejm++;
             				wybrP++;
             			}
             			else {
             				populacjaInw[i][wym][j] = populacjaX[i][o];
             				wybrP++;
             			}
         				o++;
         				if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {
         					System.out.print(populacjaInw[i][wym][j]);
         				}
                         liczbaDInw[i][wym] += populacjaInw[i][wym][j]*Math.pow(2,t[wym]-j-1);
         			}
         			if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {
         				System.out.print(" ");
         			}
         		}
    			else {
    				
    				for (int j = 0; j < t[wym]; j++) {
             			populacjaInw[i][wym][j] = populacja[i][wym][j];
             			//System.out.print(populacjaInw[i][wym][j]);
                         liczbaDInw[i][wym] += populacjaInw[i][wym][j]*Math.pow(2,t[wym]-j-1);
             		
                	}
    			}
    			
                //System.out.println("x': " +liczbaD[i][wym]);
                //System.out.println("Liczba x'" + (i+1) +"." + (wym+1) +" : " + liczbaD);
                xInw[i][wym] = a[wym] + ((b[wym]-a[wym])*liczbaDInw[i][wym])/(Math.pow(2, t[wym]) - 1);
                //System.out.println("X =  " + x[i][wym]);

                ffxInw[i] += funkcja(xInw[i][wym], A, w);
            }
            ffxInw[i] += A*lWym;
            srednia +=ffxInw[i];
            //System.out.println("Bez Inwersji: ");
           // wypiszFx(ffx[i], lWym, i);
            if (r[i] < pi){
            	if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {
	            	System.out.println("");
	            	System.out.println("Po Inwersji chromosomu nr " + (i+1) + " : ");
	                wypiszFx(ffxInw[i], lWym, i);
	                System.out.println();
            	}
            }
            
    	}
		System.out.println("Srednia: " + (srednia/n));
		if (suk == true) {
			if (nrIter < ilIter-1) {
				nrIter++;
			}			
			KrzyzRown (n,2, ffxInw, xInw, ffxSum, lWym, t, liczbaDInw, populacjaInw, sumaT, A, w, a, b, true, ilIter, nrIter, mm);
        }
		if(nrIter < ilIter && suk == false){
			nrIter++;
			//KrzyzRown (n,2, ffxInw, xInw, ffxSum, lWym, t, liczbaDInw, populacjaInw, sumaT, A, w, a, b, false, ilIter, nrIter, mm);
			//Mutacja (n, ffxInw, xInw, ffxSum, lWym, t, liczbaDInw, populacjaInw, sumaT, A, w, a, b, false, ilIter, nrIter, mm); 
			Inwersja (n, ffxInw, ffxSum, lWym, t, liczbaDInw, populacjaInw, sumaT, A, w, a, b, false, ilIter, nrIter, mm);       	
        }
    }

    
////////////////////////////////////Krzyz1
    
	static void Krzyz1(int n, double[] ffx, double [][] x, double ffxSum, int lWym, int[] t, double[][] liczbaD, int[][][] populacja, 
	int sumaT, int A, double w, int[] a, int[] b, boolean suk, int ilIter, int nrIter, int mm) {
		System.out.println("\n//////////////////////////////////////////Krzyzowanie jednopunktowe: ");
		Random rand = new Random();
		double pk = 0.6;
		int ilOsob = 0;
		double r []  = new double [sumaT];
		int NrK []  = new int [n];
		int p []  = new int [n];
		double ffxKrz []  = new double [n];
		double xKrz [][]  = new double [n][lWym];
		int populacjaKrz [][][]  = new int [n][lWym][sumaT];
		double liczbaDKrz [][] = new double [n][lWym];
		
		double ffxWyn []  = new double [n];
		double xWyn [][]  = new double [n][lWym];
		int populacjaWyn [][][]  = new int [n][lWym][sumaT];
		double liczbaDWyn [][] = new double [n][lWym];
		
		System.out.print("Wybrane osobniki o indeksie: ");
		for (int i = 0; i < n; i++) {
			r[i] = ((double) rand.nextInt(100))/100;
			//System.out.println(" Nr = " + r[wym]);
			if (r[i] < pk) {
				NrK [ilOsob] = i;
				System.out.print(" " + (NrK [ilOsob]+1));
				ilOsob++;
			}
		}
		if (ilOsob%2 == 1) {
			System.out.println();
			System.out.print("Dodany osobnik o indeksie: ");
			int i = 0;
			while (ilOsob%2 == 1 || i < n) {
				if (r[i] >= pk) {
					r[i] = r[i]-pk;
					NrK [ilOsob] = i;					
					System.out.print(" " + (NrK [ilOsob]+1));
					ilOsob++;
					break;
				}
				else {
					i++;
				}
			}
		}
		
		int LosLiczby[] = new int [ilOsob];
        int wybrPunkt = 0;
        Set<Integer>set = new LinkedHashSet<Integer>();
        while (set.size() < ilOsob) {
            set.add(rand.nextInt(ilOsob));
        }
        int i = 0;
        for (int lczb : set)
            LosLiczby[i++] = lczb;
        System.out.println();
        System.out.println("Random = "+set);
        i = 0;
        while(i != ilOsob){
            System.out.print(" "+LosLiczby[i]);
            i++;
        }
        System.out.println();
        for (i = 0; i < ilOsob; i++){
            //System.out.print("Wybrany punkt: ");
            wybrPunkt = 0;
        	if (i%2 == 0) {
	        		p[i] = 1+rand.nextInt(sumaT-2);
	        	}
	        	else {
	        		p[i] = p[i-1];
	        	}
        	for (int wym = 0; wym < lWym; wym++) {
	        	
	        	
	 			for (int j = 0; j < t[wym]; j++) {
	 				if (i%2 == 0) {
	 					if(wybrPunkt >= p[i]) {
	         				populacjaKrz[i][wym][j] = populacja[NrK [LosLiczby[i+1]]][wym][j];
	         				populacjaWyn[NrK [LosLiczby[i]]][wym][j] = populacjaKrz[i][wym][j];
	         				wybrPunkt++;
	         			}
	         			else {
	         				populacjaKrz[i][wym][j] = populacja[NrK [LosLiczby[i]]][wym][j];
	         				populacjaWyn[NrK [LosLiczby[i]]][wym][j] = populacjaKrz[i][wym][j];
	         				wybrPunkt++;
	         			}
	 				}
	 				else {
	 					if(wybrPunkt >= p[i]) {
	         				populacjaKrz[i][wym][j] = populacja[NrK [LosLiczby[i-1]]][wym][j];
	         				populacjaWyn[NrK [LosLiczby[i]]][wym][j] = populacjaKrz[i][wym][j];
	         				wybrPunkt++;
	         			}
	         			else {
	         				populacjaKrz[i][wym][j] = populacja[NrK [LosLiczby[i]]][wym][j];
	         				populacjaWyn[NrK [LosLiczby[i]]][wym][j] = populacjaKrz[i][wym][j];
	         				wybrPunkt++;
	         			}
	 				}
     				if (wybrPunkt-1 == p[i]) {
     					System.out.print(" | ");
     				}
                     System.out.print(populacjaKrz[i][wym][j]);
                     liczbaDKrz[i][wym] += populacjaKrz[i][wym][j]*Math.pow(2,t[wym]-j-1);
     			}
	 			System.out.print(" ");
	 			//System.out.println("x': " +liczbaDKrz[i][wym]);
                //System.out.println("Liczba x'" + (i+1) +"." + (wym+1) +" : " + liczbaDKrz);
                xKrz[i][wym] = a[wym] + ((b[wym]-a[wym])*liczbaDKrz[i][wym])/(Math.pow(2, t[wym]) - 1);
                //System.out.println("X =  " + xKrz[i][wym]);

                ffxKrz[i] += funkcja(xKrz[i][wym], A, w);
        	}
        	ffxKrz[i] += A*lWym;
        	if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {
        	System.out.println();
            	System.out.println("Po Krzyzowaniu chromosomu nr " + (NrK [LosLiczby[i]]+1) + " : ");
                wypiszFx(ffxKrz[i], lWym, i);
                
                System.out.println("Przed Krzyzowaniem chromosomu nr " + (NrK [LosLiczby[i]]+1) + " : ");
        	}
                    for (int wym = 0; wym < lWym; wym++) {
                        for (int j = 0; j < t[wym]; j++) {
                            
                            System.out.print(populacja[NrK [LosLiczby[i]]][wym][j]);
                        }
                        System.out.print(" ");
                    }
                    if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {
                    	wypiszFx(ffx[NrK [LosLiczby[i]]], lWym, i);
                    }
                
                    System.out.println();
        }
        
        for (i = 0; i < n; i++){
        	boolean spr = false;
        	for (int k = 0; k < ilOsob; k++){
        		if (i == NrK [LosLiczby[k]]) {
        			spr = true;
        		}        		
        	}
        	if (spr == false) {
        		for (int wym = 0; wym < lWym; wym++) {
        			
            		for (int j = 0; j < t[wym]; j++) {
            			populacjaWyn[i][wym][j] = populacja[i][wym][j];            			
            			liczbaDWyn[i][wym] += populacjaWyn[i][wym][j]*Math.pow(2,t[wym]-j-1);            			
            		}
            		xWyn[i][wym] = a[wym] + ((b[wym]-a[wym])*liczbaDWyn[i][wym])/(Math.pow(2, t[wym]) - 1);
            		ffxWyn[i] += funkcja(xWyn[i][wym], A, w);
            	}        		
        		ffxWyn[i] += A*lWym;
        	}
        	else if (spr == true) {
        		for (int wym = 0; wym < lWym; wym++) {
        			
            		for (int j = 0; j < t[wym]; j++) {
            			//populacjaWyn[i][wym][j] = populacjaKrz[NrK [LosLiczby[i]]][wym][j];            			
            			liczbaDWyn[i][wym] += populacjaWyn[i][wym][j]*Math.pow(2,t[wym]-j-1);            			
            		}
            		xWyn[i][wym] = a[wym] + ((b[wym]-a[wym])*liczbaDWyn[i][wym])/(Math.pow(2, t[wym]) - 1);
            		ffxWyn[i] += funkcja(xWyn[i][wym], A, w);
            	}        		
        		ffxWyn[i] += A*lWym;
        	}      
        }

        	double sred = 0;
        	if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {
        			System.out.println("Ocena populacji nr " + (nrIter+1) + " : ");
        	}
        	for (i = 0; i < n; i++){
        		if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {
        			System.out.print((i+1)+". Binarny: ");
        		}
        		for (int wym = 0; wym < lWym; wym++) {
        			for (int j = 0; j < t[wym]; j++) {
        				if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {
        					System.out.print(populacjaWyn[i][wym][j]);
        				}
        			}
        		}
        		if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {
        			System.out.print(" ");
        		}
        		sred += ffxWyn[i];
        		if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {
        			wypiszFx(ffxWyn[i], lWym, i);
        		}
            }
        	if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {
        		System.out.println("Srednia: " + sred/n);
        	}
        	nrIter++;
        	if (suk == true && nrIter < ilIter) {
        		Turniejowa (mm, n, ffxWyn, xWyn, lWym, ffxSum, t, liczbaDWyn, populacjaWyn, sumaT, A, w, a, b, true, ilIter, nrIter); 
        	}
        
  	}
    
	
/////////////////////////////Krzyz2
	
	static void Krzyz2(int n, int ilPunkt, double[] ffx, double [][] x, double ffxSum, int lWym, int[] t, double[][] liczbaD, int[][][] populacja, 
	int sumaT, int A, double w, int[] a, int[] b, boolean suk, int ilIter, int nrIter, int mm) {
		System.out.println("\n//////////////////////////////////////////Krzyzowanie dwupunktowe: ");
		Random rand = new Random();
		double pi = 0.6;
		int ilOsob = 0, losWym = 0;
		double r []  = new double [sumaT];
		int NrK []  = new int [n];
		int p [][]  = new int [n][ilPunkt];
		double ffxKrz []  = new double [n];
		double xKrz [][]  = new double [n][lWym];
		int populacjaKrz [][][]  = new int [n][lWym][sumaT];
		double liczbaDKrz [][] = new double [n][lWym];
		System.out.print("Wybrane osobniki o indeksie: ");
		for (int i = 0; i < n; i++) {
			r[i] = ((double) rand.nextInt(100))/100;
			//System.out.println(" Nr = " + r[wym]);
			if (r[i] < pi) {
				NrK [ilOsob] = i;
				System.out.print(" " + (NrK [ilOsob]+1));
				ilOsob++;
			}
		}
		if (ilOsob%2 == 1) {
			System.out.println();
			System.out.print("Dodany osobnik o indeksie: ");
			int i = 0;
			while (ilOsob%2 == 1 || i < n) {
				if (r[i] >= pi) {
					r[i] = r[i]-pi;
					NrK [ilOsob] = i;					
					System.out.print(" " + (NrK [ilOsob]+1));
					ilOsob++;
					break;
				}
				else {
					i++;
				}
			}
		}
		
		double ffxWyn []  = new double [n];
		double xWyn [][]  = new double [n][lWym];
		int populacjaWyn [][][]  = new int [n][lWym][sumaT];
		double liczbaDWyn [][] = new double [n][lWym];
		
		
		int LosLiczby[] = new int [ilOsob];
        int wybrPunkt = 0;
        Set<Integer>set = new LinkedHashSet<Integer>();
        while (set.size() < ilOsob) {
            set.add(rand.nextInt(ilOsob));
        }
        int i = 0;
        for (int lczb : set)
            LosLiczby[i++] = lczb;
        System.out.println();
        System.out.println("Random = "+set);
        i = 0;
        while(i != ilOsob){
            System.out.print(" "+LosLiczby[i]);
            i++;
        }
        System.out.println();
        for (i = 0; i < ilOsob; i++){
            //System.out.print("Wybrany punkt: ");
            wybrPunkt = 0;
            losWym = 0;
        	if (i%2 == 0) {
	        		p[i][losWym] = 1+rand.nextInt(sumaT-4);
	        		p[i][losWym+1] = p[i][losWym]+rand.nextInt(sumaT-p[i][losWym]-1);
	        	}
	        	else {
	        		p[i][losWym] = p[i-1][losWym];
	        		p[i][losWym+1] = p[i-1][losWym+1];
	        	}
        	for (int wym = 0; wym < lWym; wym++) {
	        	
	        	
	 			for (int j = 0; j < t[wym]; j++) {
	 				if (i%2 == 0) {
	 					if(wybrPunkt >= p[i][losWym] && wybrPunkt < p[i][losWym+1]) {
	         				populacjaKrz[i][wym][j] = populacja[NrK [LosLiczby[i+1]]][wym][j];
	         				populacjaWyn[NrK [LosLiczby[i]]][wym][j] = populacjaKrz[i][wym][j];
	         				wybrPunkt++;
	         			}
	         			else {
	         				populacjaKrz[i][wym][j] = populacja[NrK [LosLiczby[i]]][wym][j];
	         				populacjaWyn[NrK [LosLiczby[i]]][wym][j] = populacjaKrz[i][wym][j];
	         				wybrPunkt++;
	         			}
	 				}
	 				else {
	 					if(wybrPunkt >= p[i][losWym] && wybrPunkt < p[i][losWym+1]) {
	         				populacjaKrz[i][wym][j] = populacja[NrK [LosLiczby[i-1]]][wym][j];
	         				populacjaWyn[NrK [LosLiczby[i]]][wym][j] = populacjaKrz[i][wym][j];
	         				wybrPunkt++;
	         			}
	         			else {
	         				populacjaKrz[i][wym][j] = populacja[NrK [LosLiczby[i]]][wym][j];
	         				populacjaWyn[NrK [LosLiczby[i]]][wym][j] = populacjaKrz[i][wym][j];
	         				wybrPunkt++;
	         			}
	 				}
     				if (wybrPunkt-1 == p[i][losWym] || wybrPunkt-1 == p[i][losWym+1]) {
     					System.out.print(" | ");
     				}
                     System.out.print(populacjaKrz[i][wym][j]);
                     liczbaDKrz[i][wym] += populacjaKrz[i][wym][j]*Math.pow(2,t[wym]-j-1);
     			}
	 			System.out.print(" ");
	 			//System.out.println("x': " +liczbaDKrz[i][wym]);
                //System.out.println("Liczba x'" + (i+1) +"." + (wym+1) +" : " + liczbaDKrz);
                xKrz[i][wym] = a[wym] + ((b[wym]-a[wym])*liczbaDKrz[i][wym])/(Math.pow(2, t[wym]) - 1);
                //System.out.println("X =  " + xKrz[i][wym]);

                ffxKrz[i] += funkcja(xKrz[i][wym], A, w);
        	}
        	ffxKrz[i] += A*lWym;
        	System.out.println();
            	System.out.println("Po Krzyzowaniu chromosomu nr " + (NrK [LosLiczby[i]]+1) + " : ");
                wypiszFx(ffxKrz[i], lWym, i);
                
                System.out.println("Przed Krzyzowaniem chromosomu nr " + (NrK [LosLiczby[i]]+1) + " : ");
                    for (int wym = 0; wym < lWym; wym++) {
                        for (int j = 0; j < t[wym]; j++) {  
                            System.out.print(populacja[NrK [LosLiczby[i]]][wym][j]);
                        }
                        System.out.print(" ");
                    }
                    wypiszFx(ffx[NrK [LosLiczby[i]]], lWym, i);
                
                    System.out.println();
        }
        
        for (i = 0; i < n; i++){
        	boolean spr = false;
        	for (int k = 0; k < ilOsob; k++){
        		if (i == NrK [LosLiczby[k]]) {
        			spr = true;
        		}        		
        	}
        	if (spr == false) {
        		for (int wym = 0; wym < lWym; wym++) {
        			
            		for (int j = 0; j < t[wym]; j++) {
            			populacjaWyn[i][wym][j] = populacja[i][wym][j];            			
            			liczbaDWyn[i][wym] += populacjaWyn[i][wym][j]*Math.pow(2,t[wym]-j-1);            			
            		}
            		xWyn[i][wym] = a[wym] + ((b[wym]-a[wym])*liczbaDWyn[i][wym])/(Math.pow(2, t[wym]) - 1);
            		ffxWyn[i] += funkcja(xWyn[i][wym], A, w);
            	}        		
        		ffxWyn[i] += A*lWym;
        	}
        	else if (spr == true) {
        		for (int wym = 0; wym < lWym; wym++) {
        			
            		for (int j = 0; j < t[wym]; j++) {
            			//populacjaWyn[i][wym][j] = populacjaKrz[NrK [LosLiczby[i]]][wym][j];            			
            			liczbaDWyn[i][wym] += populacjaWyn[i][wym][j]*Math.pow(2,t[wym]-j-1);            			
            		}
            		xWyn[i][wym] = a[wym] + ((b[wym]-a[wym])*liczbaDWyn[i][wym])/(Math.pow(2, t[wym]) - 1);
            		ffxWyn[i] += funkcja(xWyn[i][wym], A, w);
            	}        		
        		ffxWyn[i] += A*lWym;
        	}      
        }

        	double sred = 0;
        	if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {
        			System.out.println("Ocena populacji nr " + (nrIter+1) + " : ");
        	}
        	for (i = 0; i < n; i++){
        		if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {
        			System.out.print((i+1)+". Binarny: ");
        		}
        		for (int wym = 0; wym < lWym; wym++) {
        			for (int j = 0; j < t[wym]; j++) {
        				if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {
        					System.out.print(populacjaWyn[i][wym][j]);
        				}
        			}
        		}
        		if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {
        			System.out.print(" ");
        		}
        		sred += ffxWyn[i];
        		if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {
        			wypiszFx(ffxWyn[i], lWym, i);
        		}
            }
        	if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {
        		System.out.println("Srednia: " + sred/n);
        	}
        	nrIter++;
        	if (suk == true && nrIter < ilIter) {
        		Turniejowa (mm, n, ffxWyn, xWyn, lWym, ffxSum, t, liczbaDWyn, populacjaWyn, sumaT, A, w, a, b, true, ilIter, nrIter); 
        	}
        
  	}
    
///////////////////////////////KrzyzN
	static void KrzyzN(int n, int ilPunkt, double[] ffx, double [][] x, double ffxSum, int lWym, int[] t, double[][] liczbaD, int[][][] populacja, 
			int sumaT, int A, double w, int[] a, int[] b, boolean suk, int ilIter, int nrIter, int mm) {
				System.out.println("\n//////////////////////////////////////////Krzyzowanie n-punktowe: ");
				Random rand = new Random();
				double pk = 0.6;
				int ilOsob = 0, losWym = 0;
				double r []  = new double [n];
				int NrK []  = new int [n];
				int p [][]  = new int [n][sumaT];
				double ffxKrz []  = new double [n];
				double xKrz [][]  = new double [n][lWym];
				int populacjaKrz [][][]  = new int [n][lWym][sumaT];
				double liczbaDKrz [][] = new double [n][lWym];
				
				
				double ffxWyn []  = new double [n];
				double xWyn [][]  = new double [n][lWym];
				int populacjaWyn [][][]  = new int [n][lWym][sumaT];
				double liczbaDWyn [][] = new double [n][lWym];
				
				System.out.print("Wybrane osobniki o indeksie: ");
				for (int i = 0; i < n; i++) {
					r[i] = ((double) rand.nextInt(100))/100;
					//System.out.println(" Nr = " + r[wym]);
					if (r[i] < pk) {
						NrK [ilOsob] = i;
						System.out.print(" " + (NrK [ilOsob]+1));
						ilOsob++;
					}
				}
				if (ilOsob%2 == 1 && n%2 == 0) {
					System.out.println();
					System.out.print("Dodany osobnik o indeksie: ");
					int i = 0;
					while (ilOsob%2 == 1 || i < n) {
						if (r[i] >= pk) {
							r[i] = r[i]-pk;
							NrK [ilOsob] = i;					
							System.out.print(" " + (NrK [ilOsob]+1));
							ilOsob++;
							break;
						}
						else {
							i++;
						}
					}
				}
				else if (ilOsob%2 == 1 && n%2 == 1) {
					if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {
						System.out.println();
						System.out.print("Usuniety osobnik o indeksie: " + (NrK [ilOsob]+1));
					}
					ilOsob--;
				}
				
				int LosLiczby[] = new int [ilOsob];
		        int wybrPunkt = 0;
		        Set<Integer>set = new LinkedHashSet<Integer>();
		        while (set.size() < ilOsob) {
		            set.add(rand.nextInt(ilOsob));
		        }
		        int i = 0;
		        for (int lczb : set)
		            LosLiczby[i++] = lczb;
		        System.out.println();
		        System.out.println("Random = "+set);
		        i = 0;
		        while(i != ilOsob){
		            System.out.print(" "+LosLiczby[i]);
		            i++;
		        }
		        System.out.println();
		        for (i = 0; i < ilOsob; i++){
		        	
		            wybrPunkt = 0;
		            losWym = 0;
		            
		        	if (i%2 == 0) {
		        		ilPunkt = 1+rand.nextInt(sumaT-1);
		        		System.out.print("Rand: " +  ilPunkt + " ");
		        		/*for (int o = 0; o < ilPunkt; o++) {
		        			if (o == 0) {
		        				p[i][o] = 1+rand.nextInt((sumaT-ilPunkt)/ilPunkt+1 );
		        				System.out.print(" " + p[i][o]);
		        			}
		        			else {
		        				p[i][o] = 1+ p[i][o-1]+rand.nextInt(1+(sumaT-p[i][o-1])/(ilPunkt-ilPunkt/2));
		        				System.out.print(" " + p[i][o]);
		        			}
		        			
		        			
		        		}*/  
		        		
		        		Set<Integer>set2 = new LinkedHashSet<Integer>();
		        	      while (set2.size() < ilPunkt) {
		        	         set2.add(rand.nextInt(sumaT-1)+1);
		        	      }
		        	      ArrayList<Integer> al = new ArrayList<>(set2);
		        	      Collections.sort(al);
		        	      int o = 0;
		        	      for (int lczb : al) {
		        	    	  p[i][o++] = lczb;
		        	    	 //System.out.print(" " + p[i][o]);
		        	      }
		        	    	
		        	      
			        		
		        	      
			        }
			        else {
			        	for (int o = 0; o < ilPunkt; o++) {
			        		p[i][o] = p[i-1][o];
			        		//System.out.print(" " + p[i][o]);
			        	}
			        }
		        	if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {
		        		System.out.println("\nWybrany punkt: ");
		        	}
		        	for (int wym = 0; wym < lWym; wym++) {
			        	
			        	
			 			for (int j = 0; j < t[wym]; j++) {
			 				if (losWym+1 < ilPunkt) {
			 					if (wybrPunkt == p[i][losWym] && losWym+1 < ilPunkt) {
			 						if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {
			 							System.out.print("|");
			 						}
			     					losWym++;
			     				}
			 					if (i%2 == 0) {
			 						if(losWym%2 == 1) {
				         				populacjaKrz[i][wym][j] = populacja[NrK [LosLiczby[i+1]]][wym][j];
				         				populacjaWyn[NrK [LosLiczby[i]]][wym][j] = populacjaKrz[i][wym][j]; 
				         				wybrPunkt++;
				         			}
				         			else {
				         				populacjaKrz[i][wym][j] = populacja[NrK [LosLiczby[i]]][wym][j];
				         				populacjaWyn[NrK [LosLiczby[i]]][wym][j] = populacjaKrz[i][wym][j]; 
				         				wybrPunkt++;
				         			}
				 				}
				 				else {
				 					if(losWym%2 == 1) {
				         				populacjaKrz[i][wym][j] = populacja[NrK [LosLiczby[i-1]]][wym][j];
				         				populacjaWyn[NrK [LosLiczby[i]]][wym][j] = populacjaKrz[i][wym][j]; 
				         				wybrPunkt++;
				         			}
				         			else {
				         				populacjaKrz[i][wym][j] = populacja[NrK [LosLiczby[i]]][wym][j];
				         				populacjaWyn[NrK [LosLiczby[i]]][wym][j] = populacjaKrz[i][wym][j]; 
				         				wybrPunkt++;
				         			}
				 				}
			 					
			 					
			 				}
			 				else {
			 					if (wybrPunkt== p[i][losWym]) {
			 						if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {
			 							System.out.print("|");
			 						}
			     				}
			 					if (i%2 == 0) {
				 					if(wybrPunkt >= p[i][losWym] && losWym%2 == 0) {
				         				populacjaKrz[i][wym][j] = populacja[NrK [LosLiczby[i+1]]][wym][j];
				         				populacjaWyn[NrK [LosLiczby[i]]][wym][j] = populacjaKrz[i][wym][j]; 
				         				wybrPunkt++;
				         			}
				         			else {
				         				populacjaKrz[i][wym][j] = populacja[NrK [LosLiczby[i]]][wym][j];
				         				populacjaWyn[NrK [LosLiczby[i]]][wym][j] = populacjaKrz[i][wym][j]; 
				         				wybrPunkt++;
				         			}
				 				}
				 				else {
				 					if(wybrPunkt >= p[i][losWym] && losWym%2 == 0) {
				         				populacjaKrz[i][wym][j] = populacja[NrK [LosLiczby[i-1]]][wym][j];
				         				populacjaWyn[NrK [LosLiczby[i]]][wym][j] = populacjaKrz[i][wym][j];
				         				wybrPunkt++;
				         			}
				         			else {
				         				populacjaKrz[i][wym][j] = populacja[NrK [LosLiczby[i]]][wym][j];
				         				populacjaWyn[NrK [LosLiczby[i]]][wym][j] = populacjaKrz[i][wym][j]; 
				         				wybrPunkt++;
				         			}
				 				}
			 					
			 				}
			 				if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {
			 					System.out.print(populacjaKrz[i][wym][j]);
			 				}
		                     liczbaDKrz[i][wym] += populacjaKrz[i][wym][j]*Math.pow(2,t[wym]-j-1);
		     			}
			 			if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {
			 				System.out.print(" ");
			 			}
			 			//System.out.println("x': " +liczbaDKrz[i][wym]);
		                //System.out.println("Liczba x'" + (i+1) +"." + (wym+1) +" : " + liczbaDKrz);
		                xKrz[i][wym] = a[wym] + ((b[wym]-a[wym])*liczbaDKrz[i][wym])/(Math.pow(2, t[wym]) - 1);
		                //System.out.println("X =  " + xKrz[i][wym]);

		                ffxKrz[i] += funkcja(xKrz[i][wym], A, w);
		        	}
		        	ffxKrz[i] += A*lWym;
		        	
		        	if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {	
			        	System.out.println();
			            	System.out.println("Po Krzyzowaniu chromosomu nr " + (NrK [LosLiczby[i]]+1) + " : ");
			                wypiszFx(ffxKrz[i], lWym, i);
			                
			                System.out.println("Przed Krzyzowaniem chromosomu nr " + (NrK [LosLiczby[i]]+1) + " : ");
		        	}
		                    for (int wym = 0; wym < lWym; wym++) {

		                        for (int j = 0; j < t[wym]; j++) {
		                        	if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {
		                        		System.out.print(populacja[NrK [LosLiczby[i]]][wym][j]);
		                        	}
		                        }
		                        if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {
		                        	System.out.print(" ");
		                        }
		                    }
		                    if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {
			                    System.out.println();
			                    wypiszFx(ffx[NrK [LosLiczby[i]]], lWym, i);
			                    System.out.println();
		                    }
		                
		                    
		        }
		        for (i = 0; i < n; i++){
		        	boolean spr = false;
		        	for (int k = 0; k < ilOsob; k++){
		        		if (i == NrK [LosLiczby[k]]) {
		        			spr = true;
		        		}        		
		        	}
		        	if (spr == false) {
		        		for (int wym = 0; wym < lWym; wym++) {
		        			
		            		for (int j = 0; j < t[wym]; j++) {
		            			populacjaWyn[i][wym][j] = populacja[i][wym][j];            			
		            			liczbaDWyn[i][wym] += populacjaWyn[i][wym][j]*Math.pow(2,t[wym]-j-1);            			
		            		}
		            		xWyn[i][wym] = a[wym] + ((b[wym]-a[wym])*liczbaDWyn[i][wym])/(Math.pow(2, t[wym]) - 1);
		            		ffxWyn[i] += funkcja(xWyn[i][wym], A, w);
		            	}        		
		        		ffxWyn[i] += A*lWym;
		        	}
		        	else if (spr == true) {
		        		for (int wym = 0; wym < lWym; wym++) {
		        			
		            		for (int j = 0; j < t[wym]; j++) {
		            			//populacjaWyn[i][wym][j] = populacjaKrz[NrK [LosLiczby[i]]][wym][j];            			
		            			liczbaDWyn[i][wym] += populacjaWyn[i][wym][j]*Math.pow(2,t[wym]-j-1);            			
		            		}
		            		xWyn[i][wym] = a[wym] + ((b[wym]-a[wym])*liczbaDWyn[i][wym])/(Math.pow(2, t[wym]) - 1);
		            		ffxWyn[i] += funkcja(xWyn[i][wym], A, w);
		            	}        		
		        		ffxWyn[i] += A*lWym;
		        	}      
		        }

		        	double sred = 0;
		        	if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {
		        			System.out.println("Ocena populacji nr " + (nrIter+1) + " : ");
		        	}
		        	for (i = 0; i < n; i++){
		        		if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {
		        			System.out.print((i+1)+". Binarny: ");
		        		}
		        		for (int wym = 0; wym < lWym; wym++) {
		        			for (int j = 0; j < t[wym]; j++) {
		        				if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {
		        					System.out.print(populacjaWyn[i][wym][j]);
		        				}
		        			}
		        		}
		        		if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {
		        			System.out.print(" ");
		        		}
		        		sred += ffxWyn[i];
		        		if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {
		        			wypiszFx(ffxWyn[i], lWym, i);
		        		}
		            }
		        	if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {
		        		System.out.println("Srednia: " + sred/n);
		        	}
		        	nrIter++;
		        	if (suk == true && nrIter < ilIter) {
		        		Turniejowa (mm, n, ffxWyn, xWyn, lWym, ffxSum, t, liczbaDWyn, populacjaWyn, sumaT, A, w, a, b, true, ilIter, nrIter); 
		        	}
		  	}
	
//////////////////// KrzyzRownom
	
	static void KrzyzRown(int n, int ilPunkt, double[] ffx, double [][] x, double ffxSum, int lWym, int[] t, double[][] liczbaD, int[][][] populacja, 
			int sumaT, int A, double w, int[] a, int[] b, boolean suk, int ilIter, int nrIter, int mm) {
		if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {
			System.out.println("\n//////////////////////////////////////////Krzyzowanie równomierne: ");
		}
		Random rand = new Random();
		double pk = 0.9;
		int ilOsob = 0, losWym = 0;
		double r []  = new double [n];
		int NrK []  = new int [n+1];
		int p [][]  = new int [n][ilPunkt];
		double ffxKrz []  = new double [n];
		double xKrz [][]  = new double [n][lWym];
		double xWz [][]  = new double [n][lWym];
		int populacjaKrz [][][]  = new int [n][lWym][sumaT];
		int wzorzec [][][]  = new int [n][lWym][sumaT];
		double liczbaDKrz [][] = new double [n][lWym];
		double liczbaDWz [][] = new double [n][lWym];
		
		double ffxWyn []  = new double [n];
		double xWyn [][]  = new double [n][lWym];
		int populacjaWyn [][][]  = new int [n][lWym][sumaT];
		double liczbaDWyn [][] = new double [n][lWym];
		if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {
			System.out.print("Wybrane osobniki o indeksie: ");
		}
		for (int i = 0; i < n; i++) {
			r[i] = ((double) rand.nextInt(100))/100;
			//System.out.println(" Nr = " + r[wym]);
			if (r[i] < pk) {
				NrK [ilOsob] = i;
				if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {
					System.out.print(" " + (NrK [ilOsob]+1));
				}
				ilOsob++;
			}
		}
		if (ilOsob%2 == 1 && n%2 == 0) {
			if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {
				System.out.println();
				System.out.print("Dodany osobnik o indeksie: ");
			}
			int i = 0;
			while (ilOsob%2 == 1 || i < n) {
				if (r[i] >= pk) {
					r[i] = r[i]-pk;
					NrK [ilOsob] = i;
					if (suk == true && (nrIter == 0 || nrIter == ilIter-1)) {
						if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {
							System.out.print(" " + (NrK [ilOsob]+1));
						}
					}
					else {
						if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {
							System.out.print(" " + (NrK [ilOsob]+1));
						}
					}
					ilOsob++;
					break;
				}
				else {
					i++;
				}
			}
		}
		else if (ilOsob%2 == 1 && n%2 == 1) {
			if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {
				System.out.println();
				System.out.print("Usuniety osobnik o indeksie: " + (NrK [ilOsob]+1));
			}
			ilOsob--;
		}
		
		
		
		int LosLiczby[] = new int [ilOsob];
        int wybrPunkt = 0;
        Set<Integer>set = new LinkedHashSet<Integer>();
        while (set.size() < ilOsob) {
            set.add(rand.nextInt(ilOsob));
        }
        int i = 0;
        for (int lczb : set)
            LosLiczby[i++] = lczb;
        if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {
        	System.out.println();
            System.out.println("Random = "+set);
        }
        i = 0;
        while(i != ilOsob){
        	if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {
        		System.out.print(" "+LosLiczby[i]);
        	}
            i++;
        }
        

        //////////////wzorcy      
        
        for (int i1 = 0; i1 < LosLiczby.length; i1++) {
        	if (i1%2 ==0) {
        		if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {
        			System.out.println("\nWzorzec nr " + (i1/2+1) + " : ");
        		}
        	}        	
            for (int wym = 0; wym < lWym; wym++) {

                for (int j = 0; j < t[wym]; j++) {
                	if (i1%2 == 0) {
                		wzorzec[i1][wym][j] = rand.nextInt(2);
                		if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {
                			System.out.print(wzorzec[i1][wym][j]);
                		}
                	}
                	else {
                		wzorzec[i1][wym][j] = wzorzec[i1-1][wym][j];
                	}
                    
                    liczbaDWz[i1][wym] += wzorzec[i1][wym][j]*Math.pow(2,t[wym]-j-1);
                }
                if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {
                	System.out.print(" ");
                }
                //System.out.println("x': " +liczbaD[i][wym]);
                //System.out.println("Liczba x'" + (i+1) +"." + (wym+1) +" : " + liczbaD);
                xWz[i1][wym] = a[wym] + ((b[wym]-a[wym])*liczbaDWz[i1][wym])/(Math.pow(2, t[wym]) - 1);
                //System.out.println("X =  " + x[i][wym]);
            }
            if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {
            	System.out.println();
            }
        }
        if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {
        	System.out.println();
        }
       
        for (i = 0; i < ilOsob; i++){
        	if (i%2 == 0) {
        		if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {
	        		System.out.println("\n\n\nPotomekowie z chromosomow " + (NrK [LosLiczby[i]]+1) + " i " + (NrK [LosLiczby[i+1]]+1) + ": ");
	        		System.out.println("Wzorzec nr " + (i/2+1) + " : ");
        		}
        		for (int wym = 0; wym < lWym; wym++) { 
            		for (int j = 0; j < t[wym]; j++) {
            			if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {
            				System.out.print(wzorzec[i][wym][j]);
            			}
            		}
            		if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {
            			System.out.print(" ");
            		}
            	}
        		if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {
        			System.out.print("\n\nPotomek 1 : ");
        		}
        	}
        	else {
        		if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {
        			System.out.print("Potomek 2 : ");
        		}
        	}
        	
        	for (int wym = 0; wym < lWym; wym++) {        	
	        	
	 			for (int j = 0; j < t[wym]; j++) {
	 				if (i%2 == 0) {
	 					if(wzorzec[i][wym][j] == 0) {
	 						populacjaKrz[i][wym][j] = populacja[NrK [LosLiczby[i]]][wym][j];
	 						populacjaWyn[NrK [LosLiczby[i]]][wym][j] = populacja[NrK [LosLiczby[i]]][wym][j];
	 					}
	 					else {
	 						populacjaKrz[i][wym][j] = populacja[NrK [LosLiczby[i+1]]][wym][j];
	 						populacjaWyn[NrK [LosLiczby[i]]][wym][j] = populacja[NrK [LosLiczby[i+1]]][wym][j];
	 					}	         				
	         		}
	 				else {
	 					if (wzorzec[i][wym][j] == 0) {
	 						populacjaKrz[i][wym][j] = populacja[NrK [LosLiczby[i]]][wym][j];
	 						populacjaWyn[NrK [LosLiczby[i]]][wym][j] = populacja[NrK [LosLiczby[i]]][wym][j];
	 					}
	 					else {
	 						populacjaKrz[i][wym][j] = populacja[NrK [LosLiczby[i-1]]][wym][j];
	 						populacjaWyn[NrK [LosLiczby[i]]][wym][j] = populacja[NrK [LosLiczby[i-1]]][wym][j];
	 					}
	 				}
	 				if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {	
	 					System.out.print(populacjaKrz[i][wym][j]);
	 				}
                     liczbaDKrz[i][wym] += populacjaKrz[i][wym][j]*Math.pow(2,t[wym]-j-1);
                     liczbaDWyn[NrK [LosLiczby[i]]][wym] += populacjaWyn[NrK [LosLiczby[i]]][wym][j]*Math.pow(2,t[wym]-j-1);
                     
     			}
	 			if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {
	 				System.out.print(" ");
	 			}
	 			//System.out.println("x': " +liczbaDKrz[i][wym]);
                //System.out.println("Liczba x'" + (i+1) +"." + (wym+1) +" : " + liczbaDKrz);
                xKrz[i][wym] = a[wym] + ((b[wym]-a[wym])*liczbaDKrz[i][wym])/(Math.pow(2, t[wym]) - 1);
                xWyn[NrK [LosLiczby[i]]][wym] = a[wym] + ((b[wym]-a[wym])*liczbaDWyn[NrK [LosLiczby[i]]][wym])/(Math.pow(2, t[wym]) - 1);
                //System.out.println("X =  " + xKrz[i][wym]);

                ffxKrz[i] += funkcja(xKrz[i][wym], A, w);
                ffxWyn[NrK [LosLiczby[i]]] += funkcja(xWyn[NrK [LosLiczby[i]]][wym], A, w);
        	}
        	ffxKrz[i] += A*lWym;
        	ffxWyn[NrK [LosLiczby[i]]] +=  A*lWym;
        	if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {	
        		System.out.println();
        		wypiszFx(ffxKrz[i], lWym, i);
        	}
        	
        	//wypiszFx(ffxWyn[NrK [LosLiczby[i]]], lWym, i);
        	if (i%2 == 1) {
        		//System.out.println("Po Krzyzowaniu chromosomu nr " + (NrK [LosLiczby[i]]+1) + " : ");
                
        		if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {
        			System.out.println("\nRodzic nr 1 - chromosom:  " + (NrK [LosLiczby[i-1]]+1) + " : ");
        		}
                    /*for (int wym = 0; wym < lWym; wym++) {

                        for (int j = 0; j < t[wym]; j++) {
                            
                            System.out.print(populacja[NrK [LosLiczby[i-1]]][wym][j]);
                            liczbaD[NrK [LosLiczby[i-1]]][wym] += populacja[NrK [LosLiczby[i-1]]][wym][j]*Math.pow(2,t[wym]-j-1);
                        }
                        System.out.print(" ");
                        //System.out.println("x': " +liczbaD[i][wym]);
                        //System.out.println("Liczba x'" + (i+1) +"." + (wym+1) +" : " + liczbaD);
                        x[NrK [LosLiczby[i-1]]][wym] = a[wym] + ((b[wym]-a[wym])*liczbaD[NrK [LosLiczby[i-1]]][wym])/(Math.pow(2, t[wym]) - 1);
                        //System.out.println("X =  " + x[i][wym]);

                        ffx[NrK [LosLiczby[i-1]]] += funkcja(x[NrK [LosLiczby[i-1]]][wym], A, w);
                    }
                    System.out.println();
                    
                    ffx[NrK [LosLiczby[i]]] += A*lWym;
                    */
                    
        			if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {
                    	wypiszFx(ffx[NrK [LosLiczby[i-1]]], lWym, i);
                    	System.out.println("\nRodzic nr 2 -  chromosom " + (NrK [LosLiczby[i]]+1) + " : ");
                    }
                       /* for (int wym = 0; wym < lWym; wym++) {

                            for (int j = 0; j < t[wym]; j++) {
                                
                                System.out.print(populacja[NrK [LosLiczby[i]]][wym][j]);
                                liczbaD[NrK [LosLiczby[i]]][wym] += populacja[NrK [LosLiczby[i]]][wym][j]*Math.pow(2,t[wym]-j-1);
                            }
                            System.out.print(" ");
                            //System.out.println("x': " +liczbaD[i][wym]);
                            //System.out.println("Liczba x'" + (i+1) +"." + (wym+1) +" : " + liczbaD);
                            x[NrK [LosLiczby[i]]][wym] = a[wym] + ((b[wym]-a[wym])*liczbaD[NrK [LosLiczby[i]]][wym])/(Math.pow(2, t[wym]) - 1);
                            //System.out.println("X =  " + x[i][wym]);

                            ffx[NrK [LosLiczby[i]]] += funkcja(x[NrK [LosLiczby[i]]][wym], A, w);
                        }
                        System.out.println();
                        ffx[NrK [LosLiczby[i]]] += A*lWym;
                        */
                       
        				if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {
                        	 wypiszFx(ffx[NrK [LosLiczby[i]]], lWym, i);
                        	System.out.println();
                        }
        	}        	
        }
        for (i = 0; i < n; i++){
        	boolean spr = false;
        	for (int k = 0; k < ilOsob; k++){
        		if (i == NrK [LosLiczby[k]]) {
        			spr = true;
        		}        		
        	}
        	if (spr == false) {
        		for (int wym = 0; wym < lWym; wym++) {
        			
            		for (int j = 0; j < t[wym]; j++) {
            			populacjaWyn[i][wym][j] = populacja[i][wym][j];            			
            			liczbaDWyn[i][wym] += populacjaWyn[i][wym][j]*Math.pow(2,t[wym]-j-1);            			
            		}
            		xWyn[i][wym] = a[wym] + ((b[wym]-a[wym])*liczbaDWyn[i][wym])/(Math.pow(2, t[wym]) - 1);
            		ffxWyn[i] += funkcja(xWyn[i][wym], A, w);
            	}        		
        		ffxWyn[i] += A*lWym;
        	}        	
        }

        	double sred = 0;
	        if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {
	        		System.out.println("Ocena populacji nr " + (nrIter+1) + " : ");
	        }
        	for (i = 0; i < n; i++){
        		if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {
        			System.out.print((i+1)+". Binarny: ");
        		}
        		for (int wym = 0; wym < lWym; wym++) {
        			for (int j = 0; j < t[wym]; j++) {
        				if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {
        					System.out.print(populacjaWyn[i][wym][j]);
        				}
        			}
        		}
        		if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {
        			System.out.print(" ");
        		}
        		sred += ffxWyn[i];
        		if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {
        			wypiszFx(ffxWyn[i], lWym, i);
        		}
            }
        	if (suk == false || (suk == true && (nrIter == 0 || nrIter == ilIter-1))) {
        		System.out.println("Srednia: " + sred/n);
        	}
        	nrIter++;
        	if (suk == true && nrIter < ilIter) {
        		Turniejowa (mm, n, ffxWyn, xWyn, lWym, ffxSum, t, liczbaDWyn, populacjaWyn, sumaT, A, w, a, b, true, ilIter, nrIter); 
        	}
        	if (nrIter < ilIter && suk == false){
        		KrzyzRown (n,2, ffxWyn, xWyn, ffxSum, lWym, t, liczbaDWyn, populacjaWyn, sumaT, A, w, a, b, false, ilIter, nrIter, mm);
        		//Mutacja (n, ffxWyn, x, ffxSum, lWym, t, liczbaDWyn, populacjaWyn, sumaT, A, w, a, b, false, ilIter, nrIter, mm);
     	 		//Inwersja (n, ffxWyn, ffxSum, lWym, t, liczbaDWyn, populacjaWyn, sumaT, A, w, a, b, false, ilIter, nrIter, mm);
        	}
        
        	
  	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////Sukcesja
	
	static void Sukcesja(int n, int ilPunkt, double[] ffx, double [][] x, double ffxSum, int lWym, int[] t, double[][] liczbaD, int[][][] populacja, 
			int sumaT, int A, double w, int[] a, int[] b, int ilIter, int mm) {
		Turniejowa (mm, n, ffx, x, lWym, ffxSum, t, liczbaD, populacja, sumaT, A, w, a, b, true, ilIter, 0);   
	}
	
//////////////////////////////////////////////////////////////////////////////MAIN	
    public static void main( String args[] ) {
        int A = 10, n = 20, ilEpok = 200, z = 0, lWym = 3, sumaT = 0, mm = 1;
        double w = 20*Math.PI;
        double ffx[] = new double [n];
        int m[] = new int [lWym];
        int t[] = new int [lWym];
        int a[] = {-1,-2,-3};
        int b[] = {1,2,3};
        int d[] = {2,1,3};
        for (int i = 0; i < lWym; i++){
            z = 0;
            m[i] = (int) ((b[i]-a[i])*Math.pow(10, d[i])+1);
            while (z!=1){
                if (Math.pow(2,t[i]-1) <= m[i] && m[i] <= Math.pow(2,t[i])){
                    z = 1;
                    t[i]--;
                }
                else {
                    t[i]++;
                    sumaT++;
                }
            }
            //System.out.println("m: " + t[i]);
            //System.out.println("Suma t: " + sumaT);
        }


        double x[][] = new double[n][lWym];
        Random rand = new Random();
        int populacja[][][] = new int[n][lWym][sumaT];
        int suma = 0;
        int losLiczba[] = new int[lWym];

        double liczbaD [][] = new double[n][lWym];
        for (int i = 0; i < n; i++) {
        	System.out.println("Chromosom nr " + (i+1) + " : ");
            for (int wym = 0; wym < lWym; wym++) {

                System.out.print("Wymiar binarny "+ (wym+1) + " : ");

                for (int j = 0; j < t[wym]; j++) {
                    populacja[i][wym][j] = rand.nextInt(2);
                    System.out.print(populacja[i][wym][j]);
                    liczbaD[i][wym] += populacja[i][wym][j]*Math.pow(2,t[wym]-j-1);
                }
                System.out.println();
                //System.out.println("x': " +liczbaD[i][wym]);
                //System.out.println("Liczba x'" + (i+1) +"." + (wym+1) +" : " + liczbaD);
                x[i][wym] = a[wym] + ((b[wym]-a[wym])*liczbaD[i][wym])/(Math.pow(2, t[wym]) - 1);
                //System.out.println("X =  " + x[i][wym]);

                ffx[i] += funkcja(x[i][wym], A, w);
            }
            ffx[i] += A*lWym;
            wypiszFx(ffx[i], lWym, i);
        }
        double ffxSum = 0;
        for (int i = 0; i < n; i++){
            //System.out.print("Liczby binarne " + (i+1) +" : " );
            for (int wym = 0; wym < lWym; wym++) {
                for (int j = 0; j < t[wym]; j++) {
                    //System.out.print(populacja[i][wym][j]);
                    liczbaD[i][wym] += populacja[i][wym][j] * Math.pow(2, t[wym] - j - 1);
                }
                //System.out.print("");
            }
            //System.out.println(" ");
            //wypiszFx(ffx[i], lWym, i);
            ffxSum += ffx[i];
        }
        /*
        int LosLiczby[] = new int [n];
        
        Set<Integer>set = new LinkedHashSet<Integer>();
        while (set.size() < n) {
            set.add(rand.nextInt(n)+1);
        }
        int i = 0;
        for (int lczb : set)
            LosLiczby[i++] = lczb;
            
        System.out.println("Random = "+set);
        i = 0;
        while(i != 10){
            System.out.print(" "+LosLiczby[i]);
            i++;
        }
        */
        //Turniejowa (mm, n, ffx, x, lWym, ffxSum, t, liczbaD, populacja, sumaT, A, w, a, b, false, 0, 0);       
        //Rankingowa (mm, n, ffx, x, lWym, ffxSum, t, liczbaD, populacja, sumaT, A, w, a, b, false, 0, 0);        
        //Ruletki (mm, n, ffx, x, lWym, ffxSum, t, liczbaD, populacja, sumaT, A, w, a, b, false, 0, 0);
        
        //Mutacja (n, ffx, x, ffxSum, lWym, t, liczbaD, populacja, sumaT, A, w, a, b, false, ilEpok, 0, mm);       
        //Inwersja (n, ffx, ffxSum, lWym, t, liczbaD, populacja, sumaT, A, w, a, b, false, ilEpok, 0, mm);
        
        //Krzyz1 (n, ffx, x, ffxSum, lWym, t, liczbaD, populacja, sumaT, A, w, a, b, false, ilEpok, 0, mm);
        //Krzyz2 (n,2, ffx, x, ffxSum, lWym, t, liczbaD, populacja, sumaT, A, w, a, b, false, ilEpok, 0, mm);
        //KrzyzN (n,3, ffx, x, ffxSum, lWym, t, liczbaD, populacja, sumaT, A, w, a, b, false, ilEpok, 0, mm);
        //KrzyzRown (n,2, ffx, x, ffxSum, lWym, t, liczbaD, populacja, sumaT, A, w, a, b, false, ilEpok, 0, mm);
        
        Sukcesja (n,2, ffx, x, ffxSum, lWym, t, liczbaD, populacja, sumaT, A, w, a, b, ilEpok, mm);
    }


	
}