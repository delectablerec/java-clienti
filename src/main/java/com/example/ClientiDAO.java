import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClientiDAO {
    private Connection connect() {
        // Connessione al database SQLite
        String url = "jdbc:sqlite:clienti.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public List<Clienti> getAllClienti() {
        String sql = "SELECT id, nome, cognome, email, isAdmin FROM clienti";
        List<String> list = new ArrayList<String>();
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Clienti clienti = new Clienti();
                clienti.setId(rs.getInt("id"));
                clienti.setNome(rs.getString("nome"));
                clienti.setCognome(rs.getString("cognome"));
                clienti.setEmail(rs.getString("email"));
                clienti.setIsAdmin(rs.getBoolean("isAdmin"));
                clientiList.add(clienti);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return clientiList;
    }

    // Altri metodi per inserire, aggiornare e cancellare clienti
    // ...

    public Clienti getClienteById(int id) {
        String sql = "SELECT id, nome, cognome, email, isAdmin FROM clienti WHERE id = ?";
        Clienti cliente = null;
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                cliente = new Clienti();
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setCognome(rs.getString("cognome"));
                cliente.setEmail(rs.getString("email"));
                cliente.setIsAdmin(rs.getBoolean("isAdmin"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return cliente;
    }

    public void inserisciCliente(Clienti cliente) {
        String sql = "INSERT INTO clienti (nome, cognome, email, isAdmin) VALUES (?, ?, ?, ?)";
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cliente.getNome());
            pstmt.setString(2, cliente.getCognome());
            pstmt.setString(3, cliente.getEmail());
            pstmt.setBoolean(4, cliente.getIsAdmin());
            pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void modificaCliente(Clienti cliente) {
        String sql = "UPDATE clienti SET nome = ?, cognome = ?, email = ?, isAdmin = ? WHERE id = ?";
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cliente.getNome());
            pstmt.setString(2, cliente.getCognome());
            pstmt.setString(3, cliente.getEmail());
            pstmt.setBoolean(4, cliente.getIsAdmin());
            pstmt.setInt(5, cliente.getId());
            pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void eliminaCliente(int id) {
        String sql = "DELETE FROM clienti WHERE id = ?";
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}