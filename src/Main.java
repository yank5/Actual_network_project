import java.io.IOException;
import java.net.*;
import java.sql.Array;
import java.util.*;

public class Main {

    public static ArrayList<String> hostList=new ArrayList<>(Arrays.asList("control.case","google.com","duckduckgo.com","youtube.com","facebook.com","twitter.com","baidu.com","wikipedia.org","yandex.ru","yahoo.com"));
    public static void main(String[] args) {
        port();
//        if(args.length>0) {
//            for (String c : args) {
//                switch (c) {
//                    case "--help", "-h" -> System.out.println("Utility tool for debugging network issues");
//                    case "--udp", "-u" -> udpTest();
//                    case "--tcp", "t" -> tcp();        // +add port parameter
//                    case "--local", "-l" -> local();
//                    case "--device", "-D" -> devTest();
//                    case "--dns", "-d" -> dnsTest();
//                    case "--port", "-p" -> port();     //misc port scanner
//                }
//            }
//        } else{
//            System.out.println("Utility tool for debugging network issues");
//        }



    }


    public static void tt(){

    }

    public static void port() {
        List<List<Integer>> list=new ArrayList<List<Integer>>(); //2d arraylist :p

        int y=0; //index
        for (var c : hostList.toArray()) {
            list.add(new ArrayList<Integer>());
            for (int x = 70; x < 90; ++x) {
                try {
                    new Socket().connect(new InetSocketAddress((String) c, x), 100);
//                    System.out.println("Port " + x + " reachable for: "+c);
                    list.get(y).add(x);
                } catch (IOException e) {
//            throw new RuntimeException(e);
//                    System.out.println("Port " + x + " unreachable for: " + c);
                }
            }
            ++y;
        }
        var i=0;
        for (var oo:list){
            System.out.println(hostList.get(i)+" ports: ");
            for(var p:oo){
                System.out.print(p);
            }
            System.out.println();
            ++i;
        }
    }

    //multithreading class





    public static void tcp(int port){ //only testing sockets not ports
        for (var c : hostList.toArray()) {
            try {
                new Socket(InetAddress.getByName((String) c), port);
                System.out.println("Socket success for: "+c+" port: 80");
            } catch (IOException e) {
                System.out.println("Socket failed for: "+c+" port: 80");
            }
        }
//            System.out.println(address.isReachable(1000));
        
    }
    public static void devTest() { //testing devices
        try {
            var x = NetworkInterface.getNetworkInterfaces().asIterator();
            while (x.hasNext()) {
                var c = x.next();
                System.out.println(c.getName() + " up=" + c.isUp());
            }
        } catch (SocketException e){
            System.out.println("No devices accessible?");
        }
    }

    public static void dnsTest() { //testing resolving urls
        for(var x: hostList) {
            try {
                if (InetAddress.getByName(x).getHostAddress() != null)
                    System.out.println("Dns success for: "+x);
//                    System.out.println(InetAddress.getByName(x).getHostAddress());
            } catch (UnknownHostException e) {
                    System.out.println("Dns failed for: "+x);
            }
        }
    }
    public static void udpTest() {

    }
}
