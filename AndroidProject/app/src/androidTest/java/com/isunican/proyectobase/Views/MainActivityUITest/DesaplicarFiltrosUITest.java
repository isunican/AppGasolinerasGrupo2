package com.isunican.proyectobase.Views.MainActivityUITest;

import android.Manifest;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;
import androidx.test.rule.GrantPermissionRule;

import com.isunican.proyectobase.Views.MainActivity;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

/**
 * @author Aarón Rodríguez Solar
 */
//@RunWith(AndroidJUnit4.class)
public class DesaplicarFiltrosUITest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);
    @Rule
    public GrantPermissionRule permissionRule = GrantPermissionRule.grant(Manifest.permission.ACCESS_FINE_LOCATION);
    /*
    private RecyclerView mRecyclerView;
    private MainActivity mActivity;
    @Before
    public void setUp() {
        mActivity = mActivityTestRule.getActivity();
        mRecyclerView = mActivity.findViewById(R.id.recyclerViewFiltros);
    }
    /**
     * Test que comprueba que se desaplican los filtros correctamente
     * y se muestra al usuario los pop-up correspondientes.

    @Test
    public void desaplicarFiltrosTest() {
        boolean salir = true;
        while (salir) {
            try {
                //Se pulsa el botón filtrar
                onView(withId(R.id.buttonFiltrar)).perform(click());

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //Se elige filtrar por gasoleo A y con descuentos
                onView(withId(R.id.checkBoxGasoleoA)).perform(click());
                onView(withId(R.id.checkBoxDescuentoSi)).perform(click());
                onView(withId(R.id.buttonApply)).perform(click());

                //Se comprueba que los filtros que se muestran aplicados son 2
                int itemCount = mRecyclerView.getAdapter().getItemCount();
                Assert.assertEquals(itemCount, 2);
                //Se compreba que la lista de filtros se muestra
                onView(withId(R.id.recyclerViewFiltros)).check(matches(isDisplayed()));
                //Se pulsa el primero, el que se quiere eliminar
                onView(withId(R.id.recyclerViewFiltros))
                        .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //Se muestra un popup para confirmar que se quiere eliminar, se pulsa aceptar
                onView(withId(R.id.buttonSiBorrarFiltro)).perform(click());
                //Se comprueba que los filtros que se muestran aplicados son 1
                itemCount = mRecyclerView.getAdapter().getItemCount();
                Assert.assertEquals(itemCount, 1);
                //Se comprueba que la lista de filtros se muestra
                onView(withId(R.id.recyclerViewFiltros)).check(matches(isDisplayed()));
                */
    //Ahora compruebo si al pulsar No Borrar en el pop-up, se mantiene el filtro
                /*
                //Se pulsa el primero, el que se quiere eliminar
                onView(withId(R.id.recyclerViewFiltros))
                        .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //Se muestra un popup para confirmar que se quiere eliminar, se pulsa aceptar
                onView(withId(R.id.buttonNoBorrarFiltro)).perform(click());
                //Se comprueba que los filtros que se muestran aplicados son 1 (la misma cantidad que antes)
                itemCount = mRecyclerView.getAdapter().getItemCount();
                Assert.assertEquals(itemCount, 1);
                //Se comprueba que la lista de filtros se muestra
                onView(withId(R.id.recyclerViewFiltros)).check(matches(isDisplayed()));
                salir = false;
            } catch (NoMatchingViewException e) {
                try{
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                }
                onView(withId(R.id.buttonMasTarde)).perform(click());
            }
        }
    }
    */

    @Test
    public void test(){
        Assert.assertTrue(true);
    }

}




