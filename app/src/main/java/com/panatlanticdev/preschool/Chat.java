package com.panatlanticdev.preschool;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class Chat extends AppCompatActivity {

    private Button enviarButton;

    /** The Conversation list. */
    private ArrayList<Conversation> convList;

    /** The chat adapter. */
    private ChatAdapter adp;

    /** The Editext to compose the message. */
    private EditText txt;

    /** The date of last message in conversation. */
    private Date lastMsgDate;

    /** Flag to hold if the activity is running or not. */
    private boolean isRunning;

    /** The handler. */
    private static Handler handler;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);


        convList = new ArrayList<Conversation>();
        ListView list = (ListView) findViewById(R.id.mensajesListView);
        adp = new ChatAdapter();
        list.setAdapter(adp);
        list.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        list.setStackFromBottom(true);

        txt = (EditText) findViewById(R.id.nuevoMensajeText);
        txt.setInputType(InputType.TYPE_CLASS_TEXT
                | InputType.TYPE_TEXT_FLAG_MULTI_LINE);


        enviarButton = (Button)findViewById(R.id.enviarButton);
        enviarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });

        handler = new Handler() {
            @Override
            public void close() {

            }

            @Override
            public void flush() {

            }

            @Override
            public void publish(LogRecord record) {

            }
        };
    }


    @Override
    protected void onResume()
    {
        super.onResume();
        isRunning = true;
        loadConversationList();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        isRunning = false;
    }



    private void sendMessage()
    {
        if (txt.length() == 0)
            return;

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(txt.getWindowToken(), 0);

        String s = txt.getText().toString();
        final Conversation c = new Conversation(s, new Date(), ParseUser.getCurrentUser().getObjectId(), ParseUser.getCurrentUser().getString("nombrePapa"));
        c.setStatus(Conversation.STATUS_SENDING);
        convList.add(c);
        adp.notifyDataSetChanged();
        txt.setText(null);

        UserInfo user = (UserInfo)getApplication();
        String Seccion = user.getSeccionActual();
        ParseObject po = new ParseObject(Seccion);
        po.put("senderID", ParseUser.getCurrentUser().getObjectId());
        po.put("nombreCompleto", ParseUser.getCurrentUser().getString("nombrePapa"));
        po.put("message", s);
        po.saveEventually(new SaveCallback() {

            @Override
            public void done(ParseException e) {
                if (e == null)
                    c.setStatus(Conversation.STATUS_SENT);
                else
                    c.setStatus(Conversation.STATUS_FAILED);
                adp.notifyDataSetChanged();
            }
        });
    }

    /**
     * Load the conversation list from Parse server and save the date of last
     * message that will be used to load only recent new messages
     */
    private void loadConversationList() {
        UserInfo user = (UserInfo)getApplication();
        String Seccion = user.getSeccionActual();
        ParseQuery<ParseObject> q = ParseQuery.getQuery(Seccion);
        q.orderByDescending("createdAt");
        q.setLimit(30);
        q.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> li, ParseException e)
            {
                if (li != null && li.size() > 0)
                {
                    for (int i = li.size() - 1; i >= 0; i--)
                    {
                        ParseObject po = li.get(i);
                        Conversation c = new Conversation(po.getString("message"), po.getCreatedAt(), po.getString("senderID"), po.getString("nombreCompleto"));
                        convList.add(c);
                        if (lastMsgDate == null
                                || lastMsgDate.before(c.getDate()))
                            lastMsgDate = c.getDate();
                        adp.notifyDataSetChanged();
                    }
                }
                /*handler.postDelayed(new Runnable() {

                    @Override
                    public void run()
                    {
                        if (isRunning)
                            loadConversationList();
                    }
                }, 1000);*/
            }
        });

    }

    /**
     * The Class ChatAdapter is the adapter class for Chat ListView. This
     * adapter shows the Sent or Receieved Chat message in each list item.
     */
    private class ChatAdapter extends BaseAdapter
    {

        @Override
        public int getCount()
        {
            return convList.size();
        }

        @Override
        public Conversation getItem(int arg0)
        {
            return convList.get(arg0);
        }


        @Override
        public long getItemId(int arg0)
        {
            return arg0;
        }


        @Override
        public View getView(int pos, View v, ViewGroup arg2)
        {
            Conversation c = getItem(pos);
            if (c.getSender().equals(ParseUser.getCurrentUser().getObjectId())) {
                v = getLayoutInflater().inflate(R.layout.sendmessage, null);
            }

            else {
                v = getLayoutInflater().inflate(R.layout.receivedmessage, null);
        }
            TextView lbl = (TextView) v.findViewById(R.id.texto);
            lbl.setText(c.getMsg());

            TextView senderDisplay = (TextView) v.findViewById(R.id.senderName);
            senderDisplay.setText(c.getSenderDisplay());

            return v;
        }

    }


}


