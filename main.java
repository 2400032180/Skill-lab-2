import java.sql.*;

public class InventoryCRUD {

    static final String URL = "jdbc:mysql://localhost:3306/inventory_db";
    static final String USER = "root";
    static final String PASS = "YOUR_PASSWORD";

    public static void main(String[] args) {

        try {

            Connection con = DriverManager.getConnection(URL, USER, PASS);

            // INSERT PRODUCT
            String insert = "INSERT INTO products(name,description,price,quantity) VALUES(?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(insert);
            ps.setString(1,"Laptop");
            ps.setString(2,"Gaming Laptop");
            ps.setDouble(3,75000);
            ps.setInt(4,10);
            ps.executeUpdate();

            System.out.println("Product Inserted");

            // READ PRODUCT
            String select = "SELECT * FROM products";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(select);

            System.out.println("Product List:");

            while(rs.next()){
                System.out.println(
                rs.getInt("id")+" "+
                rs.getString("name")+" "+
                rs.getString("description")+" "+
                rs.getDouble("price")+" "+
                rs.getInt("quantity"));
            }

            // UPDATE PRODUCT
            String update = "UPDATE products SET price=?, quantity=? WHERE id=?";
            PreparedStatement ps2 = con.prepareStatement(update);
            ps2.setDouble(1,72000);
            ps2.setInt(2,8);
            ps2.setInt(3,1);
            ps2.executeUpdate();

            System.out.println("Product Updated");

            // DELETE PRODUCT
            String delete = "DELETE FROM products WHERE id=?";
            PreparedStatement ps3 = con.prepareStatement(delete);
            ps3.setInt(1,2);
            ps3.executeUpdate();

            System.out.println("Product Deleted");

            con.close();

        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}