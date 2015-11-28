/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tarea1cache;

import java.io.File;
import java.util.Scanner;

/**
 *
 * @author xavier2696
 */
public class Tarea1Cache {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //tiempos
        double[] t_sincache = new double[1];
        t_sincache[0] = 0;
        double[] t_directo = new double[1];
        t_directo[0] = 0;
        double[] t_asociativo = new double[1];
        t_asociativo[0] = 0;
        double[] t_conjunto = new double[1];
        t_conjunto[0] = 0;
        //memoria cache
        Linea[] cache_directa = new Linea[8];        
        Linea[] cache_asociativa = new Linea[8];
        Linea[] cache_conjunto = new Linea[8];
        for(int i=0; i<8; i++){
            cache_directa[i] = new Linea();
            cache_directa[i].setEtiqueta(-1);
            cache_asociativa[i] = new Linea();
            cache_asociativa[i].setEtiqueta(-1);
            cache_conjunto[i]=new Linea();
            cache_conjunto[i].setEtiqueta(-1);
            cache_conjunto[i].setBloque(-1);
        }
        //memoria ram
        int[] ram = new int[4096];
        
        //numero que define siguiente posicion en memoria asociativa
        int[] siguiente_asociativa=new int[1];
        siguiente_asociativa[0]=0;
        //arreglo que contiene siguiente posicion en memoria asociativa por conjuntos
        int[] siguiente_conjunto = new int[2];
        siguiente_conjunto[0] = 0;
        siguiente_conjunto[1] = 4;
        int n=4096;
        
        
        //cache directa
        LlenarRam(ram);
        int tipo=1;
        for (int i=0;i<=n-2;i++)
            for (int j=i+1;j<=n-1;j++)
                if (leer(i,tipo,t_directo,ram,cache_directa,siguiente_asociativa,siguiente_conjunto)>leer(j,tipo,t_directo,ram,cache_directa,siguiente_asociativa,siguiente_conjunto)){
                    int temp=leer(i,tipo,t_directo,ram,cache_directa,siguiente_asociativa,siguiente_conjunto);
                    escribir(i, tipo,leer(j,tipo,t_directo,ram,cache_directa,siguiente_asociativa,siguiente_conjunto),t_directo,ram,cache_directa,siguiente_asociativa,siguiente_conjunto);
                    escribir(j,tipo,temp,t_directo,ram,cache_directa,siguiente_asociativa,siguiente_conjunto);
                }
        
        //sin cache
        
        tipo=0;
        LlenarRam(ram);
        for (int i=0;i<=n-2;i++)
            for (int j=i+1;j<=n-1;j++)
                if (leer(i,tipo,t_sincache,ram,cache_directa,siguiente_asociativa,siguiente_conjunto)>leer(j,tipo,t_sincache,ram,cache_directa,siguiente_asociativa,siguiente_conjunto)){
                    int temp=leer(i,tipo,t_sincache,ram,cache_directa,siguiente_asociativa,siguiente_conjunto);
                    escribir(i, tipo,leer(j,tipo,t_sincache,ram,cache_directa,siguiente_asociativa,siguiente_conjunto),t_sincache,ram,cache_directa,siguiente_asociativa,siguiente_conjunto);
                    escribir(j,tipo,temp,t_sincache,ram,cache_directa,siguiente_asociativa,siguiente_conjunto);
                }
        //cache asociativa
        LlenarRam(ram);
        tipo=2;
        for (int i=0;i<=n-2;i++)
            for (int j=i+1;j<=n-1;j++)
                if (leer(i,tipo,t_asociativo,ram,cache_asociativa,siguiente_asociativa,siguiente_conjunto)>leer(j,tipo,t_asociativo,ram,cache_asociativa,siguiente_asociativa,siguiente_conjunto)){
                    int temp=leer(i,tipo,t_asociativo,ram,cache_asociativa,siguiente_asociativa,siguiente_conjunto);
                    escribir(i, tipo,leer(j,tipo,t_asociativo,ram,cache_asociativa,siguiente_asociativa,siguiente_conjunto),t_asociativo,ram,cache_asociativa,siguiente_asociativa,siguiente_conjunto);
                    escribir(j,tipo,temp,t_asociativo,ram,cache_asociativa,siguiente_asociativa,siguiente_conjunto);
                }
        //cache conjunto
        LlenarRam(ram);
        tipo=3;
        for (int i=0;i<=n-2;i++)
            for (int j=i+1;j<=n-1;j++)
                if (leer(i,tipo,t_conjunto,ram,cache_conjunto,siguiente_asociativa,siguiente_conjunto)>leer(j,tipo,t_conjunto,ram,cache_conjunto,siguiente_asociativa,siguiente_conjunto)){
                    int temp=leer(i,tipo,t_conjunto,ram,cache_conjunto,siguiente_asociativa,siguiente_conjunto);
                    escribir(i, tipo,leer(j,tipo,t_conjunto,ram,cache_conjunto,siguiente_asociativa,siguiente_conjunto),t_conjunto,ram,cache_conjunto,siguiente_asociativa,siguiente_conjunto);
                    escribir(j,tipo,temp,t_conjunto,ram,cache_conjunto,siguiente_asociativa,siguiente_conjunto);
                }
        
