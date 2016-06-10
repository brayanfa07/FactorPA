
package factorlu;

public class Principal {
    
    
    public static void main(String[] args) {
        //FactorLU factor = new FactorLU();
        //Principal  principal =  new Principal();
        //Double [][] matrizA = {{2.0, 3.0}, {4.0, 5.0}};
        //Double [][] matrizB = {{1.0, 1.0}, {1.0, 1.0}};
        //factor.definirMatrizIdentidad(matrizA);
        //factor.multiplicarMatrices(matrizA, matrizB);
        //principal.multiplicarMatrices(matrizA, matrizB);
        
        //factor.buscarFactorLU();
        //
        
        InterfazPA app1 = new InterfazPA();
        //app1.
        
    }
    
    
    /*private void multiplicarMatrices(Double [][] matriz1, Double [][] matriz2){
        
        int filasResultado = matriz1.length;
        int columnasResultado = matriz2[0].length;
        Double[][] matrizResultado = new Double[filasResultado][columnasResultado];
        int dimensionComun = matriz1[0].length;
        double suma = 0;
        try{
            
            comprobarSiSePuedeMultplicarMatrices(matriz1, matriz2);
            for(int i = 0; i < filasResultado; i++){
                for(int j = 0; j < columnasResultado; j++){
                    for(int k = 0; k < dimensionComun; k++){
                        suma = suma + matriz1[i][k] * matriz2[k][j];
                    }
                    matrizResultado[i][j] = suma;
                    suma = 0;
                }
            }
            
            imprimirMatriz(matrizResultado);
        }
        catch(Exception e){
            System.out.println("No se pudo realizar la multiplicación de matrices");
            System.out.println(e.getMessage());
        }
        
        
        //return matrizResultado;    
    
    }
    
    
      public boolean comprobarSiSePuedeMultplicarMatrices(Double[][] matriz1, Double [][] matriz2){
       boolean valor = false;
       int columnasMatriz1 = matriz1[0].length;
       int filasMatriz2 = matriz2.length;
       
       try{
           if (columnasMatriz1 ==  filasMatriz2){
               valor = true;
           }       
       }
       catch(Exception e){
           System.out.println("No se pudo comprobar si las matrices se pueden multipplicar");
           System.out.println(e.getMessage());
       }
       return valor;
   }

    public void imprimirMatriz(Double[][] matriz){
        System.out.println("Hasta aqui llega");
        for (int t = 0; t< matriz.length; t++){
            for (int k = 0; k < matriz[0].length; k++){
               if(k == matriz.length - 1){
                   System.out.print(matriz[t][k] + "\n"); 
               }else  
                  System.out.print(matriz[t][k] + "\t");
            }
        }    
    } */     

      
    
    
}
