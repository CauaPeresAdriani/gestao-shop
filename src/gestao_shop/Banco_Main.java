/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestao_shop;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cauap
 */
public class Banco_Main {
    public void cadastrar_Produto(Produto produto) throws ClassNotFoundException, SQLException {
        String sql = "INSERT INTO produto(nome, preco, quantidade) VALUES(?, ?, ?)";
        Connection conexao = ConexaoMySQL.getConnection();
        PreparedStatement ps = conexao.prepareStatement(sql);
        ps.setString(1, produto.getNome());
        ps.setDouble(2, produto.getPreco());
        ps.setInt(3, produto.getQuantidade());
        ps.execute();
    }
    
    public Produto consultar_Produto(int id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM produto WHERE id = ?";
    
        try (Connection conexao = ConexaoMySQL.getConnection();
             PreparedStatement st = conexao.prepareStatement(sql)) {

            st.setInt(1, id);

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    Produto produto = new Produto();
                    produto.setId(id);
                    produto.setNome(rs.getString("nome"));
                    produto.setPreco(rs.getDouble("preco"));
                    produto.setQuantidade(rs.getInt("quantidade"));
                    return produto;
                } else {
                    throw new SQLException("Produto com ID " + id + " não encontrado.");
                }
            }
        }
    }
    
    public void atualizar_Produto(Produto produto) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE produto SET nome = ?, preco = ?, quantidade = ? WHERE id = ?";

        try (Connection conexao = ConexaoMySQL.getConnection();
             PreparedStatement ps = conexao.prepareStatement(sql)) {

            ps.setString(1, produto.getNome());
            ps.setDouble(2, produto.getPreco());
            ps.setInt(3, produto.getQuantidade());
            ps.setInt(4, produto.getId());

            int linhasAfetadas = ps.executeUpdate();
            if (linhasAfetadas == 0) {
                throw new SQLException("Nenhum produto foi atualizado. Verifique o ID.");
            }
        }
    }
    
    public void excluir_Produto(int id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM produto WHERE id = ?";
        Connection conexao = ConexaoMySQL.getConnection();
        PreparedStatement st = conexao.prepareStatement(sql);
        st.setInt(1, id);  
        st.execute();
        st.close();
        conexao.close();
    }
    
    /// BANCO_USUARIO:
    
    public void cadastrar_Usuario(Usuario usuarioLogado, Usuario novoUsuario) throws SQLException, ClassNotFoundException {
        if (usuarioLogado.getTipo() != TipoUsuario.GERENTE) {
            throw new SecurityException("Apenas gerentes podem cadastrar usuários.");
        }

        String sql = "INSERT INTO usuario(nome, email, senha, tipo) VALUES (?, ?, ?, ?)";
        try (Connection conexao = ConexaoMySQL.getConnection();
             PreparedStatement ps = conexao.prepareStatement(sql)) {

            ps.setString(1, novoUsuario.getNome());
            ps.setString(2, novoUsuario.getEmail());
            ps.setString(3, novoUsuario.getSenha());
            ps.setString(4, novoUsuario.getTipo().name());
            ps.execute();
        }
    }

    public Usuario consultar_Usuario(int id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM usuario WHERE id_usuario = ?";
        try (Connection conexao = ConexaoMySQL.getConnection();
             PreparedStatement st = conexao.prepareStatement(sql)) {

            st.setInt(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setId(id);
                    usuario.setNome(rs.getString("nome"));
                    usuario.setEmail(rs.getString("email"));
                    usuario.setSenha(rs.getString("senha"));
                    usuario.setTipo(TipoUsuario.valueOf(rs.getString("tipo").toUpperCase()));
                    return usuario;
                } else {
                    throw new SQLException("Usuário com ID " + id + " não encontrado.");
                }
            }
        }
    }

    public void atualizar_Usuario(Usuario usuarioLogado, Usuario usuario) throws SQLException, ClassNotFoundException {
        if (usuarioLogado.getTipo() != TipoUsuario.GERENTE) {
            throw new SecurityException("Apenas gerentes podem atualizar usuários.");
        }

        String sql = "UPDATE usuario SET nome = ?, email = ?, senha = ?, tipo = ? WHERE id_usuario = ?";
        try (Connection conexao = ConexaoMySQL.getConnection();
             PreparedStatement ps = conexao.prepareStatement(sql)) {

            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getSenha());
            ps.setString(4, usuario.getTipo().name());
            ps.setInt(5, usuario.getId());
            ps.executeUpdate();
        }
    }

    public void excluir_Usuario(Usuario usuarioLogado, int id) throws SQLException, ClassNotFoundException {
        if (usuarioLogado.getTipo() != TipoUsuario.GERENTE) {
            throw new SecurityException("Apenas gerentes podem excluir usuários.");
        }

        String sql = "DELETE FROM usuario WHERE id_usuario = ?";
        try (Connection conexao = ConexaoMySQL.getConnection();
             PreparedStatement st = conexao.prepareStatement(sql)) {

            st.setInt(1, id);
            st.execute();
        }
    }

    public Usuario login(String email, String senha) throws SQLException, ClassNotFoundException {
    String sql = "SELECT * FROM usuario WHERE email = ? AND senha = ?";
    try (Connection conexao = ConexaoMySQL.getConnection();
         PreparedStatement ps = conexao.prepareStatement(sql)) {

        ps.setString(1, email);
        ps.setString(2, senha);

        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id_usuario"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email")); // ✅ Pega do banco
                usuario.setSenha(rs.getString("senha")); // ✅ Pega do banco
                usuario.setTipo(TipoUsuario.valueOf(rs.getString("tipo"))); // ✅ Já está em maiúsculo

                return usuario;
            } else {
                throw new SQLException("Email ou senha incorretos.");
            }
        }
    }
}


    /// BANCO_VENDAS:
    
    public Venda consultar_Venda(int id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM venda WHERE id_venda = ?";
        Venda venda = null;

        try (Connection conexao = ConexaoMySQL.getConnection();
             PreparedStatement st = conexao.prepareStatement(sql)) {

            st.setInt(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    venda = new Venda();
                    venda.setIdVenda(rs.getInt("id_venda"));
                    
                    venda.setData(rs.getDate("data").toLocalDate());
                    venda.setValorTotal(rs.getDouble("valor_total"));
                } else {
                    throw new SQLException("Venda com ID " + id + " não encontrada.");
                }
            }
        }

        return venda;
    }

    public void atualizar_Venda(Venda venda) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE venda SET  data = ?, valor_total = ? WHERE id_venda = ?";

        try (Connection conexao = ConexaoMySQL.getConnection();
             PreparedStatement ps = conexao.prepareStatement(sql)) {

            
            ps.setDate(1, Date.valueOf(venda.getData()));
            ps.setDouble(2, venda.getValorTotal());
            ps.setInt(3, venda.getIdVenda());

            int linhasAfetadas = ps.executeUpdate();
            if (linhasAfetadas == 0) {
                throw new SQLException("Nenhuma venda foi atualizada. Verifique o ID.");
            }
        }
    }

    public void excluir_Venda(int id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM venda WHERE id_venda = ?";

        try (Connection conexao = ConexaoMySQL.getConnection();
             PreparedStatement st = conexao.prepareStatement(sql)) {

            st.setInt(1, id);
            st.execute();
        }
    }

    public double buscarPrecoPorId(int idProduto) throws SQLException, ClassNotFoundException {
        String sql = "SELECT preco FROM produto WHERE id = ?";
        try (Connection conexao = ConexaoMySQL.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idProduto);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("preco");
                } else {
                    throw new SQLException("Produto não encontrado.");
                }
            }
        }
    }
    
    public void registrarVenda(Venda venda) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO venda( id_usuario, id_produto, quantidade, valor_total, data) VALUES  ( ?, ?, ?, ?, ?)";

        try (Connection conexao = ConexaoMySQL.getConnection();
             PreparedStatement st = conexao.prepareStatement(sql)) {


            int idUsuario = venda.getIdUsuario();
            int idProduto = venda.getIdProduto();
            double quantidade = venda.getQuantidade();
            double valorTotal = venda.getValorTotal();
            LocalDate data = venda.getData();

           
            st.setInt(1, idUsuario);
            st.setInt(2, idProduto);
            st.setDouble(3, quantidade);
            st.setDouble(4, valorTotal);
            st.setDate(5, Date.valueOf(data));

            st.executeUpdate();
            System.out.println("Venda inserida com sucesso!");
        }
    }

    public String buscarNomeProdutoPorId(int idProduto) throws SQLException, ClassNotFoundException {
        String sql = "SELECT nome FROM produto WHERE id = ?";
        try (Connection conexao = ConexaoMySQL.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idProduto);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("nome");
                } else {
                    throw new SQLException("Produto não encontrado.");
                }
            }
        }
    }
    
    
    
    

}
    

