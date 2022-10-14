package com.github.florent37.singledateandtimepicker.dialog;

import static com.github.florent37.singledateandtimepicker.widget.SingleDateAndTimeConstants.STEP_MINUTES_DEFAULT;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.StateListDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.github.florent37.singledateandtimepicker.DateHelper;
import com.github.florent37.singledateandtimepicker.R;
import com.github.florent37.singledateandtimepicker.SingleDateAndTimePicker;
import com.github.florent37.singledateandtimepicker.widget.DateWithLabel;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class DoubleDateAndTimePickerDialogWithOutTab extends BaseDialog {


    private Listener listener;
    private final BottomSheetHelper bottomSheetHelper;

//    private SingleDateAndTimePicker single_date_and_time_picker;
    private SingleDateAndTimePicker single_date_and_time_picker;
    private final DateHelper dateHelper = new DateHelper();

    @Nullable
    private String tab0Text, tab1Text, title;
    @Nullable
    private Integer titleTextSize;
    @Nullable
    private Integer bottomSheetHeight;
    @Nullable
    private String todayText;
    @Nullable
    private String buttonOkText;
    @Nullable
    private Date dateNeedToShow;

    private boolean tab0Days, tab0Hours, tab0Minutes;

    private DoubleDateAndTimePickerDialogWithOutTab(Context context) {
        this(context, false);
    }

    private DoubleDateAndTimePickerDialogWithOutTab(Context context, boolean bottomSheet) {
        final int layout = bottomSheet ? R.layout.bottom_sheet_double_picker_bottom_sheet2 :
                R.layout.bottom_sheet_double_picker2;
        this.bottomSheetHelper = new BottomSheetHelper(context, layout);
        this.bottomSheetHelper.setListener(new BottomSheetHelper.Listener() {
            @Override
            public void onOpen() {
            }

            @Override
            public void onLoaded(View view) {
                init(view);
            }

            @Override
            public void onClose() {
                DoubleDateAndTimePickerDialogWithOutTab.this.onClose();
            }
        });
    }

    private void init(View view) {

        single_date_and_time_picker = (SingleDateAndTimePicker) view.findViewById(R.id.single_date_and_time_picker);
//        pickerTab1 = (SingleDateAndTimePicker) view.findViewById(R.id.picker_tab_1);

        if (single_date_and_time_picker != null) {
            if (bottomSheetHeight != null) {
                ViewGroup.LayoutParams params = single_date_and_time_picker.getLayoutParams();
                params.height = bottomSheetHeight;
                single_date_and_time_picker.setLayoutParams(params);
            }
        }

        final View titleLayout = view.findViewById(R.id.sheetTitleLayout);
        final TextView titleTextView = (TextView) view.findViewById(R.id.sheetTitle);
        if (title != null) {
            if (titleTextView != null) {
                titleTextView.setText(title);
                if (titleTextColor != null) {
                    titleTextView.setTextColor(titleTextColor);
                }
                if (titleTextSize != null) {
                    titleTextView.setTextSize(titleTextSize);
                }
            }
            if (mainColor != null && titleLayout != null) {
                titleLayout.setBackgroundColor(mainColor);
            }
        } else {
            titleLayout.setVisibility(View.GONE);
        }

        single_date_and_time_picker.setTodayText(new DateWithLabel(todayText, new Date()));

        final View sheetContentLayout = view.findViewById(R.id.sheetContentLayout);
        if (sheetContentLayout != null) {
            sheetContentLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            if (backgroundColor != null) {
                sheetContentLayout.setBackgroundColor(backgroundColor);
            }
        }


//        buttonTab0.setSelected(true);

//        if (tab0Text != null) {
//            buttonTab0.setText(tab0Text);
//        }
//        buttonTab0.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                displayTab0();
//            }
//        });

//        if (tab1Text != null) {
//            buttonTab1.setText(tab1Text);
//        }
//        buttonTab1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                displayTab1();
//            }
//        });


        final TextView buttonOk = (TextView) view.findViewById(R.id.buttonOk);
        if (buttonOk != null) {
            if (buttonOkText != null) {
                buttonOk.setText(buttonOkText);
            }

            if (mainColor != null) {
                buttonOk.setTextColor(mainColor);
            }

            if (titleTextSize != null) {
                buttonOk.setTextSize(titleTextSize);
            }

            buttonOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    if (isTab0Visible()) {
//                        displayTab1();
//                    } else {
//                        okClicked = true;
//                        close();
//                    }

                    okClicked = true;
                    close();
                }
            });
        }

        if (curved) {
            single_date_and_time_picker.setCurved(true);
//            pickerTab1.setCurved(true);
            single_date_and_time_picker.setVisibleItemCount(DEFAULT_ITEM_COUNT_MODE_CURVED);
//            pickerTab1.setVisibleItemCount(DEFAULT_ITEM_COUNT_MODE_CURVED);
        } else {
            single_date_and_time_picker.setCurved(false);
//            pickerTab1.setCurved(false);
            single_date_and_time_picker.setVisibleItemCount(DEFAULT_ITEM_COUNT_MODE_NORMAL);
//            pickerTab1.setVisibleItemCount(DEFAULT_ITEM_COUNT_MODE_NORMAL);
        }

        single_date_and_time_picker.setDisplayYears(true);

        single_date_and_time_picker.setDisplayDays(false);
        single_date_and_time_picker.setDisplayMonths(true);
        single_date_and_time_picker.setDisplayDaysOfMonth(true);

