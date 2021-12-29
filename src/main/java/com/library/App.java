package com.library;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;


/**
 * Hello world!
 *
 */
public class App

{
    static Configuration conf = HBaseConfiguration.create();
    public static void main( String[] args ) throws Exception
    {
        conf.set("hbase.zookeeper.quorum","zoo-1.au.adaltas.cloud");
        conf.set("hbase.zookeeper.property.clientPort","2181");

        Connection connection = ConnectionFactory.createConnection(conf);
        Table library = connection.getTable(TableName.valueOf("ece_2021_fall_app_1:library"));

        Scan scan1 = new Scan();
        ResultScanner scanner1 = library.getScanner(scan1);

        for (Result res : scanner1) {
            System.out.println(res);
        }
    }
}
