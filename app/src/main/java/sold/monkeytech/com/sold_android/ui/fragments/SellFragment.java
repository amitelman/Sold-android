package sold.monkeytech.com.sold_android.ui.fragments;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.monkeytechy.framework.interfaces.Action;
import com.monkeytechy.framework.interfaces.TAction;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.Period;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sold.monkeytech.com.sold_android.R;
import sold.monkeytech.com.sold_android.databinding.FragmentSellBinding;
import sold.monkeytech.com.sold_android.framework.Utils.KeyboardAndInputUtils;
import sold.monkeytech.com.sold_android.framework.Utils.TextUtils;
import sold.monkeytech.com.sold_android.framework.managers.MetaDataManager;
import sold.monkeytech.com.sold_android.framework.managers.UserManager;
import sold.monkeytech.com.sold_android.framework.models.OpenHouse;
import sold.monkeytech.com.sold_android.framework.models.OpenHouseSlots;
import sold.monkeytech.com.sold_android.framework.models.User;
import sold.monkeytech.com.sold_android.framework.serverapi.auth.ApiGetCode;
import sold.monkeytech.com.sold_android.framework.serverapi.user.ApiPostUserProperty;
import sold.monkeytech.com.sold_android.ui.activities.SearchLocationActivity;
import sold.monkeytech.com.sold_android.ui.adapters.OpenHouseDaysAdapter;
import sold.monkeytech.com.sold_android.ui.adapters.OpenHouseHoursAdapter;
import sold.monkeytech.com.sold_android.ui.adapters.utils.ItemOffsetDecoration;
import sold.monkeytech.com.sold_android.ui.dialogs.VerificationDialog;
import sold.monkeytech.com.sold_android.ui.fragments.abs.BaseFragment;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class SellFragment extends BaseFragment implements View.OnClickListener {


    private static final int CHOOSE_CITY = 0;
    private static final int CHOOSE_STREET = 1;
    private FragmentSellBinding mBinding;
    private OpenHouseHoursAdapter hoursAdapter;
    private OpenHouseDaysAdapter daysAdapter;

    private String cityName = "";
    private String streetName;
    private int cityId = -1;
    private int streetId = -1;
    private int meetupDay = -1;
    private String meetupMonth = "";
    private String meetUpHour = "";

    private Map<String, Integer> monthMap;
    private Handler handler;

    public SellFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_sell, container, false);
        View view = mBinding.getRoot();

        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(getContext(), R.dimen.open_house_item_offset);
        daysAdapter = new OpenHouseDaysAdapter(null, getOnDayClickAction(), false);
        mBinding.sellFragDaysRecyclerView.setAdapter(daysAdapter);
        mBinding.sellFragDaysRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false)); //todo: recycler view direction set to locale
        mBinding.sellFragDaysRecyclerView.addItemDecoration(itemDecoration);

        hoursAdapter = new OpenHouseHoursAdapter(null, getOnHourClickAction(), false);
        mBinding.sellFragHoursRecyclerView.setAdapter(hoursAdapter);
        mBinding.sellFragHoursRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false)); //todo: recycler view direction set to locale
        mBinding.sellFragHoursRecyclerView.addItemDecoration(itemDecoration);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        initUi();

    }

    private void initUi() {
        mBinding.sellFragCity.setOnClickListener(this);
        mBinding.sellFragStreet.setOnClickListener(this);
        mBinding.privateShowActSendBtn.setOnClickListener(this);

        if (!TextUtils.isEmpty(UserManager.getInstance().getInAppToken())) {
            mBinding.sellFragFullNameLayout.setVisibility(View.GONE);
            mBinding.sellFragPhoneLayout.setVisibility(View.GONE);
            mBinding.sellFragEmailLayout.setVisibility(View.GONE);
        }

        initMeetUpCalender();
        handler = new Handler(Looper.getMainLooper());

    }

    private void initMeetUpCalender() {
        int meetDuration = MetaDataManager.getInstance().getIntValueFromString(MetaDataManager.MEETUP_DURATION);
        int meetWeeks = MetaDataManager.getInstance().getIntValueFromString(MetaDataManager.MEETUP_WEEKS);
        int meetStartAt = MetaDataManager.getInstance().getIntValueFromString(MetaDataManager.MEETUP_START_AT);
        int meetEndAt = MetaDataManager.getInstance().getIntValueFromString(MetaDataManager.MEETUP_END_AT);
        Log.d("wowCalender", "meetDuration: " + meetDuration + " meetMonth: " + meetWeeks + " meetStartAt: " + meetStartAt + " meetEndAt: " + meetEndAt);

        LocalDate nowDate = LocalDate.now();
        LocalDate endDate = nowDate.plusWeeks(meetWeeks);
        int between = Days.daysBetween(nowDate, endDate).getDays();
        Log.d("wowCalender", "now: " + nowDate.toString() + " end: " + endDate.toString() + " between: " + between);

        List<OpenHouse> openHouseList = new ArrayList<>();
        monthMap = new HashMap<>();
        for (int i = 0; i <= between; i++) {
            LocalDate dayObj = nowDate.plusDays(i);
            int day = dayObj.getDayOfMonth();
            String dayStr = dayObj.dayOfWeek().getAsText();
            String monthStr = dayObj.monthOfYear().getAsText();
            int month = dayObj.monthOfYear().get();
            monthMap.put(monthStr, month);
//            Log.d("wowCalender", "day: " + dayObj + "| day: " + day + ", " + dayStr + ", " + monthStr);

            DateTime startTime = new DateTime().withHourOfDay(meetStartAt).withMinuteOfHour(0);
            DateTime endTime = new DateTime().withHourOfDay(meetEndAt);
            Period p = new Period(startTime, endTime);
            int minutesBetweenHours = p.getHours() * 60;
//            Log.d("wowCalender", "minutesBetweenHours: " + minutesBetweenHours);

            List<OpenHouseSlots> slots = new ArrayList<>();
            for (int j = 0; j <= minutesBetweenHours; j += meetDuration) {

                int h1 = startTime.getHourOfDay();
                int min = startTime.getMinuteOfHour();
                OpenHouseSlots slot = new OpenHouseSlots(h1 + ":" + min + (min == 0 ? "0" : ""));
//                Log.d("wowCalender", "slot: " + slot.getName());
                slots.add(slot);
                startTime = startTime.plusMinutes(meetDuration);
            }
            OpenHouse openHouse = new OpenHouse(dayStr, day, monthStr, slots);
            openHouse.setId((long) i);
            openHouseList.add(openHouse);
        }
        daysAdapter.addItems(openHouseList);
    }

    private TAction<OpenHouse> getOnDayClickAction() {
        return new TAction<OpenHouse>() {
            @Override
            public void execute(OpenHouse openHouse) {
//                Toast.makeText(PropertyPageActivity.this, "wow", Toast.LENGTH_SHORT).show();
                meetUpHour = "";
                meetupDay = openHouse.getDay();
                meetupMonth = openHouse.getMonth();
                hoursAdapter.updateItems(openHouse.getSlots());
            }
        };
    }

    private TAction<OpenHouseSlots> getOnHourClickAction() {
        return new TAction<OpenHouseSlots>() {
            @Override
            public void execute(OpenHouseSlots openHouseSlots) {
                meetUpHour = openHouseSlots.getName();
            }
        };
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sellFragCity:
                Intent intent = new Intent(getContext(), SearchLocationActivity.class);
                intent.putExtra("type", CHOOSE_CITY);
                startActivityForResult(intent, CHOOSE_CITY);
                break;
            case R.id.sellFragStreet:
                if (TextUtils.isEmpty(cityName)) {
                    Toast.makeText(getContext(), "Please choose your city first", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent2 = new Intent(getContext(), SearchLocationActivity.class);
                    intent2.putExtra("type", CHOOSE_STREET);
                    intent2.putExtra("cityId", cityId);
                    startActivityForResult(intent2, CHOOSE_STREET);
                }
                break;
            case R.id.privateShowActSendBtn:
                if (validateFormFields()) {
                    if (TextUtils.isEmpty(UserManager.getInstance().getInAppToken())) {
                        signup();
                    } else {
                        sendForm();
                    }
                }

                break;

        }
    }

    private void signup() {
        String firstName = mBinding.sellFragFirstName.getText().toString();
        String lastName = mBinding.sellFragLastName.getText().toString();
        String email = mBinding.sellFragEmail.getText().toString();
        final String phone = mBinding.sellFragPhone.getText().toString();
        mBinding.sellFragPb.show();
        new ApiGetCode(getContext()).request(phone, firstName, lastName, email, new Action() {
            @Override
            public void execute() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        mBinding.sellFragPb.hide();
                        new VerificationDialog(getContext(), phone, new Action() {
                            @Override
                            public void execute() {
                                if (validateFormFields()) {
                                    sendForm();
                                }
                            }
                        }).show();
                    }
                });
            }
        }, new Action() {
            @Override
            public void execute() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getContext(), "Error accured, Please try again", Toast.LENGTH_SHORT).show();
                        mBinding.sellFragPb.hide();
                    }
                });
            }
        });
    }

