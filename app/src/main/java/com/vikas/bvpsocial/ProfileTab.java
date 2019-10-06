package com.vikas.bvpsocial;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileTab extends Fragment {

        EditText edtProfileName,edtProfileBio,edtProfileHobbies,edtProfileSport
        ,edtProfileProfession;

        Button btnUpdateInfo;

    public ProfileTab() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_profile_tab, container, false);

        edtProfileName=view.findViewById(R.id.edtProfileName);
        edtProfileBio=view.findViewById(R.id.edtProfileBio);
        edtProfileHobbies=view.findViewById(R.id.edtProfileHobbies);
        edtProfileProfession=view.findViewById(R.id.edtProfileProfession);
        edtProfileSport=view.findViewById(R.id.edtProfileSport);

        btnUpdateInfo=view.findViewById(R.id.btnUpdateInfo);

        final ParseUser parseUser=ParseUser.getCurrentUser();

        if(parseUser.get("profileName")==null)
        {
            edtProfileName.setText("");
        }
        else
        {
            edtProfileName.setText(parseUser.get("profileName")+"");
        }
        if(parseUser.get("profileSport")==null)
        {
            edtProfileSport.setText("");
        }
        else {
            edtProfileSport.setText(parseUser.get("profileSport") + "");
        }
        if(parseUser.get("profileProfession")==null)
        {
            edtProfileProfession.setText("");
        }
        else {
            edtProfileProfession.setText(parseUser.get("profileProfession") + "");
        }
        if(parseUser.get("profileBio")==null)
        {
            edtProfileBio.setText("");
        }
        else {
            edtProfileBio.setText(parseUser.get("profileBio") + "");
        }
        if(parseUser.get("profileHobbies")==null)
        {
            edtProfileHobbies.setText("");
        }
        else {
            edtProfileHobbies.setText(parseUser.get("profileHobbies") + "");
        }


        btnUpdateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    parseUser.put("profileName",edtProfileName.getText().toString());
                    parseUser.put("profileBio",edtProfileBio.getText().toString());
                    parseUser.put("profileProfession",edtProfileProfession.getText().toString());
                    parseUser.put("profileHobbies",edtProfileHobbies.getText().toString());
                    parseUser.put("profileSport",edtProfileSport.getText().toString());

                    parseUser.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            if(e==null)
                            {
                                FancyToast.makeText(getContext(),"Successfully Updated",
                                        FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                            }
                            else
                            {
                                FancyToast.makeText(getContext(),e.getMessage(),
                                        FancyToast.LENGTH_LONG,FancyToast.INFO,true).show();
                            }
                        }
                    });
            }
        });



        return view;
    }

}
