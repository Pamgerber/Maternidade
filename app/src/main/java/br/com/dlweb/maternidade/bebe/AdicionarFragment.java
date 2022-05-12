package br.com.dlweb.maternidade.bebe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import br.com.dlweb.maternidade.R;

public class AdicionarFragment extends Fragment {

    private EditText etNomeMae;
    private EditText etCrmMedico;
    private EditText etNome;
    private EditText etDataNascimento;
    private EditText etPeso;
    private EditText etAltura;

    public AdicionarFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.bebe_fragment_adicionar, container, false);

        etNomeMae = v.findViewById(R.id.editTextNomeMae);
        etCrmMedico = v.findViewById(R.id.editTextCrmMedico);
        etNome = v.findViewById(R.id.editTextNomeBebe);
        etDataNascimento = v.findViewById(R.id.editTextDataNascimentoBebe);
        etPeso = v.findViewById(R.id.editTextPesoBebe);
        etAltura = v.findViewById(R.id.editTextAlturaBebe);

        Button btnAdicionar = v.findViewById(R.id.buttonAdicionarBebe);

        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adicionar();
            }
        });

        return v;
    }

    private void adicionar () {
        if (etNomeMae.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o nome da mae!", Toast.LENGTH_LONG).show();
        } else if (etCrmMedico.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o crm do medico!", Toast.LENGTH_LONG).show();
        } else if (etNome.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o nome!", Toast.LENGTH_LONG).show();
        } else if (etDataNascimento.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe a data de nascimento!", Toast.LENGTH_LONG).show();
        } else if (etPeso.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o peso!", Toast.LENGTH_LONG).show();
        } else if (etAltura.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe a altura!", Toast.LENGTH_LONG).show();
        } else {
            DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
            Bebe b = new Bebe();
            b.setNomeMae(Integer.parseInt(etNomeMae.getText().toString()));
            b.setCrmMedico(Integer.parseInt(etCrmMedico.getText().toString()));
            b.setNome(etNome.getText().toString());
            b.setDataNascimento(etDataNascimento.getText().toString());
            b.setPeso(etPeso.getText().toString());
            b.setAltura(etAltura.getText().toString());
        Toast.makeText(getActivity(), "Bebê salvo!", Toast.LENGTH_LONG).show();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameBebe, new ListarFragment()).commit();
    }

}