        System.out.println("Tiempos");
        System.out.println("Sin cache "+t_sincache[0]/100+" microsegundos");
        System.out.println("Directo "+t_directo[0]/100+" microsegundos");
        System.out.println("Asociativa "+t_asociativo[0]/100+" microsegundos");
        System.out.println("Asociativa por conjuntos "+t_conjunto[0]/100+" microsegundos");
        
        
        /*
        //prueba escritorio sin cache
        tipo = 0;
        escribir(100,tipo,10,t_sincache,ram,cache_directa,siguiente_asociativa,siguiente_conjunto);    //En la memoria 100 escribe un 10
      escribir(101,tipo,13,t_sincache,ram,cache_directa,siguiente_asociativa,siguiente_conjunto);
      escribir(102,tipo,21,t_sincache,ram,cache_directa,siguiente_asociativa,siguiente_conjunto);
      escribir(103,tipo,11,t_sincache,ram,cache_directa,siguiente_asociativa,siguiente_conjunto);
      escribir(104,tipo,67,t_sincache,ram,cache_directa,siguiente_asociativa,siguiente_conjunto);
      escribir(105,tipo,43,t_sincache,ram,cache_directa,siguiente_asociativa,siguiente_conjunto);
      escribir(106,tipo,9,t_sincache,ram,cache_directa,siguiente_asociativa,siguiente_conjunto);
      escribir(107,tipo,11,t_sincache,ram,cache_directa,siguiente_asociativa,siguiente_conjunto);
      escribir(108,tipo,19,t_sincache,ram,cache_directa,siguiente_asociativa,siguiente_conjunto);
      escribir(109,tipo,23,t_sincache,ram,cache_directa,siguiente_asociativa,siguiente_conjunto);
      escribir(110,tipo,32,t_sincache,ram,cache_directa,siguiente_asociativa,siguiente_conjunto);
      escribir(111,tipo,54,t_sincache,ram,cache_directa,siguiente_asociativa,siguiente_conjunto);
      escribir(112,tipo,98,t_sincache,ram,cache_directa,siguiente_asociativa,siguiente_conjunto);
      escribir(113,tipo,7,t_sincache,ram,cache_directa,siguiente_asociativa,siguiente_conjunto);
      escribir(114,tipo,13,t_sincache,ram,cache_directa,siguiente_asociativa,siguiente_conjunto);
      escribir(115,tipo,1,t_sincache,ram,cache_directa,siguiente_asociativa,siguiente_conjunto);
      int menor=leer(100,tipo,t_sincache,ram,cache_directa,siguiente_asociativa,siguiente_conjunto);
      int mayor=menor;
      int a=0;
      for(int i=101;i<=115;i++){
         a++;
         escribir(615,tipo,a,t_sincache,ram,cache_directa,siguiente_asociativa,siguiente_conjunto);
         if (leer(i,tipo,t_sincache,ram,cache_directa,siguiente_asociativa,siguiente_conjunto)<menor)
             menor=leer(i,tipo,t_sincache,ram,cache_directa,siguiente_asociativa,siguiente_conjunto);
         if (leer(i,tipo,t_sincache,ram,cache_directa,siguiente_asociativa,siguiente_conjunto)>mayor)
             mayor=leer(i,tipo,t_sincache,ram,cache_directa,siguiente_asociativa,siguiente_conjunto);
      }
        System.out.println("tiempo sin cache: "+t_sincache[0]/100);
        //memoria ram
        ram = new int[4096];
        //LlenarRam(ram);
        for(int i=0; i<8; i++){
            cache_directa[i] = new Linea();
            cache_asociativa[i] = new Linea();
            cache_conjunto[i]=new Linea();
        }
        //numero que define siguiente posicion en memoria asociativa
        siguiente_asociativa=new int[1];
        siguiente_asociativa[0]=0;
        //arreglo que contiene siguiente posicion en memoria asociativa por conjuntos
        siguiente_conjunto = new int[2];
        siguiente_conjunto[0] = 0;
        siguiente_conjunto[1] = 4;
        
        
        //prueba escritorio cache_directa
        tipo = 1;
        escribir(100,tipo,10,t_directo,ram,cache_directa,siguiente_asociativa,siguiente_conjunto);    //En la memoria 100 escribe un 10
      escribir(101,tipo,13,t_directo,ram,cache_directa,siguiente_asociativa,siguiente_conjunto);
      escribir(102,tipo,21,t_directo,ram,cache_directa,siguiente_asociativa,siguiente_conjunto);
      escribir(103,tipo,11,t_directo,ram,cache_directa,siguiente_asociativa,siguiente_conjunto);
      escribir(104,tipo,67,t_directo,ram,cache_directa,siguiente_asociativa,siguiente_conjunto);
      escribir(105,tipo,43,t_directo,ram,cache_directa,siguiente_asociativa,siguiente_conjunto);
      escribir(106,tipo,9,t_directo,ram,cache_directa,siguiente_asociativa,siguiente_conjunto);
      escribir(107,tipo,11,t_directo,ram,cache_directa,siguiente_asociativa,siguiente_conjunto);
      escribir(108,tipo,19,t_directo,ram,cache_directa,siguiente_asociativa,siguiente_conjunto);
      escribir(109,tipo,23,t_directo,ram,cache_directa,siguiente_asociativa,siguiente_conjunto);
      escribir(110,tipo,32,t_directo,ram,cache_directa,siguiente_asociativa,siguiente_conjunto);
      escribir(111,tipo,54,t_directo,ram,cache_directa,siguiente_asociativa,siguiente_conjunto);
      escribir(112,tipo,98,t_directo,ram,cache_directa,siguiente_asociativa,siguiente_conjunto);
      escribir(113,tipo,7,t_directo,ram,cache_directa,siguiente_asociativa,siguiente_conjunto);
      escribir(114,tipo,13,t_directo,ram,cache_directa,siguiente_asociativa,siguiente_conjunto);
      escribir(115,tipo,1,t_directo,ram,cache_directa,siguiente_asociativa,siguiente_conjunto);
      menor=leer(100,tipo,t_directo,ram,cache_directa,siguiente_asociativa,siguiente_conjunto);
      mayor=menor;
      a=0;
      for(int i=101;i<=115;i++){
         a++;
         escribir(615,tipo,a,t_directo,ram,cache_directa,siguiente_asociativa,siguiente_conjunto);
         if (leer(i,tipo,t_directo,ram,cache_directa,siguiente_asociativa,siguiente_conjunto)<menor)
             menor=leer(i,tipo,t_directo,ram,cache_directa,siguiente_asociativa,siguiente_conjunto);
         if (leer(i,tipo,t_directo,ram,cache_directa,siguiente_asociativa,siguiente_conjunto)>mayor)
             mayor=leer(i,tipo,t_directo,ram,cache_directa,siguiente_asociativa,siguiente_conjunto);
      }
        System.out.println("tiempo directo: "+t_directo[0]/100);
        for(int i=0; i<8; i++){
            cache_directa[i] = new Linea();
            cache_asociativa[i] = new Linea();
            cache_conjunto[i]=new Linea();
        }
        //memoria ram
        ram = new int[4096];
        //LlenarRam(ram);
        //numero que define siguiente posicion en memoria asociativa
        siguiente_asociativa=new int[1];
        siguiente_asociativa[0]=0;
        //arreglo que contiene siguiente posicion en memoria asociativa por conjuntos
        siguiente_conjunto = new int[2];
        siguiente_conjunto[0] = 0;
        siguiente_conjunto[1] = 4;
        
        
      //prueba escritorio cache_asociativa
        tipo = 2;
        escribir(100,tipo,10,t_asociativo,ram,cache_asociativa,siguiente_asociativa,siguiente_conjunto);    //En la memoria 100 escribe un 10
      escribir(101,tipo,13,t_asociativo,ram,cache_asociativa,siguiente_asociativa,siguiente_conjunto);
      escribir(102,tipo,21,t_asociativo,ram,cache_asociativa,siguiente_asociativa,siguiente_conjunto);
      escribir(103,tipo,11,t_asociativo,ram,cache_asociativa,siguiente_asociativa,siguiente_conjunto);
      escribir(104,tipo,67,t_asociativo,ram,cache_asociativa,siguiente_asociativa,siguiente_conjunto);
      escribir(105,tipo,43,t_asociativo,ram,cache_asociativa,siguiente_asociativa,siguiente_conjunto);
      escribir(106,tipo,9,t_asociativo,ram,cache_asociativa,siguiente_asociativa,siguiente_conjunto);
      escribir(107,tipo,11,t_asociativo,ram,cache_asociativa,siguiente_asociativa,siguiente_conjunto);
      escribir(108,tipo,19,t_asociativo,ram,cache_asociativa,siguiente_asociativa,siguiente_conjunto);
      escribir(109,tipo,23,t_asociativo,ram,cache_asociativa,siguiente_asociativa,siguiente_conjunto);
      escribir(110,tipo,32,t_asociativo,ram,cache_asociativa,siguiente_asociativa,siguiente_conjunto);
      escribir(111,tipo,54,t_asociativo,ram,cache_asociativa,siguiente_asociativa,siguiente_conjunto);
      escribir(112,tipo,98,t_asociativo,ram,cache_asociativa,siguiente_asociativa,siguiente_conjunto);
      escribir(113,tipo,7,t_asociativo,ram,cache_asociativa,siguiente_asociativa,siguiente_conjunto);
      escribir(114,tipo,13,t_asociativo,ram,cache_asociativa,siguiente_asociativa,siguiente_conjunto);
      escribir(115,tipo,1,t_asociativo,ram,cache_asociativa,siguiente_asociativa,siguiente_conjunto);
      menor=leer(100,tipo,t_asociativo,ram,cache_asociativa,siguiente_asociativa,siguiente_conjunto);
      mayor=menor;
      a=0;
      for(int i=101;i<=115;i++){
         a++;
         escribir(615,tipo,a,t_asociativo,ram,cache_asociativa,siguiente_asociativa,siguiente_conjunto);
         if (leer(i,tipo,t_asociativo,ram,cache_asociativa,siguiente_asociativa,siguiente_conjunto)<menor)
             menor=leer(i,tipo,t_asociativo,ram,cache_asociativa,siguiente_asociativa,siguiente_conjunto);
         if (leer(i,tipo,t_asociativo,ram,cache_asociativa,siguiente_asociativa,siguiente_conjunto)>mayor)
             mayor=leer(i,tipo,t_asociativo,ram,cache_asociativa,siguiente_asociativa,siguiente_conjunto);
      }
        System.out.println("tiempo asociativo: "+t_asociativo[0]/100); 
        for(int i=0; i<8; i++){
            cache_directa[i] = new Linea();
            cache_asociativa[i] = new Linea();
            cache_conjunto[i]=new Linea();
        }
        //memoria ram
        ram = new int[4096];
        //LlenarRam(ram);
        //numero que define siguiente posicion en memoria asociativa
        siguiente_asociativa=new int[1];
        siguiente_asociativa[0]=0;
        //arreglo que contiene siguiente posicion en memoria asociativa por conjuntos
        siguiente_conjunto = new int[2];
        siguiente_conjunto[0] = 0;
        siguiente_conjunto[1] = 4;
        
        
        //prueba escritorio cache conjuntos
        tipo = 3;
        escribir(100,tipo,10,t_conjunto,ram,cache_conjunto,siguiente_asociativa,siguiente_conjunto);    //En la memoria 100 escribe un 10
      escribir(101,tipo,13,t_conjunto,ram,cache_conjunto,siguiente_asociativa,siguiente_conjunto);
      escribir(102,tipo,21,t_conjunto,ram,cache_conjunto,siguiente_asociativa,siguiente_conjunto);
      escribir(103,tipo,11,t_conjunto,ram,cache_conjunto,siguiente_asociativa,siguiente_conjunto);
      escribir(104,tipo,67,t_conjunto,ram,cache_conjunto,siguiente_asociativa,siguiente_conjunto);
      escribir(105,tipo,43,t_conjunto,ram,cache_conjunto,siguiente_asociativa,siguiente_conjunto);
      escribir(106,tipo,9,t_conjunto,ram,cache_conjunto,siguiente_asociativa,siguiente_conjunto);
      escribir(107,tipo,11,t_conjunto,ram,cache_conjunto,siguiente_asociativa,siguiente_conjunto);
      escribir(108,tipo,19,t_conjunto,ram,cache_conjunto,siguiente_asociativa,siguiente_conjunto);
      escribir(109,tipo,23,t_conjunto,ram,cache_conjunto,siguiente_asociativa,siguiente_conjunto);
      escribir(110,tipo,32,t_conjunto,ram,cache_conjunto,siguiente_asociativa,siguiente_conjunto);
      escribir(111,tipo,54,t_conjunto,ram,cache_conjunto,siguiente_asociativa,siguiente_conjunto);
      escribir(112,tipo,98,t_conjunto,ram,cache_conjunto,siguiente_asociativa,siguiente_conjunto);
      escribir(113,tipo,7,t_conjunto,ram,cache_conjunto,siguiente_asociativa,siguiente_conjunto);
      escribir(114,tipo,13,t_conjunto,ram,cache_conjunto,siguiente_asociativa,siguiente_conjunto);
      escribir(115,tipo,1,t_conjunto,ram,cache_conjunto,siguiente_asociativa,siguiente_conjunto);
      menor=leer(100,tipo,t_conjunto,ram,cache_conjunto,siguiente_asociativa,siguiente_conjunto);
      mayor=menor;
      a=0;
      for(int i=101;i<=115;i++){
         a++;
         escribir(615,tipo,a,t_conjunto,ram,cache_conjunto,siguiente_asociativa,siguiente_conjunto);
         if (leer(i,tipo,t_conjunto,ram,cache_conjunto,siguiente_asociativa,siguiente_conjunto)<menor)
             menor=leer(i,tipo,t_conjunto,ram,cache_conjunto,siguiente_asociativa,siguiente_conjunto);
         if (leer(i,tipo,t_conjunto,ram,cache_conjunto,siguiente_asociativa,siguiente_conjunto)>mayor)
             mayor=leer(i,tipo,t_conjunto,ram,cache_conjunto,siguiente_asociativa,siguiente_conjunto);
      }
        System.out.println("tiempo conjunto: "+t_conjunto[0]/100); 
       */ 
    }
    
    
    public static void LlenarRam(int[] ram){
        int i = 0;
        try{
            Scanner scanner = new Scanner(new File("datos.txt"));
            while(scanner.hasNextInt()){
                //System.out.println(scanner.nextInt());
                ram[i]=scanner.nextInt();
                i++;
            }
            scanner.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    //i = pos
    public static int leer(int i, int tipo, double[] tiempo, int[]ram, Linea[] cache, int[] siguiente_asociativa, int[] siguiente_conjunto){
        int k=8;
        int m=8;
        int retorno = 0;
        switch(tipo){
                case 0:
                    retorno = ram[i];
                    tiempo[0] += 10;
                    break;
                case 1:
                    //definir palabra, linea, bloque, etiqueta
                    int palabra = i%k;
                    int bloque = i/k;
                    int linea = bloque%m;
                    int etiqueta = bloque/m;
                    if(cache[linea].isValido()){
                        if(cache[linea].getEtiqueta()==etiqueta){
                            //obtener valor cache
                            retorno = cache[linea].getDatos()[palabra];
                            //sumar tiempo
                            tiempo[0] += 1;
                        }else{                            
                            if(cache[linea].isModificado()){
                                //linea de cache a bloque de ram
                                int b = 0;
                                for(int j = cache[linea].getEtiqueta()*m*k; j<cache[linea].getEtiqueta()*m*k; j++){
                                    ram[j] = cache[linea].getDato(b);
                                    b++;
                                }
                                cache[linea].setModificado(false);
                                //bloque de ram a linea de cache
                                cache[linea].setEtiqueta(etiqueta);
                                int a = 0;
                                for(int j = k*bloque; j<k*bloque+k; j++){
                                    cache[linea].setDato(a, ram[j]);
                                    a++;
                                }
                                //obtener valor cache
                                retorno = cache[linea].getDatos()[palabra];
                                //sumar tiempo
                                tiempo[0] += 22;
                            }else{
                                //bloque de ram a linea de cache
                                cache[linea].setEtiqueta(etiqueta);
                                int a = 0;
                                for(int j = k*bloque; j<k*bloque+k; j++){
                                    cache[linea].setDato(a, ram[j]);
                                    a++;
                                }
                                //obtener valor cache
                                retorno = cache[linea].getDatos()[palabra];
                                //sumar tiempo
                                tiempo[0] += 11;
                            }
                        }
                    }else{
                        cache[linea].setValido(true);
                        cache[linea].setModificado(false);                        
                        //bloque de ram a linea de cache
                        cache[linea].setEtiqueta(etiqueta);
                        int a = 0;
                        for(int j = k*bloque; j<k*bloque+k; j++){
                            cache[linea].setDato(a, ram[j]);
                            a++;
                        }
                        //obtener valor cache
                        retorno = cache[linea].getDatos()[palabra];
                        //sumar tiempo
                        tiempo[0] += 11;
                    }
                    break;
                case 2:
                    
                    //etiqueta, palabra y bloque
                    palabra = i%k;
                    bloque = i/k;
                    etiqueta = bloque;
                    //ver si el dato esta en la cache
                    int linea_asociativa=-1;
                    for(int j=0; j<8;j++){
                        if(cache[j].getEtiqueta()==etiqueta ){
                            linea_asociativa=j;
                            break;
                        }
                    }
                    
                    if(linea_asociativa==-1){
                        if(cache[siguiente_asociativa[0]].isValido()){
                            if(cache[siguiente_asociativa[0]].isModificado()){
                            //linea de cache a bloque de ram
                                int b = 0;
                                for(int j = cache[siguiente_asociativa[0]].getEtiqueta()*k; j<cache[siguiente_asociativa[0]].getEtiqueta()*k+k; j++){
                                    ram[j] = cache[siguiente_asociativa[0]].getDato(b);
                                    b++;
                                }
                                cache[siguiente_asociativa[0]].setModificado(false);
                                
                                //bloque de ram a linea de cache
                                cache[siguiente_asociativa[0]].setEtiqueta(etiqueta);
                                int a = 0;
                                for(int j = k*bloque; j<k*bloque+k; j++){
                                    cache[siguiente_asociativa[0]].setDato(a, ram[j]);
                                    a++;
                                }
                                
                                //obtener valor cache
                                retorno = cache[siguiente_asociativa[0]].getDatos()[palabra];
                                //sumar tiempo
                                tiempo[0] += 22;
                        }else{                             
                            //bloque de ram a linea de cache
                            cache[siguiente_asociativa[0]].setEtiqueta(etiqueta);
                            int a = 0;
                            for(int j = k*bloque; j<k*bloque+k; j++){
                                cache[siguiente_asociativa[0]].setDato(a, ram[j]);
                                a++;
                            }
                            //obtener valor cache
                            retorno = cache[siguiente_asociativa[0]].getDatos()[palabra];
                            //sumar tiempo
                            tiempo[0] += 11;
                        }
                        }else{
                            cache[siguiente_asociativa[0]].setValido(true);
                            cache[siguiente_asociativa[0]].setModificado(false);
                                
                                //bloque de ram a linea de cache
                                cache[siguiente_asociativa[0]].setEtiqueta(etiqueta);
                                int a = 0;
                                for(int j = k*bloque; j<k*bloque+k; j++){
                                    cache[siguiente_asociativa[0]].setDato(a, ram[j]);
                                    a++;
                                }
                                
                                //obtener valor cache
                                retorno = cache[siguiente_asociativa[0]].getDatos()[palabra];
                                //sumar tiempo
                                tiempo[0] += 11;
                        }
                        
                        if(siguiente_asociativa[0]<7)
                            siguiente_asociativa[0]++;
                        else
                            siguiente_asociativa[0]=0;
                    }else{
                        //obtener valor cache
                        retorno = cache[linea_asociativa].getDatos()[palabra];
                        //sumar tiempo
                        tiempo[0] += 1;
                    }
                    break;
                case 3:
                    //etiqueta, palabra,conjunto y bloque
                    palabra = i%k;
                    bloque = i/k;
                    int conjunto = bloque%2;
                    etiqueta = bloque/2; 
                    //ver si el dato esta en la cache
                    linea_asociativa=-1;
                    for(int j=0+4*conjunto; j<4+4*conjunto;j++){
                            if(cache[j].getEtiqueta()==etiqueta){
                                linea_asociativa=j;
                                break;
                            }
                        }
                    if(linea_asociativa==-1){
                        if(cache[siguiente_conjunto[conjunto]].isValido()){
                            if(cache[siguiente_conjunto[conjunto]].isModificado()){
                            //linea de cache a bloque de ram
                                int b = 0;
                                for(int j = cache[siguiente_conjunto[conjunto]].getBloque()*k; j<cache[siguiente_conjunto[conjunto]].getBloque()*k+k; j++){
                                    ram[j] = cache[siguiente_conjunto[conjunto]].getDato(b);
                                    b++;
                                }
                                cache[siguiente_conjunto[conjunto]].setModificado(false);
                                
                                //bloque de ram a linea de cache
                                cache[siguiente_conjunto[conjunto]].setEtiqueta(etiqueta);
                                cache[siguiente_conjunto[conjunto]].setBloque(bloque);
                                int a = 0;
                                for(int j = k*bloque; j<k*bloque+k; j++){
                                    cache[siguiente_conjunto[conjunto]].setDato(a, ram[j]);
                                    a++;
                                }
                                
                                //obtener valor cache
                                retorno = cache[siguiente_conjunto[conjunto]].getDatos()[palabra];
                                //sumar tiempo
                                tiempo[0] += 22;
                        }else{                             
                            //bloque de ram a linea de cache
                            cache[siguiente_conjunto[conjunto]].setEtiqueta(etiqueta);
                            cache[siguiente_conjunto[conjunto]].setBloque(bloque);
                            int a = 0;
                            for(int j = k*bloque; j<k*bloque+k; j++){
                                cache[siguiente_conjunto[conjunto]].setDato(a, ram[j]);
                                a++;
                            }
                            //obtener valor cache
                            retorno = cache[siguiente_conjunto[conjunto]].getDatos()[palabra];
                            //sumar tiempo
                            tiempo[0] += 11;
                        }
                        }else{
                            cache[siguiente_conjunto[conjunto]].setValido(true);
                            cache[siguiente_conjunto[conjunto]].setModificado(false);
                                
                                //bloque de ram a linea de cache
                                cache[siguiente_conjunto[conjunto]].setEtiqueta(etiqueta);
                                cache[siguiente_conjunto[conjunto]].setBloque(bloque);
                                int a = 0;
                                for(int j = k*bloque; j<k*bloque+k; j++){
                                    cache[siguiente_conjunto[conjunto]].setDato(a, ram[j]);
                                    a++;
                                }
                                
                                //obtener valor cache
                                retorno = cache[siguiente_conjunto[conjunto]].getDatos()[palabra];
                                //sumar tiempo
                                tiempo[0] += 11;
                        }
                        if(siguiente_conjunto[conjunto] == 3){
                            siguiente_conjunto[conjunto] = 0;
                        }else if(siguiente_conjunto[conjunto] == 7){
                            siguiente_conjunto[conjunto] = 4;
                        }else{
                            siguiente_conjunto[conjunto]++;
                        }
                    }else{
                        //obtener valor cache
                        retorno = cache[linea_asociativa].getDatos()[palabra];
                        //sumar tiempo
                        tiempo[0] += 1;
                    }
                    break;
                default:
                    break;
        }     
        
        return retorno;
    }
    
    public static void escribir(int i, int tipo,int dato ,double[] tiempo, int[]ram, Linea[] cache, int[] siguiente_asociativa, int[] siguiente_conjunto){
        int k=8;
        int m=8;
        //int retorno = 0;
        switch(tipo){
                case 0:
                    //retorno = ram[i];
                    ram[i]=dato;
                    tiempo[0] += 10;
                    break;
                case 1:
                    //definir palabra, linea, bloque, etiqueta
                    int palabra = i%k;
                    int bloque = i/k;
                    int linea = bloque%m;
                    int etiqueta = bloque/m;
                    //System.out.println("p: "+palabra+" b: "+bloque+" l: "+linea+" e: "+etiqueta);
                    if(cache[linea].isValido()){
                        if(cache[linea].getEtiqueta()==etiqueta){
                            cache[linea].setModificado(true);
                            //almacenar dato en cache
                            cache[linea].setDato(palabra, dato);
                            //sumar tiempo
                            tiempo[0] += 1;
                        }else{                            
                            if(cache[linea].isModificado()){
                                //linea de cache a bloque de ram
                                int b = 0;
                                for(int j = cache[linea].getEtiqueta()*m*k; j<cache[linea].getEtiqueta()*m*k+k; j++){
                                    ram[j] = cache[linea].getDato(b);
                                    b++;
                                }
                                //bloque de ram a linea de cache
                                cache[linea].setEtiqueta(etiqueta);
                                int a = 0;
                                for(int j = k*bloque; j<k*bloque+k; j++){
                                    cache[linea].setDato(a, ram[j]);
                                    a++;
                                }
                                cache[linea].setModificado(true);
                                
                                //almacenar dato en cache
                                cache[linea].setDato(palabra, dato);
                                //sumar tiempo
                                tiempo[0] += 22;
                            }else{
                                //bloque de ram a linea de cache
                                cache[linea].setEtiqueta(etiqueta);
                                int a = 0;
                                for(int j = k*bloque; j<k*bloque+k; j++){
                                    cache[linea].setDato(a, ram[j]);
                                    a++;
                                }
                                cache[linea].setModificado(true);
                                
                                //almacenar dato en cache
                                cache[linea].setDato(palabra, dato);
                                //sumar tiempo
                                tiempo[0] += 11;
                            }
                        }
                        int c = 0;
                                for(int j = k*bloque; j<k*bloque+k; j++){
                                    ram[j] = cache[linea].getDato(c);
                                    c++;
                               }
                    }else{
                        cache[linea].setValido(true);
                                               
                        //bloque de ram a linea de cache
                        cache[linea].setEtiqueta(etiqueta);
                        int a = 0;
                        for(int j = k*bloque; j<k*bloque+k; j++){
                            cache[linea].setDato(a, ram[j]);
                            a++;
                        }
                        cache[linea].setModificado(true); 
                        //almacenar dato en cache
                        cache[linea].setDato(palabra, dato);
                        //sumar tiempo
                        tiempo[0] += 11;
                    }
                    int c = 0;
                    break;
                case 2:
                    //etiqueta, palabra y bloque
                    palabra = i%k;
                    bloque = i/k;
                    etiqueta = bloque;
                    //ver si el dato esta en la cache
                    int linea_asociativa=-1;
                    for(int j=0; j<8;j++){
                        if(cache[j].getEtiqueta()==etiqueta ){
                            linea_asociativa=j;
                            break;
                        }
                    }
                    if(linea_asociativa==-1){
                        if(cache[siguiente_asociativa[0]].isValido()){
                            if(cache[siguiente_asociativa[0]].isModificado()){
                            //linea de cache a bloque de ram
                                int b = 0;
                                for(int j = cache[siguiente_asociativa[0]].getEtiqueta()*k; j<cache[siguiente_asociativa[0]].getEtiqueta()*k+k; j++){
                                    ram[j] = cache[siguiente_asociativa[0]].getDato(b);
                                    b++;
                                }                              
                                System.out.println(b);
                                //bloque de ram a linea de cache
                                cache[siguiente_asociativa[0]].setEtiqueta(etiqueta);
                                int a = 0;
                                for(int j = k*bloque; j<k*bloque+k; j++){
                                    cache[siguiente_asociativa[0]].setDato(a, ram[j]);
                                    a++;
                                }
                                cache[siguiente_asociativa[0]].setModificado(true);
                                //almacenar dato en cache
                                cache[siguiente_asociativa[0]].setDato(palabra, dato);
                                //sumar tiempo
                                tiempo[0] += 22;
                        }else{
                            //bloque de ram a linea de cache
                            cache[siguiente_asociativa[0]].setEtiqueta(etiqueta);
                            int a = 0;
                            for(int j = k*bloque; j<k*bloque+k; j++){
                                cache[siguiente_asociativa[0]].setDato(a, ram[j]);
                                a++;
                            }
                            cache[siguiente_asociativa[0]].setModificado(true);
                            //almacenar dato en cache
                            cache[siguiente_asociativa[0]].setDato(palabra, dato);
                            //sumar tiempo
                            tiempo[0] += 11;
                        }
                        }else{
                            cache[siguiente_asociativa[0]].setValido(true);
                            //bloque de ram a linea de cache
                            cache[siguiente_asociativa[0]].setEtiqueta(etiqueta);
                            int a = 0;
                            for(int j = k*bloque; j<k*bloque+k; j++){
                                cache[siguiente_asociativa[0]].setDato(a, ram[j]);
                                a++;
                            }
                            cache[siguiente_asociativa[0]].setModificado(true);
                            //almacenar dato en cache
                            cache[siguiente_asociativa[0]].setDato(palabra, dato);
                            //sumar tiempo
                            tiempo[0] += 11;
                        }
                        /*for(int j = 0; j<8; j++){
                            System.out.println(cache[siguiente_asociativa[0]].getDato(j));
                        }     */  
                        /*c = 0;
                                for(int j = k*bloque; j<k*bloque+k; j++){
                                    ram[j] = cache[siguiente_asociativa[0]].getDato(c);
                                    c++;
                                }*/
                        if(siguiente_asociativa[0]<7)
                            siguiente_asociativa[0]++;
                        else
                            siguiente_asociativa[0]=0;
                        
                    }else{
                        cache[linea_asociativa].setModificado(true);
                        //almacenar dato en cache
                        cache[linea_asociativa].setDato(palabra, dato);
                        //sumar tiempo
                        tiempo[0] += 1;
                        /*c = 0;
                                for(int j = k*bloque; j<k*bloque+k; j++){
                                    ram[j] = cache[linea_asociativa].getDato(c);
                                    c++;
                                }*/
                    }
                    break;
                case 3:
                    //etiqueta, palabra,conjunto y bloque
                    palabra = i%k;
                    bloque = i/k;
                    int conjunto = bloque%2;
                    etiqueta = bloque/2;                    
                    //ver si el dato esta en la cache
                    linea_asociativa=-1;
                    
                    for(int j=0+4*conjunto; j<4+4*conjunto;j++){
                        //System.out.println(cache[j].getEtiqueta());
                            if(cache[j].getEtiqueta()==etiqueta ){ 
                                linea_asociativa=j;
                                break;
                            }
                        }
                    if(linea_asociativa==-1){
                        if(cache[siguiente_conjunto[conjunto]].isValido()){
                            if(cache[siguiente_conjunto[conjunto]].isModificado()){
                            //linea de cache a bloque de ram
                                int b = 0;
                                for(int j = cache[siguiente_conjunto[conjunto]].getBloque()*k; j<cache[siguiente_conjunto[conjunto]].getBloque()*k+k; j++){
                                    ram[j] = cache[siguiente_conjunto[conjunto]].getDato(b);
                                    b++;
                                }                               
                                //bloque de ram a linea de cache
                                cache[siguiente_conjunto[conjunto]].setEtiqueta(etiqueta);
                                cache[siguiente_conjunto[conjunto]].setBloque(bloque);
                                int a = 0;
                                for(int j = k*bloque; j<k*bloque+k; j++){
                                    cache[siguiente_conjunto[conjunto]].setDato(a, ram[j]);
                                    a++;
                                }
                                cache[siguiente_conjunto[conjunto]].setModificado(true);
                                //almacenar valor en la cache
                                cache[siguiente_conjunto[conjunto]].setDato(palabra,dato);
                                //sumar tiempo
                                tiempo[0] += 22;
                        }else{                             
                            //bloque de ram a linea de cache
                            cache[siguiente_conjunto[conjunto]].setEtiqueta(etiqueta);
                            cache[siguiente_conjunto[conjunto]].setBloque(bloque);
                            int a = 0;
                            for(int j = k*bloque; j<k*bloque+k; j++){
                                cache[siguiente_conjunto[conjunto]].setDato(a, ram[j]);
                                a++;
                            }
                            cache[siguiente_conjunto[conjunto]].setModificado(true);
                            //almacenar valor en la cache
                            cache[siguiente_conjunto[conjunto]].setDato(palabra,dato);
                            //sumar tiempo
                            tiempo[0] += 11;
                        }
                        }else{
                            cache[siguiente_conjunto[conjunto]].setValido(true);
                             //bloque de ram a linea de cache
                            cache[siguiente_conjunto[conjunto]].setEtiqueta(etiqueta);
                            cache[siguiente_conjunto[conjunto]].setBloque(bloque);
                            int a = 0;
                            for(int j = k*bloque; j<k*bloque+k; j++){
                                cache[siguiente_conjunto[conjunto]].setDato(a, ram[j]);
                                a++;
                            }
                            cache[siguiente_conjunto[conjunto]].setModificado(true);
                            //almacenar valor en la cache
                            cache[siguiente_conjunto[conjunto]].setDato(palabra,dato);
                            //sumar tiempo
                            tiempo[0] += 11;
                        }
                        /*System.out.println("no esta "+conjunto+" "+palabra+" "+dato);
                        for(int j = 0; j<8; j++){
                            System.out.print(cache[siguiente_conjunto[conjunto]].getDato(j)+" ");
                        }
                        System.out.println("");*/
                        /*System.out.println("sig "+siguiente_conjunto[conjunto]+" dato "+dato);
                        if(conjunto==0){
                            if(siguiente_conjunto[conjunto]<3)
                                siguiente_conjunto[conjunto]++;
                            else
                                siguiente_conjunto[conjunto]=0;
                        }else{
                            if(siguiente_conjunto[conjunto]<7)
                                siguiente_conjunto[conjunto]++;
                            else
                                siguiente_conjunto[conjunto]=4;
                        }*/
                        if(siguiente_conjunto[conjunto] == 3){
                            siguiente_conjunto[conjunto] = 0;
                        }else if(siguiente_conjunto[conjunto] == 7){
                            siguiente_conjunto[conjunto] = 4;
                        }else{
                            siguiente_conjunto[conjunto]++;
                        }
                    }else{
                         
                        cache[linea_asociativa].setModificado(true);
                        //almacenar valor en la cache
                        cache[linea_asociativa].setDato(palabra,dato);
                        //sumar tiempo
                        tiempo[0] += 1;
                        //System.out.println("actual "+linea_asociativa);
                        /*System.out.println("esta "+conjunto+" "+palabra);
                        for(int j = 0; j<8; j++){
                            System.out.print(cache[linea_asociativa].getDato(j)+" ");
                        }
                        System.out.println("");*/
                    }
                    break;
                default:
                    break;
        }        
    }
}
