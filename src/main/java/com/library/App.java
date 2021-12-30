package com.library;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.TableNotFoundException;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Admin;


import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;

import static org.apache.hadoop.hbase.util.Bytes.toBytes;

/**
 * Hello world!
 *
 */
public class App
{

    private static String driverName = "org.apache.hive.jdbc.HiveDriver";

    /**
     * @param args
     * @throws SQLException
     */
    public static void main(String[] args) throws SQLException {
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.exit(1);
        }
        //replace "hive" here with the name of the user the queries should run as
        Connection con = DriverManager.getConnection("jdbc:hive2://zoo-1.au.adaltas.cloud:2181", "t.koning-ece", "root12345");
        //replace 10.10.10.10 with the Hive server address
        Statement stmt = ((java.sql.Connection) con).createStatement();
        String tableName = "xyz";
        stmt.execute("drop table if exists " + tableName);

        stmt.execute("CREATE TABLE "+tableName+" (key int,value STRING) ROW FORMAT DELIMITED FIELDS TERMINATED BY ','");

        //stmt.execute("CREATE TABLE " + tableName + " (key int, value string)");
        // show tables
        String sql = "show tables '" + tableName + "'";
        System.out.println("Running: " + sql);
        ResultSet res = stmt.executeQuery(sql);
        if (res.next()) {
            System.out.println(res.getString(1));
        }
        // describe table
        sql = "describe " + tableName;
        System.out.println("Running: " + sql);
        res = stmt.executeQuery(sql);
        while (res.next()) {
            System.out.println(res.getString(1) + "\t" + res.getString(2));
        }

        // load data into table
        // NOTE: filepath has to be local to the hive server
        String filepath = "/tmp/a.txt";
        sql = "load data local inpath '" + filepath + "' into table " + tableName;
        System.out.println("Running: " + sql);
        stmt.execute(sql);

        // select * query
        sql = "select * from " + tableName;
        System.out.println("Running: " + sql);
        res = stmt.executeQuery(sql);
        while (res.next()) {
            System.out.println(String.valueOf(res.getInt(1)) + "\t" + res.getString(2));
        }

        // regular hive query
        sql = "select count(1) from " + tableName;
        System.out.println("Running: " + sql);
        res = stmt.executeQuery(sql);
        while (res.next()) {
            System.out.println(res.getString(1));
        }
    }

    /*static Configuration conf = HBaseConfiguration.create();
    public static void main( String[] args ) throws Exception {
        conf.set("zookeeper.znode.parent", "/hbase-secure");
        conf.set("hbase.zookeeper.quorum","zoo-1.au.adaltas.cloud");
        conf.set("hbase.zookeeper.quorum","zoo-2.au.adaltas.cloud");
        conf.set("hbase.zookeeper.quorum","zoo-3.au.adaltas.cloud");
        conf.set("hbase.zookeeper.property.clientPort","2181");

        try {
            Connection connection = ConnectionFactory.createConnection(conf);
            Table library = connection.getTable(TableName.valueOf("ece_2021_fall_app_1:test_kalick_fazil"));
        } catch (TableNotFoundException tnfe){
            System.out.println("pas de table");
        }

        try ( Admin admin= ConnectionFactory.createConnection(conf).getAdmin()) {
            System.out.println("la");
            System.out.println(admin.tableExists(TableName.valueOf("ece_2021_fall_app_1:test_kalick_fazil")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


     *//*   Scan scan1 = new Scan();
        ResultScanner scanner1 = library.getScanner(scan1);


        System.out.println( "Hello World!" );

        for(Result res : scanner1) {
            System.out.println(res);
            break;
        }*//*
//        Get get = new Get(toBytes("1"));
//        System.out.println(library);
//        Result result = library.get(get);
//        System.out.println(Bytes.toString(result.getRow()));


*//*        // Instantiating Configuration class
        Configuration config = HBaseConfiguration.create();
        Connection connection = ConnectionFactory.createConnection(conf);

        Table library = connection.getTable(TableName.valueOf("ece_2021_fall_app_1:library"));


        // Instantiating Get class
        Get g = new Get(Bytes.toBytes("448170"));

        // Reading the data
        Result result = library.get(g);

        // Reading values from Result class object
        byte [] value = result.getValue(Bytes.toBytes("details"),Bytes.toBytes("name"));

        byte [] value1 = result.getValue(Bytes.toBytes("personal"),Bytes.toBytes("city"));

        // Printing the values
        String name = Bytes.toString(value);
        String city = Bytes.toString(value1);

        System.out.println("name: " + name + " city: " + city);*//*


    }*/
}
