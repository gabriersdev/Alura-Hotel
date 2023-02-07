package br.com.hotelAlura.views;

import br.com.hotelAlura.controller.HospedeController;
import br.com.hotelAlura.controller.ReservaController;
import br.com.hotelAlura.model.Hospede;
import br.com.hotelAlura.model.Reserva;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Date;
import java.time.format.DateTimeFormatter;

@SuppressWarnings("serial")
public class Buscar extends JFrame {

    private JPanel contentPane;
    private JTextField txtBuscar;
    private JTable tbHospedes;
    private JTable tbReservas;
    private DefaultTableModel modelo;
    private DefaultTableModel modeloHospedes;
    private JLabel labelAtras;
    private JLabel labelExit;
    private HospedeController hospedeController;
    private ReservaController reservaController;
    int xMouse, yMouse;
    DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Buscar frame = new Buscar();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Buscar() {

        this.hospedeController = new HospedeController();
        this.reservaController = new ReservaController();

        setIconImage(Toolkit.getDefaultToolkit().getImage(Buscar.class.getResource("/images/lOGO-50PX.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 910, 571);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);
        setUndecorated(true);

        txtBuscar = new JTextField();
        txtBuscar.setBounds(536, 127, 193, 31);
        txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        contentPane.add(txtBuscar);
        txtBuscar.setColumns(10);


        JLabel lblTitulo = new JLabel("SISTEMA DE BUSCA");
        lblTitulo.setForeground(new Color(12, 138, 199));
        lblTitulo.setFont(new Font("Roboto Black", Font.BOLD, 24));
        lblTitulo.setBounds(331, 62, 280, 42);
        contentPane.add(lblTitulo);

        JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
        panel.setBackground(new Color(12, 138, 199));
        panel.setFont(new Font("Roboto", Font.PLAIN, 16));
        panel.setBounds(20, 169, 865, 328);
        contentPane.add(panel);

        tbReservas = new JTable();
        tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
        panel.addTab("Reservas", new ImageIcon(Buscar.class.getResource("/images/reservado.png")), tbReservas, null);
        modelo = (DefaultTableModel) tbReservas.getModel();
        modelo.addColumn("Numero de Reserva");
        modelo.addColumn("Data Check In");
        modelo.addColumn("Data Check Out");
        modelo.addColumn("Valor");
        modelo.addColumn("Forma de PGTO.");
        //Label da tabela
        JScrollPane scrool1 = new JScrollPane(tbReservas);
        panel.add("Reservas", scrool1);

        tbHospedes = new JTable();
        tbHospedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbHospedes.setFont(new Font("Roboto", Font.PLAIN, 16));
        panel.addTab("Hóspedes", new ImageIcon(Buscar.class.getResource("/images/pessoas.png")), tbHospedes, null);
        modeloHospedes = (DefaultTableModel) tbHospedes.getModel();
        modeloHospedes.addColumn("Numero de Hóspede");
        modeloHospedes.addColumn("Nome");
        modeloHospedes.addColumn("Sobrenome");
        modeloHospedes.addColumn("Data de Nascimento");
        modeloHospedes.addColumn("Nacionalidade");
        modeloHospedes.addColumn("Telefone");
        modeloHospedes.addColumn("Numero de Reserva");
        //Label da tabela
        JScrollPane scroll2 = new JScrollPane(tbHospedes);
        panel.add("Hóspedes", scroll2);

        JLabel lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setIcon(new ImageIcon(Buscar.class.getResource("/images/Ha-100px.png")));
        lblNewLabel_2.setBounds(56, 51, 104, 107);
        contentPane.add(lblNewLabel_2);

        JPanel header = new JPanel();
        header.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                headerMouseDragged(e);
            }
        });
        header.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                headerMousePressed(e);
            }
        });
        header.setLayout(null);
        header.setBackground(Color.WHITE);
        header.setBounds(0, 0, 910, 36);
        contentPane.add(header);

        JPanel btnAtras = new JPanel();
        btnAtras.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MenuUsuario usuario = new MenuUsuario();
                usuario.setVisible(true);
                dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnAtras.setBackground(new Color(12, 138, 199));
                labelAtras.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnAtras.setBackground(Color.white);
                labelAtras.setForeground(Color.black);
            }
        });
        btnAtras.setLayout(null);
        btnAtras.setBackground(Color.WHITE);
        btnAtras.setBounds(0, 0, 53, 36);
        header.add(btnAtras);

        labelAtras = new JLabel("<");
        labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
        labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
        labelAtras.setBounds(0, 0, 53, 36);
        btnAtras.add(labelAtras);

        JPanel btnexit = new JPanel();
        btnexit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MenuUsuario usuario = new MenuUsuario();
                usuario.setVisible(true);
                dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) { // Quando o usuário passa o mouse sobre o botão, ele muda de cor
                btnexit.setBackground(Color.red);
                labelExit.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) { //Quando o usuário remove o mouse do botão, ele retornará ao estado original
                btnexit.setBackground(Color.white);
                labelExit.setForeground(Color.black);
            }
        });

        btnexit.setLayout(null);
        btnexit.setBackground(Color.WHITE);
        btnexit.setBounds(857, 0, 53, 36);
        header.add(btnexit);

        labelExit = new JLabel("X");
        labelExit.setHorizontalAlignment(SwingConstants.CENTER);
        labelExit.setForeground(Color.BLACK);
        labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
        labelExit.setBounds(0, 0, 53, 36);
        btnexit.add(labelExit);

        JSeparator separator_1_2 = new JSeparator();
        separator_1_2.setForeground(new Color(12, 138, 199));
        separator_1_2.setBackground(new Color(12, 138, 199));
        separator_1_2.setBounds(539, 159, 193, 2);
        contentPane.add(separator_1_2);

        JPanel btnbuscar = new JPanel();
        btnbuscar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //Busca
            }
        });

        btnbuscar.setLayout(null);
        btnbuscar.setBackground(new Color(12, 138, 199));
        btnbuscar.setBounds(748, 125, 122, 35);
        btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        contentPane.add(btnbuscar);

        JLabel lblBuscar = new JLabel("BUSCAR");
        lblBuscar.setBounds(0, 0, 122, 35);
        btnbuscar.add(lblBuscar);
        lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
        lblBuscar.setForeground(Color.WHITE);
        lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));

        JPanel btnEditar = new JPanel();
        btnEditar.setLayout(null);
        btnEditar.setBackground(new Color(12, 138, 199));
        btnEditar.setBounds(635, 508, 122, 35);
        btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        contentPane.add(btnEditar);

        JLabel lblEditar = new JLabel("EDITAR");
        lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
        lblEditar.setForeground(Color.WHITE);
        lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
        lblEditar.setBounds(0, 0, 122, 35);
        btnEditar.add(lblEditar);

        JPanel btnDeletar = new JPanel();
        btnDeletar.setLayout(null);
        btnDeletar.setBackground(new Color(12, 138, 199));
        btnDeletar.setBounds(767, 508, 122, 35);
        btnDeletar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        contentPane.add(btnDeletar);

        JLabel lblExcluir = new JLabel("DELETAR");
        lblExcluir.setHorizontalAlignment(SwingConstants.CENTER);
        lblExcluir.setForeground(Color.WHITE);
        lblExcluir.setFont(new Font("Roboto", Font.PLAIN, 18));
        lblExcluir.setBounds(0, 0, 122, 35);
        btnDeletar.add(lblExcluir);
        setResizable(false);

        //Ação ao clicar em Editar
        btnEditar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                Integer reservaSelec = tbReservas.getSelectedRow();
                Integer hospedeSelec = tbHospedes.getSelectedRow();

                if (reservaSelec >= 0) {
                    try {
                        alterarReserva();
                    } catch (Exception exception) {
                        JOptionPane.showMessageDialog(null, "Ocorreu um erro! Tente novamente mais tarde", "Erro", JOptionPane.ERROR_MESSAGE);
                    } finally {
                        limparReservas();
                        listarReservas();
                    }
                } else if (hospedeSelec >= 0) {
                    try {
                        alterarHospede();
                    } catch (Exception exception) {
                        JOptionPane.showMessageDialog(null, "Ocorreu um erro! Tente novamente mais tarde" , "Erro", JOptionPane.ERROR_MESSAGE);
                    } finally {
                        limparHospede();
                        listarHospede();
                    }
                }

            }
        });

        //Ação ao clicar em Deletar
        btnDeletar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                Integer reservaSelec = tbReservas.getSelectedRow();
                Integer hospedeSelec = tbHospedes.getSelectedRow();

                if (reservaSelec >= 0) {
                    System.out.println("Clicou em DELETAR. Tabela RESERVA");
                    try {
                        deletarReserva();
                    } catch (Exception exception) {
                        JOptionPane.showMessageDialog(null, "Ocorreu um erro!. Mensagem: " + exception.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    } finally {
                        limparReservas();
                        listarReservas();
                    }
                } else if (hospedeSelec >= 0) {
                    System.out.println("Clicou em DELETAR. Tabela HOSPEDE");
                    try {
                        deletarHospede();
                    } catch (Exception exception) {
                        JOptionPane.showMessageDialog(null, "Ocorreu um erro!. Mensagem: " + exception.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    } finally {
                        limparHospede();
                        listarHospede();
                    }
                }

            }
        });

        //Adicionando registros as tabelas de Reservas e de Hóspedes
        preencherTabelas();
    }

    private void preencherTabelas() {
        listarReservas();
        listarHospede();
    }

    public void limparReservas() {
        modelo.getDataVector().clear();
        tbReservas.updateUI();
    }

    public void limparHospede() {
        modeloHospedes.getDataVector().clear();
        tbHospedes.updateUI();
    }

    private void alterarReserva() {
        Object objetoDaLinha = (Object) modelo.getValueAt(tbReservas.getSelectedRow(), 0);
        if (objetoDaLinha instanceof Integer) {

            Integer id = (Integer) objetoDaLinha;
            String dataEntrada = String.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 1));
            String dataSaida = String.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 2));
            String valor = (String) modelo.getValueAt(tbReservas.getSelectedRow(), 3);
            String formaPagamento = (String) modelo.getValueAt(tbReservas.getSelectedRow(), 4);

            this.reservaController.alterar(new Reserva(id, Date.valueOf(dataEntrada), Date.valueOf(dataSaida), valor, formaPagamento));
            JOptionPane.showMessageDialog(null, "Reserva alterada com sucesso", "Hotel Alura", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecione o campo ID");
        }
    }

    private void alterarHospede() {
        Object objetoDaLinha = (Object) modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 0);
        if (objetoDaLinha instanceof Integer) {

            Integer id = (Integer) objetoDaLinha;
            String nome = (String) (modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 1));
            String sobrenome = (String) (modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 2));
            String dataNascimento = (String.valueOf(modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 3)));
            String nacionalide = (String) modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 4);
            String telefone = (String) modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 5);
            Integer codReserva = (Integer) modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 6);

            this.hospedeController.alterar(new Hospede(id, nome, sobrenome, Date.valueOf(dataNascimento), nacionalide, telefone, codReserva));
            JOptionPane.showMessageDialog(null, "Registro do hóspede alterado com sucesso", "Hotel Alura", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecione o campo ID");
        }
    }

    private void deletarReserva() {
        //
    }

    private void deletarHospede() {
        //
    }

    private void listarReservas() {
        //modelo.addRow(new Object[]{"N.º de Reserva", "Data Check In", "Data Check Out", "Valor", "Forma de PGTO."});

        //Listando as reservas
        try {
            this.reservaController.listar().stream().forEach(reserva -> {
                this.modelo.addRow(new Object[]{reserva.getId(), reserva.getDataEntrada(), reserva.getDataSaida(), reserva.getValor(), reserva.getFormaPagamento()});
            });
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void listarHospede() {
        //modeloHospedes.addRow(new Object[]{"N.º de Hóspede", "Nome", "Sobrenome", "Data de Nascimento", "Nacionalidade", "Telefone", "N.º de Reserva"});

        //Listando os hóspedes
        try {
            this.hospedeController.listar().stream().forEach(hospede -> {
                this.modeloHospedes.addRow(new Object[]{hospede.getId(), hospede.getNome(), hospede.getSobrenome(), hospede.getData_nascimento(), hospede.getNacionalidade(), hospede.getTelefone(), hospede.getCod_reserva()});
            });
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //Código que permite movimentar a janela pela tela seguindo a posição de "x" e "y"
    private void headerMousePressed(java.awt.event.MouseEvent evt) {
        xMouse = evt.getX();
        yMouse = evt.getY();
    }

    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }
}
