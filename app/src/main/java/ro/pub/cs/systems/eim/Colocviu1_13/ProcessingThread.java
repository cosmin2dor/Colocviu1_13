package ro.pub.cs.systems.eim.Colocviu1_13;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Date;

public class ProcessingThread extends Thread {

    private Context context = null;
    private boolean isRunning = true;

    private String instruction;

    public ProcessingThread(Context context, String instruction) {
        this.context = context;
        this.instruction = instruction;
    }

    @Override
    public void run() {
        Log.d("MyTag", "Thread started");
        while (isRunning) {
            sendMessage();
            sleep();
        }
        Log.d("MyTag", "Thread stopped");
    }

    private void sendMessage() {
        Intent intent = new Intent();
        intent.setAction("Broadcast");
        intent.putExtra("Message", new Date(System.currentTimeMillis()) + " " + instruction);
        context.sendBroadcast(intent);
    }

    private void sleep() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    public void stopThread() {
        isRunning = false;
    }
}
