package io.github.haintrain.SQL;

import java.sql.Connection;
import java.sql.Statement;

public class SQL {

    private Statement statement;
    private static Connection connection;

    public SQL(Connection connection){
        this.connection = connection;
    }

    public static Connection getConnection(){
        return connection;
    }
}
