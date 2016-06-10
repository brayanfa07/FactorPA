package factorlu;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Brayan
 */


//Se inicializa la clase FactorLU
public class FactorLU {
    
    private Double [][] matrizA;
    private Double [][] matrizB;
    private Double [][] matrizBinversa;
    private Double [][] matrizP;
    private Double [][] matrizL;
    private Double [][] matrizU;
    
    //Constructor de la clase FACTORLU
    public FactorLU(){
        matrizA = null;
        matrizB = null;
        matrizBinversa = null;
        matrizP = null;
        matrizL = null;
        matrizU = null;        
    }
    
    //Método que busca el FACTOR LU
    public void buscarFactorLU(){
        System.out.println("Sistema que busca un factor LU");
        calcularMatrizEscalonada(matrizA);
    }
    
    //Operacion escalonada reducida
    public Double[][] calcularMatrizEscalonada(Double [][] matriz){
        System.out.println("Calculando matriz escalonada...");
        matrizB = definirMatrizIdentidad(matriz);
        Double[][] matrizTemporal = matriz;
        int filas = matriz.length;
        int columnas = matriz[0].length;
        int filaDondeHayUno = 0;
        int filaDondeHayCero = 0;
        int filaConLaQueSePuedeSumar = 0;
        int filaConLaQueSePuedeSumarYMultiplicar = 0;
        
        //********************************************//
        //Ver pivotes primero, convertirlos a 1
        for(int j = 0; j < filas; j++){
            for (int i = 0; i < columnas; i++){
                if (i == j){
                    if (matrizTemporal[i][j] != Double.parseDouble("1")){
                        if (verSiHayUnosEnOtraFila(matrizTemporal, i, j)  == true){
                            filaDondeHayUno = verFilaDondeHayUno(matriz, i, j);
                            matrizTemporal = intercambiarFilas(matrizTemporal, i, filaDondeHayUno, "matrizB");
                        }
                        else{
                            matrizTemporal = multiplicarPorInversoMultiplicativo(matrizTemporal, i, j, "matrizB");
                        }
                    }  
                }
                if (j > i){
                    if (matrizTemporal[i][j] != Double.parseDouble("1")){
                        if (verSiHayCerosEnOtraFila(matrizTemporal, i, j) == true){
                            filaDondeHayCero = verFilaDondeHayCero(matrizTemporal, i, j);
                            matrizTemporal = intercambiarFilas(matrizTemporal, i, filaDondeHayCero, "matrizB");
                        }
                        else{
                            if (verSiSePuedeSumarFilas(matrizTemporal, i, j) == true){
                                filaConLaQueSePuedeSumar = verConCualFilaSumar(matrizTemporal, i, j);
                                matrizTemporal = sumarFilas(matrizTemporal, i, filaConLaQueSePuedeSumar, "matrizB");
                            }
                            else{
                                filaConLaQueSePuedeSumarYMultiplicar = verConCualFilaSumarYMultiplicar(matrizTemporal, i, j);
                                matrizTemporal = sumarYMultiplicarFilas(matrizTemporal, i, j, filaConLaQueSePuedeSumarYMultiplicar, "matrizB");
                            }
                        }
                    }
                }
            }
        }
        System.out.println("Se ha calculado la matriz escalonada");
        matrizU = matrizTemporal;
        imprimirMatriz(matrizU);
        return matrizU;
    }

    
    
    
    //Método que hace cambios de filas e una matriz
    public Double[][] intercambiarFilas(Double [][] matriz, int filaFuente, int filaDestino, String matrizElemental){
        System.out.println("Intercambiando filas de la matriz...");
        Double [][] matrizConFilasCambiadas = matriz;
        Double [] filaTemporal = matriz[filaDestino]; 
        matrizConFilasCambiadas[filaDestino] = matrizConFilasCambiadas[filaFuente];
        matrizConFilasCambiadas[filaFuente] = filaTemporal;
        
        //**********************************************
        //matriz Elemental
        
        if (matrizElemental.equals("matrizB")){
            Double [] filaTemporalMatrizElemental = matrizB[filaDestino];
            matrizB[filaDestino] = matrizB[filaFuente];
            matrizB[filaFuente] = filaTemporalMatrizElemental;              
        }
        else{
            if(matrizElemental.equals("matrizBinversa")){
                Double [] filaTemporalMatrizElemental = matrizBinversa[filaDestino];
                matrizBinversa[filaDestino] = matrizBinversa[filaFuente];
                matrizBinversa[filaFuente] = filaTemporalMatrizElemental;            
            } 
        }
      
        //*********************************************
        
        imprimirMatriz(matrizConFilasCambiadas);
        System.out.println("Se ha intercambiado las filas de la matriz");
        System.out.println("Matriz B");
        imprimirMatriz(matrizB);
        return matrizConFilasCambiadas;
    }
    
    
    //Operacion de multiplicacion de matrices
    public Double[][] multiplicarMatrices(Double [][] matriz1, Double [][] matriz2){
        System.out.println("Calculando si se pueden multiplicar las matrices...");
        
        int filasResultado = matriz1.length;
        int columnasResultado = matriz2[0].length;
        Double[][] matrizResultado = new Double[filasResultado][columnasResultado];
        int dimensionComun = matriz1[0].length;
        double suma = 0;
        
        try{
            
            if (comprobarSiSePuedeMultiplicarMatrices(matriz1, matriz2) == true){
                for(int i = 0; i < filasResultado; i++){
                    for(int j = 0; j < columnasResultado; j++){
                        for(int k = 0; k < dimensionComun; k++){
                            suma = suma + matriz1[i][k] * matriz2[k][j];
                        }
                        matrizResultado[i][j] = suma;
                        suma = 0;
                    }
                }
                System.out.println("--> Multiplicacion de matrices realizada");
                imprimirMatriz(matrizResultado);
            }
        }
        catch(Exception e){
            System.out.println("No se pudo realizar la multiplicación de matrices :( ");
            System.out.println(e.getMessage());
        }
        return matrizResultado;    
    }
    
