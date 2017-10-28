package id.sch.smktelkom_mlg.privateassignment.xirpl131.showhockeyapp;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    static final CameraPosition JTG = CameraPosition.builder()
            .target(new LatLng(-6.858623, 111.642224))
            .zoom(15)
            .bearing(0)
            .tilt(45)
            .build();

    GoogleMap m_map;
    boolean mapReady = false;
    MarkerOptions house;
    MarkerOptions quba;
    MarkerOptions pasar;
    MarkerOptions kec;
    MarkerOptions koramil;
    MarkerOptions smp;

    @Override
    public Resources getResources() {
        return super.getResources();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        house = new MarkerOptions()
                .position(new LatLng(-6.886892, 111.654675))
                .title("My House")
                .icon(vectorToBitmap(R.drawable.ic_accessibility_black_24dp, Color.parseColor("#A4C639")));

        quba = new MarkerOptions()
                .position(new LatLng(-6.887492, 111.656472))
                .title("Masjid Ar-rahmah")
                .icon(vectorToBitmap(R.drawable.ic_accessibility_black_24dp, Color.parseColor("#A4C639")));

        pasar = new MarkerOptions()
                .position(new LatLng(-6.879152, 111.658757))
                .title("Pasar Jatirogo")
                .icon(vectorToBitmap(R.drawable.ic_accessibility_black_24dp, Color.parseColor("#A4C639")));

        kec = new MarkerOptions()
                .position(new LatLng(-6.885809, 111.658339))
                .title("Kantor Kec. Jatirogo")
                .icon(vectorToBitmap(R.drawable.ic_accessibility_black_24dp, Color.parseColor("#A4C639")));

        koramil = new MarkerOptions()
                .position(new LatLng(-6.888232, 111.662722))
                .title("Koramil Jatirogo")
                .icon(vectorToBitmap(R.drawable.ic_accessibility_black_24dp, Color.parseColor("#A4C639")));

        smp = new MarkerOptions()
                .position(new LatLng(-6.884760, 111.657529))
                .title("SMPN 1 Jatirogo")
                .icon(vectorToBitmap(R.drawable.ic_accessibility_black_24dp, Color.parseColor("#A4C639")));

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap map) {
        Toast toast = Toast.makeText(getApplicationContext(), "Map Ready!", Toast.LENGTH_SHORT);
        toast.show();
        mapReady = true;
        m_map = map;
        m_map.addMarker(house);
        m_map.addMarker(quba);
        m_map.addMarker(pasar);
        m_map.addMarker(kec);
        m_map.addMarker(koramil);
        m_map.addMarker(smp);
        flyTo(JTG);
    }

    private void flyTo(CameraPosition target) {
        m_map.animateCamera(CameraUpdateFactory.newCameraPosition(target), 10000, null);
    }

    private BitmapDescriptor vectorToBitmap(@DrawableRes int id, @ColorInt int color) {
        Drawable vectorDrawable = ResourcesCompat.getDrawable(getResources(), id, null);
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(),
                vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        DrawableCompat.setTint(vectorDrawable, color);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }
}
