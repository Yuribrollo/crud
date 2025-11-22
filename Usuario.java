/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.bean;

/**
 *
 * @author gregory
 */
public class Usuario {
    private int id;
    private String nomeUsu;
    
    // Métodos de encapsulamento
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeUsu() {
        return nomeUsu;
    }

    public void setNomeUsu(String nome) {
        this.nomeUsu = nome;
    }

    @Override
    public String toString() {
        return nomeUsu;
    }
}
