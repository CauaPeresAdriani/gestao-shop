/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestao_shop;

import java.time.LocalDate;

/**
 *
 * @author MASTER
 */
public class Venda {
    private int idProduto;
    private double quantidade;
    private double valorTotal;
    private LocalDate data;
    private int idUsuario;
    private int idVenda;

  
    public Venda(int idProduto , double quantidade, double valorTotal) {
        this.idProduto = idProduto;

        this.quantidade = quantidade;
        this.valorTotal = valorTotal;
        this.data = LocalDate.now(); 
    }

    public Venda(){}
    
    
    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }


    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }
    
    
}
