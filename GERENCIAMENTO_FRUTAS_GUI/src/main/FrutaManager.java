package main;

import java.util.ArrayList;

// interface com os metodos da lista
public interface FrutaManager {
	
	// Método para adicionar uma nova fruta à lista
    void adicionarFruta(String fruta);
    
    // Método para modificar o nome de uma fruta
    void modificarFruta(int index, String novaFruta);
    
    // Método para remover uma fruta
    void removerFruta(int index);
    
    // Método para listar todas as frutas inseridas
    ArrayList<String> listarFrutas(); 
}