package fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.deneme2.GlobalVariables;
import com.example.deneme2.R;

public class ProfileFragment extends Fragment {
    String adSoyad, dogumTarihi, sehir,saglik;
    TextView adSoyad_tv, dogumTarihi_tv, sehir_tv, saglik_tv;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_profile, container, false);
        adSoyad_tv = myView.findViewById(R.id.adSoyad_tv);
        dogumTarihi_tv = myView.findViewById(R.id.dogumTarihi_tv);
        sehir_tv = myView.findViewById(R.id.sehir_tv);
        saglik_tv = myView.findViewById(R.id.saglik_tv);

        //Kullanıcı profil bilgilerini global değişkenlerden çekerek gösteriyoruz
        adSoyad = ((GlobalVariables) getActivity().getApplication()).getAd()+" " +((GlobalVariables) getActivity().getApplication()).getSoyad();
        dogumTarihi = ((GlobalVariables) getActivity().getApplication()).getDogumTarihi();
        sehir = ((GlobalVariables) getActivity().getApplication()).getSehir();
        saglik = ((GlobalVariables) getActivity().getApplication()).getSaglik();


        adSoyad_tv.setText(adSoyad);
        dogumTarihi_tv.setText(dogumTarihi);
        sehir_tv.setText(sehir);
        saglik_tv.setText(saglik);

        // Inflate the layout for this fragment
        return myView;
    }
}