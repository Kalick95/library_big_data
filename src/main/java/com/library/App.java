package com.library;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.hbase.client.HTable;


import java.io.IOException;

import static org.apache.hadoop.hbase.util.Bytes.toBytes;

/**
 * Hello world!
 *
 */
public class App 
{
    static Configuration conf = HBaseConfiguration.create();
    public static void main( String[] args ) throws Exception {
        /*
        conf.set("zookeeper.znode.parent", "/hbase-secure");
        conf.set("hbase.zookeeper.quorum","zoo-1.au.adaltas.cloud");
        conf.set("hbase.zookeeper.quorum","zoo-2.au.adaltas.cloud");
        conf.set("hbase.zookeeper.quorum","zoo-3.au.adaltas.cloud");
        conf.set("hbase.zookeeper.property.clientPort","2181");

        Connection connection = ConnectionFactory.createConnection(conf);
        Table library = connection.getTable(TableName.valueOf("ece_2021_fall_app_1:library"));

        Scan scan1 = new Scan();
        ResultScanner scanner1 = library.getScanner(scan1);


        System.out.println( "Hello World!" );

        for(Result res : scanner1) {
            System.out.println(res);
            break;
        }
        */

        // Instantiating Configuration class
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

        System.out.println("name: " + name + " city: " + city);


    }
}
