package co.edu.unipiloto.proyecto_3r;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

import androidx.core.app.NotificationCompat;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MessageService extends IntentService {


    public static final String EXTRA_MESSAGE="messqge";
    public static final int NOTIFICATION_ID=5453;

    public MessageService() {

        super("MessageService");
    }




    @Override
    protected void onHandleIntent(Intent intent) {
        synchronized (this){
            try {
                wait(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        String texto = intent.getStringExtra(EXTRA_MESSAGE);

        mostrar(texto);
    }

    private void mostrar(String texto){
        Log.v("Mensaje de prueba",texto);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(android.R.drawable.sym_def_app_icon)
                .setContentTitle(texto)
                .setContentText("lamentamos la espera");

        Intent actionInten = new Intent(this,MapaVista.class);
        PendingIntent actionPendingIntent = PendingIntent.getActivity(
                this,
                0,
                actionInten,
                PendingIntent.FLAG_CANCEL_CURRENT
        );

        builder.setContentIntent(actionPendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, builder.build());


    }


}