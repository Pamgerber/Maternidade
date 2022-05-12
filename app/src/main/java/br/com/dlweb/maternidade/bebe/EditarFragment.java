package br.com.dlweb.maternidade.bebe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import br.com.dlweb.maternidade.R;

public class EditarFragment extends Fragment {

    private DatabaseHelper databaseHelper;
    private Bebe b;
    private EditText etNomeMae;
    private EditText etCrmMedico;
    private EditText etNome;
    private EditText etDataNascimento;
    private EditText etPeso;
    private EditText etAltura;

    public EditarFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.bebe_fragment_editar, container, false);
        Bundle b = getArguments();
        int id_bebe = b.getInt("id");
        databaseHelper = new DatabaseHelper(getActivity());
        b = databaseHelper.getByIdBebe(id_bebe);

        etNomeMae = v.findViewById(R.id.editTextNomeMae);
        etCrmMedico = v.findViewById(R.id.editTextCrmMedico);
        etNome = v.findViewById(R.id.editTextNomeBebe);
        etDataNascimento = v.findViewById(R.id.editTextDataNascimentoBebe);
        etPeso = v.findViewById(R.id.editTextPesoBebe);
        etAltura = v.findViewById(R.id.editTextAlturaBebe);

        etNomeMae.setText(String.valueOf(m.getNomeMae()));
        etCrmMedico.setText(String.valueOf(m.getCrmMedico()));
        etNome.setText(m.getNome());
        etDataNascimento.setText(m.getData_nascimento());
        etPeso.setText(m.getPeso());
        etAltura.setText(m.getAltura());

        Button btnEditar = v.findViewById(R.id.buttonEditarBebe);

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editar(id_bebe);
            }
        });

        Button btnExcluir = v.findViewById(R.id.buttonExcluirBebe);

        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle(R.string.dialog_excluir_bebe);
                builder.setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        excluir(id_bebe);
                    }
                });
                builder.setNegativeButton(R.string.nao, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Não faz nada
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
        return v;
    }

    private void editar (int id) {
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
            m.setId(id);
            b.setNomeMae(Integer.parseInt(etNomeMae.getText().toString()));
            b.setCrmMedico(Integer.parseInt(etCrmMedico.getText().toString()));
            b.setNome(etNome.getText().toString());
            b.setDataNascimento(etDataNascimento.getText().toString());
            b.setPeso(etPeso.getText().toString());
            b.setAltura(etAltura.getText().toString());
            databaseHelper.updateBebe(b);
            Toast.makeText(getActivity(), "Mãe atualizada!", Toast.LENGTH_LONG).show();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameBebe, new ListarFragment()).commit();
        }
    }

    private void excluir(int id) {
        b = new Bebe();
        b.setId(id);
        databaseHelper.deleteBebe(m);
        Toast.makeText(getActivity(), "Bebê excluída!", Toast.LENGTH_LONG).show();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameBebe, new ListarFragment()).commit();
    }
}