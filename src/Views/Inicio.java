/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;
import Analizadores.LexicoArit;
import Analizadores.SintacticoArit;
import Analizadores.analisis_sintactico_AST;
import Entorno.Entorno;
import Estructuras.NodoAST;
import Estructuras.Singleton;
import Instruccion.Instruccion;
import java.awt.Component;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author EG
 */
public class Inicio extends javax.swing.JFrame {
     javax.swing.JTextArea tex1;
     
     
     Font fuente=new Font("Monospaced", Font.PLAIN, 15);
     int cont=0;
    /**
     * Creates new form Inicio
     */
    public Inicio() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        salidaConsola = new javax.swing.JTextArea();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        idAnalizar = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        salidaConsola.setColumns(20);
        salidaConsola.setRows(5);
        jScrollPane1.setViewportView(salidaConsola);

        idAnalizar.setText("Analizar");
        idAnalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idAnalizarActionPerformed(evt);
            }
        });

        jMenu1.setText("Abrir");
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Guardar");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 823, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1))
                .addGap(18, 18, 18)
                .addComponent(idAnalizar)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(idAnalizar)
                        .addGap(0, 301, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
      
        String aux = "";
				String texto = "";
				JFileChooser chooser = new JFileChooser("CargaMasiva");
				FileNameExtensionFilter filter = new FileNameExtensionFilter(  "dat",
						"SWAG","txt","arit");
				chooser.setFileFilter(filter);
				int returnVal = chooser.showOpenDialog(getContentPane());
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File abre=chooser.getSelectedFile();
					FileReader archivos = null;
					try {
						archivos = new FileReader(abre);
					} catch (FileNotFoundException e3) {
						e3.printStackTrace();
					}
					BufferedReader lee=new BufferedReader(archivos);
					 try {
						while((aux=lee.readLine())!=null)
						  {
						     texto+= aux+ "\n";
						  }
					} catch (IOException e2) {
						e2.printStackTrace();
					}
				         try {
							lee.close();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
				     
				}
        tex1 = new javax.swing.JTextArea();
        JScrollPane scroll = new JScrollPane(tex1);
        tex1.setFont(fuente);
        tex1.setText(texto);
        tex1.isEditable();
        tex1.isEnabled();
        jTabbedPane1.addTab("Archivo"+(cont+1), scroll);
        cont++;
    }//GEN-LAST:event_jMenu1MouseClicked

    private void idAnalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idAnalizarActionPerformed
          try{                                           
              JScrollPane scroll = new JScrollPane();
              salidaConsola.setText("");
              Singleton.getInstance().limpiarEntorno();
              Singleton.getInstance().funcionesSistema();
              Component comp = jTabbedPane1.getComponentAt(jTabbedPane1.getSelectedIndex());
              scroll=(javax.swing.JScrollPane) comp;
              String datos = tex1.getText();
              LinkedList<Instruccion> resultados;
              LexicoArit lexico = new LexicoArit(new BufferedReader(new StringReader(datos)));
              SintacticoArit sintactico = new SintacticoArit(lexico);
              try{
                  sintactico.parse();
              }catch(Exception e){
                  System.out.println(e);
              }
              resultados=sintactico.resultado;
              Entorno ent = new Entorno(null);
              for(Instruccion ins : resultados){
                  ins.ejecutar(ent);
              }
              ent.graficarTabla();
              Graficar(datos);
          }catch(IOException ex){
             Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null,ex);
        }
    }//GEN-LAST:event_idAnalizarActionPerformed
    private void Graficar(String datos){
        NodoAST raiz;
        LexicoArit lexico = new LexicoArit(new BufferedReader(new StringReader(datos)));
        analisis_sintactico_AST sintactico = new analisis_sintactico_AST(lexico);
        try{
            sintactico.parse();
        }catch(Exception e){
            System.out.println(e);
        }
        raiz = sintactico.raiz;
        raiz.Graficar(raiz);
         try {
             raiz.generarArchivo();
             System.out.println("Grafica completa");
         } catch (IOException ex) {
             Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inicio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton idAnalizar;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    public static javax.swing.JTextArea salidaConsola;
    // End of variables declaration//GEN-END:variables
}
