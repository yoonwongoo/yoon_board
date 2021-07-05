package com.yoon.test;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

public class jdbcTest {
	static { 
        try { 
            Class.forName("oracle.jdbc.driver.OracleDriver"); 
        } catch(Exception e) { 
            e.printStackTrace(); 
        } 
    } 
    
    @Test 
    public void testConnection() { 
        try(Connection con = DriverManager.getConnection( 
                "jdbc:oracle:thin:@localhost:1521:xe", 
                "system", 
                "6245912")){ 
            System.out.println(con); 
        } catch (Exception e) { 
            fail(e.getMessage()); 
        } 
    
    }    

}
