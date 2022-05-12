package br.com.dlweb.maternidade.medico;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import br.com.dlweb.maternidade.R;

public class EditarFragment extends Fragment {

    private DatabaseHelper databaseHelper;
    private Medico me;
    private EditText etEspecialidade;
    private EditText etNome;
    private EditText etCelular;

    public EditarFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.medico_fragment_editar, container, false);
        Bundle b = getArguments();
        int crm_medico = b.getInt("crm");
        databaseHelper = new DatabaseHelper(getActivity());
        b = databaseHelper.getByCrmMedico(crm_medico);

        etEspecialidade= v.findViewById(R.id.editTextEspecialidadeMedico);
        etNome = v.findViewById(R.id.editTextNomeMedico);
        etCelular = v.findViewById(R.id.editTextCelularMedico);

        etEspecialidade.setText(m.getEspecialidade());
        etNome.setText(m.getNome());
        etCelular.setText(m.getCelular());

        Button btnEditar = v.findViewById(R.id.buttonEditarMedico);

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editar(crm_medico);
            }
        });

        Button btnExcluir = v.findViewById(R.id.buttonExcluirMedico);

        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle(R.string.dialog_excluir_medico);
                builder.setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        excluir(crm_medico);
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
        if (etEspecialidade.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe a especialidade!", Toast.LENGTH_LONG).show();
        } else if (etNome.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o nome!", Toast.LENGTH_LONG).show();
        } else if (etCelulargetText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o celular!", Toast.LENGTH_LONG).show();
        } else {
            DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
            Medico me = new Medico();
            me.setCrm(crm);
            me.setEspecialidade(etEspecialidade.getText().toString());
            me.setNome(etNome.getText().toString());
            me.setCelular(Integer.parseInt(etCelular.getText().toString()));
            databaseHelper.updateMedico(me);
            Toast.makeText(getActivity(), "Médico atualizada!", Toast.LENGTH_LONG).show();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameMedico, new ListarFragment()).commit();
        }
    }

    private void excluir(int id) {
        me = new Medico();
        me.setId(id);
        databaseHelper.deleteMedico(me);
        Toast.makeText(getActivity(), "Médico excluído!", Toast.LENGTH_LONG).show();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameMedico, new ListarFragment()).commit();
    }
}