    //Operacion de calcular matriz inversa
    public Double [][] calcularMatrizInversaGaussJordan(Double [][] matriz){
        System.out.println("Calculando matriz inversa por Gauss-Jordan...");
        matrizB = definirMatrizIdentidad(matriz);
        Double[][] matrizTemporal = matriz;
        int filas = matriz.length;
        int columnas = matriz[0].length;
        int filaDondeHayUno = 0;
        int filaDondeHayCero = 0;
        int filaConLaQueSePuedeSumar = 0;
        int filaConLaQueSePuedeSumarYMultiplicar = 0;
        
        //********************************************//
        //Ver pivotes primero, convertirlos a 1
        for(int j = 0; j < filas; j++){
            for (int i = 0; i < columnas; i++){
                if (i == j){
                    if (matrizTemporal[i][j] != Double.parseDouble("1")){
                        if (verSiHayUnosEnOtraFila(matrizTemporal, i, j)  == true){
                            filaDondeHayUno = verFilaDondeHayUno(matriz, i, j);
                            matrizTemporal = intercambiarFilas(matrizTemporal, i, filaDondeHayUno, "matrizBinversa");
                        }
                        else{
                            matrizTemporal = multiplicarPorInversoMultiplicativo(matrizTemporal, i, j, "matrizBinversa");
                        }
                    }  
                }
                if (j > i){
                    if (matrizTemporal[i][j] != Double.parseDouble("1")){
                        if (verSiHayCerosEnOtraFila(matrizTemporal, i, j) == true){
                            filaDondeHayCero = verFilaDondeHayCero(matrizTemporal, i, j);
                            matrizTemporal = intercambiarFilas(matrizTemporal, i, filaDondeHayCero, "matrizB");
                        }
                        else{
                            if (verSiSePuedeSumarFilas(matrizTemporal, i, j) == true){
                                filaConLaQueSePuedeSumar = verConCualFilaSumar(matrizTemporal, i, j);
                                matrizTemporal = sumarFilas(matrizTemporal, i, filaConLaQueSePuedeSumar, "matrizBinversa");
                            }
                            else{
                                filaConLaQueSePuedeSumarYMultiplicar = verConCualFilaSumarYMultiplicar(matrizTemporal, i, j);
                                matrizTemporal = sumarYMultiplicarFilas(matrizTemporal, i, j, filaConLaQueSePuedeSumarYMultiplicar, "matrizBinversa");
                            }
                        }
                    }
                }
            }
        }
        System.out.println("Se ha calculado la matriz inversa por Gauss-Jordan");
        //matrizU = matrizTemporal;
        //imprimirMatriz(matrizU);
        return matrizBinversa;
    }

    //Covierte la matriz a Double (por si sólo se ingresan enteros en la tabla matriz)
    public Double[][] convierteADouble(Double[][] matriz){
        int filas  = matriz.length;
        int columnas = matriz[0].length;
        Double[][] resultado = new Double[filas][columnas];
        for (int i = 0; i < filas; i++){
          for (int j = 0; j < columnas; j++){
              resultado[i][j] = matriz[i][j];
          }  
        }
        return resultado;
    }    
    
