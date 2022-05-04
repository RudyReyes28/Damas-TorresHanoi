package com.ipc1.torres_hanoi;

import com.ipc1.archivos.GuardarPartida;
import com.ipc1.archivos.ReporteJuegos;
import com.ipc1.torres_hanoi.modo_rapido.MoverBloqueIndividual;
import com.ipc1.torres_hanoi.torres.DibujarTorres;
import com.ipc1.torres_hanoi.modo_rapido.MoverBloquesSolucion;
import com.ipc1.ventanas.VentanaInicio;
import com.ipc1.ventanas.VentanaReportesHanoi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaHanoi extends JFrame {

    private DibujarTorres torresHanoi;
    private int cantBloques;
    private String nombreJugador;
    private JLabel tiempo, etiquetaTruco;
    private JLabel cantMovimientos;
    private int contadorMov;
    private int modoJuego;
    private JButton resolverJuego, abandonar, resolverAvanzar, resolverRetroceder, truco;
    private JTextField casillaAR;
    private MoverBloqueIndividual bloqueIndividual;
    private JMenuItem guardarPartida, cargarPartida, regresarPrincipal;
    private GuardarPartida salvarPartida;
    private int minutos, segundos;


    public VentanaHanoi(String nombreJugador, int cantBloques, int modoJuego) {
        this.nombreJugador = nombreJugador;
        this.cantBloques = cantBloques;
        this.modoJuego = modoJuego;
        salvarPartida = new GuardarPartida();
        this.setSize(1100,700);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setTitle("TORRES DE HANOI");
        this.setLayout(null);
        tiempo = new JLabel();
        iniciar();
        aniadirTorres();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    public void iniciar(){
        torresHanoi = new DibujarTorres(this, cantBloques,modoJuego);

    }

    public void aniadirTorres(){
        //this.setLayout(new BorderLayout());
        this.add(torresHanoi);

        iniciarComponentes();
    }

    public void iniciarComponentes(){
        agregarEtiquetas();
        colocarMenu();
        agregarCajasTexto();
        agregarBoton();
        agregarEventos();
    }

    public void agregarEtiquetas(){
        JLabel tituloTiempo = new JLabel();
        tituloTiempo.setBounds(750, 10,70,30);
        tituloTiempo.setText("Tiempo:");
        tituloTiempo.setHorizontalAlignment(SwingConstants.CENTER);
        tituloTiempo.setForeground(Color.BLACK);
        tituloTiempo.setFont(new Font("Chilanka",Font.BOLD,15));
        torresHanoi.add(tituloTiempo);


        tiempo.setBounds(820, 10,80,30);
        tiempo.setOpaque(true);
        tiempo.setBackground(Color.white);
        tiempo.setText("0:0:0");
        tiempo.setHorizontalAlignment(SwingConstants.CENTER);
        tiempo.setForeground(Color.BLACK);
        tiempo.setFont(new Font("Chilanka",Font.BOLD,15));
        torresHanoi.add(tiempo);

        JLabel etiquetaMovimientos = new JLabel();
        etiquetaMovimientos.setBounds(910, 10,100,30);
        etiquetaMovimientos.setText("Movimientos:");
        etiquetaMovimientos.setHorizontalAlignment(SwingConstants.CENTER);
        etiquetaMovimientos.setForeground(Color.BLACK);
        etiquetaMovimientos.setFont(new Font("Chilanka",Font.BOLD,15));
        torresHanoi.add(etiquetaMovimientos);

        cantMovimientos = new JLabel();
        cantMovimientos.setBounds(1010,10,50,30);
        cantMovimientos.setOpaque(true);
        cantMovimientos.setBackground(Color.white);
        cantMovimientos.setText("0");
        cantMovimientos.setHorizontalAlignment(SwingConstants.CENTER);
        cantMovimientos.setForeground(Color.BLACK);
        cantMovimientos.setFont(new Font("Chilanka",Font.BOLD,15));
        torresHanoi.add(cantMovimientos);

        JLabel torre1 = new JLabel();
        torre1.setBounds(170,122,70,30);
        torre1.setText("Torre 1");
        torre1.setHorizontalAlignment(SwingConstants.CENTER);
        torre1.setForeground(Color.BLACK);
        torre1.setFont(new Font("Chilanka",Font.BOLD,17));
        torresHanoi.add(torre1, Integer.valueOf(1));

        JLabel torre2 = new JLabel();
        torre2.setBounds(516,122,70,30);
        torre2.setText("Torre 2");
        torre2.setHorizontalAlignment(SwingConstants.CENTER);
        torre2.setForeground(Color.BLACK);
        torre2.setFont(new Font("Chilanka",Font.BOLD,17));
        torresHanoi.add(torre2,Integer.valueOf(1));

        JLabel torre3 = new JLabel();
        torre3.setBounds(862,122,70,30);
        torre3.setText("Torre 3");
        torre3.setHorizontalAlignment(SwingConstants.CENTER);
        torre3.setForeground(Color.BLACK);
        torre3.setFont(new Font("Chilanka",Font.BOLD,17));
        torresHanoi.add(torre3,Integer.valueOf(1));

        etiquetaTruco = new JLabel();
        etiquetaTruco.setBounds(0,600,1100,40);
        etiquetaTruco.setHorizontalAlignment(SwingConstants.CENTER);
        etiquetaTruco.setForeground(Color.BLACK);
        etiquetaTruco.setFont(new Font("Chilanka",Font.BOLD,17));
        torresHanoi.add(etiquetaTruco,Integer.valueOf(1));

    }

    public void colocarMenu(){
        JMenuBar barraMenu = new JMenuBar();
        JMenu menu = new JMenu();
        guardarPartida = new JMenuItem("Guardar Partida");
        regresarPrincipal = new JMenuItem("Regresar al Menu");
        cargarPartida = new JMenuItem("Cargar Partida");
        menu.setText("Opciones");
        menu.setVisible(true);
        menu.add(guardarPartida);
        menu.add(cargarPartida);
        menu.add(regresarPrincipal);

        barraMenu.add(menu);
        barraMenu.setVisible(true);
        this.setJMenuBar(barraMenu);
        accionMenu();
    }

    public void accionMenu(){
        ActionListener accionGuardar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarPartidaHanoi();
            }
        };
        guardarPartida.addActionListener(accionGuardar);

        ActionListener accionRegresar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                torresHanoi.getHiloTiempo().interrupt();
                VentanaInicio regresarInicio = new VentanaInicio();
                dispose();
            }
        };

        regresarPrincipal.addActionListener(accionRegresar);

        ActionListener accionCargarPartida = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(modoJuego==1) {
                    cargarPartidaHanoi();
                }else{
                    JOptionPane.showMessageDialog(null,"No se puede cargar partida en este modo");
                }
            }
        };

        cargarPartida.addActionListener(accionCargarPartida);
    }

    public void guardarPartidaHanoi(){
        int [][] copiaTorres = new int[3][8];
        salvarPartida.crearArchivo("PartidaSalvadaHanoi.txt");
        torresHanoi.getVerBloques().guardarPartida(copiaTorres);

        for (int i = 0; i < copiaTorres.length; i++) {
            String datos = "";
            for (int j = 0; j < copiaTorres[0].length; j++) {
                datos += copiaTorres[i][j]+",";
            }

            datos = datos.substring(0,datos.length()-1);

            salvarPartida.guardarPartida(datos);
        }

        salvarPartida.guardarMovimientos(String.valueOf(contadorMov));
        salvarPartida.guardarTiempo(tiempo.getText());
    }

    public void cargarPartidaHanoi(){
        String [][] datos = new String[5][8];
        salvarPartida.cargarDatos(datos,"PartidaSalvadaHanoi.txt" );
        try {
            int[][] cargarTorres = new int[3][8];

            for (int i = 0; i < cargarTorres.length; i++) {
                for (int j = 0; j < cargarTorres[0].length; j++) {
                    cargarTorres[i][j] = Integer.parseInt(datos[i][j]);
                }
            }

            contadorMov = Integer.parseInt(datos[3][1]);
            cantMovimientos.setText(String.valueOf(contadorMov));

            calcularTiempo(datos[4][1]);

            torresHanoi.getVerBloques().cargarPartida(cargarTorres);

            torresHanoi.getCronometro().setSegundos(segundos);
            torresHanoi.getCronometro().setMinutos(minutos);
        }catch (NumberFormatException ignore){

        }
    }

    public void calcularTiempo(String tiempo){
        int cant = tiempo.length();

        if(cant==5){
            segundos = Integer.parseInt(tiempo.substring(4,5));

        }else if(cant == 6){
            String digito1 = tiempo.substring(4,5);

            if(digito1.equals(":")){
                minutos = Integer.parseInt(tiempo.substring(2,4));
                segundos = Integer.parseInt(tiempo.substring(5,6));
            }else{
                minutos = Integer.parseInt(tiempo.substring(2,3));
                segundos = Integer.parseInt(tiempo.substring(4,6));

            }
        }else if(cant==7){
            minutos = Integer.parseInt(tiempo.substring(2,3));
            segundos = Integer.parseInt(tiempo.substring(5,7));
        }
    }

    public void agregarCajasTexto(){
        casillaAR = new JTextField();
        casillaAR.setBounds(160,50,30,30);
        casillaAR.setHorizontalAlignment(SwingConstants.CENTER);
        casillaAR.setText("1");
        torresHanoi.add(casillaAR,Integer.valueOf(1));

        if(modoJuego == 1){
            casillaAR.setVisible(false);
        }
    }

    public void agregarBoton(){
        resolverJuego = new JButton();
        resolverJuego.setBounds(105,15,150,30);
        resolverJuego.setText("Solucion rapida");
        resolverJuego.setHorizontalAlignment(SwingConstants.CENTER);
        resolverJuego.setFont(new Font("Arial",Font.BOLD,15));

        torresHanoi.add(resolverJuego,Integer.valueOf(1));

        resolverAvanzar = new JButton();
        resolverAvanzar.setBounds(200,50,80,30);
        resolverAvanzar.setText("Avanzar");
        resolverAvanzar.setHorizontalAlignment(SwingConstants.CENTER);
        resolverAvanzar.setFont(new Font("Arial",Font.BOLD,12));
        torresHanoi.add(resolverAvanzar,Integer.valueOf(1));

        resolverRetroceder = new JButton();
        resolverRetroceder.setBounds(50,50,100,30);
        resolverRetroceder.setText("Retroceder");
        resolverRetroceder.setHorizontalAlignment(SwingConstants.CENTER);
        resolverRetroceder.setFont(new Font("Arial",Font.BOLD,12));
        torresHanoi.add(resolverRetroceder,Integer.valueOf(1));

        truco = new JButton();
        truco.setBounds(950,85,70,30);
        truco.setText("Truco");
        truco.setHorizontalAlignment(SwingConstants.CENTER);
        truco.setFont(new Font("Arial",Font.BOLD,12));
        torresHanoi.add(truco,Integer.valueOf(1));

        if(modoJuego!=2){
            resolverJuego.setVisible(false);
            resolverAvanzar.setVisible(false);
            resolverRetroceder.setVisible(false);
        }else{
            truco.setVisible(false);
        }

        abandonar = new JButton();
        abandonar.setBounds(900,45,140,30);
        abandonar.setText("Abandonar");
        abandonar.setHorizontalAlignment(SwingConstants.CENTER);
        abandonar.setFont(new Font("Arial",Font.BOLD,15));
        torresHanoi.add(abandonar,Integer.valueOf(1));
    }

    public void agregarEventos(){
        bloqueIndividual = new MoverBloqueIndividual(VentanaHanoi.this);

        ActionListener eventoResolverJuego = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                MoverBloquesSolucion bloquesSolucion = new MoverBloquesSolucion(VentanaHanoi.this);
                Thread moverSolucion = new Thread(bloquesSolucion);
                moverSolucion.start();
            }
        };

        ActionListener eventoAbandonar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                torresHanoi.getHiloTiempo().interrupt();
                if(modoJuego==1) {

                    String reporte = nombreJugador + ",0,1,1," + contadorMov + "," + tiempo.getText();
                    ReporteJuegos reportesHanoi = new ReporteJuegos("ReporteHanoi.txt");
                    reportesHanoi.escribirReporte(reporte);
                }
                VentanaReportesHanoi ventanaReportesHanoi = new VentanaReportesHanoi(VentanaHanoi.this);
                ventanaReportesHanoi.setVisible(true);

            }
        };

        ActionListener eventoAvanzar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(casillaAR.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"Ingrese cant. Movimientos");
                }else {
                    int avanzarM = Integer.parseInt(casillaAR.getText());
                    bloqueIndividual.setRetrocederAvanzar(avanzarM);
                    Thread moverIndividual = new Thread(bloqueIndividual);
                    moverIndividual.start();
                }
            }
        };

        ActionListener eventoRetroceder = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(casillaAR.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"Ingrese cant. Movimientos");
                }else {
                    int avanzarM = Integer.parseInt(casillaAR.getText());
                    bloqueIndividual.setRetrocederAvanzar(-avanzarM);
                    Thread moverIndividual = new Thread(bloqueIndividual);
                    moverIndividual.start();
                }
            }
        };

        ActionListener eventoTruco = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String movTruco = torresHanoi.getVerBloques().mostrarMovimientoTruco();
                etiquetaTruco.setText(movTruco);
            }
        };

        resolverJuego.addActionListener(eventoResolverJuego);
        abandonar.addActionListener(eventoAbandonar);
        resolverAvanzar.addActionListener(eventoAvanzar);
        resolverRetroceder.addActionListener(eventoRetroceder);
        truco.addActionListener(eventoTruco);
    }

    public void setContadorMov() {
        this.contadorMov ++;
        cantMovimientos.setText(String.valueOf(contadorMov));
    }

    public void retrocederContador(){
        this.contadorMov --;
        cantMovimientos.setText(String.valueOf(contadorMov));
    }

    public void reiniciarContador(){
        this.contadorMov = 0;
        cantMovimientos.setText(String.valueOf(contadorMov));
    }

    public JLabel getTiempo() {
        return tiempo;
    }

    public DibujarTorres getTorresHanoi() {
        return torresHanoi;
    }

    public String getNombreJugador() {
        return nombreJugador;
    }

    public int getContadorMov() {
        return contadorMov;
    }

    public MoverBloqueIndividual getBloqueIndividual() {
        return bloqueIndividual;
    }
}
