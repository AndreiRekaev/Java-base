package edu.school21.sockets.app;

import edu.school21.sockets.client.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Main {
    public static void main(String args[]) throws IOException {
        if(args.length != 1 || !args[0].startsWith("--port=")) {
            System.err.println("Provide port using '--port='");
            System.exit(-1);
        }
        int port = Integer.parseInt(args[0].substring("--port=".length()));

        Client client = new Client();
        client.start(port);
    }
}