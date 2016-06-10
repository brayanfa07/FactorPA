
package factorlu;
import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Brayan
 */
public class InterfazPA{
    
    public JFrame ventana;
    
    public JPanel panelPrincipal;
    public JPanel panel1;
    public JPanel panel2;
    public JPanel panel3;
    public JPanel panel4;
    public JPanel panel5;
    public JPanel panel6;
    public JPanel panel7;
    public JPanel panel8;
    
    public JButton crearMatriz;
    public JButton calcularFactorizacionPA;
    public JButton calcularOtraVez;
    public JButton salir;
    
    public JLabel etiquetaFactorPA;
    public JLabel etiquetaCreacionMatriz;
    public JLabel etiquetaFilas;
    public JLabel etiquetaColumnas;
    public JLabel etiquetaMatrizTabla;
    public JLabel etiquetaMatrizL;
    public JLabel etiquetaMatrizU;
    public JLabel etiquetaPasoaPaso;
    
    public JTextField campoFilas;
    public JTextField campoColumnas;
    
    public JTable tablaMatrizA;
    public JTable tablaMatrizL;
    public JTable tablaMatrizU;
    public JTable tablaMatrizP;
    
    public JTextArea textoPasoaPaso;
    
    public InterfazPA(){
        construirPanelPrincipal();
        construirPanelInicial();
        construirPanelMatriz();
        construirPanelMatricesLU();
        construirPanelFinal();
        construirVentana();
    }
    
    private void construirPanelPrincipal(){
        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
    }

    private void construirPanelInicial(){
        panel1 = new JPanel();
        etiquetaFactorPA = new JLabel("Factorización PA");
        etiquetaFactorPA.setFont(new Font("Calibri", 0, 36));
        etiquetaFactorPA.setAlignmentX(Component.CENTER_ALIGNMENT);
        etiquetaFilas = new JLabel("Número de filas de la matriz");              
        etiquetaFilas.setAlignmentX(Component.CENTER_ALIGNMENT);
        campoFilas = new JTextField();
        campoFilas.setMinimumSize(new Dimension(100, 20));
        campoFilas.setPreferredSize(new Dimension(100, 20));
        campoFilas.setMaximumSize(new Dimension(200, 20));
        campoFilas.setAlignmentX(Component.CENTER_ALIGNMENT);        
        etiquetaColumnas =  new JLabel("Número de columnas de la matriz");
        etiquetaColumnas.setAlignmentX(Component.CENTER_ALIGNMENT);
        campoColumnas =  new JTextField();
        campoColumnas.setAlignmentX(Component.CENTER_ALIGNMENT);
        campoColumnas.setMinimumSize(new Dimension(100, 20));
        campoColumnas.setPreferredSize(new Dimension(100, 20));
        campoColumnas.setMaximumSize(new Dimension(200, 20));
        crearMatriz = new JButton("Crear matriz");
        crearMatriz.setAlignmentX(Component.CENTER_ALIGNMENT);    
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        
        panel1.add(etiquetaFactorPA);
        panel1.add(etiquetaFilas);
        panel1.add(campoFilas);
        Dimension minSize = new Dimension(5, 10);
        Dimension prefSize = new Dimension(5, 10);
        Dimension maxSize = new Dimension(Short.MAX_VALUE, 10);
        panel1.add(new Box.Filler(minSize, prefSize, maxSize));
        panel1.add(etiquetaColumnas);
        panel1.add(campoColumnas);
        panel1.add(new Box.Filler(minSize, prefSize, maxSize));
        panel1.add(crearMatriz);
        panelPrincipal.add(panel1);
    
    }
    
    private void construirPanelMatriz(){
        panel2 = new JPanel();
        etiquetaMatrizTabla = new JLabel("Matriz A. ¡Ingrésela! :) ");
        etiquetaMatrizTabla.setAlignmentX(Component.CENTER_ALIGNMENT);        
        tablaMatrizA = new JTable(5, 5);
        tablaMatrizA.setBorder(BorderFactory.createLineBorder(Color.black));
        tablaMatrizA.setMinimumSize(new Dimension(200, 80));
        tablaMatrizA.setPreferredSize(new Dimension(200, 80));
        tablaMatrizA.setMaximumSize(new Dimension(200, 80));
        calcularFactorizacionPA =  new JButton("Calcular Factorización LU");
        calcularFactorizacionPA.setAlignmentX(Component.CENTER_ALIGNMENT);
    
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        Dimension minSize = new Dimension(20, 20);
        Dimension prefSize = new Dimension(20, 20);
        Dimension maxSize = new Dimension(Short.MAX_VALUE, 20);
        panel2.add(new Box.Filler(minSize, prefSize, maxSize));       
        panel2.add(etiquetaMatrizTabla);
        panel2.add(tablaMatrizA);
        panel2.add(new Box.Filler(minSize, prefSize, maxSize));        
        panel2.add(calcularFactorizacionPA);
        panelPrincipal.add(panel2);
    }
    
