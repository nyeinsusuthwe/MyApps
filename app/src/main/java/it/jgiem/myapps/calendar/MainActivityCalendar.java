package it.jgiem.myapps.calendar;

import android.graphics.Color;
import android.icu.util.LocaleData;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.Locale;

import it.jgiem.myapps.R;
import it.jgiem.myapps.databinding.ActivityMainCalendarBinding;
import mmcalendar.MyanmarDate;


public class MainActivityCalendar extends AppCompatActivity {

    private ActivityMainCalendarBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainCalendarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initUi();
    }

    private void initUi() {
        //LocalDate firstDayofMonth = LocalDate.of(2024, 7,1);

        LocalDate firstDayofMonth = LocalDate.now().minusDays(LocalDate.now().getDayOfMonth()-1);

        int val = firstDayofMonth.getDayOfWeek().getValue();
        for (int i = 0; i<35; i++){
            Button btn = (Button) binding.glDate.getChildAt(i);

            if (i < val){
                btn.setVisibility(View.INVISIBLE);
            } else if (i < firstDayofMonth.lengthOfMonth() + val){
                int index = i - val;
                LocalDate date = firstDayofMonth.plusDays(index);
                btn.setText(String.valueOf(date.getDayOfMonth()));
                btn.setTag(date);


                if (date.equals(LocalDate.now())){
                    btn.setBackgroundResource(R.drawable.bg_today);
                    View view = new View(this);
                    view.setTag(date);
                    onDateClicked(view);
                }

                if (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY) {
                    btn.setTextColor(Color.parseColor("#FF0000"));
                }

            } else {
                btn.setVisibility(View.INVISIBLE);
            }
        }

    }



    private View prevSelectedDate;

    public void onDateClicked(View view) {
        LocalDate date = (LocalDate) view.getTag();

        if (prevSelectedDate != null) {
            LocalDate prevDate = (LocalDate) prevSelectedDate.getTag();
            if (prevDate.isEqual(LocalDate.now())) {
                prevSelectedDate.setBackgroundResource(R.drawable.bg_today);
            } else {
                prevSelectedDate.setBackgroundResource(R.drawable.bg_item);
            }
            prevSelectedDate = null;
        }
        view.setBackgroundResource(R.drawable.bg_selected);
        prevSelectedDate = view;

        MyanmarDate myanmarDate = MyanmarDate.of(date);
        binding.tvDay.setText(String.format(Locale.getDefault(), "%02d", date.getDayOfMonth()));
        binding.tvFullDate.setText(String.format(Locale.ENGLISH, "%02d/%02d/%04d", date.getDayOfMonth(), date.getMonthValue(), date.getYear()));
        binding.tvDate.setText(myanmarDate.getWeekDay());
        binding.tvDetail.setText(myanmarDate.format("S s k, B y k, M p f r En"));
    }
}