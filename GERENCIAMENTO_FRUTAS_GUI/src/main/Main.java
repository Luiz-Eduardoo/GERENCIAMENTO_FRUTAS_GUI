package main;

import javax.swing.*; // import para criar a interface
import java.awt.*; // import para controlar o visual da interface
import java.awt.event.ActionEvent; // import para controlar as ações
import java.awt.event.ActionListener; // Import para lidar com as ações
import java.util.ArrayList; // Import para fazer a lista


// classe FrutaManagerGUI implementa a interface FrutaManager para gerenciar as frutas
class FrutaManagerGUI implements FrutaManager {
    private ArrayList<String> frutas; // Lista para guardar as frutas
    private DefaultListModel<String> listModel; // Modelo de lista para a interface
    private JList<String> list; // mostra as frutas na interface

    // metodo construtor para inciar a interface
    public FrutaManagerGUI() {
        frutas = new ArrayList<>(); // Inicializa a lista de frutas
        listModel = new DefaultListModel<>(); // Inicializa o modelo da lista

        // Cria a Janela
        JFrame frame = new JFrame("Gerenciador de Frutas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fecha o programa ao fechar a janela
        frame.setSize(600, 300); // tamanho da janela
        frame.setLayout(new BorderLayout()); // layout da janela

        // painel superior com os botões e o campo de texto
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout()); // layout em linha

        JTextField frutaField = new JTextField(15); // Campo de texto para colocar o nome da fruta
        panel.add(frutaField); // Adiciona o campo de texto ao painel

        // Botão de adicionar
        JButton addButton = new JButton("Adicionar");
        panel.add(addButton);
        // modificar
        JButton modifyButton = new JButton("Modificar");
        panel.add(modifyButton);
        // remover
        JButton removeButton = new JButton("Remover");
        panel.add(removeButton);

        // Adiciona o painel ao topo da janela
        frame.add(panel, BorderLayout.NORTH);

        // cria a lista para mostrar as frutas
        list = new JList<>(listModel); // Lista que usa o modelo de lista para mostrar as frutas
        JScrollPane scrollPane = new JScrollPane(list); // adiciona a possibilidade de rolar com o scroll na lista
        frame.add(scrollPane, BorderLayout.CENTER); // Adiciona a lista ao centro da janela

        // Botão para listar as frutas
        JButton listButton = new JButton("Listar Frutas");
        frame.add(listButton, BorderLayout.SOUTH); // Adiciona o botão embaixo no painel

        // Ação para adicionar uma nova fruta na lista
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String novaFruta = frutaField.getText(); // Lê o texto inserido pelo usuário
                if (!novaFruta.isEmpty()) { // Verifica se não esta vazio
                    adicionarFruta(novaFruta); // Adiciona a fruta na lista
                    frutaField.setText(""); // Limpa o campo de texto depois de adicionar
                    JOptionPane.showMessageDialog(frame, novaFruta + " foi adicionada."); // confirma a ação
                }
            }
        });

        // Ação para modificar uma fruta da lista
        modifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = list.getSelectedIndex(); // Ve o indice da fruta selecionada
                if (selectedIndex != -1) { // Verifica se tem uma fruta selecionada
                    String frutaSelecionada = listModel.getElementAt(selectedIndex); // Ve o nome da fruta selecionada
                    String novaFruta = JOptionPane.showInputDialog(frame, "Modificar " + frutaSelecionada + " para:", frutaSelecionada);
                    if (novaFruta != null && !novaFruta.isEmpty()) { // Verifica se o nome da fruta não esta nulo ou vazio
                        modificarFruta(selectedIndex, novaFruta); // Modifica a fruta na lista
                        JOptionPane.showMessageDialog(frame, "Fruta: " + frutaSelecionada + " foi modificada para " + novaFruta); // confirma a ação
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Selecione uma fruta para modificar."); //  caso nenhuma fruta tenha sido selecionada, mostra o aviso
                }
            }
        });

        // Ação para remover uma fruta da lista
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = list.getSelectedIndex(); // Ve o índice da fruta selecionada
                if (selectedIndex != -1) { // Verifica se tem uma fruta selecionada
                    removerFruta(selectedIndex); // Remove a fruta da lista
                    JOptionPane.showMessageDialog(frame, "Fruta foi removida."); // confirma a ação
                } else {
                    JOptionPane.showMessageDialog(frame, "Selecione uma fruta para remover."); // caso nenhuma fruta tenha sido selecionada, mostra o aviso
                }
            }
        });

        // Ação para listar todas as frutas
        listButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (frutas.isEmpty()) { // Verifica se a lista de frutas está vazia
                    JOptionPane.showMessageDialog(frame, "Nenhuma fruta na lista."); // caso esteja vazia, mostra o aviso
                } else {
                    ArrayList<String> listaDeFrutas = listarFrutas(); // pega a lista de frutas para mostrar
                    JOptionPane.showMessageDialog(frame, "Frutas: " + listaDeFrutas); // mostra a lista de frutas
                }
            }
        });

        // verifica se pode remover ou modificar
        list.addListSelectionListener(e -> {
            boolean selectionExists = !list.isSelectionEmpty(); // Verifica se tem uma seleção na lista
            removeButton.setEnabled(selectionExists); // Habilita ou desabilita o botão de remover
            modifyButton.setEnabled(selectionExists); // Habilita ou desabilita o botão de modificar
        });

        frame.setVisible(true); // Torna a janela visível
    }

    @Override
    public void adicionarFruta(String fruta) {
        frutas.add(fruta); // Adiciona a fruta na lista
        listModel.addElement(fruta); // Adiciona a fruta ao modelo da lista
    }

    @Override
    public void modificarFruta(int index, String novaFruta) {
        frutas.set(index, novaFruta); // Modifica a fruta na lista
        listModel.set(index, novaFruta); // Modifica a fruta no modelo da lista
    }

    @Override
    public void removerFruta(int index) {
    	frutas.remove(index); // remove a fruta na lista
        listModel.remove(index); // Remove a fruta do modelo da lista
    }

    @Override
    public ArrayList<String> listarFrutas() {
        return new ArrayList<>(frutas); // Retorna uma nova lista contendo todas as frutas
    }
}


public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() { // Executa o código da interface
            @Override
            public void run() {
                new FrutaManagerGUI(); // Cria uma nova instância da classe FrutaManagerGUI
            }
        });
    }
}