    private void construirPanelMatricesLU(){
        panel3 = new JPanel();
        panel3.setLayout(new BoxLayout(panel3, BoxLayout.X_AXIS));
        construirPanelMatrizL();
        construirPanelMatrizU();
    }
    
    private void construirPanelMatrizL(){
        panel4 = new JPanel();
        panel4.setLayout(new BoxLayout(panel4, BoxLayout.Y_AXIS));
        etiquetaMatrizL = new JLabel("Matriz L");
        etiquetaMatrizL.setAlignmentX(Component.CENTER_ALIGNMENT);
        tablaMatrizL = new JTable(5, 5){
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };    
        tablaMatrizL.setMinimumSize(new Dimension(200, 80));
        tablaMatrizL.setPreferredSize(new Dimension(200, 80));
        tablaMatrizL.setMaximumSize(new Dimension(200, 80));
        tablaMatrizL.setBorder(BorderFactory.createLineBorder(Color.black));
        Dimension minSize = new Dimension(20, 20);
        Dimension prefSize = new Dimension(20, 20);
        Dimension maxSize = new Dimension(Short.MAX_VALUE, 20);
        panel4.add(new Box.Filler(minSize, prefSize, maxSize));        
        panel4.add(etiquetaMatrizL);
        panel4.add(tablaMatrizL);
        panel3.add(panel4);
        
    }
    
    private void construirPanelMatrizU(){
        panel5 = new JPanel();
        panel5.setLayout(new BoxLayout(panel5, BoxLayout.Y_AXIS));
        etiquetaMatrizU = new JLabel("Matriz U");
        etiquetaMatrizU.setAlignmentX(Component.CENTER_ALIGNMENT);        
        tablaMatrizU = new JTable(5,5){
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        tablaMatrizU.setMinimumSize(new Dimension(200, 80));
        tablaMatrizU.setPreferredSize(new Dimension(200, 80));
        tablaMatrizU.setMaximumSize(new Dimension(200, 80)); 
        tablaMatrizU.setBorder(BorderFactory.createLineBorder(Color.black));
        Dimension minSize = new Dimension(20, 20);
        Dimension prefSize = new Dimension(20, 20);
        Dimension maxSize = new Dimension(Short.MAX_VALUE, 20);
        panel5.add(new Box.Filler(minSize, prefSize, maxSize));        
        panel5.add(etiquetaMatrizU);
        panel5.add(tablaMatrizU);
        panel3.add(panel5);
        panelPrincipal.add(panel3);
    }
    
    
    private void construirPanelFinal(){
        panel6 = new JPanel();
        panel6.setLayout(new BoxLayout(panel6, BoxLayout.Y_AXIS));
        
        panel7 = new JPanel();
        panel7.setLayout(new BoxLayout(panel7, BoxLayout.Y_AXIS));
        Dimension minSize = new Dimension(10, 10);
        Dimension prefSize = new Dimension(10, 10);
        Dimension maxSize = new Dimension(10, 10);
        panel7.add(new Box.Filler(minSize, prefSize, maxSize));           
        etiquetaPasoaPaso =  new JLabel("Solución paso a paso");
        etiquetaPasoaPaso.setAlignmentX(Component.CENTER_ALIGNMENT);
        textoPasoaPaso = new JTextArea();
        textoPasoaPaso.setBorder(BorderFactory.createLineBorder(Color.black));
        textoPasoaPaso.setMinimumSize(new Dimension(300, 100));
        textoPasoaPaso.setPreferredSize(new Dimension(300,100));
        textoPasoaPaso.setMaximumSize(new Dimension(1000, 100));
        panel7.add(etiquetaPasoaPaso);
        panel7.add(textoPasoaPaso);
        
        
        panel8 = new JPanel();
        panel8.setLayout(new BoxLayout(panel8, BoxLayout.X_AXIS));
        calcularOtraVez = new JButton("Calcular otra vez");
        salir = new JButton("Salir del sistema");
        panel8.add(new Box.Filler(minSize, prefSize, maxSize));  
        panel8.add(calcularOtraVez);
        panel8.add(new Box.Filler(minSize, prefSize, maxSize));           
        panel8.add(salir);
        panel6.add(panel7);
        panel6.add(panel8);
        panelPrincipal.add(panel6);
    }
    
    private void construirVentana(){
        ventana = new JFrame("Factorización PA");
        ventana.setLayout(new BoxLayout(ventana.getContentPane(), BoxLayout.Y_AXIS));
        ventana.setBounds(100,0,450,630);
        ventana.add(panelPrincipal);
        ventana.setMinimumSize(new Dimension(450, 630));
        ventana.setResizable(true); 
        ventana.setVisible(true);    
    }
}