//    private boolean validateSignupFields() {
//        if (TextUtils.isEmpty(mBinding.sellFragFirstName.getText().toString())) {
//            Toast.makeText(getContext(), "Please set your first name", Toast.LENGTH_SHORT).show();
//            return false;
//        }
//        if (TextUtils.isEmpty(mBinding.sellFragLastName.getText().toString())) {
//            Toast.makeText(getContext(), "Please set your last name", Toast.LENGTH_SHORT).show();
//            return false;
//        }
//        if (TextUtils.isEmpty(mBinding.sellFragEmail.getText().toString())) {
//            Toast.makeText(getContext(), "Please set your email", Toast.LENGTH_SHORT).show();
//            return false;
//        }
//        if (TextUtils.isEmpty(mBinding.sellFragPhone.getText().toString())) {
//            Toast.makeText(getContext(), "Please set your phone", Toast.LENGTH_SHORT).show();
//            return false;
//        }
//        return true;
//    }

    private void sendForm() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                mBinding.sellFragPb.show();
            }
        });
        int year = DateTime.now().getYear();

        String[] separated = meetUpHour.split(":");
        String hour = separated[0];
        String minute = separated[1];
        DateTime dt = new DateTime(year, monthMap.get(meetupMonth), meetupDay, Integer.valueOf(hour), Integer.valueOf(minute));
        Date date = dt.toDate();
        Log.d("wowMeet", "Date: " + date);
        String floorStr = mBinding.sellFragFloorNum.getText().toString();
        String houseNum = mBinding.sellFragHouseNum.getText().toString();
        String aptNum = mBinding.sellFragAptNum.getText().toString();
        String desc = mBinding.sellFragDescription.getText().toString();
        int floor = 0;
        if (floorStr.length() > 0)
            floor = Integer.parseInt(floorStr);
        new ApiPostUserProperty(getContext()).request(streetId, houseNum, aptNum,
                floor, desc, (int) date.getTime(), new Action() {
                    @Override
                    public void execute() {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                mBinding.sellFragPb.hide();
                            }
                        });
                        Log.d("wow", "success");
                    }
                }, new Action() {
                    @Override
                    public void execute() {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                mBinding.sellFragPb.hide();
                            }
                        });
                        Log.d("wow", "no success");
                    }
                });
    }

    private boolean validateFormFields() {
        if (TextUtils.isEmpty(UserManager.getInstance().getInAppToken())) {
            if (TextUtils.isEmpty(mBinding.sellFragFirstName.getText().toString())) {
                Toast.makeText(getContext(), "Please set your first name", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (TextUtils.isEmpty(mBinding.sellFragLastName.getText().toString())) {
                Toast.makeText(getContext(), "Please set your last name", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (TextUtils.isEmpty(mBinding.sellFragEmail.getText().toString())) {
                Toast.makeText(getContext(), "Please set your email", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (TextUtils.isEmpty(mBinding.sellFragPhone.getText().toString())) {
                Toast.makeText(getContext(), "Please set your phone", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        if (streetId == -1) {
            Toast.makeText(getContext(), "Please Choose Street", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(mBinding.sellFragHouseNum.getText().toString())) {
            Toast.makeText(getContext(), "Please set house number", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(mBinding.sellFragDescription.getText().toString())) {
            Toast.makeText(getContext(), "Please set description", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (meetupDay == -1 || TextUtils.isEmpty(meetUpHour)) {
            Toast.makeText(getContext(), "Please set meetup time", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!mBinding.privateShowActCb.isChecked()) {
            Toast.makeText(getContext(), "Please Confirm Privacy Policy", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        KeyboardAndInputUtils.closeKeyboard(getActivity());
        if (resultCode == RESULT_OK) {
            if (requestCode == CHOOSE_CITY) {
                cityName = data.getStringExtra("cityName");
                cityId = (int) data.getLongExtra("cityId", -1);
                mBinding.sellFragCity.setText(cityName);
            } else {
                streetName = data.getStringExtra("streetName");
                streetId = (int) data.getLongExtra("streetId", -1);
                mBinding.sellFragStreet.setText(streetName);
            }
        }
    }
}

