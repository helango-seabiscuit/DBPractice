package com.hemalatha.db.performance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SampleTest {

    public static void main(String[] args) throws Exception{
        testLocalUrl();
    }


    public static void testLocalUrl() throws Exception{
        String url = "jdbc:postgresql://localhost:5432/helangovan_clustr?prepareThreshold=0";
        //String url = "jdbc:postgresql://carly:5432/build_clustr?prepareThreshold=0";
        Connection conn = DriverManager.getConnection(url);
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT count(*) from clustr_info ");
        while (rs.next()){
            System.out.println(rs.getLong(1));
        }
        rs.close();
        st.close();
        conn.close();
    }
}
