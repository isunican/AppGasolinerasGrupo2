package com.isunican.proyectobase.Views;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.isunican.proyectobase.Model.Vehiculo;
import com.isunican.proyectobase.Presenter.PresenterVehiculos;
import com.isunican.proyectobase.R;

import java.util.List;

public class MisVehiculosActivity extends AppCompatActivity {

    public PresenterVehiculos presenterVehiculos;

    // Vista de lista y adaptador para cargar datos en ella
    public ListView listViewVehiculos;
    public ArrayAdapter<Vehiculo> adapter;

    // Barra de progreso circular para mostar progeso de carga
    ProgressBar progressBar;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_vehiculos);

        this.presenterVehiculos = new PresenterVehiculos();

        // Obtenemos la vista de la lista
        listViewVehiculos = findViewById(R.id.listViewVehiculos);

        // Barra de progreso
        // https://materialdoc.com/components/progress/
        progressBar = new ProgressBar(MisVehiculosActivity.this, null, android.R.attr.progressBarStyleLarge);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(100, 100);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        RelativeLayout layout = findViewById(R.id.activity_lista_vehiculos);
        layout.addView(progressBar, params);

        // Muestra el logo en el actionBar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.por_defecto_mod);

        // Al terminar de inicializar todas las variables
        // se lanza una tarea para cargar los datos de los vehiculos
        // Esto se ha de hacer en segundo plano definiendo una tarea asíncrona
        new CargaDatosVehiculosTask(this).execute();
    }

    public class CargaDatosVehiculosTask extends AsyncTask<Void, Void, Boolean> {
        Activity activity;

        /**
         * Constructor de la tarea asincrona
         *
         * @param activity
         */
        public CargaDatosVehiculosTask(Activity activity) {
            this.activity = activity;
        }

        /**
         * onPreExecute
         *
         * @deprecated deprecated method
         * Metodo ejecutado de forma previa a la ejecucion de la tarea definida en el metodo doInBackground()
         * Muestra un diálogo de progreso
         */
        @Override
        @Deprecated
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);  //To show ProgressBar

        }

        /**
         * doInBackground
         * <p>
         * Tarea ejecutada en segundo plano
         * Llama al presenter para que lance el método de carga de los datos de los vehiculos
         *
         * @param params
         * @return boolean
         */
        @Override
        protected Boolean doInBackground(Void... params) {
            return presenterVehiculos.cargaDatosVehiculos();
        }


        /**
         * onPostExecute
         *
         * @param res
         * @deprecated deprecated method
         * Se ejecuta al finalizar doInBackground
         * Oculta el diálogo de progreso.
         * Muestra en una lista los datos de los vehículos cargados,
         * creando un adapter y pasándoselo a la lista.
         * Define el manejo de la selección de los elementos de la lista,
         * lanzando con una intent una actividad de detalle
         * a la que pasamos un objeto Vehiculo
         */
        @Override
        @Deprecated
        protected void onPostExecute(Boolean res) {
            // Si el progressDialog estaba activado, lo oculta
            progressBar.setVisibility(View.GONE);     // To Hide ProgressBar

            // Si se ha obtenido resultado en la tarea en segundo plano
            if (Boolean.TRUE.equals(res)) {
                adapter = new MisVehiculosActivity.VehiculoArrayAdapter(activity, 0, presenterVehiculos.getVehiculos());
            }
        }
    }


    /*
   ------------------------------------------------------------------
       VehiculoArrayAdapter

       Adaptador para inyectar los datos de las vehiculos
       en el listview del layout principal de la aplicacion
   ------------------------------------------------------------------
   */
    public class VehiculoArrayAdapter extends ArrayAdapter<Vehiculo> {

        private Context context;
        private List<Vehiculo> listaVehiculos;

        // Constructor
        public VehiculoArrayAdapter(Context context, int resource, List<Vehiculo> objects) {
            super(context, resource, objects);
            this.context = context;
            this.listaVehiculos = objects;
        }

        // Llamado al renderizar la lista
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            Log.d("prueba", "Pasa por aquí");
            // Obtiene el elemento que se está mostrando
            Vehiculo vehiculo = listaVehiculos.get(position);

            // Indica el layout a usar en cada elemento de la lista
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(android.content.Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.item_vehiculo, null);
            // Asocia las variables de dicho layout
            TextView modelo = view.findViewById(R.id.textViewModelo);
            TextView anotacion = view.findViewById(R.id.textViewAnotacion);


            view.setBackgroundColor(Color.WHITE);
            modelo.setTextColor(Color.BLACK);
            anotacion.setTextColor(Color.BLACK);

            // Y carga los datos del item
            modelo.setText("Modelo: "+vehiculo.getModelo());
            anotacion.setText(vehiculo.getAnotaciones());


            // Si las dimensiones de la pantalla son menores
            // reducimos el texto de las etiquetas para que se vea correctamente
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            if (displayMetrics.widthPixels < 720) {
                TextView tv = view.findViewById(R.id.textViewModeloLabel);
                RelativeLayout.LayoutParams params = ((RelativeLayout.LayoutParams) tv.getLayoutParams());
                params.setMargins(15, 0, 0, 0);
                tv.setTextSize(11);
                TextView tmp;
                //tmp = view.findViewById(R.id.textViewGasolina95Label);
                //tmp.setTextSize(11);
                tmp = view.findViewById(R.id.textViewModelo);
                tmp.setTextSize(11);
                //tmp = view.findViewById(R.id.textViewGasolina95);
                //tmp.setTextSize(11);
            }

            return view;
        }

    }
}