//        single_date_and_time_picker.setDisplayDays(tab0Days);
        single_date_and_time_picker.setDisplayHours(tab0Hours);
        single_date_and_time_picker.setDisplayMinutes(tab0Minutes);
//        pickerTab1.setDisplayDays(tab1Days);
//        pickerTab1.setDisplayHours(tab1Hours);
//        pickerTab1.setDisplayMinutes(tab1Minutes);

        single_date_and_time_picker.setMustBeOnFuture(mustBeOnFuture);
//        pickerTab1.setMustBeOnFuture(mustBeOnFuture);

        single_date_and_time_picker.setStepSizeMinutes(minutesStep);
//        pickerTab1.setStepSizeMinutes(minutesStep);

        if (mainColor != null) {
            single_date_and_time_picker.setSelectedTextColor(mainColor);
//            pickerTab1.setSelectedTextColor(mainColor);
        }

        if (minDate != null) {
            single_date_and_time_picker.setMinDate(minDate);
//            pickerTab1.setMinDate(minDate);
        }

        if (maxDate != null) {
            single_date_and_time_picker.setMaxDate(maxDate);
//            pickerTab1.setMaxDate(maxDate);
        }

        if (defaultDate != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(defaultDate);
            single_date_and_time_picker.selectDate(calendar);
//            pickerTab1.selectDate(calendar);
        }

        if (dateNeedToShow != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateNeedToShow);
            single_date_and_time_picker.selectDate(calendar);
        }

//        if (tab1Date != null) {
//            Calendar calendar = Calendar.getInstance();
//            calendar.setTime(tab1Date);
//            pickerTab1.selectDate(calendar);
//        }

        if (dayFormatter != null) {
            single_date_and_time_picker.setDayFormatter(dayFormatter);
//            pickerTab1.setDayFormatter(dayFormatter);
        }

        if (customLocale != null) {
            single_date_and_time_picker.setCustomLocale(customLocale);
//            pickerTab1.setCustomLocale(customLocale);
        }

//        if (secondDateAfterFirst) {
//            single_date_and_time_picker.addOnDateChangedListener(new SingleDateAndTimePicker.OnDateChangedListener() {
//                @Override
//                public void onDateChanged(String displayed, Date date) {
//                    pickerTab1.setMinDate(date);
//                    pickerTab1.checkPickersMinMax();
//                }
//            });
//        }
    }

