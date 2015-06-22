package com.askme.getit.askmesearch;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;



/**
 * Created by hari on 31/01/15.
 */
public class MaterialProgressDialog {
    Context context;
    Dialog dialog;

    public MaterialProgressDialog(Context context) {
        this.context = context;

        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        // Include dialog.xml file
        View view = LayoutInflater.from(context).inflate(R.layout.progress_layout, null, false);
        dialog.addContentView(view,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        dialog.setCancelable(false);
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {

            @Override
            public void onCancel(DialogInterface dialog) {
                // TODO Auto-generated method stub
            }
        });
        dialog.setCanceledOnTouchOutside(false);

        ProgressBarCircular progressBarCircular = (ProgressBarCircular) dialog.findViewById(R.id.progressIndicator);
        progressBarCircular.setBackgroundColor(context.getResources().getColor(R.color.blue));

    }


    public void showDialog() {
        if(dialog.isShowing()==false)
        {
            dialog.show();
            Log.i("btpopup", "show");
        }
    }

    public void dismissDialog() {
        if(dialog.isShowing())
        {
            dialog.dismiss();
            Log.i("btpopup", "dismiss");
        }
    }

    public boolean isShowing() {
        return dialog.isShowing();
    }
}
