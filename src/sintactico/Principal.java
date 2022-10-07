/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package compilador_final;

//import Lexer;
//import LexerColor;
import compilerTools.CodeBlock;
import compilerTools.Directory;
import compilerTools.ErrorLSSL;
import compilerTools.Functions;
import compilerTools.Grammar;
import compilerTools.Production;
import compilerTools.TextColor;
import compilerTools.Token;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.lang.Compiler.command;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.Caret;

/**
 *
 * @author Yamilka Gomez
 */
public class Principal extends javax.swing.JFrame {

    /**
     * Creates new form Pincipal
     */
    
    //
    JFileChooser seleccionar = new JFileChooser();
    File archivo;
    FileInputStream entrada;
    FileOutputStream salida;
    
    private final String title;
    private Directory directorio;
    private final ArrayList<Token> tokens;
    private final ArrayList<ErrorLSSL> errors;
    private final ArrayList<TextColor> textsColor;
    private Timer timerKeyReleased;
    private final ArrayList<Production> identProd;
    private final HashMap<String, String> identificadores;
    private boolean codeHasBeenCompiled = false;
    
    //Esto es para indicar el numero de filas en el Text Area.
    NumeroLinea numerolinea;
    
    public Principal() {
        initComponents();
        
        //Inicializacion del form.
        title = "Compilador Sintactico";
        setTitle(title);
        directorio = new Directory(this,ta_principal, title, ".comp");
        addWindowListener(new WindowAdapter() 
        {// Cuando presiona la "X" de la esquina superior derecha
            @Override
            public void windowClosing(WindowEvent e) 
            {
                directorio.Exit();
                System.exit(0);
            }
        });
        Functions.setLineNumberOnJTextComponent(ta_principal);
        timerKeyReleased = new Timer((int) (1000 * 0.3), (ActionEvent e) -> 
        {
            timerKeyReleased.stop();
            colorAnalysis();
        });
        Functions.insertAsteriskInName(this,ta_principal, () -> 
        {
            timerKeyReleased.restart();
        });
        tokens = new ArrayList<>();
        errors = new ArrayList<>();
        textsColor = new ArrayList<>();
        identProd = new ArrayList<>();
        identificadores = new HashMap<>();
        Functions.setAutocompleterJTextComponent(new String[]{"main(){}","int","float","string"}, ta_principal, () -> 
        {
            timerKeyReleased.restart();
        });
        //Fin de codigo de inicializacion.
    }
    
