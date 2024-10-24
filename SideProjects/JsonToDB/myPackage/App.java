package myPackage;

import java.sql.*;

public class App
{
    public static void main(String[] args)
    {
        String jsonContent = Json.jsonToString("/home/hossain/Desktop/Ecommerce/SideProjects/JsonToDB/myPackage/watches.json");
        Product[] products = Json.getProducts(jsonContent);

        try {

            String statement = "INSERT INTO "+DBInfo.table+" (brand, name, price, stock, pic_url) "
                                + "VALUES (?,?,?,?,?)";

            // 1. Get a connection to database
            Connection myConn = DriverManager.getConnection(DBInfo.url, DBInfo.user, DBInfo.password);
            
            // 2. Create a statement
            PreparedStatement myStmt = myConn.prepareStatement(statement);

            for (Product product : products )
            {
                myStmt.setString(1, product.getBrand());
                myStmt.setString(2, product.getName());
                myStmt.setInt(3, product.getPrice());
                myStmt.setInt(4, product.getStock());
                myStmt.setString(5, product.getPic_url());

                // 3.Execute SQL query
                boolean error = myStmt.execute();
                
                if (!error)
                {
                    System.out.println("successfull!");
                }
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
        
}