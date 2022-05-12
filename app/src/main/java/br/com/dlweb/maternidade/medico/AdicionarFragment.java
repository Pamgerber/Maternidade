package br.com.dlweb.maternidade.medico;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import br.com.dlweb.maternidade.R;

public class AdicionarFragment extends Fragment {

    private EditText etEspecialidade;
    private EditText etNome;
    private EditText etCelular;

    public AdicionarFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.medico_fragment_adicionar, container, false);

        etEspecialidade= v.findViewById(R.id.editTextEspecialidadeMedico);
        etNome = v.findViewById(R.id.editTextNomeMedico);
        etCelular = v.findViewById(R.id.editTextCelularMedico);

        Button btnAdicionar = v.findViewById(R.id.buttonAdicionarMedico);

        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adicionar();
            }
        });

        return v;
    }

    private void adicionar () {
        if (etEspecialidade.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe a especialidade!", Toast.LENGTH_LONG).show();
        } else if (etNome.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o nome!", Toast.LENGTH_LONG).show();
        } else if (etCelulargetText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o celular!", Toast.LENGTH_LONG).show();
        } else {
            DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
            Medico me = new Medico();
            me.setEspecialidade(etEspecialidade.getText().toString());
            me.setNome(etNome.getText().toString());
            me.setCelular(Integer.parseInt(etCelular.getText().toString()));
            databaseHelper.createMedico(me);
            Toast.makeText(getActivity(), "Médico salvo!", Toast.LENGTH_LONG).show();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameMedico, new ListarFragment()).commit();
        }
    }
}
