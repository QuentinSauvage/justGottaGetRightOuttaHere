package com.example.justgottagetrightouttahere_client.network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TCPServer implements Runnable{
    ServerSocket serverSocket = null;
    ServerReceiver receiver = null;
    /**
     * TCP server for tests
     */
    public TCPServer(){
        try {
            serverSocket = new ServerSocket(1789);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(){
        Socket socket = null;
        BufferedReader reader = null;
        StringBuffer stringBuffer = new StringBuffer();

        System.err.println("[SRV] Server listening for connections");
        try {
            socket = serverSocket.accept();
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.err.println("[SRV] Client connected!");
        Thread t = new Thread(new ServerReceiver(socket));
        t.start();

        for(;;){
            Scanner myObj = new Scanner(System.in);  // Create a Scanner object
            System.err.println("Enter line to send");

            String line = myObj.nextLine();  // Read user input
            send(line,socket);
        }
        //System.err.println("[SRV] Client disconected!");
    }

    public void send(String s, Socket socket){
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(socket.getOutputStream(),true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.err.println("[SRV] Server sending "+s);
        printWriter.println(s);
        printWriter.flush();
    }

    public static void main(String[] args) {
        TCPServer server = new TCPServer();
        server.run();
    }
}

//{"Action":"loadLevel","Blocks":[[0,0,0],[0,0,0]],"Players":[{"xPos":0,"yPos":0},{"xPos":1,"yPos":1},{"xPos":0,"yPos":23},{"xPos":0,"yPos":21}],"Objects":[[3,3,3],[0,0,0]],"Bullshit":"Shitbull"}
//{"Action":"loadLevel","Blocks":[[0,0,0],[0,0,0]],"Players":[{"xPos":0,"yPos":0},{"xPos":1,"yPos":1},{"xPos":0,"yPos":23},{"xPos":0,"yPos":21}],"Objects":[[0,0,0],[0,0,0]],"Bullshit":"Shitbull"}

//{"Action":"loadLevel","Blocks":[[0,0,0],[0,0,0],[0,0,0]],"Players":[{"xPos":0,"yPos":0,"Role":0},{"xPos":1,"yPos":1,"Role":1},{"xPos":0,"yPos":23,"Role":2},{"xPos":0,"yPos":21,"Role":3}],"Objects":[[0,0,0],[0,0,0],[0,0,0]]}

//{"Action":"loadLevel","Blocks":[[1,1,1],[2,2,2],[3,3,3]]};
//{"Action":"loadLevel","Blocks":[[1,1,1],[2,2,2],[3,3,3],[3,3,3],[3,3,3],[3,3,3],[3,3,3],[3,3,3]]}
//{"Action":"loadLevel", "Name":"Level1", "Blocks":[[1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1],[1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1],[1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1],[335,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1],[1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1],[1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1],[1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1097,1097,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1],[1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1097,1097,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1],[1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1097,1097,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1],[1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1],[1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1],[1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1],[1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1],[1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1],[1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1],[1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1],[1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1],[335,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1],[1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1],[1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1],[1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1],[1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1],[1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1],[1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1],[1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1],[1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1],[1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1],[1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1],[1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1],[1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1],[1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1],[1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1]],"Objects":[[0,0,0,0,0,556,0,0,0,0,0,988,579,579,579,579,579,579,579,579,0,0,0,0,0,0,209,0,0,1061,0,0],[0,1019,0,0,0,556,0,0,0,0,0,0,579,579,579,579,579,579,579,579,0,0,0,0,0,0,209,0,0,0,0,0],[0,0,0,0,0,556,0,0,0,0,0,0,587,587,587,587,587,587,587,587,0,0,0,0,0,0,209,1106,1106,1106,1106,1106],[0,0,0,0,0,64,0,0,0,0,0,0,449,450,450,645,646,450,450,451,0,0,0,0,0,0,209,0,0,0,0,0],[671,671,671,671,671,72,0,0,0,0,0,0,457,458,458,653,654,458,458,459,0,0,0,0,0,0,209,0,0,0,0,0],[671,671,671,671,671,64,988,0,0,0,0,1108,0,0,0,0,0,0,0,0,1111,0,0,0,0,0,209,65,65,0,65,65],[671,671,671,671,671,72,0,0,0,0,0,1108,0,0,0,0,0,0,0,0,1111,0,0,0,0,0,219,67,67,67,67,67],[671,671,671,671,671,64,546,546,546,546,546,1108,0,0,0,0,0,0,0,0,1111,0,0,0,0,0,0,0,0,0,0,0],[671,671,671,671,671,72,554,554,554,554,554,1108,0,0,0,0,0,0,0,0,1111,0,0,0,0,0,0,0,0,0,0,0],[41,41,41,41,41,41,41,41,41,41,41,41,1106,1106,1106,1106,1106,1106,1106,1106,1106,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[671,671,671,671,212,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,211,217,217,217,217],[0,0,0,0,209,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,65,67,41,41,0],[0,0,995,0,209,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,65,67,41,41,0],[0,0,0,0,209,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,65,67,41,41,1056],[0,0,0,0,209,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,65,67,41,41,0],[0,0,0,0,220,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,219,217,217,217,217],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],[217,217,217,217,217,212,41,41,41,41,41,41,0,0,0,0,0,0,0,0,41,41,41,41,41,41,211,217,217,217,217,217],[1030,0,0,0,0,209,41,41,41,41,41,41,0,0,0,0,0,0,0,0,41,41,41,41,41,41,209,0,0,0,0,953],[0,0,0,0,0,209,0,0,0,0,41,41,0,0,0,0,0,0,0,0,41,41,0,0,0,0,209,0,0,0,0,0],[217,217,303,303,217,220,0,0,0,0,41,41,0,0,0,0,0,0,0,0,41,41,0,65,65,0,219,217,303,303,217,217],[546,546,303,303,546,547,0,0,0,0,41,41,0,0,0,0,0,0,0,0,41,41,0,0,0,0,67,0,303,303,0,0],[546,546,546,546,546,547,0,0,0,0,41,41,0,0,0,0,0,0,0,0,41,41,0,0,0,0,67,0,0,0,0,0],[554,554,554,554,554,555,0,0,0,0,41,41,0,0,0,0,0,0,0,0,41,41,0,0,0,0,67,0,0,0,0,0]],"Players":[{"xPos":0,"yPos":0,"Role":0},{"xPos":0,"yPos":2,"Role":1},{"xPos":0,"yPos":2,"Role":2},{"xPos":0,"yPos":3,"Role":3},{"xPos":0,"yPos":4,"Role":4},{"xPos":0,"yPos":5,"Role":5}]};

//{"Action":"move","PosX":1000,"PosY":0,"Player":0}
//{"Action":"action","Changes":[{"xPos":0,"yPos":0,"value":20},{"xPos":0,"yPos":1,"value":20}]}
class ServerReceiver implements Runnable{
    Socket socket;

    public ServerReceiver(Socket socket) {
        this.socket = socket;
    }

    /**
     * Listen to the socket given when building the object
     * And calls the message handler when a message is received (if null does nothing)
     */
    @Override
    public void run() {
        BufferedReader reader = null;
        StringBuffer stringBuffer = new StringBuffer();

        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        System.err.println("[CLI] Server thread listening ...");

        for(;;){
            try {
                stringBuffer = new StringBuffer();
                stringBuffer.append(reader.readLine());
                if(stringBuffer.toString() !=null)System.err.println("[SRV] Received : " + stringBuffer.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}