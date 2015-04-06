package com.onyxbeacon.couponbeacondemo7.fragments;

import android.app.DialogFragment;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.model.GraphUser;
import com.google.gson.Gson;
import com.onyxbeacon.OnyxBeaconApplication;
import com.onyxbeacon.OnyxBeaconManager;
import com.onyxbeacon.couponbeacondemo7.R;
import com.onyxbeacon.couponbeacondemo7.logging.LogDialogFragment;
import com.onyxbeacon.couponbeacondemo7.logging.MyLogger;
import com.onyxbeacon.couponbeacondemo7.activity.MainActivity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Work 2 on 9/23/2014.
 */
public class SettingsFragment extends Fragment {
    private Button authButton;
    private OnyxBeaconManager mManager;
    private MyLogger mLogger;

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        mLogger = (MyLogger) this.getArguments().getSerializable(MainActivity.LOGGER_KEY);
        mManager = OnyxBeaconApplication.getOnyxBeaconManager(getActivity().getApplicationContext());
        View settingsView = inflater.inflate(R.layout.fragment_settings, container, false);
        authButton = (Button) settingsView.findViewById(R.id.authButton);
        authButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fbLoginClick(v);
            }
        });

        Button sendLogsBtn = (Button) settingsView.findViewById(R.id.logButton);
        sendLogsBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                displayLogDialog(v);
            }
        });

        return settingsView;
    }

    public void fbLoginClick(View btnView){
        Session session = Session.getActiveSession();
        if(session == null || (session != null && session.isClosed())) {
            // start Facebook Login
            Session.openActiveSession(this.getActivity(), true, new Session.StatusCallback() {
                // callback when session changes state
                @Override
                public void call(Session session, SessionState state,
                                 Exception exception) {
                    if (session.isOpened()) {
                        // make request to the /me API
                        Request.newMeRequest(session,
                                new Request.GraphUserCallback() {
                                    // callback after Graph API response with user
                                    // object
                                    @Override
                                    public void onCompleted(GraphUser user,
                                                            Response response) {
                                        if (user != null) {
                                            Map<String, Object> userMap = user
                                                    .asMap();
                                            HashMap<String, Map<String, Object>> userMetricsHash = new HashMap<String, Map<String, Object>>();
                                            userMap.put("type", "FB");
                                            Log.d("ONX", userMap.toString());
                                            Log.d("ONX",
                                                    (new Gson()).toJson(userMap));
                                            userMetricsHash.put("userMetrics", userMap);
                                            System.out.println("Result FB" + (new Gson()).toJson(userMap));
                                            mManager.sendGenericUserProfile((new Gson()).toJson(userMetricsHash));
                                            authButton.setText("Logout from Facebook");
                                        }
                                    }
                                }).executeAsync();
                    }
                }
            });
        } else {
            // facebook session exists, do logout
            if (!session.isClosed()) {
                session.closeAndClearTokenInformation();
                //clear your preferences if saved
                authButton.setText("Login in Facebook");
            }
        }
    }

    private void displayLogDialog(View btnView) {
        DialogFragment logDialogFragment = new LogDialogFragment();
        logDialogFragment.show(getActivity().getFragmentManager(), "logs");
    }

    public void sendLogs(String name) {
        mManager.sendLogs(mLogger.getFolderPath(), mLogger.getFileName(), name);
    }

}
