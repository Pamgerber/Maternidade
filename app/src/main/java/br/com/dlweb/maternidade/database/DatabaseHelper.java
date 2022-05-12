package br.com.dlweb.maternidade.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import br.com.dlweb.maternidade.R;
import br.com.dlweb.maternidade.mae.Mae;
import br.com.dlweb.maternidade.medico.Medico;
import br.com.dlweb.maternidade.bebe.Bebe;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "maternidade";
    private static final String TABLE_MAE = "mae";
    private static final String TABLE_BEBE = "bebe";
    private static final String TABLE_MEDICO = "medico";

    private static final String CREATE_TABLE_MAE = "CREATE TABLE " + TABLE_MAE + " (" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "nome VARCHAR(100), " +
            "logradouro VARCHAR(200), " +
            "numero INTEGER, " +
            "bairro VARCHAR(50), " +
            "cidade VARCHAR(100), " +
            "cep VARCHAR(9), " +
            "fixo VARCHAR(14), " +
            "celular VARCHAR(15), " +
            "comercial VARCHAR(15), " +
            "data_nascimento DATE);";
    private static final String SQL_CREATE_BEBE = "CREATE TABLE " + TABLE_BEBE + "(" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "id_mae INTEGER, " +
            "crm_medico INTEGER, " +
            "nome VARCHAR(100), " +
            "data_nascimento DATE, " +
            "peso DECIMAL(8,3), " +
            "altura INTEGER," +
            "CONSTRAINT fk_bebe_mae FOREIGN KEY (id_mae) REFERENCES mae (id), " +
            "CONSTRAINT fk_bebe_medico FOREIGN KEY (crm_medico) REFERENCES medico (crm))";
    private static final String SQL_CREATE_MEDICO = "CREATE TABLE " + TABLE_MEDICO + "(" +
            "_crm INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "especialidade VARCHAR(100), " +
            "nome VARCHAR(100), " +
            "celular VARCHAR(15);";
    private static final String DROP_TABLE_MAE = "DROP TABLE IF EXISTS " + TABLE_MAE;
    private static final String DROP_TABLE_BEBE = "DROP TABLE IF EXISTS " + TABLE_BEBE;
    private static final String DROP_TABLE_MEDICO = "DROP TABLE IF EXISTS " + TABLE_MEDICO;



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_MAE);
        db.execSQL(CREATE_TABLE_BEBE);
        db.execSQL(CREATE_TABLE_MEDICO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_MAE);
        db.execSQL(SQL_DELETE_BEBE);
        db.execSQL(SQL_DELETE_MEDICO);
    }

    /* Início CRUD Mãe */
    public long createMae (Mae m) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome", m.getNome());
        cv.put("logradouro", m.getLogradouro());
        cv.put("numero", m.getNumero());
        cv.put("bairro", m.getBairro());
        cv.put("cidade", m.getCidade());
        cv.put("cep", m.getCep());
        cv.put("fixo", m.getFixo());
        cv.put("celular", m.getCelular());
        cv.put("comercial", m.getComercial());
        cv.put("data_nascimento", m.getData_nascimento());
        long id = db.insert(TABLE_MAE, null, cv);
        db.close();
        return id;
    }

    public long updateMae (Mae m) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome", m.getNome());
        cv.put("logradouro", m.getLogradouro());
        cv.put("numero", m.getNumero());
        cv.put("bairro", m.getBairro());
        cv.put("cidade", m.getCidade());
        cv.put("cep", m.getCep());
        cv.put("fixo", m.getFixo());
        cv.put("celular", m.getCelular());
        cv.put("comercial", m.getComercial());
        cv.put("data_nascimento", m.getData_nascimento());
        long id = db.update(TABLE_MAE, cv, "_id = ?", new String[]{String.valueOf(m.getId())});
        db.close();
        return id;
    }

    public long deleteMae (Mae m) {
        SQLiteDatabase db = this.getWritableDatabase();
        long id = db.delete(TABLE_MAE, "_id = ?", new String[]{String.valueOf(m.getId())});
        db.close();
        return id;
    }

    public Mae getByIdMae (int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"_id", "nome", "logradouro", "numero", "bairro", "cidade", "cep", "fixo", "celular", "comercial", "data_nascimento"};
        Cursor data = db.query(TABLE_MAE, columns, "_id = ?", new String[]{String.valueOf(id)}, null, null, null);
        data.moveToFirst();
        Mae m = new Mae();
        m.setId(data.getInt(0));
        m.setNome(data.getString(1));
        m.setLogradouro(data.getString(2));
        m.setNumero(data.getInt(3));
        m.setBairro(data.getString(4));
        m.setCidade(data.getString(5));
        m.setCep(data.getString(6));
        m.setFixo(data.getString(7));
        m.setCelular(data.getString(8));
        m.setComercial(data.getString(9));
        m.setData_nascimento(data.getString(10));
        data.close();
        db.close();
        return m;
    }

    public void getAllMae (Context context, ListView lv) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"_id", "nome", "celular"};
        Cursor data = db.query(TABLE_MAE, columns, null, null, null, null, null);
        int[] to = {R.id.textViewIdListMae, R.id.textViewNomeListMae, R.id.textViewCelularListMae};
        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(context, R.layout.mae_item_list_view, data, columns, to, 0);
        lv.setAdapter(simpleCursorAdapter);
        db.close();
    }
    /* Fim CRUD Mãe */

    /* Início CRUD Bebê */
    public long createBebe (Bebe b) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome", b.getNome());
        cv.put("data_nascimento", b.getData_nascimento());
        cv.put("peso", b.getPeso());
        cv.put("altura", b.getAltura());
        long id = db.insert(TABLE_BEBE, null, cv);
        db.close();
        return id;
    }

    public long updateBebe (Bebe b) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome", b.getNome());
        cv.put("data_nascimento", b.getData_nascimento());
        cv.put("peso", b.getPeso());
        cv.put("altura", b.getAltura());
        long id = db.update(TABLE_BEBE, cv, "_id = ?", new String[]{String.valueOf(b.getId())});
        db.close();
        return id;
    }

    public long deleteBebe (Bebe b) {
        SQLiteDatabase db = this.getWritableDatabase();
        long id = db.delete(TABLE_BEBE, "_id = ?", new String[]{String.valueOf(b.getId())});
        db.close();
        return id;
    }

    public Bebe getByIdBebe (int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"_id", "nome", "data_nascimento", "peso", "altura"};
        Cursor data = db.query(TABLE_BEBE, columns, "_id = ?", new String[]{String.valueOf(id)}, null, null, null);
        data.moveToFirst();
        Bebe b = new Bebe();
        b.setId(data.getInt(0));
        b.setNome(data.getString(1));
        b.setDataNascimento(data.getString(2));
        b.setPeso(data.getString(3));
        b.setAltura(data.getString(4));
        data.close();
        db.close();
        return b;
    }

    public void getAllBebe (Context context, ListView lv) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"_id", "nome", "peso"};
        Cursor data = db.query(TABLE_BEBE, columns, null, null, null, null, null);
        int[] to = {R.id.textViewIdListBebe, R.id.textViewNomeListBebe, R.id.textViewPesoListBebe};
        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(context, R.layout.bebe_item_list_view, data, columns, to, 0);
        lv.setAdapter(simpleCursorAdapter);
        db.close();
    }
    /* Fim CRUD Bebê */

    /* Início CRUD Médico */
    public long createMedico (Medico me) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("especialidade", b.getEspecialidade());
        cv.put("nome", b.getNome());
        cv.put("celular", b.getCelular());
        long crm = db.insert(TABLE_MEDICO, null, cv);
        db.close();
        return crm;
    }

    public long updateMedico(Medico me) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("especialidade", b.getEspecialidade());
        cv.put("nome", b.getNome());
        cv.put("celular", b.getCelular());
        long crm = db.update(TABLE_MEDICO, cv, "_crm = ?", new String[]{String.valueOf(me.getCrm())});
        db.close();
        return crm;
    }

    public long deleteMedico (Medico me) {
        SQLiteDatabase db = this.getWritableDatabase();
        long crm = db.delete(TABLE_MEDICO, "_crm = ?", new String[]{String.valueOf(me.getCrm())});
        db.close();
        return crm;
    }

    public Bebe getByCrmMedico (int crm) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"_crm", "especialidade", "nome", "celular"};
        Cursor data = db.query(TABLE_MEDICO, columns, "_crm = ?", new String[]{String.valueOf(crm)}, null, null, null);
        data.moveToFirst();
        Medico me = new Medico();
        me.setCrm(data.getCrm(0));
        me.setEspecialidade(data.getString(1));
        me.setNome(data.getString(2));
        me.setCelular(data.getString(3));
        data.close();
        db.close();
        return me;
    }

    public void getAllMedico (Context context, ListView lv) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"_crm", "nome", "celular"};
        Cursor data = db.query(TABLE_MEDICO, columns, null, null, null, null, null);
        int[] to = {R.id.textViewCrmListMedico, R.id.textViewNomeListMedico, R.id.textViewCelularListMedico};
        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(context, R.layout.medico_item_list_view, data, columns, to, 0);
        lv.setAdapter(simpleCursorAdapter);
        db.close();
    }
    /* Fim CRUD Médico */
}
