
import android.content.Intent;
import android.os.Build;
import android.widget.Button;
import org.aplie.android.robolectrictestbutton.BuildConfig;
import org.aplie.android.robolectrictestbutton.MainActivity;
import org.aplie.android.robolectrictestbutton.R;
import org.aplie.android.robolectrictestbutton.SecondActivity;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.junit.Before;
import static junit.framework.Assert.assertTrue;
import org.robolectric.shadows.ShadowActivity;

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)
public class MainActivityTest {
    private MainActivity activity;

    // @Before => Anotación que especifica que este método se debe ejecutar antes de la ejecución de cada prueba.
    // Utilidad para configurar los objetos que son necesarios para la prueba
    @Before
    public void setup() {
        activity = Robolectric.setupActivity(MainActivity.class);
    }

    @Test
    public void secondActivityStartedOnClick(){
        Button button = (Button) activity.findViewById(R.id.button);
        button.performClick();

        //El intent que esperamos que se lanze cuando pulsemos el boton
        Intent expectedIntent = new Intent(activity, SecondActivity.class);

        //Un Activity de Android no tiene ningun forma de mostrar las activities que lanza
        //ShadowActivity tiene un registro de todas las actividades lanzadas y muestra esa información a traves del metodo getNextStartedActivity
        ShadowActivity shadowActivity = Shadows.shadowOf(activity);
        Intent actualIntent = shadowActivity.getNextStartedActivity();

        assertTrue(actualIntent.filterEquals(expectedIntent));
    }
}
