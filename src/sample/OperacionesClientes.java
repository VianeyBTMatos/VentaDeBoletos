package sample;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class OperacionesClientes {

    Connection connection;

    public OperacionesClientes(Connection conn){
        this.connection = conn;
    }

    public Cliente getCliente(int id){
        int clienteId = 0;
        String nombre="", apellidos="", direccion="";

        String query = "SELECT clienteid, nombre, apellidos, direccion " +
                "FROM cliente " +
                "WHERE clienteID = " + id ;
        System.out.println(query);
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            //Recorre cada resultado de todas las consultas realizadas
              if (rs.next()) {
                    clienteId = rs.getInt("clienteid");
                    nombre = rs.getString("nombre");
                    apellidos = rs.getString("apellidos");
                    direccion = rs.getString("direccion");
              }
            //System.out.println(clienteId + ", " + nombre + " " + apellidos + ", " + direccion);
            return new Cliente(clienteId, nombre, apellidos, direccion);
        }
        catch (java.sql.SQLException ex){
            ex.printStackTrace();
            System.out.println("SQLException:␣" + ex.getMessage());
            System.out.println("SQLState:␣" + ex.getSQLState());
            System.out.println("VendorError:␣" + ex.getErrorCode());

            return null;
        }
    }



    public ArrayList<Cliente> getClientes(String nombreCliente){
        int clienteId = 0;
        String nombre="", apellidos="", direccion="";

        String query = "SELECT clienteid, nombre, apellidos, direccion " +
                "FROM cliente " +
                "WHERE nombre = '" + nombreCliente + "'";
        System.out.println(query);
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            ArrayList<Cliente> arrayClientes= new ArrayList<>();

            while(rs.next()) {
                System.out.println("Entra");
                clienteId = rs.getInt("clienteid");
                nombre = rs.getString("nombre");
                apellidos = rs.getString("apellidos");
                direccion = rs.getString("direccion");
                arrayClientes.add(new Cliente(clienteId, nombre, apellidos, direccion));
            }

            //System.out.println(clienteId + ", " + nombre + " " + apellidos + ", " + direccion);

            return arrayClientes;
        }
        catch (java.sql.SQLException ex){
            ex.printStackTrace();
            System.out.println("SQLException:␣" + ex.getMessage());
            System.out.println("SQLState:␣" + ex.getSQLState());
            System.out.println("VendorError:␣" + ex.getErrorCode());

            return null;
        }
    }

    public int deleteCliente(int id){
        int clienteId;
        String nombre, apellidos, direccion;

        String query = "delete from cliente where clienteID = " + id;

        int numRegs = 0;
        try {
            Statement stmt = connection.createStatement();
            numRegs = stmt.executeUpdate(query);

            System.out.println("Cantidad de registros afectados: " + numRegs);
        }
        catch (java.sql.SQLException ex){
            ex.printStackTrace();
            System.out.println("SQLException:␣" + ex.getMessage());
            System.out.println("SQLState:␣" + ex.getSQLState());
            System.out.println("VendorError:␣" + ex.getErrorCode());
        }

        return numRegs;
    }

    public int insertCliente(String nombre, String apellidos, String direccion){

        String query = "insert into cliente(nombre, apellidos, direccion) " +
                "values ('" + nombre + "', '" + apellidos + "', '" + direccion + "')";

      /*  insert into cliente(nombre, apellidos, direccion
                values ('Jorge', 'Estrada', 'Lázaro Cárdenas 123')  */

        int numRegs = 0;
        try {
            Statement stmt = connection.createStatement();
            numRegs = stmt.executeUpdate(query);

            System.out.println("Cantidad de registros afectados: " + numRegs);

        }
        catch (java.sql.SQLException ex){
            ex.printStackTrace();
            System.out.println("SQLException:␣" + ex.getMessage());
            System.out.println("SQLState:␣" + ex.getSQLState());
            System.out.println("VendorError:␣" + ex.getErrorCode());
        }

        return numRegs;
    }

    public int updateCliente(int id1, String nombre1, String apellidos1, String direccion1){

        String query = "UPDATE cliente set nombre = '" + nombre1 + "', apellidos = '" + apellidos1 + "', direccion = '" +direccion1+ "' where clienteID = " + id1;
        System.out.println(query);

        int numRegs = 0;
        try {
            Statement stmt = connection.createStatement();
            numRegs = stmt.executeUpdate(query);

            System.out.println("Cantidad de registros afectados: " + numRegs);
        }
        catch (java.sql.SQLException ex){
            ex.printStackTrace();
            System.out.println("SQLException:␣" + ex.getMessage());
            System.out.println("SQLState:␣" + ex.getSQLState());
            System.out.println("VendorError:␣" + ex.getErrorCode());
        }

        return numRegs;
    }
}
