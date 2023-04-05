import java.awt.*;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.*;
import java.net.http.WebSocket;
import java.security.Provider;
import java.security.SecureRandom;
import java.sql.Array;
import java.util.*;
import java.util.List;

public class Main extends Thread {

    public static ArrayList<String> hostList = new ArrayList<>(Arrays.asList("control.case", "google.com", "duckduckgo.com", "youtube.com", "facebook.com", "twitter.com", "baidu.com", "wikipedia.org", "yandex.ru", "yahoo.com"));
    public static List<List<Integer>> list = new ArrayList<List<Integer>>();
    public static void main(String[] args) {
//        if(args.length>0) {
//            for (String c : args) {
//                switch (c) {
//                    default -> System.out.println("Unrecognized option '"+c+"'");
//                    case "--help", "-h" -> System.out.println("Usage: project [OPTION]...");
//                    case "--udp", "-u" -> udpTest();
//                    case "--tcp", "t" -> tcp();
//                    case "--sysinfo", "-s" -> sysInfo();
//                    case "--device", "-D" -> devTest();
//                    case "--dns", "-d" -> dnsTest();
//                    case "--port", "-p" -> port();     //misc port scanner
////                    case "--connect", "-c" ->
//                }
//            }
//        } else{
//            System.out.println("Try 'project --help' for more information.");
//        }


    }


    //    public static void ipv6(){
//        try {
//            var a=NetworkInterface.getNetworkInterfaces().asIterator().next().getHardwareAddress();
//            System.out.println(NetworkInterface.getNetworkInterfaces().asIterator().next().getInterfaceAddresses().iterator().next().getAddress().getHostAddress());
//            byte add[] = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 };
//            Inet6Address inet6Address= (Inet6Address) Inet6Address.getByAddress("google.com",a,5);
//            System.out.println(inet6Address.isIPv4CompatibleAddress());
//        } catch (UnknownHostException e) {
//            throw new RuntimeException(e);
//        } catch (SocketException e) {
//            throw new RuntimeException(e);
//        }
//    }




    public static void sysInfo() {
        System.out.println("JAVA INFO");
        for(var c:System.getProperties().keySet()){
            System.out.println(c+": "+System.getProperties().get(c));
        }
        System.out.println("OS+SHELL INFO");
        for(var c:System.getenv().keySet()){
            System.out.println(c+": "+System.getenv().get(c));
        }
    }

    public static void port() {
         //2d arraylist :p
        int y = 0; //index
        for (var c : hostList.toArray()) {
            list.add(new ArrayList<Integer>());
            for (int x = 1; x < 2; ++x) {
                try {
                    new Socket().connect(new InetSocketAddress((String) c, x), 100);     //// Add status
//                    System.out.println("Port " + x + " reachable for: "+c);
                    list.get(y).add(x);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
//            throw new RuntimeException(e);
//                    System.out.println("Port " + x + " unreachable for: " + c);
                }
            }
            ++y;

        }
        var i = 0;
        for (var oo : list) {
            System.out.println(hostList.get(i) + " ports: ");
            for (var p : oo) {
                System.out.print(p);
            }
            System.out.println();
            ++i;
        }
    }


    //multithreading class


    public static void tcp() { //only testing sockets not ports
        for (var c : hostList.toArray()) { ///port 80 always open on hostlist
            try {
                new Socket().connect(new InetSocketAddress((String)c, 80), 500);
                System.out.println("Socket success for: " + c + " port: "+80);
            } catch (IOException e) {
                System.out.println("Socket failed for: " + c + " port: "+80);
//                System.out.println(e.getMessage());
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
        } catch (SocketException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void dnsTest() { //testing resolving urls
        for (var x : hostList) {
            try {
                if (InetAddress.getByName(x).getHostAddress() != null)
                    System.out.println("Dns success for: " + x);
//                    System.out.println(InetAddress.getByName(x).getHostAddress());
            } catch (UnknownHostException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void udpTest() {   ///im really not sure this even works. whos checking that the udp packet even sends?
        var host="0.0.0.0";
        var port=999;
        DatagramPacket datagramPacket = new DatagramPacket(new byte[]{3,2,1}, 3);
        try {
            SocketAddress socketAddress = new InetSocketAddress(InetAddress.getByName(host), port);
            DatagramSocket datagramSocket = new DatagramSocket();
            datagramSocket.connect(socketAddress);
            System.out.println(datagramSocket.isConnected());
            datagramSocket.send(datagramPacket);
            System.out.println("Successful connection to "+host+" on port: "+port);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
