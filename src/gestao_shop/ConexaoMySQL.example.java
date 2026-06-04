// ARQUIVO DE EXEMPLO — copie para ConexaoMySQL.java e ajuste suas credenciais
// Este arquivo é versionado. ConexaoMySQL.java está no .gitignore.

package gestao_shop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoMySQL {

    // Opção 1 — variáveis de ambiente (recomendado, sem expor senha no código):
    //   export DB_HOST=localhost
    //   export DB_PORT=3306
    //   export DB_NAME=gestao_shop
    //   export DB_USER=root
    //   export DB_PASSWORD=suasenha

    // Opção 2 — valores fixos (só para dev local, nunca commitar com senha real):
    private static final String HOST     = getEnv("DB_HOST",     "localhost");
    private static final String PORT     = getEnv("DB_PORT",     "3306");
    private static final String DATABASE = getEnv("DB_NAME",     "gestao_shop");
    private static final String USER     = getEnv("DB_USER",     "root");
    private static final String PASSWORD = getEnv("DB_PASSWORD", "");

    private static final String URL =
        "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE
        + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=America/Sao_Paulo";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    private static String getEnv(String key, String defaultValue) {
        String value = System.getenv(key);
        return (value != null && !value.isBlank()) ? value : defaultValue;
    }
}
