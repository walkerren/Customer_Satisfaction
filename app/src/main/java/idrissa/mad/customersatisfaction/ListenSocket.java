package idrissa.mad.customersatisfaction;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import static android.widget.Toast.LENGTH_SHORT;

public class ListenSocket extends AsyncTask {
    MainCallback mainCallback = null;
    private Exception exception;

    ListenSocket(MainCallback mainCallback){
        this.mainCallback=mainCallback;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        try (Socket socket = new Socket("10.0.2.2",59908)) {
            Scanner in = new Scanner(socket.getInputStream());
            String message=in.nextLine();
            if (mainCallback != null) {
                mainCallback.updateTextview(message);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void onPostExecute(Void result) {
        // TODO: check this.exception
        // TODO: do something with the feed
        super.onPostExecute(result);
    }

    public interface MainCallback {
        // Declaration of the template function for the interface
        void updateTextview(String myString);
    }
}