    //Método que imprime una matriz en pantalla
    public void imprimirMatriz(Double[][] matriz){
        System.out.println("Mostrando la matriz..");
        for (int t = 0; t< matriz.length; t++){
            for (int k = 0; k < matriz[0].length; k++){
               if(k == matriz.length - 1){
                   System.out.print(matriz[t][k] + "\n"); 
               }else  
                  System.out.print(matriz[t][k] + "\t");
            }
        }    
    }
    
    //Método que permite obtener una fila de uan matriz
    public Double[] obtenerFila(Double[][] matriz, int fila){
        
        Double [] filaADevolver = null;
        
        try{
            filaADevolver = matriz[fila];
        }
        catch(Exception e){
            System.out.println("No se pudo obtener la fila de la matriz");
            System.out.println(e.getMessage());
        }
        return filaADevolver;
    }
    
    //Método que permite devolver una columna de una matriz
    public Double[] obtenerColumna(Double [][] matriz, int columna){
        
        Double [] columnaADevolver = null;
        ArrayList<Double> columnaADevolverArrayList;
        columnaADevolverArrayList = new ArrayList<>();
        int cantidadFilas = matriz.length;
 
        try{
            for (int i = 0; i < cantidadFilas; i++){
                columnaADevolverArrayList.add(matrizA[i][columna]);
            }
            columnaADevolver = new Double[columnaADevolverArrayList.size()];
            columnaADevolverArrayList.toArray(columnaADevolver);   
        }
        catch(Exception e){
            System.out.println("No se pudo obtener la columna de la matriz");
            System.out.println(e.getMessage());
        }
        return columnaADevolver;
    }

    //Método que establece una matriz identidad 
    public Double [][] definirMatrizIdentidad(Double [][] matriz){
        System.out.println("Estableciendo matriz identidad para operar...");
        int filas = matriz.length;
        int columnas = matriz[0].length;
        Double [][] matrizIdentidad = new Double[filas][columnas];  
        
        for (int i = 0; i < filas; i++){
            for (int j = 0; j < columnas; j++){
                if (i == j){
                    matrizIdentidad[i][j] = Double.parseDouble("1");               
                }
                else{
                    matrizIdentidad[i][j] = Double.parseDouble("0");   
                }
            }        
        }
        System.out.println("Matriz identidad establecida");
        imprimirMatriz(matrizIdentidad);
        return matrizIdentidad;
    }
    
    //Método que permite en cual fila hay un uno
    public int verFilaDondeHayUno(Double[][] matriz, int fila, int columna){
        System.out.println("Comprobando en cual fila hay un uno...");
        int filaDondeHayUno = 0;
        boolean filaResultadoModificado = false;
        int filas = matriz.length;
        
        if (verSiHayUnosEnOtraFila(matriz, fila, columna) == true){
            for (int i = 0; i < filas; i++){
                if (fila != i && filaResultadoModificado == false){
                    if (matriz[i][columna] == Double.parseDouble("1")){
                        filaDondeHayUno = i;
                        System.out.println("Hay un uno en la fila " + filaDondeHayUno);
                        filaResultadoModificado = true;
                    }
                }
            }            
        }
        return filaDondeHayUno;
    }
    
    //Método que permite en cual fila hay un cero
    public int verFilaDondeHayCero(Double[][] matriz, int fila, int columna){
        System.out.println("Comprobando en cual fila hay un cero...");
        int filaDondeHayCero = 0;
        boolean filaResultadoModificado = false;
        int filas = matriz.length;
        
        if (verSiHayCerosEnOtraFila(matriz, fila, columna) == true){
            for (int i = 0; i < filas; i++){
                if (fila != i && filaResultadoModificado == false){
                    if (matriz[i][columna] == Double.parseDouble("0")){
                        filaDondeHayCero = i;
                        System.out.println("Hay un cero en la fila " + filaDondeHayCero);
                        filaResultadoModificado = true;
                    }
                }
            }            
        }
        return filaDondeHayCero;
    }
    
    
   //Método que permite ver si hay unos en otras filas
    public boolean verSiHayUnosEnOtraFila (Double [][] matriz, int fila, int columna){
        System.out.println("Comprobando si hay unos en otras filas...");
        
        int filas = matriz.length;
        boolean hayUnos = false;
        
        for (int i = 0; i < filas; i++){
            if (fila != i && hayUnos == false){
                if (matriz[i][columna] == Double.parseDouble("1")){
                    hayUnos = true;
                    System.out.println("Si hay unos en otras filas");
                }
            }
        }
        if (hayUnos == false){
            System.out.println("No hay unos en otras filas");
        }
        return hayUnos;       
    }
    
