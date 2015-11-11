/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbhelpers;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cities;

/**
 *
 * @author jdefano
 */
public class AddQuery {
    
    private Connection conn;
    
    public AddQuery(){
        
         try {
            Properties props = new Properties();
            InputStream instr = getClass().getResourceAsStream("dbConn.properties");
            try {
                props.load(instr);
            } catch (IOException ex) {
                Logger.getLogger(AddQuery.class.getName()).log(Level.SEVERE, null, ex);
            }
            String driver = props.getProperty("driver.name");
            String url = props.getProperty("server.name");
            String username = props.getProperty("user.name");
            String passwd = props.getProperty("user.password");
            Class.forName(driver);
            try {
                conn = DriverManager.getConnection(url,username,passwd);
            } catch (SQLException ex) {
                Logger.getLogger(AddQuery.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void doAdd (Cities city) {
        
        try {
            String query = "INSERT INTO cities (cityPopulation, cityLocation, cityFounded, cityName) VALUES (?, ?, ?, ?)";
            
            PreparedStatement ps = conn.prepareStatement (query);
            
            ps.setInt(1, city.getCityPopulation());
            ps.setString(2, city.getCityLocation());
            ps.setInt(3, city.getCityFounded());
            ps.setString(4, city.getCityName());
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AddQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}