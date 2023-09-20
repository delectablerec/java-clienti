import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ClientiServlet extends HttpServlet {
    private ClientiDAO clientiDAO = new ClientiDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String azione = request.getParameter("azione");

        if ("esporta_csv".equals(azione)) {
            esportaClientiInCSV(response);
            return;
        }

        String id = request.getParameter("id");

        if ("modifica".equals(azione) && id != null) {
            Clienti cliente = clientiDAO.getClienteById(Integer.parseInt(id));
            request.setAttribute("cliente", cliente);
        } else if ("elimina".equals(azione) && id != null) {
            clientiDAO.eliminaCliente(Integer.parseInt(id));
        }

        List<Clienti> clientiList = clientiDAO.getAllClienti();
        request.setAttribute("clientiList", clientiList);
        request.setAttribute("pageContent", "content.jsp");
        request.getRequestDispatcher("layout.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String email = request.getParameter("email");
        String isAdminParam = request.getParameter("isAdmin");

        Clienti cliente = new Clienti();
        cliente.setNome(nome);
        cliente.setCognome(cognome);
        cliente.setEmail(email);
        cliente.setIsAdmin(isAdminParam != null && isAdminParam.equals("true"));

        if (id == null || id.isEmpty()) {
            clientiDAO.inserisciCliente(cliente);
        } else {
            cliente.setId(Integer.parseInt(id));
            clientiDAO.modificaCliente(cliente);
        }

        response.sendRedirect("clienti");
    }

    private void esportaClientiInCSV(HttpServletResponse response) {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment;filename=clienti.csv");

        try (PrintWriter writer = response.getWriter()) {
            // Includi una nuova intestazione per il campo isAdmin
            writer.println("ID,Nome,Cognome,Email,Amministratore");

            List<Clienti> clientiList = clientiDAO.getAllClienti();
            for (Clienti cliente : clientiList) {
                // Includi il valore del campo isAdmin in ogni riga
                writer.println(cliente.getId() + "," + cliente.getNome() + "," + cliente.getCognome() + ","
                        + cliente.getEmail() + "," + (cliente.getIsAdmin() ? "Sì" : "No"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}