    //Método que permite ver si hay ceros en otras filas
    public boolean verSiHayCerosEnOtraFila (Double [][] matriz, int fila, int columna){
        System.out.println("Comprobando si hay ceros en otras filas...");
        
        int filas = matriz.length;
        boolean hayCeros = false;
        
        for (int i = 0; i < filas; i++){
            if (fila != i && hayCeros == false){
                if (matriz[i][columna] == Double.parseDouble("0")){
                    hayCeros = true;
                    System.out.println("Sí hay ceros en otras filas");
                }
            }
        }
        if (hayCeros == false){
            System.out.println("No hay ceros en otras filas");
        }
        return hayCeros;     
   }
   
   
   //Método que permite verificar si se pueden multiplicar matrices
   public boolean comprobarSiSePuedeMultiplicarMatrices(Double[][] matriz1, Double [][] matriz2){
       System.out.println("Comprobando si se pueden multiplicar las matrices...");
       boolean valor = false;
       int columnasMatriz1 = matriz1[0].length;
       int filasMatriz2 = matriz2.length;
       
       try{
           if (columnasMatriz1 ==  filasMatriz2){
               valor = true;
               System.out.println("Las matrices si se pueden multiplicar");
           }       
       }
       catch(Exception e){
           System.out.println("No se pudo comprobar si las matrices se pueden multipplicar");
           System.out.println(e.getMessage());
       }
       if (valor == false){
           System.out.println("Las matrices no se pueden multiplicar");
       }
       return valor;
   }
   
   //Método que permite ver si se puede sumar o restar
   public boolean verSiSePuedeSumarFilas(Double [][] matriz, int fila, int columna){
       System.out.println("Comprobando si se puede sumar con otra fila...");
       boolean valor = false;
       int filas = matriz.length;
       for (int i = 0; i < filas; i++){
           if ((i != fila) && (valor == false)){
              if ((matriz[i][columna]) + (matriz[fila][columna]) == 0){
                  valor = true;
                  System.out.println("Si se puede sumar la fila con otra");
              }
           }
       }
       if (valor == false){
           System.out.println("No se puede sumar la fila con otra");
       }
       return valor;
   }
   
   //Método que permite ver con cual fila se puede sumar
   public int verConCualFilaSumar(Double [][] matriz, int fila, int columna){
       System.out.println("Comprobando con cual fila se puede sumar...");
       
       int filaConLaQueSePuedeSumar = 0;
       boolean filaResultadoModificado = false;
       int filas = matriz.length;
       if (verSiSePuedeSumarFilas(matriz, fila, columna) == true){
           for (int i = 0; i < filas; i++){
               if ((i != fila) && (filaResultadoModificado == false)){
                   if ((matriz[fila][columna] + matriz[i][columna]) == 0){
                       filaConLaQueSePuedeSumar = i;
                       filaResultadoModificado = true;
                   }
               }
           }        
       }
       if (filaResultadoModificado == true){
           System.out.println("Se puede sumar con la fila " + (filaConLaQueSePuedeSumar + 1));
       }
       return filaConLaQueSePuedeSumar;
   }
   
   
   public Double[][] sumarYMultiplicarFilas(Double [][] matriz, int filaOrigen, int columnaOrigen, int filaAuxiliar, String matrizElemental){
       System.out.println("Sumando y multiplicando las filas de la matriz ");
       Double [][] matrizConFilasSumadasYMultiplicadas = matriz;
       Double constante = 0.0;
       int columnas = matriz[0].length;
       
       constante = (-1 *  matriz[filaOrigen][columnaOrigen]) / filaAuxiliar;
       for (int i = 0; i < columnas; i++){
           matrizConFilasSumadasYMultiplicadas[filaOrigen][columnaOrigen] = (constante * (matrizConFilasSumadasYMultiplicadas[filaOrigen][columnaOrigen]) + matrizConFilasSumadasYMultiplicadas[filaAuxiliar][columnaOrigen]);
       }
       
       //***************************************************************************
       //Matriz elemental

        if (matrizElemental.equals("matrizB")){
            for (int i = 0; i < columnas; i++){
                matrizB[filaOrigen][columnaOrigen] = (constante * (matrizB[filaOrigen][columnaOrigen]) + matrizB[filaAuxiliar][columnaOrigen]);
            }       
        }
        else{
            if(matrizElemental.equals("matrizBinversa")){
                for (int i = 0; i < columnas; i++){
                    matrizBinversa[filaOrigen][columnaOrigen] = (constante * (matrizBinversa[filaOrigen][columnaOrigen]) + matrizBinversa[filaAuxiliar][columnaOrigen]);
                }     
            } 
        } 

       
       //***************************************************************************
       
       return matrizConFilasSumadasYMultiplicadas;
   }
   
