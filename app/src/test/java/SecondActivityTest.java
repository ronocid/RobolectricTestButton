
import android.os.Build;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import org.aplie.android.robolectrictestbutton.BuildConfig;
import org.aplie.android.robolectrictestbutton.R;
import org.aplie.android.robolectrictestbutton.SecondActivity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import static junit.framework.Assert.assertEquals;

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)
public class SecondActivityTest {
    private SecondActivity activity;

    // @Before => Anotación que especifica que este método se debe ejecutar antes de la ejecución de cada prueba.
    // Utilidad para configurar los objetos que son necesarios para la prueba
    @Before
    public void setup() {
        activity = Robolectric.setupActivity(SecondActivity.class);
    }

    @Test
    public void withoutEnteringData(){
        EditText et = (EditText) activity.findViewById(R.id.editText);
        TextView tv = (TextView) activity.findViewById(R.id.textView2);
        Button button = (Button) activity.findViewById(R.id.button2);

        button.performClick();

        String result = tv.getText().toString();

        assertEquals("No se muestra el mensaje cuando no se introducen datos","Sin texto",result);
    }

    @Test
    public void withEnteringData(){
        EditText et = (EditText) activity.findViewById(R.id.editText);
        TextView tv = (TextView) activity.findViewById(R.id.textView2);
        Button button = (Button) activity.findViewById(R.id.button2);
        et.setText("Prueba de texto");
        button.performClick();

        String count = tv.getText().toString();

        assertEquals("No se muestra el mensaje cuando no se introducen datos","15",count);
    }
}
