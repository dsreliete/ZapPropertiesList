package com.challenge.zap.zappropertieslist.propertyAdd;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.challenge.zap.zappropertieslist.R;
import com.challenge.zap.zappropertieslist.Utils;
import com.challenge.zap.zappropertieslist.data.model.ZapMessage;
import com.challenge.zap.zappropertieslist.propertyDetail.PropertyDetailActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by eliete on 9/1/16.
 */
public class AddPropertyDialogFragment extends android.support.v4.app.DialogFragment{

    @BindView(R.id.nameEditText)
    EditText nameEditText;
    @BindView(R.id.emailEditText)
    EditText emailEditText;
    @BindView(R.id.phoneEditText)
    EditText phoneEditText;
    @BindView(R.id.text_input_layout_name)
    TextInputLayout nameTextInput;
    @BindView(R.id.text_input_layout_email)
    TextInputLayout emailTextInput;
    @BindView(R.id.text_input_layout_phone)
    TextInputLayout phoneTextInput;
    @BindView(R.id.send)
    Button sendButton;

    private int codeAds;

    public static AddPropertyDialogFragment newInstance(int code){
        Bundle bundle = new Bundle();
        bundle.putInt(PropertyDetailActivity.EXTRA_FRAGMENT, code);
        AddPropertyDialogFragment dialog = new AddPropertyDialogFragment();
        dialog.setArguments(bundle);
        return dialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        codeAds = getArguments().getInt(PropertyDetailActivity.EXTRA_FRAGMENT);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_send_msg, container, false);
        ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        nameEditText.requestFocus();
        Utils.cleanErrorMessage(nameEditText, nameTextInput);
        nameTextInput.setErrorEnabled(true);
        Utils.cleanErrorMessage(emailEditText, emailTextInput);
        emailTextInput.setErrorEnabled(true);
        Utils.cleanErrorMessage(phoneEditText, phoneTextInput);
        phoneTextInput.setErrorEnabled(true);

        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        getDialog().setTitle(R.string.send);
    }

    @OnClick(R.id.send) public void sendMessage(){

        String name = nameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String phone = phoneEditText.getText().toString();

        if (name.isEmpty()){
            Utils.setEmptyMessage(nameTextInput, "nome");
        }else if (email.isEmpty()){
            Utils.setEmptyMessage(emailTextInput, "e-mail");
        }else if (phone.isEmpty()) {
            Utils.setEmptyMessage(phoneTextInput, "telefone");
        }else{
            SendMessage listener = (SendMessage) getActivity();
            ZapMessage zapMessage = new ZapMessage(name, email, phone, codeAds);
            listener.broadcastMessage(zapMessage);
            dismiss();
        }
    }


    public interface SendMessage {
        void broadcastMessage(ZapMessage message);
    }


}