   //Método que permite sumar filas de una matriz
   public Double[][] sumarFilas(Double[][] matriz, int filaOrigen, int filaDestino, String matrizElemental){
       System.out.println("Calculando la suma de filas...");
       Double[][] matrizSumada = matriz;
       int columnas = matriz[0].length;
       
       for (int i = 0; i < columnas; i++){
           matrizSumada[filaOrigen][i] = matrizSumada[filaOrigen][i] + matrizSumada[filaDestino][i]; 
       }
       //**********************************************************************************
       //Matriz Elemental

        if (matrizElemental.equals("matrizB")){
            for (int i = 0; i < columnas; i++){
                matrizB[filaOrigen][i] = matrizB[filaOrigen][i] + matrizB[filaDestino][i];
            }             
        }
        else{
            if(matrizElemental.equals("matrizBinversa")){
                for (int i = 0; i < columnas; i++){
                    matrizBinversa[filaOrigen][i] = matrizBinversa[filaOrigen][i] + matrizBinversa[filaDestino][i];
                }          
            } 
        }       
        
       //***********************************************************************************
       System.out.println("--> Se ha realizado la suma de filas");
       return matrizSumada;
   }
   
   public int verConCualFilaSumarYMultiplicar(Double [][] matriz, int fila, int columna){
       int filaConLaCualSumarYMultiplicar = 0;
       boolean filaResultadoModificada = false;
       int filas = matriz.length; 
   
       for (int i = 0; i < filas; i++){
           if ((i != fila)  && (matriz[i][columna] != 0)){
               filaConLaCualSumarYMultiplicar = i;
               filaResultadoModificada = true;
           }
       }
       if (filaResultadoModificada == true){
           System.out.println("La fila con la cual se puede sumar y multiplicar es " + (filaConLaCualSumarYMultiplicar + 1));
       
       }
       else{
           System.out.println("No se ha podido determinar una fila con la cual sumar u multiplicar");
       }
       return filaConLaCualSumarYMultiplicar;
   }
   
    
   //Método que calcula el inverso multiplicativo de un elemento de la matriz
   public Double inversoMultiplicativo(Double[][] matriz, int fila, int columna){
       Double inverso  = Math.pow(matriz[fila][columna], -1);
      
       return inverso;
   }
   
   //Método que calcula el inverso aditivo de un elemento de la matriz
   public Double inversoAditivo(Double [][] matriz, int fila, int columna){
       Double inverso = -1 * matriz[fila][columna];
       
       return inverso;
   }
   
   //Método que multiplica por el inverso multiplicativo toda una fila
   public Double[][] multiplicarPorInversoMultiplicativo(Double [][] matriz, int fila, int columna, String matrizElemental){
       System.out.println("Multiplicando la fila de la matriz por su inverso multiplicativo");
       
       Double numeroInverso = inversoMultiplicativo(matriz, fila, columna);
       Double[][] matrizMultiplicada = matriz;
       int columnas = matriz[0].length;
       
       for (int i = 0; i < columnas; i++){
           matrizMultiplicada[fila][i] = matrizMultiplicada[fila][i] * numeroInverso;
       }
       //**************************************************************************
       //Matriz Elemental
        if (matrizElemental.equals("matrizB")){
            for (int i = 0; i < columnas; i++){
                matrizB[fila][i] = matrizB[fila][i] * numeroInverso;
            }            
        }
        else{
            if(matrizElemental.equals("matrizBinversa")){
                for (int i = 0; i < columnas; i++){
                    matrizBinversa[fila][i] = matrizBinversa[fila][i] * numeroInverso;
                }          
            } 
        }       
       
       //***************************************************************************
       System.out.println("--> Fila de la matriz multiplicada por su inverso multiplicativo");
       imprimirMatriz(matrizMultiplicada);
       System.out.println("Matriz B");
       imprimirMatriz(matrizB);
       return matrizMultiplicada;
   }

    public Double[][] getMatrizA() {
        return matrizA;
    }


    public void setMatrizA(Double[][] pMatrizA) {
        this.matrizA = pMatrizA;
    }


    public Double[][] getMatrizB() {
        return matrizB;
    }


    public void setMatrizB(Double[][] matrizB) {
        this.matrizB = matrizB;
    }


    public Double[][] getMatrizBinversa() {
        return matrizBinversa;
    }


    public void setMatrizBinversa(Double[][] matrizBinversa) {
        this.matrizBinversa = matrizBinversa;
    }
    
}

    
    
    
    