//    @NonNull
//    private StateListDrawable getTabsListDrawable() {
//        final StateListDrawable colorState0 = new StateListDrawable();
//        colorState0.addState(new int[]{android.R.attr.state_selected}, new ColorDrawable(mainColor));
//        colorState0.addState(new int[]{-android.R.attr.state_selected}, new ColorDrawable(backgroundColor));
//        return colorState0;
//    }

    public DoubleDateAndTimePickerDialogWithOutTab setTab0Text(String tab0Text) {
        this.tab0Text = tab0Text;
        return this;
    }

    public DoubleDateAndTimePickerDialogWithOutTab setTab1Text(String tab1Text) {
        this.tab1Text = tab1Text;
        return this;
    }

    public DoubleDateAndTimePickerDialogWithOutTab setButtonOkText(@Nullable String buttonOkText) {
        this.buttonOkText = buttonOkText;
        return this;
    }

    public DoubleDateAndTimePickerDialogWithOutTab setTitle(@Nullable String title) {
        this.title = title;
        return this;
    }

    public DoubleDateAndTimePickerDialogWithOutTab setTitleTextSize(@Nullable Integer titleTextSize) {
        this.titleTextSize = titleTextSize;
        return this;
    }

    public DoubleDateAndTimePickerDialogWithOutTab setBottomSheetHeight(@Nullable Integer bottomSheetHeight) {
        this.bottomSheetHeight = bottomSheetHeight;
        return this;
    }

    public DoubleDateAndTimePickerDialogWithOutTab setTodayText(@Nullable String todayText) {
        this.todayText = todayText;
        return this;
    }

    public DoubleDateAndTimePickerDialogWithOutTab setListener(Listener listener) {
        this.listener = listener;
        return this;
    }

    public DoubleDateAndTimePickerDialogWithOutTab setCurved(boolean curved) {
        this.curved = curved;
        return this;
    }

    public DoubleDateAndTimePickerDialogWithOutTab setMinutesStep(int minutesStep) {
        this.minutesStep = minutesStep;
        return this;
    }

    public DoubleDateAndTimePickerDialogWithOutTab setMustBeOnFuture(boolean mustBeOnFuture) {
        this.mustBeOnFuture = mustBeOnFuture;
        return this;
    }

    public DoubleDateAndTimePickerDialogWithOutTab setMinDateRange(Date minDate) {
        this.minDate = minDate;
        return this;
    }

    public DoubleDateAndTimePickerDialogWithOutTab setMaxDateRange(Date maxDate) {
        this.maxDate = maxDate;
        return this;
    }

    public DoubleDateAndTimePickerDialogWithOutTab setDefaultDate(Date defaultDate) {
        this.defaultDate = defaultDate;
        return this;
    }

    public DoubleDateAndTimePickerDialogWithOutTab setDayFormatter(SimpleDateFormat dayFormatter) {
        this.dayFormatter = dayFormatter;
        return this;
    }

    public DoubleDateAndTimePickerDialogWithOutTab setCustomLocale(Locale locale) {
        this.customLocale = locale;
        return this;
    }

    public DoubleDateAndTimePickerDialogWithOutTab setDateNeedToShow(Date dateNeedToShow) {
        this.dateNeedToShow = dateNeedToShow;
        return this;
    }

//    public DoubleDateAndTimePickerDialogWithOutTab setTab1Date(Date tab1Date) {
//        this.tab1Date = tab1Date;
//        return this;
//    }
//
//    public DoubleDateAndTimePickerDialogWithOutTab setSecondDateAfterFirst(boolean secondDateAfterFirst) {
//        this.secondDateAfterFirst = secondDateAfterFirst;
//        return this;
//    }

    public DoubleDateAndTimePickerDialogWithOutTab setTab0DisplayDays(boolean tab0Days) {
        this.tab0Days = tab0Days;
        return this;
    }

    public DoubleDateAndTimePickerDialogWithOutTab setTab0DisplayHours(boolean tab0Hours) {
        this.tab0Hours = tab0Hours;
        return this;
    }

    public DoubleDateAndTimePickerDialogWithOutTab setTab0DisplayMinutes(boolean tab0Minutes) {
        this.tab0Minutes = tab0Minutes;
        return this;
    }

