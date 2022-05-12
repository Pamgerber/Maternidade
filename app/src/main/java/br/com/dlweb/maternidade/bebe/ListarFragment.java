package br.com.dlweb.maternidade.bebe;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;


import androidx.fragment.app.Fragment;

import br.com.dlweb.maternidade.R;
import br.com.dlweb.maternidade.database.DatabaseHelper;

public class ListarFragment extends Fragment {

    public ListarFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.bebe_fragment_listar, container, false);
        DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
        ListView lv = v.findViewById(R.id.listViewBebes);
        databaseHelper.getAllBebe(getActivity(), lv);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView tvId = view.findViewById(R.id.textViewIdListBebe);
                Bundle b = new Bundle();
                b.putInt("id", Integer.parseInt(tvId.getText().toString()));

                EditarFragment editarFragment = new EditarFragment();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                editarFragment.setArguments(b);
                ft.replace(R.id.frameBebe, editarFragment).commit();
            }
        });

        return v;
    }
}