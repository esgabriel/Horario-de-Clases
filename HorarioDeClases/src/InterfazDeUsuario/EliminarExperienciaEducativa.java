/**
 *Esta clase contiene los metodos para eliminar una Experiencia Educativa mediante una interfaz de usuario
 *
 * @author Luis Angel Barrientos Perez
 * @author Carlos Antonio Gallegos Palencia
 * @author Jaime Antonio Hernandez Cabrera
 * @author Gabriel Reyes Cruz
 * @author Jose Angel Rincon Martinez
 * @version 0.1
 */
package InterfazDeUsuario;

import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import horariodeclases.Archivo;
import horariodeclases.Horario;
import javax.swing.JOptionPane;

public class EliminarExperienciaEducativa extends javax.swing.JInternalFrame {

    private String horaModificada;
    private String materiaModificada;

    public EliminarExperienciaEducativa() {
        initComponents();
        actualizarTabla();

        //Metodo para quitar bordes en el JInternalFrame
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI bi = (BasicInternalFrameUI) this.getUI();
        bi.setNorthPane(null);

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
        tablaHorario = new javax.swing.JTable();
        botonEliminar = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1020, 580));

        tablaHorario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null}
            },
            new String [] {
                "Hora", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes"
            }
        ));
        tablaHorario.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tablaHorario);

        botonEliminar.setBackground(new java.awt.Color(24, 82, 157));
        botonEliminar.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        botonEliminar.setForeground(new java.awt.Color(255, 255, 255));
        botonEliminar.setText("Eliminar");
        botonEliminar.setBorder(null);
        botonEliminar.setBorderPainted(false);
        botonEliminar.setRequestFocusEnabled(false);
        botonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(392, 392, 392)
                        .addComponent(botonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 943, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(103, 103, 103)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67)
                .addComponent(botonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(133, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * El metodo obotonEliminarActionPerformed elimina algun elemento que el
     * usuario seleccione en la tabla de horario
     *
     * @param evt
     * @version 0.1
     */
    private void botonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarActionPerformed

        DefaultTableModel formatoHorario = (DefaultTableModel) tablaHorario.getModel();
        int fila = tablaHorario.getSelectedRow();
        int columna = tablaHorario.getSelectedColumn();

        if (fila >= 0 && columna >= 0) {
            String elementoSeleccionado = String.valueOf(formatoHorario.getValueAt(fila, columna));
            if (!(elementoSeleccionado.equals(" "))) {
                int confirmacion = JOptionPane.showConfirmDialog(this, "Esta seguro de eliminar la materia seleccionaada?", "Confirmacion", JOptionPane.YES_NO_OPTION);
                if (confirmacion == JOptionPane.YES_OPTION) {
                    setMateriaModificada(elementoSeleccionado);
                    Archivo archivo = new Archivo();
                    columna = 0;
                    String filaObtenida = archivo.buscarRegistro(String.valueOf(formatoHorario.getValueAt(fila, columna)), "Horario.txt", 1);

                    setHoraModificada(filaObtenida);
                    borrarDato();
                    actualizarTabla();
                }

            } else {
                JOptionPane.showMessageDialog(this, "Seleccione una casilla con inforrmacion a modificar", "Casilla vacia", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Error, seleccione una casilla", "Seleccion de casilla", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_botonEliminarActionPerformed

    /**
     * El metodo actualizarTabla permite actualizar automaticamente los
     * registros de la tabla horario despues de eliminar un registro.
     *
     * @version 0.1
     */
    private void actualizarTabla() {
        DefaultTableModel formatoHorario = (DefaultTableModel) tablaHorario.getModel();
        Archivo archivo = new Archivo();
        Horario horarioClases = new Horario();
        String horario[] = archivo.leerArchivo("Horario.txt");
        String experienciasEducativas[] = archivo.leerArchivo("ExperienciasEducativas.txt");
        formatoHorario.setRowCount(0);
        String horarioOrdenado[] = horarioClases.ordenarHorario(horario);
        for (int i = 0; i < horario.length; i++) {
            formatoHorario.addRow(horarioOrdenado[i].split("/"));
        }
    }

    /**
     * El metodo borrarDato permite borrar el registro seleccionado por el
     * usuario
     *
     * @version 0.1
     */
    private void borrarDato() {
        Archivo archivo = new Archivo();
        String[] arregloHora = getHoraModificada().split("/");
        String horaNueva = arregloHora[0] + "/";
        boolean vacio = true; //Variable para verificar si una hora esta vacia
        for (int i = 1; i < arregloHora.length; i++) {
            if (arregloHora[i].equalsIgnoreCase(getMateriaModificada())) {
                horaNueva += " /";
            } else {
                horaNueva += arregloHora[i] + "/";
            }
        }
        for (int i = 1; i < horaNueva.split("/").length; i++) {
            if (!horaNueva.split("/")[i].equals(" ")) {
                vacio = false;
            }
        }
        archivo.modificarRegistro("Horario.txt", horaNueva, vacio);
    }

    public void setHoraModificada(String horaModificada) {
        this.horaModificada = horaModificada;
    }

    public String getHoraModificada() {
        return this.horaModificada;
    }

    public void setMateriaModificada(String materiaModificada) {
        this.materiaModificada = materiaModificada;
    }

    public String getMateriaModificada() {
        return this.materiaModificada;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonEliminar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaHorario;
    // End of variables declaration//GEN-END:variables
}