//    public DoubleDateAndTimePickerDialogWithOutTab setTab1DisplayDays(boolean tab1Days) {
//        this.tab1Days = tab1Days;
//        return this;
//    }
//
//    public DoubleDateAndTimePickerDialogWithOutTab setTab1DisplayHours(boolean tab1Hours) {
//        this.tab1Hours = tab1Hours;
//        return this;
//    }
//
//    public DoubleDateAndTimePickerDialogWithOutTab setTab1DisplayMinutes(boolean tab1Minutes) {
//        this.tab1Minutes = tab1Minutes;
//        return this;
//    }

    public DoubleDateAndTimePickerDialogWithOutTab setFocusable(boolean focusable) {
        bottomSheetHelper.setFocusable(focusable);
        return this;
    }

    private DoubleDateAndTimePickerDialogWithOutTab setTimeZone(TimeZone timeZone) {
        dateHelper.setTimeZone(timeZone);
//        single_date_and_time_picker.setTimeZone(timeZone);
//        pickerTab1.setTimeZone(timeZone);
        return this;
    }

    @Override
    public void display() {
        super.display();
        this.bottomSheetHelper.display();
    }

    @Override
    public void dismiss() {
        super.dismiss();
        bottomSheetHelper.dismiss();
    }

    @Override
    public void close() {
        super.close();
        bottomSheetHelper.hide();
    }

    protected void onClose() {
        super.onClose();
        if (listener != null && okClicked) {
            listener.onDateSelected(Arrays.asList(single_date_and_time_picker.getDate()));
        }
    }

    public interface Listener {
        void onDateSelected(List<Date> dates);
    }

    public static class Builder {

        private final Context context;
        @Nullable
        private DoubleDateAndTimePickerDialogWithOutTab.Listener listener;
        private boolean bottomSheet;
        private DoubleDateAndTimePickerDialogWithOutTab dialog;

        @Nullable
        private String tab0Text;
        @Nullable
        private String tab1Text;
        @Nullable
        private String title;
        @Nullable
        private Integer titleTextSize;
        @Nullable
        private Integer bottomSheetHeight;
        @Nullable
        private String buttonOkText;
        @Nullable
        private String todayText;

        private boolean curved;
        private boolean secondDateAfterFirst;
        private boolean mustBeOnFuture;
        private int minutesStep = STEP_MINUTES_DEFAULT;

        private SimpleDateFormat dayFormatter;

        private Locale customLocale;

        @ColorInt
        @Nullable
        private Integer backgroundColor = null;

        @ColorInt
        @Nullable
        private Integer mainColor = null;

        @ColorInt
        @Nullable
        private Integer titleTextColor = null;

        @Nullable
        private Date minDate;
        @Nullable
        private Date maxDate;
        @Nullable
        private Date defaultDate;
        @Nullable
        private Date tab0Date;
        @Nullable
        private Date tab1Date;

        private boolean tab0Days = true;
        private boolean tab0Hours = true;
        private boolean tab0Minutes = true;
        private boolean tab1Days = true;
        private boolean tab1Hours = true;
        private boolean tab1Minutes = true;
        private boolean focusable = false;
        private TimeZone timeZone;

        public Builder(Context context) {
            this.context = context;
        }

        public DoubleDateAndTimePickerDialogWithOutTab.Builder title(@Nullable String title) {
            this.title = title;
            return this;
        }

        public DoubleDateAndTimePickerDialogWithOutTab.Builder titleTextSize(@Nullable Integer titleTextSize) {
            this.titleTextSize = titleTextSize;
            return this;
        }

        public DoubleDateAndTimePickerDialogWithOutTab.Builder bottomSheetHeight(@Nullable Integer bottomSheetHeight) {
            this.bottomSheetHeight = bottomSheetHeight;
            return this;
        }

        public DoubleDateAndTimePickerDialogWithOutTab.Builder todayText(@Nullable String todayText) {
            this.todayText = todayText;
            return this;
        }

        public DoubleDateAndTimePickerDialogWithOutTab.Builder bottomSheet() {
            this.bottomSheet = true;
            return this;
        }

        public DoubleDateAndTimePickerDialogWithOutTab.Builder curved() {
            this.curved = true;
            return this;
        }

        public DoubleDateAndTimePickerDialogWithOutTab.Builder mustBeOnFuture() {
            this.mustBeOnFuture = true;
            return this;
        }

        public DoubleDateAndTimePickerDialogWithOutTab.Builder dayFormatter(SimpleDateFormat dayFormatter) {
            this.dayFormatter = dayFormatter;
            return this;
        }

        public DoubleDateAndTimePickerDialogWithOutTab.Builder customLocale(Locale locale) {
            this.customLocale = locale;
            return this;
        }

        public DoubleDateAndTimePickerDialogWithOutTab.Builder minutesStep(int minutesStep) {
            this.minutesStep = minutesStep;
            return this;
        }

        public DoubleDateAndTimePickerDialogWithOutTab.Builder titleTextColor(@NonNull @ColorInt int titleTextColor) {
            this.titleTextColor = titleTextColor;
            return this;
        }

        public DoubleDateAndTimePickerDialogWithOutTab.Builder backgroundColor(@NonNull @ColorInt int backgroundColor) {
            this.backgroundColor = backgroundColor;
            return this;
        }

        public DoubleDateAndTimePickerDialogWithOutTab.Builder mainColor(@NonNull @ColorInt int mainColor) {
            this.mainColor = mainColor;
            return this;
        }

        public DoubleDateAndTimePickerDialogWithOutTab.Builder minDateRange(Date minDate) {
            this.minDate = minDate;
            return this;
        }

        public DoubleDateAndTimePickerDialogWithOutTab.Builder maxDateRange(Date maxDate) {
            this.maxDate = maxDate;
            return this;
        }

        public DoubleDateAndTimePickerDialogWithOutTab.Builder defaultDate(Date defaultDate) {
            this.defaultDate = defaultDate;
            return this;
        }

        public DoubleDateAndTimePickerDialogWithOutTab.Builder tab0Date(Date tab0Date) {
            this.tab0Date = tab0Date;
            return this;
        }

        public DoubleDateAndTimePickerDialogWithOutTab.Builder tab1Date(Date tab1Date) {
            this.tab1Date = tab1Date;
            return this;
        }

        public DoubleDateAndTimePickerDialogWithOutTab.Builder listener(
                @Nullable DoubleDateAndTimePickerDialogWithOutTab.Listener listener) {
            this.listener = listener;
            return this;
        }

        public DoubleDateAndTimePickerDialogWithOutTab.Builder tab1Text(@Nullable String tab1Text) {
            this.tab1Text = tab1Text;
            return this;
        }

        public DoubleDateAndTimePickerDialogWithOutTab.Builder tab0Text(@Nullable String tab0Text) {
            this.tab0Text = tab0Text;
            return this;
        }

        public DoubleDateAndTimePickerDialogWithOutTab.Builder buttonOkText(@Nullable String buttonOkText) {
            this.buttonOkText = buttonOkText;
            return this;
        }

        public DoubleDateAndTimePickerDialogWithOutTab.Builder secondDateAfterFirst(boolean secondDateAfterFirst) {
            this.secondDateAfterFirst = secondDateAfterFirst;
            return this;
        }

        public DoubleDateAndTimePickerDialogWithOutTab.Builder setTab0DisplayDays(boolean tab0Days) {
            this.tab0Days = tab0Days;
            return this;
        }

        public DoubleDateAndTimePickerDialogWithOutTab.Builder setTab0DisplayHours(boolean tab0Hours) {
            this.tab0Hours = tab0Hours;
            return this;
        }

        public DoubleDateAndTimePickerDialogWithOutTab.Builder setTab0DisplayMinutes(boolean tab0Minutes) {
            this.tab0Minutes = tab0Minutes;
            return this;
        }

        public DoubleDateAndTimePickerDialogWithOutTab.Builder setTab1DisplayDays(boolean tab1Days) {
            this.tab1Days = tab1Days;
            return this;
        }

        public DoubleDateAndTimePickerDialogWithOutTab.Builder setTab1DisplayHours(boolean tab1Hours) {
            this.tab1Hours = tab1Hours;
            return this;
        }

        public DoubleDateAndTimePickerDialogWithOutTab.Builder setTab1DisplayMinutes(boolean tab1Minutes) {
            this.tab1Minutes = tab1Minutes;
            return this;
        }

        public DoubleDateAndTimePickerDialogWithOutTab.Builder setTimeZone(TimeZone timeZone) {
            this.timeZone = timeZone;
            return this;
        }

        public DoubleDateAndTimePickerDialogWithOutTab.Builder focusable() {
            this.focusable = true;
            return this;
        }

        public DoubleDateAndTimePickerDialogWithOutTab build() {
            final DoubleDateAndTimePickerDialogWithOutTab dialog = new DoubleDateAndTimePickerDialogWithOutTab(context, bottomSheet)
                    .setTitle(title)
                    .setTitleTextSize(titleTextSize)
                    .setBottomSheetHeight(bottomSheetHeight)
                    .setTodayText(todayText)
                    .setListener(listener)
                    .setCurved(curved)
                    .setButtonOkText(buttonOkText)
                    .setTab0Text(tab0Text)
                    .setTab1Text(tab1Text)
                    .setMinutesStep(minutesStep)
                    .setMaxDateRange(maxDate)
                    .setMinDateRange(minDate)
                    .setDefaultDate(defaultDate)
                    .setTab0DisplayDays(tab0Days)
                    .setTab0DisplayHours(tab0Hours)
                    .setTab0DisplayMinutes(tab0Minutes)
//                    .setTab1DisplayDays(tab1Days)
//                    .setTab1DisplayHours(tab1Hours)
//                    .setTab1DisplayMinutes(tab1Minutes)
                    .setDateNeedToShow(tab0Date)
//                    .setTab1Date(tab1Date)
                    .setDayFormatter(dayFormatter)
                    .setCustomLocale(customLocale)
                    .setMustBeOnFuture(mustBeOnFuture)
//                    .setSecondDateAfterFirst(secondDateAfterFirst)
                    .setTimeZone(timeZone)
                    .setFocusable(focusable);

            if (mainColor != null) {
                dialog.setMainColor(mainColor);
            }

            if (backgroundColor != null) {
                dialog.setBackgroundColor(backgroundColor);
            }

            if (titleTextColor != null) {
                dialog.setTitleTextColor(titleTextColor);
            }

            return dialog;
        }

        public void display() {
            dialog = build();
            dialog.display();
        }

        public void close() {
            if (dialog != null) {
                dialog.close();
            }
        }

        public void dismiss() {
            if (dialog != null) {
                dialog.dismiss();
            }
        }
    }
}