    public String GuardarArchivo(File archivo,String documento){
        String mensaje = null;
        try{
            salida = new FileOutputStream(archivo);
            byte[] bytxt = documento.getBytes();
            salida.write(bytxt);
            mensaje="Archivo Guardado";
            
        }   catch (Exception e){
        }
        return mensaje;
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
        jTable1 = new javax.swing.JTable();
        bt_abrir = new javax.swing.JButton();
        bt_compilar = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        ta_analizador_semantico = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        ta_principal = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        ta_analizador_lexico = new javax.swing.JTable();
        bt_limpiar = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Compilador AA.");
        setFont(new java.awt.Font("Consolas", 0, 10)); // NOI18N

        bt_abrir.setBackground(new java.awt.Color(255, 153, 255));
        bt_abrir.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        bt_abrir.setText("Abrir");
        bt_abrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_abrirActionPerformed(evt);
            }
        });

        bt_compilar.setBackground(new java.awt.Color(255, 153, 255));
        bt_compilar.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        bt_compilar.setText("Compilar");
        bt_compilar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_compilarActionPerformed(evt);
            }
        });

        ta_analizador_semantico.setEditable(false);
        ta_analizador_semantico.setColumns(20);
        ta_analizador_semantico.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        ta_analizador_semantico.setRows(5);
        jScrollPane4.setViewportView(ta_analizador_semantico);

        jLabel1.setBackground(new java.awt.Color(255, 153, 204));
        jLabel1.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 153, 255));
        jLabel1.setText("Analizador Lexico");

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 153, 255));
        jLabel3.setText("Analizador Sintactico");

        ta_principal.setColumns(20);
        ta_principal.setRows(5);
        jScrollPane8.setViewportView(ta_principal);

        ta_analizador_lexico.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        ta_analizador_lexico.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Componente léxico", "Lexema", "[Línea, Columna]"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ta_analizador_lexico.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(ta_analizador_lexico);

        bt_limpiar.setBackground(new java.awt.Color(255, 153, 255));
        bt_limpiar.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        bt_limpiar.setText("Limpiar");
        bt_limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_limpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(312, 312, 312))
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane4)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bt_abrir, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(bt_compilar, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_limpiar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(124, 124, 124))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bt_abrir)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(bt_compilar)
                                .addComponent(bt_limpiar)))
                        .addGap(12, 12, 12))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_compilarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_compilarActionPerformed
        // TODO add your handling code here:
        
        //Analizador lexico y semantico se realiza desde aqui.
        if (getTitle().contains("*") || getTitle().equals(title)) 
        {
            if (directorio.Save()) 
            {
                compile();
            }
        } 
        else 
        {
            compile();
        }
        
        //Analizador sintactico.
        
    }//GEN-LAST:event_bt_compilarActionPerformed

    private void bt_abrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_abrirActionPerformed
     
        if (directorio.Open()) 
        {
            colorAnalysis();
            clearFields();
        }
    }//GEN-LAST:event_bt_abrirActionPerformed

    private void bt_limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_limpiarActionPerformed
       
        directorio.New();
        clearFields();
    }//GEN-LAST:event_bt_limpiarActionPerformed

    
    //Codigo agegado y funciones:
    
    //Analisis lexico.
    private void lexicalAnalysis() 
    {
        // Extraer tokens
        Lexer lexer;
        try 
        {
            File codigo = new File("code.encrypter");
            FileOutputStream output = new FileOutputStream(codigo);
            byte[] bytesText = ta_principal.getText().getBytes();
            output.write(bytesText);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(new FileInputStream(codigo), "UTF8"));
            lexer = new Lexer(entrada);
            while (true) 
            {
                Token token = lexer.yylex();
                if (token == null) 
                {
                    break;
                }
                tokens.add(token);
            }
        } 
        catch (FileNotFoundException ex) 
        {
            System.out.println("El archivo no pudo ser encontrado... " + ex.getMessage());
        } 
        catch (IOException ex) 
        {
            System.out.println("Error al escribir en el archivo... " + ex.getMessage());
        }
    }

    //Analisis sintantico.
    private void syntacticAnalysis() 
    {
        Grammar gramatica = new Grammar(tokens, errors);
        
        /*Eliminacion de errores*/
        gramatica.delete(new String[]{"Error"});
        
        /* Agrupaciones de valores */
        gramatica.group("Valor","(Numero | Cadena)",true);
        
        /* Declaracion de variables */
        gramatica.group("Variable","Tipo_Dato Identificador Igual Valor Punto_Coma",true,identProd);
        
        gramatica.finalLineColumn();
        
        /* Errores en la declaracion de variables */
        gramatica.group("Variable","Tipo_Dato Identificador Igual Valor",true,
                2,"Error sintactico: {} falta el punto y coma. [#,%]");
        gramatica.group("Variable","Tipo_Dato Identificador Igual",true,
                3,"Error sintactico: {} falta el valor a asignar para la variable. [#,%]");
        gramatica.group("Variable","Tipo_Dato Identificador Valor",true,
                4,"Error sintactico: {} falta el operador de asignacion (=). [#,%]");
        gramatica.group("Variable","Tipo_Dato",true,
                5,"Error sintactico: {} falta el identificador para la variable. [#,%]");
        gramatica.group("Variable","Identificador",true,
                6,"Error sintactico: {} falta el tipo de datos para esta variable. [#,%]");
        
        gramatica.initialLineColumn();
        
        /* Eliminacion de tipos de datos y operadores de asignacion */
        gramatica.delete("Tipo_dato",
                7,"Error sintactico: {} El tipo de dato no esta en una declaracion. [#,%]");
        gramatica.delete("Igual",
                8,"Error sintactico: {} El operador de asignacion no esta en una declaracion. [#,%]");
        
        /* Agrupar identificadores y definir parametros */
        gramatica.group("Valor","Identificador",true);
        gramatica.group("Parametros","valor (Coma valor)+");
        
        
        
        /* Agrupar Funciones */
        gramatica.group("Funcion","Identificador Parentesis_A (Valor | Parametros)? Parentesis_C",true);
        
        /* Eliminacion de funciones */
        gramatica.delete("Funcion",
                9,"Error sintactico: {} La funcion no esta declarada correctamente [#,%]");
        
        /* Expresiones logicas */
        gramatica.loopForFunExecUntilChangeNotDetected(() -> {
            gramatica.group("Exp_Logica","Parentesis_A (Exp_Logica | Funcion) Parentesis_C");
            gramatica.group("Exp_Logica","(Funcion | Exp_Logica) (Op_Logico (Funcion | Exp_Logica))");
        });
        
        /* Eliminar los Operadores Logicos */
        gramatica.delete("Op_logico",
                10,"Error sintactico: {} El operador logico no esta contenido en una exprecion logica [#,%]");
        
        /* Agrupacion de Exp logicas como valor y como parametro */
        gramatica.group("Valor","Exp_Logica");
        gramatica.group("Parametros","valor (Coma valor)+");
        
        /* Agrupacion de estructuras de control */
        gramatica.group("Est_Control","(for | while | if | do)");
        gramatica.group("Est_Control_Comp","Est_Control Parentesis_A Parentesis_C");
        gramatica.group("Est_Control_Comp","Est_Control (Valor | Parametros)");
        gramatica.group("Est_Control_Comp","Est_Control Parentesis_A (Valor | Parametros) Parentesis_C");
        
        /* Eliminacion de estructuras de control */
        //gramatica.delete("Est_Control",
          //      10,"Error Sintactico: {} La estructura de control no esta declarada correctamente [#,%]");
        
        /* Eliminacion de parentesis */
        //gramatica.delete(new String[] {"Parentesis_A","Parentesis_C"},
          //      11,"Error Sintactico: {} El parentesis [] no esta contenido en una agrupacion. [#,%]");
        
        gramatica.finalLineColumn();
        
        /* Verificacion de punto y coma al final de una sentencia */
        
        /* Identificadores o variables */
        //gramatica.group("Variable_Pc","Variable Punto_Coma",true);
        //gramatica.group("Variable_Pc","Variable",
            //    12,"Error sintactico: {} Falta el punto y coma al final de la variable. [#,%]");
        
        /* Funciones */
        //gramatica.group("Funcion_Pc","Funcion Punto_Coma");
        //gramatica.group("Funcion_pc","Funcion",
          //      13,"Error sintactico: {} falta el punto y coma en las funcion [#,%])");
        
        gramatica.initialLineColumn();
        
        /* Eliminacion del Punto y Coma */
        gramatica.delete("Punto_Coma",
                14,"Error Sintactico: {} Este punto y como no esta al final de una sentencia [#,%]");
        
        /* sentencias  */
        gramatica.group("Sentencias","(Variable_Pc | Funcion_Pc)+");
        
        /* Main */
        gramatica.group("Main","(Tipo_Dato)? Main Parentesis_A (Valor | Parametros)? Parentesis_C Llave_A (Sentencias)* Llave_C");
        
        /* */
        gramatica.loopForFunExecUntilChangeNotDetected(() -> 
        {
            gramatica.group("Est_Control_Cp_LASLC","Est_Control_Cp Llave_A (Sentencias)? Llave_C",true );
            gramatica.group("Sentencias","(Sentencias | Est_Control_Cp_LALSLC)+");
            
        });
        
        /* Estructuras de control incompletas */
        gramatica.loopForFunExecUntilChangeNotDetected(() -> 
        {
           gramatica.initialLineColumn();
           
           gramatica.group("Est_Control_Cp_LASLC","Est_Control_Cp (Sentencias)? Llave_C",true,
                   15,"Error sintactico: {} Error Sintactico, falta la llave que abre la estructura [#,%]");
           
           gramatica.finalLineColumn();
           gramatica.group("Est_Control_Cp_LASLC","Est_control_cp Llave_A Sentencias",true,
                   15,"Error sintactico: {} Error sintactico falta la llave que cierra la estructura de control [#,%]");
           
           gramatica.group("Sentencias","(Sentencias | Est_Control_Cp_LASLC)");
        });
        
        /* Eliminamos la llave de apertura y cierre */
        //gramatica.delete(new String[] {"Llave_A","Llave_C"},
        //16,"Error Sintactico: {} La llave [] No esta contenida en una agrupacion [#,%]");
        
        /* Mostrar gramáticas */
        gramatica.show();
    }

    //Ejecucion
    private void run()
    {
        
        //crear el archivo temporal.py
        String direccion = System.getProperty("user.dir") + "\\src\\compilador_final\\temporal.py";
        File fichero = new File (direccion);
        
        
        // A partir del objeto File creamos y escribimos en el fichero el codigo traducido.
        try 
        {
            if (fichero.createNewFile())
            {
                System.out.println("El fichero se ha creado correctamente");
                
            }
            else
                System.out.println("No ha podido ser creado el fichero");
            
            
        } 
        catch (IOException ioe) 
        {
        }
        
        //ejecutar el archivo temporal.py
        try 
        {
            //Process exec = Runtime.getRuntime().exec("notepad " + direccion);
            String[] parametros = new String[2];
            parametros[0] = "notepad";
            parametros[1] = direccion;
            Process exec = Runtime.getRuntime().exec(parametros,null);
            exec.waitFor();
            System.out.println("Se ejecuto el archivo temporal.py");
        } 
        catch (IOException ex) 
        {
            System.out.println("No se pudo ejecutar el archivo temporal.py");
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (InterruptedException ex) 
        {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Eliminar el archivo luego de su ejecucion.
        try 
        {   
            Files.delete(Paths.get(direccion));
            System.out.println("Archivo temporal.py eliminado con exito");
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("No se pudo borrar el archivo temporal.py");
        }
    }
    
    private void colorAnalysis() 
    {
        /* Limpiar el arreglo de colores */
        textsColor.clear();
        /* Extraer rangos de colores */
        LexerColor lexerColor;
        try 
        {
            File codigo = new File("color.encrypter");
            FileOutputStream output = new FileOutputStream(codigo);
            byte[] bytesText = ta_principal.getText().getBytes();
            output.write(bytesText);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(new FileInputStream(codigo), "UTF8"));
            lexerColor = new LexerColor(entrada);
            while (true) 
            {
                TextColor textColor = lexerColor.yylex();
                if (textColor == null) 
                {
                    break;
                }
                textsColor.add(textColor);
            }
        } 
        catch (FileNotFoundException ex) 
        {
            System.out.println("El archivo no pudo ser encontrado... " + ex.getMessage());
        } 
        catch (IOException ex) 
        {
            System.out.println("Error al escribir en el archivo... " + ex.getMessage());
        }
        //Functions.colorTextPane(textsColor,ta_principal, new Color(40, 40, 40));
    }

    private void fillTableTokens() 
    {
        tokens.forEach(token -> {
            Object[] data = new Object[]{token.getLexicalComp(), token.getLexeme(), "[" + token.getLine() + ", " + token.getColumn() + "]"};
            Functions.addRowDataInTable(ta_analizador_lexico, data);
        });
    }

    private void printConsole() 
    {
        int sizeErrors = errors.size();
        if (sizeErrors > 0) 
        {
            Functions.sortErrorsByLineAndColumn(errors);
            String strErrors = "\n";
            for (ErrorLSSL error : errors) 
            {
                String strError = String.valueOf(error);
                strErrors += strError + "\n";
            }
            this.ta_analizador_semantico.setText("Compilación terminada...\n" + strErrors + "\nLa compilación terminó con errores...");
        } 
        else 
        {
            this.ta_analizador_semantico.setText("Compilación terminada...");
        }
        this.ta_analizador_semantico.setCaretPosition(0);
    }

    private void clearFields() 
    {
        Functions.clearDataInTable(ta_analizador_lexico);
       
        this.ta_analizador_semantico.setText("");
      
        tokens.clear();
        errors.clear();
        identProd.clear();
        identificadores.clear();
        codeHasBeenCompiled = false;
        this.jLabel3.setForeground(Color.white);
    }
    
    private void compile() 
    {
        clearFields();
        lexicalAnalysis();
        fillTableTokens();
        syntacticAnalysis();
        
        printConsole();
        codeHasBeenCompiled = true;
        
        if(errors.isEmpty())
        {
            this.jLabel3.setForeground(Color.green);
            
            //codigo para generar la tabla de simbolos.
            boolean bandera_main = false;
            for(int c=0;c<tokens.size();c++)
            {
                if("Identificador".equals(tokens.get(c).getLexicalComp()) || "Main".equals(tokens.get(c).getLexicalComp()))
                {
                    
                    String nombre = tokens.get(c).getLexeme();
                    String tipo = "";
                    String ambito = "";
                    String visibilidad = "Public";
                    
                    //Asignando los tipos.
                    if("Main".equals(tokens.get(c).getLexicalComp()))
                    {
                        bandera_main = true;
                        tipo="Void";
                    }
                    else
                    {
                        if(c>0)
                        if("Tipo_Dato".equals(tokens.get(c-1).getLexicalComp()))
                        {
                            tipo=tokens.get(c-1).getLexeme();
                        }
                    }
                    
                    if(bandera_main && !"main".equals(nombre))
                    {
                        ambito = "Local";
                    }
                    else
                    {
                        ambito = "Global";
                    }
                    
                    Object[] data = {nombre,tipo,ambito,visibilidad};
                   
                }
            }
            
            
        }
        else
        {
            this.jLabel3.setForeground(Color.red);
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
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Principal().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_abrir;
    private javax.swing.JButton bt_compilar;
    private javax.swing.JButton bt_limpiar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable ta_analizador_lexico;
    private javax.swing.JTextArea ta_analizador_semantico;
    private javax.swing.JTextArea ta_principal;
    // End of variables declaration//GEN-END:variables

}