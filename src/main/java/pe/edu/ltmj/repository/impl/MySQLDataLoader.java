package pe.edu.ltmj.repository.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class MySQLDataLoader {

    public static void main(String[] args) throws Exception {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "123456");
        PreparedStatement pstmt = con.prepareStatement("INSERT INTO person VALUES(?,?,?)");
        File csvFile = new File("/home/luiggi_mendoza/Downloads/mock-eff.csv");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        int counter = 0;
        try (Scanner scanner = new Scanner(csvFile)) {
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(",");
                Long id = Long.valueOf(data[0]);
                String name = data[1];
                String birthday = data[2];

                pstmt.setLong(1, id);
                pstmt.setString(2, name);
                pstmt.setDate(3, new java.sql.Date(sdf.parse(birthday).getTime()));

                pstmt.addBatch();

                if (++counter == 1000) {
                    pstmt.executeBatch();
                    counter = 0;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (counter > 0) {
            pstmt.executeBatch();
        }
        pstmt.close();
        con.close();
    }
}
